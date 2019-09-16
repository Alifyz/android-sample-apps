# Alarm Manager API - Sample

# TL;DR

This project aims to use the Alarm Manager API to schedule a simple notification at a specific time.  

```java
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
```


# Pre-requisites

  - Android Studio 2.3+
  - Android SDK Build Tools 23+
  - Target SDK (19) - Most of them. 
 
# Contact Info

- Email: alifyz@outlook.com
- Twitter: @AlifyzPires


License
----

MIT

Copyright - 2018

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.

