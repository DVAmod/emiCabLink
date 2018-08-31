/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package emidetail.database.utils;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;

import emidetail.database.beans.Profil;
import emidetail.database.beans.Stand;
import emidetail.filter.SearchData;
import emidetail.manager.data.Gabarit;
import org.hibernate.HibernateException;

/**
 *
 * @author DVA
 */
public class ProfilFunctions {
    
    private static Integer classId = null;
    
    private static final String QUERY_BASED = "from Profil p where";
    
    public static List<Profil> selectProfilAll() {
        List<Profil> profils = new ArrayList<Profil>();
        Session s = null;
        try{
            s = Hibernate.getSession();
            s.beginTransaction();
            profils = s.createQuery("from Profil").list();
            s.getTransaction().commit();
        }catch(HibernateException e){
            System.err.println("Error db all profil");
            s.getTransaction().rollback();
        }finally{
            Hibernate.closeSession(s);
        }
        return profils;
    }
    
    
    
    public static List<Profil> selectByFilter(SearchData filter) {
        
        List<Profil> list = null;
        String criteria = "";
        
        criteria = QUERY_BASED;
        
        if (classId!=null) {
            //criteria = criteria + " p.classId="+classId;
        }
         
        if (filter.isType()) {
            if (criteria.length() > QUERY_BASED.length()+1)
                criteria = criteria + " and ";
            criteria = criteria + " p.type="+filter.getType();
        }


        if (filter.isSource()) {
            if (criteria.length() > QUERY_BASED.length()+1)
                criteria = criteria + " and ";
            criteria = criteria + " p.constructionId="+filter.getSource();
        }

        Gabarit gab = filter.getGabarit();
        if (gab!=null) {

            if (gab.getH() > 0){
                if (criteria.length() > QUERY_BASED.length()+1)
                criteria = criteria + " and ";
                criteria = criteria + " p.height="+gab.getH();
            }
            if (gab.getW() > 0 || gab.getL() > 0)
                if (criteria.length() > QUERY_BASED.length()+1)
                criteria = criteria + " and ";
            if (gab.getW() > 0){
                criteria = criteria + " p.width="+gab.getW();
                if (gab.getL() > 0)
                    criteria = criteria + " and ";
            }
            if (gab.getL() > 0){
                criteria = criteria + " p.length>="+filter.getOneLength()+ " and p.length<="+filter.getTwoLength();
            }
            
        }
        if (filter.getThs()!=null)
            if (filter.getThs().length()>1) {
                if (criteria.length() > QUERY_BASED.length()+1)
                criteria = criteria + " and ";
            criteria = criteria + " p.ths='"+filter.getThs()+"'";
            }
        
        Session s = null;
        System.out.println("Addaption sql: "+criteria);
        try{
            s = Hibernate.getSession();
            s.beginTransaction();
            list = s.createQuery(criteria).list();
            s.getTransaction().commit();
        }catch(HibernateException e){
            System.err.println("Error db profil criteria");
            s.getTransaction().rollback();
        }finally{
            Hibernate.closeSession(s);
        }
        if (list == null)
            list = new ArrayList<Profil>();
        return list;
    }
    
    
    public static List<Profil> selectProfilByType(int type) {
        
        List<Profil> profils = new ArrayList<Profil>();
        Session s = null;
        try{
            s = Hibernate.getSession();
            s.beginTransaction();
            profils = s.createQuery("from Profil where type="+type).list();
            s.getTransaction().commit();
        }catch(HibernateException e){
            System.err.println("Error db all profil");
            s.getTransaction().rollback();
        }finally{
            Hibernate.closeSession(s);
        }
        return profils;
    }
    
    
    public static List<Profil> selectProfilByClass(int typeClass) {
        
        if (typeClass > 0 ) {
            classId = typeClass;
        } else {
            classId = null;
        }
        
        List<Profil> profils = new ArrayList<Profil>();
        Session s = null;
        try{
            s = Hibernate.getSession();
            s.beginTransaction();
            profils = s.createQuery("from Profil where class_id="+typeClass).list();
            s.getTransaction().commit();
        }catch(HibernateException e){
            System.err.println("Error db all profil");
            s.getTransaction().rollback();
        }finally{
            Hibernate.closeSession(s);
        }
        return profils;
    }
    
    
    public static List<Profil> selectProfilByStand(Stand stand) {
        List<Profil> profils = new ArrayList<Profil>();
        Session s = null;
        String profil_id = "";
        try{
            s = Hibernate.getSession();
            
            if (stand != null)
                if (stand.getProfilIds().length() > 0 ) {
                    profil_id = stand.getProfilIds();
                    System.out.println("Yes find profil");
                }

            s.beginTransaction();
            profils = s.createQuery("from Profil "+
                    "where type in ("+profil_id+")").list();
            s.getTransaction().commit();
            s.flush();
        
        }catch(Exception e){ 
            System.err.println("Error from profil get by stand");
            s.getTransaction().rollback();
        } finally {
            Hibernate.closeSession(s);
        }
        
        return profils;
    }
}