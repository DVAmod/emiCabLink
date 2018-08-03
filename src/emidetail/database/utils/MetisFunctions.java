/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package emidetail.database.utils;

import emidetail.database.beans.Claims;
import java.util.List;
import emidetail.database.beans.Trays;
import emidetail.database.beans.Cover;
import emidetail.database.beans.Connector;
import emidetail.database.beans.Metis;
import emidetail.database.beans.Types;
import emidetail.database.beans.AdditionTrays;
import emidetail.database.beans.LinkTrays;
import emidetail.database.beans.MetisTrayLink;
import java.util.ArrayList;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;

/**
 *
 * @author DVAmod
 */
public class MetisFunctions {
    
    public static int count_con = 0;
    
    public static int count_stand = 0;
    
    
    public static List<Metis> getMetisByLinkId(List<LinkTrays> links) {
        Session s = null;
        List<Metis> list = null;
        if (links==null)
            return null;
        try{

            if (links.size() > 0 ) {
                String criteria = "";
                //for (int i=0; i<links.size(); i++) {
                s = Hibernate.getSession();

                s.beginTransaction();

                list = s.createQuery("from Metis as mets "+
                " where id="+links.get(0).getMetisId()).list();
                s.getTransaction().commit();
                s.flush();
                if (list!=null)
                    if (list.size() > 0)
                        System.out.println("Okey metis from cover and consol");


                //}
            }
        }catch(HibernateException e){
            System.err.println("DB link metis error "+e.getMessage());
            s.getTransaction().rollback();
        }finally{
            Hibernate.closeSession(s);
        }
        return list;
    }
    
    public static List<Metis> getMetisByType(Types type){
        List<Metis> list = null;
        Session s = null;
        try{

            if (type != null ) {
                String criteria = "";
                //for (int i=0; i<links.size(); i++) {
                s = Hibernate.getSession();

                s.beginTransaction();

                list = s.createQuery("from Metis as mets "+
                " where id="+type.getId()).list();
                s.getTransaction().commit();
                s.flush();
                if (list!=null)
                    if (list.size() > 0)
                        System.out.println("Okey metis from type");


                //}
            }
        }catch(HibernateException e){
            System.err.println("Type metis error "+e.getMessage());
            s.getTransaction().rollback();
        }finally{
            Hibernate.closeSession(s);
        }
        return list;
    }
    
    public static List<Metis> getMetisByConnector(Connector con){
        Session s = null;
        List<Metis> list = null;
        try{
            s = Hibernate.getSession();
                        
            Integer metisId = 4;
            if (con.getMetisId()!=null) {
                metisId = con.getMetisId();
            }
            s.beginTransaction();
            list = s.createQuery("select met from Metis as met "+
                    "where met.id="+metisId).list();
            s.getTransaction().commit();
            s.flush();

            if (list == null) {
                count_con = 0;
                System.out.println("Error no metis select");
            } else {
                if (list.size() > 0) {
                    int mcount = 0;
                    if (con.getMetisCount()!=null) {
                        mcount = con.getMetisCount();
                        if (con.getType()==14 || con.getType()==15 || con.getType()==19 || 
                                 con.getType()==20 || con.getType()==25 || con.getType()==42) {
                            count_con = 2;
                        } else {
                            count_con = 1;
                        }
                        
                    }
                    for (int i = 0 ; i < mcount; i++) {
                        list.add(list.get(0));
                    }
                }
            }
 
        }catch(HibernateException e){
            System.err.println("DB metis error "+e.getMessage());
            s.getTransaction().rollback();
        }finally{
            Hibernate.closeSession(s);
        }
        return list;
    }
    
    
    public static List<Metis> getMetisByConnectorAndTray(Connector con, int tray_construction, int tray_height){
        Session s = null;
        List<Metis> list = null;
        List<MetisTrayLink> links = null;
        try{
            s = Hibernate.getSession();
            s.beginTransaction();
            //org.hibernate.Hibernate.initialize(tray.getConnectors());
            int type = 11;
            int width = 11;
            
            //???  type = con.getType();
            
            int construction = tray_construction;
            if (tray_construction > 5) {
                construction = 5;
            }
            //if (tray_construction ==3 ) construction = 1;
            //if (tray_construction ==2 ) construction = 1;
            
            links = s.createQuery("select link from MetisTrayLink as link "+
                    "where link.connectorTypeId="+type+" and link.trayConstructionId="+construction).setMaxResults(1).list();
            s.getTransaction().commit();
            
            /// Dop cals metis count and 2x double
            int length = 20;
            
            if (links!=null){
                if (links.size() > 0){
                    for (MetisTrayLink link : links) {
                        type = link.getMetisId();
                        length = link.getMetisLength();
                        construction = link.getMetisConstructionId();
                        count_con = link.getMetisCount();
                        
                    }
                }
            }
            
            
            s.beginTransaction();
            list = s.createQuery("select met from Metis as met "+
                    "where met.typeId="+type+" and  met.length="+length+" and met.constructionId="+construction).list();
            s.getTransaction().commit();
            s.flush();

            if (list == null)
                System.out.println("Error no metis select");
            
 
        }catch(HibernateException e){
            System.err.println("DB metis error "+e.getMessage());
            s.getTransaction().rollback();
        }finally{
            Hibernate.closeSession(s);
        }
        return list;
    }

    
}
