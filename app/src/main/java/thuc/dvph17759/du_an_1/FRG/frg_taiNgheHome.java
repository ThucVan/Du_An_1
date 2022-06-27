package thuc.dvph17759.du_an_1.FRG;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
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
import thuc.dvph17759.du_an_1.Adapter.TaiNgheAdapter;
import thuc.dvph17759.du_an_1.Model.DienThoai;
import thuc.dvph17759.du_an_1.Model.Head_Phone;
import thuc.dvph17759.du_an_1.R;
import thuc.dvph17759.du_an_1.ulti.server;

public class frg_taiNgheHome extends Fragment {
    View v;
    RecyclerView recyclerViewmanhinhchinh;
    ArrayList<Head_Phone> mangtainghe;
    TaiNgheAdapter taiNgheAdapter;
    TextView tvXemThem;

    private void replaceFragment(Fragment fragment){
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout,fragment).commit();
    }

    void Anhxa(){
        recyclerViewmanhinhchinh =v.findViewById(R.id.rcv_tainghe_home);
        mangtainghe = new ArrayList<>();
        taiNgheAdapter = new TaiNgheAdapter(getContext(),mangtainghe);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false);
        recyclerViewmanhinhchinh.setLayoutManager(layoutManager);
        recyclerViewmanhinhchinh.setAdapter(taiNgheAdapter);
        tvXemThem = v.findViewById(R.id.tv_xemThem_tainghe);

        BottomNavigationView navigationView = getActivity().findViewById(R.id.navigation_bottom_view);

        tvXemThem.setOnClickListener(v1 -> {
            replaceFragment(new Frg_Tai_Nghe());
            navigationView.getMenu().findItem(R.id.tai_nghe).setChecked(true);
        });
    }
    void getTaiNghe(){
        RequestQueue queue = Volley.newRequestQueue(getContext());
        //2. url
        //jsonArrayRequest(duongDan,thanhCong,ThatBai)
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(server.Duongdantainghe, new Response.Listener<JSONArray>() {
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
                            mangtainghe.add(new Head_Phone(id,img,name,price,mota,sale));
                            taiNgheAdapter.notifyDataSetChanged();
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
        v = inflater.inflate(R.layout.frg_tainghehome, container, false);
        Anhxa();
        getTaiNghe();
        return v;
    }
}
