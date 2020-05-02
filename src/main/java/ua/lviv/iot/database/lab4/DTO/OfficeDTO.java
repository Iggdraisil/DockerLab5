package ua.lviv.iot.database.lab4.DTO;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.ResourceSupport;
import ua.lviv.iot.database.lab4.model.OfficeEntity;

public class OfficeDTO extends ResourceSupport {
    OfficeEntity office;

    public OfficeDTO(OfficeEntity office, Link selfLink) {
        this.office = office;
        add(selfLink);
    }

    public Integer getOfficeId() {
        return office.getId();
    }

    public String getAddress() {
        return office.getAddress();
    }


    public String getOfficeName() {
        return office.getName();
    }
}
