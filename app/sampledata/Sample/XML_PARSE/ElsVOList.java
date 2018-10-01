package com.nonawax.myels.vo;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

@Root
public class ElsVOList {


	@ElementList
	private List<ElsVO> voList;

	public List<ElsVO> getVoList() {
		return voList;
	}
	public List<ElsVO> setVoList(List<ElsVO> voList) {
		this.voList = voList;
		return voList;
	}

	public ElsVOList(List<ElsVO> list){
		voList = setVoList(list);
	}

	public ElsVOList(){
	}
}
