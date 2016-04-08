package it.weather.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

public class NavActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.nav_activity);
		if(isNetWorkConnected(NavActivity.this)){
			Toast.makeText(NavActivity.this,"网络可用",1).show();
		}else{
			Toast.makeText(NavActivity.this,"请检查网络连接",1).show();
		}
		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
				startActivity(new Intent(NavActivity.this,MainActivity.class));
				NavActivity.this.overridePendingTransition(R.anim.zoom_in,R.anim.zoom_out);
				finish();
			}
		},4*1000);
	}
	
	private boolean isNetWorkConnected(Context context){
		boolean isNetWork = false;
		if(context != null){
			ConnectivityManager mConnectivityManager = (ConnectivityManager) context.getSystemService(context.CONNECTIVITY_SERVICE);
			/*
			 * getActveNetWorkInfo();检查是否网络连接
			 * TYPE_MOBILE移动网络
			 */
//			NetworkInfo mNetWorkInfo = mConnectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
			NetworkInfo mNetWorkInfo = mConnectivityManager.getActiveNetworkInfo();
			if(mNetWorkInfo != null && mNetWorkInfo.isAvailable()){
				isNetWork = mNetWorkInfo.isAvailable();
				String NetName = mNetWorkInfo.getTypeName();
				System.out.println(NetName);
			}
		}
		return isNetWork;
	}
	
}
