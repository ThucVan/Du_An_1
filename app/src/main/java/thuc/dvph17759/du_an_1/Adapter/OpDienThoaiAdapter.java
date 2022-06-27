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

import thuc.dvph17759.du_an_1.Activity.ChiTietOp;
import thuc.dvph17759.du_an_1.Model.OpDienThoai;
import thuc.dvph17759.du_an_1.R;

public class OpDienThoaiAdapter extends RecyclerView.Adapter<OpDienThoaiAdapter.ItemViewHolder> {

    Context context;
    ArrayList<OpDienThoai> opArrayList;

    public OpDienThoaiAdapter(Context context, ArrayList<OpDienThoai> opArrayList) {
        this.context = context;
        this.opArrayList = opArrayList;
    }
    //tao layout
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product_ngang,null);
        ItemViewHolder itemViewHolder = new ItemViewHolder(view);
        return itemViewHolder;
    }
    //xu ly du lieu
    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        OpDienThoai op = opArrayList.get(position);
        holder.txttenhang.setText(op.getName());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        holder.txtgia.setText("Giá: "+decimalFormat.format(op.getPrice()));
        Picasso.get().load(op.getImg())
                .placeholder(R.drawable.home)
                .error(R.drawable.erro)
                .into(holder.imghinhsp);

        holder.layout.setOnClickListener(v -> {
            Intent intent = new Intent(context, ChiTietOp.class);
            intent.putExtra("thongtinsanpham", op);
            context.startActivity(intent);
//            Toast.makeText(context, "phần tử thứ : " + dt, Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    public int getItemCount() {
        return opArrayList.size();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder{
        LinearLayout layout;
        ImageView imghinhsp;
        TextView txttenhang,txtgia,txtmota;
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
