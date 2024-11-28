package fpoly.longlt.duan1.screen;

import static fpoly.longlt.duan1.screen.LoginScreen.id_userHere;
import static fpoly.longlt.duan1.screen.ProductDetailScreen.sp_id;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import fpoly.longlt.duan1.R;
import fpoly.longlt.duan1.adapter.KiemLaiAdapter;
import fpoly.longlt.duan1.dao.SanPhamChiTietDAO;
import fpoly.longlt.duan1.dao.UserDAO;
import fpoly.longlt.duan1.fragment.OrderFragment;
import fpoly.longlt.duan1.model.ChiTietSP;
import fpoly.longlt.duan1.model.User;

public class KiemLaiDonHang extends AppCompatActivity {
    ImageButton imgBtnEdit;
    Button btnPlaceOrder;
    TextView tvNameUserKL, tvPhoneKL, tvAddressKL;
    UserDAO userDAO;
    RecyclerView rc_kiem_lai;
    KiemLaiAdapter kiemLaiAdapter;
    SanPhamChiTietDAO sanPhamChiTietDAO;
    ArrayList<ChiTietSP> list = new ArrayList<>();
    ArrayList<User> userArrayList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_kiem_lai_don_hang);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        anhXa();
        // Xết view cho recycile
        sanPhamChiTietDAO = new SanPhamChiTietDAO(KiemLaiDonHang.this);
        list = sanPhamChiTietDAO.getAllSpByID(sp_id);
        Log.e("truyLung", "SanPham " + sp_id );
        kiemLaiAdapter = new KiemLaiAdapter(KiemLaiDonHang.this, list, sanPhamChiTietDAO);
        rc_kiem_lai.setLayoutManager(new LinearLayoutManager(KiemLaiDonHang.this));
        rc_kiem_lai.setAdapter(kiemLaiAdapter);
        // Xét các view khác
        userDAO = new UserDAO(KiemLaiDonHang.this);
        userArrayList = userDAO.getUserByID(id_userHere);
        tvNameUserKL.setText("Tên Người Nhận: "+userArrayList.get(0).getNameUser());
        tvAddressKL.setText("Địa Chỉ: " +userArrayList.get(0).getAddress());
        tvPhoneKL.setText("SĐT: "+userArrayList.get(0).getPhoneNumber());

        // Chỉnh sửa chỗ nhận bla bla bla
        imgBtnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(KiemLaiDonHang.this, EditProfileScreen.class));
                finish();
            }
        });
        // Chuyển qua màn hình đơn hàng cùng giữ liệu
        btnPlaceOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager().beginTransaction().replace(R.id.framelayout_kiem_lai, OrderFragment.newInstance()).commit();
                finish();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        kiemLaiAdapter.notifyDataSetChanged();
    }

    private void anhXa() {
        rc_kiem_lai = findViewById(R.id.recyclerViewProducts);
        tvNameUserKL = findViewById(R.id.tvUserNameKL);
        tvPhoneKL = findViewById(R.id.tvUserPhoneKL);
        tvAddressKL = findViewById(R.id.tvUserAddressKL);
        imgBtnEdit = findViewById(R.id.btnEditAddress);
        btnPlaceOrder = findViewById(R.id.btnPlaceOrder);
    }
}