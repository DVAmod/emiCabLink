/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package emidetail.view.elements;

import emidetail.manager.controls.CablingCalc;
import emidetail.manager.data.Gabarit;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author DVAmod
 */
public class ConstructCablingView {
    
    private static CablingCalc cabcalc;
    private static Pane canvas;
    private static int type = 0;
    private static float input_diam = 1f;
    private static int count_cab = 1;
    
    private static int startX = 250;
    private static int startY = 25;
    
    private static int startOldX = 25;
    private static int startOldY = 25;
    
    public static void input(Pane cabpane, CablingCalc calc) {
        canvas = cabpane;
        cabcalc = calc;
    }
    
    public static void start() {
        startX = 250;
        startY = 25;
    }
    
    public static void addData(float diam, int count, int ttt){
        type = ttt;
        input_diam = diam;
        count_cab = count;
        cabcalc.addCalcData(diam, count, type);
    }
    
    public static void addData(Gabarit gab){
        type = gab.getL();
        input_diam = gab.getH();
        count_cab = (int)gab.getW();
    }
    
    public static void showAllResult( int index ) {
        
        
            start();
        
        boolean calc_res = cabcalc.calc(new Gabarit(input_diam, count_cab, type), index+7);
        
        if (calc_res) {
            
            float th = cabcalc.trayHeight;
            float tw = cabcalc.trayWidth;
            
            
            System.out.println("res ="+calc_res+"  th="+th+"   tw="+tw);

            float ph = 200f;
            float pw = 400f;

            double kof1 = (ph-5)/th;
            double kof2 = (pw-10)/tw;
            double endH = th*kof1;
            double endW = tw*kof1;
            
            int kof_index = 0;
            if (endW > pw) {
                endH = th*kof2;
                endW = tw*kof2;
                kof_index = 2;
            } else {
                kof_index = 1;
            }

                Rectangle rect1 = new Rectangle(startX, startY-2, endW, endH);
                rect1.setFill(Color.WHITESMOKE);
                rect1.setId("rectangle");

                //Transparent rectangle with Stroke
                Rectangle rect2 = new Rectangle(startX, startY, endW, endH);
                rect2.setFill(Color.TRANSPARENT);
                rect2.setStroke(Color.BLACK);
                rect2.setStrokeWidth(5);
                rect2.setId("rectangle");
                
                double endTrayW = cabcalc.width;
                double endTrayH = cabcalc.height;
                
                if (kof_index==1) {
                    endTrayW =  (endTrayW*kof1);
                    endTrayH =  (endTrayH*kof1);
                }
                if (kof_index==2) {
                    endTrayW =  (endTrayW*kof2);
                    endTrayH =  (endTrayH*kof2);
                }
                
                Rectangle rect4 = new Rectangle(startX, (startY+endH-endTrayH/1.32), endTrayW/1.08, endTrayH/1.32);
                    rect4.setFill(Color.GREY);
                    rect4.setStroke(Color.BLACK);
                    rect4.setOpacity(.3);
                    rect4.setStrokeWidth(3);
                    rect4.setId("rectangle");

                canvas.getChildren().addAll(rect2,rect1);
            
            for (int k = 0; k < cabcalc.getDataIndex(); k++) {
                
                double rad = Math.round((cabcalc.getDiametr(k))/2);
                if (kof_index==1) {
                    rad = rad*kof1;
                }
                if (kof_index==2) {
                    rad = rad*kof2;
                }
                if (k!=0) {
                    if (type==1 || type==2) {
                        startX = (int) (startX + 0.2 * Math.round((cabcalc.getDiametr(k-1))/2));
                        if (type==2) {
                            startX = (int) (startX +  1.7 * Math.round((cabcalc.getDiametr(k-1))/2));
                        }
                    }
                    if (type==3 ) {
                        //startY = startY + 2*Math.round((cabcalc.getDiametr(k-1))/2);
                        if (k==1) {
                            startX = (int) (startX - 0.8 * Math.round((cabcalc.getDiametr(k-1))));
                        } else {
                            startX = (int) (startX - 1.9 * Math.round((cabcalc.getDiametr(k-1))));
                        }
                    }
                    if (type==4 ) {
                        startX = startX + 2;
                    }
                    
                }
                
                type = cabcalc.getType(k);
                if (k==1){
                    startX = startX - 2; 
                }
                
                if (k==0){
                    startOldX = new Integer( startX);
                    startX = (int) (startX + 2 + rad);    
                    startY = (int) (startY + (float)endH - 2.5f - rad);
                    
                    
                } else {
                    
                    startY = (int) (startOldY + (float)endH - 2.5f - rad);
                    if (kof_index==1) {
                        startX = (int) (startX +   rad + (cabcalc.getWidth(k-1)*kof1));
                    }
                    if (kof_index==2) {
                        startX = (int) (startX +   rad +  (cabcalc.getWidth(k-1)*kof2));
                    }
                    if (type==1 || type==2) {
                        startX = (int) (startX + 2);
                    }
                    if (type==4) {
                        startX = (int) (startX - 0.3* rad );
                    }
                    if (type==3) {
                        startX = (int) (startX - 0.5* rad );
                    }
                }
                
                
                count_cab = cabcalc.getCount(k);
                
                cabcalc.calcMethod(type, k);

                if (type==1 || type==2) {
                    if ( type == 2 ) {
                        count_cab = count_cab*2;
                    }
                    Rectangle rect3 = new Rectangle(startX-rad-1, startY-rad+1, rad*count_cab*2+3, 2*rad+3);
                    rect3.setFill(Color.TRANSPARENT);
                    //rect3.setOpacity(.7);
                    rect3.setId("rectangle");
                    canvas.getChildren().add(rect3);

                    for (int i = 0; i < count_cab; i++) {
                        //Rectangle with Stroke, no Fill color specified
                        Circle ci3 = null;
                        if ( type == 2 ) {
                            if (i%2==0)
                                ci3 = new Circle(startX+ rad*i*2, startY, rad);
                        } else {
                            ci3 = new Circle(startX+ rad*i*2, startY, rad);
                        }
                        if (ci3!=null) {
                            ci3.setFill(Color.CADETBLUE);
                            ci3.setStroke(Color.BLACK);
                            ci3.setStrokeWidth(2);
                            //ci3.setOpacity(.6);
                            ci3.setId("circle");
                            canvas.getChildren().add(ci3);
                        }
                    }

                }
                if (type==3 || type==4) {
                    int level = cabcalc.getLevel();
                    int count_c = (int) Math.round(Math.ceil((double)count_cab/cabcalc.getLevel()));
                    int old_startX = startX;

                    if (type==4) {
                        startY = startY+6;
                        startX = startX-2;
                        rad = rad*0.92;
                    } else {
                        rad = rad*0.95;
                    }

                    Rectangle rect3 = null;
                    if (type==3)
                        rect3 = new Rectangle(startX-rad-1, startY-(2*rad*level+1-rad), 2*rad*count_c+2, 2*rad*level+3);
                    else if (type==4) {
                        //rect3 = new Rectangle(startX-rad-1, startY-(2*rad*level+1.15*rad)+2, 2*rad*count_c+0.5*rad+2, 2*rad*level+2*rad-2);
                        //int count_c2 = (int)Math.floor(count_cab/2);
                        Ellipse ca = new Ellipse(startX+rad*count_c-0.8*rad, startY-(rad*level)+2,rad*count_c+0.4*rad ,rad*level+0.5*rad);
                        ca.setFill(Color.TRANSPARENT);
                        ca.setStroke(Color.BLACK);
                        ca.setStrokeWidth(2);
                        //ca.setOpacity(0.6);
                        ca.setId("circle");
                        canvas.getChildren().add(ca);
                        //System.out.println("c="+count_cab+"  cr="+Math.ceil((double)count_cab/2));
                    }
                    if (rect3!=null && type==3) {    
                        rect3.setFill(Color.TRANSPARENT);
                        //rect3.setOpacity(.5);
                        rect3.setId("rectangle");
                        canvas.getChildren().add(rect3);
                    }
                    for (int j = 0; j < level; j++) {
                        if ( j > 0) {
                            startY = (int) (startY - 2*rad );
                        } else if (type==4) {
                            startX = (int) (startX + 0.3*rad);
                            startY = (int) (startY - 0.8*rad);
                        }
                        if (type==4) {

                        }
                        for (int i = 0; i < count_c; i++) {
                            //Rectangle with Stroke, no Fill color specified
                            Circle ci3 = null;
                            if ( type == 4 ) {
                                if (j==0 || j==level-1) {
                                    if (count_c < 6) {
                                        if (i==0) continue;
                                        if (i==count_c-1) continue;
                                    } else {
                                        if (i==0 || i==1) continue;
                                        if (i==count_c-1 || i==count_c-2) continue;
                                    }
                                }


                                ci3 = new Circle(startX+ rad*i*2, startY+1, rad);
                            } else {
                                ci3 = new Circle(startX+ rad*i*2, startY+1, rad);
                            }
                            if (ci3!=null) {
                                ci3.setFill(Color.CADETBLUE);
                                ci3.setStroke(Color.BLACK);
                                ci3.setStrokeWidth(2);
                                //ci3.setOpacity(.4);
                                ci3.setId("circle");
                                canvas.getChildren().add(ci3);
                            }
                        }
                    }
                }
                
                
         
            }
        }
        
    }
    
