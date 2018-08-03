package emidetail.database.beans;
// Generated 13.05.2018 18:04:49 by Hibernate Tools 4.3.1



/**
 * Trays generated by hbm2java
 */
public class Trays  implements java.io.Serializable {


     private int id;
     private String description;
     private String titleDisplay;
     private String thickness;
     private String art;
     private String mass;
     private String price;
     private Integer height;
     private Integer width;
     private Integer length;
     private Integer constructionId;
     private Integer connectorId;
     private Integer connectorHardId;
     private Integer connectorOtherId;
     private String abbr;
     private Integer profilId;
     private String class_;
     private String heightStr;
     private String thsStr;
     private Integer type;
     private String lengthStr;
     private String widthStr;
     private Integer coverTypeId;
     private Integer illustrationId;
     private String seria;

    

    public Trays() {
    }

	
    public Trays(int id) {
        this.id = id;
    }
    public Trays(int id, String description, String titleDisplay, String thickness, String art, String mass, String price, Integer height, Integer width, Integer length, Integer constructionId, Integer connectorId, Integer connectorHardId, Integer connectorOtherId, String abbr, Integer profilId, String class_, String heightStr, String thsStr, Integer type, String lengthStr, String widthStr, Integer coverTypeId, Integer illustrationId) {
       this.id = id;
       this.description = description;
       this.titleDisplay = titleDisplay;
       this.thickness = thickness;
       this.art = art;
       this.mass = mass;
       this.price = price;
       this.height = height;
       this.width = width;
       this.length = length;
       this.constructionId = constructionId;
       this.connectorId = connectorId;
       this.connectorHardId = connectorHardId;
       this.connectorOtherId = connectorOtherId;
       this.abbr = abbr;
       this.profilId = profilId;
       this.class_ = class_;
       this.heightStr = heightStr;
       this.thsStr = thsStr;
       this.type = type;
       this.lengthStr = lengthStr;
       this.widthStr = widthStr;
       this.coverTypeId = coverTypeId;
       this.illustrationId = illustrationId;
    }
   
    public int getId() {
        return this.id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public String getSeria() {
        return seria;
    }

    public void setSeria(String seria) {
        this.seria = seria;
    }
    
    public String getDescription() {
        return this.description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    public String getTitleDisplay() {
        return this.titleDisplay;
    }
    
    public void setTitleDisplay(String titleDisplay) {
        this.titleDisplay = titleDisplay;
    }
    public String getThickness() {
        return this.thickness;
    }
    
    public void setThickness(String thickness) {
        this.thickness = thickness;
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
    public String getPrice() {
        return this.price;
    }
    
    public void setPrice(String price) {
        this.price = price;
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
    public Integer getConnectorId() {
        return this.connectorId;
    }
    
    public void setConnectorId(Integer connectorId) {
        this.connectorId = connectorId;
    }
    
    public Integer getConnectorHardId() {
        return this.connectorHardId;
    }
    
    public void setConnectorHardId(Integer connectorHardId) {
        this.connectorHardId = connectorHardId;
    }
    public Integer getConnectorOtherId() {
        return this.connectorOtherId;
    }
    
    public void setConnectorOtherId(Integer connectorOtherId) {
        this.connectorOtherId = connectorOtherId;
    }
    public String getAbbr() {
        return this.abbr;
    }
    
    public void setAbbr(String abbr) {
        this.abbr = abbr;
    }
    public Integer getProfilId() {
        return this.profilId;
    }
    
    public void setProfilId(Integer profilId) {
        this.profilId = profilId;
    }
    public String getClass_() {
        return this.class_;
    }
    
    public void setClass_(String class_) {
        this.class_ = class_;
    }
    public String getHeightStr() {
        return this.heightStr;
    }
    
    public void setHeightStr(String heightStr) {
        this.heightStr = heightStr;
    }
    public String getThsStr() {
        return this.thsStr;
    }
    
    public void setThsStr(String thsStr) {
        this.thsStr = thsStr;
    }
    public Integer getType() {
        return this.type;
    }
    
    public void setType(Integer type) {
        this.type = type;
    }
    public String getLengthStr() {
        return this.lengthStr;
    }
    
    public void setLengthStr(String lengthStr) {
        this.lengthStr = lengthStr;
    }
    public String getWidthStr() {
        return this.widthStr;
    }
    
    public void setWidthStr(String widthStr) {
        this.widthStr = widthStr;
    }
    public Integer getCoverTypeId() {
        return this.coverTypeId;
    }
    
    public void setCoverTypeId(Integer coverTypeId) {
        this.coverTypeId = coverTypeId;
    }
    public Integer getIllustrationId() {
        return this.illustrationId;
    }
    
    public void setIllustrationId(Integer illustrationId) {
        this.illustrationId = illustrationId;
    }




}


