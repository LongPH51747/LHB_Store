package fpoly.longlt.duan1.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import fpoly.longlt.duan1.R;
import fpoly.longlt.duan1.dao.DonHangDao;
import fpoly.longlt.duan1.model.DonHang;

public class QuanLiDonHang_Adapter extends BaseAdapter {
    ArrayList<DonHang> lst = new ArrayList<>();
    Context context;
    ImageView img_anh;
    TextView tv_ma_dh, tv_ten_kh, tv_ngay_dat, tv_tong_tien;

    public QuanLiDonHang_Adapter(ArrayList<DonHang> lst, Context context) {
        this.lst = lst;
        this.context = context;
    }

    @Override
    public int getCount() {
        if (lst != null) {
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
        convertView = View.inflate(context, R.layout.list_item_quanlidonhang_admin,null);

        img_anh = convertView.findViewById(R.id.img_sp_donhang_admin);
        tv_ma_dh = convertView.findViewById(R.id.tv_ma_don_hang_admin);
        tv_ten_kh = convertView.findViewById(R.id.tv_ten_khach_hang_admin);
        tv_ngay_dat = convertView.findViewById(R.id.tv_ngay_dat_hang_admin);
        tv_tong_tien = convertView.findViewById(R.id.tv_tong_gia_tri_admin);


        return convertView;
    }
}
