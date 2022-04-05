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
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.example.bancolombiaapp.databinding.ActivityPinImgBinding;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class PinImgActivity extends AppCompatActivity {

    private ActivityPinImgBinding pinImgBinding ;
    private int pin;
    private String username;
    private String user;
    private String img;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pinImgBinding = ActivityPinImgBinding.inflate(getLayoutInflater());
        View view = pinImgBinding.getRoot();
        setContentView(view);
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
        //final TextView textView = (TextView) findViewById(R.id.text);
        // ...
        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="http://192.168.43.39:8080/backend/auth/auth_pin.php";
        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                url, new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                Log.d("VOLLEY",response);
                                // Display the first 500 characters of the response string.
                                //textView.setText("Response is: "+response.substring(0,500));
                                try {
                                    JSONObject jsonObject = new JSONObject(response);
                                    String fullname = jsonObject.optString("fullname","null");
                                    String error = jsonObject.optString("error","ok");
                                    if(error.equals("Usuario no existe")){
                                        Toast.makeText(getApplicationContext(),
                                                "Verify Your PIN: "+error,
                                                Toast.LENGTH_SHORT).show();
                                    }else if(error.equals("ok")){
                                        Toast.makeText(getApplicationContext(),
                                                "Success PIN: "+pin+","+fullname, Toast.LENGTH_SHORT).show();
                                    }
                                } catch (JSONException e) {
                                    //e.printStackTrace();
                                    Toast.makeText(getApplicationContext(),
                                            "Error JSON"+e.getMessage(),
                                            Toast.LENGTH_SHORT).show();
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
                    protected Map<String, String> getParams() throws
                            AuthFailureError {
                        Map<String,String> data = new HashMap<>();
                        data.put("username",username);
                        data.put("pin",Integer.toString(pin));
                        return data;
                    }
                };
        // Add the request to the RequestQueue.
        queue.add(stringRequest);

    }

}