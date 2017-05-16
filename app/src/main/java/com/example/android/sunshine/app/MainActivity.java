package com.example.android.sunshine.app;

import android.graphics.Typeface;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import com.warassoc.app.adapter.ForecastListArrayAdapter;
import com.warassoc.app.model.Forecast;
import com.warassoc.app.model.util.DateUtility;
import com.warassoc.app.model.util.RandomUtility;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
            //List<String> itemList = new ArrayList<String>();
            List<Forecast> forecastList = new ArrayList<>();
            int cnt = 9;
            //StringBuffer sb = new StringBuffer();
            Forecast forecast = null;
            for (int i=0; i<cnt; i++) {
//                sb.append(days[i]);
//                sb.append(" - ");
//                sb.append(weather[i]);
//                sb.append(" - ");
//                sb.append(RandomUtility.random(75, 85));
//                sb.append("/");
//                sb.append(RandomUtility.random(60, 70));
//                itemList.add(sb.toString());
//                sb.setLength(0);

                forecastList.add(new Forecast(days[i], weather[i], RandomUtility.random(75, 85), RandomUtility.random(60, 70)));
            }

            //ListView lv = (ListView)rootView.findViewById(R.id.list_view_forecast);
            // Convert ArrayList to array
//            String[] lv_arr = new String[itemList.size()];
//            Forecast[] lv_forecast_arr = new Forecast[itemList.size()];
//            int i = 0;
//            for (String item : itemList){
//                lv_arr[i] = item;
//                i++;
//            }
            /*
            lv.setAdapter(new ArrayAdapter<String>(rootView.getContext(), android.R.layout.simple_list_item_1, lv_arr));

            ArrayAdapter<String> itemsAdapter =
                    new ArrayAdapter<String>(rootView.getContext(), android.R.layout.simple_list_item_1, lv_arr);
            ListView listView = (ListView) rootView.findViewById(R.id.list_view_forecast);
            listView.setAdapter(itemsAdapter);
            */

            // custom array adapter method
            // Construct the data source
            ArrayList<Forecast> arrayOfUsers = new ArrayList<>();
            // Create the adapter to convert the array to views
            //ForecastListArrayAdapter adapter = new ForecastListArrayAdapter(this.getContext(), forecastList);
            ForecastListArrayAdapter adapter = new ForecastListArrayAdapter(this.getContext(), Forecast.list());
            // Attach the adapter to a ListView
            ListView listView = (ListView) rootView.findViewById(R.id.list_view_forecast);
            listView.setAdapter(adapter);

            return rootView;
        }
    }
}
