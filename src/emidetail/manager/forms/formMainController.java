/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package emidetail.manager.forms;


import emidetail.database.Model;
import emidetail.database.beans.AdditionTrays;
import emidetail.database.beans.Connector;
import emidetail.database.beans.Cover;
import emidetail.database.beans.Stand;
import emidetail.database.beans.Types;
import emidetail.database.beans.Profil;
import emidetail.database.beans.Bracket;
import emidetail.database.beans.Claims;
import emidetail.database.beans.Division;
import emidetail.database.beans.Illustration;
import emidetail.database.beans.LinkStands;
import emidetail.database.beans.Report;
import emidetail.database.beans.Stands;
import emidetail.database.beans.Trays;
import emidetail.database.beans.Unit;
import emidetail.database.beans.Metis;
import emidetail.database.utils.ClaimsFunctions;
import emidetail.database.utils.ConstructionFunctions;
import emidetail.database.utils.CoverFunctions;
import emidetail.database.utils.DivisionFunctions;
import emidetail.database.utils.MetisFunctions;
import emidetail.database.utils.TraysFunctions;
import emidetail.database.utils.TypesFunctions;
import emidetail.filter.SearchData;
import emidetail.manager.ActionController;
import emidetail.manager.controls.CablingCalc;
import emidetail.manager.controls.Command;
import emidetail.manager.controls.ReportCommand;
import emidetail.manager.controls.SectionValue;
import emidetail.manager.data.Gabarit;
import emidetail.view.elements.ConstructCablingView;
import emidetail.view.elements.EditorEx;
import emidetail.view.elements.ElemTreeView;
import java.awt.Desktop;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Locale;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;

import javafx.scene.control.Dialog;
import javafx.scene.control.Alert;
import javafx.scene.control.Accordion;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.Pagination;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Slider;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.control.ToolBar;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
import javafx.scene.control.TreeView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TreeItemPropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import org.controlsfx.control.action.Action;
import org.controlsfx.control.action.ActionGroup;
import org.controlsfx.control.action.ActionMap;
import static org.controlsfx.control.action.ActionMap.action;
import static org.controlsfx.control.action.ActionMap.actions;
import org.controlsfx.control.action.ActionProxy;
import org.controlsfx.control.action.ActionUtils;
import org.controlsfx.control.action.ActionUtils.ActionTextBehavior;
import org.controlsfx.control.table.TableRowExpanderColumn;

/**
 *
 * @author DVAmod
 */
public class formMainController implements Initializable, Observer {
    
    @FXML
    private Label label;
    @FXML
    private Pane endBar;  
    @FXML
    private Pane paneMenu;
    @FXML
    private ImageView logoImage2;
    @FXML
    private ImageView logoImage1;
    @FXML
    private TableView<Trays> locTrayView;
    @FXML
    private TableColumn<Trays, String> trayDescItem;
    @FXML
    private TableColumn<Trays, String> trayArtItem;
    @FXML
    private Pagination locPaginator;
    @FXML
    private TreeTableView<Report> locTreeView;
    @FXML
    private Pane filtredTrayPane;
    @FXML
    private ComboBox<String> locTrayType;
    @FXML
    private ComboBox<String> locTraySource;
    @FXML
    private ComboBox<String> locHeight;
    @FXML
    private ComboBox<String> locWidth;
    @FXML
    private TextField locArtEdit;
    @FXML
    private CheckBox locIP44Box;

    @FXML
    private TableColumn<Connector, String> conDescItem;
    @FXML
    private TableColumn<Connector, String> conArtItem;
    
    private static final String imagePath1 = "/emidetail/img/white.png";
    
    private static final String imagePath2 = "/emidetail/img/yellow.png";
    
    private static final String imagePath3 = "/emidetail/img/logo.png";
    
    private static final String imagePath4 = "/emidetail/img/Icon.png";
    
    private static final String imagePath5 = "/emidetail/img/EMI_icon.png";
    
    private boolean slide_anchor = false;
    
    private boolean slide_page = false;
    
    private int report_type = 1;
    
    private boolean isStand = true;
    
    private static double SLIDE_ANCHOR_CLOSE = 682;
    
    private static double SLIDE_ANCHOR_OPEN = 1240;

    public static void setSLIDE_ANCHOR_OPEN(double SLIDE_ANCHOR_OPEN) {
        formMainController.SLIDE_ANCHOR_OPEN = SLIDE_ANCHOR_OPEN;
    }
    
    private static double SLIDE_ANCHOR_PROJECT = 640;

    public static void setSLIDE_ANCHOR_PROJECT(double SLIDE_ANCHOR_PROJECT) {
        formMainController.SLIDE_ANCHOR_PROJECT = SLIDE_ANCHOR_PROJECT;
    }
    
    private static final InputStream input1 = formMainController.class.getResourceAsStream(imagePath1);
    
    private static final InputStream input2 = formMainController.class.getResourceAsStream(imagePath2);
    
    private static final InputStream input3 = formMainController.class.getResourceAsStream(imagePath3);
    
    private static final InputStream input4 = formMainController.class.getResourceAsStream(imagePath4);
    
    private static final InputStream input5 = formMainController.class.getResourceAsStream(imagePath5);
    
    private static final InputStream input6 = formMainController.class.getResourceAsStream(imagePath5);
    
    private static final InputStream input7 = formMainController.class.getResourceAsStream(imagePath5);
    
    private static final ImageView image1 = new ImageView(new Image(input1));
    
    private static final ImageView image2 = new ImageView(new Image(input2));
    
    private static final ImageView image3 = new ImageView(new Image(input5));
    
    private Collection<? extends Action> actions;
    
    private int indTray = 0;
    
    private ElemTreeView elementTree;
    
    private SearchData dataFilter;
    
    private SearchData standsFilter;
    
    private SearchData profilFilter;
    
    private SearchData bracketFilter;
    
    private SearchData acsDataFilter;
    
    private EditorEx editor;
    
    private Boolean isStandsLink = false;
    
    @FXML
    private ComboBox<String> locLength;
    @FXML
    private Slider sliderScores;
    @FXML
    private TextField locScores;
    @FXML
    private ImageView locSepProject;
    @FXML
    private Label locArtLabel;
    @FXML
    private Label locLengthLabel;
    private ComboBox<Connector> locConnector;
    @FXML
    private ComboBox<Cover> locCover;
    @FXML
    private TableView<Connector> locClampingView;
    @FXML
    private ProgressBar locProgressBar;
    @FXML
    private ImageView locBottomIcon;
    @FXML
    private TextField locStateEdit;
    @FXML
    private Label locState;
    @FXML
    private AnchorPane locTrayAnchor;
    @FXML
    private TitledPane locTiled1;
    @FXML
    private TitledPane locTiled2;
    @FXML
    private Spinner<Integer> locRoadEdit;
    @FXML
    private TitledPane locTiled3;
    @FXML
    private AnchorPane locProjectAnchor;
    @FXML
    private AnchorPane locMainAnchor;
    @FXML
    private AnchorPane locSecondAnchor;
    @FXML
    private Accordion locAccord;
    @FXML
    private ListView<String> locMetisView;
    @FXML
    private RadioButton locIsFixed;
    @FXML
    private RadioButton locIsClaim;
    @FXML
    private ComboBox<String> locSectionStep;
    @FXML
    private Button buttonFilterClear;
    @FXML
    private CheckBox locCheckTrayPr;
    @FXML
    private ListView<Unit> locUnitList;
    @FXML
    private ComboBox<String> locUnitConstruction;
    @FXML
    private TableView<Stands> locStandTable;
    @FXML
    private Spinner<Integer> locClaimSpinner;
    @FXML
    private TextField locUnitLength;
    private TextField locUnitThs;
    @FXML
    private ImageView locUnitImage;
    @FXML
    private TextField locProfilEdit;
    @FXML
    private TextField locStandEdit;
    @FXML
    private ImageView locTrayImage;
    @FXML
    private TableView<Stands> locClaimView;
    @FXML
    private TableColumn<Stands, String> clDescItem;
    @FXML
    private TableColumn<Stands, String> clArtItem;
    @FXML
    private TabPane locTabPane;
    @FXML
    private Tab locTab1;
    @FXML
    private Tab locTab2;
    @FXML
    private Accordion locSectionAccordion;
    @FXML
    private TextArea locTrayArea;
    @FXML
    private ComboBox<Float> locUnitThsС;
    @FXML
    private ComboBox<String> locSectionStep2;
    @FXML
    private CheckBox locZSprey;
    @FXML
    private ListView<Stand> locListStands;
    @FXML
    private ComboBox<Types> locStandClass;
    @FXML
    private ComboBox<String> locStandLength;
    @FXML
    private ComboBox<String> locStandThs;
    @FXML
    private ComboBox<String> locStandHeight;
    @FXML
    private TextField locStandCounts;
    @FXML
    private ComboBox<String> locStandCon;
    @FXML
    private ListView<Profil> locListProfil;
    @FXML
    private ComboBox<Types> locProfilClass;
    @FXML
    private ComboBox<String> locProfilLength;
    @FXML
    private ComboBox<String> locProfilThs;
    @FXML
    private ComboBox<String> locProfilHeight;
    @FXML
    private TextField locProfilCounts;
    @FXML
    private ComboBox<String> locProfilCon;
    @FXML
    private ListView<Bracket> locListClaims;
    @FXML
    private ComboBox<String> locClaimsClass;
    @FXML
    private ComboBox<String> locClaimsLength;
    @FXML
    private ComboBox<String> locClaimsThs;
    @FXML
    private TextField locClaimsCounts;
    @FXML
    private ComboBox<String> locClaimsCon;
    @FXML
    private Button locClearStandBtn;
    @FXML
    private Button locClearProfilBtn;
    @FXML
    private Button locClearBracketBtn;
    @FXML
    private ComboBox<Division> locDividers;
    
    private boolean isConnectors = false;
    private boolean isDividers = false;
    private boolean isClaims = false;
    private boolean isInitReport = false;
    private boolean isCoverMetis = false;
    private boolean isClaimsMetis = false;
    private boolean isDivision = false;
    private boolean isDividerMetis = false;
    private boolean isCoverClaims = false;
    private boolean isTrayClaims = false;
    private boolean isConnectMetis = false;
    private boolean isInnerClaims = false;
    
    private Thread runner = null;
    
    
    private ActionController controller ;
    @FXML
    private RadioButton locInnerClaim2;
    @FXML
    private TreeTableColumn<Report, String> treeNameColumn;
    @FXML
    private TreeTableColumn<Report, String> treeCountColumn;
    @FXML
    private Label locCountPrElem;
    @FXML
    private TextField locProjectName;
    @FXML
    private DatePicker locProjectDate;
    @FXML
    private ComboBox<String> locComboClass;
    @FXML
    private ComboBox<String> locComboStyles;
    @FXML
    private ImageView locStandImage;
    @FXML
    private ImageView locProfilImage;
    @FXML
    private ImageView locBracketImage;
    @FXML
    private CheckBox locSimpleCheck;
    @FXML
    private CheckBox locConCheck;
    @FXML
    private CheckBox locDividerCheck;
    @FXML
    private CheckBox locCoverCheck;
    @FXML
    private CheckBox locClaimCheck;
    @FXML
    private Pane filtredAcsPane;
    
    private int change_cab = 0; 
    
    private CablingCalc cabCalc;
    
