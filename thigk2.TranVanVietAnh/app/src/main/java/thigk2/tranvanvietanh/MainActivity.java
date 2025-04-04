package thigk2.tranvanvietanh;

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
        Button cn1 = findViewById(R.id.btn_Cn1);
        Button cn2 = findViewById(R.id.btn_Cn2);
        Button cn3 = findViewById(R.id.btn_Cn3);
        Button cn4 = findViewById(R.id.btn_Cn4);
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        cn1.setOnClickListener(v -> startActivity(new Intent(MainActivity.this,cn1.class)));
        cn2.setOnClickListener(v -> startActivity(new Intent(MainActivity.this,cn2.class)));
        cn3.setOnClickListener(v -> startActivity(new Intent(MainActivity.this,cn3.class)));
        cn4.setOnClickListener(v -> startActivity(new Intent(MainActivity.this,cn4.class)));
    }
}