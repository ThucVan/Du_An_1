package thuc.dvph17759.du_an_1.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.text.DecimalFormat;

import thuc.dvph17759.du_an_1.Adapter.GiohangAdapter;
import thuc.dvph17759.du_an_1.R;

public class GiohangActivity extends AppCompatActivity {
    ListView listView;
    TextView txtThongbao;
    static TextView txtTongTien;
    Button btnThanhToan,btnTiepTucMuaHang;
    GiohangAdapter giohangAdapter;
    ////
    void Anhxa()
    {
        listView = findViewById(R.id.listviewgiohang);
        txtThongbao = findViewById(R.id.textviewthongbao);
        txtTongTien = findViewById(R.id.textviewtongtien);
        btnThanhToan = findViewById(R.id.buttonthanhtoangiohang);
        btnTiepTucMuaHang = findViewById(R.id.buttontieptucmuahang);
        giohangAdapter = new GiohangAdapter(GiohangActivity.this,MainAppActivity.manggiohang);
        listView.setAdapter(giohangAdapter);

        Log.d("TAG", "Anhxa: " + MainAppActivity.manggiohang.size());
    }

    void UpdateTongTien()
    {
        long tongtien=0;
        for(int i=0;i<MainAppActivity.manggiohang.size();i++)
        {
            tongtien += MainAppActivity.manggiohang.get(i).getGiasp();//cong don tien
        }
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        txtTongTien.setText(decimalFormat.format(tongtien)+" VND");
    }
    void CLickItemListview()
    {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if(MainAppActivity.manggiohang.size()<1)
                {
                    giohangAdapter.notifyDataSetChanged();
                    txtThongbao.setVisibility(View.VISIBLE);
                    listView.setVisibility(View.INVISIBLE);
                }
                else
                {
                    AlertDialog.Builder builder = new AlertDialog.Builder(GiohangActivity.this);
                    builder.setMessage("Bạn chắc chắn muốn bỏ sản phẩm này ?")
                            .setPositiveButton("Không", (dialog, which) -> {
                                dialog.dismiss();
                                check_gioHang();
                            })
                            .setNegativeButton("Đồng ý", (dialog, which) -> {
                                MainAppActivity.manggiohang.remove(i);//xoa hang trong gio hang
                                giohangAdapter.notifyDataSetChanged();
                                //cap nhat lai tong tien
                                UpdateTongTien();
                                Toast.makeText(GiohangActivity.this, "Xóa Thành Công !!!", Toast.LENGTH_SHORT).show();
                                check_gioHang();
                            })
                            .setCancelable(true);

                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();
                    check_gioHang();
                }

            }
        });
    }

    void check_gioHang(){
        if(MainAppActivity.manggiohang.size()<0)
        {
            txtThongbao.setVisibility(View.VISIBLE);
        }
        else
        {
            txtThongbao.setVisibility(View.GONE);
            giohangAdapter.notifyDataSetChanged();
            UpdateTongTien();
        }
    }

    void ClickButton()
    {
        btnTiepTucMuaHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),MainAppActivity.class);
                startActivity(intent);
            }
        });
        btnThanhToan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(MainAppActivity.manggiohang.size()>0)
                {
                    startActivity(new Intent(GiohangActivity.this, ThongTinKhachHangActivity.class));
                }
                else
                {
                    Toast.makeText(getApplicationContext(), "Khong co hang trong gio", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
    void  CheckData()
    {
        if(MainAppActivity.manggiohang.size()<=0)
        {
            giohangAdapter.notifyDataSetChanged();
            txtThongbao.setVisibility(View.VISIBLE);
            listView.setVisibility(View.INVISIBLE);
        }
        else
        {
            giohangAdapter.notifyDataSetChanged();
            txtThongbao.setVisibility(View.INVISIBLE);
            listView.setVisibility(View.VISIBLE);
        }
    }
    ////
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_giohang);
        Anhxa();
        CLickItemListview();//xu ly su kien
        ClickButton();//xu ly su kien khi click button
        UpdateTongTien();//cap nhat tong tien
        CheckData();//kiem tra gio hang
    }
}