package thigk2.tranvanvietanh;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class cn2 extends AppCompatActivity {
    private EditText edtGK, edtCK;
    private TextView tvKQ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cn2);

        edtGK = findViewById(R.id.edtGK);
        edtCK = findViewById(R.id.edtCK);
        tvKQ = findViewById(R.id.tvKQ);
        Button btnDTB = findViewById(R.id.btnDTB);

        btnDTB.setOnClickListener(v -> tinhDTB());
    }

    private void tinhDTB() {
        try {
            float diemGK = Float.parseFloat(edtGK.getText().toString().trim());
            float diemCK = Float.parseFloat(edtCK.getText().toString().trim());
            float diemTB = (diemGK + diemCK) / 2;
            tvKQ.setText("Điểm trung bình: " + diemTB);
        } catch (Exception e) {
            tvKQ.setText("Lỗi nhập điểm!");
        }
    }
}
