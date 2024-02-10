package cordova.plugin.broadcast.register;

import cordova.plugin.broadcast.register.*;

import android.annotation.SuppressLint;
import android.app.PendingIntent;
import android.app.Activity;
import android.content.*;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.pm.LabeledIntent;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.text.Html;
import android.util.Base64;
import android.view.Gravity;
import android.widget.Toast;
import android.util.Log;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.PluginResult;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import android.content.Context;

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
        if(action.equals("add")) {
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
                int n1 = Integer.parseInt(args.getJSONObject(0).getString("param1"));
                int n2 = Integer.parseInt(args.getJSONObject(0).getString("param2"));
                callbackContext.success(""+ (n1 + n2));

            }catch(Exception ex) {
                callbackContext.error("something went wrong: " + ex);
            }
        }
        else {
            callbackContext.error("parameters should not be undefined");
        }
    }

        private void openApplication(JSONArray args, CallbackContext callbackContext) {
        if(args != null) {
            try {
                final String packageName = args.getJSONObject(0).getString("package");
                cordova.getThreadPool().execute(new SocialSharingRunnable(callbackContext) {
                public void Run() {
                    final Intent intent = new Intent(Intent.ACTION_VIEW);
                    try {
                        // intent.setPackage(packageName);
                        intent.setData(Uri.parse("market://details?id=" + packageName));
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        cordova.getActivity().runOnUiThread(new Runnable() {
                            public void run() {
                            try {
                                startActivity(intent);
                            } catch (Exception e) {
                                callbackContext.error(e.getMessage());
                            }
                            }
                        });
                    }catch(Exception ex) {
                    callbackContext.error(e.getMessage());
                    }
                } 
                })
                return true;
            }catch(Exception ex) {
               callbackContext.error(e.getMessage()); 
            }
        }
    }
}
