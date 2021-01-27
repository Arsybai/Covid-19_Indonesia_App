package com.covid19Indonesia.ganyuwangi;

import android.app.Activity;
import android.app.*;
import android.os.*;
import android.view.*;
import android.view.View.*;
import android.widget.*;
import android.content.*;
import android.content.res.*;
import android.graphics.*;
import android.graphics.drawable.*;
import android.media.*;
import android.net.*;
import android.text.*;
import android.text.style.*;
import android.util.*;
import android.webkit.*;
import android.animation.*;
import android.view.animation.*;
import java.util.*;
import java.util.regex.*;
import java.text.*;
import org.json.*;
import java.util.ArrayList;
import java.util.HashMap;
import android.widget.LinearLayout;
import com.airbnb.lottie.*;
import android.widget.TextView;
import java.util.Timer;
import java.util.TimerTask;
import android.content.Intent;
import android.net.Uri;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.DialogFragment;


public class MainActivity extends  Activity { 
	
	private Timer _timer = new Timer();
	
	private ArrayList<HashMap<String, Object>> yty = new ArrayList<>();
	
	private LinearLayout linear1;
	private LottieAnimationView lottie1;
	private TextView textview1;
	
	private TimerTask load;
	private Intent intent = new Intent();
	private RequestNetwork network;
	private RequestNetwork.RequestListener _network_request_listener;
	private AlertDialog.Builder dialog;
	private SharedPreferences data;
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.main);
		initialize(_savedInstanceState);
		initializeLogic();
	}
	
	private void initialize(Bundle _savedInstanceState) {
		
		linear1 = (LinearLayout) findViewById(R.id.linear1);
		lottie1 = (LottieAnimationView) findViewById(R.id.lottie1);
		textview1 = (TextView) findViewById(R.id.textview1);
		network = new RequestNetwork(this);
		dialog = new AlertDialog.Builder(this);
		data = getSharedPreferences("data", Activity.MODE_PRIVATE);
		
		_network_request_listener = new RequestNetwork.RequestListener() {
			@Override
			public void onResponse(String _param1, String _param2, HashMap<String, Object> _param3) {
				final String _tag = _param1;
				final String _response = _param2;
				final HashMap<String, Object> _responseHeaders = _param3;
				intent.setClass(getApplicationContext(), HomeActivity.class);
				startActivity(intent);
				finish();
			}
			
			@Override
			public void onErrorResponse(String _param1, String _param2) {
				final String _tag = _param1;
				final String _message = _param2;
				dialog.setTitle("Koneksi Eror");
				dialog.setMessage("Aplikasi memerlukan koneksi internet");
				dialog.setPositiveButton("Oke", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface _dialog, int _which) {
						finish();
					}
				});
				dialog.create().show();
			}
		};
	}
	
	private void initializeLogic() {
		getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
		getWindow().setStatusBarColor(0xFFFFFFFF);
		yty = new Gson().fromJson("[\n    {\n        \"url\":\"https://www.unicef.org/indonesia/id/coronavirus\",\n        \"title\":\"Hal-hal yang perlu anda ketahui\",\n        \"subtitle\":\"Unicef Indonesia\"\n    },\n    {\n        \"url\":\"https://www.kompas.com/tren/read/2020/03/03/183500265/infografik-daftar-100-rumah-sakit-rujukan-penanganan-virus-corona\",\n        \"title\":\"100 Rumah Sakit Rujukan Penanganan Covid-19\",\n        \"subtitle\":\"Kompas\"\n    },\n    {\n        \"url\":\"https://infeksiemerging.kemkes.go.id/\",\n        \"title\":\"Media Informasi Resmi Penyakit Infeksi Emerging\",\n        \"subtitle\":\"Kementrian Kesehatan\"\n    },\n    {\n        \"url\":\"https://www.who.int/emergencies/diseases/novel-coronavirus-2019/advice-for-public\",\n        \"title\":\"Corona Virus Disease Advice for The Public\",\n        \"subtitle\":\"WHO\"\n    }\n]", new TypeToken<ArrayList<HashMap<String, Object>>>(){}.getType());
		data.edit().putString("more", new Gson().toJson(yty)).commit();
		ArsybaiUtil.showMessage(getApplicationContext(), "Loading...");
		load = new TimerTask() {
			@Override
			public void run() {
				runOnUiThread(new Runnable() {
					@Override
					public void run() {
						network.startRequestNetwork(RequestNetworkController.GET, "https://google.com", "check", _network_request_listener);
					}
				});
			}
		};
		_timer.schedule(load, (int)(1000));
	}
	
	@Override
	protected void onActivityResult(int _requestCode, int _resultCode, Intent _data) {
		
		super.onActivityResult(_requestCode, _resultCode, _data);
		
		switch (_requestCode) {
			
			default:
			break;
		}
	}
	
	
}
