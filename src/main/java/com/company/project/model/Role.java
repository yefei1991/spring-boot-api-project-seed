package com.company.project.model;

import com.company.project.core.AbstractModel;
import javax.persistence.*;

public class Role extends AbstractModel {
    private String name;

    @Column(name = "roleDesc")
    private String roledesc;

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
     * @return roleDesc
     */
    public String getRoledesc() {
        return roledesc;
    }

    /**
     * @param roledesc
     */
    public void setRoledesc(String roledesc) {
        this.roledesc = roledesc;
    }
}