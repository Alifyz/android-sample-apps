package alifyz.com.alarmmanagerapi;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.Calendar;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //Get an Instance of the Singleton Object - AlarmManager
    private AlarmManager getAlarmManager() {
        return  (AlarmManager) getApplicationContext().getSystemService(ALARM_SERVICE);
    }

    //Trigger a Pending Intent with 5 Minutes in Delay
    private void setPendingIntentWithAlarm() {
        PendingIntent pendingIntent = PendingIntent
                .getActivity(this,
                10,
                        new Intent(this, MainActivity.class),
                        PendingIntent.FLAG_UPDATE_CURRENT);

        AlarmManager alarm = getAlarmManager();
        long delay = TimeUnit.MINUTES.toMillis(1L);
        long currentTime = System.currentTimeMillis();

        //AlarmManager constants can be found in the following link:
        //https://developer.android.com/reference/android/app/AlarmManager

        //The first argument here is telling the AlarmManager to wake up the Phone if needed.
        alarm.set(AlarmManager.RTC_WAKEUP, currentTime+delay, pendingIntent);
    }

    //Trigger a Pending Intent with AlarmManager at 10:00 AM
    //The alarm will have a window of 5 minutes
    private void setPendingIntentWithCalendar() {
        PendingIntent pendingIntent = PendingIntent
                .getActivity(this,
                        10,
                        new Intent(this, MainActivity.class),
                        PendingIntent.FLAG_UPDATE_CURRENT);

        AlarmManager alarm = getAlarmManager();
        Calendar time = Calendar.getInstance();
        time.add(Calendar.DATE, 1); //Adding a new date.

        time.set(Calendar.HOUR_OF_DAY, 10); //10 AM
        time.set(Calendar.MINUTE, 0);       //10:00 AM
        time.set(Calendar.SECOND, 0);       //10:00:00 AM

        long window = TimeUnit.MINUTES.toMillis(5L);

        //SetWindow is only available at SDK >= 19
        if(Build.VERSION.SDK_INT >= 19) {
            alarm.setWindow(AlarmManager.RTC, time.getTimeInMillis(), window, pendingIntent);
        }
    }
}
