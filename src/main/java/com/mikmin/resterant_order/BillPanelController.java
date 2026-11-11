package com.mikmin.resterant_order;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class BillPanelController implements Initializable {

        @FXML
        private Button closeBillButton;

        @FXML
        private Label drinkCostLabel;

        @FXML
        private Label drinkNameLabel;

        @FXML
        private Label entreeCostLabel;

        @FXML
        private Label entreeNameLabel;

        @FXML
        private Label requestLabel;

        @FXML
        private Label totalLabel;

        @FXML
        private Label waterLabel;

        Order order;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        closeBillButton.setOnAction(e -> {
            Node source = (Node) e.getSource();

            Stage stage = (Stage) source.getScene().getWindow();

            stage.close();
        });
    }

    public void receiveOrder(Order newOrder) {
        order = newOrder;
        entreeNameLabel.setText(String.format("%s", order.getEntree()));
        entreeCostLabel.setText(String.format("$%.2f", order.getEntreePrice()));
        drinkNameLabel.setText(String.format("%s", order.getDrink()));
        drinkCostLabel.setText(String.format("$%.2f", order.getDrinkPrice()));
        if (order.isWaterSelection()) {
            waterLabel.setText("Yes");
        }
        requestLabel.setText(String.format("%s", order.getSpecialRequest()));
        totalLabel.setText(String.format("$%.2f", order.getTotalPrice()));
    }


}
