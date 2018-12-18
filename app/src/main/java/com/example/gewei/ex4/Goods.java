package com.example.gewei.ex4;

import org.litepal.crud.DataSupport;

public class Goods extends DataSupport {//书类：id,名称，api码
    private int goodsid;
    private String goodsname;
    private int goodscode;

    public int getGoodsid() {
        return goodsid;
    }

    public void setGoodsid(int goodsid) {
        this.goodsid = goodsid;
    }

    public String getGoodsname() {
        return goodsname;
    }

    public void setGoodsname(String goodsname) {
        this.goodsname = goodsname;
    }

    public int getGoodscode() {
        return goodscode;
    }

    public void setGoodscode(int goodscode) {
        this.goodscode = goodscode;
    }
}
