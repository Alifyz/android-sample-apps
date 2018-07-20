# Android - Services Sample Code

## Install

* Android Studio 2.3 or Higher
* Build Tools 23+
* Target SDK: API Level 26
* ``` git clone https://github.com/Alifyz/Android.git ```

## Libraries & Features

* Service basic Implementation
* IntentService Implementation
* Simple AlarmManager Implementation
* JobDispacher Code Sample
* IPC - Inner-Processing Communication


## Code Sample

### Service + Notification Result
```java
public class ServiceNotification extends IntentService {

    private static final String SERVICE_NAME = "ServiceNotification";
    private static final int NOTIFICATION_ID = 10;

    public ServiceNotification() {
        super(SERVICE_NAME);
    }
    /*
       The instructions inside the onHandleIntent are parsed inside a HandlerThread.
       However, to make it more powerful or more Thread friendly, the usage of AsyncTask
       or any other mechanism of background thread is advised.
     */
    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        try {
            // Perform some calculation
            Thread.sleep(4000);
            notifyUser();
        } catch (Exception e) {
            // Handle Execption
        }
    }
    /*
        Builds a notification and notify the user when the calculations inside the service are done.
     */
    private void notifyUser() {
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("Service is done!")
                .setChannelId("Default Channel")
                .setContentText("The service already finishes its job");
        NotificationManager notificationManager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(NOTIFICATION_ID, notificationBuilder.build());
    }
}
```

### Service + PendingIntent Result
```java
public class ServicePendingIntent extends IntentService {

    private static final String SERVICE_NAME = "PendingIntentServoce";
    private static final int PENDING_RESULT_CODE = 0;

    public ServicePendingIntent() {
        super(SERVICE_NAME);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        if(intent != null) {
            PendingIntent pendingIntent = intent.getParcelableExtra("PENDING");
            try {
                //Background Work!
                //Task that can take processing power and needs to be done outside the main thread.
                Thread.sleep(4000); // Just for testing purposes!
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                //When the Service is done and all calculations were finished!
                //Launch the Activity to the User.
                try {
                    pendingIntent.send(this, PENDING_RESULT_CODE, new Intent());
                    stopSelf();
                } catch (PendingIntent.CanceledException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
```

### Service + OnGoing Notification Progress
```java
public class ServiceLoadingIndicator extends IntentService {

    private static final String CHANEL_ID = "ChanelID";
    private static final int NOTIFICATION_ID = 50;
    private static final int MAX_PROGRESS = 100;
    private static int CURRENT_PROGRESS = 0;

    private NotificationCompat.Builder notificationBuilder;
    private NotificationManager notificationManagerCompat;

    public ServiceLoadingIndicator() {
        super("Service");
    }

    @Override
    public void onCreate() {
        super.onCreate();

        notificationManagerCompat =
                (NotificationManager) getApplicationContext()
                        .getSystemService(getApplicationContext().NOTIFICATION_SERVICE);

        notificationBuilder = new NotificationCompat.Builder(this, CHANEL_ID);

        notificationBuilder
                .setContentTitle("Notification Progress")
                .setContentText("Downloading something")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setPriority(NotificationCompat.PRIORITY_LOW);

        notifyUsers(notificationManagerCompat, notificationBuilder);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        Timer timerTask = new Timer();
        timerTask.schedule(new TimerTask() {
            @Override
            public void run() {
                CURRENT_PROGRESS++;
                notifyUsers(notificationManagerCompat, notificationBuilder);
            }
        }, 0, 1000);

        if (CURRENT_PROGRESS >= 100) {
            timerTask.cancel();
        }
    }
    //Implement Notitication Channels for Android Oreo and Above.
    //Ongoing Notification reporting progress.
    private void notifyUsers(NotificationManager m, NotificationCompat.Builder b) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            NotificationChannel channel = new NotificationChannel("CHANNEL101",
                    "Default Channel", NotificationManager.IMPORTANCE_DEFAULT);

            m.createNotificationChannel(channel);
            b.setChannelId("CHANNEL101");
            b.setOnlyAlertOnce(true);

            b.setProgress(MAX_PROGRESS, CURRENT_PROGRESS, false);
            m.notify(NOTIFICATION_ID, notificationBuilder.build());
        } else {
            b.setProgress(MAX_PROGRESS, CURRENT_PROGRESS, false);
            b.setChannelId("CHANNEL101");
            b.setOnlyAlertOnce(true);
            m.notify(NOTIFICATION_ID, notificationBuilder.build());
        }
    }
}
```

## Contact

* alifyz@outlook.com

## License

Copyright 2018 
Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
