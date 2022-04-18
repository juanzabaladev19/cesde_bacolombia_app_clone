package com.example.bancolombiaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.example.bancolombiaapp.databinding.ActivityPinImgBinding;
import com.example.bancolombiaapp.models.PinModel;
import com.example.bancolombiaapp.requests.PinRequest;
import com.example.bancolombiaapp.services.PinService;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PinImgActivity extends AppCompatActivity {

    private ActivityPinImgBinding pinImgBinding ;
    private int pin;
    private String username;
    private String user;
    private String img;
    private String fullname;
    private Retrofit retrofit;
    ImageView imageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pinImgBinding = ActivityPinImgBinding.inflate(getLayoutInflater());
        View view = pinImgBinding.getRoot();
        setContentView(view);
        retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.0.3:8080/cesde_backend_bancolombia_app_clone/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        username = getIntent().getStringExtra("username");
        img = getIntent().getStringExtra("img");

        imageView = findViewById(R.id.img);
        String url = img;
        Glide.with(this)
                .load(url)
                .placeholder(R.drawable.imgbanco)
                .error(R.drawable.ic_launcher_background)
                .circleCrop()
                .into(imageView);

        pinImgBinding.btnContinuar.setEnabled(false);
        pinImgBinding.btnContinuar.setBackgroundColor(Color.GRAY);
        pinImgBinding.btnVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
            }
        });


        pinImgBinding.pinView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                pinImgBinding.btnContinuar.setEnabled(false);
                if(charSequence.toString().equals("")){
                    pinImgBinding.btnContinuar.setBackgroundColor(Color.GRAY);
                    pinImgBinding.btnContinuar.setEnabled(false);
                    return;
                }else{
                    pinImgBinding.btnContinuar.setBackgroundColor(Color.GRAY);
                    pinImgBinding.btnContinuar.setEnabled(false);
                    return;
                }
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                pinImgBinding.btnContinuar.setEnabled(false);
                if(editable.toString().equals("")){
                    pinImgBinding.btnContinuar.setBackgroundColor(Color.GRAY);
                    pinImgBinding.btnContinuar.setEnabled(false);
                    return;
                }else{
                    pinImgBinding.btnContinuar.setBackgroundColor(Color.YELLOW);
                    pinImgBinding.btnContinuar.setEnabled(true);
                    //Toast.makeText(PinImgActivity.this, "length:" + editable.length(), Toast.LENGTH_SHORT).show();
                    if(editable.length() ==4){
                        pinImgBinding.btnContinuar.setBackgroundColor(Color.YELLOW);
                        pinImgBinding.btnContinuar.setEnabled(true);
                        return;
                    }else{
                        pinImgBinding.btnContinuar.setBackgroundColor(Color.GRAY);
                        pinImgBinding.btnContinuar.setEnabled(false);
                        return;
                    }

                }
            }
        });
    }

    //Recibiendo las respuestas JSON
    public  void pinValidate(View view){
        pin = Integer.parseInt(pinImgBinding.pinView.getText().toString());
        PinService pinService = retrofit.create(PinService.class);
        PinRequest pinRequest = new PinRequest();
        pinRequest.setPin(pin);
        pinRequest.setUsername(username);
        Call<PinModel> pinUserService = pinService.pinUser(pinRequest);
        pinUserService.enqueue(new Callback<PinModel>() {
            @Override
            public void onResponse(Call<PinModel> call, Response<PinModel> response) {
                if(response.isSuccessful()){
                    PinModel pinResponse = response.body();
                    if(pinResponse.getError().length()>0 || pinResponse.getFullname().length()<1){
                        Toast.makeText(PinImgActivity.this, "Pin Error",
                                Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(PinImgActivity.this,
                                "Bienvenido: "+pinResponse.getFullname(), Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(PinImgActivity.this, "Error en la respuesta",
                            Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<PinModel> call, Throwable t) {
                Toast.makeText(PinImgActivity.this,
                        "!opps, tuvimos un error, revise la conexion a internet", Toast.LENGTH_SHORT).show();
            }
        });
        


    }

}