    public static void showResult(int change_cab) {
        
        // Start calculate
        if (change_cab > 0 && type>0) {
           
           
        boolean result_cabcalc =
           cabcalc.calc(new Gabarit(input_diam, count_cab, type), change_cab);


            float th = cabcalc.trayHeight;
            float tw = cabcalc.trayWidth;
            
            
            System.out.println("res ="+result_cabcalc+"  th="+th+"   tw="+tw);

            float ph = 200f;
            float pw = 400f;

            double kof1 = (ph-5)/th;
            double kof2 = (pw-10)/tw;
            double endH = th*kof1;
            double endW = tw*kof1;
            double rad = Math.round((input_diam)/2);
            if (endW > pw) {
                endH = th*kof2;
                endW = tw*kof2;
                rad = rad*kof2;
            } else {
                rad = rad*kof1;
            }

                Rectangle rect1 = new Rectangle(startX, startY-2, endW, endH);
                rect1.setFill(Color.WHITESMOKE);
                rect1.setId("rectangle");

                //Transparent rectangle with Stroke
                Rectangle rect2 = new Rectangle(startX, startY, endW, endH);
                rect2.setFill(Color.TRANSPARENT);
                rect2.setStroke(Color.BLACK);
                rect2.setStrokeWidth(4);
                rect2.setId("rectangle");

                canvas.getChildren().addAll(rect2,rect1);



            startY = (int) (startY + (float)endH - 2.5f - rad);
            startX = (int) (startX + 2 + rad);    


            if (type==1 || type==2) {
                if ( type == 2 ) {
                    count_cab = count_cab*2;
                }
                Rectangle rect3 = new Rectangle(startX-rad-1, startY-rad+1, rad*count_cab*2+3, 2*rad+3);
                rect3.setFill(Color.TRANSPARENT);
                //rect3.setOpacity(.7);
                rect3.setId("rectangle");
                canvas.getChildren().add(rect3);

                for (int i = 0; i < count_cab; i++) {
                    //Rectangle with Stroke, no Fill color specified
                    Circle ci3 = null;
                    if ( type == 2 ) {
                        if (i%2==0)
                            ci3 = new Circle(startX+ rad*i*2, startY, rad);
                    } else {
                        ci3 = new Circle(startX+ rad*i*2, startY, rad);
                    }
                    if (ci3!=null) {
                        ci3.setFill(Color.CADETBLUE);
                        ci3.setStroke(Color.BLACK);
                        ci3.setStrokeWidth(2);
                        //ci3.setOpacity(.6);
                        ci3.setId("circle");
                        canvas.getChildren().add(ci3);
                    }
                }

            }
            if (type==3 || type==4) {
                int level = cabcalc.getLevel();
                int count_c = (int) Math.round(Math.ceil((double)count_cab/cabcalc.getLevel()));
                int old_startX = startX;
                
                if (type==4) {
                    startY = startY+6;
                    startX = startX-2;
                    rad = rad*0.92;
                } else {
                    rad = rad*0.95;
                }

                Rectangle rect3 = null;
                if (type==3)
                    rect3 = new Rectangle(startX-rad-1, startY-(2*rad*level+1-rad), 2*rad*count_c+2, 2*rad*level+3);
                else if (type==4) {
                    //rect3 = new Rectangle(startX-rad-1, startY-(2*rad*level+1.15*rad)+2, 2*rad*count_c+0.5*rad+2, 2*rad*level+2*rad-2);
                    //int count_c2 = (int)Math.floor(count_cab/2);
                    Ellipse ca = new Ellipse(startX+rad*count_c-0.8*rad, startY-(rad*level)+2,rad*count_c+0.4*rad ,rad*level+0.5*rad);
                    ca.setFill(Color.TRANSPARENT);
                    ca.setStroke(Color.BLACK);
                    ca.setStrokeWidth(3);
                    //ca.setOpacity(0.6);
                    ca.setId("circle");
                    canvas.getChildren().add(ca);
                    //System.out.println("c="+count_cab+"  cr="+Math.ceil((double)count_cab/2));
                }
                if (rect3!=null && type==3) {    
                    rect3.setFill(Color.TRANSPARENT);
                    //rect3.setOpacity(.5);
                    rect3.setId("rectangle");
                    canvas.getChildren().add(rect3);
                }
                for (int j = 0; j < level; j++) {
                    if ( j > 0) {
                        startY = (int) (startY - 2*rad );
                    } else if (type==4) {
                        startX = (int) (startX + 0.3*rad);
                        startY = (int) (startY - 0.8*rad);
                    }
                    if (type==4) {

                    }
                    for (int i = 0; i < count_c; i++) {
                        //Rectangle with Stroke, no Fill color specified
                        Circle ci3 = null;
                        if ( type == 4 ) {
                            if (j==0 || j==level-1) {
                                if (count_c < 6) {
                                    if (i==0) continue;
                                    if (i==count_c-1) continue;
                                } else {
                                    if (i==0 || i==1) continue;
                                    if (i==count_c-1 || i==count_c-2) continue;
                                }
                            }
                            

                            ci3 = new Circle(startX+ rad*i*2, startY+1, rad);
                        } else {
                            ci3 = new Circle(startX+ rad*i*2, startY+1, rad);
                        }
                        if (ci3!=null) {
                            ci3.setFill(Color.CADETBLUE);
                            ci3.setStroke(Color.BLACK);
                            ci3.setStrokeWidth(2);
                            //ci3.setOpacity(.4);
                            ci3.setId("circle");
                            canvas.getChildren().add(ci3);
                        }
                    }
                }
            }
        }
    }
}
