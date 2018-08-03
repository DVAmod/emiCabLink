/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package emidetail.database.utils;


import java.util.List;
import emidetail.database.beans.Construction;
import java.util.ArrayList;
import org.hibernate.HibernateException;
import org.hibernate.Session;

/**
 *
 * @author DVAmod
 */
public class ConstructionFunctions {
    
    public static String getDescriptionFromSource(int source) {
        String desc = "";
        List<Construction> con = new ArrayList<Construction>();
        Session s = null;
        try{
            s = Hibernate.getSession();
            s.beginTransaction();
            con = s.createQuery("from Construction where id = "+source).list();
            s.getTransaction().commit();
            
            if (con.size()>0) {
                desc = con.get(0).getFullName();
            } else {
                desc = "горячее цинкование методом погружения в расплав цинка, толщина цинкового покрытия 55-75 мкм";
            }
            
        }catch(HibernateException e){
            System.err.println("Error db all profil");
            s.getTransaction().rollback();
        }finally{
            Hibernate.closeSession(s);
        }
        return desc;
    }
    
}
