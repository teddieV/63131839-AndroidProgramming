    package ntu.tvva.ex_listviewnnlt;

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

    public class MainActivity extends AppCompatActivity {
        ListView listViewNNLT;
        ArrayList<String> dsNgonNguLT;


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
            listViewNNLT  = findViewById(R.id.lvNNLT);
            //CHuan bi du lieu hard core
            //B1
            dsNgonNguLT = new ArrayList<String>();
            dsNgonNguLT.add("Python");
            dsNgonNguLT.add("Php");
            dsNgonNguLT.add("Java");
            //B2 Tao Adapter
            ArrayAdapter<String> adapterNNLT;
            adapterNNLT = new ArrayAdapter<>(this,android.R.layout.simple_expandable_list_item_1,dsNgonNguLT);
            //B3 Gan Adapter
            listViewNNLT.setAdapter(adapterNNLT);
            //B4 Gan bo lang nghe va xlsk
            listViewNNLT.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    //code xu ly truc tiep
                    //chu y bien position da chua vi tri cua item duoc click
                    String giatriduocchon = dsNgonNguLT.get(position);
                    // Lam gi do voi gia tri nay thi tuy
                    //Toast
                    Toast.makeText(MainActivity.this, giatriduocchon, Toast.LENGTH_SHORT).show();

                }
            });

        }
    }