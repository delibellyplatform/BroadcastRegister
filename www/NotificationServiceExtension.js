var exec = require('cordova/exec');


module.exports.onNotificationReceived = function (arg0, success, error) {
    exec(success, error, "NotificationServiceExtension","onNotificationReceived", [arg0]);
}