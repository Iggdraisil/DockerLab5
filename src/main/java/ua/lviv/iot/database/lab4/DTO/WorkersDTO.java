package ua.lviv.iot.database.lab4.DTO;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.ResourceSupport;
import ua.lviv.iot.database.lab4.model.WorkersEntity;

public class WorkersDTO extends ResourceSupport {
    WorkersEntity worker;
    public WorkersDTO(WorkersEntity worker, Link selfLink) {
        this.worker=worker;
        add(selfLink);
    }
    public Integer getworkerId() {
        return worker.getId();
    }

    public String getWorkerName() {
        return worker.getName();
    }


    public String getSurname() {
        return worker.getSurname();
    }
}
