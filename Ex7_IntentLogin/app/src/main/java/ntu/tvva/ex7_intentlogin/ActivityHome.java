package ntu.tvva.ex7_intentlogin;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ActivityHome extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        TextView tvUserName = findViewById(R.id.tvUserName);
        Button btnQuayLai = findViewById(R.id.btnQuaylai);

        Intent intent = getIntent();
        String strUserName = intent.getStringExtra("UserName");

        tvUserName.setText(strUserName);
        btnQuayLai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent iQuayLai = new Intent(ActivityHome.this, MainActivity.class);
                startActivity(iQuayLai);
            }
        });
    }
}