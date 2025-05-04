package ntu.tvva.sleepcalculator;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TimePicker;
import com.google.android.material.button.MaterialButton;
import androidx.appcompat.app.AppCompatActivity;

/**
 * Activity chính của ứng dụng, cho phép người dùng chọn thời gian và tính toán giờ ngủ/thức dậy
 */
public class MainActivity extends AppCompatActivity {
    private TimePicker boChonGio;
    private MaterialButton btnTinhGioDiNgu;
    private MaterialButton btnTinhGioThucDay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        anhXaThanhPhan();
        thietLapSuKien();
    }

    /**
     * Ánh xạ các thành phần giao diện từ layout
     */
    private void anhXaThanhPhan() {
        boChonGio = findViewById(R.id.boChonGio);
        btnTinhGioDiNgu = findViewById(R.id.btnTinhGioDiNgu);
        btnTinhGioThucDay = findViewById(R.id.btnTinhGioThucDay);
    }

    /**
     * Thiết lập các sự kiện cho các nút
     */
    private void thietLapSuKien() {
        btnTinhGioDiNgu.setOnClickListener(v -> {
            int gio = boChonGio.getHour();
            int phut = boChonGio.getMinute();
            chuyenManHinhKetQua("diNgu", gio, phut);
        });

        btnTinhGioThucDay.setOnClickListener(v -> {
            int gio = boChonGio.getHour();
            int phut = boChonGio.getMinute();
            chuyenManHinhKetQua("thucDay", gio, phut);
        });
    }

    /**
     * Chuyển sang màn hình kết quả với thông tin thời gian đã chọn
     * @param loai Loại tính toán ("diNgu" hoặc "thucDay")
     * @param gio Giờ đã chọn
     * @param phut Phút đã chọn
     */
    private void chuyenManHinhKetQua(String loai, int gio, int phut) {
        Intent intent = new Intent(this, KetQuaActivity.class);
        intent.putExtra("loai", loai);
        intent.putExtra("gio", gio);
        intent.putExtra("phut", phut);
        startActivity(intent);
        // Thêm hiệu ứng chuyển Activity
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }
}