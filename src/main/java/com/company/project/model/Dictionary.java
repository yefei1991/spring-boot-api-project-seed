package com.company.project.model;

import com.company.project.core.AbstractModel;
import javax.persistence.*;

public class Dictionary extends AbstractModel {
    private String name;

    private String code;

    @Column(name = "optionCode")
    private String option;

    @Column(name = "optionDesc")
    private String optiondesc;

    /**
	 * 排序
	 */
	private Integer sort;

    public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	/**
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return code
     */
    public String getCode() {
        return code;
    }

    /**
     * @param code
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * @return option
     */
    public String getOption() {
        return option;
    }

    /**
     * @param option
     */
    public void setOption(String option) {
        this.option = option;
    }

    /**
     * @return optionDesc
     */
    public String getOptiondesc() {
        return optiondesc;
    }

    /**
     * @param optiondesc
     */
    public void setOptiondesc(String optiondesc) {
        this.optiondesc = optiondesc;
    }

}