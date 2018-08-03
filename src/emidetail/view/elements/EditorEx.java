/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package emidetail.view.elements;

import emidetail.database.Model;
import emidetail.database.beans.Stands;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import org.controlsfx.control.table.TableRowExpanderColumn;

/**
 *
 * @author DVA
 */
public class EditorEx {
    
    private GridPane editor;
    
    private List<Stands> stands;
    
    public void generateStandsData(String data, boolean doubles, int count) {
        stands = new ArrayList<Stands>();
        for (int i = 0; i < count; i++) {
            stands.add(new Stands(data, doubles));
        }
        
    }
    
    public ObservableList<Stands> getStandsData() {
        return FXCollections.observableArrayList(stands);   
    }
    
    public EditorEx() {
        editor = new GridPane();
        editor.setPadding(new Insets(10));
    }
    
    public GridPane getEditor(TableRowExpanderColumn.TableRowDataFeatures<Stands> param){
        editor = new GridPane();
        Stands st = param.getValue();
        TextField count = new TextField(String.valueOf(st.getCount()));
        TextField length = new TextField(String.valueOf(st.getLength()));
        ComboBox<Float> ths = new ComboBox<Float>();
        ths.setItems(Model.getInstance().ths_list);
        ths.setValue(Float.valueOf(String.valueOf(st.getThs())));
        CheckBox doub = new CheckBox("Двойная");
        
        editor.addRow(0, new Label("Кол., шт"),count);
        editor.addRow(1, new Label("Длина, мм "),length);
        if (st.isDoub()) {
            doub.setSelected(false);
            editor.addRow(2, new Label("Толщина, мм"),ths, doub);
        } else {
            editor.addRow(2, new Label("Толщина, мм"),ths);
        }
        
        
        
        Button btn1 = new Button("Сохранить");
        Button btn2 = new Button(" Отменить");
        
        btn1.setOnAction(e -> {
            st.setCount(Integer.valueOf(count.getText()));
            st.setLength(Integer.valueOf(length.getText()));
            st.setThs(ths.getValue());
            st.setDoub(doub.isSelected());
            param.toggleExpanded();
        });
        
        btn2.setOnAction(e -> {
            param.toggleExpanded();
        });
        
        editor.addRow(3, btn2, btn1);
        
        return editor;
    }
    
    
}
