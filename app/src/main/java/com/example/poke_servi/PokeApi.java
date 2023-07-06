package com.example.poke_servi;

import android.content.Context;
import android.os.AsyncTask;
import android.util.JsonReader;
import android.util.JsonToken;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class PokeApi extends AsyncTask <String, String, Void> {
    private Context context;
    private String tag;
    private int cantRegistros;
    private String result;

    public PokeApi(Context context, String tag) {
        this.context = context;
        this.tag = tag;
    }
    //nora
    @Override
    protected Void doInBackground(String... params) {

        String result;
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(90, TimeUnit.SECONDS)
                .writeTimeout(90, TimeUnit.SECONDS)
                .readTimeout(90, TimeUnit.SECONDS)
                .build();
        String resURI = "https://pokeapi.co/api/v2/pokemon/ditto";

        Log.d(tag, resURI);
        Request request = new Request.Builder()
                .url(resURI)
                .get()
                .build();

        try {
            Response response = client.newCall(request).execute();
            result = response.body().string();


            int code = response.code();
            JSONObject jsonObject = new JSONObject(result);
            if (code == 200) {
                Log.d(tag, result);
                JSONArray jsonArray = jsonObject.getJSONArray("abilities");
                CargarArray(jsonArray);
                response.body().close();
            } else {

                Log.e(tag,"Error 404");
            }
            response.body().close();
        }catch (Exception e){
            e.printStackTrace();


        }

        return null;
    }

    private void CargarArray(JSONArray jsonArray){
        ArrayList<String> Lista = new ArrayList<>();
        for(int i=0;i<jsonArray.length();i++){
            try {
                JSONObject json = jsonArray.getJSONObject(i);
                JSONObject jsonAbility = json.getJSONObject("ability");
                //Aquí se obtiene el dato y es guardado en una lista
                String name = jsonAbility.getString("name");
                String url = jsonAbility.getString("url");


                String isHidden = json.getString("is_hidden");
                String slot = json.getString("slot");

                Log.e(tag,name);
                Log.e(tag,url);
                Log.e(tag,isHidden);
                Log.e(tag,slot);

                Lista.add(slot);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }



}
