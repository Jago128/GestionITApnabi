/*
 */
package controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import model.Equipment;

public class ModifyWindowController implements Initializable {

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

    /**
     * Loads the routine's data into the fields.
     */
    private void loadData() {

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
