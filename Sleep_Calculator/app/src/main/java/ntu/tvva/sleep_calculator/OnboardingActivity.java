package ntu.tvva.sleep_calculator;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;
import java.util.ArrayList;
import java.util.List;

public class OnboardingActivity extends AppCompatActivity {
    private ViewPager2 viewPager;
    private LinearLayout indicatorLayout;
    private Button btnNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onboarding);

        // Khởi tạo views
        viewPager = findViewById(R.id.viewPager);
        indicatorLayout = findViewById(R.id.indicatorLayout);
        btnNext = findViewById(R.id.btnNext);

        // Tạo danh sách các trang onboarding
        List<OnboardingItem> items = new ArrayList<>();
        items.add(new OnboardingItem(R.drawable.ic_moon, "Tính toán giấc ngủ", 
            "Tính toán thời gian ngủ tối ưu dựa trên chu kỳ giấc ngủ của bạn"));
        items.add(new OnboardingItem(R.drawable.ic_moon, "Chu kỳ giấc ngủ", 
            "Mỗi chu kỳ giấc ngủ kéo dài khoảng 90 phút, và bạn cần 4-6 chu kỳ để có giấc ngủ chất lượng"));
        items.add(new OnboardingItem(R.drawable.ic_moon, "Bắt đầu ngay", 
            "Hãy bắt đầu tính toán thời gian ngủ tối ưu cho bạn"));

        // Cài đặt ViewPager
        OnboardingAdapter adapter = new OnboardingAdapter(items);
        viewPager.setAdapter(adapter);

        // Tạo indicators
        for (int i = 0; i < items.size(); i++) {
            ImageView indicator = new ImageView(this);
            indicator.setImageDrawable(getDrawable(i == 0 ? R.drawable.indicator_active : R.drawable.indicator_inactive));
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            params.setMargins(8, 0, 8, 0);
            indicator.setLayoutParams(params);
            indicatorLayout.addView(indicator);
        }

        // Xử lý sự kiện chuyển trang
        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                // Cập nhật indicators
                for (int i = 0; i < indicatorLayout.getChildCount(); i++) {
                    ImageView indicator = (ImageView) indicatorLayout.getChildAt(i);
                    indicator.setImageDrawable(getDrawable(
                            i == position ? R.drawable.indicator_active : R.drawable.indicator_inactive));
                }
                // Cập nhật text nút
                btnNext.setText(position == items.size() - 1 ? "Bắt đầu" : "Tiếp tục");
            }
        });

        // Xử lý nút tiếp tục
        btnNext.setOnClickListener(v -> {
            if (viewPager.getCurrentItem() + 1 < items.size()) {
                viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
            } else {
                startActivity(new Intent(OnboardingActivity.this, MainActivity.class));
                finish();
            }
        });
    }
} 