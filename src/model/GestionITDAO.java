package model;

import java.util.List;

public interface GestionITDAO {
    
    public List<Equipment> getEquipment();
    
    public boolean addEquipment(Category category, String brandAndModel, Status status, String assignedPerson);
    
    public boolean modifyEquipment(Equipment eq);
    
    public boolean removeEquipment(Equipment eq);
}
