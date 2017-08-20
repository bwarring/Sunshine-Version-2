package com.warassoc.app.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.Selection;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.android.sunshine.activity.R;
import com.warassoc.app.service.OpenWeatherServiceImpl;
import com.warassoc.app.service.OpenWeatherServiceRequest;

import static com.example.android.sunshine.activity.R.id.container;
import static com.example.android.sunshine.activity.R.id.list_view_forecast;
import static com.example.android.sunshine.activity.R.id.numberOfDaysLabel;


public class MainActivity extends AppCompatActivity {

    PlaceholderFragment placeholderFragment;
    EditText zipCodeText;
    TextView zipCodeTextView;
    EditText numberOfDaysText;
    TextView numberOfDaysTextView;
    ImageView headerImageView;
    Button buttonSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // set cursor position on zipCode text field
        zipCodeText = (EditText) findViewById(R.id.zipCode);
        int position = zipCodeText.getText().length();
        Editable zipCodeTextValue = zipCodeText.getText();
        Selection.setSelection(zipCodeTextValue, position);

        // set cursor position on numberOfDays text field
        numberOfDaysText = (EditText) findViewById(R.id.numberOfDays);
        position = numberOfDaysText.getText().length();
        Editable numberOfDaysTextValue = numberOfDaysText.getText();
        Selection.setSelection(numberOfDaysTextValue, position);

        if (savedInstanceState == null) {
            placeholderFragment = new PlaceholderFragment();
            //Bundle bundle=new Bundle();
            //bundle.putSerializable("zipCodeText", (Serializable)zipCodeText.);
            //bundle.putSerializable("zipCodeTextView", (Serializable)zipCodeTextView);
            //bundle.putSerializable("numberOfDaysText", (Serializable)numberOfDaysText);
            //bundle.putSerializable("numberOfDaysTextView", (Serializable)numberOfDaysTextView);
            //bundle.putSerializable("headerImageView", (Serializable)headerImageView);
            //placeholderFragment.setArguments(new Bundle());
            getSupportFragmentManager().beginTransaction()
                    .add(container, placeholderFragment)
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

    public void buttonOnClick(View v) {
        Button b = (Button) v;
        //((Button)v).setText("clicked");
        //mainLayout=R.layout.fragment_main

        // hide popup keyboard
        InputMethodManager inputManager = (InputMethodManager)
                getSystemService(Context.INPUT_METHOD_SERVICE);

        inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                InputMethodManager.HIDE_NOT_ALWAYS);

        // get forecast listview and set visiability to VISIBLE
        ListView lv = (ListView) findViewById(list_view_forecast);
        lv.setVisibility(ListView.VISIBLE);

        // hide input fields
        headerImageView = (ImageView) findViewById(R.id.imageViewBanner);
        headerImageView.setVisibility(ImageView.INVISIBLE);
        b.setVisibility(Button.INVISIBLE);

        zipCodeText = (EditText) findViewById(R.id.zipCode);
        String zipCode = zipCodeText.getText().toString();
        zipCodeText.setVisibility(TextView.INVISIBLE);
        zipCodeTextView = (TextView) findViewById(R.id.zipCodeLabel);
        zipCodeTextView.setVisibility(TextView.INVISIBLE);

        numberOfDaysText = (EditText) findViewById(R.id.numberOfDays);
        String count = numberOfDaysText.getText().toString();
        numberOfDaysText.setVisibility(TextView.INVISIBLE);
        numberOfDaysTextView = (TextView) findViewById(numberOfDaysLabel);
        numberOfDaysTextView.setVisibility(TextView.INVISIBLE);

        buttonSubmit = (Button) findViewById(R.id.submitButton);
        buttonSubmit.setVisibility(Button.INVISIBLE);

        placeholderFragment.getButtonBack().setVisibility(Button.VISIBLE);

        // call OpenWeather service to retrieve weather list
        OpenWeatherServiceRequest request = new OpenWeatherServiceRequest();
        request.setBaseUrl("http://api.openweathermap.org/data/2.5");
        request.setType("forecast");
        request.setFrequency("daily");
        request.setLocationZip(zipCode);
        //request.setLocationZip("66061");
        request.setMode("json");
        request.setUnits("metric");
        request.setCount(count);
        //request.setCount("7");
        OpenWeatherServiceImpl openWeatherService = new OpenWeatherServiceImpl(b.getContext(), b.getRootView(), request);
        openWeatherService.execute();
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        static ImageView headerImageView;
        EditText zipCodeText;
        TextView zipCodeTextView;
        EditText numberOfDaysText;
        TextView numberOfDaysTextView;
        Button buttonBack;

        public PlaceholderFragment() {
        }

        public void setArguments(Bundle arguments) {
            headerImageView = (ImageView) arguments.get("headerImageView");
            zipCodeText = (EditText) arguments.get("zipCodeText");
            zipCodeTextView = (TextView) arguments.get("zipCodeTextView");
            numberOfDaysText = (EditText) arguments.get("numberOfDaysText");
            numberOfDaysTextView = (TextView) arguments.get("numberOfDaysTextView");
        }

        public Button getButtonBack() {
            return buttonBack;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            buttonBack = (Button) rootView.findViewById(R.id.buttonBack);
            buttonBack.setVisibility(Button.INVISIBLE);

            buttonBack.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // hide input fields
                    buttonBack = (Button) v;
                    buttonBack.setVisibility(Button.INVISIBLE);

                    Intent intent = new Intent(getActivity(), MainActivity.class);
                    startActivity(intent);
/*
                    PlaceholderFragment.headerImageView.setVisibility(ImageView.VISIBLE);

                    String zipCode=zipCodeText.getText().toString();
                    zipCodeText.setVisibility(TextView.VISIBLE);
                    zipCodeTextView.setVisibility(TextView.VISIBLE);

                    String count=numberOfDaysText.getText().toString();
                    numberOfDaysText.setVisibility(TextView.VISIBLE);
                    numberOfDaysTextView.setVisibility(TextView.VISIBLE);
                    */
                }
            });
            // my <code>
