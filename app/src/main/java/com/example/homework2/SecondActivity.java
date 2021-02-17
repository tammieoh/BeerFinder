package com.example.homework2;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

import static android.widget.Toast.LENGTH_SHORT;
import static com.example.homework2.R.string.toast_alert;

public class SecondActivity extends AppCompatActivity {
    private EditText name_editText, startDate_editText, endDate_editText;
    private SwitchCompat highPoint_switch;
    private Button results_button;
    private boolean before_isClear, after_isClear;
    private String base_url;
    private static AsyncHttpClient client = new AsyncHttpClient();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        name_editText = findViewById(R.id.name_editText);
        startDate_editText = findViewById(R.id.startDate_editText);
        endDate_editText = findViewById(R.id.endDate_editText);
        highPoint_switch = findViewById(R.id.highPoint_switch);
        results_button = findViewById(R.id.results_button);


        results_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String beer_name = name_editText.getText().toString();
                String brew_before = startDate_editText.getText().toString();
                String brew_after = endDate_editText.getText().toString();

                base_url = "https://api.punkapi.com/v2/beers?";
                before_isClear = false;
                after_isClear = false;

                if(!beer_name.equals("")) {
                    base_url += "beer_name=" + beer_name + "&";
                }

                //brew_before variable is the first date --> ____ to _______
                if(!brew_before.equals("")) {
                    if(brew_before.length() == 7) {
                        brew_before = brew_before.substring(0,2) + "-" + brew_before.substring(3);
                        before_isClear = true;
                        base_url += "brewed_after=" + brew_before + "&";
                    }
                    else {
                        Toast toast = Toast.makeText(SecondActivity.this, R.string.toast_alert, LENGTH_SHORT);
                        toast.setDuration(LENGTH_SHORT);
                        toast.show();
                    }
                    Log.d("brew_first", brew_after);
                }
                else {
                    before_isClear = true;
                }

                //brew_after variable is the second date --> ____ to _______
                if(!brew_after.equals("")) {
                    if(brew_after.length() == 7) {
                        brew_after = brew_after.substring(0,2) + "-" + brew_after.substring(3);
                        after_isClear = true;
                        base_url += "brewed_before=" + brew_after + "&";
                    }
                    else {
                        Toast toast = Toast.makeText(SecondActivity.this, R.string.toast_alert, LENGTH_SHORT);
                        toast.show();
                    }
                    Log.d("brew_before", brew_before);
                }
                else {
                    after_isClear = true;
                }


                if(!brew_before.equals("") && !brew_after.equals("")) {
                    if(Integer.parseInt(brew_before.substring(5,7)) > Integer.parseInt(brew_after.substring(5,7))) {
                        Toast toast = Toast.makeText(SecondActivity.this, R.string.date_error, LENGTH_SHORT);
                        toast.show();
                        before_isClear = false;
                        after_isClear = false;
                    }
                    else if(Integer.parseInt(brew_before.substring(5,7)) == Integer.parseInt(brew_after.substring(5,7))) {
                        if(Integer.parseInt(brew_before.substring(0,2)) > Integer.parseInt(brew_after.substring(0,2))) {
                            Toast toast = Toast.makeText(SecondActivity.this, R.string.date_error, LENGTH_SHORT);
                            toast.show();
                            before_isClear = false;
                            after_isClear = false;
                        }
                        else if(Integer.parseInt(brew_before.substring(0,2)) ==  Integer.parseInt(brew_after.substring(0,2))) {
                            Toast toast = Toast.makeText(SecondActivity.this, R.string.equal_dates, LENGTH_SHORT);
                            toast.show();
                            before_isClear = false;
                            after_isClear = false;
                        }
                        else {
                            before_isClear = true;
                            after_isClear = true;
                        }
                    }
                    else {
                        before_isClear = true;
                        after_isClear = true;
                    }

                }

                int abv_point = 4;
                if(highPoint_switch.isChecked()) {
                    base_url += "abv_gt=" + abv_point;
                    Log.d("hello", "on");
                }
                else {
                    base_url += "abv_lt=" + abv_point;
                    Log.d("hello", "off");
                }
                Log.d("before going in",  before_isClear + ", " + after_isClear);
                if(before_isClear && after_isClear) {
                    Log.d("base_url", base_url);
                    Log.d("clear", "both dates are cleared");
                    launchNextActivity(v);
                }
            }
        });
    }

    public void launchNextActivity(View view) {
//        String url = ;
        Intent intent = new Intent(this, ThirdActivity.class);
//        String beer_name = name_editText.getText().toString();
//        String brew_before = startDate_editText.getText().toString();
//        String brew_after = endDate_editText.getText().toString();
//
//
//        if(!beer_name.equals("")) {
//            base_url += "beer_name=" + beer_name + "&";
//        }

//        String beer_name = name_editText.getText().toString();
//        String brew_before = startDate_editText.getText().toString();
//        String brew_after = endDate_editText.getText().toString();
//
//
//        if(!beer_name.equals("")) {
//            base_url += "beer_name=" + beer_name + "&";
//        }

//        while(!before_isClear) {
//            if(!brew_before.equals("")) {
//                if(brew_before.length() == 7) {
//                    brew_before = brew_before.substring(0,2) + "-" + brew_before.substring(3);
//                    before_isClear = true;
//                }
//                else {
//                    Toast toast = Toast.makeText(this, getString(toast_alert), LENGTH_SHORT);
//                    toast.setDuration(LENGTH_SHORT);
//                    toast.show();
//                }
//                Log.d("brew_first", brew_before);
//            }
//            else {
//                before_isClear = true;
//            }
//        }
//        while(!after_isClear) {
//            if(!brew_after.equals("")) {
//                if(brew_before.length() == 7) {
//                    brew_after = brew_after.substring(0,2) + "-" + brew_after.substring(3);
//                }
//                else {
//                    Toast toast = Toast.makeText(this, getString(toast_alert), LENGTH_SHORT);
//                    toast.show();
//                }
//                Log.d("brew_after", brew_after);
//
//            }
//            else {
//                after_isClear = true;
//            }
//        }

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

//        int abv_point = 4;
//        if(highPoint_switch.isChecked()) {
//           base_url += "abv_gt=" + abv_point;
//           Log.d("hello", "on");
//       }
//       else {
//           base_url += "abv_lt=" + abv_point;
//           Log.d("hello", "off");
//       }
//       highPoint_switch.onTouchEvent()
//        highPoint_switch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
//                int abv_point = 4;
//                if(b) {
//                    base_url += "abv_gt=" + abv_point;
//                    Log.d("switch status", "on");
//                }
//                else {
//                    base_url += "abv_lt=" + abv_point;
//                    Log.d("switch status", "off");
//                }
//            }
//        });
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
//                    ArrayList<String> abv_array = new ArrayList<>();
//                    ArrayList<String> brewDate_array = new ArrayList<>();
//                    ArrayList<ArrayList<String>> foodPairs_array = new ArrayList<>();
//                    ArrayList<String> brewTips_array = new ArrayList<>();

                    Intent intent = new Intent(SecondActivity.this, ThirdActivity.class);
                    for(int i = 0; i < json.length(); i++) {
                        names_array.add(json.getJSONObject(i).getString("name"));
                        desc_array.add(json.getJSONObject(i).getString("description"));
                        images_array.add(json.getJSONObject(i).getString("image_url"));
//                        abv_array.add(json.getJSONObject(i).getString("abv"));
//                        brewDate_array.add(json.getJSONObject(i).getString("first_brewed"));
//                        brewTips_array.add(json.getJSONObject(i).getString("brewers_tips"));
//
//                        for(int j = 0; j < )
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
