package com.example.android.sunshine.app;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.warassoc.app.adapter.ForecastListArrayAdapter;
import com.warassoc.app.model.Forecast;
import com.warassoc.app.model.util.RandomUtility;
import com.warrassoc.app.service.OpenWeatherServiceImpl;
import com.warrassoc.app.service.OpenWeatherServiceRequest;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    TextView text;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //text = (TextView) findViewById(R.id.textView2);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {


        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);

            // my <code>
//            EditText et = (EditText)rootView.findViewById(R.id.dateNow);
//            if (et != null){
//                et.setText(DateUtility.dateNowStr());
//                et.setTypeface(Typeface.SERIF);
//            }
            // </code>

            String[] days = new String[]{"Today", "Tomorrow", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday", "Monday", "Tuesday"};
            String[] weather = new String[]{"Sunny", "Sunny", "Rainy", "Rainy", "Cloudy", "Cloudy", "Sunny", "Sunny", "Sunny"};

            List<Forecast> forecastList = new ArrayList<>();
            int cnt = 9;
            Forecast forecast = null;
            for (int i = 0; i < cnt; i++) {
                forecastList.add(new Forecast(days[i], weather[i], RandomUtility.random(75, 85), RandomUtility.random(60, 70)));
            }

            // custom array adapter method
            ForecastListArrayAdapter adapter = new ForecastListArrayAdapter(this.getContext(), Forecast.list());
            // attach the adapter to forecast ListView
            ListView listView = (ListView) rootView.findViewById(R.id.list_view_forecast);
            listView.setAdapter(adapter);

            // these two need to be declared outside the try/catch block
            // so they can be closed in the finally block
            //HttpURLConnection urlConnection = null;
            //BufferedReader reader = null;

            // Will contain the raw JSON response as a string.
            String forecastJsonStr = null;
            try {
                // Construct URL for the OpenWeatherMap query
                // Possible parameters are available at OWM's forecast API page at
                // http://openweathermap.com/API#forcast

                //APPID ="2be6bb1f8190f9079a8d5270be7417c0";
                //URL url = new URL("http://api.openweathermap.org/data/2.5/forecast/daily?q=66061&mode=json&units=metric&cnt=7&APPID=2be6bb1f8190f9079a8d5270be7417c0");

                // Create the request to OpenWeatherMap, and open the connection
                //urlConnection = (HttpURLConnection) url.openConnection();
                //urlConnection.setRequestMethod("GET");
                //urlConnection.connect();
                // call service
                OpenWeatherServiceRequest request = new OpenWeatherServiceRequest();
                request.setBaseUrl("http://api.openweathermap.org/data/2.5");
                request.setType("forecast");
                request.setFrequency("daily");
                request.setLocationZip("66061");
                request.setMode("json");
                request.setUnits("metric");
                request.setCount("7");
                OpenWeatherServiceImpl openWeatherService = new OpenWeatherServiceImpl(request);
                openWeatherService.execute();

            } catch (Exception e) {
                Log.e("PlaceholderFragment", "Error ", e);
                // If the code didn't successfully get the weather data, there's no point in attempting
                // to parse it.
                //forecastJsonStr = null;
            }
            return rootView;
/*
                //String json = openWeatherService.retrieve(request);
                // Read the input stream into a String
                InputStream inputStream = urlConnection.getInputStream();
                StringBuffer buffer = new StringBuffer();
                if (inputStream == null) {
                    // Nothing to do.
                    forecastJsonStr = null;
                }
                reader = new BufferedReader(new InputStreamReader(inputStream));

                String line;
                while ((line = reader.readLine()) != null) {
                    // Since it's JSON, adding a newline isn't necessary (it won't affect parsing)
                    // But it does make debugging a *lot* easier if you print out the completed
                    // buffer for debugging.
                    buffer.append(line + "\n");
                }

                if (buffer.length() == 0) {
                    // Stream was empty.  No point in parsing.
                    forecastJsonStr = null;
                }
                forecastJsonStr = buffer.toString();
            } catch (IOException e) {
                Log.e("PlaceholderFragment", "Error ", e);
                // If the code didn't successfully get the weather data, there's no point in attempting
                // to parse it.
                forecastJsonStr = null;
            } catch (Exception e) {
                Log.e("PlaceholderFragment", "Error ", e);
                // If the code didn't successfully get the weather data, there's no point in attempting
                // to parse it.
                forecastJsonStr = null;
            }
            finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (final IOException e) {
                    Log.e("PlaceholderFragment", "Error closing stream", e);
                }
            }

                return rootView;
            }
            */
        }
    }
}
