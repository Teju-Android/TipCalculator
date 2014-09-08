package com.pavantej.tipcalculator;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class CaculateActivity extends Activity {

	EditText etAmount;
	EditText etCustom;
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
        
        etAmount.addTextChangedListener(new TextWatcher(){
            public void afterTextChanged(Editable s) {
            	if(!isAmountNull()){
            		calculateTip();
            	}
            }
            public void beforeTextChanged(CharSequence s, int start, int count, int after){}
            public void onTextChanged(CharSequence s, int start, int before, int count){}
        });
        
        etAmount.setOnFocusChangeListener(new OnFocusChangeListener() {
        	public void onFocusChange(View view, boolean gainFocus) {
        		if (gainFocus)
        			//((EditText) view).setSelection(etAmount.length());
        			setImageButtons();
        	}
       	});
        
        etCustom.setOnFocusChangeListener(new OnFocusChangeListener() {
        	public void onFocusChange(View view, boolean gainFocus) {
        		if (gainFocus){
        			resetButtons();		
        			((EditText) view).setBackgroundResource(R.drawable.button_true);
        			//((EditText) view).setSelection(etCustom.length()-1);
        			if(!isAmountNull() && !isTipNull()){
                		calculateTip();
                	}
        			
        		}
        
        	}
       	});
        
        etCustom.addTextChangedListener(new TextWatcher(){
            public void afterTextChanged(Editable s) {
            	if(!isAmountNull() && !isTipNull()){
            		calculateTip();
            	}
            }
            public void beforeTextChanged(CharSequence s, int start, int count, int after){}
            public void onTextChanged(CharSequence s, int start, int before, int count){}
        });
        
        
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.caculate, menu);
        return true;
    }
    
    void setActivity(){
    	etAmount = (EditText) findViewById(R.id.etAmount);
    	etCustom = (EditText) findViewById(R.id.etCustomTip);
    	tvResult = (TextView) findViewById(R.id.tvResult);
    	bt10Percent = (Button) findViewById(R.id.but10Percent);
    	bt15Percent = (Button) findViewById(R.id.but15Percent);
    	bt20Percent = (Button) findViewById(R.id.but20Percent);
    	tipPercent = 0.10;
    	etCustom.setText("%");
    	etAmount.setText("$");
    	etAmount.setSelection(etAmount.length());
    	setImageButtons();
    	//tvResult.setVisibility(View.INVISIBLE);
    }
    
    public void onClick10Percent(View v){
    	tipPercent = 0.1;
    	setImageButtons();
    	calculateTip();
    }
    
    public void onClick15Percent(View v){
    	tipPercent = 0.15;
        setImageButtons();
        calculateTip();
    }
    
    public void onClick20Percent(View v){
    	tipPercent = 0.2;
    	setImageButtons();
    	calculateTip();
    }
    
    void resetButtons(){
    	bt10Percent.setBackgroundResource(R.drawable.button_false);
    	bt15Percent.setBackgroundResource(R.drawable.button_false);
    	bt20Percent.setBackgroundResource(R.drawable.button_false);	
    	etCustom.setBackgroundResource(R.drawable.button_false);
    }
    
    void setImageButtons(){
    	resetButtons();
    	etAmount.requestFocus();
    	if(tipPercent == 0.10)
    		bt10Percent.setBackgroundResource(R.drawable.button_true);
    	else if(tipPercent == 0.15)
    		bt15Percent.setBackgroundResource(R.drawable.button_true);
    	else if(tipPercent == 0.20)
    		bt20Percent.setBackgroundResource(R.drawable.button_true);
    	else
    		etCustom.setBackgroundResource(R.drawable.button_true);
    }
    
    void calculateTip(){
    	if(!isAmountNull()){
    		double tip = amount*tipPercent;
        	result = "Tip is ";
        	result += tip;
        	displayResult(result);
    	}
    }
    
    void displayResult(String value){
    	tvResult.setText(value);
    }
    
    boolean isAmountNull(){
    	String amt = etAmount.getText().toString();
    	if(amt.isEmpty() || amt.equals("$")){
    		Toast.makeText(getBaseContext(), "No Amount Provided", Toast.LENGTH_SHORT).show();
    		displayResult("");
     		return true;
    	}
    	else{
    		amt = amt.substring(1,amt.length());
    		amount = Double.parseDouble(amt);
    		return false;
    	}
    }
    
    boolean isTipNull(){
    	String tip = etCustom.getText().toString();
    	if(tip.isEmpty() || tip.equals("%")){
    		Toast.makeText(getBaseContext(), "No Tip Provided", Toast.LENGTH_SHORT).show();
    		displayResult("");
     		return true;
    	}
    	else{
    		tip = tip.substring(0,tip.length()-1);
    		tipPercent = Double.parseDouble(tip)*0.01;
    		return false;
    	}
    }
    
    
}
