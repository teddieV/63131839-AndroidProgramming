package ntu.tvva.ex_listviewdanhsachtinhthanh;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView listViewTinhThanh;
    ArrayList<String> dsTinhThanh;
    ArrayAdapter<String> adapterTinhThanh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    //Anh xa id
        listViewTinhThanh = findViewById(R.id.lvTinhThanh);
        TextView tvHeader = new TextView(this);
        tvHeader.setText("Danh Sach Cac Tinh Thanh");
        tvHeader.setTextSize(20);
        tvHeader.setPadding(20, 20, 20, 20);
        tvHeader.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

        // Tạo danh sách tỉnh thành
        dsTinhThanh = new ArrayList<>();
        dsTinhThanh.add("An Giang");
        dsTinhThanh.add("Bà Rịa - Vũng Tàu");
        dsTinhThanh.add("Bắc Giang");
        dsTinhThanh.add("Bắc Kạn");
        dsTinhThanh.add("Bạc Liêu");
        dsTinhThanh.add("Bắc Ninh");
        dsTinhThanh.add("Bến Tre");
        dsTinhThanh.add("Bình Định");
        dsTinhThanh.add("Bình Dương");
        dsTinhThanh.add("Bình Phước");
        dsTinhThanh.add("Bình Thuận");
        dsTinhThanh.add("Cà Mau");
        dsTinhThanh.add("Cần Thơ");
        dsTinhThanh.add("Cao Bằng");
        dsTinhThanh.add("Đà Nẵng");
        dsTinhThanh.add("Đắk Lắk");
        dsTinhThanh.add("Đắk Nông");
        dsTinhThanh.add("Điện Biên");
        dsTinhThanh.add("Đồng Nai");
        dsTinhThanh.add("Đồng Tháp");
        dsTinhThanh.add("Gia Lai");
        dsTinhThanh.add("Hà Giang");
        dsTinhThanh.add("Hà Nam");
        dsTinhThanh.add("Hà Nội");
        dsTinhThanh.add("Hà Tĩnh");
        dsTinhThanh.add("Hải Dương");
        dsTinhThanh.add("Hải Phòng");
        dsTinhThanh.add("Hậu Giang");
        dsTinhThanh.add("Hòa Bình");
        dsTinhThanh.add("Hưng Yên");
        dsTinhThanh.add("Khánh Hòa");
        dsTinhThanh.add("Kiên Giang");
        dsTinhThanh.add("Kon Tum");
        dsTinhThanh.add("Lai Châu");
        dsTinhThanh.add("Lâm Đồng");
        dsTinhThanh.add("Lạng Sơn");
        dsTinhThanh.add("Lào Cai");
        dsTinhThanh.add("Long An");
        dsTinhThanh.add("Nam Định");
        dsTinhThanh.add("Nghệ An");
        dsTinhThanh.add("Ninh Bình");
        dsTinhThanh.add("Ninh Thuận");
        dsTinhThanh.add("Phú Thọ");
        dsTinhThanh.add("Phú Yên");
        dsTinhThanh.add("Quảng Bình");
        dsTinhThanh.add("Quảng Nam");
        dsTinhThanh.add("Quảng Ngãi");
        dsTinhThanh.add("Quảng Ninh");
        dsTinhThanh.add("Quảng Trị");
        dsTinhThanh.add("Sóc Trăng");
        dsTinhThanh.add("Sơn La");
        dsTinhThanh.add("Tây Ninh");
        dsTinhThanh.add("Thái Bình");
        dsTinhThanh.add("Thái Nguyên");
        dsTinhThanh.add("Thanh Hóa");
        dsTinhThanh.add("Thừa Thiên Huế");
        dsTinhThanh.add("Tiền Giang");
        dsTinhThanh.add("TP Hồ Chí Minh");
        dsTinhThanh.add("Trà Vinh");
        dsTinhThanh.add("Tuyên Quang");
        dsTinhThanh.add("Vĩnh Long");
        dsTinhThanh.add("Vĩnh Phúc");
        dsTinhThanh.add("Yên Bái");
        // Tạo Adapter và gán vào ListView
        adapterTinhThanh = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,dsTinhThanh);
        // Layout hiển thị một dòng văn bảndsTinhThanh
        listViewTinhThanh.setAdapter(adapterTinhThanh);
        listViewTinhThanh.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String tinhthanh = dsTinhThanh.get(position);
                Toast.makeText(MainActivity.this, tinhthanh, Toast.LENGTH_SHORT).show();
            }
        });
    }
}