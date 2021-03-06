package emidetail.database.beans;
// Generated 04.07.2018 22:46:18 by Hibernate Tools 4.3.1



/**
 * LinkTrays generated by hbm2java
 */
public class LinkTrays  implements java.io.Serializable {


     private Integer id;
     private String traysType;
     private int coverType;
     private String title;
     private Integer count;
     private boolean isFixed;
     private boolean isHard;
     private boolean isScoba;
     private Integer fixedType;
     private Integer metisId;
     private Integer constructionId;
     private Integer profilId;

    


    public LinkTrays() {
    }

	
    public LinkTrays(Integer id, String traysType, int coverType, String title) {
        this.id = id;
        this.traysType = traysType;
        this.coverType = coverType;
        this.title = title;
    }
    
    
    public LinkTrays(Integer id, String traysType, int coverType, String title, Integer count, boolean isFixed, boolean isHard, boolean isScoba, Integer fixedType, Integer metisId, Integer constructionId) {
       this.id = id;
       this.traysType = traysType;
       this.coverType = coverType;
       this.title = title;
       this.count = count;
       this.isFixed = isFixed;
       this.isHard = isHard;
       this.isScoba = isScoba;
       this.fixedType = fixedType;
       this.metisId = metisId;
       this.constructionId = constructionId;
    }
   
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    
    public Integer getProfilId() {
        return profilId;
    }

    public void setProfilId(Integer profilId) {
        this.profilId = profilId;
    }
    
    
    public String getTraysType() {
        return this.traysType;
    }
    
    public void setTraysType(String traysType) {
        this.traysType = traysType;
    }
    public int getCoverType() {
        return this.coverType;
    }
    
    public void setCoverType(int coverType) {
        this.coverType = coverType;
    }
    public String getTitle() {
        return this.title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    public Integer getCount() {
        return this.count;
    }
    
    public void setCount(Integer count) {
        this.count = count;
    }
    public boolean getIsFixed() {
        return this.isFixed;
    }
    
    public void setIsFixed(boolean isFixed) {
        this.isFixed = isFixed;
    }
    public boolean getIsHard() {
        return this.isHard;
    }
    
    public void setIsHard(boolean isHard) {
        this.isHard = isHard;
    }
    public boolean getIsScoba() {
        return this.isScoba;
    }
    
    public void setIsScoba(boolean isScoba) {
        this.isScoba = isScoba;
    }
    public Integer getFixedType() {
        return this.fixedType;
    }
    
    public void setFixedType(Integer fixedType) {
        this.fixedType = fixedType;
    }
    public Integer getMetisId() {
        return this.metisId;
    }
    
    public void setMetisId(Integer metisId) {
        this.metisId = metisId;
    }
    
    public Integer getConstructionId() {
        return constructionId;
    }

    public void setConstructionId(Integer constructionId) {
        this.constructionId = constructionId;
    }



}


