package edu.bbte.idde.vcim2315.model;

public abstract class BaseEntity {
    private long id;

    public BaseEntity() {

    }

    public BaseEntity(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
