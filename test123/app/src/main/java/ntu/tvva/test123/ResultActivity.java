package ntu.tvva.test123;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class ResultActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private Button btnBack;
    private TimeAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        recyclerView = findViewById(R.id.recyclerView);
        btnBack = findViewById(R.id.btnBack);

        ArrayList<String> times = getIntent().getStringArrayListExtra("times");
        boolean isBedtime = getIntent().getBooleanExtra("isBedtime", true);

        adapter = new TimeAdapter(times);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
} 