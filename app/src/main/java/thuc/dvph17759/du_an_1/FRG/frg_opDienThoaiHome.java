package thuc.dvph17759.du_an_1.FRG;

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

import thuc.dvph17759.du_an_1.Adapter.OpDienThoaiAdapter;
import thuc.dvph17759.du_an_1.Model.OpDienThoai;
import thuc.dvph17759.du_an_1.R;
import thuc.dvph17759.du_an_1.ulti.server;

public class frg_opDienThoaiHome extends Fragment {
    View v;
    ArrayList<OpDienThoai> mangop;
    OpDienThoaiAdapter opDienThoaiAdapter;
    RecyclerView recyclerViewmanhinhchinh;
    TextView tvXemThem;

    private void replaceFragment(Fragment fragment){
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout,fragment).commit();
    }

    void Anhxa(){
        recyclerViewmanhinhchinh =v.findViewById(R.id.rcv_opdienthoai_home);
        mangop = new ArrayList<>();
        opDienThoaiAdapter = new OpDienThoaiAdapter(getContext(),mangop);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false);
        recyclerViewmanhinhchinh.setLayoutManager(layoutManager);
        recyclerViewmanhinhchinh.setAdapter(opDienThoaiAdapter);
        tvXemThem = v.findViewById(R.id.tv_xemThem_opdienThoai);

        BottomNavigationView navigationView = getActivity().findViewById(R.id.navigation_bottom_view);
        tvXemThem.setOnClickListener(v1 -> {
            replaceFragment(new Frg_Case_Dien_Thoai());
            navigationView.getMenu().findItem(R.id.case_dien_thoai).setChecked(true);
        });
    }
    void getOp(){
        RequestQueue queue = Volley.newRequestQueue(getContext());
        //2. url
        //jsonArrayRequest(duongDan,thanhCong,ThatBai)
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(server.Duongdanopdienthoai, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                if(response!=null)
                {
                    int id=0;
                    String img = "";
                    String name = "";
                    int price=0;
                    String mota="";



                    for(int i=0;i<response.length();i++)
                    {
                        try {

                            JSONObject jsonObject = response.getJSONObject(i);
                            id=jsonObject.getInt("id");
                            img = jsonObject.getString("img");
                            name = jsonObject.getString("name");
                            price = jsonObject.getInt("price");
                            mota = jsonObject.getString("mota");


                            //add vao mang
                            mangop.add(new OpDienThoai(id,img,name,price,mota));
                            opDienThoaiAdapter.notifyDataSetChanged();
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
        v = inflater.inflate(R.layout.frg_opdienthoaihome, container, false);
        Anhxa();
        getOp();
        return v;
    }
}
