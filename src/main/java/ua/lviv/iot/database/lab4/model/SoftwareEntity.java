package ua.lviv.iot.database.lab4.model;

import javax.persistence.*;
import java.util.Set;
import java.util.Objects;

@Entity
@Table(name = "software", schema = "lab3", catalog = "")
public class SoftwareEntity {
    private Integer id;
    private String price;
    private String name;
    private Set<DesktopsEntity> desktopsById;

    @Id
    @Column(name = "id")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "price")
    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SoftwareEntity that = (SoftwareEntity) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(price, that.price) &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, price, name);
    }

    @ManyToMany(mappedBy = "desktopsHasSoftwareById")
    public Set<DesktopsEntity> getDesktopsById() {
        return desktopsById;
    }

    public void setDesktopsById(Set<DesktopsEntity> desktopsById) {
        this.desktopsById = desktopsById;
    }

    public SoftwareEntity(Integer id, String price, String name) {
        this.id = id;
        this.price = price;
        this.name = name;
    }

    public SoftwareEntity() {
    }

    @Override
    public String toString() {
        return "SoftwareEntity{" +
                "id=" + id +
                ", price='" + price + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
