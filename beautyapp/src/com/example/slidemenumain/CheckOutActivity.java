package com.example.slidemenumain;

import android.content.Intent;
import android.os.Bundle;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;
 
 
 
public class CheckOutActivity extends AppCompatActivity  {

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
	

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.check_out);
        Intent intent = getIntent();        
        ProductSN = intent.getStringExtra("ProductSN");
        Price = intent.getIntExtra("Price", 0);
        
        checkOutGO();
        
        
        (new SettingsManager(this)).getOrderAddress();
       }
    
    
    public void checkOutGO(){
    	SettingsManager m = new SettingsManager(this);
    	m.setOrderAddress(OrderAddress);
    	
    	
    	
        Amount = Price * Quantity;
		
//		WebView webView = new WebView(this);
//		webView.loadUrl("http://nonsense.hol.es/buy.php?ProductSN=" + ProductSN + "&Quantity=" + Integer.toString(Quantity) +"&Price=" + Integer.toString(Price) + "&Amount=" + Integer.toString(Amount) + "&OrderName=" + OrderName + "&OrderAddress=" +OrderAddress + "&OrderPhone=" + OrderPhone + "&ConsigneeName=" + ConsigneeName + "&ConsigneeAddress=" + ConsigneeAddress + "&ConsigneePhone=" + ConsigneePhone + "&DeliverTime=" + DeliverTime);
////		setContentView(webView);
    }



    	
    	
    	
    

  
}
