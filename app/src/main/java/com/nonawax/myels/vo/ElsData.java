package com.nonawax.myels.vo;

public class ElsData {
    /*private static final ElsData ourInstance = new ElsData();
    static ElsData getInstance() {
        return ourInstance;
    }*/
    public int getIdx() {
        return idx;
    }

    public void setIdx(int idx) {
        this.idx = idx;
    }

    public String getElsNm() {
        return ElsNm;
    }

    public void setElsNm(String elsNm) {
        ElsNm = elsNm;
    }

    public String getStartDt() {
        return startDt;
    }

    public void setStartDt(String startDt) {
        this.startDt = startDt;
    }

    public String getEndDt() {
        return endDt;
    }

    public void setEndDt(String endDt) {
        this.endDt = endDt;
    }

    private int idx;        //key
    private String ElsNm;    //ELS 이름
    private String startDt; //청약일(시작일)
    private String endDt;   //만기일(종료일)

}
