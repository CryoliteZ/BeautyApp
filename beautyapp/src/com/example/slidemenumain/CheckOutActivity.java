package com.example.slidemenumain;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class CheckOutActivity extends AppCompatActivity {

	public static int Quantity = 10;
	public static int Amount = 0;
	public static String OrderName = "Yee";
	public static String OrderAddress = "NTU";
	public static String OrderPhone = "113";
	public static String ConsigneeName = "CNNNN";
	public static String ConsigneeAddress = "Earth";
	public static String ConsigneePhone = "11111";
	public static String DeliverTime = "123123";
	public static String ProductSN;
	public static int Price;
	public static EditText customerIDEdit, customerPhoneEdit, customerAddressEdit, consigneeIDEdit, consigneePhoneEdit,
			consigneeAddressEdit, quantityEdit, priceShow;
	public static CheckBox samePhone, sameAddress, sameID;
	public static Button submitBuy;
	private SeekBar QuantityBar;
	public TextView priceValue;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.check_out);
		Intent intent = getIntent();
		ProductSN = intent.getStringExtra("ProductSN");
		Price = intent.getIntExtra("Price", 0);

		// Prevent keypad auto appear
		getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

		customerIDEdit = (EditText) findViewById(R.id.customerIDEdit);
		customerPhoneEdit = (EditText) findViewById(R.id.customerPhoneEdit);
		customerAddressEdit = (EditText) findViewById(R.id.customerAddressEdit);
		consigneeIDEdit = (EditText) findViewById(R.id.consigneeIDEdit);
		consigneePhoneEdit = (EditText) findViewById(R.id.consigneePhoneEdit);
		consigneeAddressEdit = (EditText) findViewById(R.id.consigneeAddressEdit);		
		sameID = (CheckBox)findViewById(R.id.sameID);
		samePhone = (CheckBox)findViewById(R.id.samePhone);
		sameAddress = (CheckBox)findViewById(R.id.sameAddress);		
		submitBuy = (Button)findViewById(R.id.submitBuy);	
		quantityEdit = (EditText) findViewById(R.id.quantityEdit);
		priceValue = (TextView) findViewById(R.id.priceValue);
		
		customerIDEdit.setText((new SettingsManager(this)).getOrderName());
		customerPhoneEdit.setText((new SettingsManager(this)).getOrderPhone());
		customerAddressEdit.setText((new SettingsManager(this)).getOrderAddress());
		consigneeIDEdit.setText((new SettingsManager(this)).getConsigneeName());
		consigneePhoneEdit.setText((new SettingsManager(this)).getConsigneeAddress());
		consigneeAddressEdit.setText((new SettingsManager(this)).getConsigneePhone());
		
		TextWatcher watcher= new TextWatcher() {
			
			@Override
			public void afterTextChanged(Editable s) {
				if(quantityEdit.getText().toString()!= ""){
					Amount = Integer.parseInt(quantityEdit.getText().toString()) * Price ;
					priceValue.setText(Integer.toString(Amount));
				}
				
			}
	      
	        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
	              //Do something or nothing.                
	        }
	        public void onTextChanged(CharSequence s, int start, int before, int count) {
	            //Do something or nothing
	        }
			
	    };

	    quantityEdit.addTextChangedListener(watcher);
		
		sameID.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
		       @Override
		       	public void onCheckedChanged(CompoundButton buttonView,boolean isChecked) {
		    	   if(isChecked)
		    		   consigneeIDEdit.setText(customerIDEdit.getText().toString());
		    	   else
		    		   consigneeIDEdit.setText("");
		       }
		   }
		); 
		sameAddress.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
		       @Override
		       public void onCheckedChanged(CompoundButton buttonView,boolean isChecked) {
		    	   if(isChecked)
		    		   consigneeAddressEdit.setText(customerAddressEdit.getText().toString());
		    	   else
		    		   consigneeAddressEdit.setText("");
		       }
		   }
		); 
		samePhone.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
		       @Override
		       public void onCheckedChanged(CompoundButton buttonView,boolean isChecked) {
		    	   if(isChecked)
		    		   consigneePhoneEdit.setText(customerPhoneEdit.getText().toString());
		    	   else
		    		   consigneePhoneEdit.setText("");
		       }
		   }
		); 
		submitBuy.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				checkOutGO();
			}
		});	
	}

	public void checkOutGO() {
		SettingsManager m = new SettingsManager(this);

		// get data
		OrderName = customerIDEdit.getText().toString();
		OrderPhone = customerPhoneEdit.getText().toString();
		OrderAddress = customerAddressEdit.getText().toString();
		ConsigneeName = consigneeIDEdit.getText().toString();
		ConsigneeAddress = consigneeAddressEdit.getText().toString();
		ConsigneePhone = consigneePhoneEdit.getText().toString();

		String tmpQuantity = quantityEdit.getText().toString();
		if (tmpQuantity.isEmpty()) {
			Toast.makeText(getApplicationContext(), "请键入数量", Toast.LENGTH_SHORT).show();
			return;
		}
		Quantity = Integer.parseInt(quantityEdit.getText().toString());

		// save preference data
		m.setOrderName(OrderName);
		m.setOrderPhone(OrderPhone);
		m.setOrderAddress(OrderAddress);
		m.setConsigneeName(ConsigneeName);
		m.setConsigneeAddress(ConsigneeAddress);
		m.setConsigneePhone(ConsigneePhone);

		Amount = Price * Quantity;

		WebView webView = (WebView) findViewById(R.id.unionPayWebView);
		webView.loadUrl("http://nonsense.hol.es/buy.php?ProductSN=" + ProductSN + "&Quantity="
				+ Integer.toString(Quantity) + "&Price=" + Integer.toString(Price) + "&Amount="
				+ Integer.toString(Amount) + "&OrderName=" + OrderName + "&OrderAddress=" + OrderAddress
				+ "&OrderPhone=" + OrderPhone + "&ConsigneeName=" + ConsigneeName + "&ConsigneeAddress="
				+ ConsigneeAddress + "&ConsigneePhone=" + ConsigneePhone + "&DeliverTime=" + DeliverTime);
		// setContentView(webView);
	}

}
