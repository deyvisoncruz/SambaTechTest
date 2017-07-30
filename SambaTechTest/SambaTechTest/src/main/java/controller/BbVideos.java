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
import Util.SessionUtil;
import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import model.Video;
/**
 *
 * @author Deyvison
 */
@ManagedBean(name="bbVideos")
@RequestScoped
public class BbVideos implements Serializable {

    
    private static final long serialVersionUID = 1L;
    private List<Video> videos;
    private Video video = new Video();

    public BbVideos()
    {
        
    }
    public List<Video> getVideos() {
        return videos;
    }

    public void setVideos(List<Video> videos) {
        this.videos = videos;
    }

    public Video getVideo() {
        return video;
    }

    public void setVideo(Video video) {
        this.video = video;
    }
    
    

    
    
    
}
