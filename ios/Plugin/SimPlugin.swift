import Foundation
import Capacitor
import CoreTelephony

@objc(SimPlugin)
public class SimPlugin: CAPPlugin {
    @objc func getSimCards(_ call: CAPPluginCall) {
        if let carrierCollection = CTTelephonyNetworkInfo().serviceSubscriberCellularProviders {
            var carrierInfoCollection: [[String: Any]] = []
            for (_, carrier) in carrierCollection {
                var carrierInfo: [String: Any] = [:]
                carrierInfo["allowsVOIP"] = carrier.allowsVOIP
                carrierInfo["carrierName"] = carrier.carrierName
                carrierInfo["isoCountryCode"] = carrier.isoCountryCode
                carrierInfo["mobileCountryCode"] = carrier.mobileCountryCode
                carrierInfo["mobileNetworkCode"] = carrier.mobileNetworkCode
                carrierInfoCollection.append(carrierInfo)
            }
            call.resolve([
                "simCards": carrierInfoCollection
            ])
        } else {
            call.resolve([
                "simCards": []
            ])
        }
    }

    @objc override public func checkPermissions(_ call: CAPPluginCall) {
        call.resolve([
            "readSimCard": "granted"
        ])
    }

    @objc override public func requestPermissions(_ call: CAPPluginCall) {
        call.resolve([
            "readSimCard": "granted"
        ])
    }
}
