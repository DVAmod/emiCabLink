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
public class Report implements java.io.Serializable {
    
    private int id = 0;
    
    private int index = 0;
    
    private String title = "";
    
    private String full = "";
    
    private String articul = "";
    
    private String type = "";
    
    private String comment = "";
    
    private int count = 1;
    
    private float mass = 1;
    
    private int one_size = 1;
    
    private String count_type = "шт.";
    
    private String description = "";
    
    private int parent;

    public int getParent() {
        return parent;
    }

    public void setParent(int parent) {
        this.parent = parent;
    }
    
    public Report() {
        
    }
    
    public Report(String title, int count) {
        this.title = title;
        this.count = count;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCount_type() {
        return count_type;
    }

    public void setCount_type(String count_type) {
        this.count_type = count_type;
    }
    
    
    
    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArticul() {
        return articul;
    }

    public void setArticul(String articul) {
        this.articul = articul;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public float getMass() {
        return mass;
    }

    public void setMass(float mass) {
        this.mass = mass;
    }

    public int getOne_size() {
        return one_size;
    }

    public void setOne_size(int one_size) {
        this.one_size = one_size;
    }
    
    
    public String getFull() {
        return full;
    }

    public void setFull(String full) {
        this.full = full;
    }
    
}
