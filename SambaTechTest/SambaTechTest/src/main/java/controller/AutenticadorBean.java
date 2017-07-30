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
import Util.FacesContextUtil;
import Util.SessionUtil;
import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import model.User;
import model.dao.HibernateDAO;
import model.dao.InterfaceDAO;


@RequestScoped
@ManagedBean
public class AutenticadorBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private String email;
	private String senha;
        private List<User> lu ;
        private User logado = new User();
        
    
	public String autentica() {
		System.out.println("autentica...");
			
               lu = logado.ListItens();
               for (User user : lu) {
                if (email.equals(user.getLogin()))
                {
                      logado = user;     
                      break;
                }
               }
		if (email.equals(logado.getLogin())&&senha.equals(logado.getPassword()) || email.equals("admin")&&senha.equals("admin")){
			System.out.println("Confirmou  usuario e senha ...");		
			
			//ADD USUARIO NA SESSION
			
			Object b = new Object();
			SessionUtil.setParam("USUARIOLogado", b);
                        if(email.equals("admin")&&senha.equals("admin"))
                        {
                            SessionUtil.setParam("USUARIONome", "Administrador");
                            SessionUtil.setParam("USUARIOId", "10001");
                        }
                        else
                        {
                            SessionUtil.setParam("USUARIONome", logado.getName());
                            SessionUtil.setParam("USUARIOId", logado.getId());
                        }
                FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Login Realizado com sucesso!", ""));
     
		return "./home.faces";

		} else {
			FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_INFO, "Falha ao realizar login, verifique seu usuário e sua senha e tente novamente.", ""));
			return null;

		}

	}

	/**
	 * M�todo que efetua o logout
	 * 
	 * @return
	 */
	public String registraSaida() {

		//REMOVER USUARIO DA SESSION
		
		
		return "./Login?faces-redirect=true";
	}

	// GETTERS E SETTERS


	public String getSenha() {
		return senha;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

}