package fpoly.longlt.duan1.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import fpoly.longlt.duan1.R;
import fpoly.longlt.duan1.model.SanPham;

public class SP_Admin_Adapter extends RecyclerView.Adapter<SP_Admin_Adapter.SP_Admin_Holder> {
    ArrayList<SanPham> lst = new ArrayList<>();
    Context context;

    public SP_Admin_Adapter(ArrayList<SanPham> lst, Context context) {
        this.lst = lst;
        this.context = context;
    }

    @Override
    public int getItemCount() {
        if (lst != null) {
            return lst.size();
        }
        return 0;
    }
    @Override
    public void onBindViewHolder(@NonNull SP_Admin_Holder holder, int position) {
        holder.tv_name_sp_admin.setText(lst.get(position).getTenSp());
        holder.tv_price_sp_admin.setText(lst.get(position).getPrice() + "");
        holder.chk_status_sp_admin.setChecked(lst.get(position).getStatus()==1?true:false);
    }

    @NonNull
    @Override
    public SP_Admin_Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }
    public static final class SP_Admin_Holder extends RecyclerView.ViewHolder {
        ImageView img_sp_admin;
        TextView tv_name_sp_admin, tv_price_sp_admin;
        CheckBox chk_status_sp_admin;
        public SP_Admin_Holder(@NonNull View itemView) {
            super(itemView);
            img_sp_admin = itemView.findViewById(R.id.img_sp_admin);
            tv_name_sp_admin = itemView.findViewById(R.id.tv_name_sp_admin);
            tv_price_sp_admin = itemView.findViewById(R.id.tv_price_sp_admin);
            chk_status_sp_admin = itemView.findViewById(R.id.chk_hidden_sp);
        }
    }
}
