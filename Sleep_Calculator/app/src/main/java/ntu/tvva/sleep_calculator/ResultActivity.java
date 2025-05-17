package ntu.tvva.sleep_calculator;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import android.provider.AlarmClock;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class ResultActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        // Khởi tạo views
        TextView tvTitle = findViewById(R.id.tvTieuDe);
        TextView tvDescription = findViewById(R.id.tvMoTa);
        LinearLayout timeLayout = findViewById(R.id.layoutThoiGian);
        Button btnBack = findViewById(R.id.btnQuayLai);

        // Lấy chế độ từ Intent
        String mode = getIntent().getStringExtra("mode");

        if ("bedtime".equals(mode)) {
            calculateBedtime(tvTitle, tvDescription, timeLayout);
        } else {
            calculateWakeup(tvTitle, tvDescription, timeLayout);
        }

        // Xử lý nút quay lại
        btnBack.setOnClickListener(v -> finish());
    }

    private void calculateBedtime(TextView tvTitle, TextView tvDescription, LinearLayout timeLayout) {
        // Lấy thông tin thời gian từ Intent
        int hour = getIntent().getIntExtra("hour", 6);
        int minute = getIntent().getIntExtra("minute", 30);
        boolean isAM = getIntent().getBooleanExtra("isAM", true);

        // Tính các thời điểm đi ngủ
        Calendar targetTime = Calendar.getInstance();
        targetTime.set(Calendar.HOUR, hour);
        targetTime.set(Calendar.MINUTE, minute);
        targetTime.set(Calendar.SECOND, 0);
        targetTime.set(Calendar.AM_PM, isAM ? Calendar.AM : Calendar.PM);

        tvTitle.setText("Giờ đi ngủ");
        tvDescription.setText(String.format("Để thức dậy lúc %02d:%02d %s, bạn nên đi ngủ vào một trong các thời điểm sau:",
                hour, minute, isAM ? "AM" : "PM"));

        // Hiển thị các thời điểm đi ngủ
        SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm a", Locale.getDefault());
        for (int cycles = 6; cycles >= 1; cycles--) {
            Calendar sleepTime = (Calendar) targetTime.clone();
            sleepTime.add(Calendar.MINUTE, -15 - cycles * 90);
            addTimeToLayout(timeLayout, timeFormat.format(sleepTime.getTime()), cycles, sleepTime);
        }
    }

    private void calculateWakeup(TextView tvTitle, TextView tvDescription, LinearLayout timeLayout) {
        Calendar currentTime = Calendar.getInstance();
        currentTime.set(Calendar.SECOND, 0);

        tvTitle.setText("Giờ nên thức dậy");
        tvDescription.setText("Nếu bạn đi ngủ ngay bây giờ, bạn nên thức dậy vào một trong các thời điểm sau:");

        // Hiển thị các thời điểm thức dậy
        SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm a", Locale.getDefault());
        for (int cycles = 1; cycles <= 6; cycles++) {
            Calendar wakeupTime = (Calendar) currentTime.clone();
            wakeupTime.add(Calendar.MINUTE, 15 + cycles * 90);
            addTimeToLayout(timeLayout, timeFormat.format(wakeupTime.getTime()), cycles, wakeupTime);
        }
    }

    private void addTimeToLayout(LinearLayout layout, String time, int cycles, Calendar timeCalendar) {
        LinearLayout container = new LinearLayout(this);
        container.setOrientation(LinearLayout.VERTICAL);
        container.setBackgroundResource(R.drawable.bg_time_item);
        container.setPadding(32, 24, 32, 24);
        container.setClickable(true);
        container.setFocusable(true);

        // TextView cho thời gian
        TextView tvTime = new TextView(this);
        tvTime.setText(time);
        tvTime.setTextSize(22);
        tvTime.setTextColor(ContextCompat.getColor(this, R.color.white));
        
        // TextView cho thông tin chu kỳ
        TextView tvInfo = new TextView(this);
        String cycleInfo = cycles + " chu kỳ ngủ (" + (cycles * 90) + " phút)";
        String benefit = getCycleBenefit(cycles);
        tvInfo.setText(cycleInfo + "\n" + benefit);
        tvInfo.setTextSize(14);
        tvInfo.setTextColor(ContextCompat.getColor(this, R.color.white));
        tvInfo.setAlpha(0.8f);
        
        // Thêm các TextView vào container
        container.addView(tvTime);
        container.addView(tvInfo);
        
        // Thiết lập layout params
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, 
                LinearLayout.LayoutParams.WRAP_CONTENT);
        params.setMargins(0, 16, 0, 0);
        container.setLayoutParams(params);

        // Thêm click listener
        container.setOnClickListener(v -> {
            // Hiệu ứng khi click
            container.setAlpha(0.7f);
            container.postDelayed(() -> container.setAlpha(1.0f), 100);

            // Lấy giờ và phút từ timeCalendar
            int hour = timeCalendar.get(Calendar.HOUR_OF_DAY);
            int minute = timeCalendar.get(Calendar.MINUTE);

            // Tạo intent để set báo thức
            Intent intent = new Intent(AlarmClock.ACTION_SET_ALARM);
            intent.putExtra(AlarmClock.EXTRA_HOUR, hour);
            intent.putExtra(AlarmClock.EXTRA_MINUTES, minute);
            intent.putExtra(AlarmClock.EXTRA_MESSAGE, "Sleep Calculator");

            if (intent.resolveActivity(getPackageManager()) != null) {
                startActivity(intent);
                String message = "Đã mở đồng hồ báo thức.\nVui lòng xác nhận báo thức lúc " + time + 
                               "\n(" + getCycleBenefit(cycles) + ")";
                Toast.makeText(this, message, Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "Không tìm thấy ứng dụng đồng hồ báo thức trên thiết bị của bạn", Toast.LENGTH_SHORT).show();
            }
        });
        
        layout.addView(container);
    }

    private String getCycleBenefit(int cycles) {
        switch (cycles) {
            case 1:
                return "Ngủ 1 chu kỳ (~90 phút): Power nap ngắn, giúp tăng tỉnh táo tạm thời, phù hợp khi cần tập trung nhanh nhưng không thay thế giấc ngủ đầy đủ.";
            case 2:
                return "Ngủ 2 chu kỳ (~3 giờ): Giấc ngủ ngắn, cải thiện năng lượng tạm thời, nhưng không đủ để phục hồi toàn diện.";
            case 3:
                return "Ngủ 3 chu kỳ (~4.5 giờ): Giấc ngủ tối thiểu, có thể gây mệt mỏi do thức dậy giữa chu kỳ REM, chỉ nên dùng khi hạn chế về thời gian.";
            case 4:
                return "Ngủ 4 chu kỳ (~6 giờ): Giấc ngủ ngắn nhưng chấp nhận được, phù hợp để duy trì năng lượng cơ bản khi thiếu thời gian.";
            case 5:
                return "Ngủ 5 chu kỳ (~7.5 giờ): Giấc ngủ lý tưởng cho hầu hết người lớn, cân bằng giữa phục hồi thể chất và tinh thần.";
            case 6:
                return "Ngủ 6 chu kỳ (~9 giờ): Giấc ngủ tối ưu, hỗ trợ sức khỏe toàn diện, cải thiện trí nhớ và năng suất.";
            default:
                return "";
        }
    }
}   