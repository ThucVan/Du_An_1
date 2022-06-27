package thuc.dvph17759.du_an_1.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;

import java.util.List;

import thuc.dvph17759.du_an_1.Activity.MainAppActivity;
import thuc.dvph17759.du_an_1.R;

public class advertisement_adapter extends PagerAdapter {
    private Context context;
    List<MainAppActivity.Advertisiment> list;

    public advertisement_adapter(Context context, List<MainAppActivity.Advertisiment> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View v = LayoutInflater.from(context.getApplicationContext()).inflate(R.layout.item_advertisement, null);

        ImageView img_ad = v.findViewById(R.id.img_ad);
        MainAppActivity.Advertisiment advertisiment = list.get(position);

        if (advertisiment != null){
            Glide.with(context).load(advertisiment.getImg()).into(img_ad);
        }

        container.addView(v);
        return v;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        //remove view
        container.removeView((View) object);

    }
}
