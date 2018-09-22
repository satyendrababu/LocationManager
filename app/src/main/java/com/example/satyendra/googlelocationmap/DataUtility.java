package com.example.satyendra.googlelocationmap;

import android.util.Log;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by DSPL-4 on 7/18/2018.
 */

public class DataUtility {

    public String ContextUrl = "http://192.168.0.101:8080/TaskManager/";

    private String readResponse(HttpResponse httpResponse) {
        InputStream is = null;
        String return_text = "";
        try {
            is = httpResponse.getEntity().getContent();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is));
            String line = "";
            StringBuffer stringBuffer = new StringBuffer();
            while ((line = bufferedReader.readLine())!=null){
                stringBuffer.append(line);
            }
            return_text = stringBuffer.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return return_text;
    }

    public String postLocationData(String[] params){
        String response = null;

        HttpClient httpClient = new DefaultHttpClient();
        HttpPost httpPost = new HttpPost(ContextUrl+"PostLocationData");
        List<NameValuePair> list = new ArrayList<NameValuePair>();
        Log.e("TAG", "datautility: " + params[0]+" , "+params[1]);
        list.add(new BasicNameValuePair("latitude", params[0]));
        list.add(new BasicNameValuePair("longitude", params[1]));
        try {
            httpPost.setEntity(new UrlEncodedFormEntity(list,"utf-8"));
            HttpResponse httpResponse = httpClient.execute(httpPost);
            response = readResponse(httpResponse);
            Log.e("TAG", "response: " + params[0]+" , "+params[1]+" , "+httpResponse+response);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        return response;
    }



    /*public String postAssignedTaskData(String[] params) {
        String response = null;

        HttpClient httpClient = new DefaultHttpClient();
        HttpPost httpPost = new HttpPost(ContextUrl+"PostAssignedTaskData");
        List<NameValuePair> list = new ArrayList<NameValuePair>();
        list.add(new BasicNameValuePair("task_title", params[0]));
        list.add(new BasicNameValuePair("task_desc", params[1]));
        list.add(new BasicNameValuePair("task_start_date", params[2]));
        list.add(new BasicNameValuePair("task_end_date", params[3]));
        list.add(new BasicNameValuePair("task_manager", params[4]));
        list.add(new BasicNameValuePair("task_status", params[5]));
        try {
            httpPost.setEntity(new UrlEncodedFormEntity(list,"utf-8"));
            HttpResponse httpResponse = httpClient.execute(httpPost);
            response = readResponse(httpResponse);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return response;
    }

    public String postGroupTaskData(String[] params) {

        String response = null;

        HttpClient httpClient = new DefaultHttpClient();
        HttpPost httpPost = new HttpPost(ContextUrl+"PostGroupTaskData");
        List<NameValuePair> list = new ArrayList<NameValuePair>();
        list.add(new BasicNameValuePair("task_title", params[0]));
        list.add(new BasicNameValuePair("task_desc", params[1]));
        list.add(new BasicNameValuePair("task_start_date", params[2]));
        list.add(new BasicNameValuePair("task_end_date", params[3]));
        list.add(new BasicNameValuePair("task_members", params[4]));
        list.add(new BasicNameValuePair("task_status", params[5]));
        try {
            httpPost.setEntity(new UrlEncodedFormEntity(list,"utf-8"));
            HttpResponse httpResponse = httpClient.execute(httpPost);
            response = readResponse(httpResponse);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return response;
    }

    public String getAllWorkManagerData() {
        String response = null;
        HttpClient httpClient = new DefaultHttpClient();
        HttpPost httpPost = new HttpPost(ContextUrl+"GetWorkManagerData");

        try {
            HttpResponse httpResponse = httpClient.execute(httpPost);
            response=readResponse(httpResponse);

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();

        } catch (IOException e) {

            e.printStackTrace();

        }

        return response;
    }

    public String getAllWorkManagerData(String[] params) {
        String response = null;
        HttpClient httpClient = new DefaultHttpClient();
        HttpPost httpPost = new HttpPost(ContextUrl+"GetWorkManagerDataByValue");
        List<NameValuePair> list = new ArrayList<NameValuePair>();
        list.add(new BasicNameValuePair("workname", params[0]));
        try {
            httpPost.setEntity(new UrlEncodedFormEntity(list,"utf-8"));
            HttpResponse httpResponse = httpClient.execute(httpPost);
            response = readResponse(httpResponse);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return response;
    }
    public String getMyTaskManagerData() {
        String response = null;
        HttpClient httpClient = new DefaultHttpClient();
        HttpPost httpPost = new HttpPost(ContextUrl+"GetTaskManagerData");

        try {
            HttpResponse httpResponse = httpClient.execute(httpPost);
            response=readResponse(httpResponse);

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();

        } catch (IOException e) {

            e.printStackTrace();

        }

        return response;
    }
    public String getAssignedTaskManagerData() {
        String response = null;
        HttpClient httpClient = new DefaultHttpClient();
        HttpPost httpPost = new HttpPost(ContextUrl+"GetTaskManagerData");

        try {
            HttpResponse httpResponse = httpClient.execute(httpPost);
            response=readResponse(httpResponse);

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();

        } catch (IOException e) {

            e.printStackTrace();

        }

        return response;
    }
    public String getGroupTaskManagerData() {
        String response = null;
        HttpClient httpClient = new DefaultHttpClient();
        HttpPost httpPost = new HttpPost(ContextUrl+"GetTaskManagerData");

        try {
            HttpResponse httpResponse = httpClient.execute(httpPost);
            response=readResponse(httpResponse);

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();

        } catch (IOException e) {

            e.printStackTrace();

        }

        return response;
    }

    public String getMyTaskManagerDataSubItems(String[] params) {
        String response = null;
        HttpClient httpClient = new DefaultHttpClient();
        HttpPost httpPost = new HttpPost(ContextUrl+"GetMyTaskManagerDataByValue");
        List<NameValuePair> list = new ArrayList<NameValuePair>();
        list.add(new BasicNameValuePair("task_title", params[0]));
        try {
            httpPost.setEntity(new UrlEncodedFormEntity(list,"utf-8"));
            HttpResponse httpResponse = httpClient.execute(httpPost);
            response = readResponse(httpResponse);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return response;
    }

    public String getAssignedTaskManagerDataSubItems(String[] params) {
        String response = null;
        HttpClient httpClient = new DefaultHttpClient();
        HttpPost httpPost = new HttpPost(ContextUrl+"GetAssignedTaskManagerDataByValue");
        List<NameValuePair> list = new ArrayList<NameValuePair>();
        list.add(new BasicNameValuePair("task_title", params[0]));
        try {
            httpPost.setEntity(new UrlEncodedFormEntity(list,"utf-8"));
            HttpResponse httpResponse = httpClient.execute(httpPost);
            response = readResponse(httpResponse);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return response;
    }

    public String getGroupTaskManagerDataSubItems(String[] params) {
        String response = null;
        HttpClient httpClient = new DefaultHttpClient();
        HttpPost httpPost = new HttpPost(ContextUrl+"GetGroupTaskManagerDataByValue");
        List<NameValuePair> list = new ArrayList<NameValuePair>();
        list.add(new BasicNameValuePair("task_title", params[0]));
        try {
            httpPost.setEntity(new UrlEncodedFormEntity(list,"utf-8"));
            HttpResponse httpResponse = httpClient.execute(httpPost);
            response = readResponse(httpResponse);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return response;
    }*/
}
