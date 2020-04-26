package com.example.mapwithmarker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class StatActivity extends AppCompatActivity {
    private static String url = "https://api.covid19api.com/summary";
    String NewConfirmed;
    String NewConfirmedC;
    String  TotalConfirmed;
    String TotalConfirmedC;
    String CountryC;

    TextView textView;
    String x;


    public void onClick5(View view){
        Intent intent = new Intent(StatActivity.this, HomeActivity.class);
        startActivity(intent);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new JsonTask().execute();

        setContentView(R.layout.activity_stat);

        textView = (TextView)findViewById(R.id.textView);




    }



    private class JsonTask  extends AsyncTask<String, String, String> {

        protected void onPreExecute() {
            super.onPreExecute();


        }
        public void repeat(JSONArray Countries, int i){
            try {
            CountryC = Countries.getJSONObject(i).get("Country").toString();
            x+="\n Country: "+CountryC;
            TotalConfirmedC = Countries.getJSONObject(i).get("TotalConfirmed").toString();

            x+="\n Total Confirmed: "+TotalConfirmedC;
            NewConfirmedC = Countries.getJSONObject(i).get("NewConfirmed").toString();

            x+="\n New Confirmed: "+NewConfirmedC;
            }
            catch (Exception ex){

            }
        }
        protected String doInBackground(String... params) {


            HttpURLConnection connection = null;
            BufferedReader reader = null;

            try {
                URL url = new URL("https://api.covid19api.com/summary");
                connection = (HttpURLConnection) url.openConnection();
                connection.connect();


                InputStream stream = connection.getInputStream();

                reader = new BufferedReader(new InputStreamReader(stream));

                StringBuffer buffer = new StringBuffer();
                String line = "";

                while ((line = reader.readLine()) != null) {
                    buffer.append(line+"\n");
                    Log.d("Response: ", "> " + line);   //here u ll get whole response...... :-)

                }

                return buffer.toString();


            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (connection != null) {
                    connection.disconnect();
                }
                try {
                    if (reader != null) {
                        reader.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            JSONObject jsonObj = null;
            try {
                jsonObj = new JSONObject(result);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            JSONObject global = null;
            try {
                global = jsonObj.getJSONObject("Global");
            } catch (JSONException e) {
                e.printStackTrace();
            }

            try {
                NewConfirmed = global.get("NewConfirmed").toString();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            try {
                TotalConfirmed = global.get("TotalConfirmed").toString();
            } catch (JSONException e) {
                e.printStackTrace();
            }

            JSONArray Countries = null;

            try {
                 x = "";

                Countries = jsonObj.getJSONArray("Countries");
                        CountryC = Countries.getJSONObject(195).get("Country").toString();
                        x+="Country: "+CountryC;
                        TotalConfirmedC = Countries.getJSONObject(195).get("TotalConfirmed").toString();

                        x+="\n Total Confirmed: "+TotalConfirmedC;
                        NewConfirmedC = Countries.getJSONObject(195).get("NewConfirmed").toString();

                        x+="\n New Confirmed: "+NewConfirmedC;
                        repeat(Countries, 196);
                        repeat(Countries, 197);
                        repeat(Countries, 198);


                Log.i("tag", "ggggggggggggg");

                Log.i("tag", x);
                textView.setText(x);


                }catch (Exception ex){

                }



        }
    }

}

