/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package emidetail.database.beans;

/**
 *
 * @author DVAmod
 */
public class Stands implements java.io.Serializable {
    
    private String name;
    private Integer length;
    private Number ths;
    private Integer count;
    private boolean doub;
    
    @Override
    public String toString(){
        return name+" "+length;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public Number getThs() {
        return ths;
    }

    public void setThs(Number ths) {
        this.ths = ths;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public boolean isDoub() {
        return doub;
    }

    public void setDoub(boolean doub) {
        this.doub = doub;
    }
    
    public Stands() {
        
    }
    
    public Stands(String name) {
        doub = false;
        this.name = name;
        this.length = 0;
        this.count = 0;
        this.ths = 0;
    }
    
    public Stands(String name, boolean doub) {
        this.doub = doub;
        this.name = name;
        this.length = 0;
        this.count = 0;
        this.ths = 0;
    }
    
    public Stands(String length, String ths, String count){
        if (length!=null)
            this.length = Integer.valueOf(length);
        if (ths!=null)
            this.ths = Float.valueOf(ths);
        if (count!=null)
            this.count = Integer.valueOf(count);
    }
    
    
}
