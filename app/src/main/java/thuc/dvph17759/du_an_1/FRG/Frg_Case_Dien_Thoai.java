package thuc.dvph17759.du_an_1.FRG;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import thuc.dvph17759.du_an_1.Adapter.OpDienThoaiAdapter;
import thuc.dvph17759.du_an_1.Model.OpDienThoai;
import thuc.dvph17759.du_an_1.R;
import thuc.dvph17759.du_an_1.ulti.server;

public class Frg_Case_Dien_Thoai extends Fragment {
    View v;
    ArrayList<OpDienThoai> mangop;
    OpDienThoaiAdapter opDienThoaiAdapter;
    RecyclerView recyclerViewmanhinhchinh;
    void Anhxa(){
        recyclerViewmanhinhchinh =v.findViewById(R.id.rcv_case_dien_thoai);
        mangop = new ArrayList<>();
        opDienThoaiAdapter = new OpDienThoaiAdapter(getContext(),mangop);
        recyclerViewmanhinhchinh.setHasFixedSize(true);
        recyclerViewmanhinhchinh.setLayoutManager(new GridLayoutManager(getContext(),2));
        recyclerViewmanhinhchinh.setAdapter(opDienThoaiAdapter);
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
        v = inflater.inflate(R.layout.frg_case_dien_thoai, container, false);
        Anhxa();
        getOp();
        return v;
    }
}
