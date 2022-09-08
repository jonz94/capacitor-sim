package dev.jonz94.capacitorjs.plugins.sim

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.telephony.SubscriptionManager
import com.getcapacitor.*
import com.getcapacitor.annotation.CapacitorPlugin
import com.getcapacitor.annotation.Permission
import com.getcapacitor.annotation.PermissionCallback

@CapacitorPlugin(
    name = "Sim",
    permissions = [
        Permission(
            strings = [
                Manifest.permission.READ_PHONE_STATE,
            ],
            alias = SimPlugin.READ_PHONE_STATE,
        ),
    ]
)
class SimPlugin : Plugin() {
    companion object {
        const val READ_PHONE_STATE = "readSimCard"
        private const val PERMISSION_DENIED_ERROR = "Unable to get information from sim cards, user denied permission request"
    }

    @PluginMethod
    fun getSimCards(call: PluginCall) {
        if (!isPermissionGranted()) {
            requestAllPermissions(call, "permissionCallback");
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
                carrierInfo.put("mobileCountryCode", subscriptionInfo.mccString)
                carrierInfo.put("mobileNetworkCode", subscriptionInfo.mncString)
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
        return getPermissionState(READ_PHONE_STATE) == PermissionState.GRANTED;
    }
}
