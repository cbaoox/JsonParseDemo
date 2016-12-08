package com.google.jsonparsedemo;

import android.content.Context;
import android.os.Handler;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

/**
 * Created by Administrator on 2016/11/25 0025.
 * 连接网络请求数据，这里使用HttpURLConnection
 */
public class JsonParseUtils extends Thread{
    private TextView textView,textView2;
    private ImageView imageView;
    private Handler handler;
    private URL url = null;
    private String jsonData = "";
    private InputStreamReader in = null;
    private Context mcontext;
    private String test1 = "http://open.iciba.com/dsapi";
    private String test2 = "http://121.42.189.191/harricane/online/findGoodsBySearch.do?shopUnique=0830201613441210001";

    public JsonParseUtils(TextView textView,TextView textView2,ImageView imageView, Handler handler,Context context){
        this.textView = textView;
        this.textView2 = textView2;
        this.imageView = imageView;
        this.handler = handler;
        mcontext = context;
    }

    public JsonParseUtils(){
        super();
    }

    @Override
    public void run() {
        try{
            //第一步：创建url对象
            url = new URL(test1);

            //第二步：根据URL对象获取HttpURLConnection对象
            HttpURLConnection conn = (HttpURLConnection)url.openConnection();

            //第三步：为HttpURLConnection对象设置参数
            conn.setDoInput(true);
            conn.setConnectTimeout(5000);
            conn.setRequestMethod("GET");

            //第四步：开始获取数据

            in = new InputStreamReader(conn.getInputStream());
            BufferedReader br = new BufferedReader(in);
            final StringBuffer stringBuffer = new StringBuffer();
            String inputLine = null;
            while ((inputLine = br.readLine()) != null){
                stringBuffer.append(inputLine);
                System.out.print("the callbackData"+stringBuffer.toString());
            }
            in.close();
            conn.disconnect();
            jsonData = stringBuffer.toString();

            handler.post(new Runnable() {
                @Override
                public void run() {
                    try {
                        textView.setText(parseJsonByOrgJson(jsonData).getTranslation());
                        ImageLoader.with(parseJsonByOrgJson(jsonData).picture2,imageView,mcontext);
                        textView2.setText(parseJsonByGson(jsonData).toString());
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    //解析方法一:org.json
    /**
     * 通过org.json解析json
     * @param jsonStr json字符串
     * @throws JSONException;
     */
    public static Sentence parseJsonByOrgJson(String jsonStr) throws JSONException{
        // 使用该方法解析思路，遇到大括号用JsonObject，中括号用JsonArray
        // 第一个是大括号{}
        JSONObject jsonObject = new JSONObject(jsonStr);

        Sentence sentence = new Sentence();

        String sid  = jsonObject.getString("sid");
        String tts  = jsonObject.getString("tts");
        String content  = jsonObject.getString("content");
        String note  = jsonObject.getString("note");
        String love  = jsonObject.getString("love");
        String translation  = jsonObject.getString("translation");
        String picture  = jsonObject.getString("picture");
        String picture2  = jsonObject.getString("picture2");
        String caption  = jsonObject.getString("caption");
        String dateline  = jsonObject.getString("dateline");
        String s_pv  = jsonObject.getString("s_pv");
        String sp_pv  = jsonObject.getString("sp_pv");
        String fenxiang_img  = jsonObject.getString("fenxiang_img");

        JSONArray jsonArray = jsonObject.getJSONArray("tags");
        List<Sentence.Tag> tags = new ArrayList<Sentence.Tag>();
        for(int i=0;i<jsonArray.length();i++){
            Sentence.Tag tag = new Sentence.Tag();
            // jsonArray里的每一项都是JsonObject
            JSONObject jsonObj = jsonArray.getJSONObject(i);

            tag.id = 1;
            tag.name = "test";
            tags.add(tag);
        }

        sentence.sid = sid;
        sentence.tts = tts;
        sentence.content = content;
        sentence.note = note;
        sentence.love = love;
        sentence.translation = translation;
        sentence.picture = picture;
        sentence.picture2 = picture2;
        sentence.caption = caption;
        sentence.dateline = dateline;
        sentence.s_pv = s_pv;
        sentence.sp_pv = sp_pv;
        sentence.fenxiang_img = fenxiang_img;

        sentence.tags = tags;

        return sentence;
    }

    /**
     * @param jsonStr
     * @return
     */
    public static Sentence parseJsonByGson(String jsonStr){
        Gson gson = new Gson();
        Sentence sentence = gson.fromJson(jsonStr,Sentence.class);
        return sentence;
    }
    public static Goods parseGoods(String jsonStr){
        Gson gson = new Gson();
        Goods good = gson.fromJson(jsonStr,Goods.class);
        return good;
    }
}
