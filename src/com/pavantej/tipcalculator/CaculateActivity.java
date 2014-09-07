package com.pavantej.tipcalculator;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class CaculateActivity extends Activity {

	EditText etAmount;
	TextView tvResult;
	Button bt10Percent;
	Button bt15Percent;
	Button bt20Percent;
	double amount;
	double tipPercent;
	String result;
	
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_caculate);
        setActivity();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.caculate, menu);
        return true;
    }
    
    void setActivity(){
    	etAmount = (EditText) findViewById(R.id.etAmount);
    	tvResult = (TextView) findViewById(R.id.tvResult);
    	bt10Percent = (Button) findViewById(R.id.but10Percent);
    	bt15Percent = (Button) findViewById(R.id.but15Percent);
    	bt20Percent = (Button) findViewById(R.id.but20Percent);
    	//tvResult.setVisibility(View.INVISIBLE);
    }
    
    void calculateTip(){
    	double tip = amount*tipPercent;
    	result = "Tip is ";
    	result += tip;
    	displayResult();
    }
    
    void displayResult(){
    	tvResult.setText(result);
    	tvResult.setVisibility(View.VISIBLE);
    }
    
    boolean isAmountNull(){
    	String amt = etAmount.getText().toString();
    	if(amt.isEmpty()){
    		Toast.makeText(getBaseContext(), "No Amount Provided", Toast.LENGTH_SHORT).show();
    		tvResult.setVisibility(View.INVISIBLE);
     		return true;
    	}
    	else{
    		amount = Double.parseDouble(amt);
    		return false;
    	}
    }
    
    public void onClick10Percent(View v){
    	if(!isAmountNull()){
    		tipPercent = 0.1;
    		calculateTip();
    	}
    }
    
    public void onClick15Percent(View v){
    	if(!isAmountNull()){
    		tipPercent = 0.15;
    		calculateTip();
    	}
    }
    
    public void onClick20Percent(View v){
    	if(!isAmountNull()){
    		tipPercent = 0.2;
    		calculateTip();
    	}
    }
}
