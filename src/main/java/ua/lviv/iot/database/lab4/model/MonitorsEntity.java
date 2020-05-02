package ua.lviv.iot.database.lab4.model;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "monitors")
public class MonitorsEntity {
    private Integer id;
    private String resolution;
    private Integer srgbCoverage;
    private String matrixType;
    private String price;
    private Set<WorkspaceEntity> workspaceHasMonitorsById;

    public MonitorsEntity() {

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
    @Column(name = "resolution")
    public String getResolution() {
        return resolution;
    }

    public void setResolution(String resolution) {
        this.resolution = resolution;
    }

    @Basic
    @Column(name = "srgb_coverage")
    public Integer getSrgbCoverage() {
        return srgbCoverage;
    }

    public void setSrgbCoverage(Integer srgbCoverage) {
        this.srgbCoverage = srgbCoverage;
    }

    @Basic
    @Column(name = "matrix_type")
    public String getMatrixType() {
        return matrixType;
    }

    public void setMatrixType(String matrixType) {
        this.matrixType = matrixType;
    }

    @Basic
    @Column(name = "price")
    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MonitorsEntity that = (MonitorsEntity) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(resolution, that.resolution) &&
                Objects.equals(srgbCoverage, that.srgbCoverage) &&
                Objects.equals(matrixType, that.matrixType) &&
                Objects.equals(price, that.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, resolution, srgbCoverage, matrixType, price);
    }

    @ManyToMany(mappedBy = "workspaceHasMonitorsById")
    public Set<WorkspaceEntity> getWorkspaceHasMonitorsById() {
        return workspaceHasMonitorsById;
    }

    public void setWorkspaceHasMonitorsById(Set<WorkspaceEntity> workspaceHasMonitorsById) {
        this.workspaceHasMonitorsById = workspaceHasMonitorsById;
    }

    public MonitorsEntity(Integer id, String resolution, Integer srgbCoverage, String matrixType, String price) {
        this.id = id;
        this.resolution = resolution;
        this.srgbCoverage = srgbCoverage;
        this.matrixType = matrixType;
        this.price = price;
    }

    @Override
    public String toString() {
        return "MonitorsEntity{" +
                "id=" + id +
                ", resolution='" + resolution + '\'' +
                ", srgbCoverage=" + srgbCoverage +
                ", matrixType='" + matrixType + '\'' +
                ", price='" + price + '\'' +
                '}';
    }
}
