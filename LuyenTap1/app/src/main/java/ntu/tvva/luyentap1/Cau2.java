package ntu.tvva.luyentap1;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

public class Cau2 extends AppCompatActivity {
    ListView listViewbaihat;
    ArrayList<String> dsBaiHat;
    ArrayAdapter<String> adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_cau2);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        listViewbaihat = findViewById(R.id.LVnhac);
        dsBaiHat=new ArrayList<>();
        dsBaiHat.add("Shape of You - Ed sheeran");
        dsBaiHat.add("Shape of You1 - Ed sheeran");
        dsBaiHat.add("Shape of You2 - Ed sheeran");
        dsBaiHat.add("Shape of You3 - Ed sheeran");
        dsBaiHat.add("Shape of You4- Ed sheeran");

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, dsBaiHat);
        listViewbaihat.setAdapter(adapter);


        listViewbaihat.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String baihat = dsBaiHat.get(position);
                Toast.makeText(Cau2.this , " Bạn Chọn" + baihat, Toast.LENGTH_SHORT).show();
            }
        });

    }
}