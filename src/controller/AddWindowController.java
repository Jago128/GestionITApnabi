package controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.*;

public class AddWindowController implements Initializable {

    @FXML
    private Button btnExit;

    @FXML
    private ComboBox<Category> cbCategory;

    @FXML
    private TextArea textAreaModelBrand;

    @FXML
    private ComboBox<Status> cbStatus;

    @FXML
    private TextField textFieldPersona;

    @FXML
    private Button btnAdd;

    private Controller cont;
    private MainWindowController mainCont;
    
    public void setMainWindowController(MainWindowController mainCont) {
        this.mainCont = mainCont;
    }

    public void setController(Controller cont) {
        this.cont = cont;
    }
    
    @FXML
    private void exit(ActionEvent event) {
        Stage stage = (Stage) btnExit.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void add(ActionEvent event) {
        Category cat = cbCategory.getValue();
        Status sta = cbStatus.getValue();
        String per = textFieldPersona.getText();
        String mB = textAreaModelBrand.getText();

        if (per == null || per.trim().isEmpty() || mB == null || mB.trim().isEmpty() || cat == null || sta == null) {
            showAlert(Alert.AlertType.ERROR, "Error de validacion", "Faltan campos", "Por favor, rellena todos los campos.");
        } else {
            if (cont.addEquipment(cat, mB, sta, per)) {
                Alert alert = showAlert(Alert.AlertType.CONFIRMATION, "Equipamiento añadido correctamente",
                        "El equipamiento ha sido añadido correctamente.", "¿Quieres añadir mas equipamiento?");
                if (alert.getResult().equals(ButtonType.OK)) {
                    textFieldPersona.clear();
                    textAreaModelBrand.clear();
                    cbCategory.setValue(null);
                    cbStatus.setValue(null);
                } else if (alert.getResult().equals(ButtonType.CANCEL)) {
                    Stage stage = (Stage) btnAdd.getScene().getWindow();
                    stage.close();
                }
            } else {
                showAlert(Alert.AlertType.ERROR, "ERROR", "", "Ha occurrido un error al añadir el equipamiento.");
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

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cbCategory.getItems().addAll(Category.values());
        cbStatus.getItems().addAll(Status.values());
    }
}
