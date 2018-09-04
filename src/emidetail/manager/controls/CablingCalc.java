/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package emidetail.manager.controls;

import emidetail.database.Model;
import emidetail.filter.SearchData;
import emidetail.manager.data.Gabarit;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author DVAmod
 */
public class CablingCalc {
    
    public static String CAB_CONF[] = {"Однослойный",
    "Однослойный через диаметр","Многослойный","Пучком"};
    
    public static String CAB_CONF1[] = {"Однослойный",
    "Однослойный через диаметр","Многослойный","Пучком"};
    
    public static String CAB_CONF2[] = {"Однослойный",
    "Однослойный через диаметр","Многослойный","Пучком"};
    
    public static String CAB_CONF3[] = {"Однослойный",
    "Однослойный через диаметр","Многослойный","Пучком"};
    
    private ArrayList<Float> diametrs;
    private ArrayList<Integer> counts;
    private ArrayList<Integer> types;
    
    private ArrayList<Float> widths;
    private ArrayList<Float> heights;
    
    public Float width;
    public Float height;
    
    private int level = 1;
    private int dataindex = -1;
    private int index = 0;
    private int cur_type = 0;
    
    public boolean dataclear = false;
    
    public Integer trayWidth;
    public Integer trayHeight;
    
    public Integer getDataIndex(){
        return widths.size();
    }
    
    public Integer getWidth(int i) {
        return  widths.get(i).intValue();
    }
    
    public Float getDiametr(int i){
        return diametrs.get(i);
    }
    
    public Integer getCount(int i) {
        return counts.get(i);
    }
    
    public Integer getType(int i) {
        return types.get(i);
    }
    
    public Integer getLevel() {
        if (cur_type==0)
            return 1;
        if (cur_type==1 || cur_type==2)
            return 1;
        if (cur_type==3)
            return level+1;
        if (cur_type==4)
            return level+2;
        
        return level;
    }
    
    public CablingCalc(){
        diametrs = new ArrayList<Float>();
        counts = new ArrayList<Integer>();
        types = new ArrayList<Integer>();
        widths = new ArrayList<Float>();
        heights = new ArrayList<Float>();
        
    }
    
    public void addCalcData(float diametr, int count, int type){
        diametrs.add(diametr);
        counts.add(count);
        types.add(type);
        dataindex++;
    }
    
    public void changeCalcData(float diametr, int count, int type){
        diametrs.set(index-1,diametr);
        counts.set(index-1,count);
        types.set(index-1,type);
    }
    
    
    public boolean calc (Gabarit inner, int indStr) {
        
        boolean res = false;
        if (indStr > 5){
            //this.addCalcData(inner.getH(), (int) inner.getW(), inner.getL());
            res = this.calculateAllDiametr();
        } else {
            if (indStr < index) {
                clear();
            }
            if (indStr == index && index >= 0) {
                this.changeCalcData(inner.getH(), (int) inner.getW(), inner.getL());
            } else {
                this.addCalcData(inner.getH(), (int) inner.getW(), inner.getL());
            }
            index = indStr;
            res = this.calculateDiametr();
        }
        
        return res;
    }
    
    public boolean calculateAllDiametr() {
        width = 0f;
        height = 0f;
        widths.clear();
        heights.clear();
        
        if (dataclear) {
            for (int i = 0; i < types.size(); i++) {
                Integer type1 = types.get(i);
                Gabarit res1 = calcMethod(type1,i);
                float wt = res1.getW();
                float ht = res1.getH();
                //width = wt/1.4f;
                //height = ht*1.4f;
                widths.add(wt/1.4f);
                heights.add(ht*1.4f);
                 
 
            }
            
            float max_height = 0;
            
            for (int ii = 0; ii< widths.size(); ii++ ) {
                width += widths.get(ii);
                Integer type2 = types.get(ii);
                if (type2==4) {
                    max_height = (heights.get(ii))/1.4f;
                }
                height += heights.get(ii);
            }
            
            height = height/(widths.size()-1);
            if (height < max_height) height = max_height;
            
            
            dataclear = false;
        }
        
        boolean isfind = false;
        
        if (width!=0f && height!=0f) {
           isfind = calcFiltrTray();
        }
        
        return isfind;
        
    }
    
    public void clear () {
        diametrs = new ArrayList<Float>();
        counts = new ArrayList<Integer>();
        types = new ArrayList<Integer>();
        dataindex = -1;
        level = 1;
        index = 0;
        dataclear = true;
    }
    
