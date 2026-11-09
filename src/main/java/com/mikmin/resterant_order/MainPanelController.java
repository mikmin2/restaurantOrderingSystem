/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mikmin.resterant_order;

import java.net.URL;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
/**
 * FXML Controller class
 *
 * @author 2556963
 */
public class MainPanelController implements Initializable {


    @FXML
    private MenuItem placeOrderMenu;
    @FXML
    private MenuItem clearOrderMenu;
    @FXML
    private MenuItem exitMenu;
    @FXML
    private MenuItem entreeMenu;
    @FXML
    private MenuItem drinkMenu;
    @FXML
    private MenuItem requestMenu;
    @FXML
    private MenuItem aboutMenu;
    @FXML
    private ListView<String> entreeListView;
    @FXML
    private RadioButton radioCoffee;
    @FXML
    private RadioButton radioTea;
    @FXML
    private RadioButton radioSoda;
    @FXML
    private RadioButton radioJuice;
    @FXML
    private CheckBox waterCheckBox;
    @FXML
    private TextField requestTextField;
    @FXML
    private Label totalLabel;
    @FXML
    private Label orderEntreeLabel;
    @FXML
    private Label orderDrinkLabel;
    @FXML
    private Button placeButton;
    @FXML
    private Button clearButton;
    @FXML
    private ToggleGroup tg = new ToggleGroup();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tg.getToggles().addAll(radioCoffee, radioTea, radioSoda, radioJuice);
        Order order = new Order();
        
        entreeListView.getItems().addAll("Chicken Salad", "Turkey Sandwich", "Vegetable Wrap", "Grilled Cheese");
        Map<String,Order.Entree> entreeMap = new HashMap();
        entreeMap.put("Chicken Salad", Order.Entree.CHICKEN_SALAD);
        entreeMap.put("Turkey Sandwich", Order.Entree.TURKEY_SANDWICH);
        entreeMap.put("Vegetable Wrap", Order.Entree.VEGETABLE_WRAP);
        entreeMap.put("Grilled Cheese", Order.Entree.GRILLED_CHEESE);
        
        entreeListView.getSelectionModel().selectedItemProperty().addListener(event -> {
            order.setEntree(entreeMap.get(entreeListView.getSelectionModel().getSelectedItem()));
            totalLabel.setText(String.format("Total: $%.2f", order.getTotalPrice()));
            orderEntreeLabel.setText(entreeListView.getSelectionModel().getSelectedItem());
        });
        
        radioCoffee.setUserData(Order.Drink.COFFEE);
        radioTea.setUserData(Order.Drink.TEA);
        radioSoda.setUserData(Order.Drink.SODA);
        radioJuice.setUserData(Order.Drink.JUICE);        
        
        tg.selectedToggleProperty().addListener((ov, oldToggle, newToggle) -> {
            if (newToggle != null) {
                order.setDrink((Order.Drink)newToggle.getUserData());
                totalLabel.setText(String.format("Total: $%.2f", order.getTotalPrice()));
                RadioButton temp = (RadioButton)newToggle;
                orderDrinkLabel.setText(temp.getText());
            }
        });
        
        waterCheckBox.setOnAction(e -> {
            order.setWaterSelection(waterCheckBox.isSelected());
        });
        
        requestTextField.setOnAction(e -> {
            order.setSpecialRequest(requestTextField.getText());
        });
    }    
   
    
    
    
}
