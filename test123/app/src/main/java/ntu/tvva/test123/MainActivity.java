package ntu.tvva.test123;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private Button btnCalculateBedtime;
    private Button btnCalculateWakeTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnCalculateBedtime = findViewById(R.id.btnCalculateBedtime);
        btnCalculateWakeTime = findViewById(R.id.btnCalculateWakeTime);

        btnCalculateBedtime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CalculateBedtimeActivity.class);
                startActivity(intent);
            }
        });

        btnCalculateWakeTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CalculateWakeTimeActivity.class);
                startActivity(intent);
            }
        });
    }
}