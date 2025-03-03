package ntu.tvva.lt1_cttinh_bmi;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EditText editTextCanNang = findViewById(R.id.CanNang);
        EditText editTextChieuCao = findViewById(R.id.ChieuCao);
        Button button =findViewById(R.id.btnXacNhan);
        TextView textView = findViewById(R.id.KetQua);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float CanNang = Float.parseFloat(String.valueOf(editTextCanNang.getText())) ;
                float ChieuCao = Float.parseFloat(String.valueOf(editTextChieuCao.getText()))/ 100;
                float bmi = CanNang / (ChieuCao * ChieuCao);
                // Làm tròn kết quả BMI về một chữ số thập phân
                float roundedBmi =(float) (Math.round(bmi * 10.0) / 10.0);
                // Phân loại BMI
                String danhGia;
                if (roundedBmi < 18.5) {
                    danhGia = "GẦY";
                } else if (roundedBmi < 24.9) {
                    danhGia = "BÌNH THƯỜNG";
                } else if (roundedBmi < 29.9) {
                    danhGia = "THỪA CÂN";
                } else {
                    danhGia = "BÉO PHÌ";
                }

                // Hiển thị kết quả BMI và đánh giá
                textView.setText(String.format("BMI: %.1f - %s", roundedBmi, danhGia));


            }
        });
    }
}