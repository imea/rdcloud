/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.photopartage.tp.maximfluieraru.model;

/**
 *
 * @author Maxim
 */
public class Page {

    private String section;
    private String title;
    private String page_id;

    /**
     *
     * @param section
     * @param title
     */
    public Page(String section, String title) {
        this.section = section;
        this.title = title;
    }

    public Page() {
        this.section = "";
        this.title = "";
    }

    public String getPage_id() {
        return page_id;
    }

    public void setPage_id(String page_id) {
        this.page_id = page_id;
    }

    /**
     *
     * @return
     */
    public String getSection() {
        return section;
    }

    /**
     *
     * @param section
     */
    public void setSection(String section) {
        this.section = section;
    }

    /**
     *
     * @return
     */
    public String getTitle() {
        return title;
    }

    /**
     *
     * @param title
     */
    public void setTitle(String title) {
        this.title = title;
    }

}
