package ntu.tvva.sleepcalculator;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class KetQuaActivity extends AppCompatActivity implements View.OnClickListener {
    // Khai báo các thành phần giao diện
    private TextView tieuDeKetQua;
    private TextView thongTinThoiGianNgu;
    private TextView thongTinGioThucDay;
    private TextView[] danhSachThoiGian;
    private TextView[] danhSachGoiY;
    private TextView thongTinChuKy;
    private Button btnQuayLai;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ket_qua);

        try {
            // Ánh xạ các thành phần giao diện
            anhXaGiaoDien();

            // Lấy và xử lý dữ liệu từ Intent
            xuLyDuLieu();

            // Thiết lập sự kiện cho nút quay lại
            if (btnQuayLai != null) {
                btnQuayLai.setOnClickListener(this);
            }
        } catch (Exception e) {
            Toast.makeText(this, "Có lỗi xảy ra: " + e.getMessage(), Toast.LENGTH_LONG).show();
            finish();
        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnQuayLai) {
            finish();
        }
    }

    // Phương thức ánh xạ các thành phần giao diện
    private void anhXaGiaoDien() {
        // Ánh xạ các TextView chính
        tieuDeKetQua = findViewById(R.id.tieuDeKetQua);
        thongTinThoiGianNgu = findViewById(R.id.thongTinThoiGianNgu);
        thongTinGioThucDay = findViewById(R.id.thongTinGioThucDay);
        thongTinChuKy = findViewById(R.id.thongTinChuKy);
        btnQuayLai = findViewById(R.id.btnQuayLai);

        // Kiểm tra null cho các thành phần chính
        if (tieuDeKetQua == null || thongTinThoiGianNgu == null || 
            thongTinGioThucDay == null || thongTinChuKy == null || 
            btnQuayLai == null) {
            throw new RuntimeException("Không thể tìm thấy các thành phần giao diện");
        }

        // Khởi tạo mảng TextView cho thời gian
        danhSachThoiGian = new TextView[6];
        int[] thoiGianIds = {
            R.id.thoiGian1, R.id.thoiGian2, R.id.thoiGian3,
            R.id.thoiGian4, R.id.thoiGian5, R.id.thoiGian6
        };

        for (int i = 0; i < danhSachThoiGian.length; i++) {
            danhSachThoiGian[i] = findViewById(thoiGianIds[i]);
            if (danhSachThoiGian[i] == null) {
                throw new RuntimeException("Không thể tìm thấy TextView thời gian " + (i + 1));
            }
        }

        // Khởi tạo mảng TextView cho chu kỳ
        danhSachGoiY = new TextView[6];
        int[] chuKyIds = {
            R.id.chuKy1, R.id.chuKy2, R.id.chuKy3,
            R.id.chuKy4, R.id.chuKy5, R.id.chuKy6
        };

        for (int i = 0; i < danhSachGoiY.length; i++) {
            danhSachGoiY[i] = findViewById(chuKyIds[i]);
            if (danhSachGoiY[i] == null) {
                throw new RuntimeException("Không thể tìm thấy TextView chu kỳ " + (i + 1));
            }
        }
    }

    // Phương thức xử lý dữ liệu từ Intent
    private void xuLyDuLieu() {
        Intent intent = getIntent();
        if (intent == null) {
            Toast.makeText(this, "Không thể nhận dữ liệu", Toast.LENGTH_SHORT).show();
            return;
        }

        String loaiTinhToan = intent.getStringExtra("loaiTinhToan");
        ArrayList<String> danhSachGio = intent.getStringArrayListExtra("cacGio");
        
        if (loaiTinhToan == null || danhSachGio == null || danhSachGio.isEmpty()) {
            Toast.makeText(this, "Dữ liệu không hợp lệ", Toast.LENGTH_SHORT).show();
            return;
        }

        // Cập nhật tiêu đề và thông tin dựa trên loại tính toán
        if (loaiTinhToan.equals("diNgu")) {
            tieuDeKetQua.setText("Giờ đi ngủ");
            thongTinGioThucDay.setText("Để thức dậy tỉnh táo lúc " + danhSachGio.get(0) + 
                ",\nbạn nên đi ngủ vào một trong những thời điểm sau:");
        } else {
            tieuDeKetQua.setText("Giờ thức dậy");
            thongTinGioThucDay.setText("Nếu bạn đi ngủ vào lúc " + danhSachGio.get(0) + 
                ",\nbạn nên đặt báo thức vào một trong những thời điểm sau:");
        }

        // Hiển thị các thời điểm và chu kỳ
        int soThoiGian = Math.min(danhSachThoiGian.length, danhSachGio.size() - 1);
        for (int i = 0; i < soThoiGian; i++) {
            String gioVaChuKy = danhSachGio.get(i + 1);
            String[] parts = gioVaChuKy.split(" \\(");
            String gio = parts[0];
            String chuKy = parts[1].replace(")", "");
            
            danhSachThoiGian[i].setText(gio);
            danhSachGoiY[i].setText(chuKy);
            
            danhSachThoiGian[i].setVisibility(View.VISIBLE);
            danhSachGoiY[i].setVisibility(View.VISIBLE);
        }

        // Ẩn các TextView không sử dụng
        for (int i = soThoiGian; i < danhSachThoiGian.length; i++) {
            danhSachThoiGian[i].setVisibility(View.GONE);
            danhSachGoiY[i].setVisibility(View.GONE);
        }
    }
} 