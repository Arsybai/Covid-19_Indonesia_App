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
import java.util.HashMap;
import java.util.ArrayList;
import android.widget.LinearLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ProgressBar;
import android.widget.ListView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.AdapterView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.DialogFragment;


public class HomeActivity extends  Activity { 
	
	
	private HashMap<String, Object> tmp = new HashMap<>();
	private String item = "";
	private String placesString = "";
	private HashMap<String, Object> t2 = new HashMap<>();
	
	private ArrayList<HashMap<String, Object>> indomain = new ArrayList<>();
	private ArrayList<HashMap<String, Object>> profinsi = new ArrayList<>();
	
	private LinearLayout linear1;
	private LinearLayout linear2;
	private ImageView back;
	private TextView textview1;
	private LinearLayout box1;
	private ProgressBar progressbar1;
	private TextView tds;
	private ListView litems;
	private LinearLayout linear3;
	private LinearLayout k1;
	private LinearLayout k2;
	private LinearLayout k3;
	private LinearLayout k4;
	private LinearLayout linear4;
	private TextView textview2;
	private TextView textview3;
	private TextView positif;
	private TextView textview8;
	private TextView meninggal;
	private TextView textview10;
	private TextView sembuh;
	private TextView textview12;
	private TextView dirawat;
	private TextView more;
	
