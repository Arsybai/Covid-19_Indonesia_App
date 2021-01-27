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
import android.widget.ListView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.content.Intent;
import android.net.Uri;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.AdapterView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.DialogFragment;


public class SingleActivity extends  Activity { 
	
	
	private HashMap<String, Object> pp = new HashMap<>();
	
	private ArrayList<HashMap<String, Object>> rrrr = new ArrayList<>();
	
	private LinearLayout linear1;
	private LinearLayout linear2;
	private ImageView back;
	private TextView textview1;
	private LinearLayout box1;
	private ListView litems;
	private LinearLayout linear3;
	private LinearLayout k1;
	private LinearLayout k2;
	private LinearLayout k3;
	private TextView prov;
	private TextView textview3;
	private TextView positif;
	private TextView textview8;
	private TextView meninggal;
	private TextView textview10;
	private TextView sembuh;
	
	private Intent intent = new Intent();
	private SharedPreferences data;
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.single);
		initialize(_savedInstanceState);
		initializeLogic();
	}
	
	private void initialize(Bundle _savedInstanceState) {
		
		linear1 = (LinearLayout) findViewById(R.id.linear1);
		linear2 = (LinearLayout) findViewById(R.id.linear2);
		back = (ImageView) findViewById(R.id.back);
		textview1 = (TextView) findViewById(R.id.textview1);
		box1 = (LinearLayout) findViewById(R.id.box1);
		litems = (ListView) findViewById(R.id.litems);
		linear3 = (LinearLayout) findViewById(R.id.linear3);
		k1 = (LinearLayout) findViewById(R.id.k1);
		k2 = (LinearLayout) findViewById(R.id.k2);
		k3 = (LinearLayout) findViewById(R.id.k3);
		prov = (TextView) findViewById(R.id.prov);
		textview3 = (TextView) findViewById(R.id.textview3);
		positif = (TextView) findViewById(R.id.positif);
		textview8 = (TextView) findViewById(R.id.textview8);
		meninggal = (TextView) findViewById(R.id.meninggal);
		textview10 = (TextView) findViewById(R.id.textview10);
		sembuh = (TextView) findViewById(R.id.sembuh);
		data = getSharedPreferences("data", Activity.MODE_PRIVATE);
		
		back.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				intent.setClass(getApplicationContext(), HomeActivity.class);
				startActivity(intent);
				finish();
			}
		});
		
		litems.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> _param1, View _param2, int _param3, long _param4) {
				final int _position = _param3;
				intent.setAction(Intent.ACTION_VIEW);
				intent.setData(Uri.parse(rrrr.get((int)_position).get("url").toString()));
				startActivity(intent);
			}
		});
	}
	
	private void initializeLogic() {
		getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
		getWindow().setStatusBarColor(0xFFFFFFFF);
		box1.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)10, 0xFF81D4FA));
		k1.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)10, 0xFFB3E5FC));
		k2.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)10, 0xFFB3E5FC));
		k3.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)10, 0xFFB3E5FC));
		pp = new Gson().fromJson(getIntent().getStringExtra("data"), new TypeToken<HashMap<String, Object>>(){}.getType());
		prov.setText(pp.get("Provinsi").toString());
		positif.setText(String.valueOf((long)(Double.parseDouble(pp.get("Kasus_Posi").toString()))));
		meninggal.setText(String.valueOf((long)(Double.parseDouble(pp.get("Kasus_Meni").toString()))));
		sembuh.setText(String.valueOf((long)(Double.parseDouble(pp.get("Kasus_Semb").toString()))));
		rrrr = new Gson().fromJson(data.getString("more", ""), new TypeToken<ArrayList<HashMap<String, Object>>>(){}.getType());
		litems.setAdapter(new LitemsAdapter(rrrr));
		((BaseAdapter)litems.getAdapter()).notifyDataSetChanged();
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
		intent.setClass(getApplicationContext(), HomeActivity.class);
		startActivity(intent);
		finish();
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
				_view = _inflater.inflate(R.layout.rmore, null);
			}
			
			final LinearLayout rbox = (LinearLayout) _view.findViewById(R.id.rbox);
			final TextView title = (TextView) _view.findViewById(R.id.title);
			final TextView subtitle = (TextView) _view.findViewById(R.id.subtitle);
			
			rbox.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)10, 0xFFF8BBD0));
			title.setText(rrrr.get((int)_position).get("title").toString());
			subtitle.setText(rrrr.get((int)_position).get("subtitle").toString());
			
			return _view;
		}
	}
	
	
}
