/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package emidetail.database;

import emidetail.database.beans.AdditionTrays;
import emidetail.database.beans.Connector;
import emidetail.database.beans.Cover;
import emidetail.database.beans.Stand;
import emidetail.database.beans.Illustration;
import emidetail.database.beans.Metis;
import emidetail.database.beans.Bracket;
import emidetail.database.beans.Claims;
import emidetail.database.beans.Division;
import emidetail.database.beans.LinkStands;
import emidetail.database.beans.LinkTrays;
import emidetail.database.beans.Profil;
import emidetail.database.beans.Report;
import emidetail.database.beans.Stands;
import emidetail.database.beans.Trays;
import emidetail.database.beans.Types;
import emidetail.database.beans.Unit;
import emidetail.database.utils.AdditionTraysFunctions;
import emidetail.database.utils.BracketFunctions;
import emidetail.database.utils.ClaimsFunctions;
import emidetail.database.utils.ConnectorFunctions;
import emidetail.database.utils.CoverFunctions;
import emidetail.database.utils.DivisionFunctions;
import emidetail.database.utils.LinksFunctions;
import emidetail.database.utils.MetisFunctions;
import emidetail.database.utils.ProfilFunctions;
import emidetail.database.utils.StandFunctions;
import emidetail.database.utils.TraysFunctions;
import emidetail.database.utils.TypesFunctions;
import emidetail.database.utils.UnitFunctions;
import emidetail.filter.SearchData;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Observable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.hibernate.Hibernate;

/**
 * Model all functions at db utils
 * @author DVAmod
 */
public class Model extends Observable{
    
    public int UID = 1;
    
    public List<String> addtraylist;
          
    public ObservableList<AdditionTrays> addtrays_list;
    
    public ObservableList<AdditionTrays> addTlist;
    
    public ObservableList<AdditionTrays> addFlist;
    
    
    public ObservableList<String> addsource_list;
    
    public ObservableList<String> addwidth_list;
    
    public ObservableList<String> addheight_list;
    
    public ObservableList<String> addthickness_list;
    
    public int TRAY_INDEX = 0;
    
    public int CON_INDEX = 1;
    
    public int COV_INDEX = 3;
    
    public int METIS_INDEX = 4;
    
    public int CLAIM_INDEX = 5;
    
    public int STAND_INDEX = 6;
    
    public int PROF_INDEX = 7;
    
    private int CODE_INDEX = 5;
    
    private int lastMainReport = 0;
    
    private int lastTrayReport = 0;
    
    private int lastReportIndex = 0;
    
    private int lastSecondReport = 0;
    
    public int getSecondReport() {
        return lastSecondReport;
    }
    
    public void setSecondReport(int value) {
        this.lastSecondReport = value;
    }

    public int getLastTrayReport() {
        return lastTrayReport;
    }

    public void setLastTrayReport(int lastTrayReport) {
        this.lastTrayReport = lastTrayReport;
    }
    
    private int countReport = 0;
    
    public void setCountReport(int value) {
        countReport = value;
    }

    public int getLastMainReport() {
        return lastMainReport;
    }

    public void setLastMainReport(int lastMainReport) {
        this.lastMainReport = lastMainReport;
    }
    
    private List<Trays> trayses = new ArrayList<Trays>();
    
    private List<Connector> connects = new ArrayList<Connector>();
    
    private List<Cover> covers = new ArrayList<Cover>();
    
    private List<Metis> metises = new ArrayList<Metis>() ;
    
    public List<Report> reports = new ArrayList<Report>();
    
    public List<Profil> profils = new ArrayList<Profil>();
    
    public List<Stand> stands = new ArrayList<Stand>();
    
    public List<Bracket> bracket = new ArrayList<Bracket>();
    
    public List<Claims> claimses = new ArrayList<Claims>();
    
    public List<String> mass_list = new ArrayList<String>();
    
    public ObservableList<String> type_all_list;
    
    public ObservableList<String> type_obraz_list;
    
    public ObservableList<Trays> tlist;
    
