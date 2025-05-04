package ntu.tvva.sleepcalculator;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

/**
 * Activity hiển thị kết quả tính toán giờ ngủ/thức dậy
 */
public class KetQuaActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView tieuDeKetQua;
    private TextView thongTinGioThucDay;
    private RecyclerView recyclerView;
    private Button btnQuayLai;
    private KetQuaAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ket_qua);

        try {
            anhXaGiaoDien();
            hienThiDuLieu();
            btnQuayLai.setOnClickListener(this);
        } catch (Exception e) {
            Toast.makeText(this, "Có lỗi xảy ra: " + e.getMessage(), Toast.LENGTH_LONG).show();
            finish();
        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnQuayLai) {
            finish();
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        }
    }

    /**
     * Ánh xạ các thành phần giao diện
     */
    private void anhXaGiaoDien() {
        tieuDeKetQua = findViewById(R.id.tieuDeKetQua);
        thongTinGioThucDay = findViewById(R.id.thongTinGioThucDay);
        btnQuayLai = findViewById(R.id.btnQuayLai);
        recyclerView = findViewById(R.id.recyclerView);

        // Thiết lập RecyclerView
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
    }

    /**
     * Hiển thị dữ liệu từ Intent
     */
    private void hienThiDuLieu() {
        Intent intent = getIntent();
        if (intent == null) {
            Toast.makeText(this, "Không thể nhận dữ liệu", Toast.LENGTH_SHORT).show();
            return;
        }

        String loai = intent.getStringExtra("loai");
        int gio = intent.getIntExtra("gio", 0);
        int phut = intent.getIntExtra("phut", 0);

        if (loai == null) {
            Toast.makeText(this, "Dữ liệu không hợp lệ", Toast.LENGTH_SHORT).show();
            return;
        }

        ArrayList<String> danhSachKetQua;
        if (loai.equals("diNgu")) {
            tieuDeKetQua.setText("Giờ đi ngủ");
            thongTinGioThucDay.setText(String.format("Để thức dậy tỉnh táo lúc %02d:%02d,\nbạn nên đi ngủ vào một trong những thời điểm sau:", gio, phut));
            danhSachKetQua = TinhToanGioNgu.tinhGioDiNgu(gio, phut);
        } else {
            tieuDeKetQua.setText("Giờ thức dậy");
            thongTinGioThucDay.setText(String.format("Nếu bạn đi ngủ vào lúc %02d:%02d,\nbạn nên đặt báo thức vào một trong những thời điểm sau:", gio, phut));
            danhSachKetQua = TinhToanGioNgu.tinhGioThucDay(gio, phut);
        }

        adapter = new KetQuaAdapter(danhSachKetQua);
        recyclerView.setAdapter(adapter);
    }
} 