package com.company.project.vo;

import java.io.Serializable;

public class Dic implements Serializable{

	private static final long serialVersionUID = -3187749735467715333L;

	private String label;
	
	private String value;

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	public static Dic build(String label,String value) {
		Dic dic=new Dic();
		dic.setLabel(label);
		dic.setValue(value);
		return dic;
	}

	
}
