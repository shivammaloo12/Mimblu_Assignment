package com.shivam.mimblu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
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

public class MainActivity extends AppCompatActivity {
    private RecyclerView mList;

    private LinearLayoutManager linearLayoutManager;
    private DividerItemDecoration dividerItemDecoration;
    private List<symptoms> symptomList;
    private RecyclerView.Adapter adapter;


    private RequestQueue mRequestQueue;
    private StringRequest mStringRequest;
    private String url = "http://dev.mimblu.com/mimblu-yii2-1552/api/user/symptoms";
    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toast.makeText(getApplicationContext(),"Hello",Toast.LENGTH_SHORT).show();

        mList = findViewById(R.id.recycler);
        symptomList = new ArrayList<>();

        adapter = new symptomsAdapter(getApplicationContext(),symptomList);

        linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        dividerItemDecoration = new DividerItemDecoration(mList.getContext(), linearLayoutManager.getOrientation());

        mList.setHasFixedSize(true);
        mList.setLayoutManager(linearLayoutManager);
        mList.addItemDecoration(dividerItemDecoration);
        mList.setAdapter(adapter);

        CheckBox cb=(CheckBox)findViewById(R.id.checkbox);
        CardView b1=findViewById(R.id.btnCard);



        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                b1.getContext().startActivity(new Intent(b1.getContext(), premium.class));
            }
        });


        fetchData();
    }
    private void fetchData(){
        mRequestQueue = Volley.newRequestQueue(this);

        // String Request initialized
        mStringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Toast.makeText(getApplicationContext(), "Response :" + response.toString(), Toast.LENGTH_LONG).show();//display the response on screen

                JSONObject jsonObject = null;
                try {
                    jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("list");
                  //  JSONObject ob = jsonArray.getJSONObject(0);
                    List<String> fulldata=new ArrayList<String>();
                    Log.i("findthe",jsonArray.toString());
                    JSONObject ob = jsonArray.getJSONObject(0);
                    symptoms symptomTitle=new symptoms(ob.getString("title"));
                    for (int i = 0; i < jsonArray.length(); i++) {
                        symptoms s=new symptoms();

                       ob = jsonArray.getJSONObject(i);
                       s.setTitle(ob.getString("title"));
                       symptomList.add(s);

                    }
                    for(String d:fulldata){
                        symptoms sy=new symptoms(d);
                        symptomList.add(sy);
                        Log.i("mydata",d);
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