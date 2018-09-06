package com.nonawax.myels.vo;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

@Root
public class ElsVOList {
	public List<ElsVO> getVoList() {
		return voList;
	}

	public void setVoList(List<ElsVO> voList) {
		this.voList = voList;
	}

	@ElementList(entry = "elsVO", inline = true)
	private List<ElsVO> voList;
}