    public ObservableList<Trays> flist;
    
    public ObservableList<Cover> cov_list;
    
    public ObservableList<Connector> con_list;
    
    public ObservableList<String> type_list;
    
    public ObservableList<String> source_list;
    
    public ObservableList<String> height_list;
    
    public ObservableList<String> width_list;
    
    public ObservableList<String> length_list;
    
    public ObservableList<String> metis_list;
    
    public List<Metis> metislinks;
    
    public ObservableList<String> addtray_types;
    
    public ObservableList<String> step_list;
    
    public ObservableList<Unit> unit_list;
    
    public ObservableList<Float> ths_list;
    
    public ObservableList<Stands> claim_list;
    
    public ObservableList<Profil> profil_list;
    
    public ObservableList<Stand> stand_list;
    
    public ObservableList<Bracket> bracket_list;
    
    public ObservableList<Types> type_stand_list;
    
    public ObservableList<Types> type_profil_list;
    
    public ObservableList<String> lenght_stand_list;
    
    public ObservableList<String> lenght_profil_list;
    
    public ObservableList<String> height_stand_list;
    
    public ObservableList<String> height_profil_list;
    
    public ObservableList<String> ths_stand_list;
    
    public ObservableList<String> ths_profil_list;
    
    public ObservableList<String> type_bracket_list;
    
    public ObservableList<String> length_bracket_list;
    
    public ObservableList<String> ths_bracket_list;
    
    public ObservableList<Division> dividers;
    
    public Illustration illustrat;
    
    public String claimStr = "";
    
    public Trays selectTray;
    
    private static Model instance;
    
