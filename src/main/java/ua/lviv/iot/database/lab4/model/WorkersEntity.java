package ua.lviv.iot.database.lab4.model;

import javax.persistence.*;
import java.util.Set;
import java.util.Objects;

@Entity
@Table(name = "workers", schema = "lab3", catalog = "")
public class WorkersEntity {
    private Integer id;
    private String name;
    private String surname;
    private Set<OfficeEntity> officeById;
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
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "surname")
    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WorkersEntity that = (WorkersEntity) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name) &&
                Objects.equals(surname, that.surname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, surname);
    }

    @ManyToMany(mappedBy = "workersHasOfficesById")
    public Set<OfficeEntity> getWorkersByOfficeById() {
        return officeById;
    }

    public void setWorkersByOfficeById(Set<OfficeEntity> officeById) {
        this.officeById = officeById;
    }

    @OneToMany(mappedBy = "workersByWorkersId")
    public Set<WorkspaceEntity> getWorkersByWorkspacesId() {
        return workspacesById;
    }

    public void setWorkersByWorkspacesId(Set<WorkspaceEntity> workspacesById) {
        this.workspacesById = workspacesById;
    }

    public WorkersEntity(Integer id, String name, String surname) {
        this.id = id;
        this.name = name;
        this.surname = surname;
    }

    public WorkersEntity() {
    }

    @Override
    public String toString() {
        return "WorkersEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                '}';
    }
}
