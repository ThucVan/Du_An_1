package thuc.dvph17759.du_an_1.FRG;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import thuc.dvph17759.du_an_1.Adapter.DienThoaiAdapter;
import thuc.dvph17759.du_an_1.Model.DienThoai;
import thuc.dvph17759.du_an_1.R;
import thuc.dvph17759.du_an_1.ulti.server;

public class frg_dienThoaiHome extends Fragment {
    View v;
    ArrayList<DienThoai> mangdienthoai;
    DienThoaiAdapter dienThoaiAdapter;
    TextView tvXemThem;
    RecyclerView recyclerViewmanhinhchinh;

    private void replaceFragment(Fragment fragment){
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout,fragment).commit();
    }

    void Anhxa(){
        recyclerViewmanhinhchinh = v.findViewById(R.id.rcv_dienthoai_home);
        mangdienthoai = new ArrayList<>();
        dienThoaiAdapter = new DienThoaiAdapter(getContext(),mangdienthoai);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false);
        recyclerViewmanhinhchinh.setLayoutManager(layoutManager);
        recyclerViewmanhinhchinh.setAdapter(dienThoaiAdapter);

        BottomNavigationView navigationView = getActivity().findViewById(R.id.navigation_bottom_view);
        tvXemThem = v.findViewById(R.id.tv_xemThem_dienThoai);
        tvXemThem.setOnClickListener(v1 -> {
            replaceFragment(new Frg_Dien_Thoai());
            navigationView.getMenu().findItem(R.id.dien_thoai).setChecked(true);
        });

    }
    void getDienThoai(){
        RequestQueue queue = Volley.newRequestQueue(getContext());
        //2. url
        //jsonArrayRequest(duongDan,thanhCong,ThatBai)
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(server.Duongdandienthoai, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                if(response!=null)
                {
                    int id=0;
                    String img = "";
                    String name = "";
                    int price=0;
                    String mota="";
                    String sale="";


                    for(int i=0;i<response.length();i++)
                    {
                        try {

                            JSONObject jsonObject = response.getJSONObject(i);
                            id=jsonObject.getInt("id");
                            img = jsonObject.getString("img");
                            name = jsonObject.getString("name");
                            price = jsonObject.getInt("price");
                            mota = jsonObject.getString("details");
                            sale = jsonObject.getString("sale");

                            //add vao mang
                            mangdienthoai.add(new DienThoai(id,img,name,price,mota,sale));
                            dienThoaiAdapter.notifyDataSetChanged();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        //3. thuc thi
        queue.add(jsonArrayRequest);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.frg_dienthoaihome, container, false);
        Anhxa();
        getDienThoai();
        return v;
    }
}