    public static Model getInstance() {
        if (instance==null) {
            instance = new Model();
        }
        return instance;
    }
    

    
    public Model(){
        
        
        
        trayses = TraysFunctions.selectTrays();
        connects = ConnectorFunctions.selectConnector();
        reports.add(new Report());
        reports.add(new Report());
        reports.add(new Report());
        reports.add(new Report());
        reports.add(new Report());
        
        dividers = FXCollections.observableArrayList(DivisionFunctions.selectDivision());
        
        metislinks = new ArrayList<Metis>();
        
        int t = 201;
        covers = CoverFunctions.selectCoverByType(t);
        
        cov_list = FXCollections.observableArrayList(covers);
        
        System.out.println("Con size "+connects.size());
        con_list = FXCollections.observableArrayList(connects);
        
        tlist = FXCollections.observableArrayList(trayses);
        flist = FXCollections.observableArrayList(trayses);
        
        List<AdditionTrays> additions = AdditionTraysFunctions.selectAddTrayByType(101);
        addtrays_list = FXCollections.observableArrayList(additions);
        
        
        List<Float> thslist = new ArrayList<Float>();
        thslist.add(2.0f);
        thslist.add(2.5f);
        thslist.add(3.0f);
        thslist.add(3.5f);
        ths_list = FXCollections.observableArrayList(thslist);
        
        
        List<Stands> cllist = new ArrayList<Stands>();
        cllist.add(new Stands("П-образный профиль"));
        cllist.add(new Stands("С-образный профиль"));
        
        claim_list = FXCollections.observableArrayList(cllist);
        
        
        List<String> steplist = new ArrayList<String>();
        steplist.add("0.8");
        steplist.add("1.0");
        steplist.add("1.5");
        steplist.add("1.75");
        steplist.add("2.0");
        steplist.add("2.5");
        steplist.add("3.0");
        steplist.add("4.0");
        steplist.add("6.0");
        step_list = FXCollections.observableArrayList(steplist);
        
        List<Unit> listofunit = UnitFunctions.getAllUnit();
        unit_list = FXCollections.observableArrayList(listofunit);
        
        
        List<String> typelist = (new ArrayList<String>());
        
        typelist.add("");
        typelist.add("Листовой перфорированный");
        typelist.add("Листовой неперфорированный");
        typelist.add("Листовой перфорированный тяжелой серии");
        typelist.add("Листовой неперфорированный тяжелой серии");
        typelist.add("Лоток перфорированный для больших расстояний");
        typelist.add("Лоток лестничный усиленный");
        typelist.add("Кабельрост лестничный усиленный");
        typelist.add("Лоток сетчатый");
        typelist.add("Канал усиленный световой");
        
        type_list = FXCollections.observableArrayList(typelist);
        
        List<String> sourcelist = (new ArrayList<String>());
        
        sourcelist.add("");
        sourcelist.add("Исполнение 1");
        sourcelist.add("Исполнение 2");
        sourcelist.add("Исполнение 3");
        sourcelist.add("Исполнение 4");
        sourcelist.add("Исполнение 5");
        sourcelist.add("Исполнение 6");
        
        source_list = FXCollections.observableArrayList(sourcelist);
        
        List<String> metislist = new ArrayList<String>();
        metislist.add("No items");
        metis_list = FXCollections.observableArrayList(metislist);
        
        List<String> heightlist = new ArrayList<String>();
        
        heightlist.add("");
        heightlist.add("25");
        heightlist.add("35");
        heightlist.add("40");
        heightlist.add("50");
        heightlist.add("60");
        heightlist.add("75");
        heightlist.add("85");
        heightlist.add("100");
        heightlist.add("105");
        heightlist.add("110");
        heightlist.add("120");
        heightlist.add("150");
        heightlist.add("160");
        heightlist.add("200");
        
        height_list = FXCollections.observableArrayList(heightlist);
        
        List<String> widthlist = new ArrayList<String>();
        
        widthlist.add("");
        widthlist.add("50");
        widthlist.add("60");
        widthlist.add("100");
        widthlist.add("150");
        widthlist.add("200");
        widthlist.add("250");
        widthlist.add("300");
        widthlist.add("400");
        widthlist.add("500");
        widthlist.add("600");
        
        width_list = FXCollections.observableArrayList(widthlist);
        
        
        List<String> lengthlist = new ArrayList<String>();

        lengthlist.add("");
        lengthlist.add("2000");
        lengthlist.add("2500");
        lengthlist.add("3000");
        lengthlist.add("3500");
        lengthlist.add("4000");
        lengthlist.add("4500");
        lengthlist.add("6000");
        lengthlist.add("9000");

        length_list = FXCollections.observableArrayList(lengthlist);
        
       
        initAddTrays();
    }
    
    
    private void initAddTrays(){
        List<AdditionTrays> additions = AdditionTraysFunctions.selectAddTrays();
        //addtrays_list = FXCollections.observableArrayList(additions);
        addTlist = FXCollections.observableArrayList(additions);
        addFlist = FXCollections.observableArrayList(additions);

        /*
        List<String> addtraylist = new ArrayList<String>();
        addtraylist.add("");
        addtraylist.add("горизонтальный поворот трассы 45"); //101
        addtraylist.add("горизонтальный поворот трассы 90"); //102
        addtraylist.add("вертикальный внутренний на 45º"); //103
        addtraylist.add("вертикальный внутренний на 90º"); //104
        addtraylist.add("вертикальный внешний на 45º"); //105
        addtraylist.add("вертикальный внешний на 90º"); //106
        addtraylist.add("вертикальный шарнирный"); //107
        addtraylist.add("тройниковый"); //108
        addtraylist.add("крестообразный"); //109
        addtraylist.add("ответвительный"); //110
        addtraylist.add("правая редукция");//141, 159
        addtraylist.add("левая редукция"); //140, 158?
        addtraylist.add("горизонтальный изменяемый поворота трассы на 0º-90º"); //115
        addtray_types = FXCollections.observableArrayList(addtraylist);
        */
        addtraylist = new ArrayList<String>();
        addtraylist.add(" ");
        addtray_types = FXCollections.observableArrayList(addtraylist);

                
        List<String> addsourcelist = new ArrayList<String>();
        addsourcelist.add("");
        addsourcelist.add(" 1  (SR)");
        addsourcelist.add(" 2  (HZ)");
        addsourcelist.add(" 3  (G)");
        addsourcelist.add(" 4  (X)");
        addsourcelist.add(" 5  (L)");
        addsourcelist.add(" 6  (RL)");
        addsource_list = FXCollections.observableArrayList(addsourcelist);
        
        List<String> addwidthlist = new ArrayList<String>();
        addwidthlist.add("");
        addwidthlist.add("50");
        addwidthlist.add("100");
        addwidthlist.add("150");
        addwidthlist.add("200");
        addwidthlist.add("250");
        addwidthlist.add("300");
        addwidthlist.add("400");
        addwidthlist.add("500");
        addwidthlist.add("600");
        addwidthlist.add("700");
        addwidthlist.add("800");
        addwidthlist.add("900");
        addwidthlist.add("1000");
        addwidth_list = FXCollections.observableArrayList(addwidthlist);
        
        List<String> addheightlist = new ArrayList<String>();
        addheightlist.add("");
        addheightlist.add("25");
        addheightlist.add("40");
        addheightlist.add("50");
        addheightlist.add("60");
        addheightlist.add("70");
        addheightlist.add("75");
        addheightlist.add("80");
        addheightlist.add("85");
        addheightlist.add("100");
        addheightlist.add("110");
        addheightlist.add("120");
        addheightlist.add("150");
        addheightlist.add("160");
        addheightlist.add("200");
        addheight_list = FXCollections.observableArrayList(addheightlist);
        
        List<String> addthicknesslist = new ArrayList<String>();
        addthicknesslist.add("");
        addthicknesslist.add("0,8");
        addthicknesslist.add("1,0");
        addthicknesslist.add("1,2");
        addthicknesslist.add("1,5");
        addthicknesslist.add("2,0");
        addthicknesslist.add("2,5");
        addthicknesslist.add("3,5");
        addthicknesslist.add("4,5");
        addthicknesslist.add("5,5");
        addthickness_list = FXCollections.observableArrayList(addthicknesslist);
    }
    
    
    
