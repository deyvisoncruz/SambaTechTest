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
import javax.faces.event.*;




@ManagedBean(name = "mbUser")
@RequestScoped

public class MbUser implements Serializable  {
    

    private static final long serialVersionUID = 1L;
    public User user = new User();

    public void setUser(User user) {
        this.user = user;
    }
    private List<User> users;
    public MbUser()
    {
       
    }
    public MbUser( int codid)
    {
        this.setUser(user.ListFind(codid));
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
     
     public String test() {
       return "testedsf";
    }
    public String editUser()
    {
        return "/restrict/cadastraruser.faces";
    }
    
     public String addUser() {
        if (/*user.getId() == null ||*/ user.getId() == 0)
        {
            insertUser();
        } else {
            updateUser();
        }
        limpUser();
        return null;
    }
    public void insertUser() {
        int control =0;
        for (User user1 : this.getUsers()) {
            if (this.user.getLogin().equals(user1.getLogin()))
            {
                control =1;
                break;
            }
        }
        if (control ==0)
        {
            userDAO().save(user);
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Gravação efetuada com sucesso, este usuário já está apto a enviar videos!", ""));
        }
        else
        {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Já existe usuário com este login, favor alterar!", ""));        
        }
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
    
    public int getUserId() {
        return user.getId();
    }
    public String edit(){
		return "editaruser";
	}
    public void  setUserForId(int x) {
        this.user = user.ListFind(x);
    }
}
