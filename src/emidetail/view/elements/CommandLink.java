/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package emidetail.view.elements;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import org.controlsfx.glyphfont.FontAwesome;
import org.controlsfx.glyphfont.Glyph;

/**
 *
 * @author DVAmod
 */
public class CommandLink extends Button {
        public CommandLink(String header, String text) {
            GridPane graphicGrid = new GridPane();
            graphicGrid.setHgap(5);
            graphicGrid.setVgap(5);

            // Create an icon using FontAwesome.
            Glyph icon = new Glyph("FontAwesome","GEAR");
            icon.color(Color.GREEN);

            Label btnHeaderLabel = new Label(header);
            btnHeaderLabel.setStyle("-fx-font-size: 1.5em;");

            Label detailsLabel = new Label(text);

            graphicGrid.add(icon, 0, 0);
            graphicGrid.add(btnHeaderLabel, 1, 0);
            graphicGrid.add(detailsLabel, 1, 1);

            setGraphic(graphicGrid);
        }
    }
