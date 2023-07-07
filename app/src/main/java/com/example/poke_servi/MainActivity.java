package com.example.poke_servi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements PokeApi.OnSendPruebaListener {
    Button btn;
    EditText edt1;
    EditText edt2;
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn=findViewById(R.id.btn_api);
        edt1=findViewById(R.id.et_name);
        edt2=findViewById(R.id.et_url);
        listView=(ListView)findViewById(R.id.listaView);

        btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                new PokeApi(getApplicationContext(), MainActivity.this ,"API").execute();


            }
        });


    }


    @Override
    public void onRequestFinished(AtribuPoke atribupoke) {
        Log.d("API", String.valueOf(atribupoke));
        int cantidad = atribupoke.getAbilities().size();
        int cantidadForms = atribupoke.getForms().size();
        ArrayList<String> list = new ArrayList<String>();
        ArrayList<String> listForms = new ArrayList<String>();
        ArrayList<Abilities> abilities  = new ArrayList<>();


        String name ="";
        String url ="";

        for (int i=0 ;i < cantidad ; i++){
        name = atribupoke.getAbilities().get(i).getAbility().getName();
        url = url + " " + atribupoke.getAbilities().get(i).getAbility().getUrl();
        list.add(name);
        list.add(url);


        }
        for (int i=0 ;i < cantidadForms ; i++){
            name = atribupoke.getForms().get(i).getName();
            url = atribupoke.getForms().get(i).getUrl();
            listForms.add(name);
            listForms.add(url);


        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, listForms);

        edt1.setText(name);
        edt2.setText(url);
        listView.setAdapter(adapter);

    }
}