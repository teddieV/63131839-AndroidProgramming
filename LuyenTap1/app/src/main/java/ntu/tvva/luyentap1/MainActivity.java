package ntu.tvva.luyentap1;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

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
        Button btnCau1= findViewById(R.id.btnCau1);
        Button btnCau2= findViewById(R.id.btncau2);
        Button btnCau3= findViewById(R.id.btncau3);
        Button btnCau4= findViewById(R.id.btncau4);

        btnCau1.setOnClickListener(v ->  startActivity(new Intent(MainActivity.this, Cau1.class)));
        btnCau2.setOnClickListener(v ->  startActivity(new Intent(MainActivity.this, Cau2.class)));
    }
}