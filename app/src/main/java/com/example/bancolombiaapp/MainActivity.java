package com.example.bancolombiaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.bancolombiaapp.databinding.ActivityMainBinding;
import com.example.bancolombiaapp.models.LoginUsernameModel;
import com.example.bancolombiaapp.requests.LoginUsernameRequest;
import com.example.bancolombiaapp.services.LoginService;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private com.example.bancolombiaapp.databinding.ActivityMainBinding mainBinding;
    private String username;
    private  String usernameJson;
    private Retrofit retrofit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = mainBinding.getRoot();
        setContentView(view);
        retrofit = new Retrofit.Builder()
                .baseUrl("http://10.2.5.112/cesde_backend_bancolombia_app_clone/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        mainBinding.btnContinuarArriba.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),PinImgActivity.class);
                startActivity(intent);
            }
        });

        mainBinding.txtNoCliente.setPaintFlags(mainBinding.txtNoCliente.getPaintFlags()| Paint.UNDERLINE_TEXT_FLAG);
        mainBinding.btnContinuar.setEnabled(false);
        mainBinding.btnContinuar.setBackgroundColor(Color.GRAY);
        mainBinding.etIngresarUsuario.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                mainBinding.btnContinuar.setEnabled(false);
                if(charSequence.toString().equals("")){
                    mainBinding.btnContinuar.setBackgroundColor(Color.GRAY);
                    mainBinding.btnContinuar.setEnabled(false);

                    return;
                }else{
                    mainBinding.btnContinuar.setBackgroundColor(Color.YELLOW);
                    mainBinding.btnContinuar.setEnabled(true);
                    return;
                }
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                mainBinding.btnContinuar.setEnabled(false);
                if(editable.toString().equals("")){
                    mainBinding.btnContinuar.setBackgroundColor(Color.GRAY);
                    mainBinding.btnContinuar.setEnabled(false);
                    return;
                }else{
                    mainBinding.btnContinuar.setBackgroundColor(Color.YELLOW);
                    mainBinding.btnContinuar.setEnabled(true);
                    return;
                }
            }
        });
    }

    //recibiendo las respuestas en json
    public void userValidation(View view) {
        username = mainBinding.etIngresarUsuario.getText().toString();
        LoginService loginService = retrofit.create(LoginService.class);
        LoginUsernameRequest loginRequest = new LoginUsernameRequest();
        loginRequest.setUsername(username);
        Call<LoginUsernameModel> loginUsernameService = loginService.loginUsername(loginRequest);
        loginUsernameService.enqueue(new Callback<LoginUsernameModel>() {
            @Override
            public void onResponse(Call<LoginUsernameModel> call, retrofit2.Response<LoginUsernameModel> response) {
                if(response.isSuccessful()){
                    LoginUsernameModel loginResponse = response.body();
                    if(loginResponse.getError().length() > 0 || loginResponse.getImg().length() < 1){
                        Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Intent intent = new Intent(getApplicationContext(), PinImgActivity.class);
                        intent.putExtra("username", username);
                        intent.putExtra("img", loginResponse.getImg());
                        startActivity(intent);
                    }
                }
                else{
                    Toast.makeText(MainActivity.this, "Error en la respuesta", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<LoginUsernameModel> call, Throwable t) {
                Toast.makeText(MainActivity.this, "!opps, tuvimos un error, revisa tu conexión a internet", Toast.LENGTH_SHORT).show();
            }
        });
    }
}