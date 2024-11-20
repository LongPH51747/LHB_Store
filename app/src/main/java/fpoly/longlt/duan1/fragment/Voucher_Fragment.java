package fpoly.longlt.duan1.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

import fpoly.longlt.duan1.R;
import fpoly.longlt.duan1.adapter.Voucher_Adapter;
import fpoly.longlt.duan1.dao.VoucherDao;
import fpoly.longlt.duan1.model.Voucher;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Voucher_Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Voucher_Fragment extends Fragment {
    ListView lv_quanli_voucher;
    ArrayList<Voucher> lst = new ArrayList<>();
    Voucher_Adapter adapter;
    VoucherDao voucherDao;

    public Voucher_Fragment() {
        // Required empty public constructor
    }

    public static Voucher_Fragment newInstance() {
        Voucher_Fragment fragment = new Voucher_Fragment();
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
        return inflater.inflate(R.layout.fragment_voucher_, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }
}