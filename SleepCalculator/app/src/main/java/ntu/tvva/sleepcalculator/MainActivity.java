package ntu.tvva.sleepcalculator;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TimePicker;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.button.MaterialButton;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    // Khai báo các thành phần giao diện
    private TimePicker boChonGio;
    private MaterialButton btnTinhGioDiNgu;
    private MaterialButton btnTinhGioThucDay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Ánh xạ các thành phần từ layout
        boChonGio = findViewById(R.id.boChonGio);
        btnTinhGioDiNgu = findViewById(R.id.btnTinhGioDiNgu);
        btnTinhGioThucDay = findViewById(R.id.btnTinhGioThucDay);

        // Xử lý sự kiện khi nhấn nút "Tính giờ đi ngủ"
        btnTinhGioDiNgu.setOnClickListener(v -> {
            // Lấy giờ và phút từ TimePicker
            int gioThucDay = boChonGio.getHour();
            int phutThucDay = boChonGio.getMinute();
            
            // Gọi hàm tính giờ đi ngủ
            tinhGioDiNgu(gioThucDay, phutThucDay);
        });

        // Xử lý sự kiện khi nhấn nút "Tính giờ thức dậy"
        btnTinhGioThucDay.setOnClickListener(v -> {
            // Lấy giờ và phút từ TimePicker
            int gioDiNgu = boChonGio.getHour();
            int phutDiNgu = boChonGio.getMinute();
            
            // Gọi hàm tính giờ thức dậy
            tinhGioThucDay(gioDiNgu, phutDiNgu);
        });
    }

    // Hàm tính giờ đi ngủ dựa trên giờ thức dậy
    private void tinhGioDiNgu(int gioThucDay, int phutThucDay) {
        // Danh sách để lưu các giờ đi ngủ
        ArrayList<String> danhSachGio = new ArrayList<>();
        
        // Thêm giờ thức dậy vào đầu danh sách
        danhSachGio.add(dinhDangGio(gioThucDay, phutThucDay));

        // Tính 6 giờ đi ngủ (từ 3 đến 8 chu kỳ)
        for (int soChuKy = 8; soChuKy >= 3; soChuKy--) {
            // Mỗi chu kỳ ngủ là 90 phút, cộng thêm 15 phút để ngủ
            int tongPhut = soChuKy * 90 + 15;
            
            // Tính giờ đi ngủ bằng cách trừ tổng số phút từ giờ thức dậy
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

            // Thêm giờ đi ngủ và số chu kỳ vào danh sách
            String gioDinhDang = dinhDangGio(gioDiNgu, phutDiNgu);
            danhSachGio.add(gioDinhDang + " (" + soChuKy + " chu kỳ)");
        }

        // Chuyển sang màn hình kết quả
        chuyenManHinhKetQua("diNgu", danhSachGio);
    }

    // Hàm tính giờ thức dậy dựa trên giờ đi ngủ
    private void tinhGioThucDay(int gioDiNgu, int phutDiNgu) {
        // Danh sách để lưu các giờ thức dậy
        ArrayList<String> danhSachGio = new ArrayList<>();
        
        // Thêm giờ đi ngủ vào đầu danh sách
        danhSachGio.add(dinhDangGio(gioDiNgu, phutDiNgu));

        // Tính 6 giờ thức dậy (từ 3 đến 8 chu kỳ)
        for (int soChuKy = 3; soChuKy <= 8; soChuKy++) {
            // Mỗi chu kỳ ngủ là 90 phút, cộng thêm 15 phút để ngủ
            int tongPhut = soChuKy * 90 + 15;
            
            // Tính giờ thức dậy bằng cách cộng tổng số phút vào giờ đi ngủ
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

            // Thêm giờ thức dậy và số chu kỳ vào danh sách
            String gioDinhDang = dinhDangGio(gioThucDay, phutThucDay);
            danhSachGio.add(gioDinhDang + " (" + soChuKy + " chu kỳ)");
        }

        // Chuyển sang màn hình kết quả
        chuyenManHinhKetQua("thucDay", danhSachGio);
    }

    // Hàm chuyển sang màn hình kết quả
    private void chuyenManHinhKetQua(String loaiTinhToan, ArrayList<String> danhSachGio) {
        Intent intent = new Intent(this, KetQuaActivity.class);
        intent.putExtra("loaiTinhToan", loaiTinhToan);
        intent.putStringArrayListExtra("cacGio", danhSachGio);
        startActivity(intent);
    }

    // Hàm định dạng giờ và phút thành chuỗi AM/PM
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