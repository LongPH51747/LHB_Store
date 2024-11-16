package fpoly.longlt.duan1.Adapter;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import fpoly.longlt.duan1.R;
import fpoly.longlt.duan1.model.SanPham;

public class SanPhamAdapter extends RecyclerView.Adapter<SanPhamAdapter.SanPhamViewHolder>{
    private ArrayList<SanPham> arrayList;

    public SanPhamAdapter(ArrayList<SanPham> arrayList) {
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public SanPhamViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_sp_kh, parent, false);

        return new SanPhamViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SanPhamViewHolder holder, int position) {
        SanPham sanPham =arrayList.get(position);
holder.tvNameSP.setText(sanPham.getTenSp());
holder.tvPriceSP.setText(sanPham.getPrice()+"");
holder.imgSP.setImageResource(sanPham.getImg());

    }

    @Override
    public int getItemCount() {
        if(arrayList != null){
            return arrayList.size();
        }
        return 0;
    }

    public static class SanPhamViewHolder extends RecyclerView.ViewHolder {
     ImageView imgSP;
     TextView tvNameSP, tvPriceSP;
        public SanPhamViewHolder(@NonNull View itemView) {
            super(itemView);
            imgSP = itemView.findViewById(R.id.img_sp_kh);
            tvNameSP = itemView.findViewById(R.id.tv_name_sp_kh);
            tvPriceSP = itemView.findViewById(R.id.tv_price_sp_kh);
        }
    }


}
