package com.example.mapwithmarker;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.GroundOverlayOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

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
import java.util.HashMap;
import java.util.concurrent.ExecutionException;

/**
 * An activity that displays a Google map with a marker (pin) to indicate a particular location.
 */
public class MapsMarkerActivity extends AppCompatActivity
        implements OnMapReadyCallback {

    private static String url = "https://api.apify.com/v2/key-value-stores/40xwYCZ57p5OkyBIJ/records/LATEST?disableRedirect=true";
    String abha;
    String makkah2;
    String najran;
    String jeddah;
    String hail;
    ArrayList<String> cities = new ArrayList<String>();
    GoogleMap googleMap;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new GetContacts().execute();

        setContentView(R.layout.activity_maps);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        // Retrieve the content view that renders the map.
        // Get the SupportMapFragment and request notification
        // when the map is ready to be used.




    }



    public void change_color(String x, LatLng position ){
        if(googleMap!=null){
            new GetContacts().execute();
        }
        if(Integer.parseInt(x)>3000){
            Marker melbourne = googleMap.addMarker(new MarkerOptions()
                    .position(position).title("عدد الاصابات" + x)
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));
        }else if(Integer.parseInt(x)<=3000 && Integer.parseInt(x)>1000){
            Marker melbourne = googleMap.addMarker(new MarkerOptions()
                    .position(position).title("عدد الاصابات" + x)
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW)));
        }else {
            Marker melbourne = googleMap.addMarker(new MarkerOptions()
                    .position(position).title("عدد الاصابات" + x)
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));
        }
        CircleOptions circleOptions = new CircleOptions()
                .center(new LatLng(position.latitude,position.longitude)).strokeColor(Color.RED).fillColor(Color.argb(50,255,0,0))
                .radius(30000); // In meters
        Circle circle = googleMap.addCircle(circleOptions);

    }

    /**
     * Manipulates the map when it's available.
     * The API invokes this callback when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user receives a prompt to install
     * Play services inside the SupportMapFragment. The API invokes this method after the user has
     * installed Google Play services and returned to the app.
     */

     @Override
    public void onMapReady(GoogleMap googleMap) {
         new GetContacts().execute();

         this.googleMap = googleMap;

         try {
            // Customise the styling of the base map using a JSON object defined
            // in a raw resource file.
            boolean success = googleMap.setMapStyle(
                    MapStyleOptions.loadRawResourceStyle(
                            this, R.raw.style_json));

            if (!success) {
            }
        } catch (Resources.NotFoundException e) {

        }
        // Add a marker in Sydney, Australia,
        // and move the map's camera to the same location.
        LatLng jeddah2 = new LatLng(21.543333, 39.172779);
        LatLng makkah = new LatLng(21.422510, 39.826168);
        LatLng abhaCord = new LatLng(18.216797, 42.503765);

        try {
        // change_color(najran, googleMap, new LatLng(17.4933, 44.1277));
        //change_color(makkah2,googleMap,makkah);
          //  change_color(hail,googleMap,new LatLng(27.523647 ,41.696632));
           // change_color(jeddah,googleMap, jeddah2);
            //change_color(abha,googleMap,abhaCord);




        }catch (Exception e){
            Log.d("TAG", "Exception", e);
        }

         googleMap.moveCamera(CameraUpdateFactory.newLatLng(jeddah2));


    

     }
    private class GetContacts extends AsyncTask<String, Void, String> {



        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress dialog


        }

        @Override
        protected String doInBackground(String... strings) {
            HttpHandler sh = new HttpHandler();

            // Making a request to url and getting response
            String jsonStr = sh.makeServiceCall(url);


            return jsonStr;
        }

        @Override
        protected void onPostExecute(String result) {
            Log.i("TAG","HHHHH");

            if (result != null) {
                try {
                    JSONObject jsonObj = new JSONObject(result);

                    // Getting JSON Array node

                    // looping through All Contacts

                    JSONObject c = jsonObj.getJSONObject("أبها");
                    JSONObject c2 = jsonObj.getJSONObject("مكة المكرمة");
                    najran = jsonObj.getJSONObject("نجران").getString("infected");
                    jeddah = jsonObj.getJSONObject("جدة").getString("infected");
                    hail = jsonObj.getJSONObject("تبوك").getString("infected");
                    Log.i("TAG",hail);

                    String infected = c.getString("infected");
                    String infected2 = c2.getString("infected");



                    abha = infected;
                    makkah2 = infected2;
                    cities.add(najran);
                    cities.add(jeddah);
                    cities.add(hail);
                    cities.add(abha);
                    cities.add(makkah2);
                    LatLng jeddah2 = new LatLng(21.543333, 39.172779);
                    LatLng makkah = new LatLng(21.422510, 39.826168);
                    LatLng abhaCord = new LatLng(18.216797, 42.503765);

                        Log.i("Number",najran);
                        Log.i("Number",jeddah);
                        Log.i("Number",hail);
                        Log.i("Number",abha);
                        Log.i("Number",makkah2);
                    change_color(najran, new LatLng(17.4933, 44.1277));
                        change_color(makkah2,makkah);
                          change_color(hail,new LatLng(27.523647 ,41.696632));
                        change_color(jeddah, jeddah2);
                        change_color(abha,abhaCord);
                } catch (final JSONException e) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(),
                                    "Json parsing error: " + e.getMessage(),
                                    Toast.LENGTH_LONG)
                                    .show();
                        }
                    });

                }
                catch (Exception e){
                    Log.i("Tag", "Message", e);
                }
            } else {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(),
                                "Couldn't get json from server. Check LogCat for possible errors!",
                                Toast.LENGTH_LONG)
                                .show();
                    }
                });

            }
            }

    }

}
