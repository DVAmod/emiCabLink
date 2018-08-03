/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package emidetail.database.utils;

import java.util.List;
import emidetail.database.beans.Trays;
import emidetail.database.beans.Cover;
import emidetail.database.beans.Connector;
import emidetail.database.beans.Unit;
import emidetail.database.beans.Illustration;
import org.hibernate.HibernateException;
import org.hibernate.Session;

/**
 *
 * @author DVAmod
 */
public class UnitFunctions {
    
    public static List<Unit> getAllUnit() {
        List<Unit> units = null;
        Session s = null;
        try{
            s = Hibernate.getSession();
            s.beginTransaction();
            units = s.createQuery("from Unit").setMaxResults(20).list();
            s.getTransaction().commit();
        }catch(HibernateException e){
            System.err.println("Error db unit");
        }finally{
            Hibernate.closeSession(s);
        }
        return units;
    }
    
    public static List<Unit> getUnitByProfil(int profil_type) {
        
        
        return null;
    }
    
    public static List<Unit> getUnitByTypeStand(int stand_type) {
        
        
        return null;
    }
    
    public static Illustration getIllustrationByUnit(Unit u) {
        Illustration illustr = null;
        Session s = null;
        try{
            s = Hibernate.getSession();
            s.beginTransaction();
            illustr = (Illustration) s.createQuery("from Illustration where id="+u.getIllustrationId()).uniqueResult();
            s.getTransaction().commit();
        }catch(HibernateException e){
            System.err.println("Error db unit");
        }finally{
            Hibernate.closeSession(s);
        }
        return illustr;
    }
    
    
}
