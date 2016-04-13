package com.zkrkj.peoplehospital.update;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.FileEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

public class NetworkTool{

	/**
	* @param url
	* @return
	* @throws Exception
	*/
	public static String getContent(String url) throws Exception{
		StringBuilder sb = new StringBuilder();

		HttpClient client = new DefaultHttpClient();
		HttpParams httpParams = client.getParams();
		HttpConnectionParams.setConnectionTimeout(httpParams, 3000);
		HttpConnectionParams.setSoTimeout(httpParams, 5000);
		HttpResponse response = client.execute(new HttpGet(url));
		HttpEntity entity = response.getEntity();
		if(entity != null){
			BufferedReader reader = new BufferedReader(new InputStreamReader(entity.getContent(), "UTF-8"), 8192);

			String line = null;
			while((line = reader.readLine()) != null){
				sb.append(line + "\n");
			}
			reader.close();
		}
		return sb.toString();
	}

	public static void postContent(String url, File file) throws Exception{
		HttpClient client = new DefaultHttpClient();
		HttpPost httpPost = new HttpPost(url);
		HttpEntity entity = new FileEntity(file, "binary/octet-stream");
		httpPost.setEntity(entity);
		client.execute(httpPost);
	}
}