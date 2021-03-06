package emidetail.database.beans;
// Generated 23.05.2018 14:40:25 by Hibernate Tools 4.3.1



/**
 * Profil generated by hbm2java
 */
public class Profil  implements java.io.Serializable {


     private int id;
     private String abbr;
     private Integer type;
     private String typeSpec;
     private String articul;
     private String perforation;
     private Integer length;
     private Integer width;
     private Integer height;
     private String ths;
     private String mass;
     private String title;
     private String ix;
     private String wx;
     private String iy;
     private String wy;
     private Integer constructionId;
     private Integer illustrationId;
     private Integer classId;
     private String classStr;
     private String widthStr;
     private String description;

    public Profil() {
    }
    
     @Override
    public String toString(){
        return title+"  "+articul+"  "+type;
    }


	
    public Profil(int id, String abbr, Integer type, String articul) {
        this.id = id;
        this.abbr = abbr;
        this.type = type;
        this.articul = articul;
    }
    public Profil(int id, String abbr, Integer type, String typeSpec, String articul, String perforation, Integer length, Integer width, Integer height, String ths, String mass, String title, String ix, String wx, String iy, String wy, Integer constructionId, Integer illustrationId, Integer classId, String classStr, String widthStr, String description) {
       this.id = id;
       this.abbr = abbr;
       this.type = type;
       this.typeSpec = typeSpec;
       this.articul = articul;
       this.perforation = perforation;
       this.length = length;
       this.width = width;
       this.height = height;
       this.ths = ths;
       this.mass = mass;
       this.title = title;
       this.ix = ix;
       this.wx = wx;
       this.iy = iy;
       this.wy = wy;
       this.constructionId = constructionId;
       this.illustrationId = illustrationId;
       this.classId = classId;
       this.classStr = classStr;
       this.widthStr = widthStr;
       this.description = description;
    }
   
    public int getId() {
        return this.id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    public String getAbbr() {
        return this.abbr;
    }
    
    public void setAbbr(String abbr) {
        this.abbr = abbr;
    }
    public Integer getType() {
        return this.type;
    }
    
    public void setType(Integer type) {
        this.type = type;
    }
    public String getTypeSpec() {
        return this.typeSpec;
    }
    
    public void setTypeSpec(String typeSpec) {
        this.typeSpec = typeSpec;
    }
    public String getArticul() {
        return this.articul;
    }
    
    public void setArticul(String articul) {
        this.articul = articul;
    }
    public String getPerforation() {
        return this.perforation;
    }
    
    public void setPerforation(String perforation) {
        this.perforation = perforation;
    }
    public Integer getLength() {
        return this.length;
    }
    
    public void setLength(Integer length) {
        this.length = length;
    }
    public Integer getWidth() {
        return this.width;
    }
    
    public void setWidth(Integer width) {
        this.width = width;
    }
    public Integer getHeight() {
        return this.height;
    }
    
    public void setHeight(Integer height) {
        this.height = height;
    }
    public String getThs() {
        return this.ths;
    }
    
    public void setThs(String ths) {
        this.ths = ths;
    }
    public String getMass() {
        return this.mass;
    }
    
    public void setMass(String mass) {
        this.mass = mass;
    }
    public String getTitle() {
        return this.title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    public String getIx() {
        return this.ix;
    }
    
    public void setIx(String ix) {
        this.ix = ix;
    }
    public String getWx() {
        return this.wx;
    }
    
    public void setWx(String wx) {
        this.wx = wx;
    }
    public String getIy() {
        return this.iy;
    }
    
    public void setIy(String iy) {
        this.iy = iy;
    }
    public String getWy() {
        return this.wy;
    }
    
    public void setWy(String wy) {
        this.wy = wy;
    }
    public Integer getConstructionId() {
        return this.constructionId;
    }
    
    public void setConstructionId(Integer constructionId) {
        this.constructionId = constructionId;
    }
    public Integer getIllustrationId() {
        return this.illustrationId;
    }
    
    public void setIllustrationId(Integer illustrationId) {
        this.illustrationId = illustrationId;
    }
    public Integer getClassId() {
        return this.classId;
    }
    
    public void setClassId(Integer classId) {
        this.classId = classId;
    }
    public String getClassStr() {
        return this.classStr;
    }
    
    public void setClassStr(String classStr) {
        this.classStr = classStr;
    }
    public String getWidthStr() {
        return this.widthStr;
    }
    
    public void setWidthStr(String widthStr) {
        this.widthStr = widthStr;
    }
    public String getDescription() {
        return this.description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }




}


