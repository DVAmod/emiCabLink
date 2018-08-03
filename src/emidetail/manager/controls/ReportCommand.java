/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package emidetail.manager.controls;


import emidetail.database.beans.Report;
import java.io.FileOutputStream;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import org.jxls.common.Context;
import org.jxls.util.JxlsHelper;

/**
 *
 * @author DVAmod
 */
public class ReportCommand implements Command {
    
    private static final String IN_REPORT_1 = "/object_collection_template.xls";
    private static final String IN_REPORT_2 ="/object_kop_template.xls";
    private static final String OUT_1 = "object_collection_output.xls";
    private static final String OUT_2 = "object_kop_output.xls";
    
    
    private List<Report> reports;
    
    public void addReport(Report rep ) {
        reports.add(rep);
    }
    
    public void setReport(List<Report> rps) {
        reports = rps;
    }
    
    public ReportCommand() {
        reports = new ArrayList<Report>();
    }

    @Override
    public String execute(String command) throws IOException {
        //To change body of generated methods, choose Tools | Templates.
        int type = Integer.valueOf(command);
        String[] fileArgs = new String[2] ;
        if (type==1) {
            fileArgs[0] = IN_REPORT_1;
            fileArgs[1] = OUT_1;
        }
        if (type==2) {
            fileArgs[0] = IN_REPORT_2;
            fileArgs[1] = OUT_2;
        }
        
        //List<Report> reports = generateSampleReports();
        try(InputStream is = ReportCommand.class.getResourceAsStream(fileArgs[0])) {
            try (OutputStream os = new FileOutputStream(fileArgs[1])) {
                Context context = new Context();
                context.putVar("reports", reports);
                JxlsHelper.getInstance().processTemplateAtCell(is, os, context, "Result!A2");
                
            }
            return "rep";
        } catch(Exception e) {
            return null;
        }
        
    }

    private List<Report> generateSampleReports() {
        List<Report> reps = new ArrayList<Report>();
        
        for (int i = 0; i < 10; i++) {
            Report rep = new Report();
            rep.setTitle("Title "+i);
            rep.setArticul("SS"+i+"00TR30");
            rep.setCount(i);
            rep.setMass(10f);
            
            reps.add(rep);
        }
        
        
        return reps;
    }
    
}
