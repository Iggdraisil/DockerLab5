package ua.lviv.iot.database.lab4.model;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "desktops")
public class DesktopsEntity {
    private Integer id;
    private Integer motherboardId;
    private String name;
    private String videoadapter;
    private Integer ram;
    private String storage;
    private Integer price;
    private String type;
    private Set<WorkspaceEntity> workspaceHasDesktopsById;

    @Id
    @Column(name = "id")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "motherboard_id")
    public Integer getMotherboardId() {
        return motherboardId;
    }

    public void setMotherboardId(Integer motherboardId) {
        this.motherboardId = motherboardId;
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
    @Column(name = "videoadapter")
    public String getVideoadapter() {
        return videoadapter;
    }

    public void setVideoadapter(String videoadapter) {
        this.videoadapter = videoadapter;
    }

    @Basic
    @Column(name = "RAM")
    public Integer getRam() {
        return ram;
    }

    public void setRam(Integer ram) {
        this.ram = ram;
    }

    @Basic
    @Column(name = "storage")
    public String getStorage() {
        return storage;
    }

    public void setStorage(String storage) {
        this.storage = storage;
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
    @Column(name = "type")
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DesktopsEntity that = (DesktopsEntity) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(motherboardId, that.motherboardId) &&
                Objects.equals(name, that.name) &&
                Objects.equals(videoadapter, that.videoadapter) &&
                Objects.equals(ram, that.ram) &&
                Objects.equals(storage, that.storage) &&
                Objects.equals(price, that.price) &&
                Objects.equals(type, that.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, motherboardId, name, videoadapter, ram, storage, price, type);
    }

    @ManyToMany(mappedBy = "workspaceHasDesktopsById")
    public Set<WorkspaceEntity> getWorkspaceHasDesktopsById() {
        return workspaceHasDesktopsById;
    }

    public void setWorkspaceHasDesktopsById(Set<WorkspaceEntity> workspaceHasDesktopsById) {
        this.workspaceHasDesktopsById = workspaceHasDesktopsById;
    }

    public DesktopsEntity(Integer id, Integer motherboardId, String name, String videoadapter, Integer ram, String storage, Integer price, String type) {
        this.id = id;
        this.motherboardId = motherboardId;
        this.name = name;
        this.videoadapter = videoadapter;
        this.ram = ram;
        this.storage = storage;
        this.price = price;
        this.type = type;
    }

    public DesktopsEntity() {
    }

    @Override
    public String toString() {
        return "DesktopsEntity{" +
                "id=" + id +
                ", motherboardId=" + motherboardId +
                ", name='" + name + '\'' +
                ", videoadapter='" + videoadapter + '\'' +
                ", ram=" + ram +
                ", storage='" + storage + '\'' +
                ", price=" + price +
                ", type='" + type + '\'' +
                '}';
    }
}
