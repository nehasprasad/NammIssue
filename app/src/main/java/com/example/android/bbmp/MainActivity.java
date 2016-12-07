package com.example.android.bbmp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

   public final static String EXTRA_MESSAGE="com.example.myfirstapp.MESSAGE";
   public static String item,item1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Spinner spinner1 = (Spinner) findViewById(R.id.spinner1);

        // Spinner click listener
        spinner1.setOnItemSelectedListener(this);

        // Spinner Drop down elements
        List<String> categories1 = new ArrayList<String>();
        categories1.add("Potholes");
        categories1.add("Garbage Crisis");
        categories1.add("Dying Lakes");
        categories1.add("Depleting Greenery");
        categories1.add("Street Lights");


        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories1);

        // Drop down layout style - list view with radio button
        dataAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner1.setAdapter(dataAdapter1);


    // Spinner element
    Spinner spinner = (Spinner) findViewById(R.id.spinner);

    // Spinner click listener
    spinner.setOnItemSelectedListener(this);
        spinner.setPrompt("Select one+");

    // Spinner Drop down elements
    List<String> categories = new ArrayList<String>();
    categories.add("Yelahanka");
    categories.add("Jayanagar");
    categories.add("Dasarahalli");
    categories.add("Rajarajeshwarinagar");
    categories.add("Hebbal");
    categories.add("K.R.Puram");
        categories.add("Yeshwanthpur");
        categories.add("C.V.Ramanagar");
        categories.add("Shivajinagar");
        categories.add("Rajajinagar");
        categories.add("Vijayanagar");
        categories.add("Chamrajpet");
        categories.add("Chikpet");
        categories.add("BTM Layout");
        categories.add("Basavangudi");


    // Creating adapter for spinner
    ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);

    // Drop down layout style - list view with radio button
    dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

    // attaching data adapter to spinner
    spinner.setAdapter(dataAdapter);


    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // On selecting a spinner item

        if(parent.getId()==R.id.spinner) {
            item = parent.getItemAtPosition(position).toString();
        }
        if(parent.getId()==R.id.spinner1)
            item1=parent.getItemAtPosition(position).toString();

    }

    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub

    }


    public void sendMessage1(View view){

        Intent intent=new Intent(this,MapsActivity.class);

        //intent.putExtra("pos2",pos1);

        startActivity(intent);

    }
    public void sendMessage(View view){

        Intent intent=new Intent(this,HelpActivity.class);

        //intent.putExtra("pos2",pos1);

        startActivity(intent);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
