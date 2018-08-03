package emidetail.database.beans;
// Generated 13.05.2018 18:04:49 by Hibernate Tools 4.3.1



/**
 * Cover generated by hbm2java
 */
public class Cover  implements java.io.Serializable {


     private Integer id;
     private String titleDisplay;
     private String class_;
     private String art;
     private String mass;
     private String description;
     private String thickness;
     private Integer height;
     private Integer width;
     private Integer length;
     private Integer constructionId;
     private String seria;
     private Integer type;
     private Integer coverTypeId;
     private String typeSpec;
     private Integer widthSpec;

    public Cover() {
    }

	
    public Cover(Integer id, String titleDisplay) {
        this.id = id;
        this.titleDisplay = titleDisplay;
    }
    
    public Cover(Integer id, String titleDisplay, String class_, String art, String mass, String description, String thickness, Integer height, Integer width, Integer length, Integer constructionId, String seria, Integer type, Integer coverTypeId, String typeSpec, Integer widthSpec) {
       this.id = id;
       this.titleDisplay = titleDisplay;
       this.class_ = class_;
       this.art = art;
       this.mass = mass;
       this.description = description;
       this.thickness = thickness;
       this.height = height;
       this.width = width;
       this.length = length;
       this.constructionId = constructionId;
       this.seria = seria;
       this.type = type;
       this.coverTypeId = coverTypeId;
       this.typeSpec = typeSpec;
       this.widthSpec = widthSpec;
    }
   
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    public String getTitleDisplay() {
        return this.titleDisplay;
    }
    
    public void setTitleDisplay(String titleDisplay) {
        this.titleDisplay = titleDisplay;
    }
    public String getClass_() {
        return this.class_;
    }
    
    public void setClass_(String class_) {
        this.class_ = class_;
    }
    public String getArt() {
        return this.art;
    }
    
    public void setArt(String art) {
        this.art = art;
    }
    public String getMass() {
        return this.mass;
    }
    
    public void setMass(String mass) {
        this.mass = mass;
    }
    public String getDescription() {
        return this.description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    public String getThickness() {
        return this.thickness;
    }
    
    public void setThickness(String thickness) {
        this.thickness = thickness;
    }
    public Integer getHeight() {
        return this.height;
    }
    
    public void setHeight(Integer height) {
        this.height = height;
    }
    public Integer getWidth() {
        return this.width;
    }
    
    public void setWidth(Integer width) {
        this.width = width;
    }
    public Integer getLength() {
        return this.length;
    }
    
    public void setLength(Integer length) {
        this.length = length;
    }
    public Integer getConstructionId() {
        return this.constructionId;
    }
    
    public void setConstructionId(Integer constructionId) {
        this.constructionId = constructionId;
    }
    public String getSeria() {
        return this.seria;
    }
    
    public void setSeria(String seria) {
        this.seria = seria;
    }
    public Integer getType() {
        return this.type;
    }
    
    public void setType(Integer type) {
        this.type = type;
    }
    public Integer getCoverTypeId() {
        return this.coverTypeId;
    }
    
    public void setCoverTypeId(Integer coverTypeId) {
        this.coverTypeId = coverTypeId;
    }
    public String getTypeSpec() {
        return this.typeSpec;
    }
    
    public void setTypeSpec(String typeSpec) {
        this.typeSpec = typeSpec;
    }
    public Integer getWidthSpec() {
        return this.widthSpec;
    }
    
    public void setWidthSpec(Integer widthSpec) {
        this.widthSpec = widthSpec;
    }




}


