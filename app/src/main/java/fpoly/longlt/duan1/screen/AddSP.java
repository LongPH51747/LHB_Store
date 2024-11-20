package fpoly.longlt.duan1.screen;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

import fpoly.longlt.duan1.R;
import fpoly.longlt.duan1.adapter.SP_Admin_Adapter;
import fpoly.longlt.duan1.dao.SanPhamDAO;
import fpoly.longlt.duan1.model.ChiTietSP;
import fpoly.longlt.duan1.model.SanPham;

public class AddSP extends AppCompatActivity {
    EditText edtTenSP, edtGiaSP, edtMoTaSP, edsoluong;
    CheckBox chk_size_s, chk_size_m, chk_size_l, chk_size_xl, chk_size_xxl;
    ImageView img_back, img_add;
    Button btn_add;
    ArrayList<SanPham> lstSP = new ArrayList<>();
    ArrayList<String> lstSize = new ArrayList<>();
    ArrayList<ChiTietSP> lstCTSP = new ArrayList<>();
    SanPham sp;
    ChiTietSP ctsp;
    SanPhamDAO sanPhamDAO;
    SP_Admin_Adapter adminAdapter;
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
        btn_add.setOnClickListener(v -> {
            sanPhamDAO = new SanPhamDAO(AddSP.this);

            sp = new SanPham();
            ctsp = new ChiTietSP();

            sp.setTenSp(edtTenSP.getText().toString());
            sp.setPrice(Integer.parseInt(edtGiaSP.getText().toString()));
            sp.setStatus(1);
            sp.setImg("img_2");
            lstSize = size();

            boolean checkSP = sanPhamDAO.insertSP(sp);
            boolean checkCTSP = false;

            for (int i = 0; i < lstSize.size(); i++){
                int id_sp = sanPhamDAO.getIdSP(sp);
                ctsp.setSp_id(id_sp);
                ctsp.setMota(edtMoTaSP.getText().toString());
                ctsp.setColor("red");
                ctsp.setSoluong(Integer.parseInt(edsoluong.getText().toString()));
                ctsp.setSize(ctsp.getSize());
                checkCTSP = sanPhamDAO.insertChiTietSP(ctsp);
            }

            Log.d("checkSP", checkCTSP + "");
            Log.d("checkSP", checkSP + "");

            if (checkCTSP && checkSP){
                Toast.makeText(this, "Thêm sản phẩm thành công", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }

    public void anhxa(){
        btn_add = findViewById(R.id.btn_add);
        img_add = findViewById(R.id.img_sp);
        img_back = findViewById(R.id.img_back_screen_admin);
        edtTenSP = findViewById(R.id.name_sp);
        edsoluong = findViewById(R.id.quantity_sp);
        edtGiaSP = findViewById(R.id.price_sp);
        edtMoTaSP = findViewById(R.id.description_sp);
        chk_size_s = findViewById(R.id.chk_size_s);
        chk_size_m = findViewById(R.id.chk_size_m);
        chk_size_l = findViewById(R.id.chk_size_l);
        chk_size_xl = findViewById(R.id.chk_size_xl);
        chk_size_xxl = findViewById(R.id.chk_size_xxl);
    }

    public ArrayList<String> size(){
        ArrayList<String> lstSizes = new ArrayList<>();
        if (chk_size_s.isChecked()){
            lstSizes.add("S");
        }
        if (chk_size_m.isChecked()){
            lstSizes.add("M");
        }
        if (chk_size_l.isChecked()){
            lstSizes.add("L");
        }
        if (chk_size_xl.isChecked()) {
            lstSizes.add("XL");
        }
        if (chk_size_xxl.isChecked()){
            lstSizes.add("XXL");
        }
        return lstSizes;
    }
}