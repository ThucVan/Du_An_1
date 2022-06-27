package thuc.dvph17759.du_an_1.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import thuc.dvph17759.du_an_1.Activity.MainActivitySuSanPham;
import thuc.dvph17759.du_an_1.Model.DienThoai;
import thuc.dvph17759.du_an_1.R;

public class adapter_admin_dienThoai extends RecyclerView.Adapter<adapter_admin_dienThoai.ItemViewHolder> implements Filterable {
    Context context;
    ArrayList<DienThoai> dienthoaiArrayList;
    ArrayList<DienThoai> list;

    public adapter_admin_dienThoai(Context context, ArrayList<DienThoai> dienthoaiArrayList) {
        this.context = context;
        this.dienthoaiArrayList = dienthoaiArrayList;
        this.list = dienthoaiArrayList;
    }

    //tao layout
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product_ngang, null);
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
        holder.txtprice.setText("Giá: " + decimalFormat.format(dt.getPrice()));

        Picasso.get().load(dt.getImg())
                .placeholder(R.drawable.home)
                .error(R.drawable.erro)
                .into(holder.imghinhsp);

        holder.layout.setOnClickListener(v -> {
            // gọi hàm xóa sản phẩm
            AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
            builder.setMessage("Bạn có chắc chắn muốn xóa sản phẩm này ?")
                    .setNegativeButton("OK", (dialog, which) -> {
                        dienthoaiArrayList.remove(position);
                        dialog.dismiss();
                        Toast.makeText(v.getContext(), "Xóa Thành Công !!!", Toast.LENGTH_SHORT).show();
                    })
                    .setPositiveButton("No", (dialog, which) -> {
                        dialog.dismiss();
                    });
            AlertDialog alertDialog = builder.create();
            alertDialog.show();
            builder.show();
            });

        holder.layout.setOnLongClickListener(v -> {
            //gọi hàm sửa sản phẩm
            Intent intent = new Intent(v.getContext(), MainActivitySuSanPham.class);
            Bundle bundle = new Bundle();
            bundle.putString("name_prodcut", dt.getName());
            bundle.putString("price_prodcut", String.valueOf(dt.getPrice()));
            bundle.putString("img_prodcut", dt.getImg());
            bundle.putString("deltais_prodcut", dt.getDetails());
            intent.putExtras(bundle);
            v.getContext().startActivity(intent);
            return true;
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
                if (search.isEmpty()) {
                    dienthoaiArrayList = list;
                } else {
                    List<DienThoai> dt = new ArrayList<>();
                    for (DienThoai dienThoai : list) {
                        if (dienThoai.getName().toLowerCase().contains(search.toLowerCase())) {
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

    public class ItemViewHolder extends RecyclerView.ViewHolder {
        LinearLayout layout;
        ImageView imghinhsp;
        TextView txtname, txtprice, txtmota, txtsale;

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