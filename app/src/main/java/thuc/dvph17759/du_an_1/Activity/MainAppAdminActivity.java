package thuc.dvph17759.du_an_1.Activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.navigation.NavigationView;

import thuc.dvph17759.du_an_1.FRG.frg_add_SP;
import thuc.dvph17759.du_an_1.FRG.frg_admin;
import thuc.dvph17759.du_an_1.FRG.frg_dienThoai_admin;
import thuc.dvph17759.du_an_1.FRG.frg_doi_matKhau_admin;
import thuc.dvph17759.du_an_1.FRG.frg_op_admin;
import thuc.dvph17759.du_an_1.FRG.frg_sac_admin;
import thuc.dvph17759.du_an_1.FRG.frg_taiNghe_admin;
import thuc.dvph17759.du_an_1.R;

public class MainAppAdminActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    Toolbar toolbar;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    FrameLayout frameLayout;
    ActionBarDrawerToggle toggle;
    Fragment selectFrg = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_app_admin);

        toolbar = findViewById(R.id.toolbar);
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigation_view);
        frameLayout = findViewById(R.id.frame_layout_admin);

        setSupportActionBar(toolbar);

        getSupportActionBar().setTitle("Admin");

        navigationView.setNavigationItemSelectedListener(this);

        replaceFragment(new frg_admin());

        toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.drawer_add_sanPham:
                selectFrg = new frg_add_SP();
                getSupportActionBar().setTitle("Thêm Sản Phẩm");

                replaceFRG();
                break;
            case R.id.drawer_dien_thoai:
                selectFrg = new frg_dienThoai_admin();
                getSupportActionBar().setTitle("Điện Thoại");

                replaceFRG();
                break;
            case R.id.drawer_tai_nghe:
                selectFrg = new frg_taiNghe_admin();
                getSupportActionBar().setTitle("Tai Nghe");

                replaceFRG();
                break;
            case R.id.drawer_sac_dien_thoai:
                selectFrg = new frg_sac_admin();
                getSupportActionBar().setTitle("Sạc Điện Thoại");

                replaceFRG();
                break;
            case R.id.drawer_op_dien_thoai:
                selectFrg = new frg_op_admin();
                getSupportActionBar().setTitle("Ốp Điện Thoại");

                replaceFRG();
                break;
            case R.id.drawer_doi_mat_khau:
                //doi mat khau admin
                selectFrg = new frg_doi_matKhau_admin();
                getSupportActionBar().setTitle("Đổi Mật Khẩu");

                replaceFRG();
                break;
            case R.id.drawer_out_admin:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage("Bạn có chắc chắn muốn thoát quyền admin ?")
                        .setPositiveButton("Có", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                startActivity(new Intent(getApplicationContext(), MainAppActivity.class));
                                dialog.dismiss();
                            }
                        })
                        .setNegativeButton("Không", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
                builder.show();
                break;
        }
        return true;
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    private void replaceFRG(){
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout_admin, selectFrg).commit();
        drawerLayout.closeDrawer(GravityCompat.START);
    }

    private void replaceFragment(Fragment fragment){
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout_admin,fragment).commit();
    }
}