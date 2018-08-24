package emidetail.database.beans;
// Generated 13.05.2018 18:04:49 by Hibernate Tools 4.3.1



/**
 * Illustration generated by hbm2java
 */
public class Illustration  implements java.io.Serializable {


     private int id;
     private String description;
     private String abbr;
     private byte[] imgData;

    public Illustration() {
    }

	
    public Illustration(int id, byte[] imgData) {
        this.id = id;
        this.imgData = imgData;
    }
    public Illustration(int id, String description, String abbr, byte[] imgData) {
       this.id = id;
       this.description = description;
       this.abbr = abbr;
       this.imgData = imgData;
    }
   
    public int getId() {
        return this.id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    public String getDescription() {
        return this.description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    public String getAbbr() {
        return this.abbr;
    }
    
    public void setAbbr(String abbr) {
        this.abbr = abbr;
    }
    
    public byte[] getImgData() {
        return this.imgData;
    }
    
    public void setImgData(byte[] imgData) {
        this.imgData = imgData;
    }




}


