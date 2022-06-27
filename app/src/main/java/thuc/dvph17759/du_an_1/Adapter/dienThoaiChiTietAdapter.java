package thuc.dvph17759.du_an_1.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import thuc.dvph17759.du_an_1.Model.DienThoai;
import thuc.dvph17759.du_an_1.R;

public class dienThoaiChiTietAdapter extends RecyclerView.Adapter<dienThoaiChiTietAdapter.ItemViewHolder> {

    Context context;
    ArrayList<DienThoai> dienthoaiArrayList;

    public dienThoaiChiTietAdapter(Context context, ArrayList<DienThoai> dienthoaiArrayList) {
        this.context = context;
        this.dienthoaiArrayList = dienthoaiArrayList;
    }

    //tao layout
    @Override
    public dienThoaiChiTietAdapter.ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_dienthoai,null);
        return new ItemViewHolder(view);
    }
    //xu ly du lieu
    @Override
    public void onBindViewHolder(@NonNull dienThoaiChiTietAdapter.ItemViewHolder holder, int position) {
        DienThoai dt = dienthoaiArrayList.get(position);
        holder.txtname.setText(dt.getName());
        holder.txtprice.setText(dt.getPrice());
        holder.txtmota.setText(dt.getDetails());
        holder.txtsale.setText(dt.getSale());
        Picasso.get().load(dt.getImg())
                .placeholder(R.drawable.home)
                .error(R.drawable.erro)
                .into(holder.imghinhsp);
    }

    @Override
    public int getItemCount() {
        return dienthoaiArrayList.size();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder{
        ImageView imghinhsp;
        TextView txtname,txtprice,txtmota,txtsale;
        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            imghinhsp = itemView.findViewById(R.id.imageviewsanpham);
            txtname = itemView.findViewById(R.id.textviewname);
            txtprice = itemView.findViewById(R.id.textviewprice);
            txtmota = itemView.findViewById(R.id.textviewmota);
            txtsale = itemView.findViewById(R.id.textviewsale);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });
        }
    }
}
