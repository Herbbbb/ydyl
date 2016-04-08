package com.zkrkj.peoplehospital.update;

import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.util.Log;

public class Config {
	/**
	 * AndroidManifest.xml
	 * @param context
	 * @return
	 */
	public static int getVerCode(Context context, String PACKAGE_NAME) {
		int verCode = -1;
		try {
			verCode = context.getPackageManager().getPackageInfo(PACKAGE_NAME,
					0).versionCode;
		} catch (NameNotFoundException e) {
		}
		return verCode;
	}

	/**
	 * AndroidManifest.xml
	 * @param context
	 * @return
	 */
	public static String getVerName(Context context,String PACKAGE_NAME) {
		String verName = "";
		try {
			verName = context.getPackageManager().getPackageInfo(PACKAGE_NAME,
					0).versionName;
		} catch (NameNotFoundException e) {
		}
		return verName;

	}
	
	
	/**
	 * ver.json
	 * [{"verCode":"2","verName":"ver2"}]
	 */

	/**
	 * strings.xml
	 * @param context
	 * @return
	 */
	public static String getAppName(Context context,int appName) {
		String verName = context.getResources().getText(appName)
				.toString();
		return verName;
	}
	
	/** 
     * 
     *  
     * @param ipInt 
     * @return 
     */  
    public static String int2ip(int ipInt) {  
        StringBuilder sb = new StringBuilder();  
        try {
        	sb.append(ipInt & 0xFF).append(".");  
            sb.append((ipInt >> 8) & 0xFF).append(".");  
            sb.append((ipInt >> 16) & 0xFF).append(".");  
            sb.append((ipInt >> 24) & 0xFF);  
		} catch (Exception e) {
			return String.valueOf(ipInt);
		}
        return sb.toString();  
    }  
  
    /** 
     * 
     *  
     * @param context 
     * @return 
     */  
    public static String getLocalIpAddress(Context context) {  
        try {  
            WifiManager wifiManager = (WifiManager) context  
                    .getSystemService(Context.WIFI_SERVICE);  
            WifiInfo wifiInfo = wifiManager.getConnectionInfo();
            int i = wifiInfo.getIpAddress();  
            return int2ip(i);  
        } catch (Exception ex) { 
            return " " + ex.getMessage();  
        }  
    }  
}
