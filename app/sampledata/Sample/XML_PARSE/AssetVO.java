package com.nonawax.myels.vo;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root
public class AssetVO {
	@Element
	private String name;
	@Element
	private String value;

	public AssetVO(String name, String value){
		this.name = name;
		this.value = value;
	}

	public AssetVO(){
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}
