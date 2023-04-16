package dev.jonz94.capacitorjs.plugins.sim

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.telephony.SubscriptionManager
import com.getcapacitor.*
import com.getcapacitor.annotation.CapacitorPlugin
import com.getcapacitor.annotation.Permission
import com.getcapacitor.annotation.PermissionCallback

@SuppressLint("InlinedApi")
@CapacitorPlugin(
    name = "Sim",
    permissions = [
        Permission(
            strings = [
                Manifest.permission.READ_PHONE_STATE,
            ],
            alias = SimPlugin.READ_PHONE_STATE,
        ),
        // `READ_PHONE_NUMBERS` permission is required for api level >= 30
        Permission(
            strings = [
                Manifest.permission.READ_PHONE_NUMBERS,
            ],
            alias = SimPlugin.READ_PHONE_NUMBERS,
        ),
    ]
)
class SimPlugin : Plugin() {
    companion object {
        const val READ_PHONE_STATE = "readPhoneState"
        const val READ_PHONE_NUMBERS = "readPhoneNumbers"
        private const val PERMISSION_DENIED_ERROR = "Unable to get information from sim cards, user denied permission request"
    }

    @PluginMethod
    fun getSimCards(call: PluginCall) {
        if (!isPermissionGranted()) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                requestPermissionForAliases(arrayOf(READ_PHONE_NUMBERS, READ_PHONE_STATE), call, "permissionCallback")
            } else {
                requestPermissionForAlias(READ_PHONE_STATE, call, "permissionCallback")
            }
            return
        }

        val subscriptionManager = context.getSystemService(Context.TELEPHONY_SUBSCRIPTION_SERVICE) as SubscriptionManager

        @SuppressLint("MissingPermission")
        val subscriptionInfoList = subscriptionManager.activeSubscriptionInfoList

        val carrierInfoCollection = JSArray()
        if (subscriptionInfoList != null) {
            for (subscriptionInfo in subscriptionInfoList) {
                val carrierInfo = JSObject()
                carrierInfo.put("number", subscriptionInfo.number)
                carrierInfo.put("carrierName", subscriptionInfo.carrierName)
                carrierInfo.put("isoCountryCode", subscriptionInfo.countryIso)

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                    carrierInfo.put("mobileCountryCode", subscriptionInfo.mccString)
                    carrierInfo.put("mobileNetworkCode", subscriptionInfo.mncString)
                } else {
                    carrierInfo.put("mobileCountryCode", subscriptionInfo.mcc.toString())
                    carrierInfo.put("mobileNetworkCode", subscriptionInfo.mnc.toString())
                }

                carrierInfoCollection.put(carrierInfo)
            }
        }

        val ret = JSObject()
        ret.put("simCards", carrierInfoCollection)

        call.resolve(ret)
    }

    @PermissionCallback
    private fun permissionCallback(call: PluginCall) {
        if (!isPermissionGranted()) {
            Logger.debug(logTag, "User denied read phone state permission");
            call.reject(PERMISSION_DENIED_ERROR);
            return;
        }

        when (call.methodName) {
            "getSimCards" -> getSimCards(call)
        }
    }

    private fun isPermissionGranted(): Boolean {
        // for api level >= 30 (android >= 11), `READ_PHONE_NUMBERS` permission is required.
        // if `READ_PHONE_NUMBERS` permission is not present, the return phone number will be empty string in some cases.
        // see: https://developer.android.com/reference/android/telephony/SubscriptionInfo#getNumber()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            return getPermissionState(READ_PHONE_NUMBERS) == PermissionState.GRANTED && getPermissionState(READ_PHONE_STATE) == PermissionState.GRANTED;
        }

        return getPermissionState(READ_PHONE_STATE) == PermissionState.GRANTED;
    }
}
