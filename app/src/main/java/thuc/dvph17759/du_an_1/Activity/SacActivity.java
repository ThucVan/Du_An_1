package thuc.dvph17759.du_an_1.Activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
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

import thuc.dvph17759.du_an_1.Adapter.SacAdapter;
import thuc.dvph17759.du_an_1.Model.Phone_Charger;
import thuc.dvph17759.du_an_1.R;
import thuc.dvph17759.du_an_1.ulti.server;

public class SacActivity extends AppCompatActivity {

    ArrayList<Phone_Charger> mangsac;
    SacAdapter sacAdapter;
    RecyclerView recyclerViewmanhinhchinh;
    void Anhxa(){
        recyclerViewmanhinhchinh =findViewById(R.id.recycleview);
        mangsac = new ArrayList<>();
        sacAdapter = new SacAdapter(getApplicationContext(),mangsac);
        recyclerViewmanhinhchinh.setHasFixedSize(true);
        recyclerViewmanhinhchinh.setLayoutManager(new GridLayoutManager(getApplicationContext(),2));//2 cot
        recyclerViewmanhinhchinh.setAdapter(sacAdapter);

    }
    void getSac(){
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
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
                    int gia= Integer.parseInt("");
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
                            sacAdapter.notifyDataSetChanged();
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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sac);
        Anhxa();
        getSac();

    }
}