/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package emidetail.database.utils;

import emidetail.database.beans.Stand;
import emidetail.database.beans.Bracket;
import emidetail.filter.SearchData;
import emidetail.manager.data.Gabarit;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;

/**
 *
 * @author DVAmod
 */
public class BracketFunctions {
    
    private static Integer classId = null;

    
    private static final String QUERY_BASED = "from Bracket b where";
    
    public static List<Bracket> selectBracketAll() {
        List<Bracket> brackets = new ArrayList<Bracket>();
        Session s = null;
        try{
            s = Hibernate.getSession();
            s.beginTransaction();
            brackets = s.createQuery("from Bracket ").list();
            s.getTransaction().commit();
        }catch(HibernateException e){
            
             System.err.println("Error db all by type braket");
            s.getTransaction().rollback();
        }finally{
            Hibernate.closeSession(s);
        }
        return brackets;
    }
    
    public static List<Bracket> selectByFilter(SearchData filter) {
        
        List<Bracket> list = null;
        String criteria = "";
        
        criteria = QUERY_BASED;
        
        if (classId!=null) {
            //criteria = criteria + " b.classId="+classId;
        }
         
        if (filter.getArticul()!= null) {
            if (filter.getArticul().length()>0){
                if (criteria.length() > QUERY_BASED.length()+1)
                    criteria = criteria + " and ";
                criteria = criteria + " b.type='"+filter.getArticul()+"'";
            }
        }


        if (filter.isSource()) {
            if (criteria.length() > QUERY_BASED.length()+1)
                criteria = criteria + " and ";
            criteria = criteria + " b.constructionId="+filter.getSource();
        }

        Gabarit gab = filter.getGabarit();
        if (gab!=null) {

            if (gab.getH() > 0){
                if (criteria.length() > QUERY_BASED.length()+1)
                criteria = criteria + " and ";
                criteria = criteria + " b.height="+gab.getH();
            }
            if (gab.getW() > 0 || gab.getL() > 0)
                 if (criteria.length() > QUERY_BASED.length()+1)
                criteria = criteria + " and ";
            if (gab.getW() > 0){
                criteria = criteria + " b.width="+gab.getW();
                if (gab.getL() > 0)
                    criteria = criteria + " and ";
            }
            if (gab.getL() > 0){
                criteria = criteria + " b.length>="+filter.getOneLength()+" and b.length<="+filter.getTwoLength();
            }
            
        }
        if (filter.getThs()!=null)
            if (filter.getThs().length()>1) {
                if (criteria.length() > QUERY_BASED.length()+1)
                criteria = criteria + " and ";
            criteria = criteria + " b.ths='"+filter.getThs()+"'";
            }
        
        Session s = null;
        System.out.println("Addaption sql: "+criteria);
        try{
            s = Hibernate.getSession();
            s.beginTransaction();
            list = s.createQuery(criteria).list();
            s.getTransaction().commit();
        }catch(HibernateException e){
            System.err.println("Error db braket criteria");
            s.getTransaction().rollback();
        }finally{
            Hibernate.closeSession(s);
        }
        if (list == null)
            list = new ArrayList<Bracket>();
        return list;
    }
    
    public static List<Bracket> selectBracketByClass(int class_id) {
        
        if (class_id > 0 ) {
            classId = class_id;
        } else {
            classId = null;
        }
        
        List<Bracket> brackets = new ArrayList<Bracket>();
        Session s = null;
        try{
            s = Hibernate.getSession();
            s.beginTransaction();
            brackets = s.createQuery("from Bracket where class_id ="+class_id).list();
            s.getTransaction().commit();
        }catch(HibernateException e){
            
             System.err.println("Error db all by type braket");
            s.getTransaction().rollback();
        }finally{
            Hibernate.closeSession(s);
        }
        return brackets;
    }
    
    public static List<Bracket> selectBracketByProfil(int profil_type) {
        
        List<Bracket> brackets = new ArrayList<Bracket>();
        Session s = null;
        try{
            s = Hibernate.getSession();
            s.beginTransaction();
            brackets = s.createQuery("from Bracket where profil_ids LIKE ('%"+profil_type+"%')").list();
            s.getTransaction().commit();
        }catch(HibernateException e){
            
             System.err.println("Error db all by type braket");
            s.getTransaction().rollback();
        }finally{
            Hibernate.closeSession(s);
        }
        return brackets;
    }
    
}
