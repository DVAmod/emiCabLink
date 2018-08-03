package emidetail.manager;

import emidetail.manager.controls.Command;
import emidetail.manager.controls.EditTaskCommand;
import emidetail.manager.controls.ReportCommand;
import java.io.IOException;
import java.util.List;
import emidetail.database.beans.Report;
import emidetail.manager.controls.ProjectActionCommand;

/**
 * MainController is manager of controls
 * command
 * @author Denis_Vitovtov
 *
 */
public class ActionController {

    /**
    * Serial version uid
    */
    private static final long serialVersionUID = 3L;


    private Command command;
    
    private List<Report> data;
    
    private String out;
    
    private int types;

    public ActionController() {

    }
    
    public String getOutput() {
        return out;
    }
    
    public void addData(List<Report> list){
        data = list;
    }
    
    public List<Report> getData() {
        return data;
    }


    public void process(String strCommand,String strAttr) {
        String next = null;
        if (strCommand.equalsIgnoreCase(Command.LOAD_PR)) {
            command = new ProjectActionCommand();
        }
        if (strCommand.equalsIgnoreCase(Command.SAVE_PR)) {
            command = new ProjectActionCommand();
            ((ProjectActionCommand)command).loadData(data);
        }
        if (strCommand.equalsIgnoreCase(Command.REPORT_S)) {
            command = new ReportCommand();
            ReportCommand rc = (ReportCommand)command;
            rc.setReport(data);
        }

        
        try {
            next = command.execute(strAttr);
            out = next;
            if ("yes".equalsIgnoreCase(next)) {
                ProjectActionCommand pa = (ProjectActionCommand)command;
                data = pa.getData();
            }
        } catch (IOException e) {
            out = null;
        }
        
        
    }
}