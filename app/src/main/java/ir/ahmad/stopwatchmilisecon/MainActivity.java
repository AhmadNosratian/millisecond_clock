package ir.ahmad.stopwatchmilisecon;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.widget.Button;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Date;


/**
 * Ahmad Nosratian created on 20/08/2020
 */

public class MainActivity extends AppCompatActivity {

    TextView txtTimer;
    Handler handler = new Handler();

    private Date today;
    long timeInMilliseconds = 0L;

    Runnable updateTimeThread = new Runnable() {
        @Override
        public void run() {
            timeInMilliseconds = System.currentTimeMillis() - today.getTime();

            long millis = timeInMilliseconds % 1000;
            long second = (timeInMilliseconds / 1000) % 60;
            long minute = (timeInMilliseconds / (1000 * 60)) % 60;
            long hour = (timeInMilliseconds / (1000 * 60 * 60)) % 24;

            txtTimer.setText(String.format("%02d:%02d:%02d.%03d", hour, minute, second, millis));
            handler.postDelayed(this, 0);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtTimer = findViewById(R.id.timerValue);

        today = clearTodayClock();
        handler.postDelayed(updateTimeThread, 0);
    }

    private Date clearTodayClock() {
        Date today = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(today);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.clear(Calendar.MINUTE);
        calendar.clear(Calendar.SECOND);
        calendar.clear(Calendar.MILLISECOND);
        return calendar.getTime();
    }
}