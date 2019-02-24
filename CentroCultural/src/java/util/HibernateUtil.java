package util;

import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.SessionFactory;

public class HibernateUtil {
    //apaga o final
    private static SessionFactory sessionFactory;
    //elimina o static{} e joga o try catch pro Get
    public static SessionFactory getSessionFactory() {
        
          try {
            //dentro de configure, caso o pacote seja alterado,
            //especifique onde o cfg está
            sessionFactory = new AnnotationConfiguration().configure("/hibernate/hibernate.cfg.xml").buildSessionFactory();
        } catch (Throwable ex) {
            // Log the exception. 
            System.err.println("Criação da Fábrica de Sessão Falhou! " + ex);
            throw new ExceptionInInitializerError(ex);
        }
        
        return sessionFactory;
    }
}
