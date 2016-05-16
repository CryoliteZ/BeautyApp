package csmsuse.pushing.beautyapp;

import java.util.ArrayList;
import java.util.Map;

import android.content.Context;
import android.content.SharedPreferences;

public class SettingsManager {
	private final String SETTINGS_CMD_INFO = "ISTVSsetting";

	private Context mContext;
	private SharedPreferences userInfo;

	public SettingsManager(Context context) {
		mContext = context;
		userInfo = mContext.getSharedPreferences("userInfo", 0);
	}
	
	
	/*
	 * 
	 * public static String OrderName = "Yee";
	public static String OrderAddress = "NTU";
	public static String OrderPhone = "113";
	public static String ConsigneeName = "CNNNN";
	public static String ConsigneeAddress = "Earth";
	public static String ConsigneePhone = "11111";
	 * 
	 */
	

	/* setters */
	
	/* setters */


	public void setAge(int age) {
		SharedPreferences.Editor editor = userInfo.edit();
		editor.putInt("age", age);
		editor.commit();
	}

	public void setAge(String age) {
		SharedPreferences.Editor editor = userInfo.edit();
		try {
			editor.putInt("age", Integer.parseInt(age));
		} catch (Exception e) {
		}
		;
		editor.commit();
	}

	/* gender (1 for male, 2 for female, 3 for others) */
	public void setGender(int gender) {
		SharedPreferences.Editor editor = userInfo.edit();
		editor.putInt("gender", gender);
		editor.commit();
	}

	public void setGender(String gender) {
		SharedPreferences.Editor editor = userInfo.edit();
		try {
			editor.putInt("gender", Integer.parseInt(gender));
		} catch (Exception x) {
		}
		;
		editor.commit();	}

	

	public void setOrderName(String name) {
		SharedPreferences.Editor editor = userInfo.edit();
		editor.putString("OrderName", name);
		editor.commit();
	}
	public void setOrderAddress(String name) {
		SharedPreferences.Editor editor = userInfo.edit();
		editor.putString("OrderAddress", name);
		editor.commit();
	}
	public void setOrderPhone(String name) {
		SharedPreferences.Editor editor = userInfo.edit();
		editor.putString("OrderPhone", name);
		editor.commit();
	}

	public void setConsigneeName(String name) {
		SharedPreferences.Editor editor = userInfo.edit();
		editor.putString("ConsigneeName", name);
		editor.commit();
	}
	public void setConsigneeAddress(String name) {
		SharedPreferences.Editor editor = userInfo.edit();
		editor.putString("ConsigneeAddress", name);
		editor.commit();
	}
	public void setConsigneePhone(String name) {
		SharedPreferences.Editor editor = userInfo.edit();
		editor.putString("ConsigneePhone", name);
		editor.commit();
	}




	/* getters */	

	public int getAge() {
		return this.userInfo.getInt("age", 0);
	}

	public int getGender() {
		return this.userInfo.getInt("gender", 0);	}
	

	public String getOrderName() {
		return this.userInfo.getString("OrderName", "No name");
	}
	public String getOrderAddress() {
		return this.userInfo.getString("OrderAddress", "No Address");
	}
	public String getOrderPhone() {
		return this.userInfo.getString("OrderPhone", "No Phone");
	}
	public String getConsigneeName() {
		return this.userInfo.getString("ConsigneeName", "No Cname");
	}
	public String getConsigneeAddress() {
		return this.userInfo.getString("ConsigneeAddress", "No CAddress");
	}
	public String getConsigneePhone() {
		return this.userInfo.getString("ConsigneePhone", "No CPhone");
	}



}
