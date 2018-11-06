package com.warassoc.app.activity;

//import android.app.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.Selection;
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

    //@Inject
    OpenWeatherServiceImpl openWeatherService;
    //@Inject
    //public MainActivity(OpenWeatherServiceImpl openWeatherService){
    //    this.openWeatherService=openWeatherService;
    //};

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

        ImageView listViewBanner = (ImageView) findViewById(R.id.imageView4);
        if (listViewBanner != null) {
            listViewBanner.setVisibility(ImageView.VISIBLE);
        }

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
        request.setMode("json");
        request.setUnits("metric");
        request.setCount(count);
        // @Inject quit working - see Dagger v2.0
        // https://dzone.com/articles/dagger-2-tutorial-dependency-injection-made-easy <=== ****
        // https://proandroiddev.com/how-to-dagger-2-with-android-part-1-18b5b941453f
        // https://www.techyourchance.com/dagger-2-scopes-demystified/
        // https://code.tutsplus.com/tutorials/dependency-injection-with-dagger-2-on-android--cms-23345
        if (openWeatherService == null) {
            openWeatherService = new OpenWeatherServiceImpl();
        }
        openWeatherService.setContext(b.getContext());
        openWeatherService.setRootView(b.getRootView());
        openWeatherService.setRequest(request);
        //OpenWeatherServiceImpl openWeatherService = new OpenWeatherServiceImpl(b.getContext(), b.getRootView(), request);
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
        ImageView bannerImageView;

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

            bannerImageView = (ImageView) rootView.findViewById(R.id.imageView4);
            if (bannerImageView != null) {
                bannerImageView.setVisibility(ImageView.INVISIBLE);
            }

            buttonBack.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // hide input fields
                    buttonBack = (Button) v;
                    buttonBack.setVisibility(Button.INVISIBLE);
                    if (bannerImageView != null) {
                        bannerImageView.setVisibility(ImageView.INVISIBLE);
                    }

                    Intent intent = new Intent(getActivity(), MainActivity.class);
                    startActivity(intent);
                }
            });
            return rootView;
        }
    }
}