    public Report getReport( int ind) {
        return reports.get(ind);
    }
    
    public Report getParentReport(int ind) {
        
        Report res = null;
        for (Report report : reports) {
            
                if (report.getParent()==ind){
                    res = report;
                    lastReportIndex = ind;
                    return res;
                }
        }
        
        return res;
    }
    
    public Report getLastPrevPrevReport() {
        return reports.get((countReport-3));
    }
    
    public Report getLastPrevReport() {
        return reports.get((countReport-2));
    }
    
    public Report getLastReport() {
        return reports.get((countReport-1));
    }
    
    public void addReport(Report rep){
        rep.setId(countReport);
        if (rep.getIndex() == TRAY_INDEX) {
            lastTrayReport = rep.getId();
        }
        if (rep.getIndex() < 3) {
                lastMainReport = rep.getId();
                rep.setParent(0);
        }
        if (rep.getIndex() == 3) {
            lastSecondReport = rep.getId();
            rep.setParent(0);
        } else {
            if (rep.getIndex() > 3) {
                if (lastSecondReport > lastMainReport) {
                    System.out.println("Add second report parent");
                    rep.setParent(lastSecondReport);
                } else {
                    System.out.println("Add main report parent");
                    rep.setParent(lastMainReport);
                }
            }
        }
        countReport++;    
        reports.add(rep);
    }
    
    
    public void updateReports(int id,Report rep){
        reports.set(id, rep);
    }
    
    public void updateStandFilter(SearchData data) {
        stand_list.clear();
        stand_list.addAll(StandFunctions.selectByFilter(data));
        
    }
    
    public void updateProfilFilter(SearchData data) {
        profil_list.clear();
        profil_list.addAll(ProfilFunctions.selectByFilter(data));
    }
    
    public void updateBracketFilter(SearchData data){
        bracket_list.clear();
        bracket_list.addAll(BracketFunctions.selectByFilter(data));
    }
    
