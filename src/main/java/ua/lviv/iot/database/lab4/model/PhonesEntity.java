package ua.lviv.iot.database.lab4.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "phones")
public class PhonesEntity {
    private Integer number;
    private Integer price;
    private Integer workspace;

    @Id
    @Column(name = "number")
    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    @Basic
    @Column(name = "price")
    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    @Basic
    @Column(name = "workspace_id")
    public Integer getWorkspace() {
        return workspace;
    }

    public void setWorkspace(Integer workspace) {
        this.workspace = workspace;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PhonesEntity that = (PhonesEntity) o;
        return Objects.equals(number, that.number) &&
                Objects.equals(price, that.price) &&
                Objects.equals(workspace, that.workspace);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, price, workspace);
    }

    public PhonesEntity() {
    }

    public PhonesEntity(Integer number, Integer price, Integer workspace) {
        this.number = number;
        this.price = price;
        this.workspace = workspace;
    }

    @Override
    public String toString() {
        return "PhonesEntity{" +
                "number=" + number +
                ", price=" + price +
                ", workspace=" + workspace +
                '}';
    }
}
