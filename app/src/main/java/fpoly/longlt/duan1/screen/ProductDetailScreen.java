package fpoly.longlt.duan1.screen;

import android.database.Cursor;
import android.net.Uri;
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

import java.io.File;
import java.util.ArrayList;

import fpoly.longlt.duan1.R;
import fpoly.longlt.duan1.dao.SanPhamDAO;
import fpoly.longlt.duan1.model.ChiTietSP;
import fpoly.longlt.duan1.model.SanPham;

public class ProductDetailScreen extends AppCompatActivity {
    private Spinner spnSize, spnColors;
    private TextView tvNameSPCT, tvPriceSPCT, tvMoTa;
    ImageView imgSPCT;
    int sp_id;
    SanPhamDAO dao;

    //    ArrayList<String> listSize = new ArrayList<>();
//    ArrayList<String> listColors = new ArrayList<>();
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

//        String productName = getIntent().getStringExtra("tensp");
//        int productPrice = getIntent().getIntExtra("price", 0);
//        String productDescription = getIntent().getStringExtra("description");
//        String productImg = getIntent().getStringExtra("img");
//        Log.d("id","id: "+sp_id);

// Cập nhật giao diện
//        tvNameSPCT.setText(productName);
//        tvPriceSPCT.setText(String.valueOf(productPrice)+"VND");
        Bundle bundle = getIntent().getExtras();
        if (bundle!=null){
            sp_id = bundle.getInt("id");
        }

            dao = new SanPhamDAO(this);
            ChiTietSP sp = dao.getSPChiTiet(sp_id);
            SanPham sanPham = dao.getSP(sp_id);
            tvNameSPCT.setText(sanPham.getTenSp());
            tvPriceSPCT.setText(sanPham.getPrice()+"VND");
            tvMoTa.setText(sanPham.getMota());
            Log.d("anh", "ảnh: "+bundle.getString("img"));
            try {
                String imgPath = sanPham.getImg();  // Lấy đường dẫn tệp từ SQLite
                File imgFile = new  File(imgPath);  // Tạo đối tượng File từ đường dẫn
                if(imgFile.exists()) {
                    Uri uri = Uri.fromFile(imgFile);  // Chuyển đường dẫn thành URI
                    imgSPCT.setImageURI(uri);  // Đặt URI vào ImageView
                }
            } catch (Exception e){
                imgSPCT.setImageResource(R.drawable.img_2);
            }
        setUpSpiner(sp_id);

    }
//    public void hienThiChiTietSanPham(int spId) {
//        SanPhamDAO sanPhamDAO = new SanPhamDAO(this);
//        Cursor cursor = sanPhamDAO.getChiTietSanPham(spId);
//
//        if (cursor != null && cursor.moveToFirst()) {
//            // Lấy giá trị từ các cột
//            String tensp = cursor.getString(cursor.getColumnIndexOrThrow("tensp"));
//            String img = cursor.getString(cursor.getColumnIndexOrThrow("img"));
//            int status = cursor.getInt(cursor.getColumnIndexOrThrow("status"));
//            int price = cursor.getInt(cursor.getColumnIndexOrThrow("price"));
//            String description = cursor.getString(cursor.getColumnIndexOrThrow("description"));
//            String size = cursor.getString(cursor.getColumnIndexOrThrow("size"));
//            String color = cursor.getString(cursor.getColumnIndexOrThrow("color"));
//            int soLuong = cursor.getInt(cursor.getColumnIndexOrThrow("soluong"));
//
//            // Gán dữ liệu lên TextView
//            tvNameSPCT.setText(tensp);
//            try {
//                String imgPath = img;  // Lấy đường dẫn tệp từ SQLite
//                File imgFile = new  File(imgPath);  // Tạo đối tượng File từ đường dẫn
//                if(imgFile.exists()) {
//                    Uri uri = Uri.fromFile(imgFile);  // Chuyển đường dẫn thành URI
//                    imgSPCT.setImageURI(uri);  // Đặt URI vào ImageView
//                }
//            } catch (Exception e){
//                imgSPCT.setImageResource(R.drawable.img_2);
//            } // Hoặc dùng thư viện như Glide nếu là đường dẫn ảnh
//            tvPriceSPCT.setText(String.valueOf(price));
//            tvMoTa.setText(description);
//        }
//
//        // Đừng quên đóng Cursor sau khi sử dụng
//        if (cursor != null) {
//            cursor.close();
//        }
//    }
    // Lấy ID tài nguyên ảnh từ tên ảnh lưu trong cơ sở dữ liệu
//        int imageResId = getResources().getIdentifier(productImg, "drawable", getPackageName());
//
//// Kiểm tra nếu tài nguyên ảnh tồn tại và hiển thị ảnh
//        if (imageResId != 0) {
//            imgSPCT.setImageResource(imageResId);
//        } else {
//            // Nếu không tìm thấy ảnh, có thể set ảnh mặc định
//            imgSPCT.setImageResource(R.drawable.img_2);  // Placeholder image
//        }
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
        tvMoTa = findViewById(R.id.tvMoTaSPCT);
    }
}

