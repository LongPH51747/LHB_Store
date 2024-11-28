package fpoly.longlt.duan1.screen;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.textfield.TextInputEditText;

import fpoly.longlt.duan1.R;
import fpoly.longlt.duan1.dao.UserDAO;
import fpoly.longlt.duan1.fragment.AccountFragment;
import fpoly.longlt.duan1.model.User;


public class EditProfileScreen extends AppCompatActivity {
    ImageView ivTurnBack, profileImage;
    TextInputEditText edtNameUpdate, edtPhoneNumberUpdate, edtAddressUpdate;
    Button btnCancel, btnUpdate;
    String name, phone, address, img;

    public static final int PICK_IMAGE = 1;
    private ActivityResultLauncher<Intent> galleryLauncher;
    Uri imgUri;
    public String imagePath;
    UserDAO userDAO;
    private int id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_edit_profile_screen);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        // Ánh xạ
        anhXa();

        // Lay id cua user
        Intent intent = getIntent();
        User user = (User) intent.getSerializableExtra("user_id");
        if (user != null){
            id = user.getId_user();
            Log.d("id", "id: "+id);
        }else {
            Log.e("id", "onCreate: " + id);
        }
        // Phan tro ra chuyen doi man hinh tu activity sang fragment
        ivTurnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager().beginTransaction().replace(R.id.framelayoutInAccount, AccountFragment.newInstance()).commit();
                finish();
            }
        });

        // Huy cac thuoc tinh da them
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edtAddressUpdate.setText("");
                edtNameUpdate.setText("");
                edtPhoneNumberUpdate.setText("");
                profileImage.setImageResource(R.drawable.baseline_account_circle_24);
            }
        });

        // Chon anh tu thu vien
    }

    // Ham mo thu vien anh

    private void anhXa() {
        ivTurnBack = findViewById(R.id.ivBack);
        profileImage = findViewById(R.id.profileImage);
        edtNameUpdate = findViewById(R.id.etName);
        edtAddressUpdate = findViewById(R.id.etAddress);
        edtPhoneNumberUpdate = findViewById(R.id.etPhone);
        btnCancel = findViewById(R.id.btnCancel);
        btnUpdate = findViewById(R.id.btnSave);
    }
}