package fpoly.longlt.duan1.screen;

import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import fpoly.longlt.duan1.R;

public class AddSP extends AppCompatActivity {
    EditText edtTenSP, edtGiaSP, edtMoTaSP;
    CheckBox chk_size_s, chk_size_m, chk_size_l, chk_size_xl, chk_size_xxl;
    ImageView img_back, img_add;
    Button btn_add;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_sp);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        anhxa();
        img_back.setOnClickListener(v -> finish());
    }

    public void anhxa(){
        btn_add = findViewById(R.id.btn_add);
        img_add = findViewById(R.id.img_sp);
        img_back = findViewById(R.id.img_back_screen_admin);
        edtTenSP = findViewById(R.id.name_sp);
        edtGiaSP = findViewById(R.id.price_sp);
        edtMoTaSP = findViewById(R.id.description_sp);
        chk_size_s = findViewById(R.id.chk_size_s);
        chk_size_m = findViewById(R.id.chk_size_m);
        chk_size_l = findViewById(R.id.chk_size_l);
        chk_size_xl = findViewById(R.id.chk_size_xl);
    }
}