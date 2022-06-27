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
import thuc.dvph17759.du_an_1.Model.OpDienThoai;
import thuc.dvph17759.du_an_1.R;

public class adpter_op_admin extends RecyclerView.Adapter<adpter_op_admin.ViewHoler> {
    Context context;
    ArrayList<OpDienThoai> opDienThoaiArrayList;
    ArrayList<OpDienThoai> list;

    public adpter_op_admin(Context context, ArrayList<OpDienThoai> opDienThoaiArrayList) {
        this.context = context;
        this.opDienThoaiArrayList = opDienThoaiArrayList;
        this.list = opDienThoaiArrayList;
    }

    @NonNull
    @Override
    public ViewHoler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product_ngang, null);
//        notifyDataSetChanged();
        return new ViewHoler(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHoler holder, int position) {
        OpDienThoai op = opDienThoaiArrayList.get(position);
        holder.txtname.setText(op.getName());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        holder.txtprice.setText("Giá: "+decimalFormat.format(op.getPrice()));
        Picasso.get().load(op.getImg())
                .placeholder(R.drawable.home)
                .error(R.drawable.erro)
                .into(holder.imghinhsp);

        holder.layout.setOnClickListener(v -> {
            // gọi hàm xóa sản phẩm
            AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
            builder.setMessage("Bạn có chắc chắn muốn xóa sản phẩm này ?")
                    .setNegativeButton("OK", (dialog, which) -> {
                        opDienThoaiArrayList.remove(position);
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
            bundle.putString("name_prodcut", op.getName());
            bundle.putString("price_prodcut", String.valueOf(op.getPrice()));
            bundle.putString("img_prodcut", op.getImg());
            bundle.putString("deltais_prodcut", op.getMota());
            intent.putExtras(bundle);
            v.getContext().startActivity(intent);
            return true;
        });
    }

    @Override
    public int getItemCount() {
        return opDienThoaiArrayList.size();
    }

    public class ViewHoler extends RecyclerView.ViewHolder {
        LinearLayout layout;
        ImageView imghinhsp;
        TextView txtname, txtprice, txtmota, txtsale;

        public ViewHoler(@NonNull View itemView) {
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
                    opDienThoaiArrayList = list;
                } else {
                    List<OpDienThoai> opDienThoais = new ArrayList<>();
                    for (OpDienThoai opDienThoai : list) {
                        if (opDienThoai.getName().toLowerCase().contains(search.toLowerCase())) {
                            opDienThoais.add(opDienThoai);
                        }
                    }
                    opDienThoaiArrayList = (ArrayList<OpDienThoai>) opDienThoais;
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = opDienThoaiArrayList;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                opDienThoaiArrayList = (ArrayList<OpDienThoai>) results.values;
                notifyDataSetChanged();
            }
        };
    }

}
