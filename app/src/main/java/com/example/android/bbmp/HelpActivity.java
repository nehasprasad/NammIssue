package com.example.android.bbmp;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;


public class HelpActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
        Toolbar toolbar1 = (Toolbar) findViewById(R.id.tool_bar);
        toolbar1.setTitle("Instructions");

        // Parent layout
     /*   LinearLayout parentLayout = (LinearLayout)findViewById(R.id.layout);

        // Layout inflater
        LayoutInflater layoutInflater = getLayoutInflater();
        View view;

        String[] p={
                "Select the constituency to which the area which is facing the issue belongs to from the dropdown list",
                "Select the type of the issue that needs to be addressed from the drop down list",
                "Press Next button",
                "Mark the probable location of the place facing the issue using the custom marker provided with the map",
                "Press Next button",
                "Enter a brief description about the issue and its location",
                "Attach a picture of the place with the issue from the gallery,if it already exists in your gallery using the attach button",
                "If you want to take the photo on spot using the camera use the camera button to attach that image.",
                "After all the fields have been entered and you have made sure of the data,click Submit button to send all the details to our database."
        };


        for (int i = 1; i < p.length; i++){
            // Add the text layout to the parent layout
            view = layoutInflater.inflate(R.layout.text, parentLayout, false);

            // In order to get the view we have to use the new view with text_layout in it
            TextView textView = (TextView)view.findViewById(R.id.text);
            textView.setText(p[i]);

            // Add the text view to the parent layout
            parentLayout.addView(textView);*/

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_help, menu);
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
}
