package fpoly.longlt.duan1.adapter;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import fpoly.longlt.duan1.dao.SanPhamDAO;
import fpoly.longlt.duan1.R;
import fpoly.longlt.duan1.model.SanPham;
import fpoly.longlt.duan1.screen.productDetailScreen;

public class SanPhamAdapter extends RecyclerView.Adapter<SanPhamAdapter.SanPhamViewHolder>{
    Context context;
   ArrayList<SanPham> arrayList;
   SanPhamDAO sanPhamDAO;

    public SanPhamAdapter(Context context, ArrayList<SanPham> arrayList, SanPhamDAO sanPhamDAO) {
        this.context = context;
        this.arrayList = arrayList;
        this.sanPhamDAO = sanPhamDAO;
    }

    @NonNull
    @Override
    public SanPhamViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_sp_kh, parent, false);

        return new SanPhamViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SanPhamViewHolder holder, int position) {
        SanPham sanPham = arrayList.get(position);
        holder.tvNameSP.setText(sanPham.getTenSp());
        holder.tvPriceSP.setText(String.valueOf(sanPham.getPrice()));

        // Lấy tên ảnh từ cơ sở dữ liệu (String)
        String imageName = sanPham.getImg();  // Đây là tên ảnh bạn lưu trong cơ sở dữ liệu, ví dụ: "product_image"

        // Lấy ID tài nguyên từ tên ảnh trong drawable
        int imageResId = holder.itemView.getContext().getResources().getIdentifier(imageName, "drawable", holder.itemView.getContext().getPackageName());

        // Kiểm tra nếu tài nguyên ảnh tồn tại
        if (imageResId != 0) {
            holder.imgSP.setImageResource(imageResId);  // Set ảnh từ drawable vào ImageView
        } else {
            // Nếu không tìm thấy ảnh, có thể set ảnh mặc định
            holder.imgSP.setImageResource(R.drawable.img_3);  // Placeholder image
        }

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
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    itemView.getContext().startActivity(new Intent(itemView.getContext(), productDetailScreen.class));
                }
            });
        }
    }

//nothing
}
