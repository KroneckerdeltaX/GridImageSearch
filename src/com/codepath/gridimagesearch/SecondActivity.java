package com.codepath.gridimagesearch;



import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;



public class SecondActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_second);
	//my objects
		Spinner spinner1= (Spinner) findViewById(R.id.spinner1);
		Spinner spinner2= (Spinner) findViewById(R.id.spinner2);
		Spinner spinner3= (Spinner) findViewById(R.id.spinner3);
		
		ArrayAdapter<CharSequence> spinnerAdapter1 = ArrayAdapter.createFromResource(
			this, R.array.spinner1_opt, android.R.layout.simple_spinner_dropdown_item);
		
		ArrayAdapter<CharSequence> spinnerAdapter2 = ArrayAdapter.createFromResource(
				this, R.array.spinner2_opt, android.R.layout.simple_spinner_dropdown_item);
		
		ArrayAdapter<CharSequence> spinnerAdapter3 = ArrayAdapter.createFromResource(
				this, R.array.spinner3_opt, android.R.layout.simple_spinner_dropdown_item);
		
	
		
		spinner1.setAdapter(spinnerAdapter1);
		spinner2.setAdapter(spinnerAdapter2);
		spinner3.setAdapter(spinnerAdapter3);
		
		//EditText et_Filter= (EditText) findViewById(R.id.et_Filter);
		
		
		
		
		
		
		
		
	}

	public void onSave(View v) {
		
		
	
		Intent i = new Intent(); //Initializing intent to pass data from second activity back to first screen
		
		//pull values out of current spinners . my objects
		Spinner spinner1= (Spinner) findViewById(R.id.spinner1);
		Spinner spinner2= (Spinner) findViewById(R.id.spinner2);
		Spinner spinner3= (Spinner) findViewById(R.id.spinner3);
		EditText et_Filter= (EditText)findViewById(R.id.et_Filter);
		
		//my field names
		i.putExtra("spinval1", spinner1.getSelectedItem().toString()); //associate user's options saved to intent
		i.putExtra("spinval2", spinner2.getSelectedItem().toString());
		i.putExtra("spinval3", spinner3.getSelectedItem().toString());
		i.putExtra("et_filter", et_Filter.getText().toString());
			setResult(RESULT_OK, i); //set result code and bundle data for response
			finish();  // closes the current activity and passes data to parent activity
	
		   
		        }
		    
	
	
	
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		
		getMenuInflater().inflate(R.menu.second, menu);
		
		return true;
	}
	

}
