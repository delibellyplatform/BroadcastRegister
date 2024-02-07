package cordova.plugin.broadcast.register;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * This class echoes a string called from JavaScript.
 */
public class BroadcastRegister extends CordovaPlugin {

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        // if (action.equals("coolMethod")) {
        //     String message = args.getString(0);
        //     this.coolMethod(message, callbackContext);
        //     return true;
        // }
        if(action.equals('add')) {
            this.add(args, callbackContext);
            return true;
        }
        return false;
    }

    // private void coolMethod(String message, CallbackContext callbackContext) {
    //     if (message != null && message.length() > 0) {
    //         callbackContext.success(message);
    //     } else {
    //         callbackContext.error("Expected one non-empty string argument.");
    //     }
    // }

    private void add(JSONArray args, CallbackContext callbackContext) {
        if(args != null) {
            try {
                let n1 = Integer.parseInt(args.getJSONObject(0).getString('param1'));
                let n2 = Integer.parseInt(args.getJSONObject(0).getString('param2'));
                callbackContext.success(""+ (n1 + n2));

            }catch(Exception ex) {
                callbackContext.error("something went wrong: " + ex);
            }
        }
        else {
            callbackContext.error('parameters should not be undefined')''
        }
    }
}
