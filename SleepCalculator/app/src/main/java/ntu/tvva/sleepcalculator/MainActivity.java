package ntu.tvva.sleepcalculator;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TimePicker;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.button.MaterialButton;
import java.util.Calendar;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private TimePicker boChonGio;
    private MaterialButton btnTinhGioDiNgu;
    private MaterialButton btnTinhGioThucDay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Ánh xạ các thành phần
        boChonGio = findViewById(R.id.boChonGio);
        btnTinhGioDiNgu = findViewById(R.id.btnTinhGioDiNgu);
        btnTinhGioThucDay = findViewById(R.id.btnTinhGioThucDay);

        // Xử lý sự kiện nút tính giờ đi ngủ
        btnTinhGioDiNgu.setOnClickListener(v -> {
            int gio = boChonGio.getHour();
            int phut = boChonGio.getMinute();
            tinhGioDiNgu(gio, phut);
        });

        // Xử lý sự kiện nút tính giờ thức dậy
        btnTinhGioThucDay.setOnClickListener(v -> {
            Calendar hienTai = Calendar.getInstance();
            int gio = hienTai.get(Calendar.HOUR_OF_DAY);
            int phut = hienTai.get(Calendar.MINUTE);
            tinhGioThucDay(gio, phut);
        });
    }

    private void tinhGioDiNgu(int gioThucDay, int phutThucDay) {
        ArrayList<String> danhSachGio = new ArrayList<>();
        danhSachGio.add(dinhDangGio(gioThucDay, phutThucDay)); // Thêm giờ thức dậy vào đầu danh sách

        // Tính 6 giờ đi ngủ (3-8 chu kỳ)
        for (int soChuKy = 8; soChuKy >= 3; soChuKy--) {
            int tongPhut = soChuKy * 90 + 15; // 90 phút/chu kỳ + 15 phút để ngủ
            int gioDiNgu = gioThucDay;
            int phutDiNgu = phutThucDay - tongPhut;

            // Xử lý khi phút < 0
            while (phutDiNgu < 0) {
                phutDiNgu += 60;
                gioDiNgu--;
            }

            // Xử lý khi giờ < 0
            while (gioDiNgu < 0) {
                gioDiNgu += 24;
            }

            // Thêm thông tin về số chu kỳ vào giờ
            String gioDinhDang = dinhDangGio(gioDiNgu, phutDiNgu);
            danhSachGio.add(gioDinhDang + " (" + soChuKy + " chu kỳ)");
        }

        // Chuyển sang màn hình kết quả
        Intent intent = new Intent(this, KetQuaActivity.class);
        intent.putExtra("loaiTinhToan", "diNgu");
        intent.putStringArrayListExtra("cacGio", danhSachGio);
        startActivity(intent);
    }

    private void tinhGioThucDay(int gioDiNgu, int phutDiNgu) {
        ArrayList<String> danhSachGio = new ArrayList<>();
        danhSachGio.add(dinhDangGio(gioDiNgu, phutDiNgu)); // Thêm giờ đi ngủ vào đầu danh sách

        // Tính 6 giờ thức dậy (3-8 chu kỳ)
        for (int soChuKy = 3; soChuKy <= 8; soChuKy++) {
            int tongPhut = soChuKy * 90 + 15; // 90 phút/chu kỳ + 15 phút để ngủ
            int gioThucDay = gioDiNgu;
            int phutThucDay = phutDiNgu + tongPhut;

            // Xử lý khi phút >= 60
            while (phutThucDay >= 60) {
                phutThucDay -= 60;
                gioThucDay++;
            }

            // Xử lý khi giờ >= 24
            while (gioThucDay >= 24) {
                gioThucDay -= 24;
            }

            // Thêm thông tin về số chu kỳ vào giờ
            String gioDinhDang = dinhDangGio(gioThucDay, phutThucDay);
            danhSachGio.add(gioDinhDang + " (" + soChuKy + " chu kỳ)");
        }

        // Chuyển sang màn hình kết quả
        Intent intent = new Intent(this, KetQuaActivity.class);
        intent.putExtra("loaiTinhToan", "thucDay");
        intent.putStringArrayListExtra("cacGio", danhSachGio);
        startActivity(intent);
    }

    private String dinhDangGio(int gio, int phut) {
        String sangChieu = "AM";
        if (gio >= 12) {
            sangChieu = "PM";
            if (gio > 12) {
                gio -= 12;
            }
        }
        if (gio == 0) {
            gio = 12;
        }
        return String.format("%d:%02d %s", gio, phut, sangChieu);
    }
}