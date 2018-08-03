/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package emidetail.manager.controls;

import emidetail.database.Model;
import emidetail.database.beans.Connector;
import emidetail.database.beans.Cover;
import emidetail.database.beans.Division;
import emidetail.database.utils.MetisFunctions;

/**
 *
 * @author DVAmod
 */
public class SectionValue {
        
        public static int countSection;
        public static int countConnector;
        public static int countMetis;
        public static int countMetisCov;
        public static int countMetisStands;
        public static int countMetisDividers;
        public static int countStands;
        public static int countDividers;
        public static int countCover;
        public static int countCoverClaim;
        public static int countClaim;
        public static int countMetisClaim;
        
        public static boolean fixed = false;
        
        public static boolean hard = false;
        
        public static int roadLenght = 100;

        public static float sectionStep = 1f;
        
        
        public SectionValue(int sect,int conn, int cover, int stands, int divider, int claim){
            this.countSection = sect;
            this.countConnector = conn;
            this.countCover = cover;
            this.countDividers = divider;
            this.countStands = stands;
            this.countClaim = claim;
        }
        
        public void addOther(int covClaim) {
            this.countCoverClaim = covClaim;
        }
        
        public void addMetis(int met, int metCov, int metStand, int metDiv, int metClaim) {
            this.countMetis = met;
            this.countMetisCov = metCov;
            this.countMetisStands = metStand;
            this.countMetisDividers = metDiv;
            this.countMetisClaim = metClaim;
            
        }
        
        public static int getRoadLenght() {
            return roadLenght;
        }

        public static void setRoadLenght( int roadLenght) {
            SectionValue.roadLenght = roadLenght;
        }

        public static float getSectionStep() {
            return sectionStep;
        }

        public static void setSectionStep( float sectionStep) {
            SectionValue.sectionStep = sectionStep;
        }
        
    
    /// locRoadEdit.getValue()
    
    /// locSectionStep.getValue()    locSectionStep2.getValue()
        
        
    public static SectionValue calcSectionValues(Connector conSel, Cover covSel, Division dividerSel ) {
        SectionValue val = null;
        //System.out.println("Road " + SectionValue.roadLenght);
        
        //System.out.println("Step "+Float.valueOf(SectionValue.sectionStep));
        //System.out.println("Metis count "+MetisFunctions.count_con);
        float length = Model.getInstance().selectTray.getLength()/100;
        float lengthCov = 0;
        if (covSel!=null){
            lengthCov =  covSel.getLength()/100;
        } else {
            lengthCov =  length;
        }
        float lengthDiv = 0;
        if (dividerSel!=null) {
            lengthDiv = dividerSel.getLength()/100;
        } else {
            lengthDiv = length;
        }
        System.out.println("Length "+length);
        
        
        int sections = (int) Math.round(Math.ceil((double)(SectionValue.roadLenght*10)/(double)length));
        int covers = (int) Math.round(Math.ceil((double)(SectionValue.roadLenght*10)/(double)lengthCov));
        int dividers = (int) Math.round(Math.ceil((double)(SectionValue.roadLenght*10)/(double)lengthDiv));
        int conn = Math.round(sections-1);
        if (conSel!=null){   
              if (conSel.getType()==14 || conSel.getType()==15 || conSel.getType()==19 || conSel.getType()==20
                      || conSel.getType()==21 || conSel.getType()==25 || conSel.getType()==27){
                  conn = conn*2; 
               }
        }
        int stands = Math.round(roadLenght/Float.valueOf(SectionValue.sectionStep));
        
        
        int met = 0;
        met = (int)Math.round(conn*Model.getInstance().metis_list.size());
        //if (conSel!=null)
        ///if (conSel.getHeight() <= 60){
        ///    met = (int)Math.round(conn*Model.getInstance().metis_list.size());
        ///} else {
        ///    met = (int)Math.round(conn*Model.getInstance().metis_list.size()/2);
        ///}
        if (conSel!=null)
            if (conSel.getType()==14 || conSel.getType()==15 || conSel.getType()==19 || conSel.getType()==20
                      || conSel.getType()==21 || conSel.getType()==25 || conSel.getType()==27){
                if (conSel.getHeight()>=85) {
                    met = (int) Math.round(met/2);
                }
            } else {
                met = (int) Math.round(met);
            }
        int metDiv = Math.round(dividers*3);
        
        int covClaim = 0;
        if (hard) {
            covClaim = Math.round(covers*2);
        } else {
            covClaim = Math.round(covers*4);
        }
        int metCov = Math.round(covers*4);
        int claim = 0;
        if (fixed) {
           claim = stands*4;
        } else {
            claim = stands*2;
        }
        int claimMetis = claim;
        System.out.println("s c m - "+sections+" "+conn+" "+met);
        System.out.println("stands "+stands);
        System.out.println("claim "+claim);
        
        
        
        val = new SectionValue(sections, conn, covers, stands, dividers, claim);
        val.addOther(covClaim);
        int metStands =0;
        if (Float.valueOf(SectionValue.sectionStep) > 3) {
            metStands = 2 * stands;
        } else {
            metStands = stands;
        }
        val.addMetis(met, metCov, metStands*2, metDiv, claimMetis);
        
        
        return val;
    }
}
