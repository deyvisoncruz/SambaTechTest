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
import java.io.InputStream;
import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.*;
import javax.servlet.http.Part;
import model.Video;
import org.apache.commons.io.IOUtils;

@ManagedBean(name = "mbVideo")
@RequestScoped

public class MbVideo implements Serializable  {

    
    private static final long serialVersionUID = 1L;
    private Video video = new Video();    
    private List<Video> videos;
    private javax.servlet.http.Part file;

    public Part getFile() {
        return file;
    }

    public void setFile(Part file) {
        this.file = file;
    }
   
     public MbVideo()
     {
         
     }
     
    private InterfaceDAO<Video> videoDAO()
    {
        InterfaceDAO<Video> videoDAO = new HibernateDAO<Video>(Video.class, FacesContextUtil.getRequestSession());
        
        return videoDAO;
    }
     
    public String editVideo()
    {
        return "/restrict/videos.faces";
    }
     public String addVideo() {
        if (/*user.getId() == null ||*/ video.getId() == 0)
        {
           
            insertVideo();
        } else {
           
            updateVideo();
        }
        limpVideo();
        return null;
    }
    
    public void insertVideo() {
        try {
           InputStream is = file.getInputStream();
            //código usando Apache Commons IO
            byte[] bytes = IOUtils.toByteArray(is);
            video.setFile(bytes);
            videoDAO().save(video);
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Gravação efetuada com sucesso, este usuário já está apto a enviar videos!", ""));
        
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
            new FacesMessage(FacesMessage.SEVERITY_INFO, "Erro na Gravação!", ""));
        
        }
           
    }
    private void updateVideo() {
       videoDAO().update(video);
       FacesContext.getCurrentInstance().addMessage(null,
               new FacesMessage(FacesMessage.SEVERITY_INFO, "Atualização efetuada com sucesso", ""));
    }
    public void deleteUser(){
        videoDAO().remove(video);    
        FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Remoção realizada com sucesso", ""));
     
    }
    public String limpVideo() {
      
        video = new Video();
        return editVideo();
    }
    
    public Video getVideo() {
        return video;
    }

    public void setVideo(Video video) {
        this.video = video;
    }
    
     public List<Video> getVideos() {
        return videos;
    }

    public void setVideos(List<Video> videos) {
        this.videos = videos;
    }
      
    
}
