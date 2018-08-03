/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package emidetail.database.utils;

import emidetail.database.beans.Types;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;

/**
 *
 * @author DVAmod
 */
public class TypesFunctions {
    
    public static List<Types> getTypeList(String unique) {
        
        List<Types> types = new ArrayList<Types>();
        Session s = null;
        try{
            s = Hibernate.getSession();
            s.beginTransaction();
            types = s.createQuery("from Types where type='"+unique+"'").list();
            s.getTransaction().commit();
        }catch(HibernateException e){
            
             System.err.println("Error db all by type types");
            s.getTransaction().rollback();
        }finally{
            Hibernate.closeSession(s);
        }
        return types;
    }
    
}