    public void addClassesByStand() {
            List<Types> typestands = new ArrayList<Types>();
             typestands.add(new Types(0,"","","",""));
             typestands.addAll(TypesFunctions.getTypeList("Stand"));
             type_stand_list.clear();
             type_stand_list.addAll(typestands); 
             
             
             List<Types> typeprofils = new ArrayList<Types>();
             typeprofils.add(new Types(0,"","","",""));
             typeprofils.addAll(TypesFunctions.getTypeList("Profil"));
             type_profil_list.clear();
             type_profil_list.addAll(typeprofils);
    }
    
    
    public void updateClassByStand(boolean isIt) {
        if (isIt) {
            List<Types> typestands = new ArrayList<Types>();
             typestands.add(new Types(0,"","","",""));
             typestands.addAll(TypesFunctions.getTypeList("Stand"));
             type_stand_list.clear();
             type_stand_list.addAll(typestands); // ???
             
             type_profil_list.clear();
             type_profil_list.add(new Types(0,"","","",""));
             /// ???
        } else {
            type_profil_list.clear();
             type_profil_list.add(new Types(0,"","","",""));
            type_profil_list.addAll(TypesFunctions.getTypeList("Profil"));
             
             
            type_stand_list.clear();
            type_stand_list.add(new Types(0,"","","",""));
        }
    }
    
    
    public void initProfilsAndStands(){
        
        if (profils.isEmpty() && stands.isEmpty() && bracket.isEmpty()) {
            
            List<String> typebrackets = Arrays.asList("","SMA","SM","СНК","BSU","BSI");
            type_bracket_list = FXCollections.observableArrayList(typebrackets);
            
            List<String> typeslist = Arrays.asList("","СНК","SM","НК","BS");
            type_all_list = FXCollections.observableArrayList(typeslist);
            
            List<String> obrazlist = Arrays.asList("","S-образный","П-образный","I-образный","Z-образный","L-образный");
            type_obraz_list = FXCollections.observableArrayList(obrazlist);
            
            
            List<String> thsbracket = Arrays.asList("","2,0", "2,5", "3,0","4,0","5,0","8,0");
             ths_bracket_list = FXCollections.observableArrayList(thsbracket);
            
            List<String> braket_length = new ArrayList<String>();
            braket_length.add("");
            braket_length.add("0-60");
            braket_length.add("60-80");
            braket_length.add("80-100");
            braket_length.add("100-120");
            braket_length.add("120-150");
            braket_length.add("150-170");
            braket_length.add("170-190");
            braket_length.add("190-220");
            
            length_bracket_list = FXCollections.observableArrayList(braket_length); 
             
            
             List<String> thsstands = Arrays.asList("","1,5","2,0", "2,5", "3,0");
             ths_stand_list = FXCollections.observableArrayList(thsstands);
            
             
            List<Types> typestands = new ArrayList<Types>();
             typestands.add(new Types(0,"","","",""));
             typestands.addAll(TypesFunctions.getTypeList("Stand"));
             type_stand_list = FXCollections.observableArrayList(typestands);
             
             
             List<Types> typeprofils = new ArrayList<Types>();
             typeprofils.add(new Types(0,"","","",""));
             typeprofils.addAll(TypesFunctions.getTypeList("Profil"));
             type_profil_list = FXCollections.observableArrayList(typeprofils);
             
            
            List<String> stands_length = new ArrayList<String>();
            stands_length.add("");
            stands_length.add("100-115-130");
            stands_length.add("135-150-175");
            stands_length.add("180-200-215");
            stands_length.add("220-230-250");
            stands_length.add("270-280-300");
            stands_length.add("315-320-330");
            stands_length.add("350-400-415");
            stands_length.add("420-430-450");
            stands_length.add("480-500-515");
            stands_length.add("520-550-565");
            stands_length.add("600-615-620");
            stands_length.add("650-700-715");
            stands_length.add("750-800-810-815");
            stands_length.add("900-910-915-950");
            stands_length.add("1000-1010-1015-1020");
            stands_length.add("1100-1110-1300");
            
            lenght_stand_list = FXCollections.observableArrayList(stands_length);
            
            
            List<String> stands_height = new ArrayList<String>();
            stands_height.add("");
            stands_height.add("0-60");
            stands_height.add("60-80");
            stands_height.add("80-100");
            stands_height.add("100-120");
            stands_height.add("120-150");
            stands_height.add("150-170");
            stands_height.add("170-190");
            stands_height.add("190-220");
            
            height_stand_list = FXCollections.observableArrayList(stands_height);
            
            
            
            List<String> profils_length = Arrays.asList(
                    "","140-200", "200-300", "300-400", "400-500", 
                    "500-600", "600-700","700-800","800-900",
                    "900-1000","1000-1200","1200-1500","1500-2000",
                    "2000-2500","2500-3000","3000-4500","4500-6000");
            
            
            lenght_profil_list = FXCollections.observableArrayList(profils_length);
            
            
            List<String> profils_height = Arrays.asList(
                    "", "21", "32", "41", "50", 
                    "64", "70","80","82","100");
            
            height_profil_list = FXCollections.observableArrayList(profils_height);
            
            List<String> thsprofils = Arrays.asList("","2,0", "2,5", "3,0", "3,5", "4,0", "4,5", "5,0");
            ths_profil_list = FXCollections.observableArrayList(thsprofils);
            
            
            profils = ProfilFunctions.selectProfilAll();

            profil_list = FXCollections.observableArrayList(profils);

            stands = StandFunctions.selectStandAll();

            stand_list = FXCollections.observableArrayList(stands);

            bracket = BracketFunctions.selectBracketAll();

            bracket_list = FXCollections.observableArrayList(bracket);
        
        }
    }
    
