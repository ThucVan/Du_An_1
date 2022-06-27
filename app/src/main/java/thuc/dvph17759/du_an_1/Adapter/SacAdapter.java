package thuc.dvph17759.du_an_1.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

import thuc.dvph17759.du_an_1.Activity.ChiTietSac;
import thuc.dvph17759.du_an_1.Model.Phone_Charger;
import thuc.dvph17759.du_an_1.R;

public class SacAdapter extends RecyclerView.Adapter<SacAdapter.ItemViewHolder> {
    Context context;
    ArrayList<Phone_Charger> sacArrayList;

    public SacAdapter(Context context, ArrayList<Phone_Charger> sacArrayList) {
        this.context = context;
        this.sacArrayList = sacArrayList;
    }

    //tao layout
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product_ngang,null);
        ItemViewHolder itemViewHolder = new ItemViewHolder(view);
//        notifyDataSetChanged();
        return itemViewHolder;
    }
    //xu ly du lieu
    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        Phone_Charger sac = sacArrayList.get(position);
        holder.txttensanpham.setText(sac.getTensp());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        holder.txtgiasanpham.setText("Giá: "+decimalFormat.format(sac.getGia()));
        Picasso.get().load(sac.getImg())
                .placeholder(R.drawable.home)
                .error(R.drawable.erro)
                .into(holder.imghinhsp);

        holder.layout.setOnClickListener(v -> {
            Intent intent = new Intent(context, ChiTietSac.class);
            intent.putExtra("thongtinsanpham", sac);
            context.startActivity(intent);

//            Toast.makeText(context, "phần tử thứ : " + dt, Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    public int getItemCount() {
        return sacArrayList.size();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder{
        LinearLayout layout;
        ImageView imghinhsp;
        TextView txttenhang,txttensanpham,txtgiasanpham,txtchitiet;
        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            layout = itemView.findViewById(R.id.layout_item);
            imghinhsp = itemView.findViewById(R.id.img_product);
            txttensanpham = itemView.findViewById(R.id.tv_name_product);
            txtgiasanpham = itemView.findViewById(R.id.tv_price_product);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });
        }
    }
}
