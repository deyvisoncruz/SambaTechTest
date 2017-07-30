/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import Util.SessionUtil;
import java.io.Serializable;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import org.hibernate.annotations.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *
 * @author Deyvison
 */

@Entity
@Table(name="Video", schema="public")
@SequenceGenerator(name="SEQ",sequenceName="SEQ_DB_NAME5")
public class Video  implements Serializable {

    public Video(){
           
    }
   
    private static final long serialVersionUID =  1L; 
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="SEQ")
    public int Id;

    public void setId(int Id) {
        this.Id = Id;
    }
    
    @Column
    //@CreationTimestamp
    public Timestamp  Create_at;
     
    @Column
    private String name;
    @Column
    private Long binVideo;    
    
    @Column
    private int user_id;   

    public int getUser_id() {
        User u = new User();
        return u.ListFind((int) SessionUtil.getParam("USUARIOId")).getId();
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }
    
    @Column(name = "videofile")
    private byte[] file;

    public byte[] getFile() {
        return file;
    }

    public void setFile(byte[] file) {
        this.file = file;
    }
    
     public int getId() {
        return this.Id;
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

    
    
    public Long getBinVideo() {
        return binVideo;
    }

    public void setBinVideo(Long binVideo) {
        this.binVideo = binVideo;
    }

    
    
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
   
}