    public void clearProfilsandStands (int index) {
        if (index==1) {
            
            stands = StandFunctions.selectStandAll();

            stand_list.clear();
            stand_list.addAll(stands);
        } else if (index==2) {
            profils = ProfilFunctions.selectProfilAll();

            profil_list.clear();
            profil_list.addAll(profils);
            
        } else if (index==3) {
            
            bracket = BracketFunctions.selectBracketAll();

            bracket_list.clear();
            bracket_list.addAll(bracket);
        }
        
    };
    
    
    public Integer updateProfilsAndStands(Profil profil, Stand stand, int source){
        int indOf = 0;
        if (profil == null) {
            if (stand != null && source==1) {
                profil_list.clear();
                profil_list.addAll(ProfilFunctions.selectProfilByStand(stand));
                bracket_list.clear();
                bracket_list.addAll(BracketFunctions.selectBracketByClass(stand.getClassId()));
                
            }
        } else {
            if (source==1){
                
                Object sel = ((Object)profil);
                profil_list.clear();
                profil_list.addAll(ProfilFunctions.selectProfilByStand(stand));
                int indexProfil = profil_list.indexOf(sel);
                indOf = indexProfil;
                System.err.println("Sel  "+sel);
                bracket_list.clear();
                bracket_list.addAll(BracketFunctions.selectBracketByClass(stand.getClassId()));
            }
            
            if (stand != null && source==2) {
                Stand sel2 = (stand);
                stand_list.clear();
                stand_list.addAll(StandFunctions.selectByClass(profil.getClassId()));
                indOf = stand_list.lastIndexOf(sel2);
                for (int i = 0; i < stand_list.size(); i++) {
                    Stand next = stand_list.get(i);
                    if (next.getArticul().equalsIgnoreCase(sel2.getArticul())) {
                       indOf = i;
                       
                    }
                }
                
                System.err.println("Sel  "+sel2);
                bracket_list.clear();
                bracket_list.addAll(BracketFunctions.selectBracketByProfil(profil.getType()));
            } else if (source==2) {
                stand_list.clear();
                stand_list.addAll(StandFunctions.selectStandByProfil(profil.getType()));
                bracket_list.clear();
                bracket_list.addAll(BracketFunctions.selectBracketByClass(profil.getClassId()));
            }
        }
        return indOf;
    }
    
