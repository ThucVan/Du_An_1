package thuc.dvph17759.du_an_1.FRG;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.SearchView;

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

import thuc.dvph17759.du_an_1.Adapter.adapter_admin_sac;
import thuc.dvph17759.du_an_1.Model.Phone_Charger;
import thuc.dvph17759.du_an_1.R;
import thuc.dvph17759.du_an_1.ulti.server;

public class frg_sac_admin extends Fragment {
    View v;
    RecyclerView recyclerView;
    ArrayList<Phone_Charger> mangsac;
    adapter_admin_sac sac_adapter;
    SearchView searchView;
    ImageButton img;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.frg_admin_dien_thoai, container, false);
        getSac();
        finID();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                sac_adapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                sac_adapter.getFilter().filter(newText);
                return false;
            }
        });

        return v;
    }

    void finID(){
        img = v.findViewById(R.id.dcgiohang);
        recyclerView = v.findViewById(R.id.rcsearch);
        searchView = v.findViewById(R.id.search);
        mangsac = new ArrayList<>();
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getActivity(), 2);
        recyclerView.setLayoutManager(layoutManager);
        sac_adapter = new adapter_admin_sac(getActivity(), mangsac);
        recyclerView.setAdapter(sac_adapter);
    }

    void getSac(){
        RequestQueue queue = Volley.newRequestQueue(getContext());
        //2. url
        //jsonArrayRequest(duongDan,thanhCong,ThatBai)
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(server.Duongdansac, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                if(response!=null)
                {
                    int id=0;
                    String tenhang = "";
                    String tensp="";
                    int gia=0;
                    String img="";
                    String chitiet="";

                    for(int i=0;i<response.length();i++)
                    {
                        try {
                            //boc tach json
                            JSONObject jsonObject = response.getJSONObject(i);
                            id=jsonObject.getInt("id");
                            img = jsonObject.getString("img");
                            tenhang = jsonObject.getString("tenhang");
                            tensp = jsonObject.getString("tensp");
                            gia = jsonObject.getInt("gia");
                            chitiet = jsonObject.getString("chitiet");

                            //add vao mang
                            mangsac.add(new Phone_Charger(id,img,tenhang,tensp,gia,chitiet));
                            sac_adapter.notifyDataSetChanged();
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
}
