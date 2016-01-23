/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.photopartage.tp.maximfluieraru.listner;

import com.photopartage.tp.maximfluieraru.dao.implementaion.CommentDAO;
import com.photopartage.tp.maximfluieraru.dao.implementaion.PhotoDAO;
import com.photopartage.tp.maximfluieraru.dao.implementaion.UserFriendDAO;

import com.photopartage.tp.maximfluieraru.model.Photo;
import com.photopartage.tp.maximfluieraru.model.User;

import java.util.ArrayList;
import javax.servlet.ServletContextAttributeEvent;
import javax.servlet.ServletContextAttributeListener;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletRequestAttributeEvent;
import javax.servlet.ServletRequestAttributeListener;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;


/**
 * Web application lifecycle listener.
 *
 * @author Maxim
 */
public class ContextListner implements ServletContextListener, ServletContextAttributeListener, HttpSessionListener, HttpSessionAttributeListener, ServletRequestListener, ServletRequestAttributeListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }

    @Override
    public void attributeAdded(ServletContextAttributeEvent scae) {

    }

    @Override
    public void attributeRemoved(ServletContextAttributeEvent scae) {

    }

    @Override
    public void attributeReplaced(ServletContextAttributeEvent scae) {

    }

    @Override
    public void sessionCreated(HttpSessionEvent hse) {

        ArrayList<Photo> listPhoto = new PhotoDAO().findAllPublic();
        CommentDAO cdao = new CommentDAO();
        for (Photo photo : listPhoto) {
            photo.setPhoto_comments(cdao.findAllbyPhoto(photo.getPhoto_id()));
        }
        hse.getSession().setAttribute("publicPhotos", listPhoto);

    }

    @Override
    public void sessionDestroyed(HttpSessionEvent hse) {

    }

    @Override
    public void attributeAdded(HttpSessionBindingEvent hsbe) {

        if ("user".equals(hsbe.getName())) {
            User user = (User) hsbe.getValue();
            ArrayList<Photo> listPhoto = new PhotoDAO().findAllByUser(user);
            CommentDAO cdao = new CommentDAO();
            for (Photo photo : listPhoto) {
                photo.setPhoto_comments(cdao.findAllbyPhoto(photo.getPhoto_id()));
            }
            
            user.getUser_friends().addAll((new UserFriendDAO()).findAllFriends(user.getUser_email()));
            user.getUser_friends_RecivedInvitation().addAll((new UserFriendDAO()).findAllRecivedInvitation(user.getUser_email()));
            user.getUser_friends_UnconfirmedInvitation().addAll((new UserFriendDAO()).findAllUnconfirmedInvitation(user.getUser_email()));
          
            
            
            user.setUser_photos(listPhoto);
            listPhoto = new PhotoDAO().findAllProtectedByUser(user);
            
            for (Photo photo : listPhoto) {
                photo.setPhoto_comments(cdao.findAllbyPhoto(photo.getPhoto_id()));
            }
           
            user.setUser_friends_photos(listPhoto);
        }

    }

    @Override
    public void attributeRemoved(HttpSessionBindingEvent hsbe) {

    }

    @Override
    public void attributeReplaced(HttpSessionBindingEvent hsbe) {

    }

    @Override
    public void requestDestroyed(ServletRequestEvent sre) {

    }

    @Override
    public void requestInitialized(ServletRequestEvent sre) {

    }

    @Override
    public void attributeAdded(ServletRequestAttributeEvent srae) {

    }

    @Override
    public void attributeRemoved(ServletRequestAttributeEvent srae) {

    }

    @Override
    public void attributeReplaced(ServletRequestAttributeEvent srae) {

    }
}