    public List<LinkStands> getLinkStandsMetis(String standT, String profilT) {
        List<LinkStands> linkSts = new ArrayList<LinkStands>();
        linkSts = LinksFunctions.getLikedMetis(standT, profilT);
        if (linkSts.size() == 0) {
            linkSts = null;
        }
        return linkSts;
    }
    
    public void updateTrays(){
        tlist = FXCollections.observableArrayList(trayses);
        
    }
    
    public void updateIllustrat(Unit unit){
        illustrat = UnitFunctions.getIllustrationByUnit(unit);
    }
    
    public void updateDividers(Trays tray){
        List<Division> divisions = new ArrayList<Division>();
        divisions = DivisionFunctions.selectDivisionByTray(tray);
        dividers.clear();
        if (divisions.size()>0)
            dividers.addAll(divisions);
        
        
    }
    
    public void updateCover(Trays tray) {
        List<Cover> covers1 = null;
        selectTray = tray;
        if (tray==null) {
            con_list.clear();
            return ;
        } else {
            cov_list.clear();
        }
        
        
        covers1 = CoverFunctions.selectCoverByTray(tray);
        if (covers1!=null)
            if (covers1.size()>0) {
                System.out.println("Covers:");
                cov_list.clear();
                cov_list.addAll(covers1);
                
                //notifyObservers();
                System.out.println("Yes covers "+covers1.size());
            }
    }
    
    
    public void updateConnectors(Trays tray){
        List<Connector> cons = null;
        List<Connector> sk = null;
        
        selectTray = tray;
        if (tray==null) {
            con_list.clear();
            return ;
        }
        cons = ConnectorFunctions.selectConnectorByTray(tray);
        sk = ConnectorFunctions.selectScobaByTray(tray);
        if (cons != null)    
        if (cons.size() > 0)
        {
            con_list.clear();
            con_list.addAll(cons);
            
            notifyObservers();
            System.out.println("Yes "+cons.size());
            
        }else{
            
            System.err.println("No select con");
        }
        if (sk!=null){
            if (cons==null) {
                con_list.clear();
                
            }else {
                if (cons.size()==0)
                    con_list.clear();
            }
            
            con_list.addAll(sk);
            notifyObservers();
        } else {
            System.err.println("No select sk");
        }
 
    }
    
    
    public void updateMetis(Trays tray, Connector con){
        
        if (tray==null || con==null) {
            metis_list.clear();
            return ;
        }
        List<Metis> mlist = null;
        System.out.println("Tray height on 2x = "+tray.getHeight());
        mlist = MetisFunctions.getMetisByConnector(con);
        if (mlist != null && tray!=null)    
        if (mlist.size() > 0)
        {
            metis_list.clear();
            mass_list.clear();
            for (int i = 0; i < MetisFunctions.count_con; i++) {
                
            
                for (Metis metis : mlist) {
                    metis_list.addAll(metis.getArticul());
                    mass_list.add(metis.getMass());
                }
            }
            notifyObservers();
            System.out.println("Yes "+mlist.size());
            
        }else{
            System.err.println("No");
        }

    }
    
    public void updateMetisByTrays() {
        
    }
    
