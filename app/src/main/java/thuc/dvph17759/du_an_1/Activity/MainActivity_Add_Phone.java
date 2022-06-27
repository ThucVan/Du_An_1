package thuc.dvph17759.du_an_1.Activity;

import android.Manifest;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.normal.TedPermission;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import gun0912.tedbottompicker.TedBottomPicker;
import thuc.dvph17759.du_an_1.Adapter.DienThoaiAdapter;
import thuc.dvph17759.du_an_1.Adapter.OpDienThoaiAdapter;
import thuc.dvph17759.du_an_1.Adapter.SacAdapter;
import thuc.dvph17759.du_an_1.Adapter.TaiNgheAdapter;
import thuc.dvph17759.du_an_1.Model.DienThoai;
import thuc.dvph17759.du_an_1.Model.Head_Phone;
import thuc.dvph17759.du_an_1.Model.OpDienThoai;
import thuc.dvph17759.du_an_1.Model.Phone_Charger;
import thuc.dvph17759.du_an_1.R;

public class MainActivity_Add_Phone extends AppCompatActivity {
    Spinner spinner;
    TextInputEditText ed_tenSP, ed_gia, ed_mota;
    ImageView img;
    Button btn_camera, btn_add, btn_huy;
    ArrayList<DienThoai> mangdienthoai;
    ArrayList<OpDienThoai> mangop;
    ArrayList<Phone_Charger> mangsac;
    ArrayList<Head_Phone> mangtainghe;
    DienThoaiAdapter dienThoaiAdapter;
    TaiNgheAdapter taiNgheAdapter;
    SacAdapter sacAdapter;
    OpDienThoaiAdapter opDienThoaiAdapter;

    String name_sp, mota_sp, img_sp;
    String sale = "";
    int gia_sp;
    int id = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_add_phone);

        ed_tenSP = findViewById(R.id.ed_tenDienThoai);
        ed_gia = findViewById(R.id.ed_giaDienThoai);
        ed_mota = findViewById(R.id.ed_moTa);
        btn_camera = findViewById(R.id.btn_hinhAnh);
        btn_add = findViewById(R.id.btnThem);
        btn_huy = findViewById(R.id.btnHuy);
        spinner = findViewById(R.id.spinner_sanPham);
        img = findViewById(R.id.img_dienThoai);

        mangtainghe = new ArrayList<>();
        mangsac = new ArrayList<>();
        mangop = new ArrayList<>();
        mangdienthoai = new ArrayList<>();

        dienThoaiAdapter = new DienThoaiAdapter(this, mangdienthoai);

        taiNgheAdapter = new TaiNgheAdapter(this, mangtainghe);

        sacAdapter = new SacAdapter(this, mangsac);

        opDienThoaiAdapter = new OpDienThoaiAdapter(this, mangop);

        ArrayList<String> name = new ArrayList<>();
        name.add("Điện Thoại");
        name.add("Tai Nghe");
        name.add("Sạc Điện Thoại");
        name.add("Ốp Điện Thoại");

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, name);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        Log.d("TAG", "đthoai trước khi add : " + mangdienthoai.size());
        Log.d("TAG", "tai nghe trước khi add : " + mangtainghe.size());
        Log.d("TAG", "sạc trước khi add : " + mangsac.size());
        Log.d("TAG", "ốp trước khi add : " + mangop.size());

        btn_add.setOnClickListener(v -> {
            if (getThongTinSP() == false) {
                Toast.makeText(getApplicationContext(), "Bạn Cần Điền Đầy Đủ Thông Tin Để Thêm Sản Phẩm !!!", Toast.LENGTH_SHORT).show();
            } else if (spinner.getSelectedItem().toString() == "Điện Thoại") {
                mangdienthoai.add(new DienThoai(id, img_sp, name_sp, gia_sp, mota_sp, sale));
                Log.d("TAG", "onCreate: đthoai sau khi add : " + mangdienthoai.size());
                dienThoaiAdapter.notifyDataSetChanged();
                Toast.makeText(getApplicationContext(), "Thêm Thành Công !!!", Toast.LENGTH_SHORT).show();
            } else if (spinner.getSelectedItem().toString() == "Tai Nghe") {
                mangtainghe.add(new Head_Phone(id, img_sp, name_sp, gia_sp, mota_sp, sale));
                taiNgheAdapter.notifyDataSetChanged();
                Log.d("TAG", "tai nghe sau khi add : " + mangtainghe.size());
                Toast.makeText(getApplicationContext(), "Thêm Thành Công !!!", Toast.LENGTH_SHORT).show();
            } else if (spinner.getSelectedItem().toString() == "Sạc Điện Thoại") {
                mangsac.add(new Phone_Charger(id, img_sp, "", name_sp, gia_sp, sale));
                sacAdapter.notifyDataSetChanged();
                Log.d("TAG", "sạc sau khi add : " + mangsac.size());
                Toast.makeText(getApplicationContext(), "Thêm Thành Công !!!", Toast.LENGTH_SHORT).show();
            } else if (spinner.getSelectedItem().toString() == "Ốp Điện Thoại") {
                mangop.add(new OpDienThoai(id, img_sp, name_sp, gia_sp, mota_sp));
                opDienThoaiAdapter.notifyDataSetChanged();
                Log.d("TAG", "ốp sau khi add : " + mangop.size());
                Toast.makeText(getApplicationContext(), "Thêm Thành Công !!!", Toast.LENGTH_SHORT).show();
            }

        });

        btn_huy.setOnClickListener(v -> {
            ed_mota.setText("");
            ed_gia.setText("");
            ed_tenSP.setText("");
        });

        btn_camera.setOnClickListener(v -> {
            requestPermissonChangImage();
        });
    }

    private void requestPermissonChangImage() {
        PermissionListener permissionlistener = new PermissionListener() {
            @Override
            public void onPermissionGranted() {
                openImagePicker();
            }

            @Override
            public void onPermissionDenied(List<String> deniedPermissions) {
                Toast.makeText(MainActivity_Add_Phone.this, "Permission Denied\n" + deniedPermissions.toString(), Toast.LENGTH_SHORT).show();
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
                    img.setImageBitmap(bitmap);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };

        TedBottomPicker tedBottomPicker = new TedBottomPicker.Builder(this)
                .setOnImageSelectedListener(onImageSelectedListener).create();
        tedBottomPicker.show(getSupportFragmentManager());
    }

    private boolean getThongTinSP() {
        if (ed_tenSP.getText().toString().equals("") || ed_gia.getText().toString().equals("")
                || ed_mota.getText().toString().equals("")) {
            return false;
        } else {
            name_sp = ed_tenSP.getText().toString();
            gia_sp = Integer.parseInt(ed_gia.getText().toString());
            mota_sp = ed_mota.getText().toString();
            img_sp = "https://img.global.news.samsung.com/vn/wp-content/uploads/2019/03/Galaxy-A50-Mat-truoc-3.jpg";
            return true;
        }
    }
}