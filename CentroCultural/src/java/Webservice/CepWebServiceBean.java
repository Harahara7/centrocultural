package Webservice;

import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import modelo.CepWebService;

@ManagedBean
@SessionScoped
public class CepWebServiceBean {
    
    private String cepCampo;
    private CepWebService cep;
    
    public void buscarCEP() throws MalformedURLException, IOException{
    
        StringBuilder resposta = new StringBuilder();
        //Serviço deve ser requisitado via URL
        URL url = new URL("http://"
                + "ws.matheuscastiglioni.com.br"
                + "/ws/cep/find/"+cepCampo+"/json/");
        HttpURLConnection conexao = (HttpURLConnection) url.openConnection();
        conexao.setRequestMethod("GET");
        conexao.setConnectTimeout(60000);
        BufferedReader dados = new BufferedReader(new InputStreamReader(conexao.getInputStream()));
        String linha;
        while((linha = dados.readLine()) != null){
         resposta.append(linha);
        }//while
        
        dados.close();
        
        Gson g = new Gson();
        CepWebService cep = new CepWebService();
        setCep(g.fromJson(resposta.toString(), CepWebService.class));
        
        
    }//buscarCEP

    public String getCepCampo() {
        return cepCampo;
    }

    /**
     * @param cepCampo the cepCampo to set
     */
    public void setCepCampo(String cepCampo) {
        this.cepCampo = cepCampo;
    }

    /**
     * @return the cep
     */
    public CepWebService getCep() {
        return cep;
    }

    /**
     * @param cep the cep to set
     */
    public void setCep(CepWebService cep) {
        this.cep = cep;
    }
    
}
