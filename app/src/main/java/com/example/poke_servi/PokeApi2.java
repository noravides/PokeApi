package com.example.poke_servi;

import android.content.Context;
import android.os.AsyncTask;
import android.util.JsonReader;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class PokeApi2 extends AsyncTask <String, String, Void> {
    private Context context;
    private String tag;
    private int cantRegistros;

    public PokeApi2(Context context, String tag) {
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

            InputStream is = response.body().byteStream();

            int code = response.code();
            if (code == 200) {
                cantRegistros = Integer.parseInt(response.header("Age"));
              //  publishProgress("1", "Procesando diagnósticos CIE-10");
                JsonReader jsonReader = new JsonReader(new InputStreamReader(is, "UTF-8"));
                getFromJson(jsonReader);
                response.body().close();
            } else {
               // Log.e(TAG, e.getMessage() == null ? e.toString() : e.getMessage());;
                Log.e(tag,"Error 404");
            }
            response.body().close();
        }catch (Exception e){
            e.printStackTrace();


        }

        return null;
    }

    private void getFromJson(JsonReader jsonReader) throws IOException {

        jsonReader.beginObject();
        while(jsonReader.hasNext()) { //object principal
            final String innerName = jsonReader.nextName();
            //final boolean isInnerNull = jsonReader.peek() == JsonToken.NULL;
            if (innerName.equals("abilities")) { //array
                jsonReader.beginArray();
                while (jsonReader.hasNext()) {
                    jsonReader.beginObject();
                         final String innerName2 = jsonReader.nextName();

                         if (innerName2.equals("ability")) {
                            jsonReader.beginObject();
                                while (jsonReader.hasNext()) {
                                    final String innerAtrib = jsonReader.nextName();
                                    if( innerAtrib.equals( "name" )) {
                                         String nombre =jsonReader.nextString();
                                         Log.d(tag,"Nombre = "+nombre);
                                    }else if(innerAtrib.equals("url")) {
                                        String url = jsonReader.nextString();
                                        Log.d(tag, "URL = " + url);
                                        jsonReader.endObject();
                                    }else if(innerAtrib.equals("is_hidden")) {
                                        Log.d(tag, "URL = " + "hiddeon");

                                    } else if(innerAtrib.equals("slot")) {
                                        Log.d(tag, "URL = " + "slot");
                                        jsonReader.endObject();
                                    }

                                }
                             //jsonReader.endObject();
                            // jsonReader.skipValue();
                         }


                }



            }

        }//object array
        //jsonReader.endArray();

    }



}
