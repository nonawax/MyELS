package com.nonawax.myels.code;

import com.nonawax.myels.vo.AssetVO;

import java.util.ArrayList;

public class AssetSourceCode {
	ArrayList<AssetVO> assetList = null;
	public AssetSourceCode() {
		assetList = new ArrayList<AssetVO>();
		assetList.add(new AssetVO("KOSPI","K"));
		assetList.add(new AssetVO("DEX","D"));
		assetList.add(new AssetVO("HSCEI","H"));
		assetList.add(new AssetVO("KOSPI_200","K2"));
	}
	public ArrayList<AssetVO> getList(){
		return assetList;
	}
}
