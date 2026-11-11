/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mikmin.resterant_order;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author 2556963
 */
public class MainPanelController implements Initializable {
    public Order order = new Order();

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

        
        entreeListView.getItems().addAll("Chicken Salad", "Turkey Sandwich", "Vegetable Wrap", "Grilled Cheese");
        Map<String,Order.Entree> entreeMap = new HashMap();
        entreeMap.put("Chicken Salad", Order.Entree.CHICKEN_SALAD);
        entreeMap.put("Turkey Sandwich", Order.Entree.TURKEY_SANDWICH);
        entreeMap.put("Vegetable Wrap", Order.Entree.VEGETABLE_WRAP);
        entreeMap.put("Grilled Cheese", Order.Entree.GRILLED_CHEESE);

        placeButton.setDisable(true);
        placeOrderMenu.setDisable(true);
        
        entreeListView.getSelectionModel().selectedItemProperty().addListener(event -> {
            order.setEntree(entreeMap.get(entreeListView.getSelectionModel().getSelectedItem()));
            totalLabel.setText(String.format("Total: $%.2f", order.getTotalPrice()));
            orderEntreeLabel.setText(entreeListView.getSelectionModel().getSelectedItem());

            if (order.getDrink() != Order.Drink.NONE && order.getEntree() != Order.Entree.NONE) {
                placeButton.setDisable(false);
                placeOrderMenu.setDisable(false);
            }
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

                if (order.getDrink() != Order.Drink.NONE && order.getEntree() != Order.Entree.NONE) {
                    placeButton.setDisable(false);
                    placeOrderMenu.setDisable(false);
                }
            }
        });
        
        waterCheckBox.setOnAction(e -> {
            order.setWaterSelection(waterCheckBox.isSelected());
        });
        
        requestTextField.setOnKeyTyped(e -> {
            order.setSpecialRequest(requestTextField.getText());
        });

        clearButton.setOnAction(e -> {
            radioCoffee.setSelected(false);
            radioJuice.setSelected(false);
            radioSoda.setSelected(false);
            radioTea.setSelected(false);

            entreeListView.getSelectionModel().clearSelection();

            waterCheckBox.setSelected(false);

            requestTextField.clear();

            placeButton.setDisable(true);
            placeOrderMenu.setDisable(false);

            order.setEntree(Order.Entree.NONE);
            order.setDrink(Order.Drink.NONE);
            order.setWaterSelection(false);
            order.setSpecialRequest("-");

            totalLabel.setText("Total: $0.00");
            orderEntreeLabel.setText(" ");
            orderDrinkLabel.setText(" ");
        });

        placeButton.setOnAction(e -> {
            FXMLLoader loader = new FXMLLoader(App.class.getResource("billPanel.fxml"));
            try {
                Parent billParent = loader.load();

                BillPanelController BPC = loader.getController();

                Order sendingOrder = order;

                BPC.receiveOrder(sendingOrder);

                Scene billScene = new Scene(billParent);

                Stage billStage = new Stage();
                billStage.setScene(billScene);
                billStage.setTitle("Order Summary");

                billStage.show();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }


        });

        exitMenu.setOnAction(e -> {
            Node source = (Node) e.getSource();

            Stage stage = (Stage) source.getScene().getWindow();

            stage.close();
        });

        clearOrderMenu.setOnAction(e -> {
            radioCoffee.setSelected(false);
            radioJuice.setSelected(false);
            radioSoda.setSelected(false);
            radioTea.setSelected(false);

            entreeListView.getSelectionModel().clearSelection();

            waterCheckBox.setSelected(false);

            requestTextField.clear();

            placeButton.setDisable(true);

            order.setEntree(Order.Entree.NONE);
            order.setDrink(Order.Drink.NONE);
            order.setWaterSelection(false);
            order.setSpecialRequest("-");

            totalLabel.setText("Total: $0.00");
            orderEntreeLabel.setText(" ");
            orderDrinkLabel.setText(" ");
        });

        placeOrderMenu.setOnAction(e -> {
            FXMLLoader loader = new FXMLLoader(App.class.getResource("billPanel.fxml"));
            try {
                Parent billParent = loader.load();

                BillPanelController BPC = loader.getController();

                Order sendingOrder = order;

                BPC.receiveOrder(sendingOrder);

                Scene billScene = new Scene(billParent);

                Stage billStage = new Stage();
                billStage.setScene(billScene);
                billStage.setTitle("Order Summary");

                billStage.show();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        entreeMenu.setOnAction(e -> {
            entreeListView.getSelectionModel().clearSelection();
            order.setEntree(Order.Entree.NONE);
            orderEntreeLabel.setText(" ");
            totalLabel.setText(String.format("Total: $%.2f", order.getTotalPrice()));
        });

        drinkMenu.setOnAction(e -> {
            radioCoffee.setSelected(false);
            radioJuice.setSelected(false);
            radioSoda.setSelected(false);
            radioTea.setSelected(false);

            order.setDrink(Order.Drink.NONE);
            orderEntreeLabel.setText(" ");
            totalLabel.setText(String.format("Total: $%.2f", order.getTotalPrice()));
        });

        requestMenu.setOnAction(e -> {
            requestTextField.clear();
            order.setSpecialRequest("-");
        });

        aboutMenu.setOnAction(e -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
        });
    }    
   
    
    public Order getOrder() {
        return order;

    }


    
}
