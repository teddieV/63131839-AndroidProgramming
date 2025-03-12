package ntu.tvva.lt2_doidonvinhietdo;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText edtC = findViewById(R.id.edtC);
        EditText edtF = findViewById(R.id.edtF);
        Button btnCtoF = findViewById(R.id.btnCtoF);
        Button btnFtoC = findViewById(R.id.btnFtoC);
        TextView txtKetQua = findViewById(R.id.txtKetQua);

        btnCtoF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String cStr = edtC.getText().toString().trim();
                if (cStr.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Vui lòng nhập nhiệt độ Celsius!", Toast.LENGTH_SHORT).show();
                    return;
                }

                try {
                    double celsius = Double.parseDouble(cStr);
                    double fahrenheit = (celsius * 9 / 5) + 32;
                    txtKetQua.setText("Nhiệt độ Fahrenheit: " + fahrenheit);
                    edtC.setText("");  // Xóa dữ liệu sau khi chuyển đổi
                } catch (NumberFormatException e) {
                    Toast.makeText(MainActivity.this, "Vui lòng nhập số hợp lệ!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnFtoC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fStr = edtF.getText().toString().trim();
                if (fStr.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Vui lòng nhập nhiệt độ Fahrenheit!", Toast.LENGTH_SHORT).show();
                    return;
                }

                try {
                    double fahrenheit = Double.parseDouble(fStr);
                    double celsius = (fahrenheit - 32) * 5 / 9;
                    txtKetQua.setText("Nhiệt độ Celsius: " + celsius);
                    edtF.setText("");  // Xóa dữ liệu sau khi chuyển đổi
                } catch (NumberFormatException e) {
                    Toast.makeText(MainActivity.this, "Vui lòng nhập số hợp lệ!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
