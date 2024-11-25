package fpoly.longlt.duan1.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;

import fpoly.longlt.duan1.R;
import fpoly.longlt.duan1.adapter.SanPhamAdapter;
import fpoly.longlt.duan1.dao.SanPhamDAO;
import fpoly.longlt.duan1.model.SanPham;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AccountFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AccountFragment extends Fragment {
    SanPhamDAO sanPhamDAO;
    Button btnPayment, btnVoucher, btnEdit;
    RecyclerView rvProduct;
    SanPhamAdapter adapter;
    ArrayList<SanPham> arrayList;




    public AccountFragment() {

    }


    public static AccountFragment newInstance() {
        AccountFragment fragment = new AccountFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.fragment_account, container, false);
            rvProduct = view.findViewById(R.id.rv_products);

        return view;
    }

    public void LoadData(){
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getContext(), 2);
        rvProduct.setLayoutManager(layoutManager);
        sanPhamDAO = new SanPhamDAO(getContext());
        arrayList = sanPhamDAO.getAllSP();
        adapter = new SanPhamAdapter(getContext(), arrayList, sanPhamDAO);
        rvProduct.setAdapter(adapter);
    }
}