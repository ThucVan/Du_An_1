package thuc.dvph17759.du_an_1.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
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
import thuc.dvph17759.du_an_1.Model.Head_Phone;
import thuc.dvph17759.du_an_1.R;

public class adapter_admin_taiNghe extends RecyclerView.Adapter<adapter_admin_taiNghe.viewHolder> {
    Context context;
    ArrayList<Head_Phone> taingheArrayList;
    ArrayList<Head_Phone> list;

    public adapter_admin_taiNghe(Context context, ArrayList<Head_Phone> taingheArrayList) {
        this.context = context;
        this.taingheArrayList = taingheArrayList;
        this.list = taingheArrayList;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product_ngang, null);
//        notifyDataSetChanged();
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        Head_Phone head = taingheArrayList.get(position);
        holder.txtname.setText(head.getName());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        holder.txtprice.setText("Giá: "+decimalFormat.format(head.getPrice()));
        Picasso.get().load(head.getImg())
                .placeholder(R.drawable.home)
                .error(R.drawable.erro)
                .into(holder.imghinhsp);

        holder.layout.setOnClickListener(v -> {
            // gọi hàm xóa sản phẩm
            AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
            builder.setMessage("Bạn có chắc chắn muốn xóa sản phẩm này ?")
                    .setNegativeButton("OK", (dialog, which) -> {
                        taingheArrayList.remove(position);
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
            bundle.putString("name_prodcut", head.getName());
            bundle.putString("price_prodcut", String.valueOf(head.getPrice()));
            bundle.putString("img_prodcut", head.getImg());
            bundle.putString("deltais_prodcut", head.getDetails());
            intent.putExtras(bundle);
            v.getContext().startActivity(intent);
            return true;
        });
    }

    @Override
    public int getItemCount() {
        return taingheArrayList.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        LinearLayout layout;
        ImageView imghinhsp;
        TextView txtname, txtprice, txtmota, txtsale;

        public viewHolder(@NonNull View itemView) {
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

    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String search = constraint.toString();
                if (search.isEmpty()) {
                    taingheArrayList = list;
                } else {
                    List<Head_Phone> head_phones = new ArrayList<>();
                    for (Head_Phone head_phone : list) {
                        if (head_phone.getName().toLowerCase().contains(search.toLowerCase())) {
                            head_phones.add(head_phone);
                        }
                    }
                    taingheArrayList = (ArrayList<Head_Phone>) head_phones;
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = taingheArrayList;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                taingheArrayList = (ArrayList<Head_Phone>) results.values;
                notifyDataSetChanged();
            }
        };
    }
}
