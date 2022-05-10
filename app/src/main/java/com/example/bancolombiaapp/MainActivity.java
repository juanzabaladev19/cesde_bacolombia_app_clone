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

import com.example.bancolombiaapp.config.RetrofitConfig;
import com.example.bancolombiaapp.databinding.ActivityMainBinding;
import com.example.bancolombiaapp.models.LoginUsernameModel;
import com.example.bancolombiaapp.requests.LoginUsernameRequest;
import com.example.bancolombiaapp.services.LoginService;

import retrofit2.Call;
import retrofit2.Callback;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding mainBinding;
    private String username;
    private  String usernameJson;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = mainBinding.getRoot();
        setContentView(view);
        mainBinding.btnContinuarArriba.setOnClickListener(view1 -> {
            Intent intent = new Intent(getApplicationContext(),PinImgActivity.class);
            startActivity(intent);
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
        LoginService loginService = RetrofitConfig.build().create(LoginService.class);
        LoginUsernameRequest loginRequest = new LoginUsernameRequest();
        loginRequest.setUsername(username);
        Call<LoginUsernameModel> loginUsernameService = loginService.loginUsername(loginRequest);
        loginUsernameService.enqueue(new Callback<LoginUsernameModel>() {
            @Override
            public void onResponse(Call<LoginUsernameModel> call, retrofit2.Response<LoginUsernameModel> response) {
                if(response.isSuccessful()){
                    LoginUsernameModel loginResponse = response.body();
                    if(loginResponse.getError().length() > 0 || loginResponse.getImg().length() < 1){
                        Toast.makeText(MainActivity.this, "Username Error", Toast.LENGTH_SHORT).show();
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