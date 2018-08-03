/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package emidetail.view.elements;


import emidetail.database.beans.Report;
import javafx.fxml.FXML;
import javafx.scene.control.*;


/**
 *
 * @author DVAmod
 */
public class ElemTreeView {
    
    public ElemTreeView(TreeTableView<Report> locTree){
        locationTreeView = locTree;
    }
            
  // 
  private TreeTableView<Report> locationTreeView;
 
  private Report rootNode;
  
  // Load tree
  public void initialize() {
    //loadTreeItems("initial 1", "initial 2", "initial 3");
    rootNode = new Report("New project", 0);
    locationTreeView.setShowRoot(true);
     TreeItem<Report> root = new TreeItem<>(rootNode);
     root.setExpanded(true);
     locationTreeView.setRoot(root);
  } 
  
  public void setRoot(String root){
      rootNode = new Report(root,0);
      TreeItem<Report> rootR = locationTreeView.getRoot();
      rootR.setValue(rootNode);
      rootR.setExpanded(true);
      locationTreeView.setRoot(rootR);
  }
  
  public int addTreeItems(Report... items) {
     TreeItem<Report> root =  locationTreeView.getRoot();
     TreeItem<Report> ti = null;
     for (Report item: items) {
         if (item!=null){
            ti =  new TreeItem<>(item);
            ti.setExpanded(true);
            root.getChildren().add(ti);
         }
    }
     if (ti!=null)
        return root.getChildren().lastIndexOf(ti);
     return 1;
  }
  
  public int addTreeLeaf(int index,Report items) {
      TreeItem<Report> leaf = new TreeItem<>(items);
      leaf.setExpanded(true);
      locationTreeView.getRoot().getChildren().get(index).getChildren().add(leaf);
      return locationTreeView.getRoot().getChildren().lastIndexOf(leaf);
  }
  
  public int addTreeDoubleLeaf(int index,Report items,Report lastItems) {
      TreeItem<Report> leaf = new TreeItem<>(items);
      leaf.setExpanded(true);
      TreeItem<Report> lastleaf = new TreeItem<>(lastItems);
      leaf.setExpanded(true);
      locationTreeView.getRoot().getChildren().get(index).getChildren().add(leaf);
      locationTreeView.getRoot().getChildren().get(index).getChildren().get(
        locationTreeView.getRoot().getChildren().get(index).getChildren().indexOf(leaf)
              
      ).getChildren().add(lastleaf);
      return locationTreeView.getRoot().getChildren().lastIndexOf(leaf);
  }
  
  public void addLastLeaf(Report item) {
      TreeItem<Report> root = locationTreeView.getRoot();
      TreeItem<Report> last = root.getChildren().get(root.getChildren().size()-1);
      last.getChildren().add(new TreeItem<Report>(item));
  }

  // loads some strings into the tree in the application UI.
  public int loadTreeItems(Report... rootItems) {
    TreeItem<Report> root = new TreeItem<>(rootNode);
    root.setExpanded(true);
    TreeItem<Report> ti = null;
    for (Report itemString: rootItems) {
      ti =  new TreeItem<>(itemString);
      ti.setExpanded(true);
      root.getChildren().add(ti);
    }

    locationTreeView.setRoot(root);
    return root.getChildren().lastIndexOf(ti);
  }
}
