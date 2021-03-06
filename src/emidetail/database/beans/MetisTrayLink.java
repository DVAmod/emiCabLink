package emidetail.database.beans;
// Generated 13.05.2018 18:04:49 by Hibernate Tools 4.3.1



/**
 * MetisTrayLink generated by hbm2java
 */
public class MetisTrayLink  implements java.io.Serializable {


     private int id;
     private Integer trayTypeId;
     private Integer trayConstructionId;
     private Integer coverConstructionId;
     private Integer metisConstructionId;
     private Integer metisId;
     private Integer metisCount;
     private Integer metisLength;
     private Integer standConstructionId;
     private Integer standTypeId;
     private Integer connectorTypeId;

    public MetisTrayLink() {
    }

	
    public MetisTrayLink(int id) {
        this.id = id;
    }
    public MetisTrayLink(int id, Integer trayTypeId, Integer trayConstructionId, Integer coverConstructionId, Integer metisConstructionId, Integer metisId, Integer metisCount, Integer metisLength, Integer standConstructionId, Integer standTypeId, Integer connectorTypeId) {
       this.id = id;
       this.trayTypeId = trayTypeId;
       this.trayConstructionId = trayConstructionId;
       this.coverConstructionId = coverConstructionId;
       this.metisConstructionId = metisConstructionId;
       this.metisId = metisId;
       this.metisCount = metisCount;
       this.metisLength = metisLength;
       this.standConstructionId = standConstructionId;
       this.standTypeId = standTypeId;
       this.connectorTypeId = connectorTypeId;
    }
   
    public int getId() {
        return this.id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    public Integer getTrayTypeId() {
        return this.trayTypeId;
    }
    
    public void setTrayTypeId(Integer trayTypeId) {
        this.trayTypeId = trayTypeId;
    }
    public Integer getTrayConstructionId() {
        return this.trayConstructionId;
    }
    
    public void setTrayConstructionId(Integer trayConstructionId) {
        this.trayConstructionId = trayConstructionId;
    }
    public Integer getCoverConstructionId() {
        return this.coverConstructionId;
    }
    
    public void setCoverConstructionId(Integer coverConstructionId) {
        this.coverConstructionId = coverConstructionId;
    }
    public Integer getMetisConstructionId() {
        return this.metisConstructionId;
    }
    
    public void setMetisConstructionId(Integer metisConstructionId) {
        this.metisConstructionId = metisConstructionId;
    }
    public Integer getMetisId() {
        return this.metisId;
    }
    
    public void setMetisId(Integer metisId) {
        this.metisId = metisId;
    }
    public Integer getMetisCount() {
        return this.metisCount;
    }
    
    public void setMetisCount(Integer metisCount) {
        this.metisCount = metisCount;
    }
    public Integer getMetisLength() {
        return this.metisLength;
    }
    
    public void setMetisLength(Integer metisLength) {
        this.metisLength = metisLength;
    }
    public Integer getStandConstructionId() {
        return this.standConstructionId;
    }
    
    public void setStandConstructionId(Integer standConstructionId) {
        this.standConstructionId = standConstructionId;
    }
    public Integer getStandTypeId() {
        return this.standTypeId;
    }
    
    public void setStandTypeId(Integer standTypeId) {
        this.standTypeId = standTypeId;
    }
    public Integer getConnectorTypeId() {
        return this.connectorTypeId;
    }
    
    public void setConnectorTypeId(Integer connectorTypeId) {
        this.connectorTypeId = connectorTypeId;
    }




}


