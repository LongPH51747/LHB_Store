package fpoly.longlt.duan1.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import fpoly.longlt.duan1.R;
import fpoly.longlt.duan1.model.Voucher;

public class Voucher_Adapter extends BaseAdapter {
    Context context;
    ArrayList<Voucher> lst = new ArrayList<>();
    TextView tv_gioihan, tv_ma, tv_giatri, tv_dk;

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
        return convertView;
    }

}
