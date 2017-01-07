package com.tldn1.travellers.backgrounds;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by X on 1/5/2017.
 */

public class BackgroundInsert extends AsyncTask<String,String,String> {
    Context ctx;

    public BackgroundInsert(Context ctx) {
        this.ctx = ctx;
    }

    @Override
    protected String doInBackground(String[] params) {
        String name = params[0];
        String arrival = params[1];
        String howLong = params[2];
        String price = params[3];
        String gender = params[4];
        String age = params[5];
        String language = params[6];


        String data = "";
        String urlParams;
        String server_url="http://10.0.3.2/Travellers/insert.php";
        int tmp;
        try{
            urlParams = "name="+name+"&arrival="+arrival+"&howLong="+howLong+"&price="+price+"&gender="+gender+"&age="+age+"&language="+language;
            URL url = new URL(server_url);
            HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
            httpURLConnection.setDoOutput(true);
            OutputStream os = httpURLConnection.getOutputStream();
            os.write(urlParams.getBytes());
            os.flush();
            os.close();
            InputStream is = httpURLConnection.getInputStream();

            while((tmp=is.read())!=-1){
                data+=(char)tmp;
            }
            is.close();
            httpURLConnection.disconnect();
            return data;

        } catch (MalformedURLException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

    }
    @Override
    protected void onPostExecute(String s) {

        Toast.makeText(ctx,s,Toast.LENGTH_SHORT).show();
    }
}
