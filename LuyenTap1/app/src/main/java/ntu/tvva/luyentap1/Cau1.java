    package ntu.tvva.luyentap1;

    import android.os.Bundle;
    import android.view.View;
    import android.widget.Button;
    import android.widget.EditText;

    import androidx.activity.EdgeToEdge;
    import androidx.appcompat.app.AppCompatActivity;
    import androidx.core.graphics.Insets;
    import androidx.core.view.ViewCompat;
    import androidx.core.view.WindowInsetsCompat;

    public class Cau1 extends AppCompatActivity {

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            EdgeToEdge.enable(this);
            setContentView(R.layout.activity_cau1);
            ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
                Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
                v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
                return insets;
            });
            EditText editTextChieuCao = findViewById(R.id.ChieuCao);
            EditText editTextCannang = findViewById(R.id.KG);
            Button buttonKetQuaBMI = findViewById(R.id.btnKetQua);
            EditText editTextKetqua =findViewById(R.id.idketquaBmi);

            buttonKetQuaBMI.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    float Cannang = Float.parseFloat(String.valueOf(editTextCannang.getText()));
                    float ChieuCao = Float.parseFloat(String.valueOf(editTextChieuCao.getText()));
                    float bmi = Cannang / (ChieuCao*ChieuCao);
                    String danhgia;
                    if (bmi<18.5){
                        danhgia="Gầy";
                    }else if (bmi<24.9){
                        danhgia="Binh Thuong";
                    }else if(bmi<29.9){
                        danhgia="Thua can";
                    }else {
                        danhgia="BeoPhi";
                    }
                    editTextKetqua.setText(String.format("bmi: %.1f - %s", bmi,danhgia));

                }
            });

        }

    }