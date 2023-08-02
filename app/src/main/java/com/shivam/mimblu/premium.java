package com.shivam.mimblu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class premium extends AppCompatActivity {
    private RecyclerView mList;

    private LinearLayoutManager linearLayoutManager;
    private DividerItemDecoration dividerItemDecoration;
    private List<plans> plansList;
    private RecyclerView.Adapter adapter;


    private RequestQueue mRequestQueue;
    private StringRequest mStringRequest;
    private String url = "http://dev.mimblu.com/mimblu-yii2-1552/api/plan/all";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_premium);
        plansList = new ArrayList<>();



        mList = findViewById(R.id.planRecycle);

        adapter = new plansAdapter(getApplicationContext(),plansList);

        linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        dividerItemDecoration = new DividerItemDecoration(mList.getContext(), linearLayoutManager.getOrientation());

        mList.setHasFixedSize(true);
        mList.setLayoutManager(linearLayoutManager);
        mList.addItemDecoration(dividerItemDecoration);
        mList.setAdapter(adapter);





        fetchData();
    }


    private void fetchData(){
        mRequestQueue = Volley.newRequestQueue(this);

        // String Request initialized
        mStringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                //display the response on screen

                JSONObject jsonObject = null;
                try {
                    jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("list");
                    //  JSONObject ob = jsonArray.getJSONObject(0);

                    Log.i("findthe",jsonArray.toString());
                    JSONObject ob = jsonArray.getJSONObject(0);
                    symptoms symptomTitle=new symptoms(ob.getString("title"));
                    for (int i = 0; i < jsonArray.length(); i++) {
                        plans s=new plans();

                        ob = jsonArray.getJSONObject(i);
                       // s.setTitle(ob.getString("title"));

                        s.setDuration(ob.getString("duration"));
                        s.setPrice(ob.getString("final_price"));
                        s.setDesc(ob.getString("description"));
                        s.setVideoDesc(ob.getString("video_description"));
                        s.setDiscountedPrice(ob.getString("discounted_price"));

                       // Toast.makeText(getApplicationContext(), "Response :" + ob.getString("title"), Toast.LENGTH_LONG).show();
                        plansList.add(s);

                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "error:"+error.toString() , Toast.LENGTH_LONG).show();
                System.out.println("error h"+error.toString());
            }
        });

        mRequestQueue.add(mStringRequest);

    }


}