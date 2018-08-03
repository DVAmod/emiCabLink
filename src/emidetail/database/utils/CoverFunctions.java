/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package emidetail.database.utils;

import emidetail.database.beans.Types;
import java.util.List;
import emidetail.database.beans.Trays;
import emidetail.database.beans.Cover;
import java.util.ArrayList;
import org.hibernate.HibernateException;
import org.hibernate.Session;

/**
 *
 * @author DVAmod
 */
public class CoverFunctions {
    
    public static List selectCover() {
        
        return null;
    }
    
    public static List<Cover> selectCoverByType(int type) {
        List<Cover> covers = new ArrayList<Cover>();
        Session s = null;
        try{
            s = Hibernate.getSession();
            s.beginTransaction();
            covers = s.createQuery("from Cover where type="+type).setMaxResults(100).list();
            s.getTransaction().commit();
        }catch(HibernateException e){
            System.err.println("Error db cover type");
        }finally{
            Hibernate.closeSession(s);
        }
        return covers;
    }
    
    public static List<Cover> selectCoverByTray(Trays tray) {
        List<Cover> covers = new ArrayList<Cover>();
        List<Cover> coversSel = new ArrayList<Cover>();
        Integer types = new Integer(0);
        Session s = null;
        try{
            s = Hibernate.getSession();

            /// Add dop operation
            
            int type = 9;
                        
            types = tray.getCoverTypeId();
            
            
            
            if (types>0)
            type = types;
            
            
            int construction = tray.getConstructionId();
            if (tray.getConstructionId() > 6) {
                construction = 6;
            }
            if (tray.getConstructionId() == 3) {
                construction = 2;
            }
            
            String ths = "1.2";
            if (Float.valueOf(tray.getThickness().replace(",", ".")) >= 1.5f && tray.getHeight()>=85) {
                ths = "1.5";
            } else {
                if (Float.valueOf(tray.getThickness().replace(",", ".")) > 1.0f) {
                    ths = "1.2";
                }else{
                    ths = tray.getThickness().replace(",", ".");
                }
            }
            ths = ths.replace(".", ",");
            System.err.println("ths "+ths);
            
            
            int length = 3000;
            if(3000 < tray.getLength() && tray.getLength() <4000) {
                length = 3000;
            }else{
                length = tray.getLength();
            }
            
            s.beginTransaction();
            covers = s.createQuery("from Cover as cov "+
                    " where cov.type="+type+
                    "  and cov.width="+tray.getWidth()+
                    " and cov.thickness='"+ths+"' "+
                    " and (cov.length="+length+"  or  cov.length="+Math.floor(tray.getLength()/2)+") "+
                    "  and cov.constructionId="+construction
            ).list();
            s.getTransaction().commit();
            
            
            if (covers.size()==0) {
                s.flush();
                s.beginTransaction();
                covers = s.createQuery("from Cover as cov "+
                        " where cov.type="+type+
                        "  and cov.width="+tray.getWidth()+
                        " and (cov.length="+length+"  or  cov.length="+Math.floor(tray.getLength()/2)+") "+
                        "  and cov.constructionId="+construction
                ).list();
                s.getTransaction().commit();
                
            }
            
            if (covers.size() > 0) {
                Float maxCov = 0f;
                if (covers.size() > 1) {
                    if (Float.valueOf(tray.getThickness().replace(",", "."))>= 1.5) {
                        Float thsCov = 0f;
                        maxCov = 0.1f;
                        for (Cover cover : covers) {
                            thsCov = 0.8f;
                            if (cover.getThickness() !=null)
                            thsCov = Float.valueOf(cover.getThickness().replace(",", "."));
                            if (thsCov > maxCov) {
                                maxCov = thsCov;
                            }
                        }   
                    } else {
                        Float thsCov = 0f;
                        maxCov = 1.5f;
                        for (Cover cover : covers) {
                            thsCov = 0.8f;
                            if (cover.getThickness() !=null)
                            thsCov = Float.valueOf(cover.getThickness().replace(",", "."));
                            if (thsCov < maxCov) {
                                maxCov = thsCov;
                            }
                        }
                    
                        
                    }
                    for (Cover cover : covers) {
                        if (maxCov == Float.valueOf(cover.getThickness().replace(",", "."))) {
                            coversSel.add(cover);
                        }
                    }
                    if (coversSel.size()>0){
                        covers.clear();
                        covers.addAll(coversSel);
                    }else{
                        if (covers.size()> 0) {
                            covers = covers.subList(0, 1);
                        }
                    }
                    s.flush();
                    s.beginTransaction();
                    Object cover = s.createQuery("from Cover as cov "+
                            " where cov.type="+type+
                            "  and cov.width="+tray.getWidth()+
                            " and (cov.length="+length+"  or  cov.length="+Math.floor(tray.getLength()/2)+") "+
                            " and cov.titleDisplay LIKE ('%снегозащитная%')"+
                            "  and cov.constructionId="+construction
                    ).uniqueResult();
                    s.getTransaction().commit();
                    if (cover!=null){
                        covers.add((Cover)cover);
                    }
                    
                } 
            } else {
                s.flush();
                s.beginTransaction();
                Object cover = s.createQuery("from Cover as cov "+
                        " where cov.type="+type+
                        "  and cov.width="+tray.getWidth()+
                        " and (cov.length="+length+"  or  cov.length="+Math.floor(tray.getLength()/2)+") "
                ).uniqueResult();
                s.getTransaction().commit();
                if (cover!=null){
                    covers.add((Cover)cover);
                }
                // снегозащитная
                s.flush();
                s.beginTransaction();
                cover = s.createQuery("from Cover as cov "+
                        " where cov.type="+type+
                        "  and cov.width="+tray.getWidth()+
                        " and (cov.length="+length+"  or  cov.length="+Math.floor(tray.getLength()/2)+") "+
                        " and cov.titleDisplay LIKE ('%снегозащитная%')"
                ).uniqueResult();
                s.getTransaction().commit();
                if (cover!=null){
                    covers.add((Cover)cover);
                }
            }
            
        }catch(HibernateException e){ 
            System.err.println("DB error select cover "+e.getMessage());
            s.getTransaction().rollback();
        }finally{
            Hibernate.closeSession(s);
        }
        return covers;
    }
    
}
