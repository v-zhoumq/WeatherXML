package it.weather.http;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.TimeoutException;

import org.xmlpull.v1.XmlPullParser;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.util.Xml;
import it.weather.bean.weather;

public class HttpUtils implements Runnable{

	private Handler handler;
	private final String BASE_URL = "http://flash.weather.com.cn/wmaps/xml/";
	private final String WEAICON_URL = "http://m.weather.com.cn/img/c";
	private String city;
	private int i;
	private Vector<String> weaIconUrl = new Vector<String>();
	public HttpUtils(Handler handler,String city){
		this.handler = handler;
		this.city = city;
	}
	
	@Override
	public void run() {
		weaIconUrl.removeAllElements();
		Message msg = new Message().obtain();
		List<weather> list = parseXml(getConn());
		if(list==null){
			msg.what = 2;
		}else{
			msg.what = 1;
			msg.obj = list;
		}
		handler.sendMessage(msg);
	}
	
	private InputStream getConn(){
		try {
			URL mUrl = new URL(BASE_URL+city+".xml");
			System.out.println("HttpUtils1--url:"+mUrl.toString());
			HttpURLConnection conn = (HttpURLConnection) mUrl.openConnection();
			conn.setReadTimeout(5*1000);
			conn.setConnectTimeout(3*1000);
			return conn.getInputStream();
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	private List<weather> parseXml(InputStream is){
		try {
			XmlPullParser parse = Xml.newPullParser();
			parse.setInput(new InputStreamReader(is));
			int eventType = parse.getEventType();
			List<weather> list = null;
			while(eventType != XmlPullParser.END_DOCUMENT){
				String TagName = parse.getName();
				switch (eventType) {
				case XmlPullParser.START_DOCUMENT:
					System.out.println("¿ªÊ¼½âÎö..");
					list = new ArrayList<weather>();
					break;
				case XmlPullParser.START_TAG:
					if(TagName.equals("city")){
						weaIconUrl.addElement(WEAICON_URL+parse.getAttributeValue(null,"state1")+".gif");
						weather weather_bean = new weather(parse.getAttributeValue(null,"tem2"),
								parse.getAttributeValue(null,"tem1"),
								parse.getAttributeValue(null,"cityname"),
								getWeaIcon(),
								parse.getAttributeValue(null,"stateDetailed"));
						list.add(weather_bean);
					}
					break;
				}
				eventType = parse.next();
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private Bitmap getWeaIcon(){
			try {
				System.out.println("i:"+i);
				URL url = new URL(weaIconUrl.elementAt(i));
				HttpURLConnection conn = (HttpURLConnection) url.openConnection();
				System.out.println("weaIconUrl:"+weaIconUrl.elementAt(i));
				i++;
				return BitmapFactory.decodeStream(conn.getInputStream());
			} catch (Exception e) {
				e.printStackTrace();
			}
		return null;
	}
	
}
