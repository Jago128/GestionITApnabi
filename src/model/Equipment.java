package model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "EQUIPMENT")
public class Equipment implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "equipment_id")
    private int id;

    @Enumerated(EnumType.STRING)
    @Column(name = "category")
    private Category category;

    @Column(name = "brandAndModel", nullable = false, length = 50)
    private String brandAndModel;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status;

    @Column(name = "assignedPerson", nullable = false, length = 50)
    private String assignedPerson;

    public Equipment() {
        this.category = Category.OTROS;
        this.brandAndModel = "";
        this.status = Status.OPERATIVO;
        this.assignedPerson = "";
    }
    
    public Equipment(Category category, String brandAndModel, Status status, String assignedPerson) {
        this.category = category;
        this.brandAndModel = brandAndModel;
        this.status = status;
        this.assignedPerson = assignedPerson;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getBrandAndModel() {
        return brandAndModel;
    }

    public void setBrandAndModel(String brandAndModel) {
        this.brandAndModel = brandAndModel;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getAssignedPerson() {
        return assignedPerson;
    }

    public void setAssignedPerson(String assignedPerson) {
        this.assignedPerson = assignedPerson;
    }

    @Override
    public String toString() {
        return "Equipment{" + "id=" + id + ", category=" + category + ", brandAndModel=" + brandAndModel + ", status=" + status + ", assignedPerson=" + assignedPerson + '}';
    }
}
