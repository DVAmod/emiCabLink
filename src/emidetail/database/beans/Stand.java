package emidetail.database.beans;
// Generated 13.05.2018 18:04:49 by Hibernate Tools 4.3.1



/**
 * Stand generated by hbm2java
 */
public class Stand  implements java.io.Serializable {


  private int id;
     private String title;
     private int type;
     private String typeSpec;
     private String articul;
     private String description;
     private String abbr;
     private int length;
     private Integer width;
     private Integer height;
     private Integer constructionId;
     private String mass;
     private String ths;
     private String loading;
     private String perforation;
     private String claimType;
     private Integer classId;
     private String profilIds;
     private Integer illustrationId;
     private String profilStr;
     private Integer metisCount;
     private String metis;
     private Integer metisId;
     private String classStr;
     private String lengthStr;
     private String widthStr;
     private String heightStr;
     private String thsStr;

    public Stand() {
    }
    
    
  @Override
    public String toString(){
        return title+"  "+articul+",  "+lengthStr+", "+thsStr+", исполнение "+constructionId;
    }

	
    public Stand(int id, int type, int length) {
        this.id = id;
        this.type = type;
        this.length = length;
    }
    public Stand(int id, String title, int type, String typeSpec, String articul, String description, String abbr, int length, Integer width, Integer height, Integer constructionId, String mass, String ths, String loading, String perforation, String claimType, Integer classId, String profilIds, Integer illustrationId, String profilStr, Integer metisCount, String metis, Integer metisId, String classStr, String lengthStr, String widthStr, String heightStr, String thsStr) {
       this.id = id;
       this.title = title;
       this.type = type;
       this.typeSpec = typeSpec;
       this.articul = articul;
       this.description = description;
       this.abbr = abbr;
       this.length = length;
       this.width = width;
       this.height = height;
       this.constructionId = constructionId;
       this.mass = mass;
       this.ths = ths;
       this.loading = loading;
       this.perforation = perforation;
       this.claimType = claimType;
       this.classId = classId;
       this.profilIds = profilIds;
       this.illustrationId = illustrationId;
       this.profilStr = profilStr;
       this.metisCount = metisCount;
       this.metis = metis;
       this.metisId = metisId;
       this.classStr = classStr;
       this.lengthStr = lengthStr;
       this.widthStr = widthStr;
       this.heightStr = heightStr;
       this.thsStr = thsStr;
    }
   
    public int getId() {
        return this.id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    public String getTitle() {
        return this.title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    public int getType() {
        return this.type;
    }
    
    public void setType(int type) {
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
    public int getLength() {
        return this.length;
    }
    
    public void setLength(int length) {
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
    public Integer getConstructionId() {
        return this.constructionId;
    }
    
    public void setConstructionId(Integer constructionId) {
        this.constructionId = constructionId;
    }
    public String getMass() {
        return this.mass;
    }
    
    public void setMass(String mass) {
        this.mass = mass;
    }
    public String getThs() {
        return this.ths;
    }
    
    public void setThs(String ths) {
        this.ths = ths;
    }
    public String getLoading() {
        return this.loading;
    }
    
    public void setLoading(String loading) {
        this.loading = loading;
    }
    public String getPerforation() {
        return this.perforation;
    }
    
    public void setPerforation(String perforation) {
        this.perforation = perforation;
    }
    public String getClaimType() {
        return this.claimType;
    }
    
    public void setClaimType(String claimType) {
        this.claimType = claimType;
    }
    public Integer getClassId() {
        return this.classId;
    }
    
    public void setClassId(Integer classId) {
        this.classId = classId;
    }
    public String getProfilIds() {
        return this.profilIds;
    }
    
    public void setProfilIds(String profilIds) {
        this.profilIds = profilIds;
    }
    public Integer getIllustrationId() {
        return this.illustrationId;
    }
    
    public void setIllustrationId(Integer illustrationId) {
        this.illustrationId = illustrationId;
    }
    public String getProfilStr() {
        return this.profilStr;
    }
    
    public void setProfilStr(String profilStr) {
        this.profilStr = profilStr;
    }
    public Integer getMetisCount() {
        return this.metisCount;
    }
    
    public void setMetisCount(Integer metisCount) {
        this.metisCount = metisCount;
    }
    public String getMetis() {
        return this.metis;
    }
    
    public void setMetis(String metis) {
        this.metis = metis;
    }
    public Integer getMetisId() {
        return this.metisId;
    }
    
    public void setMetisId(Integer metisId) {
        this.metisId = metisId;
    }
    public String getClassStr() {
        return this.classStr;
    }
    
    public void setClassStr(String classStr) {
        this.classStr = classStr;
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




}
