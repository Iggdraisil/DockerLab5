package ua.lviv.iot.database.lab4.DTO;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.ResourceSupport;
import ua.lviv.iot.database.lab4.model.MonitorsEntity;

public class MonitorsDTO extends ResourceSupport {
    MonitorsEntity monitor;

    public MonitorsDTO(MonitorsEntity monitor, Link selfLink) {
        this.monitor=monitor;
        add(selfLink);
    }
    public Integer getMonitorId() {
        return monitor.getId();
    }

    public Integer getSrgbCoverage() {
        return monitor.getSrgbCoverage();
    }


    public String getMatrixType() {
        return monitor.getMatrixType();
    }

    public String getPrice() {
        return monitor.getPrice();
    }


    public String getResolution() {
        return monitor.getResolution();
    }
}
