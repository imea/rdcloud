/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.photopartage.tp.maximfluieraru.dao;

import java.sql.Connection;

/**
 *
 * @author Maxim
 * @since 2015-10-16
 * @version 1.0
 *
 * @param <T>
 */
public abstract class DAO<T> {
//
//    protected Connection conection;
//
//    /**
//     * Constructor
//     *
//     * @param Conection
//     * @see Connection
//     */
//    public DAO(Connection conection) {
//        
//        this.conection = conection;
//    }
//
//    /**
//     * this.connection get method
//     *
//     * @return Connection
//     */
//    public Connection getConection() {
//        return this.conection;
//    }
//
//    /**
//     * this.connection set method
//     *
//     * @param conection
//     */
//    public void setConection(Connection conection) {
//        this.conection = conection;
//    }

    /**
     * Create method
     *
     * @param object
     * @return boolean
     */
    public abstract boolean insert(T object);

    /**
     * Delete method
     *
     * @param object
     * @return boolean
     */
    public abstract boolean delete(T object);

    /**
     * Update method
     *
     * @param object
     * @return boolean
     */
    public abstract boolean update(T object);

    /**
     *
     * @param id
     * @return T
     */
    public abstract T find(String value);

//	public abstract boolean create(T x);    //INSERT
//	public abstract T read(int id);         //SELECT
//	public abstract T read(String id);      //SELECT
//	public abstract boolean update(T x);    //UPDATE
//	public abstract boolean delete(T x);    //DELETE
//	public abstract List<T> findAll();      //SELECT
}
