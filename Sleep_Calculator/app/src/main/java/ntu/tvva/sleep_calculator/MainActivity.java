package ntu.tvva.sleep_calculator;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TimePicker;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.card.MaterialCardView;

import ntu.tvva.sleep_calculator.R;
import ntu.tvva.sleep_calculator.ResultActivity;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {    
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Khởi tạo views
        TimePicker timePicker = findViewById(R.id.boChonThoiGian);
        MaterialButton btnCalculateBedtime = findViewById(R.id.btnTinhGioNgu);
        MaterialButton btnCalculateWakeup = findViewById(R.id.btnTinhGioThuc);
        MaterialCardView timePickerCard = findViewById(R.id.timePickerCard);

        // Load animations
        Animation fadeIn = AnimationUtils.loadAnimation(this, R.anim.fade_in);
        Animation slideUp = AnimationUtils.loadAnimation(this, R.anim.slide_up);

        // Apply animations
        timePickerCard.startAnimation(fadeIn);
        btnCalculateBedtime.startAnimation(slideUp);
        btnCalculateWakeup.startAnimation(slideUp);

        // Cài đặt hiển thị 12 giờ
        timePicker.setIs24HourView(false);

        // Xử lý nút tính giờ đi ngủ
        btnCalculateBedtime.setOnClickListener(v -> {
            // Thêm hiệu ứng ripple
            v.setPressed(true);
            v.postDelayed(() -> v.setPressed(false), 100);

            int hour = timePicker.getHour();
            int minute = timePicker.getMinute();
            boolean isAM = hour < 12;
            
            // Chuyển đổi sang định dạng 12 giờ
            if (hour == 0) hour = 12;
            if (hour > 12) hour -= 12;

            // Chuyển sang màn hình kết quả với animation
            Intent intent = new Intent(this, ResultActivity.class);
            intent.putExtra("mode", "bedtime");
            intent.putExtra("hour", hour);
            intent.putExtra("minute", minute);
            intent.putExtra("isAM", isAM);
            startActivity(intent);
            overridePendingTransition(R.anim.slide_up, R.anim.fade_in);
        });

        // Xử lý nút tính giờ thức dậy
        btnCalculateWakeup.setOnClickListener(v -> {
            // Thêm hiệu ứng ripple
            v.setPressed(true);
            v.postDelayed(() -> v.setPressed(false), 100);

            Intent intent = new Intent(this, ResultActivity.class);
            intent.putExtra("mode", "wakeup");
            startActivity(intent);
            overridePendingTransition(R.anim.slide_up, R.anim.fade_in);
        });
    }
}