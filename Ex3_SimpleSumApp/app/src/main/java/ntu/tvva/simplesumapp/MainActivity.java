package ntu.tvva.simplesumapp;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Gắn layout tuong ung vơi file nay
        setContentView(R.layout.activity_main);

    }
    // Day la bo lang nghe và xu ly su kien click tinh tong
    public void XuLyCong(View view){
        // Tim cac dieu khien, tham chieu den tren tep XML
        EditText edittextSoA = findViewById(R.id.edtA);
        EditText edittextSoB= findViewById(R.id.edtB);
        EditText edittextSoKQ = findViewById(R.id.edtKQ);
        //Lay du lieu ve o dieu khien so A
        String strA= edittextSoA.getText().toString(); //strA = "2"

        //Lay du lieu ve o dieu khien so B
        String strB= edittextSoB.getText().toString();//strB = "4"
        // Chuyen du lieu sang dang so
        int so_A=    Integer.parseInt(strA);
        int so_B=    Integer.parseInt(strB);

        //Tinh toan theo yeu cau
        int  tong = so_A + so_B;
        String strTong = String.valueOf(tong); // chuyen sang dang chuoi
        // Hien ra man hinh

        edittextSoKQ.setText(strTong);

    }

}