package controller;

import java.util.List;
import model.*;

public class Controller {
    
    GestionITDAO dao = new DBImplementation();
    
    public List<Equipment> getEquipment() {
        return dao.getEquipment();
    }

    public boolean addEquipment(Category category, String brandAndModel, Status status, String assignedPerson) {
        return dao.addEquipment(category, brandAndModel, status, assignedPerson);
    }

    public boolean modifyEquipment(Equipment eq) {
        return dao.modifyEquipment(eq);
    }

    public boolean removeEquipment(Equipment eq) {
        return dao.removeEquipment(eq);
    }
}
