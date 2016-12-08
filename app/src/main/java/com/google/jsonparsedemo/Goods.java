package com.google.jsonparsedemo;
/**
 * Created by Administrator on 2016/12/10 0010.
 */
public class Goods {
    public String goods_hits;
    public int goods_id;
    public Double goods_sale_price;
    public String goods_name;
    public String goods_picturepath;
    public String goods_barcode;
    public int good_sold;

    @Override
    public String toString() {
        return "Goods{" +
                "good_sold='" + good_sold + '\'' +
                ", goods_hits='" + goods_hits + '\'' +
                ", goods_id='" + goods_id + '\'' +
                ", goods_sale_price='" + goods_sale_price + '\'' +
                ", goods_name='" + goods_name + '\'' +
                ", goods_picturepath='" + goods_picturepath + '\'' +
                ", goods_barcode='" + goods_barcode + '\'' +
                '}';
    }

}
