<<<<<<<< HEAD:app/src/main/java/fpoly/longlt/duan1/screen/MainActivity.java
package fpoly.longlt.duan1.screen;
========
package fpoly.longlt.duan1.Screen;
>>>>>>>> Bao:app/src/main/java/fpoly/longlt/duan1/Screen/ManHinhCho.java

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import fpoly.longlt.duan1.R;

<<<<<<<< HEAD:app/src/main/java/fpoly/longlt/duan1/screen/MainActivity.java
public class MainActivity extends AppCompatActivity {
========
public class ManHinhCho extends AppCompatActivity {
>>>>>>>> Bao:app/src/main/java/fpoly/longlt/duan1/Screen/ManHinhCho.java

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
    }
}