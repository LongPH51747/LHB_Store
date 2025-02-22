package fpoly.longlt.duan1.screen;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.textfield.TextInputEditText;

import org.w3c.dom.Text;

import fpoly.longlt.duan1.Dao.UserDAO;
import fpoly.longlt.duan1.R;
import fpoly.longlt.duan1.Dao.UserDAO;

public class LoginScreen extends AppCompatActivity {
    private TextInputEditText edtUserNameLogIn, edtPassWordLogIn;
    private TextView txtRegisterPage;
    private Button btnLogIn;
    private UserDAO userDAO;
    private String name, pass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login_screen);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        anhXa();
        txtRegisterPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginScreen.this, RegisterScreen.class));
                finish();
            }
        });
        btnLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkLogIn();
            }
        });
    }

    private void checkLogIn() {
        name = edtUserNameLogIn.getText().toString();
        pass = edtPassWordLogIn.getText().toString();
        if (name.isEmpty() || pass.isEmpty()){
            Toast.makeText(this, "The Box was Empty...404...", Toast.LENGTH_SHORT).show();
        }else {
            userDAO = new UserDAO(this);
            if (userDAO.checkLogInAdmin(name,pass)){
                Toast.makeText(this, "Welcome Back Sir!!!", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(LoginScreen.this, AdminScreen.class));
                finish();
            }
            if (userDAO.checkLoginUser(name, pass)) {
                Toast.makeText(this, "Dang Nhap Thanh Cong", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(LoginScreen.this, ManHinhChinh.class));
                finish();
            }else {
                Toast.makeText(this, "Wrong Username or Password...", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void anhXa() {
        edtUserNameLogIn = findViewById(R.id.edtUserNameLogin);
        edtPassWordLogIn = findViewById(R.id.edtPassWordLogin);
        btnLogIn = findViewById(R.id.btnLogin);
        txtRegisterPage = findViewById(R.id.txtRegisterPage);
    }
}