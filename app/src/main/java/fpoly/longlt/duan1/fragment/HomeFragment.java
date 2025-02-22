package fpoly.longlt.duan1.fragment;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import fpoly.longlt.duan1.Adapter.BannerAdapter;
import fpoly.longlt.duan1.Adapter.SanPhamAdapter;
import fpoly.longlt.duan1.model.SanPham;
import fpoly.longlt.duan1.R;

public class HomeFragment extends Fragment {
    private ViewPager2 viewPager2;
    private RecyclerView recyclerView;
    private Handler sliderHandler = new Handler();
    private int currentPage = 0;
    private static final int SLIDE_DELAY = 3000; // Thời gian chờ giữa các slide, tính bằng ms

    private SQLiteDatabase db;
    private SanPhamAdapter sanPhamAdapter;
    private ArrayList<SanPham> arrayList;

    public HomeFragment() {
        // Constructor mặc định
    }

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Mở hoặc tạo database (cập nhật tên của database theo cấu trúc dự án của bạn)

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        recyclerView = view.findViewById(R.id.recyclerViewProducts);

        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));


        viewPager2 = view.findViewById(R.id.viewPagerBanner);


        // Danh sách ảnh banner
        List<Integer> bannerImages = Arrays.asList(
                R.drawable.banner1,
                R.drawable.banner2,
                R.drawable.banner3,
                R.drawable.banner4
        );

        // Thiết lập Adapter cho ViewPager2
        BannerAdapter bannerAdapter = new BannerAdapter(getContext(), bannerImages);
        viewPager2.setAdapter(bannerAdapter);

        // Thiết lập vòng lặp tự động
        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                currentPage = position;
                sliderHandler.removeCallbacks(slideRunnable);
                sliderHandler.postDelayed(slideRunnable, SLIDE_DELAY);
            }
        });
   arrayList = new ArrayList<>();
   arrayList.add(new SanPham("Áo hoodie", R.drawable.img_2, 200000));
        arrayList.add(new SanPham("Quần jean trắng", R.drawable.img_3, 200000));
        arrayList.add(new SanPham("Áo hoodie", R.drawable.img_2, 200000));
        arrayList.add(new SanPham("Quần jean trắng", R.drawable.img_3, 200000));

        sanPhamAdapter = new SanPhamAdapter(arrayList);
        recyclerView.setAdapter(sanPhamAdapter);


        // Thiết lập RecyclerView cho danh sách sản phẩm
        return view;
    }

    private final Runnable slideRunnable = new Runnable() {
        @Override
        public void run() {
            if (viewPager2.getAdapter() != null) {
                int itemCount = viewPager2.getAdapter().getItemCount();
                currentPage = (currentPage + 1) % itemCount;
                viewPager2.setCurrentItem(currentPage, true);
                sliderHandler.postDelayed(this, SLIDE_DELAY);
            }
        }
    };

    @Override
    public void onResume() {
        super.onResume();
        sliderHandler.postDelayed(slideRunnable, SLIDE_DELAY);
    }

    @Override
    public void onPause() {
        super.onPause();
        sliderHandler.removeCallbacks(slideRunnable);
    }


}
