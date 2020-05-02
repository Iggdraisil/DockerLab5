package ua.lviv.iot.database.lab4.model;

import javax.persistence.*;
import java.util.Set;
import java.util.Objects;

@Entity
@Table(name = "workspace")
public class WorkspaceEntity {
    private Integer id;
    private Integer worker;
    private String ip;
    private Integer officeId;
    private Set<PrintersEntity> printersById;

    private WorkersEntity workersByWorkersId;
    private RoutersEntity router;

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
    @JoinTable(name = "printers_has_workspace",
            joinColumns = @JoinColumn(name = "workspace_id"),
            inverseJoinColumns = @JoinColumn(name = "printers_id"))
    public Set<PrintersEntity> getPrintersById() {
        return printersById;
    }

    public void setPrintersById(Set<PrintersEntity> printersById) {
        this.printersById = printersById;
    }

    @ManyToOne
    @JoinColumn(name = "workers_id", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    public WorkersEntity getWorkersByWorkersId() {
        return workersByWorkersId;
    }

    public void setWorkersByWorkersId(WorkersEntity workersByWorkersId) {
        this.workersByWorkersId = workersByWorkersId;
    }

    @ManyToOne
    @JoinColumns({@JoinColumn(name = "routers_ip", referencedColumnName = "ip", nullable = false, insertable = false, updatable = false),
            @JoinColumn(name = "routers_office_id", referencedColumnName = "office_id", nullable = false, insertable = false, updatable = false)})
    public RoutersEntity getRouters() {
        return router;
    }

    public void setRouters(RoutersEntity routers) {
        this.router = routers;
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
