/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package emidetail.database.utils;

import emidetail.database.beans.AdditionTrays;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;

/**
 *
 * @author DVA
 */
public class AdditionTraysFunctions {
    
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
    
    
}
