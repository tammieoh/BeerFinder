package com.example.homework2;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;

import androidx.appcompat.app.AppCompatActivity;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class SecondActivity extends AppCompatActivity {
    private EditText name_editText, startDate_editText, endDate_editText;
    private Switch highPoint_switch;
    private Button results_button;
    private String base_url = "https://api.punkapi.com/v2/beers?";
    private static AsyncHttpClient client = new AsyncHttpClient();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        name_editText = findViewById(R.id.name_editText);
        startDate_editText = findViewById(R.id.startDate_editText);
        endDate_editText = findViewById(R.id.endDate_editText);
        highPoint_switch = findViewById(R.id.highPoint_swtich);
        results_button = findViewById(R.id.results_button);


        results_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchNextActivity(v);
            }
        });
    }

    public void launchNextActivity(View view) {
//        String url = ;
        Intent intent = new Intent(this, ThirdActivity.class);
        String beer_name = name_editText.getText().toString();
        String brew_before = startDate_editText.getText().toString();
        String brew_after = endDate_editText.getText().toString();


        if(!beer_name.equals("")) {
            base_url += "beer_name=" + beer_name + "&";
        }
        if(!brew_before.equals("")) {
            brew_before = brew_before.substring(0,2) + "-" + brew_before.substring(2);
        }
        if(!brew_after.equals("")) {
            brew_after = brew_after.substring(0,2) + "-" + brew_after.substring(2);
        }

//        if(!brew_before.equals("")) {
//            base_url += "brew_before=" + brew_before + "&";
//        }
//        if(brew_after == null) {
//            base_url += "brew_after=" + brew_after + "&";
//        }
//        if(highPoint_switch.performClick()) {
//            base_url += "abv_gt=" + abv_point;
//        }
//        else {
//            base_url += "abv_lt=" + abv_point;
//        }

        highPoint_switch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                int abv_point = 4;
                if(b) {
                    base_url += "abv_gt=" + abv_point;
                }
                else {
                    base_url += "abv_lt=" + abv_point;
                }
            }
        });
        // set the header because of the api endpoint
        client.addHeader("Accept", "application/json");
        // send a get request to the api url
        client.get(base_url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                Log.d("api_url", base_url);
                try {
                    JSONArray json = new JSONArray(new String(responseBody));

                    ArrayList<String> names_array = new ArrayList<>();
                    ArrayList<String> desc_array = new ArrayList<>();
                    ArrayList<String> images_array = new ArrayList<>();

                    Intent intent = new Intent(SecondActivity.this, ThirdActivity.class);
                    for(int i = 0; i < json.length(); i++) {
                        names_array.add(json.getJSONObject(i).getString("name"));
                        desc_array.add(json.getJSONObject(i).getString("description"));
                        images_array.add(json.getJSONObject(i).getString("image_url"));
                    }
                    intent.putExtra("names", names_array);
                    intent.putExtra("descs", desc_array);
                    intent.putExtra("images", images_array);
                    startActivity(intent);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Log.d("get failed", base_url);
            }
        });
    }
}
