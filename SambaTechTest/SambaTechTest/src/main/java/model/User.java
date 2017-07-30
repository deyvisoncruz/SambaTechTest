/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import Util.HibernateUtil;
import java.io.Serializable;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.annotations.ForeignKey;

/**
 *
 * @author Deyvison
 */
@Entity
@Table(name="User", schema="public")
@SequenceGenerator(name="SEQ",sequenceName="SEQ_DB_NAME3")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="SEQ")
    public int Id;
    
    @Column
    //@CreationTimestamp
    public Timestamp  Create_at;
    
    
    
    
    private static final long serialVersionUID =  1L; 
    
    @Column
    private String name;
    @Column
    private String login;
    @Column
    private String password;
   

    public User(){
              
    }
    
    public int getId() {
        return this.Id;
    }
    
    
    
    /**
     * @param create_at the create_at to set
     */
    public void setCreate_at(Timestamp  create_at) {
        this.Create_at = create_at;
    }

    

    
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login.toLowerCase();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
 
      
    public int IdEdit() {
        return this.Id;
    } 
    
    public List ListItens() {
        SessionFactory sf =HibernateUtil.getSessionFactory();
        org.hibernate.Session session= sf.openSession();
        Criteria crit = session.createCriteria(User.class);
        
        List results = crit.list();
    return results;
    }

    public User ListFind(int i) {

        List<User> l = this.ListItens();
        User aux = new User();
        for (User t : l) {
            if(t.getId()==i )
            {
                aux =t;
                break;
            }
        }
        return  aux;
    }
    public void setId(int Id) {
        this.Id = Id;
    }
}

