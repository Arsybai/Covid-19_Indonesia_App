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
import android.widget.ListView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.content.SharedPreferences;
import android.content.Intent;
import android.net.Uri;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.View;
import android.widget.AdapterView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.DialogFragment;


public class MoreActivity extends  Activity { 
	
	
	private ArrayList<HashMap<String, Object>> rrrrr = new ArrayList<>();
	
	private LinearLayout linear1;
	private ListView listview1;
	private ImageView back;
	private TextView textview1;
	
	private SharedPreferences data;
	private Intent intent = new Intent();
	private AlertDialog.Builder sql;
	private AlertDialog.Builder sgl;
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.more);
		initialize(_savedInstanceState);
		initializeLogic();
	}
	
	private void initialize(Bundle _savedInstanceState) {
		
		linear1 = (LinearLayout) findViewById(R.id.linear1);
		listview1 = (ListView) findViewById(R.id.listview1);
		back = (ImageView) findViewById(R.id.back);
		textview1 = (TextView) findViewById(R.id.textview1);
		data = getSharedPreferences("data", Activity.MODE_PRIVATE);
		sql = new AlertDialog.Builder(this);
		sgl = new AlertDialog.Builder(this);
		
		listview1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> _param1, View _param2, int _param3, long _param4) {
				final int _position = _param3;
				intent.setAction(Intent.ACTION_VIEW);
				intent.setData(Uri.parse(rrrrr.get((int)_position).get("url").toString()));
				startActivity(intent);
			}
		});
		
		back.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				intent.setClass(getApplicationContext(), HomeActivity.class);
				startActivity(intent);
				finish();
			}
		});
	}
	
	private void initializeLogic() {
		rrrrr = new Gson().fromJson(data.getString("more", ""), new TypeToken<ArrayList<HashMap<String, Object>>>(){}.getType());
		listview1.setAdapter(new Listview1Adapter(rrrrr));
		((BaseAdapter)listview1.getAdapter()).notifyDataSetChanged();
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
	public class Listview1Adapter extends BaseAdapter {
		ArrayList<HashMap<String, Object>> _data;
		public Listview1Adapter(ArrayList<HashMap<String, Object>> _arr) {
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
			title.setText(rrrrr.get((int)_position).get("title").toString());
			subtitle.setText(rrrrr.get((int)_position).get("subtitle").toString());
			
			return _view;
		}
	}
	
	
}