    /* 
    ACS
    */
    @FXML
    private ComboBox<String> locAcsTrayTyper;
    @FXML
    private ComboBox<String> locAcsType;
    @FXML
    private ComboBox<String> locAcsSource;
    @FXML
    private ComboBox<String> locAcsHeight;
    @FXML
    private ComboBox<String> locAcsWidth;
    @FXML
    private ComboBox<String> locThsAcs;
    @FXML
    private TextField locArtAcs;
    @FXML
    private Label locArtLabel1;
    @FXML
    private Label locLengthLabel1;
    @FXML
    private TableView<AdditionTrays> locAcsTable;
    @FXML
    private TableColumn<AdditionTrays, String> colAcsDesc;
    @FXML
    private TableColumn<AdditionTrays, String> colAcsArticul;
    @FXML
    private CheckBox locAcsCon;
    @FXML
    private CheckBox locAcsMain;
    @FXML
    private CheckBox locAcsCover;
    @FXML
    private TextField locCountAcs;
    @FXML
    private ComboBox<String> CablingMethod1;
    @FXML
    private TextField locCabelCount1;
    @FXML
    private TextField locCableDiametr1;
    @FXML
    private TextField locCableDiametr2;
    @FXML
    private TextField locCabelCount2;
    @FXML
    private ComboBox<String> CablingMethod2;
    @FXML
    private TextField locCableDiametr3;
    @FXML
    private TextField locCabelCount3;
    @FXML
    private ComboBox<String> CablingMethod3;
    @FXML
    private TextField locCableDiametr4;
    @FXML
    private TextField locCabelCount4;
    @FXML
    private ComboBox<String> CablingMethod4;
    @FXML
    private ImageView locHintView;
    @FXML
    private ImageView locCableView;
    @FXML
    private TextField locCableRate;
    @FXML
    private Pane locAcsTrayType;
    @FXML
    private Pane locCablePane;
    @FXML
    private TextField locCableQuid;
    @FXML
    private ListView<?> locCatalogClass;
    @FXML
    private TableView<?> locCatalogView;
    @FXML
    private TableView<?> locCatalogSelect;
    @FXML
    private Label locLabelProjectName;
    
    
    public void initUnit(){
        locUnitList.setItems(Model.getInstance().unit_list);
        
        locSectionStep2.setItems(Model.getInstance().step_list);
        locSectionStep2.setValue("1");
        
        //locStandTable
        TableColumn<Stands, String> titleUnitItem = new TableColumn<Stands, String>("Наименование");
        TableColumn<Stands, String> countUnitItem = new TableColumn<Stands, String>("Количество");
        TableColumn<Stands, String> lengUnitItem = new TableColumn<>("Длина");
        
        titleUnitItem.setCellValueFactory(new PropertyValueFactory<>("name"));
        countUnitItem.setCellValueFactory(new PropertyValueFactory<>("count"));
        lengUnitItem.setCellValueFactory(new PropertyValueFactory<>("length"));
        
        editor = new EditorEx();
        editor.generateStandsData( "Рейка СК", false, 2);
        
        TableRowExpanderColumn expander = new TableRowExpanderColumn((param) -> {
            return editor.getEditor((TableRowExpanderColumn.TableRowDataFeatures<Stands>) param); //To change body of generated lambdas, choose Tools | Templates.
        }) ;
        locStandTable.getColumns().addAll(expander, titleUnitItem, countUnitItem, lengUnitItem);
        
        locStandTable.setItems(editor.getStandsData());
        
        locUnitThsС.setItems(Model.getInstance().ths_list);
        
        locUnitConstruction.setItems(Model.getInstance().source_list);
        
        locClaimSpinner.setValueFactory(new SpinnerValueFactory<Integer>(){
            
            private int start = 0;
            
            @Override
            public void decrement(int steps) {
                if (this.getValue()!=null)
                    start = getValue();
                this.setValue(new Integer(start-steps)); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void increment(int steps) {
                if (this.getValue()!=null)
                    start = getValue();
                this.setValue(new Integer(start+steps)); //To change body of generated methods, choose Tools | Templates.
            }
            
        });
        
    }
    
    public void initOtherConstruction(){
        
        List<String> methodsElement = Arrays.asList(CablingCalc.CAB_CONF);
        List<String> methodsElement1 = Arrays.asList(CablingCalc.CAB_CONF1);
        List<String> methodsElement2 = Arrays.asList(CablingCalc.CAB_CONF2);
        List<String> methodsElement3 = Arrays.asList(CablingCalc.CAB_CONF3);
        CablingMethod1.setItems(FXCollections.observableList(methodsElement));
        CablingMethod2.setItems(FXCollections.observableList(methodsElement1));
        CablingMethod3.setItems(FXCollections.observableList(methodsElement2));
        CablingMethod4.setItems(FXCollections.observableList(methodsElement3));
        cabCalc = new CablingCalc();
        
        
        locComboClass.setItems(Model.getInstance().type_all_list);
        
        locComboStyles.setItems(Model.getInstance().type_obraz_list);
        
        colAcsDesc.setCellValueFactory(new PropertyValueFactory<>("definition"));
        colAcsArticul.setCellValueFactory(new PropertyValueFactory<>("articul"));

        locAcsTable.setItems(Model.getInstance().addFlist);
        
        if (Model.getInstance().type_list != null){
            if (locAcsTrayTyper == null){
                System.out.println("null locAcsTrayTyper");
            }
            locAcsTrayTyper.setItems(Model.getInstance().type_list);            
            locAcsType.setItems(Model.getInstance().addtray_types);
            locAcsSource.setItems(Model.getInstance().addsource_list);
            locAcsWidth.setItems(Model.getInstance().addwidth_list);
            locAcsHeight.setItems(Model.getInstance().addheight_list);
            locThsAcs.setItems(Model.getInstance().addthickness_list);
        }

        
        treeNameColumn.setCellValueFactory(new TreeItemPropertyValueFactory<>("title"));
        treeCountColumn.setCellValueFactory(new TreeItemPropertyValueFactory<>("count"));
        
        Model.getInstance().initProfilsAndStands();
        
        locStandClass.setItems(Model.getInstance().type_stand_list);
        locStandLength.setItems(Model.getInstance().lenght_stand_list);
        locStandHeight.setItems(Model.getInstance().height_stand_list);
        locStandThs.setItems(Model.getInstance().ths_stand_list);
        locStandCon.setItems(Model.getInstance().source_list);
        
        locProfilClass.setItems(Model.getInstance().type_profil_list);
        locProfilLength.setItems(Model.getInstance().lenght_profil_list);
        locProfilHeight.setItems(Model.getInstance().height_profil_list);
        locProfilThs.setItems(Model.getInstance().ths_profil_list);
        locProfilCon.setItems(Model.getInstance().source_list);
        
        locClaimsClass.setItems(Model.getInstance().type_bracket_list);
        locClaimsLength.setItems(Model.getInstance().length_bracket_list);
        locClaimsThs.setItems(Model.getInstance().ths_bracket_list);
        locClaimsCon.setItems(Model.getInstance().source_list);
        
        locListStands.setItems(Model.getInstance().stand_list);
        locListProfil.setItems(Model.getInstance().profil_list);
        locListClaims.setItems(Model.getInstance().bracket_list);//Bracket
    }
    
    public void initTray(){
        
        Thread runner = new Thread(new Runnable() {
            @Override
            public void run() {
                while(true){
                    try {
                        SectionValue.setRoadLenght(locRoadEdit.getValue());
                        SectionValue.setSectionStep(Float.valueOf(locSectionStep.getValue()));
                        Thread.sleep(1500);
                    } catch (Exception ex) {
                    }
                }
            }
        });
        runner.start();
        
        
        locDividers.setItems(Model.getInstance().dividers);
        
        locDividers.setCellFactory(p -> new ListCell <Division> () {

        @Override
        protected void updateItem(Division item, boolean empty) {
                super.updateItem(item, empty);
                if (item != null && !empty) {
                    setText(item.getTitleDisplay());
                } else {
                    setText(null);
                }
            }
        });
        
        locDividers.setConverter(new DividerConverter());
        
        sliderScores.setBlockIncrement(0.05);
        sliderScores.setMin(0.0);
        sliderScores.setMax(2.5);
        sliderScores.setLabelFormatter(new StringConverter<Double>() {
            @Override
            public String toString(Double object) {
                return object.toString();
            }

            @Override
            public Double fromString(String string) {
                return Double.valueOf(string);
            }
        });
        
        
        sliderScores.setMinorTickCount(1);
        sliderScores.valueProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> ov,
                Number old_val, Number new_val) {
                    boolean isChange = false;
                    if (new_val.doubleValue() < 1.0d) {
                        
                        if ( 0.6<Double.valueOf(dataFilter.getThs()) && 0.75>Double.valueOf(dataFilter.getThs())){
                            locScores.setText(String.format("%.2f", new_val.doubleValue()-0.05));
                            isChange = true;
                        } else {
                            
                            if (!String.format("%.2f", new_val).equalsIgnoreCase("0,00")) {
                                locScores.setText(String.format("%.2f", new_val.doubleValue()+0.05));
                                if (String.format("%.2f", new_val).equalsIgnoreCase("0,25")|| locScores.getText().equalsIgnoreCase("0,30")) {
                                    sliderScores.setValue(0.55);
                                    locScores.setText("0,55");
                                }
                                isChange = true;
                            } else {
                                locScores.setText("0,0");
                                isChange = true;
                            }
                           
                        }
                        
                        
                    } else {
                        if (String.format("%.2f", new_val).equalsIgnoreCase("1,00")) {
                            isChange = true;
                        }
                        if (String.format("%.2f", new_val).equalsIgnoreCase("1,50")) {
                            isChange = true;
                        }
                        if (String.format("%.2f", new_val).equalsIgnoreCase("1,25")) {
                            locScores.setText(String.format("%.2f", (new_val.doubleValue()-0.05)));
                            isChange = true;
                        } else {
                            locScores.setText(String.format("%.2f", new_val.doubleValue()));
                        }
                        if (String.format("%.2f", new_val).equalsIgnoreCase("1,75")) {
                            sliderScores.setValue(2.0);
                            locScores.setText("2,0");
                            isChange = true;
                        }
                        if (String.format("%.2f", new_val).equalsIgnoreCase("2,50")) {
                            locScores.setText(String.format("%.2f", new_val.doubleValue()+1.5));
                            isChange = true;
                        }
                        if (String.format("%.2f", new_val).equalsIgnoreCase("2,25")) {
                            sliderScores.setValue(2.5);
                            locScores.setText("4,0");
                            isChange = true;
                        }
                        
                    }
                    if (isChange) {
                        if (locScores.getText().equalsIgnoreCase("0,55")) {
                            dataFilter.setThs(locScores.getText().replace(",", "."));
                        } else {
                            dataFilter.setThs(Float.valueOf(locScores.getText().replace(",", ".")).toString());
                        }
                        Model.getInstance().filtered(dataFilter, false);
                    } else {
                        dataFilter.setThs(Double.toString(new_val.doubleValue()));
                    }
                    System.out.println("Ths "+dataFilter.getThs());
            }
        });
        
        Model.getInstance().updateTrays();
        
        locSectionStep.setItems(Model.getInstance().step_list);
        locSectionStep.setValue("1");
        
        clDescItem.setCellValueFactory(new PropertyValueFactory<>("name"));
        
        locClaimView.setItems(Model.getInstance().claim_list);
        
        locRoadEdit.setValueFactory(new SpinnerValueFactory<Integer>(){
            
            private int start = 100;
            
            @Override
            public void decrement(int steps) {
                if (this.getValue()!=null)
                    start = getValue();
                this.setValue(new Integer(start-steps)); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void increment(int steps) {
                if (this.getValue()!=null)
                    start = getValue();
                this.setValue(new Integer(start+steps)); //To change body of generated methods, choose Tools | Templates.
            }
            
        });
        locRoadEdit.increment(2);
        
        ObservableList<String> list1 = Model.getInstance().type_list;
        locTrayType.setItems(list1);
        
        ObservableList<String> list2 = Model.getInstance().source_list;
        locTraySource.setItems(list2);
        
        locHeight.setItems(Model.getInstance().height_list);
        
        locWidth.setItems(Model.getInstance().width_list);
        
        locLength.setItems(Model.getInstance().length_list);
        
        trayDescItem.setCellValueFactory(new PropertyValueFactory<>("titleDisplay"));
        trayArtItem.setCellValueFactory(new PropertyValueFactory<>("art"));

        locTrayView.setItems(Model.getInstance().flist);
        
        elementTree.initialize();
        elementTree.setRoot("New Project");
        
    }

    
    private void standUpdate() {
        if (locListStands.getSelectionModel().getSelectedItem()==null)
            if (Model.getInstance().stand_list!=null)
                if (Model.getInstance().stand_list.size()>0)
                    locListStands.getSelectionModel().select(0);
    }
    
    private void profilUpdate() {
        if (locListProfil.getSelectionModel().getSelectedItem()==null)
            if (Model.getInstance().profil_list!=null)
                if (Model.getInstance().profil_list.size()>0)
                    locListProfil.getSelectionModel().select(0);
    }
    
    private void bracketUpdate() {
        if (locListClaims.getSelectionModel().getSelectedItem()==null)
            if (Model.getInstance().bracket_list!=null)
                if (Model.getInstance().bracket_list.size()>0)
                    locListClaims.getSelectionModel().select(0);
    }

    @FXML
    private void onStandClass(ActionEvent event) {
        Types prClass = locStandClass.getSelectionModel().getSelectedItem();
        if (prClass!=null) {
        standsFilter.addType(prClass.getId());
        Model.getInstance().updateStandFilter(standsFilter);
            this.standUpdate();
    }
    }

    @FXML
    private void onStandLength(ActionEvent event) {
        String strL = locStandLength.getSelectionModel().getSelectedItem();
        int length = 1;
        /*
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
        */
        if (strL.startsWith("100-")) {
            standsFilter.clearLength();
            standsFilter.addLength(100);
            standsFilter.addLength(133);
        }
        if (strL.startsWith("135")) {
            standsFilter.clearLength();
            standsFilter.addLength(135);
            standsFilter.addLength(176);
        }
        if (strL.startsWith("180")) {
            standsFilter.clearLength();
            standsFilter.addLength(180);
            standsFilter.addLength(215);
        }
        if (strL.startsWith("220")) {
            standsFilter.clearLength();
            standsFilter.addLength(220);
            standsFilter.addLength(250);
        }
        if (strL.startsWith("270")) {
            standsFilter.clearLength();
            standsFilter.addLength(270);
            standsFilter.addLength(300);
        }
        if (strL.startsWith("315")) {
            standsFilter.clearLength();
            standsFilter.addLength(315);
            standsFilter.addLength(330);
        }
        if (strL.startsWith("350")) {
            standsFilter.clearLength();
            standsFilter.addLength(350);
            standsFilter.addLength(415);
        }
        if (strL.startsWith("420")) {
            standsFilter.clearLength();
            standsFilter.addLength(420);
            standsFilter.addLength(450);
        }
        if (strL.startsWith("480")) {
            standsFilter.clearLength();
            standsFilter.addLength(480);
            standsFilter.addLength(515);
        }
        if (strL.startsWith("520")) {
            standsFilter.clearLength();
            standsFilter.addLength(520);
            standsFilter.addLength(565);
        }
        if (strL.startsWith("600")) {
            standsFilter.clearLength();
            standsFilter.addLength(600);
            standsFilter.addLength(620);
        }
        if (strL.startsWith("650")) {
            standsFilter.clearLength();
            standsFilter.addLength(650);
            standsFilter.addLength(715);
        }
        if (strL.startsWith("750")) {
            standsFilter.clearLength();
            standsFilter.addLength(750);
            standsFilter.addLength(815);
        }
        if (strL.startsWith("900")) {
            standsFilter.clearLength();
            standsFilter.addLength(900);
            standsFilter.addLength(950);
        }
        if (strL.startsWith("1000")) {
            standsFilter.clearLength();
            standsFilter.addLength(1000);
            standsFilter.addLength(1020);
        }
        if (strL.startsWith("1100")) {
            standsFilter.clearLength();
            standsFilter.addLength(1100);
            standsFilter.addLength(1320);
        }
        if (strL.length()==0) {
            standsFilter.setLength(0);
        } else {
            standsFilter.setLength(length);
        }
        Model.getInstance().updateStandFilter(standsFilter);
        this.standUpdate();
    }

    @FXML
    private void onStandThs(ActionEvent event) {
        String ths =locStandThs.getSelectionModel().getSelectedItem();
        standsFilter.setThs(ths);
        Model.getInstance().updateStandFilter(standsFilter);
        this.standUpdate();
    }

    @FXML
    private void onStandHeight(ActionEvent event) {
        String strH = locStandHeight.getSelectionModel().getSelectedItem();
        int height = 1;
        if (strH.startsWith("0")) {
            standsFilter.clearHeight();
            standsFilter.addHeight(0);
            standsFilter.addHeight(60);
        }
        if (strH.startsWith("60")) {
            standsFilter.clearHeight();
            standsFilter.addHeight(60);
            standsFilter.addHeight(80);
        }
        if (strH.startsWith("80")) {
            standsFilter.clearHeight();
            standsFilter.addHeight(80);
            standsFilter.addHeight(100);
        }
        if (strH.startsWith("100")) {
            standsFilter.clearHeight();
            standsFilter.addHeight(100);
            standsFilter.addHeight(120);
        }
        if (strH.startsWith("120")) {
            standsFilter.clearHeight();
            standsFilter.addHeight(120);
            standsFilter.addHeight(150);
        }
        if (strH.startsWith("150")) {
            standsFilter.clearHeight();
            standsFilter.addHeight(150);
            standsFilter.addHeight(170);
        }
        if (strH.startsWith("170")) {
            standsFilter.clearHeight();
            standsFilter.addHeight(170);
            standsFilter.addHeight(190);
        }
        if (strH.startsWith("190")) {
            standsFilter.clearHeight();
            standsFilter.addHeight(190);
            standsFilter.addHeight(230);
        }
        if (strH.length()==0) {
            standsFilter.setHeight(0);
        } else {
            standsFilter.setHeight(height);
        }
        Model.getInstance().updateStandFilter(standsFilter);
        this.standUpdate();
    }

    @FXML
    private void onStandConstruction(ActionEvent event) {
        int con = locStandCon.getSelectionModel().getSelectedIndex();
        
        standsFilter.setConstruction(con);
        standsFilter.addSource(con);
        
        Model.getInstance().updateStandFilter(standsFilter);
        this.standUpdate();
    }

    @FXML
    private void onProfilClass(ActionEvent event) {
        Types prClass = locProfilClass.getSelectionModel().getSelectedItem();
        if (prClass!=null) {
        profilFilter.addType(prClass.getId());
        Model.getInstance().updateProfilFilter(profilFilter);
            this.profilUpdate();
    }
    }

    @FXML
    private void onProfilLength(ActionEvent event) {
        String strL = locProfilLength.getSelectionModel().getSelectedItem();
        int length = 1;
        if (strL.startsWith("140")) {
            profilFilter.clearLength();
            profilFilter.addLength(100);
            profilFilter.addLength(199);
        }
        if (strL.startsWith("200-")) {
            profilFilter.clearLength();
            profilFilter.addLength(200);
            profilFilter.addLength(299);
        }
        if (strL.startsWith("300-")) {
            profilFilter.clearLength();
            profilFilter.addLength(300);
            profilFilter.addLength(399);
        }
        if (strL.startsWith("400-")) {
            profilFilter.clearLength();
            profilFilter.addLength(400);
            profilFilter.addLength(499);
        }
        if (strL.startsWith("500")) {
            profilFilter.clearLength();
            profilFilter.addLength(500);
            profilFilter.addLength(600);
        }
        if (strL.startsWith("600")) {
            profilFilter.clearLength();
            profilFilter.addLength(600);
            profilFilter.addLength(700);
        }
        if (strL.startsWith("700")) {
            profilFilter.clearLength();
            profilFilter.addLength(700);
            profilFilter.addLength(800);
        }
        if (strL.startsWith("800")) {
            profilFilter.clearLength();
            profilFilter.addLength(800);
            profilFilter.addLength(900);
        }
        if (strL.startsWith("900")) {
            profilFilter.clearLength();
            profilFilter.addLength(900);
            profilFilter.addLength(1000);
        }
        if (strL.startsWith("1000")) {
            profilFilter.clearLength();
            profilFilter.addLength(1000);
            profilFilter.addLength(1200);
        }
        if (strL.startsWith("1200")) {
            profilFilter.clearLength();
            profilFilter.addLength(1200);
            profilFilter.addLength(1500);
        }
        if (strL.startsWith("1500")) {
            profilFilter.clearLength();
            profilFilter.addLength(1500);
            profilFilter.addLength(2000);
        }
        if (strL.startsWith("2000")) {
            profilFilter.clearLength();
            profilFilter.addLength(2000);
            profilFilter.addLength(2500);
        }
        if (strL.startsWith("2500")) {
            profilFilter.clearLength();
            profilFilter.addLength(2500);
            profilFilter.addLength(3000);
        }
        if (strL.startsWith("3000")) {
            profilFilter.clearLength();
            profilFilter.addLength(3000);
            profilFilter.addLength(4500);
        }
        if (strL.startsWith("4500")) {
            profilFilter.clearLength();
            profilFilter.addLength(4500);
            profilFilter.addLength(6000);
        }
        if (strL.length()==0) {
            profilFilter.setLength(0);
        } else {
            profilFilter.setLength(length);
        }
        Model.getInstance().updateProfilFilter(profilFilter);
        this.profilUpdate();
    }

    @FXML
    private void onProfilThs(ActionEvent event) {
        String ths =locProfilThs.getSelectionModel().getSelectedItem();
        profilFilter.setThs(ths);
        Model.getInstance().updateProfilFilter(profilFilter);
        this.profilUpdate();
    }

    @FXML
    private void onProfilHeight(ActionEvent event) {
        String strL = locProfilHeight.getSelectionModel().getSelectedItem();
        int height = Integer.valueOf(strL);
        profilFilter.setHeight(height);
        Model.getInstance().updateProfilFilter(profilFilter);
        this.profilUpdate();
    }

    @FXML
    private void onProfilConstruction(ActionEvent event) {
        int con = locProfilCon.getSelectionModel().getSelectedIndex();
        profilFilter.setConstruction(con);
        profilFilter.addSource(con);
        Model.getInstance().updateProfilFilter(profilFilter);
        this.profilUpdate();
    }

    @FXML
    private void onBracketClass(ActionEvent event) {
        String brClass = locClaimsClass.getSelectionModel().getSelectedItem();
        if (brClass!=null) {
        bracketFilter.setArticul(brClass);
        Model.getInstance().updateBracketFilter(bracketFilter);
            this.bracketUpdate();
    }
    }

    @FXML
    private void onBracketLength(ActionEvent event) {
        String strL = locClaimsLength.getSelectionModel().getSelectedItem();
        int length = 1;
        if (strL.startsWith("0")) {
            bracketFilter.clearLength();
            bracketFilter.addLength(0);
            bracketFilter.addLength(60);
        }
        if (strL.startsWith("60")) {
            bracketFilter.clearLength();
            bracketFilter.addLength(60);
            bracketFilter.addLength(80);
        }
        if (strL.startsWith("80")) {
            bracketFilter.clearLength();
            bracketFilter.addLength(80);
            bracketFilter.addLength(100);
        }
        if (strL.startsWith("100")) {
            bracketFilter.clearLength();
            bracketFilter.addLength(100);
            bracketFilter.addLength(120);
        }
        if (strL.startsWith("120")) {
            bracketFilter.clearLength();
            bracketFilter.addLength(120);
            bracketFilter.addLength(150);
        }
        if (strL.startsWith("150")) {
            bracketFilter.clearLength();
            bracketFilter.addLength(150);
            bracketFilter.addLength(170);
        }
        if (strL.startsWith("170")) {
            bracketFilter.clearLength();
            bracketFilter.addLength(170);
            bracketFilter.addLength(190);
        }
        if (strL.startsWith("190")) {
            bracketFilter.clearLength();
            bracketFilter.addLength(190);
            bracketFilter.addLength(230);
        }
        if (strL.length()==0) {
            bracketFilter.setLength(0);
        } else {
            bracketFilter.setLength(length);
        }
        Model.getInstance().updateBracketFilter(bracketFilter);
        this.bracketUpdate();
    }

    @FXML
    private void onBracketThs(ActionEvent event) {
        String ths =locClaimsThs.getSelectionModel().getSelectedItem();
        bracketFilter.setThs(ths);
        Model.getInstance().updateBracketFilter(bracketFilter);
        this.bracketUpdate();
    }

    @FXML
    private void onBracketConstruction(ActionEvent event) {
        int con = locClaimsCon.getSelectionModel().getSelectedIndex();
        bracketFilter.setConstruction(con);
        bracketFilter.addSource(con);
        Model.getInstance().updateBracketFilter(bracketFilter);
        this.bracketUpdate();
    }

    @FXML
    private void handleClearStand(MouseEvent event) {
        locStandClass.getSelectionModel().select(0);  //.setValue(new Types(0,"","","",""));
        locStandLength.getSelectionModel().select(0);
        locStandHeight.getSelectionModel().select(0);
        locStandThs.getSelectionModel().select(0);
        locStandCon.getSelectionModel().select(0);
        Model.getInstance().clearProfilsandStands(1);
        standsFilter.clear();
    }

    @FXML
    private void handleClearProfil(MouseEvent event) {
        locProfilClass.getSelectionModel().select(0);//.setValue(new Types(0,"","","",""));
        locProfilLength.getSelectionModel().select(0);
        locProfilHeight.getSelectionModel().select(0);
        locProfilThs.getSelectionModel().select(0);
        locProfilCon.getSelectionModel().select(0);
        Model.getInstance().clearProfilsandStands(2);
        profilFilter.clear();
        
    }

    @FXML
    private void handleClearBracket(MouseEvent event) {
        locClaimsClass.getSelectionModel().select(0);
        locClaimsLength.getSelectionModel().select(0);
        
        locClaimsThs.getSelectionModel().select(0);
        locClaimsCon.getSelectionModel().select(0);
        Model.getInstance().clearProfilsandStands(3);
        bracketFilter.clear();
    }

    @FXML
    private void handleSimpleLinkIn(ActionEvent event) {
        isStandsLink = locSimpleCheck.isSelected();
        if (isStandsLink) {
            ///???
            Model.getInstance().updateClassByStand(isStand);
            if (isStand) {
                //locProfilClass.setItems(value);
                Stand stand = locListStands.getSelectionModel().getSelectedItem();
                Profil profil = locListProfil.getSelectionModel().getSelectedItem();
                Integer indPr = Model.getInstance().updateProfilsAndStands(profil, stand, 1);
                if (indPr > 0) {
                    locListProfil.getSelectionModel().select(indPr);
                }
                System.out.println("Index sel Profil = "+indPr);
             
            } else {
                Stand stand = locListStands.getSelectionModel().getSelectedItem();
                Profil profil = locListProfil.getSelectionModel().getSelectedItem();
                Integer indSt = Model.getInstance().updateProfilsAndStands(profil, stand, 2);
                if (indSt > 0) {
                    locListStands.getSelectionModel().select(indSt);
                }
                System.out.println("Index sel Stand = "+indSt);
            }
        } else {
            Model.getInstance().addClassesByStand();
        }
    }
    
    private void AlertDialogShow(String titleTxt, int index){
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(titleTxt);
        alert.setHeaderText("Внимание !!!");
        String s = "";
        if (index==0)
            s ="Пожалуйста, выберите консоль и профиль для добавления в проект... ";
        if (index==1)
            s ="Добавлены в проект успешно... ";
        
        alert.setContentText(s);

        alert.show();

    }
    
    public void addMetisInProject(LinkStands linked) {
        Report rep = null;
        if (linked!=null) {
            if (linked.getDescription().startsWith("Комплект метизов")) {
                rep = new Report();
                rep.setTitle("Комплект метизов "+linked.getArticul());
                rep.setFull(rep.getTitle());
                rep.setType("");
                rep.setArticul(linked.getArticul());
                rep.setCount(linked.getCount());
                rep.setCount_type("шт.");
                
                rep.setMass(Float.valueOf(linked.getMass().replace(",", ".")));
                rep.setIndex(4);
                Model.getInstance().addReport(rep);
            } else {
                rep = new Report();
                rep.setTitle(linked.getDescription());
                rep.setFull(rep.getTitle());
                rep.setType("Несущие конструкции "+linked.getClassStr());
                rep.setArticul(linked.getArticul());
                rep.setCount(linked.getCount());
                rep.setCount_type("шт.");
                
                rep.setMass(Float.valueOf(linked.getMass().replace(",", ".")));
                rep.setIndex(6);
                rep.setDescription("АО «СЗ ЭМИ»");
                Model.getInstance().addReport(rep);
            }
            
            
        } else {
            Stand stand = locListStands.getSelectionModel().getSelectedItem();
            rep = new Report();
            rep.setTitle("Комплект метизов "+stand.getMetis());
            rep.setFull(rep.getTitle());
            rep.setType("");
            rep.setArticul(stand.getMetis());
            rep.setCount(stand.getMetisCount());
            rep.setCount_type("шт.");

            rep.setMass(Float.valueOf("0.16"));
            rep.setIndex(4);
            Model.getInstance().addReport(rep);
        }
    }
    
    public void addStandInProject() {
        Stand stand = locListStands.getSelectionModel().getSelectedItem();
        Profil profil = locListProfil.getSelectionModel().getSelectedItem();
        if (stand==null || profil==null) {
            this.AlertDialogShow("Выбор крепления", 0);
        } else {
            
            if (!isInitReport) {
                AddReportValues(5, null, "3");
                elementTree.addTreeItems(Model.getInstance().getLastReport());
                isInitReport = true;
                this.clearProjectTree(true);
            }
            
            String standT = ""+stand.getType();
            String profilT = ""+profil.getType();
            AddReportValues(5, null, locProfilCounts.getText());
            
            elementTree.addTreeItems(Model.getInstance().getLastPrevReport());
            elementTree.addLastLeaf(Model.getInstance().getLastReport());
            //elementTree.addTreeItems(locListProfil.getSelectionModel().getSelectedItem().getTitle()+"       "+locClaimsCounts.getText());
            //elementTree.addLastLeaf(stand.getTitle()+"       "+locStandCounts.getText());
            
            List<LinkStands> linkedMetises = Model.getInstance().getLinkStandsMetis(standT, profilT);
            if (linkedMetises != null){
                for (LinkStands linkedMetis : linkedMetises) {
                    this.addMetisInProject(linkedMetis);
                }
                elementTree.addLastLeaf(Model.getInstance().getLastReport());
                this.AlertDialogShow("Успешно", 1);        
            } else {
                this.addMetisInProject(null);
                 elementTree.addLastLeaf(Model.getInstance().getLastReport());
            }
        }
    }

    @FXML
    private void handleAddStandsInPr(ActionEvent event) {
        //SectionValue val = calcSectionValues();
        if (isStandsLink) {
            this.addStandInProject();
        }else{
            if (locListStands.getSelectionModel().getSelectedItem()!=null){
                //Stand stand = locListStands.getSelectionModel().getSelectedItem();
                //elementTree.addTreeItems(stand.getTitle()+"       "+locStandCounts.getText());
                if (!isInitReport) {
                AddReportValues(6, null, "1");
                elementTree.addTreeItems(Model.getInstance().getLastReport());
                    isInitReport = true;
                    this.clearProjectTree(true);
                }
                
                AddReportValues(6, null, "1");
                elementTree.addTreeItems(Model.getInstance().getLastReport());
                    
                //elementTree.addLastLeaf("Комплект метизов "+stand.getMetis()+"    "+stand.getMetisCount());
            }
        }
    }

    @FXML
    private void handleAddProfilInPr(ActionEvent event) {
        // locProfilCounts
        if (isStandsLink) {
            this.addStandInProject();
        }else{
            if (locListProfil.getSelectionModel().getSelectedItem()!=null){
                //elementTree.addTreeItems(locListProfil.getSelectionModel().getSelectedItem().getTitle()+"       "+locClaimsCounts.getText());
                
                if (!isInitReport) {
                AddReportValues(6, null, "2");
                elementTree.addTreeItems(Model.getInstance().getLastReport());
                    isInitReport = true;
                    this.clearProjectTree(true);
                }

                AddReportValues(6, null, "2");
                elementTree.addTreeItems(Model.getInstance().getLastReport());

            }
        }
    }

    @FXML
    private void handleAddClaimsInPr(ActionEvent event) {
        // locListClaims
        if (locListClaims.getSelectionModel().getSelectedItem()!=null){
            //elementTree.addTreeItems(locListClaims.getSelectionModel().getSelectedItem().getTitleDisplay()+"       "+locClaimsCounts.getText());
            if (!isInitReport) {
            AddReportValues(6, null, "3");
            elementTree.addTreeItems(Model.getInstance().getLastReport());
                isInitReport = true;
                this.clearProjectTree(true);
            }
                    
            AddReportValues(6, null, "3");
            elementTree.addTreeItems(Model.getInstance().getLastReport());
                    
        }
    }

    @FXML
    private void handleSelectStand(MouseEvent event) {
        if (locListStands.getSelectionModel().getSelectedItem()!=null) {
            isStand = true;
            if (isStandsLink) {
                Stand stand = locListStands.getSelectionModel().getSelectedItem();
                Profil profil = locListProfil.getSelectionModel().getSelectedItem();
                Integer indPr = Model.getInstance().updateProfilsAndStands(profil, stand, 1);
                if (indPr > 0) {
                    locListProfil.getSelectionModel().select(indPr);
                }
                System.out.println("Index sel Profil = "+indPr);
                
            }
        }
    }

    @FXML
    private void handleSelectProfil(MouseEvent event) {
        if (locListProfil.getSelectionModel().getSelectedItem()!=null) {
            isStand = false;
            if (isStandsLink) {
                Stand stand = locListStands.getSelectionModel().getSelectedItem();
                Profil profil = locListProfil.getSelectionModel().getSelectedItem();
                Integer indSt = Model.getInstance().updateProfilsAndStands(profil, stand, 2);
                if (indSt > 0) {
                    locListStands.getSelectionModel().select(indSt);
                }
                System.out.println("Index sel Stand = "+indSt);
            }
        }
    }

    @FXML
    private void handleSelectBracket(ContextMenuEvent event) {
        if (locListClaims.getSelectionModel().getSelectedItem()!=null) {
            
        }
    }

    @FXML
    private void handleConnectorsEnabled(ActionEvent event) {
        isConnectors = !isConnectors;
        if (isConnectors) {
            locClampingView.setDisable(false);
        } else {
            locClampingView.setDisable(true);
        }
    }

    @FXML
    private void handleDividersEnabled(ActionEvent event) {
        isDividers = !isDividers;
        if (isDividers){
            locDividers.setDisable(false);
            locInnerClaim2.setDisable(false);
        } else {
            locDividers.setDisable(true);
            locInnerClaim2.setDisable(true);
        }
    }

    @FXML
    private void handleClaimsEnabled(ActionEvent event) {
        isClaims = !isClaims;
        if (isClaims){
            locClaimView.setDisable(false);
        } else {
            locClaimView.setDisable(true);
        }
    }

    @FXML
    private void handleIsFixed(ActionEvent event) {
        if (locIsClaim.isSelected())
            locIsClaim.setSelected(false);
    }

    @FXML
    private void handleIsClaim(ActionEvent event) {
        if (locIsFixed.isSelected())
            locIsFixed.setSelected(false);
    }

    @FXML
    private void handleClearProfil(ActionEvent event) {
    }

    @FXML
    private void handleLoadTrayPr(ActionEvent event) {
        
    }

    @FXML
    private void handleArtAcsReleased(KeyEvent event) {
    }

    @FXML
    private void handleAcsIn(ActionEvent event) {
        if (locAcsTable.getSelectionModel().getSelectedItem()!=null) {
            
            if (!isInitReport) {
                this.AddReportValues(9, calcSectionValues(), "");
                elementTree.addTreeItems(Model.getInstance().getLastReport());
                isInitReport = true;
                this.clearProjectTree(true);
            }
            
            this.AddReportValues(9, calcSectionValues(), "");
            elementTree.addTreeItems(Model.getInstance().getLastReport());
            
        }
    }


    @FXML
    private void onGotoTray(ActionEvent event) {
    }

    @FXML
    private void onClearCables(ActionEvent event) {
    }
    
    private void clearCablingView() {
        for( int k=0; k<65; k++) {
            Node rec = locCablePane.lookup("#rectangle");
            Node cir = locCablePane.lookup("#circle");
            if (rec!=null) {
                locCablePane.getChildren().remove(rec);
                System.out.println("remove");
            }
            if (cir!=null) {
                locCablePane.getChildren().remove(cir);
                System.out.println("remove");
            }
        }
    }
    
    private Gabarit inputCablingValue(int value) {
        int type = 0;
        float input_diam = 1f;
        int count_cab = 1;
        
        try{
            switch (value) {
                case 1: type = 1 + CablingMethod1.getSelectionModel().getSelectedIndex();
                        count_cab = Integer.valueOf(locCabelCount1.getText());
                        input_diam = Float.valueOf(locCableDiametr1.getText());
                        break;
                        
                case 2: type = 1 + CablingMethod2.getSelectionModel().getSelectedIndex();
                        count_cab = Integer.valueOf(locCabelCount2.getText());
                        input_diam = Float.valueOf(locCableDiametr2.getText());
                        break;
                case 3: type = 1 + CablingMethod3.getSelectionModel().getSelectedIndex();
                        count_cab = Integer.valueOf(locCabelCount3.getText());
                        input_diam = Float.valueOf(locCableDiametr3.getText());
                        break;
                case 4: type = 1 + CablingMethod4.getSelectionModel().getSelectedIndex();
                        count_cab = Integer.valueOf(locCabelCount4.getText());
                        input_diam = Float.valueOf(locCableDiametr4.getText());
                        break;
            }
        }catch(Exception e) {
            System.err.println(" Not find numeric parametr for calc");
            return null;
        }
        
        return new Gabarit(input_diam, count_cab, type);
    }

    @FXML
    private void onCalcCables(ActionEvent event) {
        
        this.clearCablingView();
        
        int type = 0;
        float input_diam = 1f;
        int count_cab = 1;
           
        ConstructCablingView.input(locCablePane, cabCalc);
        ConstructCablingView.addData(this.inputCablingValue(change_cab));
        
        // Start calculate
        
        ConstructCablingView.start();
        ConstructCablingView.showResult(change_cab);
        
        locCableRate.setText(cabCalc.trayWidth + " x "+cabCalc.trayHeight);
        Float freeDgr = ((cabCalc.swidth*cabCalc.sheight))/(cabCalc.trayWidth*cabCalc.trayHeight)*100;
        locCableQuid.setText(freeDgr.toString());
        
    }

    @FXML
    private void onCancelCables(ActionEvent event) {
    }

    private void onChangeCab1(ActionEvent event) {
        change_cab = 1;
    }

    @FXML
    private void onChangeCab2(InputMethodEvent event) {
        change_cab = 2;
    }

    @FXML
    private void onChangeCab3(InputMethodEvent event) {
        change_cab = 3;
    }

    @FXML
    private void onChangeCab4(InputMethodEvent event) {
        change_cab = 4;
    }

    @FXML
    private void onChangeCab1(InputMethodEvent event) {
        change_cab = 1;
    }

    private void onChangeCab2(ActionEvent event) {
        change_cab = 2;
    }

    private void onChangeCab3(ActionEvent event) {
        change_cab = 3;
    }

    private void onChangeCab4(ActionEvent event) {
        change_cab = 4;
    }

    @FXML
    private void onCalcCablesAll(ActionEvent event) {
        
        clearCablingView();
        
        int type = 0;
        float input_diam = 1f;
        int count_cab = 1;
        
        cabCalc.clear();
        
        for (int index_input = 1; index_input < 5; index_input++) {
            
            
            ConstructCablingView.input(locCablePane, cabCalc);
            Gabarit gab = this.inputCablingValue(index_input);
            
            if (gab!=null) {
                input_diam = gab.getH();
                count_cab = (int)gab.getW();
                type = gab.getL();
            
                ConstructCablingView.addData(input_diam,count_cab,type);
            }
            //ConstructCablingView.showAllResult(index_input-1);
            
        }
        
        
            ConstructCablingView.showAllResult(1);
            
            locCableRate.setText(cabCalc.trayWidth + " x "+cabCalc.trayHeight);
            Float freeDgr = ((cabCalc.swidth*cabCalc.sheight))/(cabCalc.trayWidth*cabCalc.trayHeight)*100;
            locCableQuid.setText(freeDgr.toString());
        
    }

    @FXML
    private void onChangeCab11(ActionEvent event) {
        change_cab = 1;
    }

    @FXML
    private void onChangeCab22(ActionEvent event) {
        change_cab = 2;
    }

    @FXML
    private void onChangeCab33(ActionEvent event) {
        change_cab = 3;
    }

    @FXML
    private void onChangeCab44(ActionEvent event) {
        change_cab = 4;
    }

    @FXML
    private void handleSeriesSelect(ActionEvent event) {
    }

    @FXML
    private void handleObrazSelect(ActionEvent event) {
    }

    @FXML
    private void handleCatalog1(ActionEvent event) {
    }

    @FXML
    private void handleCatalog2(ActionEvent event) {
    }

    @FXML
    private void handleCatalog3(ActionEvent event) {
    }

    @FXML
    private void handleCatalog4(ActionEvent event) {
    }

    @FXML
    private void handleAddCatalog(ActionEvent event) {
    }

    @FXML
    private void handleDelCatalog(ActionEvent event) {
    }

    @FXML
    private void handleCleanCatalog(ActionEvent event) {
    }

    @FXML
    private void handleConfirmCatalog(ActionEvent event) {
    }

    @FXML
    private void handleAddTenCatalog(ActionEvent event) {
    }

    @FXML
    private void handleDelTenCatalog(ActionEvent event) {
    }



    private static class DividerConverter extends StringConverter<Division> {

        public DividerConverter() {
        }

        @Override
        public String toString(Division object) {
            return  object.getTitleDisplay(); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public Division fromString(String string) {
            return (Division) DivisionFunctions.selectDivision().get(0); //To change body of generated methods, choose Tools | Templates.
        }
    }

    
    public class CoverConverter extends StringConverter<Cover>{

        @Override
        public String toString(Cover object) {
            return  object.getTitleDisplay();//To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public Cover fromString(String string) {
           return (Cover) CoverFunctions.selectCover().get(0); //To change body of generated methods, choose Tools | Templates.
        }
            
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Action fitered
        
        ActionMap.register(this);
        // Firstly, create a list of Actions
         actions = Arrays.asList(
            new ActionGroup("Проект", image3,  actions("action11","action12","action13") ),
                 ActionUtils.ACTION_SEPARATOR,
            new ActionGroup("Отчет", image3,  actions("action21","action22","---","action221","action222","---","action23") ),
            ActionUtils.ACTION_SEPARATOR,
            new ActionGroup("Вид", image3,  actions("action31","action32") ),
            ActionUtils.ACTION_SEPARATOR,
            new ActionGroup("Справка", image3,  action("action41"))
          );
   
        ToolBar menuBar1 = ActionUtils.createToolBar(actions, ActionTextBehavior.SHOW ); //flatten(actions,FXCollections.<Action>observableArrayList())
        
        paneMenu.getChildren().add(menuBar1);
        locAccord.setExpandedPane(locTiled1);
        logoImage1.setImage(new Image(input3));
        logoImage2.setImage(new Image(input4));
        locBottomIcon.setImage(new Image(input6));
        locSepProject.setImage(new Image(input7));
        
        elementTree = new ElemTreeView(locTreeView);
        
        initTray();
        
        initUnit();
        
        initOtherConstruction();
        
        conDescItem.setCellValueFactory(new PropertyValueFactory<>("titleDisplay"));
        conArtItem.setCellValueFactory(new PropertyValueFactory<>("class_"));
        
        locClampingView.setItems(Model.getInstance().con_list);
        
        //locConnector.setItems(Model.getInstance().con_list);
        
        locCover.setItems(Model.getInstance().cov_list);
        
        locCover.setCellFactory(p -> new ListCell <Cover> () {

        @Override
        protected void updateItem(Cover item, boolean empty) {
                super.updateItem(item, empty);
                if (item != null && !empty) {
                    setText(item.getTitleDisplay());
                } else {
                    setText(null);
                }
            }
        });
        
        Model.getInstance().selectTray = Model.getInstance().tlist.get(2);
        
        locCover.setConverter(new CoverConverter());
        
        locMetisView.setItems(Model.getInstance().metis_list);
        
        dataFilter = new SearchData();
        
        standsFilter = new SearchData();
        
        profilFilter = new SearchData();
        
        bracketFilter = new SearchData();
        
        acsDataFilter = new SearchData();
        trayFilterForAcs = new SearchData();
        
        dataFilter.setThs("0");
        
        controller = new ActionController();
        
        Model.getInstance().addObserver(this);
        
        locSecondAnchor.prefWidthProperty().bind(locMainAnchor.widthProperty());
        locSecondAnchor.prefHeightProperty().bind(locMainAnchor.heightProperty());
        locMainAnchor.widthProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                locTrayAnchor.setPrefWidth(new Double(newValue.doubleValue()-34));
                formMainController.setSLIDE_ANCHOR_PROJECT(new Double(640+(1884-newValue.doubleValue())));
                formMainController.setSLIDE_ANCHOR_OPEN(new Double(newValue.doubleValue()-44));
            }
        });
        
        
        runner = new Thread(new Runnable() {
            @Override
            public void run() {
                while(true){
                    try {
                        if (locRoadEdit!=null && locSectionStep!=null && locRoadEdit.getValue()!=null && locSectionStep.getValue()!=null) {
                            SectionValue.setRoadLenght(locRoadEdit.getValue());
                            SectionValue.setSectionStep(Float.valueOf(locSectionStep.getValue()));
    }
                        Thread.sleep(1000);
                    } catch (InterruptedException ex) {
                    }
                }
            }
        });
        runner.start();
    
        //
        locClaimView.getSelectionModel().select(0);
    }
    
    private void loadProjectAction(List<Report> loaded) {
        this.clearProjectTree(false);
        if (loaded!=null)
            if (loaded.size() > 0) {
                        //elementTree.loadTreeItems(new Report("Name project", 0));
                        int treeIndex = 0;
                        for (int j=0; j < loaded.size(); j++) {
                            Report report = loaded.get(j);
                            System.out.println("Report "+report.getId()+" add "+report.getTitle());
                           //Model.getInstance().addReport(report); 
                           if (report.getIndex() == Model.getInstance().TRAY_INDEX) {
                               treeIndex = elementTree.addTreeItems(report);
                           }
                           if (report.getIndex() == Model.getInstance().CON_INDEX) {
                               elementTree.addTreeLeaf(treeIndex, report);
                           }
                           if (report.getIndex() == Model.getInstance().COV_INDEX) {
                               elementTree.addTreeItems(report);
                           }
                           if (report.getIndex() >3 && report.getIndex() <= 5) {
                               elementTree.addLastLeaf(report);
                           }
                           if (report.getIndex() > 5) {
                               elementTree.addTreeItems(report);
                           }
                           
                        }
            } else {
                System.err.println("Not load report project");
            }
    }
    
    
    @ActionProxy(text="Загрузить", graphic=imagePath1, accelerator="ctrl+shift+T")
    private void action11() {
    	 System.out.println( "Action 1.1 is executed");
        controller.addData(Model.getInstance().reports);
        controller.process(Command.LOAD_PR, Command.LOAD_PR);
        try {
            Thread.yield();
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            Logger.getLogger(formMainController.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (Model.getInstance().reports.size() ==0)
            System.err.println("Not loaded data");
        
        List<Report> loaded = Model.getInstance().reports;
        if (loaded!=null) {
            if (loaded.size() > 0) {
                if (Model.getInstance().reports.size() > 0) {
                    Alert alert = new Alert(AlertType.CONFIRMATION, "Если вы загрузите новый проект, то текущий будет стерт ?", ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
                    alert.setHeaderText("Внимание !!!");
                    alert.showAndWait();

                    if (alert.getResult() == ButtonType.YES) {

                        this.loadProjectAction(loaded);
                        isInitReport = true;
                    }
                }else {

                    this.loadProjectAction(loaded);
                    isInitReport = true;
                }
                locCountPrElem.setText(""+Model.getInstance().reports.size());
                Model.getInstance().setCountReport(Model.getInstance().reports.size());
            }
        }
        
    }
    
    @ActionProxy(text="Сохранить", graphic=imagePath1)
    private void action12() {
    	 System.out.println( "Action 1.2 is executed");
        controller.addData(Model.getInstance().reports);
        controller.process(Command.SAVE_PR, Command.SAVE_PR);
        if (controller.getOutput()!=null)
            this.AlertDialogShow("Успешно", 1);
    }
    
    @ActionProxy(text="Выход", graphic=imagePath2)
    private void action13() {
    	 System.out.println( "Action 1.3 is executed");
         runner.stop();
         System.exit(0);
    }
    
    @ActionProxy(text="Сформировать отчет", graphic=imagePath2)
    private void action21() {
        /*
    	   Command task = new ReportCommand();
        try {
            task.execute("");
        } catch (IOException ex) {
            Logger.getLogger(formMainController.class.getName()).log(Level.SEVERE, null, ex);
        }
        */
        
        controller.addData(Model.getInstance().reports);
        controller.process(Command.REPORT_S, "");
        if (controller.getOutput()!=null)
            this.AlertDialogShow("Успешно", 1);
        
    }
    
    @ActionProxy(text="Показать отчет", graphic=imagePath2)
    private void action22() {
        try {
            /*
            Command task = new ReportCommand();
            try {
            task.execute("");
            } catch (IOException ex) {
            Logger.getLogger(formMainController.class.getName()).log(Level.SEVERE, null, ex);
            }
            */
            
            controller.addData(Model.getInstance().reports);
            controller.process(Command.REPORT_S, "2");
            if (controller.getOutput()!=null)
                Desktop.getDesktop().open(new File("object_kop_output.xls")); // /tmp/MyFirstExcel.xlsx
        } catch (IOException ex) {
            Logger.getLogger(formMainController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    
    @ActionProxy(text="Краткий отчет по прайсу", graphic=imagePath1)
    private void action221() {
    	 System.out.println( "Action 2.2.1 is executed");
         this.showReportWin();
    }
    
    @ActionProxy(text="Полный отчет", graphic=imagePath1)
    private void action222() {
    	 System.out.println( "Полный отчет (смета)");
         this.showReportWin();
    }   
    
    @ActionProxy(text="Отчет по выбранным аксесуарам", graphic=imagePath1)
    private void action23() {
    	 System.out.println( "Action 2.3 is executed");
         this.showReportWin();
    }
    
    @ActionProxy(text="Основной вид", graphic=imagePath1)
    private void action31() {
    	 System.out.println( "Action 3 is executed");
    }
    
    @ActionProxy(text="Расширенный вид (упрощенный)", graphic=imagePath1)
    private void action32() {
    	 System.out.println( "Action 3 is executed");
    }
    
    @ActionProxy(text="О программе", graphic=imagePath2)
    private void action41() {
    	 System.out.println( "Action 4 is executed");
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/emidetail/view/forms/formAbout.fxml"));
            /* 
             * if "fx:controller" is not set in fxml
             * fxmlLoader.setController(NewWindowController);
             */
            Scene scene = new Scene(fxmlLoader.load(), 600, 550);
            Stage stage = new Stage();
            stage.setTitle("О программе");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            Logger logger = Logger.getLogger(getClass().getName());
            logger.log(Level.SEVERE, "Failed to create new Window.", e);
        }
    }

    @Override
    public void update(Observable o, Object o1) {
        System.out.println("Update method");
        System.out.println("Flist "+Model.getInstance().flist.size());
        System.out.println("Conlist "+Model.getInstance().con_list.size());
        locTrayView.setItems(Model.getInstance().flist);
        locClampingView.setItems(Model.getInstance().con_list);
        
        locAcsTable.setItems(Model.getInstance().addFlist);
    }

    @FXML
    private void handleTArtReleased(KeyEvent event) {
        System.out.println("Edit "+ locArtEdit.getText());
        dataFilter.setArticul(locArtEdit.getText());
        Model.getInstance().filtered(dataFilter, true);
    }

    @FXML
    private void handleTraySelected(MouseEvent event) {
        if (locTrayView.getSelectionModel().getSelectedItem()!=null){
            System.out.println("Table select "+locTrayView.getSelectionModel().getSelectedItem().getArt()+ "  "+ locTrayView.getSelectionModel().getSelectedItem().getId());
            Trays tray = locTrayView.getSelectionModel().getSelectedItem();
            Model.getInstance().updateConnectors(tray);
            Model.getInstance().updateCover(tray);
            Model.getInstance().updateDividers(tray);
            
            Model.getInstance().updateIllustratTray(tray);
            
            
            locTrayArea.setWrapText(true);
            String descTray = tray.getAbbr();
            descTray = descTray.concat(", "+tray.getWidthStr()+", "+tray.getHeightStr()+", "+tray.getLengthStr());
            descTray = descTray.concat(", "+tray.getDescription());
            descTray = descTray.concat(", "+tray.getThsStr());
            descTray = descTray.concat(", допустимая нагрузка на лоток 1,2 кН/м, при расстоянии между опорами 2 метра");
            descTray = descTray.concat(", "+ConstructionFunctions.getDescriptionFromSource(tray.getConstructionId()));
            locTrayArea.setText(descTray);
            
            Illustration ill = Model.getInstance().illustrat;
            if (ill!=null) {
                System.out.println("Illustration desc "+ill.getDescription());
                locTrayImage.setImage(new Image(new ByteArrayInputStream(ill.getImgData())));
            } else {
                System.out.println("Illustration not set");
                locTrayImage.setImage(new Image(input3));
            }
            
            if (Model.getInstance().con_list!=null) 
                if (Model.getInstance().con_list.size() > 0)
                    locClampingView.getSelectionModel().select(0);
            
            
        }
    }

    
    @FXML
    private void handleTypeAction(ActionEvent event) {
        int sel_index = locTrayType.getSelectionModel().getSelectedIndex();
        System.out.println("Sel "+sel_index+" - "+locTrayType.getValue());
        int type_index = 0;
        if (sel_index == 1){
            type_index = 1;
        }
        if (sel_index == 2){
            type_index = 2;
        }
        if (sel_index == 3){
            type_index = 3;
        }
        if (sel_index == 4){
            type_index = 4;
        }
        if (sel_index == 5){
            type_index = 5;
        }
        if (sel_index == 6){
            type_index = 6;
        }
        if (sel_index == 7){
            type_index = 7;
        }
        if (sel_index == 8){
            type_index = 8;
        }
        if (sel_index == 9){
            type_index = 9;
        }
        dataFilter.addType(type_index);
        Model.getInstance().filtered(dataFilter, false);
    }

    @FXML
    private void handleSourceAction(ActionEvent event) {
        int sel_index = locTraySource.getSelectionModel().getSelectedIndex();
        System.out.println("Sel "+sel_index+" - "+locTraySource.getValue());
        int source_index = 0;
        if (sel_index < 6) {
            source_index = sel_index;
        } else {
            source_index = 6;
        }
        dataFilter.addSource(source_index);
        Model.getInstance().filtered(dataFilter, false);
    }
    
    
    private void AddReportValues(int state, SectionValue vals, String code) {
        String temp = "";
        Report rep = null;
        float mass_dec = 0;
        
        DecimalFormatSymbols otherSymbols = new DecimalFormatSymbols(Locale.getDefault());
        otherSymbols.setDecimalSeparator('.');
        DecimalFormat df = new DecimalFormat("##0.00", otherSymbols);

        if (state == 1 ) {
            rep = new Report();
            //temp = Model.getInstance().selectTray.getTitleDisplay()+"   "+vals.countSection;
            //System.out.println("Title tr "+Model.getInstance().selectTray.getTitleDisplay());
            rep.setTitle(""+Model.getInstance().selectTray.getTitleDisplay());
            rep.setFull(locTrayArea.getText());
            rep.setType(""+Model.getInstance().selectTray.getSeria());
            rep.setArticul(Model.getInstance().selectTray.getArt());
            rep.setCount(vals.countSection);
            rep.setCount_type("шт.");
            rep.setDescription("АО «СЗ ЭМИ»");
            rep.setComment("вес за штуку");
            String mass_tr = Model.getInstance().selectTray.getMass();
            mass_tr = mass_tr.replace(",", ".");
            rep.setMass(Float.valueOf(mass_tr));
            rep.setIndex(Model.getInstance().TRAY_INDEX);
            String format_mass = df.format((double)rep.getMass());
            System.out.println("*******Format "+format_mass);
            mass_dec = Float.valueOf(format_mass);
            rep.setMass(mass_dec);
            Model.getInstance().addReport( rep);
        }
        if (state == 2) {
            //temp = locClampingView.getSelectionModel().getSelectedItem().getTitleDisplay()+"   "+vals.countConnector+
             ///           ""+locCover.getSelectionModel().getSelectedItem().getTitleDisplay()+"      "+vals.countSection;
            if (locConCheck.isSelected()) {
                Connector conSel = locClampingView.getSelectionModel().getSelectedItem();
            
                rep = new Report();
                //System.out.println("Title con "+locClampingView.getSelectionModel().getSelectedItem().getTitleDisplay());
                rep.setTitle(conSel.getTitleDisplay()+", высота "+conSel.getHeight()+
                        ", толщина "+conSel.getThickness()+"мм"+
                        ", исполнение"+conSel.getConstructionId());
                
                rep.setFull(conSel.getTitleDisplay()+", толщина "+conSel.getThickness()+"мм"+
                       ", "+ConstructionFunctions.getDescriptionFromSource(conSel.getConstructionId()) );
                rep.setType(""+conSel.getClass_());
                rep.setArticul(conSel.getArt());
                rep.setCount(vals.countConnector);
                rep.setCount_type("шт.");
                rep.setDescription("АО «СЗ ЭМИ»");
                rep.setComment("вес за штуку");
                String mass_con = conSel.getMass();
                mass_con = mass_con.replace(",", ".");
                rep.setMass(Float.valueOf(mass_con));
                rep.setIndex(Model.getInstance().CON_INDEX);
                mass_dec = Float.valueOf(df.format(rep.getMass()));
                rep.setMass(mass_dec);
                Model.getInstance().addReport(rep);
                
                 Model.getInstance().metis_list.clear();
                Model.getInstance().updateMetis(Model.getInstance().selectTray,conSel);
                if (Model.getInstance().metis_list != null)
                    if (Model.getInstance().metis_list.size() > 0 ) {
                        isConnectMetis = true;
                        rep = new Report();
                        rep.setTitle("Комплект метизов "+locMetisView.getItems().get(0));
                        rep.setFull(rep.getTitle());
                        rep.setType("");
                        rep.setArticul(locMetisView.getItems().get(0));
                        // System.out.println("Metis art "+locMetisView.getItems().get(0));
                        rep.setCount(vals.countMetis);
                        rep.setDescription("");
                         rep.setComment("вес за штуку");
                        rep.setCount_type("шт.");
                        rep.setMass(Float.valueOf(Model.getInstance().mass_list.get(0).replace(",", ".")));
                        rep.setIndex(Model.getInstance().METIS_INDEX);
                        rep.setParent(Model.getInstance().getLastReport().getId());
                        mass_dec = Float.valueOf(df.format(rep.getMass()));
                        rep.setMass(mass_dec);
                        Model.getInstance().addReport(rep);
                    }
            
            }
            
        }
        if (state == 3) {
            //temp = locClampingView.getSelectionModel().getSelectedItem().getTitleDisplay()+"   "+vals.countConnector+
            //            ""+locCover.getSelectionModel().getSelectedItem().getTitleDisplay()+"      "+vals.countSection+
            //            code+"                       "+vals.countStands;
            if (locCoverCheck.isSelected()) {
            
                if (locCover.getSelectionModel().getSelectedItem()!=null) {
                    rep = new Report();
                    Cover coverSel = locCover.getSelectionModel().getSelectedItem();
                    //System.out.println("Title cov "+locCover.getSelectionModel().getSelectedItem().getTitleDisplay());
                    rep.setTitle(coverSel.getTitleDisplay()
                            +", длина "+coverSel.getLength()
                            +", ширина "+coverSel.getWidth()
                            +", толщина "+coverSel.getThickness()+" мм"
                            +", исполнение "+coverSel.getConstructionId());
                    rep.setFull(coverSel.getTitleDisplay()
                            +", ширина "+coverSel.getWidth()
                            +", длина "+coverSel.getLength()
                            +", толщина "+coverSel.getThickness()
                            + ", " + coverSel.getDescription()
                            +", "
                            +ConstructionFunctions.getDescriptionFromSource(coverSel.getConstructionId()));
                    rep.setType(coverSel.getClass_());
                    rep.setArticul(coverSel.getArt());
                    rep.setCount(vals.countCover);
                    rep.setCount_type("шт.");
                    rep.setDescription("АО «СЗ ЭМИ»");
                    rep.setComment("вес за штуку");
                    rep.setMass(Float.valueOf(coverSel.getMass().replace(",",".")));
                    rep.setIndex(Model.getInstance().COV_INDEX);

                    mass_dec = Float.valueOf(df.format(rep.getMass()));
                    rep.setMass(mass_dec);
                    Model.getInstance().addReport( rep);

                    
                                        
                    boolean ishard = false; 
                    if (locSectionStep.getSelectionModel().getSelectedIndex() > 4) {
                        ishard = true;
                    }
                    if (locIsClaim.isSelected()) {
                        ishard = true;
                    }
                    if (locIsFixed.isSelected()) {
                        ishard = false;
                    }
                    int standType = 0;
                    if (locClaimView.getSelectionModel().getSelectedItem()!=null)
                        if (locClaimView.getSelectionModel().getSelectedIndex()>0){
                            standType = 1;
                        }
                    Model.getInstance().claimses.clear();
                    Model.getInstance().updateClaimsByTrays(standType,Model.getInstance().selectTray, ishard, 1); // ???  
                    
                    vals.hard = ishard;
                    vals = calcSectionValues();
                   
                    List<Claims> claims = Model.getInstance().claimses;
                    if (claims!=null)
                        if (claims.size()>0) {
                            isCoverClaims = true;
                            rep = new Report();
                            System.err.println("3 Claims find - "+claims.get(0).getAbbr());
                            rep.setTitle(claims.get(0).getAbbr()+", исполнение "+claims.get(0).getConstructionId());
                            rep.setFull(claims.get(0).getAbbr()+
                                    ", толщина "+claims.get(0).getThickness()+" мм"+
                                    ", "+ConstructionFunctions.getDescriptionFromSource(claims.get(0).getConstructionId()));
                            rep.setType(""+claims.get(0).getClass_());
                            rep.setArticul(claims.get(0).getArticul());
                            //if (claims.get(0).getType()==51 || claims.get(0).getType()==61 || claims.get(0).getType()==65 || claims.get(0).getType()==54) {
                            //    rep.setCount(vals.countSection*2);
                            //} else {
                            //    rep.setCount(vals.countSection);
                            //}
                            rep.setCount(vals.countCoverClaim);
                            rep.setCount_type("шт.");
                            rep.setDescription("АО «СЗ ЭМИ»");
                            rep.setComment("");
                            rep.setMass(Float.valueOf(claims.get(0).getMass().replace(",", ".")));
                            rep.setIndex(5);
                            mass_dec = Float.valueOf(df.format(rep.getMass()));
                            rep.setMass(mass_dec);
                            Model.getInstance().addReport(rep);
                        }
                    
                    
                    //standType
                    if (locSectionStep.getSelectionModel().getSelectedIndex() > 4) {
                        ishard = true;
                    } else {
                        ishard = false;
                    }
                    
                    Model.getInstance().metislinks.clear();
                    Model.getInstance().updateMetisByStand(standType, Model.getInstance().selectTray, ishard, 1);
                    List<Metis> metises = Model.getInstance().metislinks;
                    if (metises != null)
                        if (metises.size() > 0) {
                            isCoverMetis = true;
                            rep = new Report();
                            rep.setTitle("Комплект метизов "+metises.get(0).getArticul());
                            rep.setFull(rep.getTitle());
                            rep.setType("");
                            rep.setArticul(metises.get(0).getArticul());
                            rep.setCount(vals.countMetisCov);
                            rep.setCount_type("шт.");
                            rep.setDescription("АО «СЗ ЭМИ»");
                            rep.setComment("");
                            rep.setMass(Float.valueOf(metises.get(0).getMass().replace(",", ".")));
                            rep.setIndex(5);
                            mass_dec = Float.valueOf(df.format(rep.getMass()));
                            rep.setMass(mass_dec);
                            Model.getInstance().addReport(rep);
                        }
                }
            }
        }
        if (state == 7) {
            if (locClaimCheck.isSelected() && locClaimView.getSelectionModel().getSelectedItem()!=null) {
                boolean ishard = false; 
                    if (locSectionStep.getSelectionModel().getSelectedIndex() > 4) {
                        ishard = true;
                    }
                int standType = 0;
                    if (locClaimView.getSelectionModel().getSelectedItem()!=null)
                        if (locClaimView.getSelectionModel().getSelectedIndex()>0){
                            standType = 1;
                        }    
                    
                int covis;
                if (locCoverCheck.isSelected() && locCover.getSelectionModel().getSelectedItem()!=null) {
                    covis = 2;
                    Model.getInstance().updateClaimsByTrays(standType,Model.getInstance().selectTray, ishard, covis); // ???  
                } else {
                    covis = 0;
                    Model.getInstance().updateClaimsByTrays(standType,Model.getInstance().selectTray, ishard, covis); // ???  
                }    
                  
                //Model.getInstance().claimses
                
                 
                boolean isfixedstand = false;  
                List<Claims> claims = Model.getInstance().claimses;
                if (claims!=null)
                 if (claims.size()> 0)    
                if (claims.get(0).getType()==51 || claims.get(0).getType()==61 || claims.get(0).getType()==65 || claims.get(0).getType()==54) {
                    isfixedstand = true;
                } else {
                    isfixedstand = false;
                }
                vals.fixed = isfixedstand;
                vals.hard = ishard;
                 vals = calcSectionValues();
                 
                 
                
                
                if (claims!=null)
                    if (claims.size()>0) {
                        isTrayClaims = true;
                        rep = new Report();
                        System.err.println("4 Claims find - "+claims.get(0).getAbbr());
                        rep.setTitle(claims.get(0).getAbbr()+", исполнение "+claims.get(0).getConstructionId());
                        rep.setFull(claims.get(0).getAbbr()+
                                    ", толщина "+claims.get(0).getThickness()+" мм"+
                                    ", "+ConstructionFunctions.getDescriptionFromSource(claims.get(0).getConstructionId()));
                        rep.setType(""+claims.get(0).getClass_());
                        rep.setArticul(claims.get(0).getArticul());
                        //if (claims.get(0).getType()==51 || claims.get(0).getType()==61 || claims.get(0).getType()==65 || claims.get(0).getType()==54) {
                        //    rep.setCount(vals.countSection*2);
                        //} else {
                        //    rep.setCount(vals.countSection);
                        //}
                        rep.setCount(vals.countClaim);
                        rep.setCount_type("шт.");
                        rep.setDescription("АО «СЗ ЭМИ»");
                        rep.setComment("");
                        rep.setMass(Float.valueOf(claims.get(0).getMass().replace(",", ".")));
                        rep.setIndex(5);
                        mass_dec = Float.valueOf( df.format(rep.getMass()));
                        rep.setMass(mass_dec);
                        Model.getInstance().addReport(rep);
                    }
                
                
                Model.getInstance().metislinks.clear();
                Model.getInstance().updateMetisByStand(standType, Model.getInstance().selectTray, ishard, covis);
                    List<Metis> metises = Model.getInstance().metislinks;
                    boolean covisFind = false;
                    if (metises != null)
                        if (metises.size() > 0) {
                            isClaimsMetis = true;
                            covisFind = true;
                            rep = new Report();
                            rep.setTitle("Комплект метизов "+metises.get(0).getArticul());
                            rep.setFull(rep.getTitle());
                            rep.setType("");
                            rep.setArticul(metises.get(0).getArticul());
                            rep.setCount(vals.countMetisClaim);
                            rep.setCount_type("шт.");
                            rep.setDescription("АО «СЗ ЭМИ»");
                            rep.setComment("");
                            rep.setMass(Float.valueOf(metises.get(0).getMass().replace(",", ".")));
                            rep.setIndex(5);
                            mass_dec = Float.valueOf(df.format(rep.getMass()));
                            rep.setMass(mass_dec);
                            Model.getInstance().addReport(rep);
                        }
                    if (!covisFind && covis==2) {
                        Model.getInstance().updateMetisByStand(standType, Model.getInstance().selectTray, ishard, 0);
                         metises = Model.getInstance().metislinks;
                        if (metises != null)
                        if (metises.size() > 0) {
                            isClaimsMetis = true;
                            rep = new Report();
                            rep.setTitle("Комплект метизов "+metises.get(0).getArticul());
                            rep.setFull(rep.getTitle());
                            rep.setType("");
                            rep.setArticul(metises.get(0).getArticul());
                            rep.setCount(vals.countMetisClaim);
                            rep.setCount_type("шт.");
                            rep.setDescription("АО «СЗ ЭМИ»");
                            rep.setComment("");
                            rep.setMass(Float.valueOf(metises.get(0).getMass().replace(",", ".")));
                            rep.setIndex(5);
                            mass_dec = Float.valueOf(df.format(rep.getMass()));
                            rep.setMass(mass_dec);
                            Model.getInstance().addReport(rep);
                        }
                    }
            }
        }
        if (state == 8) {
            if (locDividerCheck.isSelected()) {
                if (locDividers.getSelectionModel().getSelectedItem()!=null) {
                    isDivision = true;
                    rep = new Report();
                    Division divider = locDividers.getSelectionModel().getSelectedItem();
                    rep.setTitle(divider.getTitleDisplay());
                    rep.setFull(divider.getTitleDisplay().replace("исполнение 1", "").replace("исполнение 2", "").replace("исполнение 6", "")+
                            ", толщина "+divider.getThickness()+" мм"+
                                    ", "+ConstructionFunctions.getDescriptionFromSource(divider.getConstructionId()));
                    rep.setType("");
                    rep.setArticul(divider.getArt());
                    rep.setCount(vals.countDividers);
                    rep.setCount_type("шт.");
                    rep.setDescription("АО «СЗ ЭМИ»");
                    rep.setComment("вес за штуку");
                    rep.setMass(Float.valueOf(divider.getMass().replace(",", ".")));
                    rep.setIndex(5);
                    mass_dec = Float.valueOf(df.format(rep.getMass()));
                    rep.setMass(mass_dec);
                    Model.getInstance().addReport(rep);
                    int typeId = 4;
                    if (divider.getConstructionId() > 1) {
                        typeId = 5;
                    }
                    if (divider.getConstructionId() > 3) {
                        typeId = 6;
                    }
                    
                    if (locInnerClaim2.isSelected()) {
                        
                    }
                    
                    List<Metis> metises = MetisFunctions.getMetisByType(new Types(typeId));
                    if (metises != null)
                        if (metises.size() > 0) {
                            isDividerMetis = true;
                            rep = new Report();
                            rep.setTitle("Комплект метизов "+metises.get(0).getArticul());
                            rep.setFull(rep.getTitle());
                            rep.setType("");
                            rep.setArticul(metises.get(0).getArticul());
                            rep.setCount(vals.countMetisDividers);
                            rep.setCount_type("шт.");
                            rep.setDescription("АО «СЗ ЭМИ»");
                            rep.setComment("");
                            rep.setMass(Float.valueOf(metises.get(0).getMass().replace(",", ".")));
                            rep.setIndex(5);
                            mass_dec = Float.valueOf(df.format(rep.getMass()));
                            rep.setMass(mass_dec);
                            Model.getInstance().addReport(rep);
                        }
                }
                
                List<Claims> innerClaims = ClaimsFunctions.getInnerClaims(Model.getInstance().selectTray);
                if (innerClaims!=null)
                    if (innerClaims.size() > 0) {
                        
                        isInnerClaims = true;
                        rep = new Report();
                        System.err.println("4 Claims find - "+innerClaims.get(0).getAbbr());
                        rep.setTitle(innerClaims.get(0).getAbbr()+", исполнение "+innerClaims.get(0).getConstructionId());
                        rep.setFull(innerClaims.get(0).getAbbr()+
                                    ", толщина "+innerClaims.get(0).getThickness()+" мм"+
                                    ", "+ConstructionFunctions.getDescriptionFromSource(innerClaims.get(0).getConstructionId()));
                        rep.setType(""+innerClaims.get(0).getClass_());
                        rep.setArticul(innerClaims.get(0).getArticul());
                        //if (claims.get(0).getType()==51 || claims.get(0).getType()==61 || claims.get(0).getType()==65 || claims.get(0).getType()==54) {
                        //    rep.setCount(vals.countSection*2);
                        //} else {
                        //    rep.setCount(vals.countSection);
                        //}
                        
                        rep.setCount(vals.countSection*2);
                        if (Model.getInstance().selectTray!=null) {
                            if (Model.getInstance().selectTray.getLength()> 3000) {
                                rep.setCount(vals.countSection*3);
            }
                            if (Model.getInstance().selectTray.getLength() >5000) {
                                rep.setCount(vals.countSection*4);
        }
                        }
                        rep.setCount_type("шт.");
                        rep.setDescription("АО «СЗ ЭМИ»");
                        rep.setComment("");
                        rep.setMass(Float.valueOf(innerClaims.get(0).getMass().replace(",", ".")));
                        rep.setIndex(5);
                        mass_dec = Float.valueOf(df.format(rep.getMass()));
                        rep.setMass(mass_dec);
                        Model.getInstance().addReport(rep);
                    }
            }
        }
        if (state == 9) {
            rep = new Report();
            rep.setTitle(locAcsTable.getSelectionModel().getSelectedItem().getDefinition());
            rep.setFull(rep.getTitle());
            rep.setType("");
            rep.setArticul(locAcsTable.getSelectionModel().getSelectedItem().getArticul());
            int countAcs = 1;
            try{
                countAcs = Integer.valueOf(locCountAcs.getText());
            } catch (Exception e) {
                
            }
            rep.setCount(countAcs);
            rep.setCount_type("шт.");
            rep.setIndex(Model.getInstance().STAND_INDEX);
            rep.setMass(Float.valueOf(locAcsTable.getSelectionModel().getSelectedItem().getMass().replace(",", ".")));
            rep.setDescription("АО «СЗ ЭМИ»");
            rep.setComment("");
            mass_dec = Float.valueOf(df.format(rep.getMass()));
            rep.setMass(mass_dec);
            Model.getInstance().addReport(rep);
        }
        if (state == 4) {
            rep = new Report();
            rep.setTitle(code);
            rep.setFull(rep.getTitle());
            rep.setType("");
            rep.setArticul("");
            rep.setCount(vals.countStands);
            rep.setCount_type("шт.");
            rep.setIndex(Model.getInstance().STAND_INDEX);
            rep.setDescription("АО «СЗ ЭМИ»");
            rep.setComment("");
            mass_dec = Float.valueOf(df.format(rep.getMass()));
            rep.setMass(mass_dec);
            Model.getInstance().addReport(rep);
        }
        if (state == 5) {
            Stand stand = locListStands.getSelectionModel().getSelectedItem();
            Profil profil = locListProfil.getSelectionModel().getSelectedItem();
            rep = new Report();
            rep.setTitle(profil.getTitle());
            rep.setFull(rep.getTitle());
            rep.setType(""+profil.getTypeSpec());
            rep.setArticul(profil.getArticul());
            int count2 = 0;
            try{
                count2 = Integer.valueOf(code);
            }catch(Exception e) {
                
            }
            rep.setCount(count2);
            rep.setCount_type("шт.");
            rep.setMass(Float.valueOf(profil.getMass().replace(",", ".")));
            rep.setIndex(Model.getInstance().PROF_INDEX);
            rep.setDescription("АО «СЗ ЭМИ»");
            rep.setComment("");
            mass_dec = Float.valueOf(df.format(rep.getMass()));
            rep.setMass(mass_dec);
            Model.getInstance().addReport(rep);
            
            
            rep = new Report();
            rep.setTitle(stand.getTitle()+" "+stand.getWidth()+"x"+stand.getHeight()+" L="+stand.getLength()+", "+stand.getThsStr()+", исполнение "+stand.getConstructionId());
            rep.setFull(rep.getTitle());
            rep.setType(""+stand.getTypeSpec());
            rep.setArticul(stand.getArticul());
             count2 = 0;
            try{
                count2 = Integer.valueOf(locStandCounts.getText());
            }catch(Exception e) {
                
            }
            rep.setCount(count2);
            rep.setCount_type("шт.");
            rep.setMass(Float.valueOf(stand.getMass().replace(",", ".")));
            rep.setIndex(Model.getInstance().PROF_INDEX);
            rep.setDescription("АО «СЗ ЭМИ»");
            rep.setComment("");
            mass_dec = Float.valueOf(df.format(rep.getMass()));
            rep.setMass(mass_dec);
            Model.getInstance().addReport(rep);
            
        }
        if (state == 6) {
            int code2 = 0;
            try{
                code2 = Integer.valueOf(code);
            }catch(Exception e) {
                
            }
            if (code2 == 1 ) {
                
                Stand stand = locListStands.getSelectionModel().getSelectedItem();
                rep = new Report();
                rep.setTitle(stand.getTitle()+" "+stand.getWidth()+"x"+stand.getHeight()+" L="+stand.getLength()+", "+stand.getThsStr()+", исполнение "+stand.getConstructionId());
                rep.setFull(rep.getTitle());
                rep.setType("");
                rep.setArticul(stand.getArticul());
                int count3 = 0;
                try{
                    count3 = Integer.valueOf(locStandCounts.getText());
                }catch(Exception e) {

                }
                rep.setCount(count3);
                rep.setCount_type("шт.");
                rep.setMass(Float.valueOf(stand.getMass().replace(",", ".")));
                rep.setIndex(3);
                rep.setDescription("АО «СЗ ЭМИ»");
                rep.setComment("");
                mass_dec = Float.valueOf(df.format(rep.getMass()));
                rep.setMass(mass_dec);
                Model.getInstance().addReport(rep);
            }
            if (code2 == 2 ) {
                Profil profil = locListProfil.getSelectionModel().getSelectedItem();
                rep = new Report();
                rep.setTitle(profil.getTitle());
                rep.setFull(rep.getTitle());
                rep.setType(""+profil.getTypeSpec());
                rep.setArticul(profil.getArticul());
                int count3 = 0;
                try{
                    count3 = Integer.valueOf(locProfilCounts.getText());
                }catch(Exception e) {

                }
                rep.setCount(count3);
                rep.setCount_type("шт.");
                rep.setMass(Float.valueOf(profil.getMass().replace(",", ".")));
                rep.setIndex(3);
                rep.setDescription("АО «СЗ ЭМИ»");
                rep.setComment("");
                mass_dec = Float.valueOf(df.format(rep.getMass()));
                rep.setMass(mass_dec);
                Model.getInstance().addReport(rep);
            }
            if (code2 == 3 ) {
                Bracket brack = locListClaims.getSelectionModel().getSelectedItem();
                rep = new Report();
                rep.setTitle(brack.getTitleDisplay());
                rep.setFull(rep.getTitle());
                rep.setType(""+brack.getTypeSpec());
                rep.setArticul(brack.getArticul());
                int count3 = 0;
                try{
                    count3 = Integer.valueOf(locClaimsCounts.getText());
                }catch(Exception e) {

                }
                rep.setCount(count3);
                rep.setCount_type("шт.");
                rep.setMass(Float.valueOf(brack.getMass().replace(",", ".")));
                rep.setIndex(3);
                rep.setDescription("АО «СЗ ЭМИ»");
                rep.setComment("");
                mass_dec = Float.valueOf(df.format(rep.getMass()));
                rep.setMass(mass_dec);
                Model.getInstance().addReport(rep);
            }
            
        }
    }
    
    public void clearProjectTree(boolean clReport) {
        if (clReport) {
            Model.getInstance().reports.clear();
        }
            Model.getInstance().setLastMainReport(0);
            Model.getInstance().setLastTrayReport(0);
            Model.getInstance().setSecondReport(0);
            Model.getInstance().setCountReport(0);
            locCountPrElem.setText("0");
        
            elementTree.initialize();
    }

    @FXML
    private void handleTrayIn(ActionEvent event) {
        // Add Tray to Tree
        //locAccord.setExpandedPane(locTiled2);
        locStateEdit.setPromptText("перейти к выбору крепления лотка");
        
        if (!isInitReport){
        
            if (Model.getInstance().selectTray!=null)
                AddReportValues(1, calcSectionValues(), null);

            this.addTrayProject();
            isInitReport = true;
            this.clearProjectTree(true);

        } 
        if (Model.getInstance().selectTray!=null)
            AddReportValues(1, calcSectionValues(), null);
        
        this.addTrayProject();
    }
    
    

    @FXML
    private void handleSelectUnitList(MouseEvent event) {
        if (locUnitList.getSelectionModel().getSelectedItem()!=null) {
            
            Model.getInstance().updateIllustrat(locUnitList.getSelectionModel().getSelectedItem());
            Illustration ill = Model.getInstance().illustrat;
            System.out.println("Illustration abbr "+ill.getAbbr());
            locUnitImage.setImage(new Image(new ByteArrayInputStream(ill.getImgData())));
            
            locStandEdit.setText("Параметры "+locUnitList.getSelectionModel().getSelectedItem().getStandNames());
            locProfilEdit.setText("Параметры "+locUnitList.getSelectionModel().getSelectedItem().getSectionName());
            
            editor.generateStandsData( locUnitList.getSelectionModel().getSelectedItem().getStandNames(), locUnitList.getSelectionModel().getSelectedItem().getStandDouble(), 4);
            locStandTable.setItems(editor.getStandsData());
            locUnitConstruction.getSelectionModel().select(1);
            
            if (locUnitList.getSelectionModel().getSelectedItem().getClaimName()!=null){
                if (!locUnitList.getSelectionModel().getSelectedItem().getClaimName().equalsIgnoreCase("")) {
                    locClaimSpinner.setDisable(false);
                } else {
                    locClaimSpinner.setDisable(true);
                }
            } else {
                locClaimSpinner.setDisable(true);
            }
        }
    }

    @FXML
    private void handleClaimSelect(MouseEvent event) {
    }

    @FXML
    private void handleUnitCodeIn(ActionEvent event) {
        String element[] = new String[18];
        // Formating code on unit
        if (Model.getInstance().illustrat!=null) {
            String code = "";
            if (Model.getInstance().illustrat!=null) {
                code = code.concat(Model.getInstance().illustrat.getAbbr());
            } else {
                code = locUnitList.getSelectionModel().getSelectedItem().getTitleDisplay().substring(10);
            }
            if (locClaimSpinner !=null)
                if (locClaimSpinner.getValue()!=null)
            if (locClaimSpinner.getValue()!=0)
                element[0] = String.valueOf(locClaimSpinner.getValue());
            try {
                element[1] = String.valueOf(Math.round(  Float.valueOf(locUnitLength.getText().replace(",", "."))/10f));
            } catch (Exception e) {
                element[1] = "120";
            }
            element[2] = String.valueOf(Math.round(10*locUnitThsС.getValue()));
            int stand_ind = 0;
            for (Stands stand : locStandTable.getItems()) {
                if (stand.getCount()!=0) {
                   element[stand_ind+3] = String.valueOf(stand.getCount())+ String.valueOf(Math.round(stand.getLength()/10));
                   Float fths = Float.valueOf(String.valueOf(stand.getThs()));
                   element[stand_ind+4] = String.valueOf(Math.round(fths*10));
                   stand_ind = stand_ind + 2;
                }
            }
            element[stand_ind+3] = String.valueOf(locUnitConstruction.getSelectionModel().getSelectedIndex());
 
            
            
                    
            for (int i = 0; i < 18; i++) {
                if (element[i] != null)
                    code = code.concat(". "+element[i]);
            }
            
                    
            AddReportValues(4, calcSectionValues(),code);
            
            elementTree.addTreeItems(Model.getInstance().getLastReport());
        }
    }

    
        
    public void addTrayProject() {
        // Loads tree items
        if (Model.getInstance().selectTray != null){
            SectionValue val = calcSectionValues();
            
            if (locClampingView.getSelectionModel().getSelectedItem()!=null)
             AddReportValues(2, val, "");
            Report trayRep = Model.getInstance().getReport(Model.getInstance().getLastTrayReport());
            
            System.out.println("REPORT: ");
            System.out.println("1  Tray - "+trayRep.getTitle());
            
            indTray = elementTree.addTreeItems(trayRep);
            
            if (locConCheck.isSelected()) {

                Report conRep = Model.getInstance().getReport(Model.getInstance().getLastMainReport());
                System.out.println("2 Connector - "+conRep.getTitle());
                elementTree.addTreeItems(conRep);
                Report metRep = Model.getInstance().getParentReport(conRep.getId());
                if (metRep!=null && isConnectMetis){
                    isConnectMetis = false;
                    System.out.println("2 Connector met - "+metRep.getTitle());
                    elementTree.addLastLeaf(metRep);
                }
                if (isConnectMetis) {
                    if (metRep==null) {
                        System.err.println("2 Not connector metis add");
                    }
                }
                if (!isConnectMetis) {
                    System.err.println("2 Not find connector metis");
                }
            }
            
            if(locCoverCheck.isSelected())    
            if (locCover.getSelectionModel().getSelectedItem()!=null){    
                AddReportValues(3, val, "");
                Report covRep = Model.getInstance().getReport(Model.getInstance().getSecondReport());
                System.out.println("3 Cover "+covRep.getTitle());
                int covIndex = elementTree.addTreeItems( covRep);
                if (isCoverClaims) {
                    
                    Report claimCovRep = Model.getInstance().getLastPrevReport();
                    //Report claimCovRep = Model.getInstance().getParentReport(Model.getInstance().getSecondReport());
                    System.out.println("3 Claim for cover - "+claimCovRep.getTitle());
                    elementTree.addTreeLeaf(covIndex, claimCovRep);
                } else {
                    System.err.println("3 Not add claims for cover");
                }
                if (isCoverClaims)
                if (isCoverMetis){
                    //elementTree.addLastLeaf( Model.getInstance().getLastPrevReport());
                    Report metCovRep = Model.getInstance().getLastReport();
                    System.out.println("3 Cover met add - "+metCovRep.getTitle());
                    elementTree.addTreeLeaf(covIndex, metCovRep);
                    isCoverMetis = false;
                    //elementTree.addTreeLeaf(1, "Зажим крышки поясного типа "+Model.getInstance().claimStr+"FX        "+val.countSection*2);
                } else {
                    System.err.println("3 Cover metis not find");
                    elementTree.addLastLeaf( Model.getInstance().getParentReport(Model.getInstance().getSecondReport()));
                    //elementTree.addTreeLeaf(1, "Фиксатор крышки универсальный  KL0000FXS"+"            "+val.countSection*4);
                }
                
                if (isCoverClaims) {
                    isCoverClaims = false;
                }
            }
            
            if (locClaimCheck.isSelected() && locDividers.getSelectionModel().getSelectedItem()!=null) {
                AddReportValues(7, val, "");
                if (isTrayClaims) {
                    Report claimsRep = Model.getInstance().getLastPrevReport();
                    System.out.println("4 Claim add to tray - "+claimsRep.getTitle());
                    elementTree.addTreeLeaf(indTray, claimsRep);
                } else {
                    System.err.println("4 Claim for tray not add");
                }
                
                if (isClaimsMetis) {
                    System.out.println("4 Claim metis add - "+Model.getInstance().getLastReport().getTitle());
                    elementTree.addTreeLeaf(indTray, Model.getInstance().getLastReport());
                    isClaimsMetis = false;
                } else {
                    System.err.println("4 Claim metis not add");
                }
                
                if (isTrayClaims)
                    isTrayClaims =false;
            }
            
            if (locDividerCheck.isSelected() && locDividers.getSelectionModel().getSelectedItem()!=null) {
                AddReportValues(8, val, "");
                Integer dividerIndex = indTray;
                if (isDivision) {
                    Report divisionRep = null;
                    Report divMetisRep = null;
                    Report divInner = null;
                    if (isDividerMetis){
                        isDividerMetis = false;
                        if (isInnerClaims) {
                            divInner = Model.getInstance().getLastReport();
                            elementTree.addTreeLeaf(indTray, divInner);
                            divisionRep = Model.getInstance().getLastPrevPrevReport();
                            divMetisRep = Model.getInstance().getLastPrevReport();
                            
                            isInnerClaims = false;
                        } else {
                        divisionRep = Model.getInstance().getLastPrevReport();
                            divMetisRep = Model.getInstance().getLastReport();
                        }
                        dividerIndex = elementTree.addTreeDoubleLeaf(indTray, divisionRep,divMetisRep);
                        System.out.println("5 Divider and metis add to tray - "+divisionRep.getTitle());
                        
                    } else {
                        if (isInnerClaims) {
                            divisionRep = Model.getInstance().getLastPrevReport();
                            isInnerClaims = false;
                            divInner = Model.getInstance().getLastReport();
                            elementTree.addTreeLeaf(indTray, divInner);
                        } else {
                        divisionRep = Model.getInstance().getLastReport();
                        }
                        System.out.println("5 Divider add to tray - "+divisionRep.getTitle());
                        dividerIndex = elementTree.addTreeLeaf(indTray, divisionRep);
                    }
                    
                    isDivision = false;
                } else {
                    System.err.println("5 Divider not add ");
                }
                
            }
            
            locCountPrElem.setText(""+Model.getInstance().reports.size());
            
        } else {
            System.out.println("No selected trays");
        }
    }
    
    @FXML
    private void handleConnectorIn(ActionEvent event) {
        // Add Connector to Tree
        //locAccord.setExpandedPane(locTiled3);
        //locStateEdit.setPromptText("Расчет сечения лотка");
        
        //locTabPane.getSelectionModel().select(1);
        
        locSectionAccordion.setExpandedPane(locSectionAccordion.getPanes().get(0));
        
        this.addTrayProject();
        
    }

    @FXML
    private void handleFilterClear(ActionEvent event) {
        locArtEdit.clear();
        locIP44Box.setSelected(false);
        locHeight.setValue(null);
        locWidth.setValue(null);
        locTrayType.setValue(null);
        locTrayType.getSelectionModel().clearSelection();
        locTraySource.setValue(null);
        locTraySource.getSelectionModel().clearSelection();
        dataFilter = new SearchData();
        Model.getInstance().flist.clear();
        Model.getInstance().flist.addAll(Model.getInstance().tlist);
    }


    private void showProjectPanel() {
        if (slide_anchor) {
            locTrayAnchor.setPrefWidth(SLIDE_ANCHOR_OPEN);
            locProjectAnchor.setPrefWidth(SLIDE_ANCHOR_PROJECT);
            locLabelProjectName.setText("ОТКРЫТЬ проект");
        } else {
            locTrayAnchor.setPrefWidth(SLIDE_ANCHOR_CLOSE);
            locProjectAnchor.setPrefWidth(SLIDE_ANCHOR_OPEN);
            locLabelProjectName.setText("ЗАКРЫТЬ проект");
        }
        slide_anchor = !slide_anchor;
    }
    

    @FXML
    private void handleIP44Select(ActionEvent event) {
        dataFilter.setIp44(!dataFilter.isIp44());
        System.out.println("IP 44 "+dataFilter.isIp44());
        Model.getInstance().filtered(dataFilter, false);
    }

    @FXML
    private void handleShowProject(MouseEvent event) {
        this.showProjectPanel();
    }

    @FXML
    private void handleNotifProject(MouseEvent event) {
    }

    @FXML
    private void handleHeightAction(ActionEvent event) {
        System.out.println("Get height "+ locHeight.getValue());
        if (!locHeight.getValue().equalsIgnoreCase("")){
            dataFilter.setHeight(Integer.valueOf(locHeight.getValue()));
        } else {
            dataFilter.setHeight(0);
        }
        Model.getInstance().filtered(dataFilter, false);
    }

    @FXML
    private void handleWidthAction(ActionEvent event) {
        System.out.println("Get width "+locWidth.getValue());
        if (!locWidth.getValue().equalsIgnoreCase("")) {
            dataFilter.setWidth(Integer.valueOf(locWidth.getValue()));
        } else {
            dataFilter.setWidth(0);
        }
        Model.getInstance().filtered(dataFilter, false);
    }
    
    @FXML
    private void handleLengthAction(ActionEvent event) {
        System.out.println("Get length "+locLength.getValue());
        if (!locLength.getValue().equalsIgnoreCase("")) {
            dataFilter.setLength(Integer.valueOf(locLength.getValue()));
        } else {
            dataFilter.setLength(0);
        }
        Model.getInstance().filtered(dataFilter, false);
    }

    @FXML
    private void handleScoreEdit(ActionEvent event) {
        System.out.println("Filtered by score");
        Model.getInstance().filtered(dataFilter, false);
    }

    @FXML
    private void handleShowPicture(MouseEvent event) {
        if (slide_page) {
            locPaginator.setCurrentPageIndex(0);
            // show pictures window
        } else {
            locPaginator.setCurrentPageIndex(1);
        }
        slide_page = ! slide_page;
    }

    private void handleConnectionSelect(ActionEvent event) {
        //Connector select
        System.out.println("Connector select");
        
    }

    @FXML
    private void handleCoverSelect(ActionEvent event) {
        System.out.println("Cover select");
    }

    @FXML
    private void handleCoverEnabled(ActionEvent event) {
        locCover.setDisable(!locCover.isDisable());
        locIsClaim.setDisable(!locIsClaim.isDisable());
        locIsFixed.setDisable(!locIsFixed.isDisable());
    }

    @FXML
    private void handleShowPrOnT(ActionEvent event) {
        this.showProjectPanel();
    }

    @FXML
    private void handleClampingSelect(MouseEvent event) {
        // Claimp select
        if (locClampingView.getSelectionModel().getSelectedItem()!=null) {
            System.out.println("Table con select "+locClampingView.getSelectionModel().getSelectedItem().getArt()+ "  "+ locClampingView.getSelectionModel().getSelectedItem().getId());
            Model.getInstance().updateMetis(locTrayView.getSelectionModel().getSelectedItem(),locClampingView.getSelectionModel().getSelectedItem());
        
        }
        
    }
    
    private void showReportWin() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/emidetail/view/forms/formReport.fxml"));
            /* 
             * if "fx:controller" is not set in fxml
             * fxmlLoader.setController(NewWindowController);
             */
            Scene scene = new Scene(fxmlLoader.load(), 800, 600);
            Stage stage = new Stage();
            stage.setTitle("Формирование отчета");
            
            stage.setScene(scene);
            stage.show();
            
            
        } catch (IOException e) {
            Logger logger = Logger.getLogger(getClass().getName());
            logger.log(Level.SEVERE, "Failed to create new Window.", e);
        }
    }

    @FXML
    private void handleReport(ActionEvent event) {
        try{
        controller.addData(Model.getInstance().reports);
            controller.process(Command.REPORT_S, "1");
        if (controller.getOutput()!=null)
                Desktop.getDesktop().open(new File("object_collection_output.xls")); // /tmp/MyFirstExcel.xlsx
        } catch (IOException ex) {
            Logger.getLogger(formMainController.class.getName()).log(Level.SEVERE, null, ex);
    }
    }

    @FXML
    private void handleSelectTab(Event event) {
        if (locStateEdit!= null) {
            Tab tab = (Tab)event.getSource();
            locStateEdit.setPromptText("Выбрать "+tab.getText());
        }
    }

    @FXML
    private void handleStateTraySel(MouseEvent event) {
        locStateEdit.setPromptText("Выбор лотка");
    }

    @FXML
    private void handleStateConSel(MouseEvent event) {
        locStateEdit.setPromptText("Выбор прекрепления лотка");
    }

    @FXML
    private void handleStateCalcSel(MouseEvent event) {
        locStateEdit.setPromptText("Расчет сечения лотка");
    }

    @FXML
    private void handleSectionStAction(ActionEvent event) {
        System.out.println("Value "+locRoadEdit.getValue());
        System.out.println("step "+locSectionStep.getValue());
    }
    
    @FXML
    private void handleClearTree(ActionEvent event) {
        Alert alert = new Alert(AlertType.CONFIRMATION, "Вы уверены что хотите удалить все элементы в проекте ?", ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
        alert.setHeaderText("Внимание !!!");
        alert.showAndWait();

        if (alert.getResult() == ButtonType.YES) {
            this.clearProjectTree(true);
        }
        
    }

    @FXML
    private void handleChangePrName(ActionEvent event) {
        elementTree.setRoot(locProjectName.getText());
    }
    
    @FXML
    private void handleChangeTree(ActionEvent event) {
        this.showReportWin();
    }
    
    public SectionValue calcSectionValues() {
        SectionValue val = SectionValue.calcSectionValues(locClampingView.getSelectionModel().getSelectedItem(),
                    locCover.getSelectionModel().getSelectedItem(), locDividers.getSelectionModel().getSelectedItem());
        return val;
    }

    
    /*
    
    acs
    
    */
   
    private SearchData trayFilterForAcs;
    
    @FXML
    private void handleAcsTrayTypeAction(ActionEvent event){
        int sel_index = locAcsTrayTyper.getSelectionModel().getSelectedIndex();
        System.out.println("Sel "+sel_index+" - "+locAcsTrayTyper.getValue());

        //trayFilterForAcs = null;
        //trayFilterForAcs = new SearchData();
        //acsDataFilter = null;
        //acsDataFilter = new SearchData();
        /*
        typelist.add("");
        1   typelist.add("Листовой перфорированный");
        2   typelist.add("Листовой неперфорированный");
        3   typelist.add("Листовой перфорированный тяжелой серии");
        4   typelist.add("Листовой неперфорированный тяжелой серии");
        5   typelist.add("Лоток перфорированный для больших расстояний");
        6   typelist.add("Лоток лестничный усиленный");
        7   typelist.add("Кабельрост лестничный усиленный");
        8   typelist.add("Лоток сетчатый");
        9   typelist.add("Канал усиленный световой");
        */

        //List<String> addtraylist = Model.getInstance().addtraylist;
        Model.getInstance().addtraylist.clear();
        Model.getInstance().addtraylist.add("");
        if (sel_index <= 0){
            trayFilterForAcs.addType(0);
            acsDataFilter.addType(0);
            locAcsTrayTyper.getSelectionModel().selectFirst();
        }
        if ((sel_index == 1) 
                || (sel_index == 2) 
                || (sel_index == 3)
                || (sel_index == 4)
                || (sel_index == 5)){
            int tray_index = 0;
            if (sel_index == 1){
                tray_index = 1;
            }            
            if (sel_index == 2){
                tray_index = 2;
            }
            if (sel_index == 3){
                tray_index = 3;
            }
            if (sel_index == 4){
                tray_index = 4;
            }
            if (sel_index == 5){
                tray_index = 5;
            }
            trayFilterForAcs.addType(tray_index);
            acsDataFilter.addType(tray_index);
            acsDataFilter.addToTypes(101);
            acsDataFilter.addToTypes(102);
            acsDataFilter.addToTypes(103);
            acsDataFilter.addToTypes(104);
            acsDataFilter.addToTypes(105);
            acsDataFilter.addToTypes(106);
            acsDataFilter.addToTypes(107);
            acsDataFilter.addToTypes(108);
            acsDataFilter.addToTypes(109);
            acsDataFilter.addToTypes(110);
            acsDataFilter.addToTypes(111);
            acsDataFilter.addToTypes(112);
            acsDataFilter.addToTypes(113);
            acsDataFilter.addToTypes(114);
            acsDataFilter.addToTypes(115);

            /*addtraylist.add("");
            addtraylist.add("горизонтальный поворот трассы 45"); //101
            addtraylist.add("горизонтальный поворот трассы 90"); //102
            addtraylist.add("вертикальный внутренний на 45º"); //103
            addtraylist.add("вертикальный внутренний на 90º"); //104
            addtraylist.add("вертикальный внешний на 45º"); //105
            addtraylist.add("вертикальный внешний на 90º"); //106
            addtraylist.add("вертикальный шарнирный"); //107
            addtraylist.add("тройниковый"); //108, 111, 112, 113, 114
            addtraylist.add("крестообразный"); //109
            addtraylist.add("ответвительный"); //110
            addtraylist.add("горизонтальный изменяемый поворота трассы на 0º-90º"); //115*/
            
            Model.getInstance().addtraylist.add("горизонтальный поворот трассы 45"); //101
            Model.getInstance().addtraylist.add("горизонтальный поворот трассы 90"); //102
            Model.getInstance().addtraylist.add("вертикальный внутренний на 45º"); //103
            Model.getInstance().addtraylist.add("вертикальный внутренний на 90º"); //104
            Model.getInstance().addtraylist.add("вертикальный внешний на 45º"); //105
            Model.getInstance().addtraylist.add("вертикальный внешний на 90º"); //106
            Model.getInstance().addtraylist.add("вертикальный шарнирный"); //107
            Model.getInstance().addtraylist.add("тройниковый"); //108, 111, 112, 113, 114
            Model.getInstance().addtraylist.add("крестообразный"); //109
            Model.getInstance().addtraylist.add("ответвительный"); //110
            Model.getInstance().addtraylist.add("горизонтальный изменяемый поворота трассы на 0º-90º"); //115
        }
        if (sel_index == 6){
            // Лоток лестничный усиленный
            trayFilterForAcs.addType(6);

            acsDataFilter.addType(6);
            acsDataFilter.addToTypes(120);
            acsDataFilter.addToTypes(122);
            acsDataFilter.addToTypes(124);
            acsDataFilter.addToTypes(126);
            acsDataFilter.addToTypes(133);
            acsDataFilter.addToTypes(135);
            acsDataFilter.addToTypes(137);
            acsDataFilter.addToTypes(140);
            acsDataFilter.addToTypes(141);
            acsDataFilter.addToTypes(142);
            acsDataFilter.addToTypes(144);
            acsDataFilter.addToTypes(148);
            acsDataFilter.addToTypes(152);
            acsDataFilter.addToTypes(156);
            acsDataFilter.addToTypes(157);
            acsDataFilter.addToTypes(158);
            acsDataFilter.addToTypes(159);
            acsDataFilter.addToTypes(160);
            
            Model.getInstance().addtraylist.add("горизонтальный поворот трассы 30º"); // 120
            Model.getInstance().addtraylist.add("горизонтальный поворот трассы 45º"); // 122
            Model.getInstance().addtraylist.add("горизонтальный поворот трассы 60º"); // 124
            Model.getInstance().addtraylist.add("горизонтальный поворот трассы 90º"); // 126
            Model.getInstance().addtraylist.add("вертикальный внешний поворот трассы 90º"); // 128, 148
            Model.getInstance().addtraylist.add("вертикальный внутренний поворот трассы 90º"); // 130, 152
            Model.getInstance().addtraylist.add("тройниковый"); // 133
            Model.getInstance().addtraylist.add("крестообразный"); //135
            Model.getInstance().addtraylist.add("вертикальный шарнирный"); //156, 157
            Model.getInstance().addtraylist.add("редукция левая"); //140
            Model.getInstance().addtraylist.add("редукция правая"); //141, 159
            Model.getInstance().addtraylist.add("редукция центральная"); //142, 158, 160
            /*
            addtraylist.add("");
            addtraylist.add("горизонтальный поворот трассы 30º"); // 120
            addtraylist.add("горизонтальный поворот трассы 45º"); // 122
            addtraylist.add("горизонтальный поворот трассы 60º"); // 124
            addtraylist.add("горизонтальный поворот трассы 90º"); // 126
            addtraylist.add("вертикальный внешний поворот трассы 90º"); // 128, 148
            addtraylist.add("вертикальный внутренний поворот трассы 90º"); // 130, 152
            addtraylist.add("тройниковый"); // 133
            addtraylist.add("крестообразный"); //135
            addtraylist.add("вертикальный шарнирный"); //156, 157
            addtraylist.add("редукция левая"); //140
            addtraylist.add("редукция правая"); //141, 159
            addtraylist.add("редукция центральная"); //142, 158, 160*/
        }
        if (sel_index == 7){
            // Кабельрост лестничный усиленный
            trayFilterForAcs.addType(7);
            acsDataFilter.addType(7);
            Model.getInstance().addtraylist.add("горизонтальный поворот трассы 30º"); // 121 
            Model.getInstance().addtraylist.add("горизонтальный поворот трассы 45º"); // 123
            Model.getInstance().addtraylist.add("горизонтальный поворот трассы 60º"); // 125, 138
            Model.getInstance().addtraylist.add("горизонтальный поворот трассы 90º"); // 127, 145
            Model.getInstance().addtraylist.add("вертикальный внешний поворот трассы 90º"); // 129, 149
            Model.getInstance().addtraylist.add("вертикальный внутренний поворот трассы 90º"); // 131, 153
            Model.getInstance().addtraylist.add("тройниковый"); // 134
            Model.getInstance().addtraylist.add("крестообразный"); // 136 
            
            /*
            addtraylist.add("");
            addtraylist.add("горизонтальный поворот трассы 30º"); // 121 
            addtraylist.add("горизонтальный поворот трассы 45º"); // 123
            addtraylist.add("горизонтальный поворот трассы 60º"); // 125, 138
            addtraylist.add("горизонтальный поворот трассы 90º"); // 127, 145
            addtraylist.add("вертикальный внешний поворот трассы 90º"); // 129, 149
            addtraylist.add("вертикальный внутренний поворот трассы 90º"); // 131, 153
            addtraylist.add("тройниковый"); // 134
            addtraylist.add("крестообразный"); // 136 */
        }
        if (sel_index == 8){
            //Лоток сетчатый
            acsDataFilter.addType(8);
            trayFilterForAcs.addType(8);
        }
        if (sel_index == 9){
            //Канал усиленный световой
            acsDataFilter.addType(9);
            trayFilterForAcs.addType(9);
        }
        Model.getInstance().addtray_types = FXCollections.observableArrayList(Model.getInstance().addtraylist);
        locAcsType.getSelectionModel().clearSelection();
        locAcsType.setItems(Model.getInstance().addtray_types);
        
        applyAcsFilters();
    }
    
    private List<AdditionTrays> createTrayListForAcs(){
        List<Trays> trayListForAcs = filtered(trayFilterForAcs);
        if ((trayListForAcs == null)){
            System.out.println("Отфильтрованный список треев == null");
            return null;
        }
        if (trayListForAcs.isEmpty()){
            System.out.println("Отфильтрованный список треев пустой");
            return null;            
        }
        System.out.println("размер отфильтрованного списка треев = " + trayListForAcs.size());
        return convertTraysListToAddTraysList(trayListForAcs);
    }
    
    private List<Trays> filtered(SearchData data) {
        return TraysFunctions.selectTraysByFilter(data);
    }
    
    private List<String> convertTraysListToStringList(ObservableList<Trays> listIn){
        List<String> result = new ArrayList<String>();
        if ((listIn != null) && (!listIn.isEmpty())){
            for(Trays t : listIn){
                result.add(t.getDescription());
            }
        }
        return result;
    }
        
    private List<AdditionTrays> convertTraysListToAddTraysList(List<Trays> listIn){
        if (listIn != null){
            System.out.println("вызываю конвертер лотков в аксессуары " + listIn.size());            
        } else {
            System.out.println("в конвертер попал неинециализированный список треев");
        }
        List<AdditionTrays> result = new ArrayList<AdditionTrays>();
        if ((listIn != null) && (!listIn.isEmpty())){
            for(Trays t : listIn){
    /*          public AdditionTrays(
                int id, 
                String articul, 
                String definition, 
                String inner, 
                String thickness, 
                Integer lenth, 
                Integer width1, 
                Integer width2, 
                Integer height, 
                String mass, 
                String radius, 
                Integer constructionId, 
                String mark, 
                Integer type, 
                Integer coverTypeId, 
                Integer countConnection) {
    */
                result.add(new AdditionTrays(
                        t.getId(), 
                        t.getArt(),
                        t.getTitleDisplay(),
                        "!!" + t.getDescription(),
                        t.getThickness(),
                        t.getLength(),
                        t.getWidth(),
                        t.getWidth(),
                        t.getHeight(),
                        t.getMass(),
                        null,
                        t.getConstructionId(),
                        null,
                        t.getType(),
                        t.getCoverTypeId(),
                        0));
            }
        }
        System.out.println("наконвертировал " + result.size() + " лоткоАксессуаров");
        return result;
    }
    
    @FXML
    private void handleAcsTypeAction(ActionEvent event) {
        System.out.println("Поймал событие handleAcsTypeAction");
        int sel_tray_index = locAcsTrayTyper.getSelectionModel().getSelectedIndex();
        int sel_acs_index = locAcsType.getSelectionModel().getSelectedIndex();
        System.out.println("Sel "+sel_acs_index+" - "+locAcsType.getValue());
        
        if (sel_acs_index <= 0){
            acsDataFilter.addType(0);
        }        
        acsDataFilter.clearTypes();
        int tray_index = 0;
            if (sel_tray_index == 1){
                tray_index = 1;
            }            
            if (sel_tray_index == 2){
                tray_index = 2;
            }
            if (sel_tray_index == 3){
                tray_index = 3;
            }
            if (sel_tray_index == 4){
                tray_index = 4;
            }
            if (sel_tray_index == 5){
                tray_index = 5;
            }
            acsDataFilter.addType(tray_index);
        if ((sel_tray_index == 1) 
                || (sel_tray_index == 2) 
                || (sel_tray_index == 3)
                || (sel_tray_index == 4)
                || (sel_tray_index == 5)){
            

            if (sel_acs_index == 1){
                acsDataFilter.addToTypes(101);
            }
            if (sel_acs_index == 2){
                acsDataFilter.addToTypes(102);
            }
            if (sel_acs_index == 3){
                acsDataFilter.addToTypes(103);
            }
            if (sel_acs_index == 4){
                acsDataFilter.addToTypes(104);
            }
            if (sel_acs_index == 5){
                acsDataFilter.addToTypes(105);
            }
            if (sel_acs_index == 6){
                acsDataFilter.addToTypes(106);
            }
            if (sel_acs_index == 7){
                acsDataFilter.addToTypes(107);
            }
            if (sel_acs_index == 8){
                acsDataFilter.addToTypes(108);
                acsDataFilter.addToTypes(111);
                acsDataFilter.addToTypes(112);
                acsDataFilter.addToTypes(113);
                acsDataFilter.addToTypes(114);
            }
            if (sel_acs_index == 9){
                acsDataFilter.addToTypes(109);
            }
            if (sel_acs_index == 10){
                acsDataFilter.addToTypes(110);
            }
            if (sel_acs_index == 11){
                acsDataFilter.addToTypes(115);
            }
        }
        if (sel_tray_index == 6){
            acsDataFilter.addType(6);
            if (sel_acs_index == 1){
                acsDataFilter.addToTypes(120);
            }
            if (sel_acs_index == 2){
                acsDataFilter.addToTypes(122);
            }
            if (sel_acs_index == 3){
                acsDataFilter.addToTypes(124);
            }
            if (sel_acs_index == 4){
                acsDataFilter.addToTypes(126);
            }
            if (sel_acs_index == 5){
                acsDataFilter.addToTypes(128);
                acsDataFilter.addToTypes(148);
            }
            if (sel_acs_index == 6){
                acsDataFilter.addToTypes(130);
                acsDataFilter.addToTypes(152);
            }
            if (sel_acs_index == 7){
                acsDataFilter.addToTypes(133);
            }
            if (sel_acs_index == 8){
                acsDataFilter.addToTypes(135);
            }
            if (sel_acs_index == 9){
                acsDataFilter.addToTypes(156);
                acsDataFilter.addToTypes(157);
            }
            if (sel_acs_index == 10){
                acsDataFilter.addToTypes(140);
            }
            if (sel_acs_index == 11){
                acsDataFilter.addToTypes(141);
                acsDataFilter.addToTypes(159);
            }
            if (sel_acs_index == 12){
                acsDataFilter.addToTypes(142);
                acsDataFilter.addToTypes(158);
                acsDataFilter.addToTypes(160);
            }
        }
        if (sel_tray_index == 7){
            // Кабельрост лестничный усиленный
            acsDataFilter.addType(7);
            if (sel_acs_index == 1){
                acsDataFilter.addToTypes(121);
            }
            if (sel_acs_index == 2){
                acsDataFilter.addToTypes(123);
            }
            if (sel_acs_index == 3){
                acsDataFilter.addToTypes(125);
                acsDataFilter.addToTypes(138);
            }
            if (sel_acs_index == 4){
                acsDataFilter.addToTypes(127);
                acsDataFilter.addToTypes(145);
            }
            if (sel_acs_index == 5){
                acsDataFilter.addToTypes(129);
                acsDataFilter.addToTypes(149);
            }
            if (sel_acs_index == 6){
                acsDataFilter.addToTypes(131);
                acsDataFilter.addToTypes(153);
            }
            if (sel_acs_index == 7){
                acsDataFilter.addToTypes(134);
            }
            if (sel_acs_index == 8){
                acsDataFilter.addToTypes(136);
            }
        }
        
        

        applyAcsFilters();
    }

    private boolean needTrays(){
        //return true;
        
        if (locAcsType.getSelectionModel().getSelectedIndex() <= 0){
            return true;
        } else {
            return false;
        }        
    }
    
    private void applyAcsFilters(){
        if (needTrays()){
            Model.getInstance().filteredAcs(acsDataFilter, false, createTrayListForAcs());
        } else {
            Model.getInstance().filteredAcs(acsDataFilter, false, null);            
        }
    }
    
    @FXML
    private void handleAcsSourceAction(ActionEvent event) {
        int sel_index = locAcsSource.getSelectionModel().getSelectedIndex();
        System.out.println("Sel "+sel_index+" - "+locAcsSource.getValue());
        int source_index = 0;
        if (sel_index == 0){
            acsDataFilter.clearSource();
            trayFilterForAcs.clearSource();
        } else if ((sel_index > 0) && (sel_index < 6)) {
            source_index = sel_index;
        } else {
            source_index = 6;
        }
        acsDataFilter.addSource(source_index);
        trayFilterForAcs.addSource(source_index);
        applyAcsFilters();
    }
    
    @FXML
    private void handleAcsWidthAction(ActionEvent event) {
        System.out.println("Get width "+locAcsWidth.getValue());
        if (!locAcsWidth.getValue().equalsIgnoreCase("")) {
            int takeWidth = Integer.valueOf(locAcsWidth.getValue());
            // System.out.println("Get width "+takeWidth);
            acsDataFilter.setWidth(takeWidth);
            trayFilterForAcs.setWidth(takeWidth);
        } else {
            acsDataFilter.setWidth(0);
            trayFilterForAcs.setWidth(0);
        }
        applyAcsFilters();
    }
    
    @FXML
    private void handleAcsHeightAction(ActionEvent event) {
        System.out.println("Get height "+ locAcsHeight.getValue());
        if (!locAcsHeight.getValue().equalsIgnoreCase("")){
            acsDataFilter.setHeight(Integer.valueOf(locAcsHeight.getValue()));
            trayFilterForAcs.setHeight(Integer.valueOf(locAcsHeight.getValue()));
        } else {
            acsDataFilter.setHeight(0);
            trayFilterForAcs.setHeight(0);
        }
        applyAcsFilters();
    }

    @FXML
    private void handleThsAcsAction(ActionEvent event) {
        System.out.println("Get ths "+locThsAcs.getValue());
        if (!locThsAcs.getValue().equalsIgnoreCase("")) {
            acsDataFilter.setThs(locThsAcs.getValue());
            trayFilterForAcs.setThs(locThsAcs.getValue());
        } else {
            acsDataFilter.setThs("0");
            trayFilterForAcs.setThs("0");
        }
        applyAcsFilters();
    }

    @FXML
    private void handleAcsFilterClear(ActionEvent event) {
        acsDataFilter = null;
        acsDataFilter = new SearchData();
        trayFilterForAcs = null;
        trayFilterForAcs = new SearchData();
        
        // trayType
        locAcsTrayTyper.getSelectionModel().clearSelection();        
                
        // acsType
        locAcsType.getSelectionModel().clearSelection();
        
        // source
        locAcsSource.getSelectionModel().clearSelection();
                
        // width
        locAcsWidth.getSelectionModel().clearSelection();
                
        // height
        locAcsHeight.getSelectionModel().clearSelection();
        
        // ths
        locThsAcs.getSelectionModel().clearSelection();
            
        //
        //List<AdditionTrays> additions = AdditionTraysFunctions.selectAddTrays();
        //Model.getInstance().addTlist = FXCollections.observableArrayList(additions);
        //Model.getInstance().addFlist = FXCollections.observableArrayList(additions);
        applyAcsFilters();
    }

    
    
}
