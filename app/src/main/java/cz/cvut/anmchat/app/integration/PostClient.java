package cz.cvut.anmchat.app.integration;

import android.os.AsyncTask;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by krejcir on 20.11.14.
 */
public class PostClient extends AsyncTask<String, String, String> {

    @Override
    protected String doInBackground(String... strings) {
        String urlName = strings[0];
        String content = strings[1];
        try {
            HttpClient client = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost(urlName);
            httpPost.addHeader("Accept", "application/json");
            httpPost.addHeader("Content-Type", "application/json");
            httpPost.setEntity(new StringEntity(content));
            HttpResponse response = client.execute(httpPost);
            HttpEntity entity = response.getEntity();
            InputStream inputStream = entity.getContent();
            BufferedReader bReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"), 8);
            StringBuilder sBuilder = new StringBuilder();
            String line = null;
            while ((line = bReader.readLine()) != null) {
                sBuilder.append(line);
            }
            inputStream.close();
            return sBuilder.toString();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }
}
