/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package emidetail.database.utils;

import emidetail.database.beans.AdditionTrays;
import emidetail.filter.SearchData;
import emidetail.manager.data.Gabarit;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;

/**
 *
 * @author DVA
 */
public class AdditionTraysFunctions {
    
        private static final String QUERY_ADD_BASED_STR = "from AdditionTrays t where t.articul LIKE '";
    
        private static final String QUERY_ADD_BASED = "from AdditionTrays t where";
    
        public static List<AdditionTrays> selectAddTrays() {
        List<AdditionTrays> trays = new ArrayList<AdditionTrays>();
        Session s = null;
        try{
            s = Hibernate.getSession();
            s.beginTransaction();
            trays = s.createQuery("from AdditionTrays where type=101").setMaxResults(200).list();
            s.getTransaction().commit();
        }catch(HibernateException e){
            System.err.println("Error db select addtrays");
        }finally{
            Hibernate.closeSession(s);
        }
        return trays;
    }
    
    public static List<AdditionTrays> selectAddTrayByType(int type) {
        List<AdditionTrays> addtrays = new ArrayList<AdditionTrays>();
        Session s = null;
        try{
            s = Hibernate.getSession();
            s.beginTransaction();
            addtrays = s.createQuery("from AdditionTrays where type="+type).list();
            s.getTransaction().commit();
        }catch(HibernateException e){
            System.err.println("Error db cover type");
        }finally{
            Hibernate.closeSession(s);
        }
        return addtrays;
    }
    
    public static List<AdditionTrays> selectAddTraysAll() {
        List<AdditionTrays> trays = new ArrayList<AdditionTrays>();
        Session s = null;
        try{
            s = Hibernate.getSession();
            s.beginTransaction();
            trays = s.createQuery("from AdditionTrays").list();
            s.getTransaction().commit();
        }catch(HibernateException e){
            System.err.println("Error db all addition tray");
            s.getTransaction().rollback();
        }finally{
            Hibernate.closeSession(s);
        }
        return trays;
    }
 
        
    public static List<AdditionTrays> selectAddTraysByFilter(SearchData filter) {
        if (filter==null)
            return null;
        List<AdditionTrays> list = null;
        String criteria = "";
        if (filter.getArticul()!=null) {
            if (!filter.getArticul().equalsIgnoreCase("")) {
                criteria = criteria.concat(QUERY_ADD_BASED_STR);
                criteria = criteria + filter.getArticul() + "' ";
            
            } else {
                criteria = criteria.concat(QUERY_ADD_BASED);
                criteria = criteria + " 1=1 ";
            } 
        } else {
            criteria = criteria.concat(QUERY_ADD_BASED);
            criteria = criteria + " 1=1 ";
        }
            
            
            if (filter.isIp44()) {
                if (criteria.length() > QUERY_ADD_BASED.length()+1)
                    criteria = criteria + " and ";
                criteria = criteria + "t.type=11";
            } else {
                if (filter.isType()) {
                    if (criteria.length() > QUERY_ADD_BASED.length()+1)
                        criteria = criteria + " and ";
                    criteria = criteria + " (t.type="+filter.getType() + ")";
                }
            }
            
            if (filter.isSource()) {
                if (criteria.length() > QUERY_ADD_BASED.length()+1)
                    criteria = criteria + " and ";
                criteria = criteria + " t.constructionId="+filter.getSource();
            }
            
            Gabarit gab = filter.getGabarit();
            if (gab!=null) {
                
                if (gab.getH() > 0){
                    if (criteria.length() > QUERY_ADD_BASED.length()+1)
                    criteria = criteria + " and ";
                    criteria = criteria + " t.height="+gab.getH();
                }
                if (gab.getW() > 0 || gab.getL() > 0)
                    if (criteria.length() > QUERY_ADD_BASED.length()+1)
                    criteria = criteria + " and ";
                if (gab.getW() > 0){
                    criteria = criteria + " (t.width1="+gab.getW() + " or t.width2=" +gab.getW() + ")";
                    if (gab.getL() > 0)
                        if (criteria.length() > QUERY_ADD_BASED.length()+1)
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
                    if (criteria.length() > QUERY_ADD_BASED.length()+1)
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
            System.err.println("Error db addtray criteria");
            s.getTransaction().rollback();
        }finally{
            Hibernate.closeSession(s);
        }
        return list;
    }
    
}
