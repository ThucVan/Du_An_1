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
import thuc.dvph17759.du_an_1.Model.Phone_Charger;
import thuc.dvph17759.du_an_1.R;

public class adapter_admin_sac extends RecyclerView.Adapter<adapter_admin_sac.ViewHolder> {
    Context context;
    ArrayList<Phone_Charger> sacArrayList;
    ArrayList<Phone_Charger> list;

    public adapter_admin_sac(Context context, ArrayList<Phone_Charger> sacArrayList) {
        this.context = context;
        this.sacArrayList = sacArrayList;
        this.list = sacArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product_ngang, null);
//        notifyDataSetChanged();
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Phone_Charger sac = sacArrayList.get(position);
        holder.txtname.setText(sac.getTensp());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        holder.txtprice.setText("Giá: "+decimalFormat.format(sac.getGia()));
        Picasso.get().load(sac.getImg())
                .placeholder(R.drawable.home)
                .error(R.drawable.erro)
                .into(holder.imghinhsp);

        holder.layout.setOnClickListener(v -> {
            // gọi hàm xóa sản phẩm
            AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
            builder.setMessage("Bạn có chắc chắn muốn xóa sản phẩm này ?")
                    .setNegativeButton("OK", (dialog, which) -> {
                        sacArrayList.remove(position);
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
            bundle.putString("name_prodcut", sac.getTensp());
            bundle.putString("price_prodcut", String.valueOf(sac.getGia()));
            bundle.putString("img_prodcut", sac.getImg());
            bundle.putString("deltais_prodcut", sac.getChitiet());
            intent.putExtras(bundle);
            v.getContext().startActivity(intent);
            return true;
        });
    }

    @Override
    public int getItemCount() {
        return sacArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        LinearLayout layout;
        ImageView imghinhsp;
        TextView txtname, txtprice, txtmota, txtsale;

        public ViewHolder(@NonNull View itemView) {
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
                    sacArrayList = list;
                } else {
                    List<Phone_Charger> phone_chargers = new ArrayList<>();
                    for (Phone_Charger phone_charger : list) {
                        if (phone_charger.getTensp().toLowerCase().contains(search.toLowerCase())) {
                            phone_chargers.add(phone_charger);
                        }
                    }
                    sacArrayList = (ArrayList<Phone_Charger>) phone_chargers;
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = sacArrayList;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                sacArrayList = (ArrayList<Phone_Charger>) results.values;
                notifyDataSetChanged();
            }
        };
    }
}
