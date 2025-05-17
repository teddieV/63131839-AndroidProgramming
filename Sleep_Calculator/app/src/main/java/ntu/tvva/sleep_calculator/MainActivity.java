package ntu.tvva.sleep_calculator;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TimePicker;
import androidx.appcompat.app.AppCompatActivity;

import ntu.tvva.sleep_calculator.R;
import ntu.tvva.sleep_calculator.ResultActivity;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {    
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Khởi tạo views
        TimePicker timePicker = findViewById(R.id.boChonThoiGian);
        Button btnCalculateBedtime = findViewById(R.id.btnTinhGioNgu);
        Button btnCalculateWakeup = findViewById(R.id.btnTinhGioThuc);
        // Cài đặt hiển thị 12 giờ
        timePicker.setIs24HourView(false);
        // Xử lý nút tính giờ đi ngủ
        btnCalculateBedtime.setOnClickListener(v -> {
            int hour = timePicker.getHour();
            int minute = timePicker.getMinute();
            boolean isAM = hour < 12;
            // Chuyển đổi sang định dạng 12 giờ
            if (hour == 0) hour = 12;
            if (hour > 12) hour -= 12;
            // Chuyển sang màn hình kết quả
            Intent intent = new Intent(this, ResultActivity.class);
            intent.putExtra("mode", "bedtime");
            intent.putExtra("hour", hour);
            intent.putExtra("minute", minute);
            intent.putExtra("isAM", isAM);
            startActivity(intent);
        });
        // Xử lý nút tính giờ thức dậy
        btnCalculateWakeup.setOnClickListener(v -> {
            Intent intent = new Intent(this, ResultActivity.class);
            intent.putExtra("mode", "wakeup");
            startActivity(intent);
        });
    }
}