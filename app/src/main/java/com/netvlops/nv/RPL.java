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
import com.netvlops.nv.Adapter.AdapterListRPL;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static com.netvlops.nv.Server.url.url_rpl;

public class RPL extends AppCompatActivity {
    private RecyclerView lvSiswa;
    private RequestQueue requestQueue;
    private StringRequest stringRequest;

    SwipeRefreshLayout swLayout;

    ArrayList<HashMap<String, String>> list_data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rpl);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "WE ARE RPL'S STUDENTS!!!", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


        lvSiswa = (RecyclerView) findViewById(R.id.lvRPL);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        lvSiswa.setLayoutManager(llm);


        final SwipeRefreshLayout dorefresh = (SwipeRefreshLayout) findViewById(R.id.swipeRefreshRPL);
        dorefresh.setColorSchemeResources(android.R.color.holo_green_dark,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_blue_bright);
        swLayout = (SwipeRefreshLayout) findViewById(R.id.swipeRefreshRPL);
        swLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swLayout.setRefreshing(false);
                loadDataRPL();
            }
        });
        loadDataRPL();
    }

    private void loadDataRPL() {

        final ProgressDialog pd = new ProgressDialog(this);
        pd.setTitle("Mohon Tunggu Sebentar");
        pd.setMessage("Sedang Mengambil Data...");
        pd.show();
        requestQueue = Volley.newRequestQueue(RPL.this);

        list_data = new ArrayList<HashMap<String, String>>();
        stringRequest = new StringRequest(Request.Method.POST, url_rpl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                pd.dismiss();
                Log.d("response ", response);
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("data");
                    for (int a = 0; a < jsonArray.length(); a++) {
                        JSONObject json = jsonArray.getJSONObject(a);
                        HashMap<String, String> map = new HashMap<String, String>();
                        map.put("id_rpl", json.getString("id_rpl"));
                        map.put("nama_rpl", json.getString("nama_rpl"));
                        map.put("img_rpl", json.getString("img_rpl"));
                        map.put("desc_rpl", json.getString("desc_rpl"));
                        list_data.add(map);
                        AdapterListRPL adapter = new AdapterListRPL(RPL.this, list_data);
                        lvSiswa.setAdapter(adapter);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //Toast.makeText(RPL.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                Toast.makeText(RPL.this, "Tidak Ada Sambungan Internet", Toast.LENGTH_SHORT).show();
            }

        }
        ) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("id_rpl", "14");

                return params;
            }
        };

        requestQueue.add(stringRequest);
    }
}
