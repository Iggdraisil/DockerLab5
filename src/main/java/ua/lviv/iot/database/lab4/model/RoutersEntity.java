package ua.lviv.iot.database.lab4.model;

import javax.persistence.*;
import java.util.Set;
import java.util.Objects;

@Entity
@Table(name = "routers")
@IdClass(RoutersEntityPK.class)
public class RoutersEntity {
    private String ip;
    private Integer officeId;
    private OfficeEntity officeByOfficeId;
    private Set<WorkspaceEntity> workspaces;

    @Id
    @Column(name = "ip")
    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    @Id
    @Column(name = "office_id")
    public Integer getOfficeId() {
        return officeId;
    }

    public void setOfficeId(Integer officeId) {
        this.officeId = officeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoutersEntity that = (RoutersEntity) o;
        return Objects.equals(ip, that.ip) &&
                Objects.equals(officeId, that.officeId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ip, officeId);
    }

    @ManyToOne
    @JoinColumn(name = "office_id", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    public OfficeEntity getOfficeByOfficeId() {
        return officeByOfficeId;
    }

    public void setOfficeByOfficeId(OfficeEntity officeByOfficeId) {
        this.officeByOfficeId = officeByOfficeId;
    }

    @OneToMany(mappedBy = "routers")
    public Set<WorkspaceEntity> getWorkspaces() {
        return workspaces;
    }

    public void setWorkspaces(Set<WorkspaceEntity> workspaces) {
        this.workspaces = workspaces;
    }

    public RoutersEntity(String ip, Integer officeId) {
        this.ip = ip;
        this.officeId = officeId;
    }

    public RoutersEntity() {
    }

    @Override
    public String toString() {
        return "RoutersEntity{" +
                "ip='" + ip + '\'' +
                ", officeId=" + officeId +
                ", officeByOfficeId=" + officeByOfficeId +
                '}';
    }
}
