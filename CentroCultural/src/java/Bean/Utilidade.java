/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bean;

import java.util.InputMismatchException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author Yusuke
 */
public class Utilidade {
    
    public void mensagemAviso(int numero){
        switch(numero){
            case 1:
               FacesContext.getCurrentInstance()
               .addMessage(null, new FacesMessage
               (FacesMessage.SEVERITY_INFO, "Dados cadastrados"
                       + " com sucesso!", null));
            break;
            case 2:
               FacesContext.getCurrentInstance()
               .addMessage(null, new FacesMessage
               (FacesMessage.SEVERITY_INFO, "Dados foram alterados"
                       + " com sucesso!", null));
            break;
            case 3:
               FacesContext.getCurrentInstance()
               .addMessage(null, new FacesMessage
               (FacesMessage.SEVERITY_FATAL, "As Informações foram excluidas"
                       + " com sucesso!", null));
            break;
            case 4:
               FacesContext.getCurrentInstance()
               .addMessage(null, new FacesMessage
               (FacesMessage.SEVERITY_INFO, "Evento selecionado foi confirmado!", null));
            break;
            case 5:
               FacesContext.getCurrentInstance()
               .addMessage(null, new FacesMessage
               (FacesMessage.SEVERITY_INFO, "Login Efeituado com Sucesso.", null));
            break;
            case 0:
               FacesContext.getCurrentInstance()
               .addMessage(null, new FacesMessage
               (FacesMessage.SEVERITY_ERROR, "Algum campo está inválido!", null));
            break;
            case -1:
               FacesContext.getCurrentInstance()
               .addMessage(null, new FacesMessage
               (FacesMessage.SEVERITY_WARN, "Usuário ou Senha Incorretos!", null));
            break;
            case -2:
               FacesContext.getCurrentInstance()
               .addMessage(null, new FacesMessage
               (FacesMessage.SEVERITY_ERROR, "Algum campo ou Data está invalido!", null));
            break;
            case -3:
               FacesContext.getCurrentInstance()
               .addMessage(null, new FacesMessage
               (FacesMessage.SEVERITY_WARN, "Já existe um Evento confirmado nesta Data!", null));
            break;
            case -4:
               FacesContext.getCurrentInstance()
               .addMessage(null, new FacesMessage
               (FacesMessage.SEVERITY_FATAL, "Evento foi cancelado!", null));
            break;
            default: 
            break;
            
        }//switch
    }//mensagens
    
    public boolean isCPF(String CPF) {
        // considera-se erro CPF's formados por uma sequencia de numeros iguais
        if (CPF.equals("00000000000") || 
            CPF.equals("11111111111") ||
            CPF.equals("22222222222") || CPF.equals("33333333333") ||
            CPF.equals("44444444444") || CPF.equals("55555555555") ||
            CPF.equals("66666666666") || CPF.equals("77777777777") ||
            CPF.equals("88888888888") || CPF.equals("99999999999") ||
            (CPF.length() != 11))
            return(false);
          
        char dig10, dig11;
        int sm, i, r, num, peso;
          
        // "try" - protege o codigo para eventuais erros de conversao de tipo (int)
        try {
        // Calculo do 1o. Digito Verificador
            sm = 0;
            peso = 10;
            for (i=0; i<9; i++) {              
        // converte o i-esimo caractere do CPF em um numero:
        // por exemplo, transforma o caractere '0' no inteiro 0         
        // (48 eh a posicao de '0' na tabela ASCII)         
            num = (int)(CPF.charAt(i) - 48); 
            sm = sm + (num * peso);
            peso = peso - 1;
            }
          
            r = 11 - (sm % 11);
            if ((r == 10) || (r == 11))
                dig10 = '0';
            else dig10 = (char)(r + 48); // converte no respectivo caractere numerico
          
        // Calculo do 2o. Digito Verificador
            sm = 0;
            peso = 11;
            for(i=0; i<10; i++) {
            num = (int)(CPF.charAt(i) - 48);
            sm = sm + (num * peso);
            peso = peso - 1;
            }
          
            r = 11 - (sm % 11);
            if ((r == 10) || (r == 11))
                 dig11 = '0';
            else dig11 = (char)(r + 48);
          
        // Verifica se os digitos calculados conferem com os digitos informados.
            if ((dig10 == CPF.charAt(9)) && (dig11 == CPF.charAt(10)))
                 return(true);
            else return(false);
                } catch (InputMismatchException erro) {
                return(false);
            }
        }
    
    public  String imprimeCPF(String CPF) {
            return(CPF.substring(0, 3) + "." + CPF.substring(3, 6) + "." +
            CPF.substring(6, 9) + "-" + CPF.substring(9, 11));
        }
    
    public boolean isCNPJ(String CNPJ) {
// considera-se erro CNPJ's formados por uma sequencia de numeros iguais
    if (CNPJ.equals("00000000000000") || CNPJ.equals("11111111111111") ||
        CNPJ.equals("22222222222222") || CNPJ.equals("33333333333333") ||
        CNPJ.equals("44444444444444") || CNPJ.equals("55555555555555") ||
        CNPJ.equals("66666666666666") || CNPJ.equals("77777777777777") ||
        CNPJ.equals("88888888888888") || CNPJ.equals("99999999999999") ||
       (CNPJ.length() != 14))
       return(false);
 
    char dig13, dig14;
    int sm, i, r, num, peso;
 
// "try" - protege o código para eventuais erros de conversao de tipo (int)
    try {
// Calculo do 1o. Digito Verificador
      sm = 0;
      peso = 2;
      for (i=11; i>=0; i--) {
// converte o i-ésimo caractere do CNPJ em um número:
// por exemplo, transforma o caractere '0' no inteiro 0
// (48 eh a posição de '0' na tabela ASCII)
        num = (int)(CNPJ.charAt(i) - 48);
        sm = sm + (num * peso);
        peso = peso + 1;
        if (peso == 10)
           peso = 2;
      }
 
      r = sm % 11;
      if ((r == 0) || (r == 1))
         dig13 = '0';
      else dig13 = (char)((11-r) + 48);
 
// Calculo do 2o. Digito Verificador
      sm = 0;
      peso = 2;
      for (i=12; i>=0; i--) {
        num = (int)(CNPJ.charAt(i)- 48);
        sm = sm + (num * peso);
        peso = peso + 1;
        if (peso == 10)
           peso = 2;
      }
 
      r = sm % 11;
      if ((r == 0) || (r == 1))
         dig14 = '0';
      else dig14 = (char)((11-r) + 48);
 
// Verifica se os dígitos calculados conferem com os dígitos informados.
      if ((dig13 == CNPJ.charAt(12)) && (dig14 == CNPJ.charAt(13)))
         return(true);
      else return(false);
    } catch (InputMismatchException erro) {
        return(false);
    }
  }

     public boolean isEmail(String email) {
        boolean resposta = false;
        if (email != null && email.length() > 0) {
            String curinga = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
            Pattern pattern = Pattern.compile(curinga, Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(email);
            if (matcher.matches()) {
                resposta = true;
                System.out.println("Email Valido!");
            }
        }
        return resposta;
    }
    
}
