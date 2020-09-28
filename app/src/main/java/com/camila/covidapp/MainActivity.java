package com.camila.covidapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;

import com.camila.covidapp.adapter.AdapterCountry;
import com.camila.covidapp.model.Country;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;

public class MainActivity extends AppCompatActivity {

    private static final String URL="https://api.covid19api.com/summary";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        if (hayInet()){
            setContentView(R.layout.activity_main);
            processHttp();
        }else{
            setContentView(R.layout.inet_activity);
        }
    }

    public void processHttp(){
        AsyncHttpClient client = new AsyncHttpClient();
        client.get(URL, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String data = new String(responseBody);
                Log.d("INFO",data);
                processCountry(data);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });
    }

    public void processCountry(String data){
        try {
            JSONObject root = new JSONObject(data);
            JSONArray results = root.getJSONArray("Countries");

            List<Country> list = new ArrayList<>();

            for (int i = 0; i<results.length(); i++) {
                JSONObject country1 = results.getJSONObject(i);
                String name = country1.getString("Country");
                String code = country1.getString("CountryCode");
                String totalCases = country1.getString("TotalConfirmed");
                String totalDeaths = country1.getString("TotalDeaths");
                String totalRecovered= country1.getString("TotalRecovered");

                Country country = new Country(name, code, totalCases,totalDeaths,totalRecovered);
                list.add(country);
            }

            RecyclerView rc = findViewById(R.id.rc_country);
            AdapterCountry ad = new AdapterCountry(this,list,R.layout.item_country);
            LinearLayoutManager lm = new LinearLayoutManager(this);
            lm.setOrientation(RecyclerView.VERTICAL);

            rc.setLayoutManager(lm);
            rc.setAdapter(ad);

        }catch (JSONException e){
            e.printStackTrace();
        }
    }

    //validar conexion a inet
    public boolean hayInet(){
        boolean connected = false;
        ConnectivityManager cm;
        cm = (ConnectivityManager) this.getSystemService(CONNECTIVITY_SERVICE);

        Network [] networks = cm.getAllNetworks();

        for(Network network : networks){
            NetworkInfo info = cm.getNetworkInfo(network);
            if (info.isConnected()){
                connected = true;
            }
        }

        return connected;
    }

}