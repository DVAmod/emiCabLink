/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package emidetail.database.utils;

import java.util.List;
import emidetail.database.beans.Trays;
import emidetail.database.beans.Division;
import java.util.ArrayList;
import org.hibernate.HibernateException;
import org.hibernate.Session;

/**
 *
 * @author DVAmod
 */
public class DivisionFunctions {
    
    public static List<Division> selectDivision() {
        List<Division> dividers = new ArrayList<Division>();
        Session s = null;
        try{
            s = Hibernate.getSession();
            s.beginTransaction();
            dividers = s.createQuery("from Division").list();
            s.getTransaction().commit();
        }catch(HibernateException e){
            System.err.println("Error db all profil");
            s.getTransaction().rollback();
        }finally{
            Hibernate.closeSession(s);
        }
        return dividers;
    }
    
    public static List<Division> selectDivisionByTray(Trays tray) {
        List<Division> dividers = new ArrayList<Division>();
        Session s = null;
        try{
            int height = 0;
            int length = 0;
            int source = 1;
            String ths = "1,2";
            if (tray.getConstructionId()==4 || tray.getConstructionId()==5) {
                source = 2;
            } else {
                source = tray.getConstructionId();
            }
            height = tray.getHeight();
            if (tray.getType()==6||tray.getType()==7)
            
                if (tray.getHeight()==120) {
                    height = 110;
                }
            if (tray.getHeight()>200) {
                height = 200;
            }
            if (tray.getType()>7){
                height = 250;
            }
            if (tray.getLength()>3000) {
                length = 2000;
            } else {
                length = tray.getLength();
            }
            if (Float.valueOf(tray.getThickness().replace(",", ".")) >= 1.5f && tray.getHeight()>=110 && (tray.getType()==6||tray.getType()==7)) {
                ths = "1.5";
            } else {
                if (Float.valueOf(tray.getThickness().replace(",", ".")) > 1.0f) {
                    ths = "1.2";
                }else{
                    ths = "1.0";
                }
            }
            ths = ths.replace(",", ".");
            System.err.println("ths "+ths);
            
            
            s = Hibernate.getSession();
            s.beginTransaction();
            dividers = s.createQuery("from Division where "+
                    " construction_id="+source+
                    " and width="+height+
                    " and length="+length+
                    " and thickness='"+ths+"'").list();
            s.getTransaction().commit();
            //s.flush();
            
            if (dividers.size()<=0){
                
                
                s.beginTransaction();
                dividers = s.createQuery("from Division where "+
                        " construction_id="+source+
                        " and width="+height+
                        " and length="+length).list();
                s.getTransaction().commit();
                
                
                
                if (dividers.size() > 0)
                    dividers = dividers.subList(0, 1);
                
            }
            
            s.flush();
            
        }catch(HibernateException e){
            System.err.println("Error db Division");
            s.getTransaction().rollback();
        }finally{
            Hibernate.closeSession(s);
        }
        
        
        return dividers;
    }
    
}
