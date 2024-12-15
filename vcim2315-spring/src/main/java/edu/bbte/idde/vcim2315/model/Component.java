package edu.bbte.idde.vcim2315.model;

public class Component extends BaseEntity {
    private String name;
    private String type;
    private double price;
    private int quantity;

    public Component() {
        super();
    }

    public Component(long id, String name, String type, double price, int quantity) {
        super(id);
        this.name = name;
        this.type = type;
        this.price = price;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
