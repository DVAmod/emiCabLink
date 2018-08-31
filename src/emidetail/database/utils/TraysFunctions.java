/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package emidetail.database.utils;

import emidetail.database.beans.Illustration;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;

import emidetail.database.beans.Trays;
import emidetail.filter.SearchData;
import emidetail.manager.data.Gabarit;
import org.hibernate.HibernateException;

/**
 *
 * @author DVAmod
 */
public class TraysFunctions {
    
    private static final String QUERY_BASED_STR = "from Trays t where t.articul LIKE '";
    
    private static final String QUERY_BASED = "from Trays t where";
    
    public static List<Trays> selectTrays() {
        List<Trays> trays = new ArrayList<Trays>();
        Session s = null;
        try{
            s = Hibernate.getSession();
            s.beginTransaction();
            trays = s.createQuery("from Trays where type=2").setMaxResults(200).list();
            s.getTransaction().commit();
        }catch(HibernateException e){
            System.err.println("Error db select trays");
        }finally{
            Hibernate.closeSession(s);
        }
        return trays;
    }
    
    public static List<Trays> selectTraysAll() {
        List<Trays> trays = new ArrayList<Trays>();
        Session s = null;
        try{
            s = Hibernate.getSession();
            s.beginTransaction();
            trays = s.createQuery("from Trays").list();
            s.getTransaction().commit();
        }catch(HibernateException e){
            System.err.println("Error db all tray");
            s.getTransaction().rollback();
        }finally{
            Hibernate.closeSession(s);
        }
        return trays;
    }
    
    public static List<Trays> selectTraysByType(int type) {
        
        return null;
    }
    
    public static List<Trays> selectTraysByFilter(SearchData filter) {
        if (filter==null)
            return null;
        List<Trays> list = null;
        String criteria = "";
        if (filter.getArticul()!=null) {
            if (!filter.getArticul().equalsIgnoreCase("")) {
                criteria = criteria.concat(QUERY_BASED_STR);
                criteria = criteria + filter.getArticul() + "' ";
            
            } else {
                criteria = criteria.concat(QUERY_BASED);
                criteria = criteria + " 1=1 ";
            } 
        } else {
            criteria = criteria.concat(QUERY_BASED);
            criteria = criteria + " 1=1 ";
        }
            
            
            if (filter.isIp44()) {
                if (criteria.length() > QUERY_BASED.length()+1)
                    criteria = criteria + " and ";
                criteria = criteria + "t.type=11";
            } else {
                if (filter.isType()) {
                    if (criteria.length() > QUERY_BASED.length()+1)
                        criteria = criteria + " and ";
                    criteria = criteria + " t.type="+filter.getType();
                }
            }
            
            if (filter.isSource()) {
                if (criteria.length() > QUERY_BASED.length()+1)
                    criteria = criteria + " and ";
                criteria = criteria + " t.constructionId="+filter.getSource();
            }
            
            Gabarit gab = filter.getGabarit();
            if (gab!=null) {
                
                if (gab.getH() > 0){
                    if (criteria.length() > QUERY_BASED.length()+1)
                    criteria = criteria + " and ";
                    criteria = criteria + " t.height="+gab.getH();
                }
                if (gab.getW() > 0 || gab.getL() > 0)
                    if (criteria.length() > QUERY_BASED.length()+1)
                    criteria = criteria + " and ";
                if (gab.getW() > 0){
                    criteria = criteria + " t.width="+gab.getW();
                    if (gab.getL() > 0)
                        if (criteria.length() > QUERY_BASED.length()+1)
                        criteria = criteria + " and ";
                }
                if (gab.getL() > 0){
                    criteria = criteria + " t.length="+gab.getL();
                }
            
            }
            
            if (filter.getThs()!=null){
                if (filter.getThs().equalsIgnoreCase("0.0") || filter.getThs().equalsIgnoreCase("0,00") || filter.getThs().equalsIgnoreCase("0") || filter.getThs().equalsIgnoreCase("0,0") || filter.getThs().equalsIgnoreCase("")) {
                    System.out.println("Not tray ths");
                } else {
                    String ths = "1.2";
                    ths = filter.getThs();
                    if (!ths.equalsIgnoreCase("1.0") && !ths.equalsIgnoreCase("2.0") && !ths.equalsIgnoreCase("4.0"))
                        ths = ths.replace(".", ",");
                    System.err.println("ths "+ths);
                    if (criteria.length() > QUERY_BASED.length()+1)
                        criteria = criteria + " and ";
                    criteria = criteria + " t.thickness='"+ths+"'";
                }
            }
            
        Session s = null;
        System.out.println("Addaption sql: "+criteria);
        try{
            s = Hibernate.getSession();
            s.beginTransaction();
            list = s.createQuery(criteria).list();
            s.getTransaction().commit();
        }catch(HibernateException e){
            System.err.println("Error db tray criteria");
            s.getTransaction().rollback();
        }finally{
            Hibernate.closeSession(s);
        }
        return list;
    }
    
    public static List selectTraysByIP() {
        
        return null;
    }

    public static Illustration getIllustrationByTray(Trays tray) {
        Illustration illustr = null;
        Session s = null;
        if (tray.getIllustrationId()!=null)
            if (tray.getIllustrationId()!=0)
                try{
                    s = Hibernate.getSession();
                    s.beginTransaction();
                    illustr = (Illustration) s.createQuery("from Illustration where id="+tray.getIllustrationId()).uniqueResult();
                    s.getTransaction().commit();
                }catch(HibernateException e){
                    System.err.println("Error db unit");
                }finally{
                    Hibernate.closeSession(s);
                }
        return illustr;
    }
    
}
