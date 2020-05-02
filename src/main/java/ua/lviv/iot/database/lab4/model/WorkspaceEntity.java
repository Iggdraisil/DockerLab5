package ua.lviv.iot.database.lab4.model;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "workspace")
public class WorkspaceEntity {
    private Integer id;
    private Integer worker;
    private String ip;
    private Integer officeId;
    private Set<DesktopsEntity> workspaceHasDesktopsById;
    private Set<MonitorsEntity> workspaceHasMonitorsById;

    @Id
    @Column(name = "id")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "workers_id")
    public Integer getWorker() {
        return worker;
    }

    public void setWorker(Integer worker) {
        this.worker = worker;
    }

    @Basic
    @Column(name = "routers_ip")
    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    @Basic
    @Column(name = "routers_office_id")
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
        WorkspaceEntity that = (WorkspaceEntity) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(worker, that.worker) &&
                Objects.equals(ip, that.ip) &&
                Objects.equals(officeId, that.officeId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, worker, ip, officeId);
    }

    @ManyToMany
    @JoinTable(name = "workspace_has_desktops",
            joinColumns = @JoinColumn(name = "workspace_id"),
            inverseJoinColumns = @JoinColumn(name = "desktops_id"))
    public Set<DesktopsEntity> getWorkspaceHasDesktopsById() {
        return workspaceHasDesktopsById;
    }

    public void setWorkspaceHasDesktopsById(Set<DesktopsEntity> workspaceHasDesktopsById) {
        this.workspaceHasDesktopsById = workspaceHasDesktopsById;
    }

    @ManyToMany
    @JoinTable(name = "workspace_has_monitors",
            joinColumns = @JoinColumn(name = "workspace_id"),
            inverseJoinColumns = @JoinColumn(name = "monitors_id"))
    public Set<MonitorsEntity> getWorkspaceHasMonitorsById() {
        return workspaceHasMonitorsById;
    }

    public void setWorkspaceHasMonitorsById(Set<MonitorsEntity> workspaceHasMonitorsById) {
        this.workspaceHasMonitorsById = workspaceHasMonitorsById;
    }

    public WorkspaceEntity(Integer id, Integer worker, String ip, Integer officeId) {
        this.id = id;
        this.worker = worker;
        this.ip = ip;
        this.officeId = officeId;
    }

    public WorkspaceEntity() {
    }

    @Override
    public String toString() {
        return "WorkspaceEntity{" +
                "id=" + id +
                ", worker=" + worker +
                ", ip='" + ip + '\'' +
                ", officeId=" + officeId +
                '}';
    }
}
