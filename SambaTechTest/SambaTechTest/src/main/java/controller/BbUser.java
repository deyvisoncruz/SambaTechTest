/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import model.dao.HibernateDAO;
import model.dao.InterfaceDAO;
import model.User;
import Util.FacesContextUtil;
import Util.SessionUtil;
import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
/**
 *
 * @author Deyvison
 */
@ManagedBean(name="bbUser")
@RequestScoped
public class BbUser implements Serializable {
    
    private static final long serialVersionUID = 1L;
    private List<User> users;
    private User user = new User();

    public BbUser()
    {
        
    }
    public User getUser() {
        user = user.ListFind((int) SessionUtil.getParam("USUARIOId"));
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<User> getUsers() {
        InterfaceDAO<User> UserDAO = new HibernateDAO<User>(User.class, FacesContextUtil.getRequestSession());
        return UserDAO.getEntities();
    }
    
    public List<User> getListUser() {
         User u = new User();
         List<User> l = u.ListItens();
         for (User user : l) {
            l.remove(user);
        }
         l.add(u.ListFind((int) SessionUtil.getParam("USUARIOId")));
         return l;
    }
    public int getUserId()
    {
        User u = new User();
        return u.ListFind((int) SessionUtil.getParam("USUARIOId")).getId();
    }
    
    public void setUserId( int x)
    {
        
    }
}
