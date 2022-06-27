package thuc.dvph17759.du_an_1.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import thuc.dvph17759.du_an_1.Activity.ChiTietDienThoai;
import thuc.dvph17759.du_an_1.Model.DienThoai;
import thuc.dvph17759.du_an_1.R;

public class DienThoaiAdapter extends RecyclerView.Adapter<DienThoaiAdapter.ItemViewHolder> implements Filterable {

    Context context;
    ArrayList<DienThoai> dienthoaiArrayList;
    ArrayList<DienThoai> list;

    public DienThoaiAdapter(Context context, ArrayList<DienThoai> dienthoaiArrayList) {
        this.context = context;
        this.dienthoaiArrayList = dienthoaiArrayList;
        this.list = dienthoaiArrayList;
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
        DienThoai dt = dienthoaiArrayList.get(position);
        holder.txtname.setText(dt.getName());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        holder.txtprice.setText("Giá: "+decimalFormat.format(dt.getPrice()));
//        holder.txtmota.setText(dt.getDetails());
//        holder.txtsale.setText(dt.getSale());

        Picasso.get().load(dt.getImg())
                .placeholder(R.drawable.home)
                .error(R.drawable.erro)
                .into(holder.imghinhsp);

        holder.layout.setOnClickListener(v -> {
            Intent intent = new Intent(context, ChiTietDienThoai.class);
            intent.putExtra("thongtinsanpham", dt);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            }
            context.startActivity(intent);
//            Toast.makeText(context, "phần tử thứ : " + dt, Toast.LENGTH_SHORT).show();
        });

    }

    @Override
    public int getItemCount() {
        return dienthoaiArrayList.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String search = constraint.toString();
                if(search.isEmpty()){
                    dienthoaiArrayList = list;
                }else {
                    List<DienThoai> dt = new ArrayList<>();
                    for(DienThoai dienThoai : list){
                        if(dienThoai.getName().toLowerCase().contains(search.toLowerCase())){
                            dt.add(dienThoai);
                        }
                    }
                    dienthoaiArrayList = (ArrayList<DienThoai>) dt;
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = dienthoaiArrayList;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                dienthoaiArrayList = (ArrayList<DienThoai>) results.values;
                notifyDataSetChanged();
            }
        };
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder{
        LinearLayout layout;
        ImageView imghinhsp;
        TextView txtname,txtprice,txtmota,txtsale;
        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            layout = itemView.findViewById(R.id.layout_item);
            imghinhsp = itemView.findViewById(R.id.img_product);
            txtname = itemView.findViewById(R.id.tv_name_product);
            txtprice = itemView.findViewById(R.id.tv_price_product);
//            txtmota = itemView.findViewById(R.id.textviewmota);
//            txtsale = itemView.findViewById(R.id.textviewsale);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });
        }
    }
}
