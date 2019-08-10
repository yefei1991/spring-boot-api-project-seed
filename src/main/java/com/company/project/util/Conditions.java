package com.company.project.util;

import tk.mybatis.mapper.entity.Condition;

public class Conditions extends Condition{

	public Conditions(Class<?> entityClass) {
		super(entityClass);
	}

	public static Conditions instance(Class<?> entityClass) {
		Conditions con=new Conditions(entityClass);
		con.and();
		return con;
	}
	
	public Criteria notDeleted() {
		return this.firstCriteria().andEqualTo("deleted",false);
	}
	public Criteria firstCriteria() {
		return this.getOredCriteria().get(0);
	}
}
