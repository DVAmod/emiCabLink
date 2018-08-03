/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package emidetail.database.utils;

import emidetail.database.beans.Types;
import java.util.List;
import emidetail.database.beans.Connector;
import emidetail.database.beans.Trays;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;
import org.hibernate.HibernateException;
import org.hibernate.Session;

/**
 *
 * @author DVAmod
 */
public class ConnectorFunctions {
    
    public static List<Connector> selectConnector() {
        List<Connector> connect = new ArrayList<Connector>();
        Session s = null;
        try{
            s = Hibernate.getSession();
            s.beginTransaction();
            connect = s.createQuery("from Connector").setMaxResults(100).list();
            s.getTransaction().commit();
        }catch(HibernateException e){
            
        }finally{
            Hibernate.closeSession(s);
        }
        return connect;
    }
    
    public static List selectConnectorByType(int type) {
        
        return null;
    }
    
    public static List<Connector> selectScobaByTray(Trays tray) {
        Session s = null;
        List<Connector> list = new ArrayList<Connector>();
        List<Connector> listSel = new ArrayList<Connector>();
        try{
            s = Hibernate.getSession();
            s.beginTransaction();
            //org.hibernate.Hibernate.initialize(tray.getConnectors());
            int type = 11;
            if (tray.getHeight() >= 150) {
                type = 10;
            }
            
            if (tray.getConnectorHardId()!=null && tray.getConnectorHardId()!=0) {
                type = tray.getConnectorHardId();
            }
            
            String ths = "1.2";
            if (Float.valueOf(tray.getThickness().replace(",", ".")) >= 1.5f && tray.getHeight()>=85) {
                ths = String.valueOf( tray.getThickness());
            } else {
                if (Float.valueOf(tray.getThickness().replace(",", ".")) > 1.0f) {
                    ths = ""+Float.valueOf(tray.getThickness().replace(",", "."));
                }else{
                    ths = "1.0";
                }
            }
            ths = ths.replace(".", ",");
            System.err.println("ths "+ths);
            
            Integer construction = tray.getConstructionId();
            if (tray.getConstructionId()==3) {
                construction = 2;
            }
            if (tray.getConstructionId() > 5) {
                construction = 5;
            }
            Integer height = 0;
            Integer width = 0;
            if (tray.getHeight()<40) {
                height = 40;
            } else {
                height = tray.getHeight();
            }
            
                width = tray.getWidth();
            
            
            if (type==44){
                height = 85;
                ths = "2,0";
                if (width == 150) width = 200;
            }
            if (type==17){
                if (Float.valueOf(tray.getThickness().replace(",", ".")) > 1.5f) {
                    ths = "1,5";
                } else {
                    ths = "1,2";
                }
            }
            if (type==19||type==20){
                ths="1,5";
            }
            if (type==21||type==25){
                ths="2,0";
            }
            if (type==28){
                ths = "1,5";
                height = null;
            }
            if (type==23||type==24){
                width=null;
                height=null;
                construction=null;
                ths=null;
            }
            
            String query = "select con from Connector as con "+
                    "where con.type="+type+
                    " and con.width="+width+
                    " and con.height="+height;
            if (ths!=null)
                query = query + " and con.thickness='"+ths+"' ";
            if (construction!=null)
                    query =query.concat(" and con.constructionId="+construction);
            list = s.createQuery(query).list();
            s.getTransaction().commit();
            s.flush();
            //for (Connector con : cons) {
            //    list.add(con);
            //}
            if (list != null ) {
                if (list.size()>0) {
                    System.out.println("Yes hard connector select");
                
                } else {
                    s.beginTransaction();
                    query = "select con from Connector as con "+
                    "where con.type="+type+
                    " and con.width="+width+
                    " and con.height="+height;
                    
                    if (construction!=null)
                            query =query.concat(" and con.constructionId="+construction);
                    list = s.createQuery(query).list();
                    s.getTransaction().commit();
                    s.flush();
                    
                    if (tray.getHeight()>=85){
                        ths = "1,5";
                    } else {
                        ths = "1,2";
                    }
                    
                    for (Connector connector : list) {
                        if (connector.getThickness().equalsIgnoreCase(ths)) {
                            listSel.add(connector);
                        }
                    }
                    
                    if (listSel.size() > 0){
                        list.clear();
                        list.add(listSel.get(0));
                    } else {
                        if (list.size() > 0)
                        list = list.subList(0, 1);
                    }
                    
                
                }
            }
 
        }catch(HibernateException e){
            System.err.println("DB error "+e.getMessage());
            s.getTransaction().rollback();
        }finally{
            Hibernate.closeSession(s);
        }
        return list;
    }
    
