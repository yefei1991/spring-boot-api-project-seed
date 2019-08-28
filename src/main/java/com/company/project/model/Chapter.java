package com.company.project.model;

import javax.persistence.Column;

import com.company.project.core.IdModel;

public class Chapter extends IdModel {
    @Column(name = "novelId")
    private Integer novelid;

    private String url;

    private String title;

    private String prev;

    private String next;

    private Integer sort;

    private Boolean downloaded;

    private String content;

    /**
     * @return novelId
     */
    public Integer getNovelid() {
        return novelid;
    }

    /**
     * @param novelid
     */
    public void setNovelid(Integer novelid) {
        this.novelid = novelid;
    }

    /**
     * @return url
     */
    public String getUrl() {
        return url;
    }

    /**
     * @param url
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * @return title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return prev
     */
    public String getPrev() {
        return prev;
    }

    /**
     * @param prev
     */
    public void setPrev(String prev) {
        this.prev = prev;
    }

    /**
     * @return next
     */
    public String getNext() {
        return next;
    }

    /**
     * @param next
     */
    public void setNext(String next) {
        this.next = next;
    }

    /**
     * @return sort
     */
    public Integer getSort() {
        return sort;
    }

    /**
     * @param sort
     */
    public void setSort(Integer sort) {
        this.sort = sort;
    }

    /**
     * @return downloaded
     */
    public Boolean getDownloaded() {
        return downloaded;
    }

    /**
     * @param downloaded
     */
    public void setDownloaded(Boolean downloaded) {
        this.downloaded = downloaded;
    }

    /**
     * @return content
     */
    public String getContent() {
        return content;
    }

    /**
     * @param content
     */
    public void setContent(String content) {
        this.content = content;
    }
}