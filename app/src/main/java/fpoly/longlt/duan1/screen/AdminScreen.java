package fpoly.longlt.duan1.screen;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import fpoly.longlt.duan1.R;
import fpoly.longlt.duan1.fragment.AdminFragment_Accont;
import fpoly.longlt.duan1.fragment.AdminFragment_Home;

public class AdminScreen extends AppCompatActivity {
    BottomNavigationView btnBottomNavigation;
    FrameLayout framelayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_admin_screen);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        anhxa();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.framelayout_admin, AdminFragment_Home.newInstance())
                .commit();
        btnBottomNavigation.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                Fragment frag =null;
                if (menuItem.getItemId() == R.id.nav_home_admin){
                    frag = AdminFragment_Home.newInstance();
                } else if (menuItem.getItemId() == R.id.nav_account_admin) {
                    frag = AdminFragment_Accont.newInstance();
                }
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.framelayout_admin, frag)
                        .commit();
                return true;
            }
        });
    }

    public void anhxa(){
        btnBottomNavigation = findViewById(R.id.btnBottomNavigation);
        framelayout = findViewById(R.id.framelayout_admin);
    }
}