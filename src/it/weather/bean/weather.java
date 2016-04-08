package it.weather.bean;

import android.graphics.Bitmap;

public class weather {

	private String lowTem;
	private String highTem;
	private String cityname;
	private Bitmap weaIcon;
	private String stateDetailed;
	
	public weather() {
	}
	
	public weather(String lowTem,String highTem,String cityname,Bitmap weaIcon,String stateDetailed){
		this.lowTem = lowTem;
		this.highTem = highTem;
		this.cityname = cityname;
		this.weaIcon = weaIcon;
		this.stateDetailed = stateDetailed;
	}
	
	public void setStateDetailed(String stateDetailed) {
		this.stateDetailed = stateDetailed;
	}
	
	public String getStateDetailed() {
		return stateDetailed;
	}
	
	public String getLowTem() {
		return lowTem;
	}
	public void setLowTem(String lowTem) {
		this.lowTem = lowTem;
	}
	public String getHighTem() {
		return highTem;
	}
	public void setHighTem(String highTem) {
		this.highTem = highTem;
	}
	public String getCityname() {
		return cityname;
	}
	public void setCityname(String cityname) {
		this.cityname = cityname;
	}
	public Bitmap getWeaIcon() {
		return weaIcon;
	}
	public void setWeaIcon(Bitmap weaIcon) {
		this.weaIcon = weaIcon;
	}
	
}
