package cordova.plugin.broadcast.register;

import cordova.plugin.broadcast.register.*;

import org.apache.cordova.*;
import android.widget.Toast;
import android.content.Context;

import com.onesignal.notifications.IActionButton;
import com.onesignal.notifications.IDisplayableMutableNotification;
import com.onesignal.notifications.INotificationReceivedEvent;
import com.onesignal.notifications.INotificationServiceExtension;

public class NotificationServiceExtension implements INotificationServiceExtension {

   @Override
   public void onNotificationReceived(INotificationReceivedEvent event) {
      Context context = this.cordova.getActivity().getApplicationContext();
      int duration = Toast.LENGTH_LONG;

      IDisplayableMutableNotification notification = event.getNotification();

      Toast toast = Toast.makeText(context, "Hello World!", duration);
         toast.show();

      if (notification.getActionButtons() != null) {
         for (IActionButton button : notification.getActionButtons()) {
            // you can modify your action buttons here
         }
      }

      if(notification.getTitle() != null) {
      Toast.makeText(context, notification.getTitle(), Toast.LENGTH_LONG).show();
      }

     // this is an example of how to modify the notification by changing the background color to blue
      notification.setExtender(builder -> builder.setColor(0xFF0000FF));
      notification.complete();
      
   }
}