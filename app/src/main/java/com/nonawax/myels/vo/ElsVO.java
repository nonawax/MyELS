package com.nonawax.myels.vo;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.ArrayList;
import java.util.List;

@Root
public class ElsVO {
    /*private static final ElsData ourInstance = new ElsData();
    static ElsData getInstance() {
        return ourInstance;
    }*/
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    @Element
    private int id;        //key
    @Element
    private String ElsNm;    //ELS 이름
    @Element
    private String startDt; //청약일(시작일)
    @Element
    private String endDt;   //만기일(종료일)
    @ElementList
    private  List<AssetVO> assetVoList;

    public List<AssetVO> getAssetVoList() {
        return assetVoList;
    }

    public void setAssetVoList(List<AssetVO> assetVoList) {
        this.assetVoList = assetVoList;
    }

    public ElsVO(){
        assetVoList = new ArrayList<AssetVO>();
    }
}
