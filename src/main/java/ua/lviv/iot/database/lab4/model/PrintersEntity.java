package ua.lviv.iot.database.lab4.model;

import javax.persistence.*;
import java.util.Set;
import java.util.Objects;

@Entity
@Table(name = "printers", schema = "lab3", catalog = "")
public class PrintersEntity {
    private Integer id;
    private String type;
    private String brand;
    private String model;
    private Set<WorkspaceEntity> workspacesById;

    @Id
    @Column(name = "id")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "type")
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Basic
    @Column(name = "brand")
    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    @Basic
    @Column(name = "model")
    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PrintersEntity that = (PrintersEntity) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(type, that.type) &&
                Objects.equals(brand, that.brand) &&
                Objects.equals(model, that.model);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, type, brand, model);
    }

    @ManyToMany(mappedBy = "printersById")
    public Set<WorkspaceEntity> getWorkspacesById() {
        return workspacesById;
    }

    public void setWorkspacesById(Set<WorkspaceEntity> workspacesById) {
        this.workspacesById = workspacesById;
    }

    public PrintersEntity() {
    }

    public PrintersEntity(Integer id, String type, String brand, String model) {
        this.id = id;
        this.type = type;
        this.brand = brand;
        this.model = model;
    }

    @Override
    public String toString() {
        return "PrintersEntity{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                '}';
    }
}
