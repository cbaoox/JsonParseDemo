package com.google.jsonparsedemo;

import java.util.List;

/**
 * Created by Administrator on 2016/11/25 0025.
 */
public class Sentence {
    public String caption;
    public String content;
    public String dateline;
    public String fenxiang_img;
    public String love;
    public String note;
    public String picture;
    public String picture2;
    public String s_pv;
    public String sid;
    public String sp_pv;
    public String translation;
    public String tts;

    public List<Tag> tags;

    public String getTranslation(){
        return content+'\n'+note;
    }

    @Override
    public String toString() {
        return "caption=" + caption + "\n" +
                "content=" + content+ "\n" +
                "dateline=" + dateline+ "\n" +
                "fenxiang_img=" + fenxiang_img+ "\n" +
                "love=" + love + "\n" +
                "note=" + note + "\n" +
                "picture=" + picture + "\n" +
                "picture2=" + picture2 + "\n" +
                "s_pv=" + s_pv + "\n" +
                "sid=" + sid + "\n" +
                "sp_pv="+ sp_pv + "\n" +
                "translation=" + translation + "\n" +
                "tts=" + tts + "\n" +
                "tags=" + tags + "\n";
    }
    static class Tag{
        public int id;
        public String name;

        @Override
        public String toString() {

            return "id="+id+"\n"+"name="+name+"\n";
        }
    }

}