//            EditText et = (EditText)rootView.findViewById(R.id.dateNow);
//            if (et != null){
//                et.setText(DateUtility.dateNowStr());
//                et.setTypeface(Typeface.SERIF);
//            }
            // </code>

            //String[] days = new String[]{"Today", "Tomorrow", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday", "Monday", "Tuesday"};
            //String[] weather = new String[]{"Sunny", "Sunny", "Rainy", "Rainy", "Cloudy", "Cloudy", "Sunny", "Sunny", "Sunny"};

            //List<Forecast> forecastList = new ArrayList<>();
            //int cnt = 9;
            //Forecast forecast = null;
            //for (int i = 0; i < cnt; i++) {
            //    forecastList.add(new Forecast(days[i], weather[i], RandomUtility.random(75, 85), RandomUtility.random(60, 70)));
            //}

            // custom array adapter method
            //ForecastListArrayAdapter adapter = new ForecastListArrayAdapter(this.getContext(), Forecast.list());
            // attach the adapter to forecast ListView
            //ListView listView = (ListView) rootView.findViewById(R.id.list_view_forecast);
            //listView.setAdapter(adapter);

            // these two need to be declared outside the try/catch block
            // so they can be closed in the finally block
            //HttpURLConnection urlConnection = null;
            //BufferedReader reader = null;

            // Will contain the raw JSON response as a string.
            //String forecastJsonStr = null;
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
                /*
                OpenWeatherServiceRequest request = new OpenWeatherServiceRequest();
                request.setBaseUrl("http://api.openweathermap.org/data/2.5");
                request.setType("forecast");
                request.setFrequency("daily");
                request.setLocationZip("66061");
                request.setMode("json");
                request.setUnits("metric");
                request.setCount("7");
                OpenWeatherServiceImpl openWeatherService = new OpenWeatherServiceImpl(this.getContext(), rootView, request);
                openWeatherService.execute();
                */
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
