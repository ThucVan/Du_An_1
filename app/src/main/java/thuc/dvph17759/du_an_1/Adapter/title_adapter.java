//package thuc.dvph17759.du_an_1.Adapter;
//
//import android.content.Context;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import thuc.dvph17759.du_an_1.Model.Phone;
//import thuc.dvph17759.du_an_1.Model.title;
//import thuc.dvph17759.du_an_1.R;
//
//public class title_adapter extends RecyclerView.Adapter<title_adapter.ViewHolder> {
//    List<title> list;
//    Context context;
//
//    public title_adapter(List<title> list, Context context) {
//        this.list = list;
//        this.context = context;
//    }
//
//    @NonNull
//    @Override
//    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_title, null);
//
//        return new ViewHolder(v);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
//        title obj = list.get(position);
//
//        holder.tvNameTitle.setText(obj.getName());
//
//        // data test
//        List<Phone> phoneList = new ArrayList<>();
//        phoneList.add(new Phone(1,R.drawable.img_ip13, "Iphone 13 pro max", "40.000.000", "thông tin chi tiết", "35.000.000"));
//        phoneList.add(new Phone(2,R.drawable.img_z_flod3, "galaxy zfod3", "30.000.000", "thông tin chi tiết", "25.000.000"));
//        phoneList.add(new Phone(3,R.drawable.img_xiaomi_t11_pro, "xiaomi T11 pro", "20.000.000", "thông tin chi tiết", "15.000.000"));
//        phoneList.add(new Phone(4,R.drawable.img_ipord, "airpod 3", "4.000.000", "thông tin chi tiết", "3.500.000"));
//
//        phoneList.add(new Phone(1,R.drawable.img_ip13, "Iphone 13 pro max", "40.000.000", "thông tin chi tiết", "35.000.000"));
//        phoneList.add(new Phone(2,R.drawable.img_z_flod3, "galaxy zfod3", "30.000.000", "thông tin chi tiết", "25.000.000"));
//        phoneList.add(new Phone(3,R.drawable.img_xiaomi_t11_pro, "xiaomi T11 pro", "20.000.000", "thông tin chi tiết", "15.000.000"));
//        phoneList.add(new Phone(4,R.drawable.img_ipord, "airpod 3", "4.000.000", "thông tin chi tiết", "3.500.000"));
//
//        phone_adapter adapter = new phone_adapter(phoneList, context);
//        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context, RecyclerView.HORIZONTAL, false);
//        holder.rcv_product.setLayoutManager(layoutManager);
//        holder.rcv_product.setAdapter(adapter);
//
//
//        // chuyển frg xem thêm các sản phẩm cùng title
//        holder.tv_xem_them.setOnClickListener(v -> {
//            Toast.makeText(v.getContext(), "gọi hàm Xem Thêm", Toast.LENGTH_SHORT).show();
//        });
//
//    }
//
//    @Override
//    public int getItemCount() {
//        return list.size();
//    }
//
//    public class ViewHolder extends RecyclerView.ViewHolder {
//        TextView tvNameTitle, tv_xem_them;
//        RecyclerView rcv_product;
//        public ViewHolder(@NonNull View itemView) {
//            super(itemView);
//
//            tvNameTitle = itemView.findViewById(R.id.tv_name_title);
//            rcv_product = itemView.findViewById(R.id.rcv_product);
//            tv_xem_them = itemView.findViewById(R.id.tv_xem_them);
//        }
//    }
//}
