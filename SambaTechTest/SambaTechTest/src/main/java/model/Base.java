/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

/**
 *
 * @author Deyvison
 * 
 */
@MappedSuperclass
public  abstract class Base implements Serializable {
    

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="SEQ")
    protected int Id;
    
    @Column
    //@CreationTimestamp
    protected Timestamp  Create_at;
    
    @Column
    //@CreationTimestamp
    protected Timestamp   Update_at;
    
    @Column
    //@CreationTimestamp
    protected Timestamp  Delete_at;
    
    
    

    
    
    public Base(){
        
        Timestamp data = new Timestamp(System.currentTimeMillis());
        
        Create_at = data;
        Update_at = null; 
        Delete_at = null;
        
    }
    
    public int getId() {
        return this.Id;
    }
    
    public Timestamp getDelete_at() {
        return Delete_at;
    }
    
    public String getDelete_atToString()
    {
        String aux ="";
        if (Delete_at != null)
            aux = (String) (new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(this.getDelete_at()));
        
        return aux;
    }

    public void setDelete_at(Timestamp Delete_at) {
        this.Delete_at = Delete_at;
        
    }
    
    /**
     * @return the create_at
     */
    public Timestamp  getCreate_at() {
        return Create_at;
    }

    public String getCreate_atToString()
    {
        String aux ="";
        if (Create_at != null)
            aux = (String) (new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(this.getCreate_at()));
        
        return aux;
    }
    /**
     * @param create_at the create_at to set
     */
    public void setCreate_at(Timestamp  create_at) {
        this.Create_at = create_at;
    }

    /**
     * @return the update_at
     */
    public Timestamp getUpdate_at() {
        return Update_at;
    }

    public String getUpdate_atToString()
    {
        String aux ="";
        if (Update_at != null)
            aux = (String) (new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(this.getUpdate_at()));
        
        return aux;
    }
    /**
     * @param update_at the update_at to set
     */
    public void setUpdate_at(Timestamp update_at) {
        this.Update_at = update_at;
    }

}