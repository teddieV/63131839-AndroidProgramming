package ntu.tvva.ex6_intentdongian;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    Button nutMH2;
    Button nutMH3;
    void Timdieukhien(){
        nutMH2 = (Button)  findViewById(R.id.btnMH2);
        nutMH3 = (Button)  findViewById(R.id.btnMH3);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        //Tim cac nut dieu khien nut bam
        nutMH2 = (Button)  findViewById(R.id.btnMH2);
        nutMH3 = (Button)  findViewById(R.id.btnMH3);
        Timdieukhien();
        //Gan bo lang nghe su kien
        nutMH2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //XU LY CHUYEN MAN HINH
                // B1: Tao 1 intent(2 tham so 1 ) Man hinh hien tai ; 2 man hinh chuyen toi class
                Intent intentMH2 = new Intent(MainActivity.this, MainActivityMH2.class);
                // B2: GUI
                startActivity(intentMH2);
            }
        });
        nutMH3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //XU LY CHUYEN MAN HINH
                // B1: Tao 1 intent(2 tham so 1 ) Man hinh hien tai ; 2 man hinh chuyen toi class
                Intent intentMH3 = new Intent(MainActivity.this, MainActivityMH3.class);
                // B2: GUI
                startActivity(intentMH3);

            }
        });
    }

}