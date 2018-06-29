package com.acme.a3csci3130;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
/**
 * DetailViewActivity
 * Activity for examining contact
 */
public class DetailViewActivity extends Activity {

    private EditText nameField, addressField;
    private Spinner primbusiness, province;
    Contact receivedPersonInfo;
    private MyApplicationData appState;

    /** onCreate: used when activity starts**/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
	appState = ((MyApplicationData) getApplicationContext());
	
        setContentView(R.layout.activity_detail_view);
        receivedPersonInfo = (Contact)getIntent().getSerializableExtra("Contact");

        /* Get info from fields*/
        nameField = (EditText) findViewById(R.id.name);
	addressField = (EditText) findViewById(R.id.address);
	primbusiness = (Spinner) findViewById(R.id.primbusiness);
	province= (Spinner) findViewById(R.id.province);

        if(receivedPersonInfo != null){
            nameField.setText(receivedPersonInfo.name);
	    addressField.setText(receivedPersonInfo.address);
        }


	/* Populate spinners*/
	ArrayAdapter<CharSequence> bus_adapter = ArrayAdapter.createFromResource(this,
        R.array.str_primbusiness, android.R.layout.simple_spinner_item);
	bus_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	primbusiness.setAdapter(bus_adapter);

	/* Set to correct position*/
	int position = bus_adapter.getPosition(receivedPersonInfo.business);
	primbusiness.getItemAtPosition(position);


	ArrayAdapter<CharSequence> prov_adapter = ArrayAdapter.createFromResource(this,
        R.array.str_province, android.R.layout.simple_spinner_item);
	prov_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	province.setAdapter(prov_adapter);
	
	/* Set to correct position*/
	position = prov_adapter.getPosition(receivedPersonInfo.province);
	province.getItemAtPosition(position); 
    }

    /**
     *updateContact:
     *
     * Callback to update button. Change the contact values from the current fields
     **/
    public void updateContact(View v){

        receivedPersonInfo.name = nameField.getText().toString();
        receivedPersonInfo.address = addressField.getText().toString();
        receivedPersonInfo.business = primbusiness.getItemAtPosition(primbusiness.getSelectedItemPosition()).toString();
        receivedPersonInfo.province = province.getItemAtPosition(province.getSelectedItemPosition()).toString();
        receivedPersonInfo.toMap(); // update hash
        appState.firebaseReference.child(receivedPersonInfo.uid).setValue(receivedPersonInfo);
    }


    /**
     *eraseContact:
     *
     * Callback to erase button. Erases the contact values from the database.
     **/

    public void eraseContact(View v)
    {
        appState.firebaseReference.child(receivedPersonInfo.uid).removeValue();
    }
}
