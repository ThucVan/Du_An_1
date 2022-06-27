package thuc.dvph17759.du_an_1.Activity;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

import thuc.dvph17759.du_an_1.R;

public class ThongTinKhachHangActivity extends AppCompatActivity {
    TextInputEditText ed_name, ed_sdt, ed_dc;
    Button btn_ok, btn_huy;
    String rgx = "(84|0[3|5|7|8|9])+([0-9]{8})\\b";
    TextView tv_chonvitri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_thong_tin_khachhang);

        ed_dc = findViewById(R.id.ed_dia_chi);
        ed_name = findViewById(R.id.ed_hoTen);
        ed_sdt = findViewById(R.id.ed_sdt);
        btn_ok = findViewById(R.id.btn_ok);
        btn_huy = findViewById(R.id.btn_huy);
        tv_chonvitri = findViewById(R.id.tv_chonvitri);

        getDiaChi();

        tv_chonvitri.setPaintFlags(tv_chonvitri.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

        btn_huy.setOnClickListener(v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Bạn chắc chắn muốn xóa ?")
                    .setNegativeButton("Ok", (dialog, which) -> {
                        ed_name.setText("");
                        ed_sdt.setText("");
                        ed_dc.setText("");
                        dialog.dismiss();
                    })
                    .setPositiveButton("No", (dialog, which) -> {
                        dialog.dismiss();
                    })
                    .setCancelable(true);
            AlertDialog dialog = builder.create();
            dialog.show();
        });

        btn_ok.setOnClickListener(v -> {
            if(ed_dc.getText().toString().equals("") || ed_name.getText().toString().equals("") || ed_sdt.getText().toString().equals("")){
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Bạn cần điền đầy đủ thông tin để tiếp tục !!!")
                        .setNegativeButton("OK", (dialog, which) -> {
                            dialog.dismiss();
                        })
                        .setCancelable(true);
                AlertDialog dialog = builder.create();
                dialog.show();
            }else if( !ed_sdt.getText().toString().matches(rgx)){
                ed_sdt.setError("Định dạng số điện sai !!!");
            }else{
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Bạn chắc chắn với thông tin này : ")
                        .setMessage("Họ Tên : " + ed_name.getText().toString() + "\n" +
                                "Số Điện Thoại Liên Hệ : " + ed_sdt.getText().toString() + "\n" +
                                "Địa Chỉ : " + ed_dc.getText().toString())
                        .setCancelable(true)
                        .setNegativeButton("Ok", (dialog, which) -> {
                            AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
                            builder1.setMessage("Cảm ơn bạn đã sử dụng dịch vụ của chúng tôi !!! \n" +
                                    "Bạn sẽ được đưa về trang chủ ngay bây giờ !!!")
                                    .setNegativeButton("Ok", (dialog1, which1) -> {
                                        for(int i =0; i< MainAppActivity.manggiohang.size(); i++){
                                            MainAppActivity.manggiohang.remove(i);
                                        }
                                        startActivity(new Intent(this, MainAppActivity.class));
                                        dialog1.dismiss();
                                    });
                            AlertDialog dialog1 = builder1.create();
                            dialog1.show();
                            dialog.dismiss();
                        })
                        .setPositiveButton("No", (dialog, which) -> {
                            dialog.dismiss();
                        });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
    }

    public void back_home(View view) {
        startActivity(new Intent(this, MainAppActivity.class));
    }

    public void open_ggmap(View view) {
        startActivity(new Intent(this, MainActivityGGmap.class));
    }

    void getDiaChi(){
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if(bundle != null) {
            String dia_chi = bundle.getString("adress");
            ed_dc.setText(dia_chi);
        }
    }
}