/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import javax.persistence.Column;
import javax.persistence.Entity;
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
public class Video extends Base  {
    public Video(){
        super();        
    }
    
    
    @Column
    private String name;
    @Column
    private Long binVideo;    

    public Long getBinVideo() {
        return binVideo;
    }

    public void setBinVideo(Long binVideo) {
        this.binVideo = binVideo;
    }

    
    @ManyToOne(optional=false)
    @ForeignKey(name = "VideoUser") 
    @JoinColumn(name="User_Id", referencedColumnName = "Id")
    private User user;
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
