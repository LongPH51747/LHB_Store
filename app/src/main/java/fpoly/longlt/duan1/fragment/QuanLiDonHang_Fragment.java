package fpoly.longlt.duan1.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import fpoly.longlt.duan1.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link QuanLiDonHang_Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class QuanLiDonHang_Fragment extends Fragment {

    public QuanLiDonHang_Fragment() {
        // Required empty public constructor
    }
    public static QuanLiDonHang_Fragment newInstance() {
        QuanLiDonHang_Fragment fragment = new QuanLiDonHang_Fragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_quan_li_don_hang_, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}