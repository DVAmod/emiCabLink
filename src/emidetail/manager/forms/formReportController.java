/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package emidetail.manager.forms;

import emidetail.database.Model;
import emidetail.database.beans.Report;
import emidetail.manager.ActionController;
import emidetail.manager.controls.Command;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import org.controlsfx.control.spreadsheet.GridBase;
import org.controlsfx.control.spreadsheet.SpreadsheetView;
import emidetail.view.elements.CommandLink;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.print.PageLayout;
import javafx.print.PageOrientation;
import javafx.print.Paper;
import javafx.print.PrintResolution;
import javafx.print.Printer;
import javafx.print.PrinterJob;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.transform.Scale;

/**
 * FXML Controller class
 *
 * @author DVAmod
 */
public class formReportController implements Initializable {
    
    private SpreadsheetView spreadSheetView;
    private ActionController controller ;
    
    
    @FXML
    private AnchorPane locSelectPane;
    @FXML
    private AnchorPane locReportPane;
    @FXML
    private AnchorPane locPrintPane;
    
    
    
    
    private void buildGrid(GridBase grid) {
        
    }
    
    public void generatePickers() {
        
    }
    
    private Map<Integer, Double> generateRowHeight() {
        Map<Integer, Double> rowHeight = new HashMap<>();
        rowHeight.put(3, 100.0);
        return rowHeight;
    }
    
    public void showReportData(List<Report> reports) {
        GridBase grid = new GridBase(30, 6);
        grid.setRowHeightCallback(new GridBase.MapBasedRowHeightFactory(generateRowHeight()));
        buildGrid(grid);
 
        spreadSheetView = new SpreadsheetView(grid);
        spreadSheetView.setShowRowHeader(true);
        spreadSheetView.setShowColumnHeader(true);
        spreadSheetView.setEditable(true);
        //spreadSheetView.getRowPickers().addAll(3);
        //spreadSheetView.getColumnPickers().addAll(0,2,4,6);

        generatePickers();
 
        spreadSheetView.getFixedRows().add(0);
        spreadSheetView.getColumns().get(0).setFixed(true);
        spreadSheetView.getColumns().get(1).setPrefWidth(250);
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        controller = new ActionController();
        GridPane content = new GridPane();
        content.setHgap(10);
        content.setVgap(5);
        // TODO
        Label headerLabel = new Label("Выбирете отчет из списка");
        headerLabel.setStyle("-fx-font-size: 1.5em;");

        CommandLink zooButton = new CommandLink("Внутренний отчет (Спецификация)",
                "Отчет в пределах организации и для проектных отделов");
        zooButton.setOnAction(e -> {
            System.out.println("Otchet 1   inner");
            try{
                controller.addData(Model.getInstance().reports);
                controller.process(Command.REPORT_S, "1");
                if (controller.getOutput()!=null)
                    Desktop.getDesktop().open(new File("object_collection_output.xls")); // /tmp/MyFirstExcel.xlsx
            } catch (IOException ex) {
                Logger.getLogger(formMainController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        CommandLink circusButton = new CommandLink("Отчет по прайсу ",
                "Основной вид отчета для внешних клиентов");
        circusButton.setOnAction(e -> {
            System.out.println("Otchet 2  export");
             try{
                controller.addData(Model.getInstance().reports);
                controller.process(Command.REPORT_S, "2");
                if (controller.getOutput()!=null)
                    Desktop.getDesktop().open(new File("object_kop_output.xls")); // /tmp/MyFirstExcel.xlsx
            } catch (IOException ex) {
                Logger.getLogger(formMainController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        CommandLink homeButton = new CommandLink("Отчет по акссесуарам", "Только выбранные отдельно акссесуары");
        circusButton.setOnAction(e -> {
            System.out.println("Otchet 3  accsesuars");
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Данный вид отчета находится в подготовке...", ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
            alert.setHeaderText("Внимание !!!");
            alert.show();
        });
        
        content.add(headerLabel, 1, 0);
        content.add(zooButton, 1, 1);
        content.add(circusButton, 1, 2);
        content.add(homeButton, 1, 3);

        locSelectPane.getChildren().add(content);
    }    

    @FXML
    private void handleExport(ActionEvent event) {
        ActionController controller = new ActionController();
        controller.addData(Model.getInstance().reports);
        try{
            controller.process(Command.REPORT_S, "");
        } catch(Exception e) {
            System.err.println("Error export");
        }
        if (controller.getOutput()!=null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Данные отчета сохранены успешно", ButtonType.YES);
            //alert.setHeaderText("Внимание !!!");
            alert.show();
        } 
            
    }

    @FXML
    private void handlePrint(ActionEvent event) {
        Node root = locPrintPane;
        Printer defaultprinter = Printer.getDefaultPrinter();
        PrinterJob job = PrinterJob.createPrinterJob(defaultprinter);

        if(job != null && job.showPrintDialog(root.getScene().getWindow())){
            Printer printer = job.getPrinter();
            PageLayout pageLayout = printer.createPageLayout(Paper.A4, PageOrientation.LANDSCAPE, Printer.MarginType.HARDWARE_MINIMUM);

            root.setVisible(false);
            root.setManaged(false);

            double width = root.getBoundsInLocal().getWidth();
            double height = root.getBoundsInLocal().getHeight();

            PrintResolution resolution = job.getJobSettings().getPrintResolution();

            width /= resolution.getFeedResolution();

            height /= resolution.getCrossFeedResolution();

            double scaleX = pageLayout.getPrintableWidth()/width/600;
            double scaleY = pageLayout.getPrintableHeight()/height/600;

            Scale scale = new Scale(scaleX, scaleY);

            root.getTransforms().add(scale);

            boolean success = job.printPage(pageLayout, root);
            if(success){
                job.endJob();
            }
            root.getTransforms().remove(scale);
        }
        root.setManaged(true);
        root.setVisible(true);
    }
    
}
