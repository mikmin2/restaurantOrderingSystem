module com.mikmin.resterant_order {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;

    opens com.mikmin.resterant_order to javafx.fxml;
    exports com.mikmin.resterant_order;
}
