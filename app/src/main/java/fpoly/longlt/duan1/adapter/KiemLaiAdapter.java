package fpoly.longlt.duan1.adapter;

import static fpoly.longlt.duan1.screen.ProductDetailScreen.sp_id;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import fpoly.longlt.duan1.R;
import fpoly.longlt.duan1.dao.SanPhamChiTietDAO;
import fpoly.longlt.duan1.model.ChiTietSP;

public class KiemLaiAdapter extends RecyclerView.Adapter<KiemLaiAdapter.KiemLaiViewHolder> {
    Context context;
    ArrayList<ChiTietSP> list;
    String nameString;
    int priceAmount, priceOneProduct;
    SanPhamChiTietDAO sanPhamChiTietDAO;

    public KiemLaiAdapter(Context context, ArrayList<ChiTietSP> list, SanPhamChiTietDAO sanPhamChiTietDAO) {
        this.context = context;
        this.list = list;
        this.sanPhamChiTietDAO = sanPhamChiTietDAO;
    }

    @NonNull
    @Override
    public KiemLaiViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_prepare_order, parent, false);
        return new KiemLaiViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull KiemLaiViewHolder holder, int position) {
        ChiTietSP chiTietSP = list.get(position);
        nameString = sanPhamChiTietDAO.getNameProductByID_SP(position);
        priceOneProduct = sanPhamChiTietDAO.getPriceByID(position);
        Log.e("gia1sp", "onBindViewHolder: " + priceOneProduct );
        priceAmount = priceOneProduct * chiTietSP.getSoluong();
        holder.name.setText("Name Product: "+ nameString);
        holder.colorAndSize.setText("Màu sắc: " + chiTietSP.getColor()+"| Size: "+ chiTietSP.getSize());
        holder.number.setText("x" + chiTietSP.getSoluong());
        holder.price.setText("Price: " + priceAmount +"đ");
    }

    @Override
    public int getItemCount() {
        if(list != null)
            return list.size();
        return 0;
    }

    public static class KiemLaiViewHolder extends RecyclerView.ViewHolder {
        TextView name, colorAndSize, price, number;
        ImageView imgPrepare;
        public KiemLaiViewHolder(@NonNull View view) {
            super(view);
            name = view.findViewById(R.id.tvProductName);
            colorAndSize = view.findViewById(R.id.tvProductDetailsColorAndSize);
            price = view.findViewById(R.id.tvProductPrice);
            number = view.findViewById(R.id.tvProductQuantity);
            imgPrepare = view.findViewById(R.id.ivProductImage);
        }
    }
}
