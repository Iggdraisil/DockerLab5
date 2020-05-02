package ua.lviv.iot.database.lab4.model;

import javax.persistence.*;
import java.util.Set;
import java.util.Objects;

@Entity
@Table(name = "office")
public class OfficeEntity {

    private Integer id;
    private String address;
    private String name;
    private Set<WorkersEntity> workersHasOfficesById;
    public OfficeEntity() {
    }

    @Id
    @Column(name = "id")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "address")
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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
        OfficeEntity that = (OfficeEntity) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(address, that.address) &&
                Objects.equals(name, that.name);
    }

    public OfficeEntity(Integer id, String address, String name) {
        this.id = id;
        this.address = address;
        this.name = name;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, address, name);
    }

    @ManyToMany
    @JoinTable(name = "workers_has_offices",
            joinColumns = @JoinColumn(name = "workers_id"),
            inverseJoinColumns = @JoinColumn(name = "offices_id"))
    public Set<WorkersEntity> getWorkersHasOfficesById() {
        return workersHasOfficesById;
    }

    public void setWorkersHasOfficesById(Set<WorkersEntity> workersHasOfficesById) {
        this.workersHasOfficesById = workersHasOfficesById;
    }

    @Override
    public String toString() {
        return "OfficeEntity{" +
                "id=" + id +
                ", address='" + address + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
