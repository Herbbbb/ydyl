package com.zkrkj.peoplehospital.update;

import java.util.HashMap;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

@SuppressLint("NewApi")
public class CheckVersionTask extends AsyncTask<Void, Integer, Boolean> {

	private int newVerCode = -1;
	private String newVerName = "";
	private Context currentContext;
	private int curVerCode;
	private int type; //
	public static String UPDATE_SERVER;//
	public static String UPDATE_VERJSON;//
	public static String UPDATE_APKNAME;// 
	public static String PACKAGE_NAME;// 
	public static String APP_NAME;
	public static String appName;
	public static String UPDATE_SAVENAME;
	private TaskInf inf;
	private HashMap<String, String> map;

	public CheckVersionTask(Context context, String UPDATE_SERVER,
			String UPDATE_VERJSON, String UPDATE_APKNAME, String PACKAGE_NAME,
			String APP_NAME,String appName,String UPDATE_SAVENAME) {
		this.currentContext = context;
		this.UPDATE_SERVER = UPDATE_SERVER;
		this.UPDATE_VERJSON = UPDATE_VERJSON;
		this.UPDATE_APKNAME = UPDATE_APKNAME;
		this.PACKAGE_NAME = PACKAGE_NAME;
		this.APP_NAME = APP_NAME;
		this.appName = appName;
		this.UPDATE_SAVENAME = UPDATE_SAVENAME;
		this.curVerCode = Config.getVerCode(context, PACKAGE_NAME);//101
		Log.wtf("curVerCode", curVerCode+"");
	}

	public void setListener(TaskInf inf) {
		this.inf = inf;
	}

	private Boolean getServerVerCode() {
		try {
			String verjson = NetworkTool.getContent(UPDATE_SERVER
					+ UPDATE_VERJSON);
			JSONArray array = new JSONArray(verjson);
			if (array.length() > 0) {
				JSONObject obj = array.getJSONObject(0);
				try {
					newVerCode = Integer.parseInt(obj.getString("verCode"));
					newVerName = obj.getString("verName");
					type = Integer.parseInt(obj.getString("type"));
					// application.setNewVername(newVerName);
				} catch (Exception e) {
					newVerCode = -1;
					newVerName = "";
					return false;
				}
			}
		} catch (Exception e) {
			if (e != null) {

			}
			return false;
		}
		return true;
	}

	@Override
	protected void onPreExecute() {
		inf.onPreExecute();
		super.onPreExecute();
	}

	@Override
	protected Boolean doInBackground(Void... params) {
		Log.wtf("getServerVerCode()", getServerVerCode()+"");
		if (getServerVerCode()) {
			if (newVerCode > curVerCode) {
				return true;
			}
		}
		return false;
	}

	@Override
	protected void onPostExecute(Boolean result) {
		// TODO Auto-generated method stub
		super.onPostExecute(result);
		map = new HashMap<String, String>();
		if (result) {
			map.put("newVerName", newVerName);
			map.put("IsUpdate", "true");
			map.put("checkTime", String.valueOf(System.currentTimeMillis()));
			map.put("type", type+"");
			map.put("newVerCode", newVerCode+"");
		}else {
			map.put("IsUpdate", "false");
		}
		inf.isSuccess(map);
	}
}
