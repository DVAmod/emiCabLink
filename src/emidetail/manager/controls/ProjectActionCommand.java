/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package emidetail.manager.controls;

import emidetail.database.Model;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import emidetail.database.beans.Report;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.stage.FileChooser;

/**
 *
 * @author DVAmod
 */
public class ProjectActionCommand implements Command {
    
    
    private List<Report> reports = new ArrayList<Report>();
    private FileChooser fileChooser = null;
    private File file = null;
    
    public void loadData(List data) {
        reports = data;
        
    }
    
    public List<Report> getData() {
        return reports;
    }
    
    private void SaveFile(File fileSaved) {
        System.out.println("File saved "+ fileSaved.getAbsolutePath());
        if (reports!=null) {
            ObjectOutputStream oos = null;
            FileOutputStream fout = null;
            try{
                fout = new FileOutputStream(fileSaved, true);
                oos = new ObjectOutputStream(fout);
                oos.writeObject(reports);
            } catch (Exception ex) {
                ex.printStackTrace();
            } finally {
                if(oos != null){
                    try {
                        oos.close();
                    } catch (IOException ex) {
                        Logger.getLogger(ProjectActionCommand.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } 
            }
        }
        
    }
    
    private void LoadFile(File fileLoad) {
        System.out.println(" File load "+ fileLoad.getAbsolutePath());
        
        ObjectInputStream objectinputstream = null;
        try {
            FileInputStream streamIn = new FileInputStream(fileLoad);
            objectinputstream = new ObjectInputStream(streamIn);
            Collection readCol = (Collection) objectinputstream.readObject();
            System.out.println("Collection ");
            List<Report> readCase = new ArrayList<Report>();
            int i = 0;
            for (Object object : readCol) {
                i++;
                //System.out.println(""+i+" - "+object.toString());
                Report readOne = (Report) object;
                //System.out.println("Rep "+readOne.getTitle()+"  "+readOne.getArticul()+" "+String.valueOf(readOne.getMass()));
                readCase.add(readOne);
            }
            System.out.println("Load Report size "+i);
            if (readCase.size()>0) {
                reports.clear();
                reports.addAll(readCase);
                Model.getInstance().reports.clear();
                Model.getInstance().reports.addAll(reports);
            }
            System.out.println("Report size "+reports.size());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(objectinputstream != null){
                try {
                    objectinputstream .close();
                } catch (IOException ex) {
                    Logger.getLogger(ProjectActionCommand.class.getName()).log(Level.SEVERE, null, ex);
                }
            } 
        }
    }

    @Override
    public String execute(String command) throws IOException {
        
        if (command != null) {
            
             fileChooser = new FileChooser();
  
            //Set extension filter
            FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Emi Detail Project (*.emipr)", "*.emipr");
            fileChooser.getExtensionFilters().add(extFilter);

            
            
            if (command.startsWith("save")) {
                //Show save file dialog
                file = fileChooser.showSaveDialog(null);
              
              if(file != null){
                  SaveFile( file);
              }
            } else
            if (command.startsWith("load")) {
                //Show save file dialog
                file = fileChooser.showOpenDialog(null);
                
                if(file != null){
                  LoadFile( file);
                  return "yes";
                }
                
            }
            
        }
        if (file!=null)
        return file.getPath();
        return null;
    }
    
}
