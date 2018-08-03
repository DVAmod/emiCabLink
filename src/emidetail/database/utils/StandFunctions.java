/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package emidetail.database.utils;

import emidetail.database.beans.Stand;
import emidetail.database.beans.Trays;
import emidetail.filter.SearchData;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;



/**
 *
 * @author DVA
 */
public class StandFunctions {
    
    private static Integer classId = null;
    
    private static final String QUERY_BASED = "from Stand s where";
    
    public static List<Stand> selectStandAll() {
        List<Stand> stands = new ArrayList<Stand>();
        Session s = null;
        try{
            s = Hibernate.getSession();
            s.beginTransaction();
            stands = s.createQuery("from Stand").list();
            s.getTransaction().commit();
        }catch(HibernateException e){
            System.err.println("Error db all stand");
            s.getTransaction().rollback();
        }finally{
            Hibernate.closeSession(s);
        }
        return stands;
    }
    
    
    
    public static List<Stand> selectStandByType(int type) {
         
        List<Stand> stands = new ArrayList<Stand>();
        Session s = null;
        try{
            s = Hibernate.getSession();
            s.beginTransaction();
            stands = s.createQuery("from Stand where type="+type).list();
            s.getTransaction().commit();
        }catch(HibernateException e){
            
             System.err.println("Error db all by type stand");
            s.getTransaction().rollback();
        }finally{
            Hibernate.closeSession(s);
        }
         
         
        return stands; 
    }
    
    
    
    public static List<Stand> selectByFilter(SearchData filter) {
        List<Stand> list = null;
        String criteria = "";
        
        
        
        criteria = QUERY_BASED;
        
        if (classId!=null) {
           // criteria = criteria + " s.classId="+classId;
        }
         
        if (filter.isType()) {
            if (criteria.length() > QUERY_BASED.length()+1)
                criteria = criteria + " and ";
            criteria = criteria + " s.type="+filter.getType();
        }


        if (filter.isSource()) {
            if (criteria.length() > QUERY_BASED.length()+1)
                criteria = criteria + " and ";
            criteria = criteria + " s.constructionId="+filter.getSource();
        }

        SearchData.Gabarit gab = filter.getGabarit();
        if (gab!=null) {

            if (gab.getH() > 0){
                if (criteria.length() > QUERY_BASED.length()+1)
                criteria = criteria + " and ";
                criteria = criteria + " s.height>="+filter.getOneHeight()+" and s.height<="+filter.getTwoHeight();
            }
            if (gab.getW() > 0 || gab.getL() > 0)
                 if (criteria.length() > QUERY_BASED.length()+1)
                criteria = criteria + " and ";
            if (gab.getW() > 0){
                criteria = criteria + " s.width="+gab.getW();
                if (gab.getL() > 0)
                    criteria = criteria + " and ";
            }
            if (gab.getL() > 0){
                criteria = criteria + " s.length>="+filter.getOneLength()+ " and s.length<="+filter.getTwoLength();
            }
            
        }
        if (filter.getThs()!=null)
            if (filter.getThs().length()>1) {
                if (criteria.length() > QUERY_BASED.length()+1)
                criteria = criteria + " and ";
            criteria = criteria + " s.ths='"+filter.getThs()+"'";
            }
        
        Session s = null;
        System.out.println("Addaption sql: "+criteria);
        try{
            s = Hibernate.getSession();
            s.beginTransaction();
            list = s.createQuery(criteria).list();
            s.getTransaction().commit();
        }catch(HibernateException e){
            System.err.println("Error db stand criteria");
            s.getTransaction().rollback();
        }finally{
            Hibernate.closeSession(s);
        }
        if (list == null)
            list = new ArrayList<Stand>();
        return list;
    }
    
    
    public static List<Stand> selectByClass(int class_id){
        
        if (class_id > 0 ) {
            classId = class_id;
        } else {
            classId = null;
        }
        List<Stand> stands = new ArrayList<Stand>();
        Session s = null;
        try{
            s = Hibernate.getSession();
            s.beginTransaction();
            stands = s.createQuery("from Stand where class_id="+class_id).list();
            s.getTransaction().commit();
        }catch(HibernateException e){
            
             System.err.println("Error db all by type stand");
            s.getTransaction().rollback();
        }finally{
            Hibernate.closeSession(s);
        }
         
         
        return stands;
    }
     
     
    public static List<Stand> selectStandByProfil(int profil_type) {
         
        List<Stand> stands = new ArrayList<Stand>();
        Session s = null;
        try{
            s = Hibernate.getSession();
            s.beginTransaction();
            stands = s.createQuery("from Stand where  profil_ids LIKE ('%"+profil_type+"%')").list();
            s.getTransaction().commit();
        }catch(HibernateException e){
             System.err.println("Error db all by profil stand");
            
            s.getTransaction().rollback();
        }finally{
            Hibernate.closeSession(s);
        }
         
         
        return stands; 
     }
    
    
}
