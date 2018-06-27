package com.acme.a3csci3130;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

/**
 * Class that defines the activity and relevant callbacks.
 *
 */
public class CreateContactAcitivity extends Activity {

    private Button submitButton;
    private EditText uidField, nameField, emailField, addressField;
    private MyApplicationData appState;
    private Spinner primbusiness, province;

    /**
     * onCreate
     * called by android for creating UI
     *
     **/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_contact_acitivity);
        //Get the app wide shared variables
        appState = ((MyApplicationData) getApplicationContext());

        submitButton = (Button) findViewById(R.id.submitButton);
        nameField = (EditText) findViewById(R.id.name);
	uidField = (EditText) findViewById(R.id.uid);
        emailField = (EditText) findViewById(R.id.email);
	addressField = (EditText) findViewById(R.id.address);
	primbusiness = (Spinner) findViewById(R.id.primbusiness);
	province= (Spinner) findViewById(R.id.province);


	/* Populate spinners*/
	ArrayAdapter<CharSequence> bus_adapter = ArrayAdapter.createFromResource(this,
        R.array.str_primbusiness, android.R.layout.simple_spinner_item);
	bus_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	primbusiness.setAdapter(bus_adapter);


	ArrayAdapter<CharSequence> prov_adapter = ArrayAdapter.createFromResource(this,
        R.array.str_province, android.R.layout.simple_spinner_item);
	prov_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	province.setAdapter(buss_adapter);
	
    }

    /**
     * submitInfoButton
     *----------
     * Callback for submitButton 
     **/
    public void submitInfoButton(View v) {
        //each entry needs a unique ID
        String personID = appState.firebaseReference.push().getKey();
	String name = nameField.getText().toString();
	String uid = uidField.getText().toString(); //don't need
        String email = emailField.getText().toString();
	String address = addressField.getText().toString();
	String business = primbusiness.getItemAtPosition(spinner.getSelectedItemPosition()).toString();
	String prov = prov.getItemAtPosition(spinner.getSelectedItemPosition()).toString();

	
        Contact person = new Contact(personID, name, email, address, business, prov);


        appState.firebaseReference.child(personID).setValue(person);

        finish();

    }
}
