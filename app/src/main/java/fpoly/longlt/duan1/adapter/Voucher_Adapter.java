package fpoly.longlt.duan1.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import fpoly.longlt.duan1.R;
import fpoly.longlt.duan1.model.Voucher;

public class Voucher_Adapter extends BaseAdapter {
    Context context;
    ArrayList<Voucher> lst = new ArrayList<>();
    TextView tv_gioihan, tv_ma, tv_giatri, tv_dk;
    Button btn_sd;
    Voucher vc;

    public Voucher_Adapter(Context context, ArrayList<Voucher> lst) {
        this.context = context;
        this.lst = lst;
    }

    @Override
    public int getCount() {
        if (lst != null){
            return lst.size();
        }
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = View.inflate(context, R.layout.list_item_voucher, null);

        tv_ma = convertView.findViewById(R.id.tv_ma_voucher);
        tv_giatri = convertView.findViewById(R.id.tv_gia_tri_giam);
        tv_gioihan = convertView.findViewById(R.id.tv_thoi_han);
        tv_dk = convertView.findViewById(R.id.tv_dieu_kien);
        btn_sd = convertView.findViewById(R.id.btn_su_dung);

        vc = lst.get(position);
        tv_ma.setText("Mã voucher: "+vc.getCode());
        tv_giatri.setText("Giá trị giảm: "+vc.getDiscount_price());
        tv_gioihan.setText("Thời gian sử dụng: \n"+vc.getStart_date()+"-"+vc.getEnd_date());
        tv_dk.setText("Đơn hàng phải lớn hơn "+vc.getDieukien());

        return convertView;
    }

}