	private AlertDialog.Builder sgl;
	private RequestNetwork indo;
	private RequestNetwork.RequestListener _indo_request_listener;
	private RequestNetwork provinsi;
	private RequestNetwork.RequestListener _provinsi_request_listener;
	private AlertDialog.Builder dbl;
	private Intent intent = new Intent();
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.home);
		initialize(_savedInstanceState);
		initializeLogic();
	}
	
	private void initialize(Bundle _savedInstanceState) {
		
		linear1 = (LinearLayout) findViewById(R.id.linear1);
		linear2 = (LinearLayout) findViewById(R.id.linear2);
		back = (ImageView) findViewById(R.id.back);
		textview1 = (TextView) findViewById(R.id.textview1);
		box1 = (LinearLayout) findViewById(R.id.box1);
		progressbar1 = (ProgressBar) findViewById(R.id.progressbar1);
		tds = (TextView) findViewById(R.id.tds);
		litems = (ListView) findViewById(R.id.litems);
		linear3 = (LinearLayout) findViewById(R.id.linear3);
		k1 = (LinearLayout) findViewById(R.id.k1);
		k2 = (LinearLayout) findViewById(R.id.k2);
		k3 = (LinearLayout) findViewById(R.id.k3);
		k4 = (LinearLayout) findViewById(R.id.k4);
		linear4 = (LinearLayout) findViewById(R.id.linear4);
		textview2 = (TextView) findViewById(R.id.textview2);
		textview3 = (TextView) findViewById(R.id.textview3);
		positif = (TextView) findViewById(R.id.positif);
		textview8 = (TextView) findViewById(R.id.textview8);
		meninggal = (TextView) findViewById(R.id.meninggal);
		textview10 = (TextView) findViewById(R.id.textview10);
		sembuh = (TextView) findViewById(R.id.sembuh);
		textview12 = (TextView) findViewById(R.id.textview12);
		dirawat = (TextView) findViewById(R.id.dirawat);
		more = (TextView) findViewById(R.id.more);
		sgl = new AlertDialog.Builder(this);
		indo = new RequestNetwork(this);
		provinsi = new RequestNetwork(this);
		dbl = new AlertDialog.Builder(this);
		
		back.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				dbl.setTitle("Keluar");
				dbl.setMessage("Apakah anda yakin ingin keluar?");
				dbl.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface _dialog, int _which) {
						finish();
					}
				});
				dbl.setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface _dialog, int _which) {
						
					}
				});
				dbl.create().show();
			}
		});
		
		litems.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> _param1, View _param2, int _param3, long _param4) {
				final int _position = _param3;
				tmp = new HashMap<>();
				tmp = profinsi.get((int)_position);
				placesString = (new Gson()).toJson(tmp.get("attributes"), new TypeToken<HashMap<String, Object>>(){}.getType());
				intent.putExtra("data", placesString);
				intent.setClass(getApplicationContext(), SingleActivity.class);
				startActivity(intent);
				finish();
			}
		});
		
		more.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				intent.setClass(getApplicationContext(), MoreActivity.class);
				startActivity(intent);
				finish();
			}
		});
		
		_indo_request_listener = new RequestNetwork.RequestListener() {
			@Override
			public void onResponse(String _param1, String _param2, HashMap<String, Object> _param3) {
				final String _tag = _param1;
				final String _response = _param2;
				final HashMap<String, Object> _responseHeaders = _param3;
				indomain = new Gson().fromJson(_response, new TypeToken<ArrayList<HashMap<String, Object>>>(){}.getType());
				positif.setText(indomain.get((int)0).get("positif").toString());
				sembuh.setText(indomain.get((int)0).get("sembuh").toString());
				meninggal.setText(indomain.get((int)0).get("meninggal").toString());
				dirawat.setText(indomain.get((int)0).get("dirawat").toString());
			}
			
			@Override
			public void onErrorResponse(String _param1, String _param2) {
				final String _tag = _param1;
				final String _message = _param2;
				sgl.setTitle("Koneksi Eror");
				sgl.setMessage(_message.replace("api.kawalcorona.com", "HOST"));
				sgl.setPositiveButton("Oke", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface _dialog, int _which) {
						finish();
					}
				});
				sgl.create().show();
			}
		};
		
		_provinsi_request_listener = new RequestNetwork.RequestListener() {
			@Override
			public void onResponse(String _param1, String _param2, HashMap<String, Object> _param3) {
				final String _tag = _param1;
				final String _response = _param2;
				final HashMap<String, Object> _responseHeaders = _param3;
				profinsi = new Gson().fromJson(_response, new TypeToken<ArrayList<HashMap<String, Object>>>(){}.getType());
				progressbar1.setVisibility(View.GONE);
				tds.setVisibility(View.VISIBLE);
				litems.setVisibility(View.VISIBLE);
				litems.setAdapter(new LitemsAdapter(profinsi));
				((BaseAdapter)litems.getAdapter()).notifyDataSetChanged();
			}
			
			@Override
			public void onErrorResponse(String _param1, String _param2) {
				final String _tag = _param1;
				final String _message = _param2;
				sgl.setTitle("Koneksi Eror");
				sgl.setMessage(_message.replace("api.kawalcorona.com", "HOST"));
				sgl.setPositiveButton("Oke", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface _dialog, int _which) {
						finish();
					}
				});
				sgl.create().show();
			}
		};
	}
	
	private void initializeLogic() {
		getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
		getWindow().setStatusBarColor(0xFFFFFFFF);
		box1.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)10, 0xFF81D4FA));
		k1.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)10, 0xFFB3E5FC));
		k2.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)10, 0xFFB3E5FC));
		k3.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)10, 0xFFB3E5FC));
		k4.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)10, 0xFFB3E5FC));
		litems.setVisibility(View.GONE);
		tds.setVisibility(View.GONE);
		indo.startRequestNetwork(RequestNetworkController.GET, "https://api.kawalcorona.com/indonesia", "stat", _indo_request_listener);
		provinsi.startRequestNetwork(RequestNetworkController.GET, "https://api.kawalcorona.com/indonesia/provinsi", "substat", _provinsi_request_listener);
	}
	
	@Override
	protected void onActivityResult(int _requestCode, int _resultCode, Intent _data) {
		
		super.onActivityResult(_requestCode, _resultCode, _data);
		
		switch (_requestCode) {
			
			default:
			break;
		}
	}
	
	@Override
	public void onBackPressed() {
		dbl.setTitle("Keluar");
		dbl.setMessage("Apakah anda yakin ingin keluar?");
		dbl.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface _dialog, int _which) {
				finish();
			}
		});
		dbl.setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface _dialog, int _which) {
				
			}
		});
		dbl.create().show();
	}
	public class LitemsAdapter extends BaseAdapter {
		ArrayList<HashMap<String, Object>> _data;
		public LitemsAdapter(ArrayList<HashMap<String, Object>> _arr) {
			_data = _arr;
		}
		
		@Override
		public int getCount() {
			return _data.size();
		}
		
		@Override
		public HashMap<String, Object> getItem(int _index) {
			return _data.get(_index);
		}
		
		@Override
		public long getItemId(int _index) {
			return _index;
		}
		@Override
		public View getView(final int _position, View _v, ViewGroup _container) {
			LayoutInflater _inflater = (LayoutInflater)getBaseContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			View _view = _v;
			if (_view == null) {
				_view = _inflater.inflate(R.layout.listed, null);
			}
			
			final LinearLayout lbox = (LinearLayout) _view.findViewById(R.id.lbox);
			final TextView textview1 = (TextView) _view.findViewById(R.id.textview1);
			
			lbox.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)10, 0xFFA7FFEB));
			tmp = new HashMap<>();
			tmp = profinsi.get((int)_position);
			placesString = (new Gson()).toJson(tmp.get("attributes"), new TypeToken<HashMap<String, Object>>(){}.getType());
			t2 = new HashMap<>();
			t2 = new Gson().fromJson(placesString, new TypeToken<HashMap<String, Object>>(){}.getType());
			textview1.setText(t2.get("Provinsi").toString());
			
			return _view;
		}
	}
	
	
}
