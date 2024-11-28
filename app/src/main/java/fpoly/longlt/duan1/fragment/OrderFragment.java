package fpoly.longlt.duan1.fragment;

import static fpoly.longlt.duan1.screen.LoginScreen.id_userHere;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import fpoly.longlt.duan1.R;
import fpoly.longlt.duan1.adapter.BillsAdapter;
import fpoly.longlt.duan1.dao.BillsDAO;
import fpoly.longlt.duan1.dao.UserDAO;
import fpoly.longlt.duan1.model.DonHang;


public class OrderFragment extends Fragment {
    TextView txtNameUserBills;
    RecyclerView rc_content_bills;
    BillsAdapter billsAdapter;
    BillsDAO billsDAO;
    UserDAO userDAO;
    ArrayList<DonHang> donHangArrayList = new ArrayList<>();
    public OrderFragment() {

    }


    public static OrderFragment newInstance() {
        OrderFragment fragment = new OrderFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        txtNameUserBills = view.findViewById(R.id.txtNameUserBills);
        rc_content_bills = view.findViewById(R.id.rc_content_bills);
        // Xet username
        userDAO = new UserDAO(getContext());
        txtNameUserBills.setText(userDAO.getNameUserByID(id_userHere));
        // Xet adapter cho Bills
        rc_content_bills.setLayoutManager(new LinearLayoutManager(getContext().getApplicationContext()));
        billsDAO = new BillsDAO(getContext().getApplicationContext());
        donHangArrayList = billsDAO.getAll(id_userHere);
        billsAdapter = new BillsAdapter(getContext(),donHangArrayList,billsDAO);
        rc_content_bills.setAdapter(billsAdapter);
        billsAdapter.notifyDataSetChanged();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_order, container, false);
    }
}