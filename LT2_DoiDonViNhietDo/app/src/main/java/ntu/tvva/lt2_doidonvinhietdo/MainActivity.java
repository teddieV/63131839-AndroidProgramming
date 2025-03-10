package ntu.tvva.lt2_doidonvinhietdo;

package com.example.doinhietdo;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
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
                String cStr = edtC.getText().toString();
                if (!cStr.isEmpty()) {
                    double celsius = Double.parseDouble(cStr);
                    double fahrenheit = (celsius * 9 / 5) + 32;
                    txtKetQua.setText("Nhiệt độ Fahrenheit: " + fahrenheit);
                }
            }
        });

        btnFtoC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fStr = edtF.getText().toString();
                if (!fStr.isEmpty()) {
                    double fahrenheit = Double.parseDouble(fStr);
                    double celsius = (fahrenheit - 32) * 5 / 9;
                    txtKetQua.setText("Nhiệt độ Celsius: " + celsius);
                }
            }
        });
    }
}
