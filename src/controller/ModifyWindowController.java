package controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.stage.Stage;

import model.*;

public class ModifyWindowController implements Initializable {

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
    private Button btnModify;

    private Controller cont;
    private MainWindowController mainCont;
    private Equipment equip;

    public void setMainWindowController(MainWindowController mainCont) {
        this.mainCont = mainCont;
    }

    public void setController(Controller cont) {
        this.cont = cont;
    }

    public void setEquipmentToModify(Equipment equip) {
        this.equip = equip;
        loadData();
    }

    private void loadData() {
        cbCategory.setValue(equip.getCategory());
        textAreaModelBrand.setText(equip.getBrandAndModel());
        cbStatus.setValue(equip.getStatus());
        textFieldPersona.setText(equip.getAssignedPerson());
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

    @FXML
    private void exit(ActionEvent event) {
        Stage stage = (Stage) btnExit.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void modify(ActionEvent event) {
        String brandAndModel = textAreaModelBrand.getText();
        String person = textFieldPersona.getText();
        Status status = cbStatus.getValue();
        Category cat = cbCategory.getValue();

        if (brandAndModel == null || brandAndModel.trim().isEmpty() || person == null || person.trim().isEmpty()) {
            showAlert(Alert.AlertType.WARNING, "Error de validacion", "Faltan campos", "Por favor, rellena todos los campos.");
        } else {
            if (equip.getAssignedPerson().equals(person) && equip.getBrandAndModel().equals(brandAndModel) && equip.getCategory().equals(cat)
                    && equip.getStatus().equals(status)) {
                showAlert(Alert.AlertType.WARNING, "Error de validacion", "Campos no cambiados", "Por favor, cambia algun campo.");
            } else {
                equip.setAssignedPerson(person);
                equip.setBrandAndModel(brandAndModel);
                equip.setCategory(cat);
                equip.setStatus(status);
                if (cont.modifyEquipment(equip)) {
                    mainCont.loadAllEquipment();
                    showAlert(Alert.AlertType.INFORMATION, "Equipamiento modificado",
                            "", "El equipamiento ha sido modificado correctamente.");
                    Stage stage = (Stage) btnModify.getScene().getWindow();
                    stage.close();
                } else {
                    showAlert(Alert.AlertType.ERROR, "ERROR", "", "Ha occurrido un error al modificar el equipamiento.");
                }
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cbCategory.getItems().addAll(Category.values());
        cbStatus.getItems().addAll(Status.values());
    }
}
