package it.weather.adapter;

import java.util.List;
import java.util.Vector;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import it.weather.activity.R;
import it.weather.bean.weather;

public class MyAdapter extends BaseAdapter {

	private List<weather> list;
	private LayoutInflater inflater;
	
	public MyAdapter(List<weather> list,Context context){
		this.list = list;
		inflater = LayoutInflater.from(context);
	}
	
	@Override
	public int getCount() {
		if(list.size()!=0) return list.size();
		return 0;
	}

	@Override
	public Object getItem(int position) {
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHodler vh = null;
		if(vh==null){
			vh = new ViewHodler();
			convertView = inflater.inflate(R.layout.item,null);
			vh.tv_city = (TextView) convertView.findViewById(R.id.tv_city);
			vh.tv_lowTem = (TextView) convertView.findViewById(R.id.tv_lowTem);
			vh.tv_highTem = (TextView) convertView.findViewById(R.id.tv_highTem);
			vh.tv_stateDetailed = (TextView) convertView.findViewById(R.id.tv_stateDetailed);
			vh.img_weaIcon = (ImageView) convertView.findViewById(R.id.img_weaIcon);
			convertView.setTag(vh);
		}else{
			vh = (ViewHodler) convertView.getTag();
		}
		vh.tv_city.setText(list.get(position).getCityname());
		vh.tv_lowTem.setText(list.get(position).getLowTem()+"¡æ   ~");
		vh.tv_highTem.setText(list.get(position).getHighTem()+"¡æ");
		vh.tv_stateDetailed.setText(list.get(position).getStateDetailed());
		vh.img_weaIcon.setImageBitmap(list.get(position).getWeaIcon());
		return convertView;
	}
	
	class ViewHodler{
		public TextView tv_city,tv_lowTem,tv_highTem,tv_stateDetailed;
		public ImageView img_weaIcon;
	}
}
