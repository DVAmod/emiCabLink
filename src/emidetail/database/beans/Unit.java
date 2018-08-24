package emidetail.database.beans;
// Generated Mar 22, 2018 8:47:18 PM by Hibernate Tools 4.3.1



/**
 * Unit generated by hbm2java
 */
public class Unit  implements java.io.Serializable {


     private int id;
     private String sectionName;
     private String claimName;
     private String standNames;
     private boolean standDouble;
     private Integer illustrationId;
     private String titleDisplay;

    public Unit() {
    }
    
    public String toString(){
        return titleDisplay;
    }

	
    public Unit(int id, String titleDisplay) {
        this.id = id;
        this.titleDisplay = titleDisplay;
    }
    public Unit(int id, String sectionName, String claimName, String standNames, boolean standDouble, Integer illustrationId, String titleDisplay) {
       this.id = id;
       this.sectionName = sectionName;
       this.claimName = claimName;
       this.standNames = standNames;
       this.standDouble = standDouble;
       this.illustrationId = illustrationId;
       this.titleDisplay = titleDisplay;
    }
   
    public int getId() {
        return this.id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    public String getSectionName() {
        return this.sectionName;
    }
    
    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }
    public String getClaimName() {
        return this.claimName;
    }
    
    public void setClaimName(String claimName) {
        this.claimName = claimName;
    }
    public String getStandNames() {
        return this.standNames;
    }
    
    public void setStandNames(String standNames) {
        this.standNames = standNames;
    }
    public boolean getStandDouble() {
        return this.standDouble;
    }
    
    public void setStandDouble(boolean standDouble) {
        this.standDouble = standDouble;
    }
    public Integer getIllustrationId() {
        return this.illustrationId;
    }
    
    public void setIllustrationId(Integer illustrationId) {
        this.illustrationId = illustrationId;
    }
    public String getTitleDisplay() {
        return this.titleDisplay;
    }
    
    public void setTitleDisplay(String titleDisplay) {
        this.titleDisplay = titleDisplay;
    }




}


