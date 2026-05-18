package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.*;

public class MainWindowController implements Initializable {

    @FXML
    private TableView<Equipment> tableEquipment;
    @FXML
    private Button btnExit;
    @FXML
    private Button btnAdd;
    @FXML
    private Button btnModify;
    @FXML
    private Button btnDelete;
    @FXML
    private TableColumn<Equipment, Category> colCateg;
    @FXML
    private TableColumn<Equipment, String> colBrandAndModel;
    @FXML
    private TableColumn<Equipment, Status> colStatus;
    @FXML
    private TableColumn<Equipment, String> colAssignedPerson;

    private Controller cont;
    private Equipment selected;
    private ObservableList<Equipment> equipment;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cont = new Controller();
        configureTableColumns();
        setUpList();
        tableEquipment.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> getSelectedTableItem()
        );
    }

    public void setUpList() {
        equipment = FXCollections.observableArrayList();
        equipment.setAll(cont.getEquipment());
        tableEquipment.setItems(equipment);
    }

    private void configureTableColumns() {
        colCateg.setCellValueFactory(new PropertyValueFactory<>("category"));
        colBrandAndModel.setCellValueFactory(new PropertyValueFactory<>("brandAndModel"));
        colAssignedPerson.setCellValueFactory(new PropertyValueFactory<>("assignedPerson"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
    }

    private void getSelectedTableItem() {
        selected = tableEquipment.getSelectionModel().getSelectedItem();
    }

    public void loadAllEquipment() {
        equipment.setAll(cont.getEquipment());
    }

    @FXML
    private void exit(ActionEvent event) {
        Stage stage = (Stage) btnExit.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void add(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/AddWindow.fxml"));
            Parent root = loader.load();
            AddWindowController addCont = loader.getController();
            addCont.setController(cont);
            addCont.setMainWindowController(this);
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setTitle("Ventana Añadir");
            stage.setScene(scene);
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(((Node) event.getSource()).getScene().getWindow());
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void modify(ActionEvent event) {
        if (selected == null) {
            showAlert(Alert.AlertType.WARNING, "ERROR!", "¡Sin seleccion!", "Selecciona equipamiento antes de usar esta funcion.");
        } else {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/ModifyWindow.fxml"));
                Parent root = loader.load();
                ModifyWindowController modifyCont = loader.getController();
                modifyCont.setController(cont);
                modifyCont.setMainWindowController(this);
                modifyCont.setEquipmentToModify(selected);
                Scene scene = new Scene(root);
                Stage stage = new Stage();
                stage.setTitle("Ventana Modificar");
                stage.setScene(scene);
                stage.initModality(Modality.WINDOW_MODAL);
                stage.initOwner(((Node) event.getSource()).getScene().getWindow());
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    private void delete(ActionEvent event) {
        if (selected == null) {
            showAlert(Alert.AlertType.WARNING, "ERROR!", "¡Sin seleccion!", "Selecciona equipamiento antes de usar esta funcion.");
        } else {
            Alert warning = showAlert(Alert.AlertType.CONFIRMATION, "AVISO",
                    "¿Estas seguro de que quieres borrar el equipamiento seleccionado?", "Elija una opcion.");
            if (warning.getResult().equals(ButtonType.OK)) {
                if (cont.removeEquipment(selected)) {
                    showAlert(Alert.AlertType.INFORMATION, "Equipamiento eliminado", "", "El equipamiento seleccionado ha sido eliminado correctamente.");
                    loadAllEquipment();
                } else {
                    showAlert(Alert.AlertType.ERROR, "ERROR", "Equipamiento no encontradd", "No se ha encontrado el equipamiento.");
                }
            }
        }
    }

    private Alert showAlert(Alert.AlertType type, String title, String header, String content) {
        Alert alert = new Alert(type);
        alert.setTitle(title);

        if (header == null || !header.isEmpty()) {
            alert.setHeaderText(header);
        }

        alert.setContentText(content);
        alert.showAndWait();
        if (type == Alert.AlertType.CONFIRMATION) {
            return alert;
        }

        return null;
    }
}
