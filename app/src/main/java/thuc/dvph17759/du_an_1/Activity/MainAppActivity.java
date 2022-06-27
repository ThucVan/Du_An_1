package thuc.dvph17759.du_an_1.Activity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import me.relex.circleindicator.CircleIndicator;
import thuc.dvph17759.du_an_1.Adapter.advertisement_adapter;
import thuc.dvph17759.du_an_1.FRG.Frg_Case_Dien_Thoai;
import thuc.dvph17759.du_an_1.FRG.Frg_Dien_Thoai;
import thuc.dvph17759.du_an_1.FRG.Frg_Sac_Dien_Thoai;
import thuc.dvph17759.du_an_1.FRG.Frg_Tai_Nghe;
import thuc.dvph17759.du_an_1.FRG.Frg_Trang_chu;
import thuc.dvph17759.du_an_1.Model.Giohang;
import thuc.dvph17759.du_an_1.R;

public class MainAppActivity extends AppCompatActivity {
    public static ArrayList<Giohang> manggiohang;
    ViewPager viewPager;
    CircleIndicator circleIndicator;
    advertisement_adapter adapter_ad;
    List<Advertisiment> list_ad;
    Timer timer;
    FrameLayout frameLayout;
    BottomNavigationView navigationView;
    SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_app);

        fin_ID();
        autoSlideAd();

        replaceFragment(new Frg_Trang_chu());
        navigationView.getMenu().findItem(R.id.trang_chu).setChecked(true);
        navigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment selectFrg = null;
                switch (item.getItemId()){
                    case R.id.trang_chu:
                        selectFrg = new Frg_Trang_chu();
                        break;
                    case R.id.dien_thoai:
                        selectFrg = new Frg_Dien_Thoai();
                        break;
                    case R.id.tai_nghe:
                        selectFrg = new Frg_Tai_Nghe();
                        break;
                    case R.id.sac_dien_thoai:
                        selectFrg = new Frg_Sac_Dien_Thoai();
                        break;
                    case R.id.case_dien_thoai:
                        selectFrg = new Frg_Case_Dien_Thoai();
                        break;
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, selectFrg).commit();
                return true;
            }
        });
        list_ad = getListAd();
        adapter_ad = new advertisement_adapter(this, list_ad);
        viewPager.setAdapter(adapter_ad);

        circleIndicator.setViewPager(viewPager);
        adapter_ad.registerDataSetObserver(circleIndicator.getDataSetObserver());
    }


    private void fin_ID(){
        viewPager = findViewById(R.id.view_pager);
        circleIndicator = findViewById(R.id.circle_indicator);
        frameLayout = findViewById(R.id.frame_layout);
        navigationView = findViewById(R.id.navigation_bottom_view);
        searchView = findViewById(R.id.search_bar);

        searchView.setOnClickListener(v -> {
            startActivity(new Intent(MainAppActivity.this, MainSearchActivity.class));
        });
        if(manggiohang!=null)
        {

        }
        else
        {
            manggiohang = new ArrayList<>();
        }
    }

    //data test
    private List<Advertisiment> getListAd(){
        List<Advertisiment> ad = new ArrayList<>();
        ad.add(new Advertisiment(R.drawable.img_ip13));
        ad.add(new Advertisiment(R.drawable.img_ipord));
        ad.add(new Advertisiment(R.drawable.img_xiaomi_t11_pro));
        ad.add(new Advertisiment(R.drawable.img_z_flod3));

        return ad;
    }


    private void autoSlideAd(){
            // init Timer
            if (timer == null) {
                timer = new Timer();
            }
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    new Handler(Looper.getMainLooper()).post(() -> {
                        int c_ad = viewPager.getCurrentItem();
                        int tt_ad = list_ad.size() - 1;
                        if (c_ad < tt_ad) {
                            c_ad++;
                            viewPager.setCurrentItem(c_ad);
                        } else {
                            viewPager.setCurrentItem(0);
                        }
                    });
                }
            }, 500, 3000);
        }

    public void btn_show_market(View view) {
        // viết hàm show giỏ hàng
        startActivity(new Intent(this, GiohangActivity.class));

    }

    private void replaceFragment(Fragment fragment){
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout,fragment).commit();
    }

    public void btn_show_dangNhap(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainAppActivity.this);
        View view1 = LayoutInflater.from(MainAppActivity.this).inflate(R.layout.dialog_login,null);
        builder.setView(view1);
        AlertDialog alertDialog =builder.create();
        alertDialog.show();

        Button btn_dangNhap = view1.findViewById(R.id.btn_dang_nhap);
        Button btn_huyDangNhap = view1.findViewById(R.id.btn_huy_dang_nhap);

        btn_dangNhap.setOnClickListener(v -> {
            startActivity(new Intent(MainAppActivity.this, LoginActivity.class));
        });

        btn_huyDangNhap.setOnClickListener(v -> {
            alertDialog.dismiss();
        });
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.menu_search, menu);
//        SearchManager searchManager = (SearchManager) getSystemService(MainAppActivity.this.SEARCH_SERVICE);
//        searchView = (SearchView) menu.findItem(R.id.menu_search).getActionView();
//        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
//        searchView.setMaxWidth(Integer.MAX_VALUE);
//
//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//                dienThoaiAdapter.getFilter().filter(query);
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String newText) {
//                dienThoaiAdapter.getFilter().filter(newText);
//                return false;
//            }
//        });
//
//        return super.onCreateOptionsMenu(menu);
//    }

    public class Advertisiment {
        private  int img;

        public Advertisiment(int img) {
            this.img = img;
        }

        public int getImg() {
            return img;
        }

        public void setImg(int img) {
            this.img = img;
        }
    }

}