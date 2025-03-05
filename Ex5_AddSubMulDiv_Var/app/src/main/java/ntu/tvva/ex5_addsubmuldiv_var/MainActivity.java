package ntu.tvva.ex5_addsubmuldiv_var;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    EditText editTextA;
    EditText editTextB;
    Button nutCong, nutTru, nutNhan, nutChia;
    TextView tvKetQua;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TimView();
        nutCong.setOnClickListener(boLangNgheCong);
        nutTru.setOnClickListener(boLangNgheTru);
        nutNhan.setOnClickListener(boLangNgheNhan);
        nutChia.setOnClickListener(boLangNgheChia);
        //Bộ lắng nghe ẩn danh
        nutChia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String stra = editTextA.getText().toString();
                String strb = editTextB.getText().toString();

                double a = Double.parseDouble(stra);
                double b = Double.parseDouble(strb);

                Double chia = a / b;

                String strchia = String.valueOf(chia);

                tvKetQua.setText(strchia);
            }
        });
    }

    void TimView(){
        editTextA = findViewById(R.id.edtA);
        editTextB = findViewById(R.id.edtB);
        nutCong = findViewById(R.id.btnCong);
        nutTru = findViewById(R.id.btnTru);
        nutNhan = findViewById(R.id.btnNhan);
        nutChia = findViewById(R.id.btnChia);
        tvKetQua = findViewById(R.id.tvKetQua);
    }
 // TAO CAC BO LANG NGHE VA XU LY SU KIEN
    View.OnClickListener boLangNgheCong = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            //Xu ly cong
            String stra = editTextA.getText().toString();
            String strb = editTextB.getText().toString();

            double a = Double.parseDouble(stra);
            double b = Double.parseDouble(strb);

            Double sum = a + b;

            String strsum = String.valueOf(sum);

            tvKetQua.setText(strsum);
        }
    };

    View.OnClickListener boLangNgheTru = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            //Xu ly tru
            String stra = editTextA.getText().toString();
            String strb = editTextB.getText().toString();

            double a = Double.parseDouble(stra);
            double b = Double.parseDouble(strb);

            Double tru = a - b;

            String strtru = String.valueOf(tru);

            tvKetQua.setText(strtru);
        }
    };

    View.OnClickListener boLangNgheNhan = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            //xu ly nhan
            String stra = editTextA.getText().toString();
            String strb = editTextB.getText().toString();

            double a = Double.parseDouble(stra);
            double b = Double.parseDouble(strb);

            Double nhan = a * b;

            String strnhan = String.valueOf(nhan);

            tvKetQua.setText(strnhan);
        }
    };

    View.OnClickListener boLangNgheChia = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            //xu ly nhan
            String stra = editTextA.getText().toString();
            String strb = editTextB.getText().toString();

            double a = Double.parseDouble(stra);
            double b = Double.parseDouble(strb);

            Double chia = a / b;

            String strchia = String.valueOf(chia);

            tvKetQua.setText(strchia);
        }
    };
}