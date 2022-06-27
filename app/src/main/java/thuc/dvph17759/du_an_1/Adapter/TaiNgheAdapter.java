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

import thuc.dvph17759.du_an_1.Activity.ChiTietTaiNghe;
import thuc.dvph17759.du_an_1.Model.Head_Phone;
import thuc.dvph17759.du_an_1.R;

public class TaiNgheAdapter extends RecyclerView.Adapter<TaiNgheAdapter.ItemViewHolder> {
    Context context;
    ArrayList<Head_Phone> taingheArrayList;

    public TaiNgheAdapter(Context context, ArrayList<Head_Phone> taingheArrayList) {
        this.context = context;
        this.taingheArrayList = taingheArrayList;
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
        Head_Phone head = taingheArrayList.get(position);
        holder.txttenhang.setText(head.getName());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        holder.txtgia.setText("Giá: "+decimalFormat.format(head.getPrice()));
        Picasso.get().load(head.getImg())
                .placeholder(R.drawable.home)
                .error(R.drawable.erro)
                .into(holder.imghinhsp);

        holder.layout.setOnClickListener(v -> {
            Intent intent = new Intent(context, ChiTietTaiNghe.class);
            intent.putExtra("thongtinsanpham", head);
            context.startActivity(intent);

//            Toast.makeText(context, "phần tử thứ : " + dt, Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    public int getItemCount() {
        return taingheArrayList.size();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder{
        LinearLayout layout;
        ImageView imghinhsp;
        TextView txttenhang,txtgia,txtmota,txtsale;
        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            layout = itemView.findViewById(R.id.layout_item);
            imghinhsp = itemView.findViewById(R.id.img_product);
            txttenhang = itemView.findViewById(R.id.tv_name_product);
            txtgia = itemView.findViewById(R.id.tv_price_product);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });
        }
    }
}