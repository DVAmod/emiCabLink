/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package emidetail.database.utils;


import emidetail.database.beans.AdditionTrays;
import emidetail.database.beans.LinkTrays;
import emidetail.database.beans.Claims;
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
public class ClaimsFunctions {
    
    public static List<Claims> getInnerClaims(Trays tray){
        List<Claims> claimses = new ArrayList<Claims>();
        int type = 64;
        int construction = 0;
        int width = 0;
        String ths = "1,5";

        if (tray==null)
            return null;
        
        width = tray.getWidth();
        construction = tray.getConstructionId();
        
        Session s = null;
        try {
            
            if (tray.getType() < 6 ) {
                    String criteria = "";

                    s = Hibernate.getSession();
                    s.beginTransaction();
                    
                    criteria = " construction_id="+construction;
                    
                    if (type != 0) {
                        criteria += "  and type="+type;
                    }
                    if (width > 0) {
                        criteria += "  and width="+width;
                    }
                    if (ths.contains(",")) {
                        criteria += "  and thickness='"+ths+"'";
                    }
                    
                    System.err.println("start inner");
                    
                    String query = "select clm from Claims as clm where "+
                            criteria; 
                            
                    System.out.println("Query: "+query);

                    claimses= s.createQuery(query).list();
                    
                    
                    s.getTransaction().commit();
                    
                    System.err.println("end inner");
            }
        } catch (Exception e) {
            System.err.println("Error inner claim for tray select db");
            s.getTransaction().rollback();
        } finally {
            Hibernate.closeSession(s);
        }
        return claimses;
    }
    
    public static List<Claims> getClaimsByAddition(AdditionTrays acs) {
        
        
        return null;
    }
    
    public static List<Claims> getClaimsByTray(Trays tray, List<LinkTrays> links)  {
        
        List<Claims> claimses = new ArrayList<Claims>();
        int type = 0;
        int construction = 0;
        int width = 0;
        int height = 0;
        String ths = "1,5";

        
        
        Session s = null;
        try{
            
            if (links.size() > 0 ) {
                String criteria = "";
                //for (int i=0; i<links.size(); i++) {
                    s = Hibernate.getSession();
                    s.beginTransaction();
                    type = links.get(0).getFixedType();

                    construction = tray.getConstructionId();
                    if (tray.getConstructionId() > 6) {
                        construction = 6;
                    }
                    if (tray.getConstructionId() == 3) {
                        construction = 2;
                    }

                    if (tray.getWidth()>0){
                        width=tray.getWidth();
                    }
                    if (tray.getHeight()>0){
                        height=tray.getHeight();
                    }

                    if (type == 54 || type == 58) {
                        if (Float.valueOf(tray.getThickness().replace(",", ".")) >= 2f || tray.getHeight()>85) {
                            ths = "2,0";
                        } 
                        width = 0;
                    }
                    if (type == 64) { // scoba vnutrinai
                        height = 0;
                        ths = "";
                    }
                    if (type == 65) {
                        if (Float.valueOf(tray.getThickness().replace(",", ".")) >= 2f || tray.getHeight()>85) {
                            ths = "2,0";
                        } 
                        width = 0;
                        height = 0;
                    }
                    if (type == 51 || type == 61){
                        width = 0;
                        height = 0;
                        ths = "1,5";
                        if (type == 51) {
                            if (construction >= 4) {
                                construction = 4;
                            } else {
                                construction = 1;
                            }
                        }

                    }
                    if (type==57 || type==58) {
                        if (height==0) {
                            height = width;
                        }
                         ths = "2,0";
                        width = 0;
                    }
                    if (type==55 || type==56) {
                        if (height==0) {
                            height = width;
                        }
                        width = 0;
                        
                    }
                    
                        criteria = " construction_id="+construction;
                    
                    if (type != 0) {
                        criteria += "  and type="+type;
                    }
                    if (width > 0) {
                        criteria += "  and width="+width;
                    }
                    if (height > 0) {
                        criteria += "  and height="+height;
                    }
                    if (ths.contains(",")) {
                        criteria += "  and thickness='"+ths+"'";
                    }
                    
                    System.err.println("a4");
                    System.out.println(""+criteria);
                    s.flush();
                    
                    String query = "select clm from Claims as clm where "+
                            criteria; 
                            
                    System.out.println("Query: "+query);

                    claimses= s.createQuery(query).list();
                    
                    
                    s.getTransaction().commit();
                    
                    System.err.println("a6");
                    
                    
                    System.err.println("a7");
                    
               // }
            
            }
        }catch(HibernateException e){
            
             System.err.println("Error db all by claims");
            s.getTransaction().rollback();
        }finally{
            Hibernate.closeSession(s);
        }
        
        return claimses;
    }
    
}
