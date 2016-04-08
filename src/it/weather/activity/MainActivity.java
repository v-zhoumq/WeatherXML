package it.weather.activity;

import java.util.List;
import java.util.Vector;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import it.weather.adapter.MyAdapter;
import it.weather.bean.weather;
import it.weather.http.HttpUtils;

public class MainActivity extends Activity implements OnClickListener {

	/*
	 * 温馨提示：网速太渣会造成小Bug
	 * author:zhouMingQue
	 * time:2016-4-8
	 * 
	 */
	
	private EditText edt_city;
	private Button btn_getWeaInfo;
	private ListView lv;
	private TextView title_city;
	private ProgressDialog dialog;
	private Handler handler = new Handler(){
		public void handleMessage(android.os.Message msg) {
			dialog.dismiss();
			if(msg.what == 1){
				MyAdapter adapter = new MyAdapter((List<weather>)msg.obj,MainActivity.this);
				lv.setAdapter(adapter);
				title_city.setText(edt_city.getText().toString().trim());
				edt_city.setText("");
			}else if(msg.what == 2){
				AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
				alert.setMessage("没有该城市："+edt_city.getText().toString().trim());
				alert.setPositiveButton("确定",null);
				alert.show();
			}
		};
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initViewAndOnClick();
	}

	private void initViewAndOnClick() {
		edt_city = (EditText) this.findViewById(R.id.edt_city);
		title_city = (TextView) this.findViewById(R.id.tv_edt_city);
		btn_getWeaInfo = (Button) this.findViewById(R.id.btn_getWeaInfo);
		lv = (ListView) this.findViewById(R.id.lv);
		
		btn_getWeaInfo.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		if(TextUtils.isEmpty(edt_city.getText())){
			Toast.makeText(MainActivity.this,"亲，补全城市名称！",1).show();
			return;
		}
		new Thread(new HttpUtils(handler,edt_city.getText().toString().trim())).start();
		dialog = ProgressDialog.show(MainActivity.this,"Weather","加载...");
	}
}
