package com.example.json_response_demo;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class API_GET_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_api_get);

        String url = "https://jsonplaceholder.typicode.com/todos/1/users";

        ArrayList<String> arrName = new ArrayList<>();
        ListView listView = findViewById(R.id.listView);

        AndroidNetworking.initialize(this);
        AndroidNetworking.get(url)
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONArray(new JSONArrayRequestListener() {

                    @Override
                    public void onResponse(JSONArray response){

                        Log.d("RES", response.toString());

                        //parsing
                        try {

                            for(int i=0; i<response.length(); i++){

                                JSONObject objResult = response.getJSONObject(i);
                                String Name = objResult.getString("name");

                                arrName.add(Name);

                                ArrayAdapter<String> arrAdapter = new ArrayAdapter<String>(API_GET_Activity.this, android.R.layout.simple_list_item_1, arrName);
                                listView.setAdapter(arrAdapter);

                            }
                        }
                        catch (JSONException e){
                            e.printStackTrace();
                        }

                    }

                    @Override
                    public void onError(ANError anError){

                        Log.e("ERROR", anError.toString());
                    }

                });
    }
}