package com.example.dormmanage.bean;

/**
 * @author 龙
 */
public class BedsManager {
    private Integer dormId;

    private String beda;

    private String bedb;

    private String bedc;

    private String bedd;

    public Integer getDormId() {
        return dormId;
    }

    public void setDormId(Integer dormId) {
        this.dormId = dormId;
    }

    public String getBeda() {
        return beda;
    }

    public void setBeda(String beda) {
        this.beda = beda == null ? null : beda.trim();
    }

    public String getBedb() {
        return bedb;
    }

    public void setBedb(String bedb) {
        this.bedb = bedb == null ? null : bedb.trim();
    }

    public String getBedc() {
        return bedc;
    }

    public void setBedc(String bedc) {
        this.bedc = bedc == null ? null : bedc.trim();
    }

    public String getBedd() {
        return bedd;
    }

    public void setBedd(String bedd) {
        this.bedd = bedd == null ? null : bedd.trim();
    }
}