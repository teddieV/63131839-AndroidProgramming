package ntu.tvva.addsubmuldiv_anynomous;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    // khai bao cac doi tuong gan voi dieu khien tuong ung
    EditText editTextSo1;
    EditText editTextSo2;
    EditText editTextKQ;
    Button nutCong,nutTru,nutNhan,nutChia;
    void TimDieuKhien(){
        editTextSo1 = findViewById(R.id.edtSo1);
        editTextSo2 = findViewById(R.id.edtSo2);
        editTextKQ = findViewById(R.id.edtKetqua);
        nutCong =findViewById(R.id.btnCong);
        nutTru =findViewById(R.id.btnTru);
        nutNhan =findViewById(R.id.btnNhan);
        nutChia =findViewById(R.id.btnChia);

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TimDieuKhien();
        // Gắn Bộ Lăng nghe su kien va acode xu ly cho tung nut
        View.OnClickListener boLangNgheCong = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                XULY_CONG();
                //Xu Ly Cong...
                //code xu ly cong
                //B1 layDL 2 so
                //B1.1 Tim View.EditText so 1 va so 2

                //b1.2 Lay DL tu 2 dieu khien tren
                String soThu1= editTextSo1.getText().toString();
                String soThu2= editTextSo2.getText().toString();
                //B1.3 Chuyen DL tu chuoi sang so
                float soA= Float.parseFloat(soThu1);
                float soB= Float.parseFloat(soThu2);
                //B2.Tinh Toan
                float tong = soA + soB;
                //B3 Hien Ket Qua
                //B3.1

                //B3.2 Chuan Bi Du Lieu Xuat,Bien thanh dang chuo


                String chuoiKQ = String.valueOf(tong);
                //B3.3 Gan Ket Qua len Dieu Khien
                editTextKQ.setText(chuoiKQ);
            }
        };
        nutCong.setOnClickListener(boLangNgheCong);
        nutTru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                XULY_TRU();
                //code xu ly tru
                //B1 layDL 2 so
                //B1.1 Tim View.EditText so 1 va so 2

                //b1.2 Lay DL tu 2 dieu khien tren
                String soThu1= editTextSo1.getText().toString();
                String soThu2= editTextSo2.getText().toString();
                //B1.3 Chuyen DL tu chuoi sang so
                float soA= Float.parseFloat(soThu1);
                float soB= Float.parseFloat(soThu2);
                //B2.Tinh Toan
                float tru = soA - soB;
                //B3 Hien Ket Qua
                //B3.1

                //B3.2 Chuan Bi Du Lieu Xuat,Bien thanh dang chuo


                String chuoiKQ = String.valueOf(tru);
                //B3.3 Gan Ket Qua len Dieu Khien
                editTextKQ.setText(chuoiKQ);
            }
        });
        nutNhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                XULY_NHAN();
                //code xu ly nhan
                //B1 layDL 2 so
                //B1.1 Tim View.EditText so 1 va so 2

                //b1.2 Lay DL tu 2 dieu khien tren
                String soThu1= editTextSo1.getText().toString();
                String soThu2= editTextSo2.getText().toString();
                //B1.3 Chuyen DL tu chuoi sang so
                float soA= Float.parseFloat(soThu1);
                float soB= Float.parseFloat(soThu2);
                //B2.Tinh Toan
                float nhan = soA * soB;
                //B3 Hien Ket Qua
                //B3.1

                //B3.2 Chuan Bi Du Lieu Xuat,Bien thanh dang chuoi


                String chuoiKQ = String.valueOf(nhan);
                //B3.3 Gan Ket Qua len Dieu Khien
                editTextKQ.setText(chuoiKQ);
            }
        });
        nutChia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                XULY_CHIA();
                //code xu ly chia
                //B1 layDL 2 so
                //B1.1 Tim View.EditText so 1 va so 2

                //b1.2 Lay DL tu 2 dieu khien tren
                String soThu1= editTextSo1.getText().toString();
                String soThu2= editTextSo2.getText().toString();
                //B1.3 Chuyen DL tu chuoi sang so
                float soA= Float.parseFloat(soThu1);
                float soB= Float.parseFloat(soThu2);
                //B2.Tinh Toan
                float chia = soA / soB;
                //B3 Hien Ket Qua
                //B3.1

                //B3.2 Chuan Bi Du Lieu Xuat,Bien thanh dang chuoi


                String chuoiKQ = String.valueOf(chia);
                //B3.3 Gan Ket Qua len Dieu Khien
                editTextKQ.setText(chuoiKQ);
            }
        });

    }

    void XULY_CONG() {
        //Lay Du Lieu
        String so1=editTextSo1.getText().toString();
        String so2=editTextSo2.getText().toString();
        float num1 = Float.parseFloat(so1);
        float num2 = Float.parseFloat(so2);
        float tong = num1 + num2;
        String chuoiKQ = String.valueOf(tong);
        editTextKQ.setText(chuoiKQ);

    }
    void XULY_TRU(){
        String so1=editTextSo1.getText().toString();
        String so2=editTextSo2.getText().toString();
        float num1 = Float.parseFloat(so1);
        float num2 = Float.parseFloat(so2);
        float tong = num1 - num2;
        String chuoiKQ = String.valueOf(tong);
        editTextKQ.setText(chuoiKQ);

    }
    void XULY_NHAN() {
        String so1=editTextSo1.getText().toString();
        String so2=editTextSo2.getText().toString();
        float num1 = Float.parseFloat(so1);
        float num2 = Float.parseFloat(so2);
        float tong = num1 * num2;
        String chuoiKQ = String.valueOf(tong);
        editTextKQ.setText(chuoiKQ);

    }
    void XULY_CHIA() {
        String so1=editTextSo1.getText().toString();
        String so2=editTextSo2.getText().toString();
        float num1 = Float.parseFloat(so1);
        float num2 = Float.parseFloat(so2);
        float tong = num1 / num2;
        String chuoiKQ = String.valueOf(tong);
        editTextKQ.setText(chuoiKQ);

    }

}