    private void calcLevel(int type, int count) {
        if (type==3) {
            if (count <= 15) {
                level = 1;
            } else if (count <= 30) {
                level = 2;
            } else if (count <= 45) {
                level = 3;
            } else {
                level = 3;
            }
        }
        if (type==4) {
            if (count <= 10) {
                level = 1;
            } else if (count <= 20) {
                level = 2;
            } else if (count <= 30) {
                level = 3;
            } else {
                level = 3;
            }
        }
    }
    
    private Gabarit cals1(float diam, int count) {
        // Ширина лотка = диаметр кабелей * количество кабелей * 1,4
        cur_type = 1;
        float width = diam*count*1.4f ;
        float height = diam*1.2f;
        return new Gabarit(height,width, 0);
    }
    
    private Gabarit cals2(float diam, int count) {
        cur_type = 2;
        float width = 2*diam*count*1.4f ;
        float height = diam*1.2f;
        return new Gabarit(height,width, 0);
    }
    
    private Gabarit cals3(float diam, int count) {
        this.calcLevel(3, count);
        cur_type = 3;
        float width = (float) (diam*Math.ceil((double)count/(level+1))*1.4f) ;
        float height = (float) ((level+1)*diam*1.3f);
        return new Gabarit(height,width, 0);
    }
    
    private Gabarit cals4(float diam, int count) {
        
        this.calcLevel(4, count);
        cur_type = 4;
        float width = (float) (diam*Math.ceil((double)count/(level+2))*1.4f) ;
        float height = (float) ((level+2)*diam*1.4f);
        return new Gabarit(height,width, 0);
    }
    
    private boolean checkData(float diam, int count) {
        if (diam > 2f) {
            if (count > 0) {
                return true;
            }
        }   
        return false;    
    }
    
    public Gabarit calcMethod(int type, int index1) {
        Gabarit result = null;
        switch (type) {
            case 1:
                if (!checkData(diametrs.get(index1), counts.get(index1))) break;
                result = cals1(diametrs.get(index1), counts.get(index1));
                break;
            case 2:
                if (!checkData(diametrs.get(index1), counts.get(index1))) break;
                result = cals2(diametrs.get(index1), counts.get(index1));
                break;    
            case 3:
                if (!checkData(diametrs.get(index1), counts.get(index1))) break;
                result = cals3(diametrs.get(index1), counts.get(index1));
                break;    
            case 4:
                if (!checkData(diametrs.get(index1), counts.get(index1))) break;
                result = cals4(diametrs.get(index1), counts.get(index1));
                break;    
            
        }
        return result;
    }
    
    
    public boolean calculateDiametr() {
        width = 0f;
        height = 0f;
        
        if (dataclear) {
            for (int i = 0; i < types.size(); i++) {
                Integer type1 = types.get(i);
                 Gabarit res1 = calcMethod(type1,dataindex);
                 float wt = res1.getW();
                 float ht = res1.getH();
                 if (wt > width) width = wt;
                 if (ht > height) height = ht;
 
            }
            dataclear = false;
        } else {
            Gabarit res = calcMethod(types.get(dataindex), dataindex);
            width = res.getW();
            height = res.getH();
        }
        
        boolean isfind = false;
        
        if (width!=0f && height!=0f) {
           isfind = calcFiltrTray();
        }
        
        return isfind;
    }
    
    private boolean calcFiltrTray() {
        List<String> hll = Model.getInstance().height_list;
        List<String> wll = Model.getInstance().width_list;
        boolean wfind = false;
        boolean hfind = false;
        
        for (int i = 1; i < wll.size(); i++) {
            String wl = wll.get(i);
            try {
                Integer wi = Integer.valueOf(wl);
                if (wi.floatValue() >= width) {
                    trayWidth = wi;
                    wfind = true;
                    break;
                }
            } catch (Exception ex) {
                System.err.println("Error integer pars value");
            }    
        }
        
        for (int i = 1; i < hll.size(); i++) {
            String hl = hll.get(i);
            try {
                Integer hi = Integer.valueOf(hl);
                if (hi.floatValue() >= height) {
                    trayHeight = hi;
                    hfind = true;
                    break;
                }
            } catch (Exception ex) {
                System.err.println("Error integer pars value");
            }
        }
        
        if (wfind && hfind) {
            return true;
        }
        
        return false;
    }
    
}
