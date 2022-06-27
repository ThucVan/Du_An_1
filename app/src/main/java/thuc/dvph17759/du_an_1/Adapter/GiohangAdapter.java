package thuc.dvph17759.du_an_1.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

import thuc.dvph17759.du_an_1.Model.Giohang;
import thuc.dvph17759.du_an_1.R;

public class GiohangAdapter extends BaseAdapter {
    Context context;
    ArrayList<Giohang> giohangArrayList;

    public GiohangAdapter(Context context, ArrayList<Giohang> giohangArrayList) {
        this.context = context;
        this.giohangArrayList = giohangArrayList;
    }

    @Override
    public int getCount() {
        return giohangArrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return giohangArrayList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }
    public class GHViewHolder{
        ImageView imgiohang;
        TextView txttengiohang, txtgiagiohang;
        Button btnCong,btnTru,btnGiaTri;
    }
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        GHViewHolder viewHolder = null;
        if(view==null)
        {
            viewHolder = new GHViewHolder();
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.item_giohang,null);
            viewHolder.txttengiohang = view.findViewById(R.id.textviewgiohang);
            viewHolder.txtgiagiohang = view.findViewById(R.id.textviewgiagiohang);
            viewHolder.imgiohang = view.findViewById(R.id.imageviewgiohang);
            viewHolder.btnGiaTri = view.findViewById(R.id.buttonvalues);
//            viewHolder.btnTru = view.findViewById(R.id.buttonminus);
//            viewHolder.btnCong = view.findViewById(R.id.buttonplus);
            view.setTag(viewHolder);
        }
        else
        {
            viewHolder = (GHViewHolder)view.getTag();
        }
        Giohang giohang = (Giohang)getItem(i);
        viewHolder.txttengiohang.setText(giohang.getTensp());
        DecimalFormat decimalFormat =new DecimalFormat("###,###,###");
        viewHolder.txtgiagiohang.setText(String.valueOf(giohang.getGiasp()));
        Picasso.get().load(giohang.getHinhsp())
                .placeholder(R.drawable.home)
                .error(R.drawable.erro)
                .into(viewHolder.imgiohang);
        viewHolder.btnGiaTri.setText(Integer.toString(giohang.getSoluongsp()));//lay gia tri
        return view;
    }
}
