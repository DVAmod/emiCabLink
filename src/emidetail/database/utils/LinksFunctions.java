/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package emidetail.database.utils;


import emidetail.database.beans.LinkStands;
import emidetail.database.beans.LinkTrays;
import emidetail.database.beans.Metis;
import emidetail.database.beans.Trays;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;

/**
 *
 * @author DVAmod
 */
public class LinksFunctions {
    
    
    public static List<LinkTrays> getLinkedMetis(int standType, Trays tray, boolean ishard, int cover){
        List<LinkTrays> links = null;
        
        int type = 0;
        String hard = "!=1";
        int construction = 0;
        
        construction = tray.getConstructionId();
        
        
        type = tray.getType();

           if (ishard) {
                hard = "=1";
            } 
        
        
        Session s = null;
        try{
            s = Hibernate.getSession();
            s.beginTransaction();
            String query = "from LinkTrays where "+
                    " cover_type=" + cover +
                    " and isFixed!=1"+
                    " and  trays_type LIKE ('%"+type+"%')"+
                    " and construction_id="+construction+
                    " and profil_id="+standType;
            
            
                query +=  " and isHard"+hard;
           
            
            links = s.createQuery(query).list();
            s.getTransaction().commit();
            
            s.flush();

            System.out.println("Commit tran linked");

            
            
        } catch (Exception e) {
            System.err.println("Error db all by linked metis");
            //s.getTransaction().rollback();
        } finally {
            Hibernate.closeSession(s);
        }
        
        return links;
    }
    
    
    
    public static List<LinkTrays> getLinkedTrays(int standType,Trays tray, boolean ishard, int cover) {
        
        List<LinkTrays> links = new ArrayList<LinkTrays>();
        Session s = null;
        String hard = "!=1";
        
        int type = 0;
        
        
        type = tray.getType();
        

           if (ishard) {
                hard = "=1";
            } 
        
        try{
            s = Hibernate.getSession();
            s.beginTransaction();
            
            System.err.println("a1");
            
            String queryStr = "from LinkTrays where "+
                    " cover_type=" + cover +
                    " and trays_type LIKE ('%"+type+"%')"+
                    " and isFixed=1"+
                    " and profil_id="+standType;
            
                queryStr += " and isHard"+hard ;
            
            System.err.println("Query: "+queryStr);
            
            links = s.createQuery(queryStr).list();
            
            System.err.println("a2");
            s.getTransaction().commit();
            s.flush();

            try {
                Thread.sleep(500);
            } catch (InterruptedException ex) {
                Logger.getLogger(LinksFunctions.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            System.err.println("a3");
            }catch(HibernateException e){
            
             System.err.println("Error db all by linked functions");
            s.getTransaction().rollback();
        }finally{
            Hibernate.closeSession(s);
        }
        return links;
    }
    
    
    public static List<LinkStands> getLikedMetis(String standType, String profilType) {
        
        List<LinkStands> links = new ArrayList<LinkStands>();
        Session s = null;
        try{
            s = Hibernate.getSession();
            s.beginTransaction();
            links = s.createQuery("from LinkStands ls where "+
                    " ls.standIds LIKE ('%"+standType+"%') "+
                    " and  ls.profilIds LIKE ('%"+profilType+"%')").list();
            s.getTransaction().commit();
        }catch(HibernateException e){
            
             System.err.println("Error db all by linked");
            s.getTransaction().rollback();
        }finally{
            Hibernate.closeSession(s);
        }
        return links;
    }
    
}