    public void updateClaimsByTrays(int standType,Trays tray, boolean ishard, int cover) {
        claimses.clear();
        List<LinkTrays> links = LinksFunctions.getLinkedTrays(standType,tray, ishard, cover);
        if (links !=null)
            if (links.size() > 0 ) {
                try {
                    System.err.println(" Yes links succsess "+links.size());

                    Thread.sleep(750);

                } catch (InterruptedException ex) {
                    Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
                }
                List<Claims> cls1 = ClaimsFunctions.getClaimsByTray(tray, links);
                if (cls1 != null) {
                    claimses.addAll(cls1);
                    System.out.println("Claims count: "+cls1.size());
                }
            }
        
    }
    
    
    public void updateMetisByStand(int stand_type_id, Trays tray, boolean hard, int covis){
        List<Metis> mlist = null;
        List<LinkTrays> links = null;
        links = LinksFunctions.getLinkedMetis(stand_type_id, tray, hard, covis);
        
        try {
            Thread.sleep(500);
        } catch (InterruptedException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        if (links != null)    
        if (links.size() > 0)
        {
            mlist = MetisFunctions.getMetisByLinkId(links);
            //metis_list.clear();
            metislinks.clear();
            if (mlist!=null)
            for (Metis metis : mlist) {
                System.out.println("Get metis articul " + metis.getArticul());
                metislinks.add(metis);
                //metis_list.addAll(metis.getArticul());
                for (int i = 0; i < MetisFunctions.count_stand-1; i++) {
                    
                    //metis_list.addAll(metis.getArticul());
                }
            }
            
            notifyObservers();
            System.out.println("Yes "+mlist.size());
            
        }else{
            System.err.println("No");
        }

    }
    
    
    
    public ObservableList<Trays> filtered(SearchData data, boolean isSpec) {
        
        
        
        if (isSpec && data.getArticul() != null && !data.getArticul().equalsIgnoreCase("")) {
            
            flist.clear();
            for (Trays tray: tlist) {
                    String articul = tray.getArt();
                    if (articul.contains(data.getArticul())) {
                        flist.add(tray);
                    }
                }
            if (flist.size() > 0) {
                notifyObservers();
            }
        } else {
            List<Trays> list = null;
            list = TraysFunctions.selectTraysByFilter(data);
            if (list!=null)
                if (list.size()>0) {
                    flist.clear();
                    flist.addAll(list);
                    notifyObservers();
                }else {
                    flist.clear();
                }
        }
        if (selectTray == null) {
            //flist.clear();
            return null;
        }
        return null;
    }
    
    public void filteredAll(SearchData data) {
        
    }
    
    
    public void filterOther(Trays tray) {
        
    }

    public void updateIllustratTray(Trays selectedItem) {
        illustrat = TraysFunctions.getIllustrationByTray(selectedItem);
        if (illustrat!=null) {
            claimStr = illustrat.getDescription();
        }else {
            claimStr = "";
        }
    }
    
    public ObservableList<AdditionTrays> filteredAcs(SearchData data) {
        System.out.println("Вошел в filteredAcs без треев");

        List<AdditionTrays> list = null;
        list = AdditionTraysFunctions.selectAddTraysByFilter(data);
        if (list!=null){
            if (list.size()>0) {
                System.out.println("Модель получила список добавочных треев " + list.size());
                addFlist.addAll(list);
            }else {
                System.out.println("Модель получила пустой список добавочных треев");
            }                
        } else {
            System.out.println("Модель получила неинициализированный список добавочных треев");
        }

        System.out.println("Итоговый размер списка в модели (треи + добавочные треи) = " + addFlist.size());
        
        return null;
    }    
  
    public ObservableList<AdditionTrays> filteredAcs(SearchData data, boolean isSpec, List traysList) {
        System.out.println("Вошел в filteredAcs");

        if (data == null){
            System.out.println("Фильтр аксессуаров null");
            return null;
        }

        addFlist.clear();

        if (isSpec && data.getArticul() != null && !data.getArticul().equalsIgnoreCase("")) {
            System.out.println("Пытаюсь отфильтровать filteredAcs по артикулу");
            for (AdditionTrays tray: addTlist) {
                    String articul = tray.getArticul();
                    if (articul.contains(data.getArticul())) {
                        addFlist.add(tray);
                    }
                }
            if (addFlist.size() > 0) {
                notifyObservers();
            }
        } else {
            System.out.println("начинаю формировать filteredAcs");

            if ((traysList != null) && (!traysList.isEmpty())){
                addFlist.addAll(traysList);
            } else {
                System.out.println("список аксессуарных треев null или пустой");                        
            }
                    
            filteredAcs(data);
        }
        /*
        if (selectTray == null) {
            return null;
        }*/
        return null;
    }    
    
}
