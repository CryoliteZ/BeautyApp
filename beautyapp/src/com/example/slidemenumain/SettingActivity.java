package com.example.slidemenumain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.os.Vibrator;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

public class SettingActivity extends Activity implements AbsListView.OnScrollListener {



	/* Swipe container */
	SwipeRefreshLayout swipeContainer;

	/* List information */
	public ListView listView;
	public SimpleAdapter adapter;
	private int[] image = { R.drawable.ic_face_black_48dp, R.drawable.ic_wc_black_24dp,
			R.drawable.ic_cake_black_24dp, R.drawable.ic_local_phone_black_48dp, R.drawable.ic_my_location_black_48dp,
			R.drawable.ic_account_circle_black_48dp, R.drawable.ic_local_phone_black_48dp,
			R.drawable.ic_local_airport_black_48dp, R.drawable.ic_account_circle_black_48dp,
			R.drawable.ic_account_circle_black_48dp };
	private String[] settingText = { "Username", "Gender", "Age", "Phone", "Address", "Consign Name", "Consign Phone",
			"Consign Address", "Help", "About us" };
	private String[] settingHint = { "Set your order name", "What's your gender?", "Set your age for item filter",
			"Phone contact purpose",
			"Set your address so we can find you and sent you the goodies!",
			"Set the consignee name", "Set the consignee phone", "Set the consignee address", "Getting confused? Click Here!",
			"Contact Pushing#1" };

	private int lastTopValue = 0;
	private ImageView backgroundImage;

