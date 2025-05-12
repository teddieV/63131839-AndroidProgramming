package ntu.tvva.test123;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TimePicker;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class CalculateWakeTimeActivity extends AppCompatActivity {
    private TimePicker timePicker;
    private Button btnCalculate;
    private static final int SLEEP_CYCLE_MINUTES = 90;
    private static final int FALL_ASLEEP_MINUTES = 14;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculate_wake_time);

        timePicker = findViewById(R.id.timePicker);
        btnCalculate = findViewById(R.id.btnCalculate);

        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int hour = timePicker.getHour();
                int minute = timePicker.getMinute();
                
                List<String> wakeTimes = calculateWakeTimes(hour, minute);
                
                Intent intent = new Intent(CalculateWakeTimeActivity.this, ResultActivity.class);
                intent.putStringArrayListExtra("times", new ArrayList<>(wakeTimes));
                intent.putExtra("isBedtime", false);
                startActivity(intent);
            }
        });
    }

    private List<String> calculateWakeTimes(int sleepHour, int sleepMinute) {
        List<String> wakeTimes = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, sleepHour);
        calendar.set(Calendar.MINUTE, sleepMinute);
        
        // Add fall asleep time
        calendar.add(Calendar.MINUTE, FALL_ASLEEP_MINUTES);
        
        // Calculate 4 sleep cycles forward
        for (int i = 1; i <= 4; i++) {
            calendar.add(Calendar.MINUTE, SLEEP_CYCLE_MINUTES);
            int hour = calendar.get(Calendar.HOUR_OF_DAY);
            int minute = calendar.get(Calendar.MINUTE);
            String amPm = hour < 12 ? "AM" : "PM";
            if (hour > 12) hour -= 12;
            if (hour == 0) hour = 12;
            wakeTimes.add(String.format("%d:%02d %s", hour, minute, amPm));
        }
        
        return wakeTimes;
    }
} 