package com.example.good_luck.jsonexample3;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ModelObject modelObject;
    private Context myContext;
    private ListView listView;
    ArrayList<ModelObject> modelObjectArrayList = new ArrayList<ModelObject>();
    ContactBaseAdpater mContactBaseAdpter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myContext = this;
        listView = (ListView) findViewById(R.id.listview);
        contactslist();
    }

    private void contactslist() {
        String Url = "http://api.androidhive.info/contacts/";
        RequestQueue requestQueue = Volley.newRequestQueue(myContext);
        StringRequest request = new StringRequest(Request.Method.GET, Url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject object = new JSONObject(response);
                    JSONArray jsonArray = object.getJSONArray("contacts");

                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        modelObject = new ModelObject();
                        modelObject.setId(jsonObject.getString("id"));
                        modelObject.setName(jsonObject.getString("name"));
                        modelObject.setEmail(jsonObject.getString("email"));
                        modelObjectArrayList.add(modelObject);

                    }
                    mContactBaseAdpter = new ContactBaseAdpater(myContext, modelObjectArrayList);
                    listView.setAdapter(mContactBaseAdpter);
                    mContactBaseAdpter.notifyDataSetChanged();
                } catch (Exception e) {

                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        requestQueue.add(request);

    }
}