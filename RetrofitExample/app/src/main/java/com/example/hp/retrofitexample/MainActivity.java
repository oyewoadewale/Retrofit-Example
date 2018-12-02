package com.example.hp.retrofitexample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private TextView textViewResults;

    private EarthQuakeApi earthQuakeApi;

    private static final String BASE_URL = "https://earthquake.usgs.gov/";
    private static final String FORMAT = "geojson";
    private static final String ORDER_BY_TIME = "time";
    private static final String WEB_FAILURE_MESSAGE = "Failed to communicate with " +
            "USGS Earthquakes.";
    private String minMagnitude = String.valueOf(4.5);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewResults = findViewById(R.id.text_view_result);

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://earthquake.usgs.gov/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();


        getEarthquake();
        // getPosts();
    }

    private void getEarthquake() {
        Call<List<EarthQuakeAPIRecord>> call = earthQuakeApi.getEarthquakes();

        call.enqueue(new Callback<List<EarthQuakeAPIRecord>>() {
            @Override
            public void onResponse(Call<List<EarthQuakeAPIRecord>> call, Response<List<EarthQuakeAPIRecord>> response) {

                if (!response.isSuccessful()) {
                    textViewResults.setText("Code: " + response.code());
                    return;
                }

                List<EarthQuakeAPIRecord> earthQuakeAPIRecord = response.body();

                for (EarthQuakeAPIRecord earthQuake: earthQuakeAPIRecord) {

                    String contents ="";
                    contents += "Magnitude: " + earthQuake.getMagnitude() + "\n";
                    contents += "Place: " + earthQuake.getPlace() + "\n";
                    contents += "Time: " + earthQuake.getTime() + "\n";
                    contents += "Time: " + earthQuake.getTsunami() + "\n";
                    contents += "Time: " + earthQuake.getLongitude() + "\n";
                    contents += "Time: " + earthQuake.getLatitude() + "\n\n";

                    textViewResults.append(contents);

                }

            }

            @Override
            public void onFailure(Call<List<EarthQuakeAPIRecord>> call, Throwable t) {
                textViewResults.setText(t.getMessage());

            }
        });


    }
}
