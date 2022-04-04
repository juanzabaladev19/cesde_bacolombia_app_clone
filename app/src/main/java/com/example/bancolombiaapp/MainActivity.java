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

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {


    private com.example.bancolombiaapp.databinding.ActivityMainBinding mainBinding;
    private String username;
    private  String usernameJson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = mainBinding.getRoot();
        setContentView(view);

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
    public void userValidation(View view) throws JSONException {
        username = mainBinding.etIngresarUsuario.getText().toString();
        //JSONObject json = new JSONObject();
        //json.put("username",username);
        //usernameJson = (json.toString());

        //final TextView textView = (TextView) findViewById(R.id.text);
        // ...
        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="http://192.168.43.39:8080/backend/auth/auth_username.php";
        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                Log.d("VOLLEY",response);
                                // Display the first 500 characters of the response string.
                                //textView.setText("Response is: "+ response.substring(0,500));
                                try {
                                    JSONObject jsonObject = new JSONObject(response);
                                    String img = jsonObject.optString("img","null");
                                    String error = jsonObject.optString("error","ok");
                                    if(error.equals("Usuario no existe")){
                                        Toast.makeText(getApplicationContext(),
                                                "Failure Login: "+error, Toast.LENGTH_SHORT).show();
                                    }else if(error.equals("ok")){
                                        Toast.makeText(getApplicationContext(),
                                                "Success Login: "+img, Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(getApplicationContext(),PinImgActivity.class);
                                        intent.putExtra("img",img);
                                        intent.putExtra("username",username);
                                        startActivity(intent);
                                    }
                                } catch (JSONException e) {
                                    //e.printStackTrace();
                                    Toast.makeText(getApplicationContext(),
                                            "Error JSON"+e.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //textView.setText("That didn't work!");
                        Toast.makeText(getApplicationContext(),
                                "error"+error.getMessage().toString(
                                ), Toast.LENGTH_SHORT).show();
                    }
                }){
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String,String> data = new HashMap<>();
                        data.put("username",username);

                        return data;
                    }
                };
        // Add the request to the RequestQueue.
        queue.add(stringRequest);
    }

}