package thuc.dvph17759.du_an_1.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import thuc.dvph17759.du_an_1.FRG.MapFragment;
import thuc.dvph17759.du_an_1.R;

public class MainActivityGGmap extends AppCompatActivity {
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_ggmap);

        toolbar = findViewById(R.id.toolbar_diaChi);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");

        Fragment fragment = new MapFragment();

        getSupportFragmentManager().beginTransaction().replace(R.id.layout_gg, fragment).commit();
    }

    public void back_home(View view) {
        startActivity(new Intent(this , ThongTinKhachHangActivity.class));
    }
}