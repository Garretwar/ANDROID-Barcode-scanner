package com.example.myproject.Databases;

import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;


public class zapros_ThirdAct extends Thread {
    String id;

    String Links = ""; // тут має бути лінк

    InputStream is = null;
    String result = null;
    String line = null;

    public void run()
    {
        // создаем лист для отправки запросов
        ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();

        // один параметр, если нужно два и более просто добоовляем также
        nameValuePairs.add(new BasicNameValuePair("ID", id));

        //  подключаемся к php запросу и отправляем в него id
        try {
            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httppost = new HttpPost("http://mydiploma.zzz.com.ua/1.php");
            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs, "UTF-8"));
            HttpResponse response = httpclient.execute(httppost);
            HttpEntity entity = response.getEntity();
            is = entity.getContent();
            Log.e("pass 1", "connection success ");
        } catch (UnsupportedEncodingException e) {
            Log.e("Fail 1", e.toString());
        } catch (UnsupportedOperationException e) {
            Log.e("Fail 1", e.toString());
        } catch (ClientProtocolException e) {
            Log.e("Fail 1", e.toString());
        } catch (IOException e) {
            Log.e("Fail 1", e.toString());
        }

        // получаем ответ от php запроса в формате json
        try
        {
            BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"), 8);
            StringBuilder sb = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
            is.close();
            result = sb.toString();
            Log.e("pass 2", "connection success" + result);
        } catch (Exception e)
        {
            Log.e("Fail 2", e.toString());
        }

       //обрабатываем полученный json
       try
       {
        JSONObject json_data = new JSONObject(result);

           Links=(json_data.getString("Links"));


          Log.e("pass 3",Links);
        }
     catch(Exception e)
       {
         Log.e("Fail 3", e.toString());

      }
    }

    // принемаем id при запуске потока
    public void start(String idp)
    {
        this.id = idp;
        this.start();
    }

    public String resLinks ()
    {
        return  Links;
    }
}
