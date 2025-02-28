package ntu.tvva.ex4_addsubmuldiv_onclick;

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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        TimDieuKhien();
    }
    void TimDieuKhien(){
        editTextSo1 = (EditText)findViewById(R.id.edtSo1);
        editTextSo2 = (EditText)findViewById(R.id.edtSo2);
        editTextKQ = (EditText)findViewById(R.id.edtKetqua);
        nutCong =(Button)findViewById(R.id.btnCong);
        nutTru =(Button)findViewById(R.id.btnTru);
        nutNhan =(Button)findViewById(R.id.btnNhan);
        nutChia =(Button)findViewById(R.id.btnChia);

    }



    //Ham xu ly cong
    public void XuLyCong(View v){
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
    //Ham Xu ly tru
    public void XuLyTru(View v){
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
        float tong = soA - soB;
        //B3 Hien Ket Qua
        //B3.1

        //B3.2 Chuan Bi Du Lieu Xuat,Bien thanh dang chuo


        String chuoiKQ = String.valueOf(tong);
        //B3.3 Gan Ket Qua len Dieu Khien
        editTextKQ.setText(chuoiKQ);
    }
    //Ham Xu ly nhan
    public void XuLyNhan(View v){
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
        float tong = soA * soB;
        //B3 Hien Ket Qua
        //B3.1

        //B3.2 Chuan Bi Du Lieu Xuat,Bien thanh dang chuoi


        String chuoiKQ = String.valueOf(tong);
        //B3.3 Gan Ket Qua len Dieu Khien
        editTextKQ.setText(chuoiKQ);
    }
    //Ham Xu ly chia
    public void XulyChia(View v){
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
        float tong = soA / soB;
        //B3 Hien Ket Qua
        //B3.1

        //B3.2 Chuan Bi Du Lieu Xuat,Bien thanh dang chuoi


        String chuoiKQ = String.valueOf(tong);
        //B3.3 Gan Ket Qua len Dieu Khien
        editTextKQ.setText(chuoiKQ);
    }
}