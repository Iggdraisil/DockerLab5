package ua.lviv.iot.database.lab4.DTO;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.ResourceSupport;
import ua.lviv.iot.database.lab4.model.OfficeEntity;
import ua.lviv.iot.database.lab4.model.RoutersEntity;
import ua.lviv.iot.database.lab4.model.WorkspaceEntity;

import java.util.Set;

public class RoutersDTO extends ResourceSupport {
    RoutersEntity router;
    public RoutersDTO(RoutersEntity router, Link selfLink) {
        this.router=router;
        add(selfLink);
    }
    public String getrouterId() {
        return router.getIp();
    }

    public OfficeEntity getOffice() {
        return router.getOfficeByOfficeId();
    }


    public Set<WorkspaceEntity> getWorkspaces() {
        return router.getWorkspaces();
    }

}
