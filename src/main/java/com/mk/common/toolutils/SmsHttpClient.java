package com.mk.common.toolutils;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.*;


public class SmsHttpClient {
   
      
    public static String post(String url, Map<String, String> params) {
        System.out.println("url\\:\\\\"+url);
        DefaultHttpClient httpclient = new DefaultHttpClient();
        httpclient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 15000);
        httpclient.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, 15000);
        String body = null;  
          
        HttpPost post = postForm(url, params);
          
        body = invoke(httpclient, post);  
      
       httpclient.getConnectionManager().shutdown();  
          
        return body;  
    }  
    
    public static String get(String url) {  
       DefaultHttpClient httpclient = new DefaultHttpClient();
        String body = null;  
          
        HttpGet get = new HttpGet(url);
        body = invoke(httpclient, get);  
          
        httpclient.getConnectionManager().shutdown();  
          
        return body;  
    }  
         
    
    private static String invoke(DefaultHttpClient httpclient,
            HttpUriRequest httpost) {
          
        HttpResponse response = sendRequest(httpclient, httpost);
        String body = paseResponse(response);  
         
        return body;  
    }  
  
    private static String paseResponse(HttpResponse response) {
        HttpEntity entity = response.getEntity();
          
        String charset = EntityUtils.getContentCharSet(entity);
         
        String body = null;  
       try {  
            body = EntityUtils.toString(entity);
        } catch (ParseException e) {
            e.printStackTrace();  
        } catch (IOException e) {  
            e.printStackTrace();  
       }  
          
       return body;  
   }  
 
    private static HttpResponse sendRequest(DefaultHttpClient httpclient,
                                            HttpUriRequest httpost) {
        HttpResponse response = null;
         
        try {  
            response = httpclient.execute(httpost);  
        } catch (ClientProtocolException e) {
            e.printStackTrace();  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
        return response;  
    }  
 
    private static HttpPost postForm(String url, Map<String, String> params){
         
        HttpPost httpost = new HttpPost(url);
       List<NameValuePair> nvps = new ArrayList <NameValuePair>();
          
        Set<String> keySet = params.keySet();  
        for(String key : keySet) {  
           nvps.add(new BasicNameValuePair(key, params.get(key)));
      }  
          
        try {  
           httpost.setEntity(new UrlEncodedFormEntity(nvps, HTTP.UTF_8));
      } catch (UnsupportedEncodingException e) {  
           e.printStackTrace();  
       }  
         
       return httpost;  
   }  
    
    public static void main(String[] args){
        String url = "http://huidu.imike.cn/ots/order/querylist";
    	Map m = new HashMap();
    	m.put("hotelid", "2188");
    	m.put("roomstatus", "1" );
    	m.put("enddateday", "20150813" );
    	m.put("startdate", "20150812" );
    	m.put("startdateday", "20150812" );
    	
    	SmsHttpClient shc =  new  SmsHttpClient();
		String result = shc.post(url, m);
		System.out.println(result);
    		
    }
}  
