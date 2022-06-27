package thuc.dvph17759.du_an_1.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;

import thuc.dvph17759.du_an_1.Model.Giohang;
import thuc.dvph17759.du_an_1.Model.Phone_Charger;
import thuc.dvph17759.du_an_1.R;

public class ChiTietSac extends AppCompatActivity {
    ImageView imgchitiet;
    TextView txtten,txtgia,txtmota;
    Spinner spinner;
    Button btnmua;
    String Tensanpham;
    Integer Giasanpham;
    String Hinhanhsanpham;
    String Motasanpham;
    int id;
    void Anhxa()
    {
        imgchitiet = findViewById(R.id.imageviewchitietsanpham);
        txtten = findViewById(R.id.textviewtenchitietsanpham);
        txtgia = findViewById(R.id.textviewgiachitietsanpham);
        txtmota = findViewById(R.id.textviewmotachitietsanpham);
        spinner = findViewById(R.id.spinner);
        btnmua = findViewById(R.id.buttondatmua);
    }

    void EventSpinner()
    {
        Integer[] soluong = new Integer[]{1,2,3,4,5,6,7,8,9,10};
        ArrayAdapter<Integer> arrayAdapter =
                new ArrayAdapter<>(this,android.R.layout.simple_dropdown_item_1line,soluong);
        spinner.setAdapter(arrayAdapter);
    }
    void GetInfomation()
    {
        //lay thong tin truyen sang
        Phone_Charger phone_charger = (Phone_Charger) getIntent().getSerializableExtra("thongtinsanpham");
        Tensanpham = phone_charger.getTensp();
        Giasanpham = phone_charger.getGia();
        Hinhanhsanpham = phone_charger.getImg();
        Motasanpham = phone_charger.getChitiet();
        //dua thong tin vao chi tiet
        txtten.setText(Tensanpham);
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        txtgia.setText("Gia: " + Giasanpham + " VND");
        txtmota.setText(Motasanpham);
        //anhr
        Picasso.get().load(Hinhanhsanpham)
                .placeholder(R.drawable.home)
                .error((R.drawable.erro))
                .into(imgchitiet);
    }
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_san_pham);
        Anhxa();//findbyviewId
        GetInfomation();//lay thong tin chuyen sang
        EventSpinner();//chon so luong
        EventButton();//them san pham

    }
    void  EventButton()//xu ly su kien click button
    {
        btnmua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(MainAppActivity.manggiohang.size()>0)//gio hang khong rong
                {
                    int sl = Integer.parseInt(spinner.getSelectedItem().toString());//lay so luong trong spinner
                    boolean tontaimahang = false;
                    for(int i=0;i<MainAppActivity.manggiohang.size();i++)//dem xem trong gio hang co gi
                    {
                        if(MainAppActivity.manggiohang.get(i).getIdsp()==id)//neu co hang ta muon mua them
                        {
                            //so luong = so luong cu + moi
                            MainAppActivity.manggiohang.get(i).setSoluongsp(MainAppActivity.manggiohang.get(i).getSoluongsp()+sl);
                            if(MainAppActivity.manggiohang.get(i).getSoluongsp()>=10)//neu so luong sp >10, giu nguyen 10
                            {
                                MainAppActivity.manggiohang.get(i).setSoluongsp(10);
                            }
                            //tinh tien = Don gia * so luong
                            MainAppActivity.manggiohang.get(i).setGiasp(Giasanpham*MainAppActivity.manggiohang.get(i).getSoluongsp());
                            tontaimahang  =true;
                        }
                    }
                    if(tontaimahang==false)//truong hop co hang, nhung ma hang muon mua them khong ton tai
                    {
                        int sl1 = Integer.parseInt(spinner.getSelectedItem().toString());//lay so luong trong spinner
                        //tinh tien
                        long Tien2 = sl1*Giasanpham;
                        //them vao mang gio hang
                        MainAppActivity.manggiohang.add(new Giohang(id,Tensanpham,Tien2,Hinhanhsanpham,sl1));
                    }
                }
                else //gio hang rong
                {
                    int sl2 = Integer.parseInt(spinner.getSelectedItem().toString());//lay so luong trong spinner
                    //tinh tien
                    long Tien2 = sl2*Giasanpham;
                    //them vao mang gio hang
                    MainAppActivity.manggiohang.add(new Giohang(id,Tensanpham,Tien2,Hinhanhsanpham,sl2));
                }
                Intent intent = new Intent(getApplicationContext(),GiohangActivity.class);
                startActivity(intent);
            }
        });
    }
}