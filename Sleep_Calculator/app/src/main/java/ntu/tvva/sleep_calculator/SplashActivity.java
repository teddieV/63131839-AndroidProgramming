package ntu.tvva.sleep_calculator;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {
    // Thời gian hiển thị màn hình chào (3 giây)
    private static final long THOI_GIAN_CHO = 3000;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        // Sau 3 giây, chuyển sang màn hình giới thiệu
        new Handler().postDelayed(() -> {
            // Tạo Intent để chuyển sang màn hình giới thiệu
            Intent intent = new Intent(SplashActivity.this, OnboardingActivity.class);
            startActivity(intent);
            finish(); // Đóng màn hình chào
        }, THOI_GIAN_CHO);
    }
}