	private SettingsManager settingsManager;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_settings);

		/* initialize SettingsManager */
		settingsManager = new SettingsManager(this);
		/* ------Start AllJoyn Service KEYWORD!!---- */

		swipeContainer = (SwipeRefreshLayout) findViewById(R.id.swipe_container);
		swipeContainer.setOnRefreshListener(new OnRefreshListener() {
			@Override
			public void onRefresh() {
				new Handler().postDelayed(new Runnable() {
					@Override
					public void run() {
						swipeContainer.setRefreshing(false);
						Toast.makeText(SettingActivity.this, "All your settings have been saved", Toast.LENGTH_SHORT)
								.show();						
						Vibrator v = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);
						v.vibrate(500);
					}
				}, 3000);
			}

		});
		// Configure the refreshing colors
		swipeContainer.setColorSchemeResources(android.R.color.holo_blue_bright, android.R.color.holo_green_light,
				android.R.color.holo_orange_light, android.R.color.holo_red_light);

		/* ListView */
		listView = (ListView) findViewById(R.id.settingListView);
		List<Map<String, Object>> items = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < settingText.length; i++) {
			Map<String, Object> item = new HashMap<String, Object>();
			item.put("image", image[i]);
			item.put("text", settingText[i]);
			item.put("hint", settingHint[i]);
			items.add(item);
		}
		adapter = new SimpleAdapter(this, items, R.layout.activity_settings_listrow,
				new String[] { "image", "text", "hint" },
				new int[] { R.id.settingImage, R.id.settingText, R.id.settingHint });
		listView.setAdapter(adapter);

		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				switch (position) {
				case 1:
					final EditText input = new EditText(SettingActivity.this);
					input.setSingleLine(true);
					AlertDialog.Builder userDialog = new AlertDialog.Builder(SettingActivity.this).setTitle("USERNAME")
							.setMessage("Set your orderName:").setIcon(R.drawable.ic_face_black_24dp).setView(input)
							.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int which) {
							settingsManager.setOrderName(input.getText().toString());
						}
					}).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int which) {
//							Toast.makeText(SettingActivity.this, "neg", Toast.LENGTH_SHORT).show();
							dialog.cancel();
						}
					});
					input.setHint(settingsManager.getOrderName());
					userDialog.show();
					break;

				case 2:
					AlertDialog.Builder genderDialog = new AlertDialog.Builder(SettingActivity.this).setTitle("GENDER")
							.setIcon(R.drawable.ic_wc_black_24dp)
							.setPositiveButton("MALE", new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int which) {
							settingsManager.setGender(1);
						}
					}).setNegativeButton("FEMALE", new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int which) {
							settingsManager.setGender(2);
						}
					}).setNeutralButton("OTHER", new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int which) {
							settingsManager.setGender(3);
						}
					});
					genderDialog.show();
					break;

				case 3:
					String[] items = { "Below 12", "12~18", "Above 18" };
					AlertDialog.Builder ageDialog = new AlertDialog.Builder(SettingActivity.this).setTitle("AGE")
							.setIcon(R.drawable.ic_cake_black_24dp)
							.setSingleChoiceItems(items, settingsManager.getAge(), null)
							.setPositiveButton("CONFIRM", new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int which) {
							int position = ((AlertDialog) dialog).getListView().getCheckedItemPosition();
							settingsManager.setAge(position);

						}
					});
					ageDialog.show();
					break;

				case 4:
					final EditText inputPhone = new EditText(SettingActivity.this);
					inputPhone.setSingleLine(true);
					AlertDialog.Builder phoneDialog = new AlertDialog.Builder(SettingActivity.this).setTitle("ORDER PHONE")
							.setMessage("Set your phone:").setIcon(R.drawable.ic_local_phone_black_24dp).setView(inputPhone)
							.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int which) {
							settingsManager.setOrderPhone(inputPhone.getText().toString());
						}
					}).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int which) {
//							Toast.makeText(SettingActivity.this, "neg", Toast.LENGTH_SHORT).show();
							dialog.cancel();
						}
					});
					inputPhone.setHint(settingsManager.getOrderPhone());
					phoneDialog.show();
					break;

				case 5:
					final EditText inputAddress = new EditText(SettingActivity.this);
					inputAddress.setSingleLine(true);
					AlertDialog.Builder addressDialog = new AlertDialog.Builder(SettingActivity.this).setTitle("ORDER ADDRESS")
							.setMessage("Set your address:").setIcon(R.drawable.ic_my_location_black_24dp).setView(inputAddress)
							.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int which) {
							settingsManager.setOrderAddress(inputAddress.getText().toString());
						}
					}).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int which) {
//							Toast.makeText(SettingActivity.this, "neg", Toast.LENGTH_SHORT).show();
							dialog.cancel();
						}
					});
					inputAddress.setHint(settingsManager.getOrderAddress());
					addressDialog.show();
					break;

				case 6:
					final EditText inputConsigneeName = new EditText(SettingActivity.this);
					inputConsigneeName.setSingleLine(true);
					AlertDialog.Builder ConsigneeNamesDialog = new AlertDialog.Builder(SettingActivity.this).setTitle("CONSIGNEE NAME")
							.setMessage("Set consignee address:").setIcon(R.drawable.ic_face_black_24dp).setView(inputConsigneeName)
							.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int which) {
							settingsManager.setOrderAddress(inputConsigneeName.getText().toString());
						}
					}).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int which) {
//							Toast.makeText(SettingActivity.this, "neg", Toast.LENGTH_SHORT).show();
							dialog.cancel();
						}
					});
					inputConsigneeName.setHint(settingsManager.getOrderAddress());
					ConsigneeNamesDialog.show();
					break;
					
				case 7:
					final EditText inputConsigneePhone = new EditText(SettingActivity.this);
					inputConsigneePhone.setSingleLine(true);
					AlertDialog.Builder ConsigneePhoneDialog = new AlertDialog.Builder(SettingActivity.this).setTitle("CONSIGNEE PHONE")
							.setMessage("Set consignee address:").setIcon(R.drawable.ic_local_phone_black_24dp).setView(inputConsigneePhone)
							.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int which) {
							settingsManager.setConsigneePhone(inputConsigneePhone.getText().toString());
						}
					}).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int which) {
//							Toast.makeText(SettingActivity.this, "neg", Toast.LENGTH_SHORT).show();
							dialog.cancel();
						}
					});
					inputConsigneePhone.setHint(settingsManager.getConsigneePhone());
					ConsigneePhoneDialog.show();
					break;
					
				case 8:
					final EditText inputConsigneeAddress = new EditText(SettingActivity.this);
					inputConsigneeAddress.setSingleLine(true);
					AlertDialog.Builder ConsigneeAddressDialog = new AlertDialog.Builder(SettingActivity.this).setTitle("CONSIGNEE PHONE")
							.setMessage("Set consignee address:").setIcon(R.drawable.ic_local_airport_black_24dp).setView(inputConsigneeAddress)
							.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int which) {
							settingsManager.setConsigneeAddress(inputConsigneeAddress.getText().toString());
						}
					}).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int which) {
//							Toast.makeText(SettingActivity.this, "neg", Toast.LENGTH_SHORT).show();
							dialog.cancel();
						}
					});
					inputConsigneeAddress.setHint(settingsManager.getConsigneeAddress());
					ConsigneeAddressDialog.show();
					break;
				}

			}

		});

		// inflate custom header and attach it to the list
		LayoutInflater inflater = getLayoutInflater();
		ViewGroup header = (ViewGroup) inflater.inflate(R.layout.activity_settings_header, listView, false);
		listView.addHeaderView(header, null, false);

		// we take the background image and button reference from the header
		backgroundImage = (ImageView) header.findViewById(R.id.listHeaderImage);
		listView.setOnScrollListener(this);

	}

	@Override
	public void onScrollStateChanged(AbsListView view, int scrollState) {

	}

	@Override
	public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
		Rect rect = new Rect();
		backgroundImage.getLocalVisibleRect(rect);
		if (lastTopValue != rect.top) {
			lastTopValue = rect.top;
			backgroundImage.setY((float) (rect.top / 2.0));
		}
	}

}
