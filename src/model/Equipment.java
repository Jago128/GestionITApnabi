package model;

public class Equipment {

    private String id;

    private Category category;

    private String brandAndModel;

    private Status status;

    private String assignedPerson;

    public Equipment() {
        this.id = "";
        this.category = Category.OTROS;
        this.brandAndModel = "";
        this.status = Status.OPERATIVO;
        this.assignedPerson = "";
    }

    public Equipment(String id, Category category, String brandAndModel, Status status, String assignedPerson) {
        this.id = id;
        this.category = category;
        this.brandAndModel = brandAndModel;
        this.status = status;
        this.assignedPerson = assignedPerson;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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
