package com.company.project.model;

import com.company.project.core.AbstractModel;

public class User extends AbstractModel{
	
    private String username;

    private String password;
    
    private String name;

    /**
     * 是否启用
     */
    private Boolean enable;

    /**
     * @return username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getEnable() {
		return enable;
	}

	public void setEnable(Boolean enable) {
		this.enable = enable;
	}


    public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}