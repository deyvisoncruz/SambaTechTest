/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

/**
 *
 * @author Deyvison
 */
import model.dao.HibernateDAO;
import model.dao.InterfaceDAO;
import model.User;
import Util.FacesContextUtil;
import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;





public class MbUser implements Serializable  {
    

    private static final long serialVersionUID = 1L;
    private User user = new User();
    private List<User> users;
    public MbUser()
    {
        
    }
    
    
    private InterfaceDAO<User> userDAO()
    {
        InterfaceDAO<User> userDAO = new HibernateDAO<User>(User.class, FacesContextUtil.getRequestSession());
        
        return userDAO;
    }
    
     public String limpUser() {
        user = new User();
        return editUser();
    }
     
    public String editUser()
    {
        return "/restrict/cadastraruser.faces";
    }
    
     public String addUser() {
        if (user == null || user.getId() == 0)
        {
            insertUser();
        } else {
            updateUser();
        }
        limpUser();
        return null;
    }
    private void insertUser() {
       userDAO().save(user);
       FacesContext.getCurrentInstance().addMessage(null,
               new FacesMessage(FacesMessage.SEVERITY_INFO, "Gravação efetuada com sucesso", ""));
   }
   
    private void updateUser() {
       userDAO().update(user);
       FacesContext.getCurrentInstance().addMessage(null,
               new FacesMessage(FacesMessage.SEVERITY_INFO, "Atualização efetuada com sucesso", ""));
   }
    public void deleteUser(){
        userDAO().remove(user);        
    }
    public List<User> getUsers() {       
        users = userDAO().getEntities();
        return users;
    }
    
    public void setUsers(List<User> users) {
        this.users = users;
    }
    
    public User getUser() {
        return user;
    }
    
}
