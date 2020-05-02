package ua.lviv.iot.database.lab4.DTO;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.ResourceSupport;
import ua.lviv.iot.database.lab4.model.SoftwareEntity;

public class SoftwareDTO extends ResourceSupport {
    SoftwareEntity software;
    public SoftwareDTO(SoftwareEntity software, Link selfLink) {
        this.software=software;
        add(selfLink);
    }

    public Integer getsoftwareId() {
        return software.getId();
    }

    public String getSoftwareName() {
        return software.getName();
    }


    public String getSoftwarePrice() {
        return software.getPrice();
    }

}
