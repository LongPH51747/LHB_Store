package fpoly.longlt.duan1.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import fpoly.longlt.duan1.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link QuanLIKhachHang_Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class QuanLIKhachHang_Fragment extends Fragment {

    public QuanLIKhachHang_Fragment() {
        // Required empty public constructor
    }

    public static QuanLIKhachHang_Fragment newInstance() {
        QuanLIKhachHang_Fragment fragment = new QuanLIKhachHang_Fragment();
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
        return inflater.inflate(R.layout.fragment_quan_l_i_khach_hang_, container, false);
    }
}