package fpoly.longlt.duan1.screen;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

import fpoly.longlt.duan1.R;
import fpoly.longlt.duan1.dao.SanPhamDAO;

public class ProductDetailScreen extends AppCompatActivity {

    private Spinner spnSize, spnColors;
    private TextView tvNameSPCT, tvPriceSPCT;
    ImageView imgSPCT;

    ArrayList<String> listSize = new ArrayList<>();
    ArrayList<String> listColors = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_product_detail_screen);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        anhXa();

        // Lấy thông tin sản phẩm từ Intent
        int sp_id = getIntent().getIntExtra("sp_id", -1);
        String productName = getIntent().getStringExtra("tensp");
        int productPrice = getIntent().getIntExtra("price", 0);
        String productDescription = getIntent().getStringExtra("description");
        String productImg = getIntent().getStringExtra("img");
        Log.d("id","id: "+sp_id);

// Cập nhật giao diện
        tvNameSPCT.setText(productName);
        tvPriceSPCT.setText(String.valueOf(productPrice)+"VND");

// Lấy ID tài nguyên ảnh từ tên ảnh lưu trong cơ sở dữ liệu
        int imageResId = getResources().getIdentifier(productImg, "drawable", getPackageName());

// Kiểm tra nếu tài nguyên ảnh tồn tại và hiển thị ảnh
        if (imageResId != 0) {
            imgSPCT.setImageResource(imageResId);
        } else {
            // Nếu không tìm thấy ảnh, có thể set ảnh mặc định
            imgSPCT.setImageResource(R.drawable.img_2);  // Placeholder image
        }
        setUpSpiner(sp_id);
    }

    private void setUpSpiner(int sp_id){
        SanPhamDAO sanPhamDAO = new SanPhamDAO(this);

        // Lấy danh sách màu sắc và kích thước từ cơ sở dữ liệu
        ArrayList<String> listColors = sanPhamDAO.getAllColors(sp_id);
        ArrayList<String> listSize = sanPhamDAO.getAllSizes(sp_id);

        // Tạo ArrayAdapter cho Spinner Size
        ArrayAdapter<String> sizeAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, listSize);
        sizeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnSize.setAdapter(sizeAdapter);

        // Tạo ArrayAdapter cho Spinner Color
        ArrayAdapter<String> colorAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, listColors);
        colorAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnColors.setAdapter(colorAdapter);
    }

    private void anhXa(){
        spnSize = findViewById(R.id.spnSize);
        spnColors = findViewById(R.id.spnColor);
        tvNameSPCT = findViewById(R.id.tvNameSPCT);
        imgSPCT = findViewById(R.id.imgSPCT);
        tvPriceSPCT = findViewById(R.id.tvPriceSPCT);
    }
}