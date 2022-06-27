package thuc.dvph17759.du_an_1.Activity;

import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.normal.TedPermission;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.List;

import gun0912.tedbottompicker.TedBottomPicker;
import thuc.dvph17759.du_an_1.R;

public class MainActivitySuSanPham extends AppCompatActivity {
    TextInputEditText ed_name, ed_gia, ed_mota;
    ImageView img_sp;
    Button btn_sua, btn_huy, btn_chonAnh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_sua_san_pham);

        ed_name = findViewById(R.id.ed_tenDienThoai);
        ed_gia = findViewById(R.id.ed_giaDienThoai);
        ed_mota = findViewById(R.id.ed_moTa);
        img_sp = findViewById(R.id.img_dienThoai);

        btn_chonAnh = findViewById(R.id.btn_hinhAnh);
        btn_huy = findViewById(R.id.btnHuy);

        btn_sua = findViewById(R.id.btn_sua);

       getDataIntent();

        btn_chonAnh.setOnClickListener(v -> {
            requestPermissonChangImage();
        });

        btn_huy.setOnClickListener(v -> {
            getDataIntent();
        });

        btn_sua.setOnClickListener(v -> {
            // gọi hàm sửa
            Toast.makeText(this, "Gọi Hàm Sửa !!!", Toast.LENGTH_SHORT).show();
        });
    }

    public void back_home(View view) {
        startActivity(new Intent(this, MainAppAdminActivity.class));
    }

    private void requestPermissonChangImage() {
        PermissionListener permissionlistener = new PermissionListener() {
            @Override
            public void onPermissionGranted() {
                openImagePicker();
            }

            @Override
            public void onPermissionDenied(List<String> deniedPermissions) {
                Toast.makeText(MainActivitySuSanPham.this, "Permission Denied\n" + deniedPermissions.toString(), Toast.LENGTH_SHORT).show();
            }
        };

        TedPermission.create()
                .setPermissionListener(permissionlistener)
                .setDeniedMessage("If you reject permission,you can not use this service\n\nPlease turn on permissions at [Setting] > [Permission]")
                .setPermissions(Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .check();
    }

    private void openImagePicker() {
        TedBottomPicker.OnImageSelectedListener onImageSelectedListener = new TedBottomPicker.OnImageSelectedListener() {
            @Override
            public void onImageSelected(Uri uri) {
                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                    img_sp.setImageBitmap(bitmap);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };

        TedBottomPicker tedBottomPicker = new TedBottomPicker.Builder(this)
                .setOnImageSelectedListener(onImageSelectedListener).create();
        tedBottomPicker.show(getSupportFragmentManager());
    }

    void getDataIntent(){
        Intent intent = this.getIntent();
        Bundle bundle = intent.getExtras();
        Log.d("TAG", "onCreate: " + bundle.getString("name_prodcut"));
        if(bundle != null){
            String name = bundle.getString("name_prodcut");
            String price = bundle.getString("price_prodcut");
            String img = bundle.getString("img_prodcut");
            String deltais = bundle.getString("deltais_prodcut");
            ed_name.setText(name);
            ed_gia.setText(price);
            ed_mota.setText(deltais);
            Picasso.get().load(img)
                    .placeholder(R.drawable.home)
                    .error(R.drawable.erro)
                    .into(img_sp);
        }
    }
}