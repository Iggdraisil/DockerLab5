package ua.lviv.iot.database.lab4.DTO;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.ResourceSupport;
import ua.lviv.iot.database.lab4.model.OfficeEntity;
import ua.lviv.iot.database.lab4.model.RoutersEntity;
import ua.lviv.iot.database.lab4.model.WorkersEntity;
import ua.lviv.iot.database.lab4.model.WorkspaceEntity;

public class WorkspaceDTO extends ResourceSupport {
    WorkspaceEntity workspace;
    public WorkspaceDTO(WorkspaceEntity workspace, Link selfLink) {
        this.workspace=workspace;
        add(selfLink);
    }
    public Integer getworkspaceId() {
        return workspace.getId();
    }

    public String getIp() {
        return workspace.getIp();
    }


    public OfficeEntity getOffice() {
        return workspace.getRouters().getOfficeByOfficeId();
    }

    public WorkersEntity getWorker() {
        return workspace.getWorkersByWorkersId();
    }

    public RoutersEntity getRouter() {
        return workspace.getRouters();
    }
}
