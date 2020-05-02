package ua.lviv.iot.database.lab4.DTO;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.ResourceSupport;
import ua.lviv.iot.database.lab4.model.WorkspaceEntity;

public class WorkspaceDTO extends ResourceSupport {
    WorkspaceEntity workspace;
    public WorkspaceDTO(WorkspaceEntity workspace, Link selfLink) {
        this.workspace=workspace;
        add(selfLink);
    }
}
