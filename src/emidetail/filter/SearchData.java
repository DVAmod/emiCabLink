/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package emidetail.filter;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author DVAmod
 */
public class SearchData {
    
    private List<Integer> types;
    private List<Integer> sources;
    private int height;
    private int width;
    private int length;
    
    private List<Integer> lengths;
    private List<Integer> heights;
    
    private String articul;
    private String ths;
    private boolean ip44;
    private int construction;
    
    public SearchData(){
        types = new ArrayList<Integer>();
        sources = new ArrayList<Integer>();
        lengths = new ArrayList<Integer>();
        heights = new ArrayList<Integer>();
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setLength(int length) {
        this.length = length;
    }
    
    public void addLength(int length) {
        this.lengths.add(length);
    }
    
    public void clearLength(){
        this.lengths.clear();
    }
    
    public void addHeight(int height) {
        this.heights.add(height);
    }
    
    public void clearHeight(){
        this.heights.clear();
    }
    
    public void addType(int t) {
        types.clear();
        types.add(t);
    }
    
    public void addToTypes(int t) {
        types.add(t);
        //System.out.println("SearchData addToTypes = " + types.toString());
    }   
    
    public void addSource(int s) {
        sources.clear();
        sources.add(s);
    }
    
    public void clearTypes(){
        types.clear();
        //System.out.println("SearchData clearTypes = " + types.toString());
    }

    public String getArticul() {
        return articul;
    }

    public void setArticul(String articul) {
        this.articul = articul;
    }

    public boolean isIp44() {
        return ip44;
    }

    public void setIp44(boolean ip44) {
        this.ip44 = ip44;
    }
    
    public SearchData(List<Integer> t, List<Integer> s) {
        types = t;
        sources = s;
    }
    
    public SearchData(int h, int w, int l) {
        height = h;
        width = w;
        length = l;
    }
    
    public Gabarit getGabarit() {
        return new Gabarit(height, width, length);
    }
    
    public String getOneLength(){
        
        return lengths.get(0).toString();
    }
    
    public String getTwoLength(){
        
        return lengths.get(1).toString();
    }
    
    public String getOneHeight(){
        
        return heights.get(0).toString();
    }
    
    public String getTwoHeight(){
        
        return heights.get(1).toString();
    }

    public String getSource() {
        return String.valueOf(sources.get(0)); //To change body of generated methods, choose Tools | Templates.
    }

    public String getType() {
        if ((types == null) || (types.isEmpty())){
            return null;
        }
        if (types.size() == 1){
            return String.valueOf(types.get(0));           
        }
        
        String result = String.valueOf(types.get(0));
        for (int i = 1; i < types.size(); i++){
            result = result + (" or t.type=" + types.get(i));
            //System.out.println(result);
        }
        return result;
    }

    public boolean isSource() {
        boolean in = (sources.size()>0)? true: false;
        if (in) {
            boolean notzero = (sources.get(0)==0) ? false: true;
            return notzero;
        } else {
            return false;
        }
         //To change body of generated methods, choose Tools | Templates.
    }

    public boolean isType() {
        boolean in = (types.size()>0)? true: false;
        if (in) {
            boolean notzero = (types.get(0)==0) ? false: true;
            return notzero;
        } else {
            return false;
        }//To change body of generated methods, choose Tools | Templates.
    }

    public void clear() {
        types = new ArrayList<Integer>();
        sources = new ArrayList<Integer>();
        lengths = new ArrayList<Integer>();
        heights = new ArrayList<Integer>();
        this.height = 0;
        this.length = 0;
        this.ths = "";
        this.articul = "";
    }

    public void clearSource() {
        sources.clear(); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
    public class Gabarit {
        private int height = 0;
        private int width = 0;
        private int length = 0;
        
        public Gabarit () {
            
        }
        
        public Gabarit(int h, int w, int l) {
            height = h;
            width = w;
            length = l;
        }
        
        public int getH(){
            return height;
        }
        
        public int getW(){
            return width;
        }
        
        public int getL(){
            return length;
        }
    }

    /**
     * @return the ths
     */
    public String getThs() {
        return ths;
    }

    /**
     * @param ths the ths to set
     */
    public void setThs(String ths) {
        this.ths = ths;
    }

    /**
     * @return the construction
     */
    public int getConstruction() {
        return construction;
    }

    /**
     * @param construction the construction to set
     */
    public void setConstruction(int construction) {
        this.construction = construction;
    }
}
