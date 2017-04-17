package com.example.android.reportbee;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Anurag10 on 4/12/2017.
 */

public class MarkAttendence extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private List<ListStudent> listStudents;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.mark_attendance);
            recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            listStudents = new ArrayList<>();
            Log.v("Data",getJsonArray().toString());
            loadRecycleViewData(getJsonArray());

        }
    public static JSONArray getJsonArray()
    {
        ArrayList<String> list = new ArrayList<String>();
        list.add("Steve Jobs");list.add("Sathya Nadella");list.add("Mark Zuckkerberg");
        list.add("Elon Musk");list.add("Sunder Pichai");list.add("Anant Mani");
        list.add("Pete Cashmore");list.add("Sheryl Sandberg");list.add("Tim Cook");
        list.add("Andy Rubin");list.add("Jack Dorsey");list.add("Larry Page");
        list.add("Michael Dell");list.add("Alfred Lin");list.add("Marissa Mayer");list.add("Jeff Weiner");
        list.add("Paul Otellini");list.add("Bill Gates");list.add("Jack Ma");list.add("Jeff Bezos");
        JSONObject responseDetailsJson = new JSONObject();
        JSONArray jsonArray = new JSONArray();
        try {
            for (int i = 0,j = 1; i < list.size(); i++,j++) {
                responseDetailsJson = new JSONObject();
                responseDetailsJson.put("name", list.get(i));
                if(String.valueOf(j).length() == 1){
                responseDetailsJson.put("roll_no","0" + String.valueOf(j));}
                else{
                    responseDetailsJson.put("roll_no",String.valueOf(j));
                }
                responseDetailsJson.put("id",j);
                jsonArray.put(responseDetailsJson);
            }
        }catch(JSONException e ){
            e.printStackTrace();
        }
        return jsonArray;
    }

    private void loadRecycleViewData(JSONArray response){
                try{
                    //Parsing json array response
                    String jsonResponse = " ";
                    for(int i = 0;i < response.length();i++){
                        JSONObject person = (JSONObject) response.get(i);
                        String rollNumber = person.getString("roll_no");
                        String studentName = person.getString("name");
                        jsonResponse += "RollNumber:" + rollNumber + "\n\n";
                        jsonResponse += "StudentName:" + studentName + "\n\n";
                        Log.v("EditText",jsonResponse);
                        ListStudent listStudent = new ListStudent(
                                rollNumber,studentName
                        );
                        listStudents.add(listStudent);
                    }
                    adapter = new MyAdapter(listStudents, getApplicationContext());
                    recyclerView.setAdapter(adapter);
                }catch(JSONException e){
                    e.printStackTrace();
                }
    }
}
