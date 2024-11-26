package fpoly.longlt.duan1.fragment;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import fpoly.longlt.duan1.R;
import fpoly.longlt.duan1.adapter.Voucher_Adapter;
import fpoly.longlt.duan1.dao.VoucherDao;
import fpoly.longlt.duan1.model.Voucher;
import fpoly.longlt.duan1.screen.AddVoucher;

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
    TextView btn_add_vch;
    Voucher vc;

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
        lv_quanli_voucher = view.findViewById(R.id.lv_quanli_voucher);
        btn_add_vch = view.findViewById(R.id.btn_add_vch);
        btn_add_vch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                View view1 = getLayoutInflater().inflate(R.layout.activity_add_voucher, null);
                builder.setView(view1);
                AlertDialog dialog = builder.create();
                EditText ed_ma, ed_giagiam, ed_start_date, ed_end_date;
                Button btn_add_vc;
                ImageView imgback;
                ed_ma = view1.findViewById(R.id.ed_ma_vc);
                ed_giagiam = view1.findViewById(R.id.ed_giagiam_vc);
                ed_start_date = view1.findViewById(R.id.ed_start_date);
                ed_end_date = view1.findViewById(R.id.ed_end_date);
                btn_add_vc = view1.findViewById(R.id.btn_them_vc);
                imgback = view1.findViewById(R.id.img_back);
                imgback.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                btn_add_vc.setOnClickListener(new View.OnClickListener() {
                    @Override public void onClick(View v) {
                        String ma = ed_ma.getText().toString();
                        String giagiam = ed_giagiam.getText().toString();
                        String start_date = ed_start_date.getText().toString();
                        String end_date = ed_end_date.getText().toString();
                        if (ma.isEmpty() || giagiam.isEmpty() || start_date.isEmpty() || end_date.isEmpty()) {
                            Toast.makeText(getContext(), "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            voucherDao = new VoucherDao(getContext());
                            vc = new Voucher();
                            vc.setCode(ma);
                            vc.setDiscount_price(Integer.parseInt(giagiam));
                            vc.setStart_date(start_date);
                            vc.setEnd_date(end_date);
                            SimpleDateFormat sdf = new SimpleDateFormat("dd/mm/yyyy");
                            sdf.setLenient(false);
                            try {
                                Date inputDate = sdf.parse(start_date);
                                Date currentDate = new Date();
                                if (inputDate.after(currentDate)){
                                    vc.setStatus(0);
                                }
                                else {
                                    vc.setStatus(1);
                                }
                            } catch (ParseException e) {
                                throw new RuntimeException(e);
                            }

                            // Lấy ngày hiện tại
                            boolean check = voucherDao.insertVC(vc);
                            if (check){
                                Toast.makeText(getContext(), "Thêm voucher thành công", Toast.LENGTH_SHORT).show();
                                dialog.dismiss();
                                loadData();
                            }
                            else {
                                Toast.makeText(getContext(), "thêm voucher thất bại", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });
                dialog.setCancelable(false);
                dialog.show();
            }
        });
        loadData();
    }

    public void loadData(){
        voucherDao = new VoucherDao(getContext());
        lst = voucherDao.getAllVC();
        adapter = new Voucher_Adapter(getActivity(), lst);
        lv_quanli_voucher.setAdapter(adapter);
    }
    public String getDate(){
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH); // Tháng bắt đầu từ 0 (January = 0)
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        String toDay = day + "/" + (month + 1) + "/" + year;
        return toDay;
    }
}
