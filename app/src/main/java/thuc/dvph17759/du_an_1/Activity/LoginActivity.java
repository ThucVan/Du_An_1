package thuc.dvph17759.du_an_1.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import thuc.dvph17759.du_an_1.R;

public class LoginActivity extends AppCompatActivity {
    EditText edTaiKhoan,edPassWord;
    Button buttonDangNhap;
    Button btnHuy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        finID();

        buttonDangNhap.setOnClickListener(v -> {
            String name = edTaiKhoan.getText().toString();
            String pass = edPassWord.getText().toString();
            if(name.equals("") || pass.equals("")){
                Toast.makeText( LoginActivity.this, "Vui Lòng Điền Đủ Thông tin!!!", Toast.LENGTH_SHORT).show();
            }else{
                if(name.equals("admin")){
                    if(pass.equals("admin")){
                        Toast.makeText( LoginActivity.this, "Đăng nhập thành công ^.^", Toast.LENGTH_SHORT).show();
                        edPassWord.setText("");
                        edTaiKhoan.setText("");
                        startActivity(new Intent(LoginActivity.this, MainAppAdminActivity.class));
                    }else {
                        edPassWord.setText("");
                        edPassWord.setText("");
                        Toast.makeText( LoginActivity.this, "Tài khoản hoặc mật khẩu không chính xác!!!", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    edPassWord.setText("");
                    edTaiKhoan.setText("");
                    Toast.makeText( LoginActivity.this, "Tài khoản Không Tồn tại!!!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnHuy.setOnClickListener(v -> {
            //xóa text
            edPassWord.setText("");
            edTaiKhoan.setText("");
            startActivity(new Intent(LoginActivity.this, MainAppActivity.class));
        });

    }

    public void finID(){
        edTaiKhoan =  findViewById(R.id.ed_tai_khoan);
        edPassWord =  findViewById(R.id.ed_passWord);
        buttonDangNhap = (Button) findViewById(R.id.buttonDangNhap);
        btnHuy = (Button) findViewById(R.id.btn_huy);

    }
}