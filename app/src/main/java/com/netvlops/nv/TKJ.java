package com.netvlops.nv;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.netvlops.nv.Adapter.AdapterListTKJ;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static com.netvlops.nv.Server.url.url_tkj;

public class TKJ extends AppCompatActivity {
    private RecyclerView lvSiswa;
    private RequestQueue requestQueue;
    private StringRequest stringRequest;

    SwipeRefreshLayout swLayout;
    ArrayList<HashMap<String, String>> list_data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tkj);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        lvSiswa = (RecyclerView) findViewById(R.id.lvTKJ);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        lvSiswa.setLayoutManager(llm);

        final SwipeRefreshLayout dorefresh = (SwipeRefreshLayout)findViewById(R.id.swipeRefreshTKJ);
        dorefresh.setColorSchemeResources(android.R.color.holo_green_dark,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_blue_bright);

        swLayout = (SwipeRefreshLayout) findViewById(R.id.swipeRefreshTKJ);
        swLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swLayout.setRefreshing(false);
                loadDataTKJ();
            }
        });
        loadDataTKJ();
    }

    private void loadDataTKJ() {
        final ProgressDialog pd = new ProgressDialog(this);
//        pd.setTitle("Sedang Mengambil Data");
//        pd.setMessage("Mohon Tunggu Sebentar...");
//        pd.show();
        requestQueue = Volley.newRequestQueue(TKJ.this);

        list_data = new ArrayList<HashMap<String, String>>();
        stringRequest = new StringRequest(Request.Method.POST, url_tkj, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
//                pd.dismiss();
                Log.d("response ", response);
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("data");
                    for (int a = 0; a < jsonArray.length(); a++) {
                        JSONObject json = jsonArray.getJSONObject(a);
                        HashMap<String, String> map = new HashMap<String, String>();
                        map.put("id_tkj", json.getString("id_tkj"));
                        map.put("nama_tkj", json.getString("nama_tkj"));
                        map.put("img_tkj", json.getString("img_tkj"));
                        map.put("desc_tkj", json.getString("desc_tkj"));
                        list_data.add(map);
                        AdapterListTKJ adapter = new AdapterListTKJ(TKJ.this, list_data);
                        lvSiswa.setAdapter(adapter);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
               // Toast.makeText(TKJ.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                Toast.makeText(TKJ.this, "Tidak Ada Sambungan Internet", Toast.LENGTH_SHORT).show();
            }
        })
        {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("id_tkj", "1");

                return params;
            }
        };;
        requestQueue.add(stringRequest);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "WE ARE TKJ'S STUDENTS!!!", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }
}
