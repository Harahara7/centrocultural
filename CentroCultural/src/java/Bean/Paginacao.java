/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bean;

import javax.faces.bean.ManagedBean;

/**
 *
 * @author Yusuke
 */
@ManagedBean
public class Paginacao {

    public String locatarioFisico() {
        return "locatarioFisico";
    }

    public String locatarioJuridico() {
        return "locatarioJuridico";
    }

    public String locatario() {
        return "locatario";
    }

    public String evento() {
        return "evento";
    }
    
    public String chart(){
    return "chart";
    }
    
    public String index() {
        return "index";
    }

    //redirecionar pra pagina de login
    //apenas para testes
    public String login() {
        return "login";
    }
}