    public static List<Connector> selectConnectorByTray(Trays tray) {
        Session s = null;
        Set<Connector> cons = null;
        List<Connector> list = new ArrayList<Connector>();
        List<Connector> listSel = new ArrayList<Connector>();
        try{
            s = Hibernate.getSession();
            s.beginTransaction();
            
            Integer construction = tray.getConstructionId();
            if (tray.getConstructionId()==3) {
                construction = 2;
            }
            if (tray.getConstructionId() > 5) {
                construction = 5;
            }
            
            int type = 14;
            if (tray.getConnectorId()>0){
                type = tray.getConnectorId();
            }
            
            Integer height = 25;
            if (tray.getHeight()>25){
                height = tray.getHeight();
            }
            
            
            
            String ths = "1.2";
            
            if (Float.valueOf(tray.getThickness().replace(",", ".")) >= 1.5f && tray.getHeight()>=85) {
                ths = String.valueOf( tray.getThickness());
            } else {
                if (Float.valueOf(tray.getThickness().replace(",", ".")) > 1.0f) {
                    ths = "1.2";
                }else{
                    ths = "1.2";
                }
            }
            ths = ths.replace(".", ",");
            System.err.println("ths "+ths);
            if (type==19||type==20){
                ths="1,5";
            }
            if (type==21||type==25){
                ths="2,0";
            }
            if (type==44){
                ths="2,0";
                height = 85;
            }
            if (type==17){
                if (Float.valueOf(tray.getThickness().replace(",", ".")) > 1.5f) {
                    ths = "1,5";
                } else {
                    ths = "1,2";
                }
            }
            if (type==28){
                ths = "1,5";
                height = null;
            }
            if (type==23||type==24){
                ths=null;
                height=null;
                construction=null;
            }
            
            String query = "select con from Connector as con "+
                    "where con.type="+type+
                    " and con.height="+height;
            if (ths!=null)
                query = query + " and con.thickness='"+ths+"' ";
            if (construction!=null)
                query = query + "  and con.constructionId="+construction;
            
            list = s.createQuery(query).list();
            s.getTransaction().commit();
            s.flush();
            
            if (list != null ) {
                if (list.size()>0) {
                    System.out.println("Yes  connector select");
                
                } else {
                    s.beginTransaction();
                    query = "select con from Connector as con "+
                    "where con.type="+type+
                    " and con.height="+height;
                    
                    if (construction!=null)
                            query =query.concat(" and con.constructionId="+construction);
                    list = s.createQuery(query).list();
                    s.getTransaction().commit();
                    s.flush();
                    
                    if (tray.getHeight()>=85){
                        ths = "1,5";
                    } else {
                        ths = "1,2";
                    }
                    
                    for (Connector connector : list) {
                        if (connector.getThickness().equalsIgnoreCase(ths)) {
                            listSel.add(connector);
                        }
                    }
                    
                    if (listSel.size() > 0){
                        list.clear();
                        list.add(listSel.get(0));
                    } else {
                        if (list.size() > 0)
                        list = list.subList(0, 1);
                    }
                    
                
                }
            }
 
        }catch(HibernateException e){
            System.err.println("DB error "+e.getMessage());
            s.getTransaction().rollback();
        }finally{
            Hibernate.closeSession(s);
        }
        return list;
    }
    
    public static List selectConnectorByIP() {
        
        return null;
    }
    
}
