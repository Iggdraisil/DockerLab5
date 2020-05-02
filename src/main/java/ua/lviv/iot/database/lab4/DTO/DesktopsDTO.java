package ua.lviv.iot.database.lab4.DTO;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.ResourceSupport;
import ua.lviv.iot.database.lab4.model.DesktopsEntity;

import javax.persistence.Basic;
import javax.persistence.Column;

public class DesktopsDTO extends ResourceSupport {
    DesktopsEntity desktop;
    public DesktopsDTO(DesktopsEntity desktop, Link selfLink) {
        this.desktop=desktop;
        add(selfLink);
    }

    public Integer getDesktopId() {
        return desktop.getId();
    }

    public Integer getMotherboardId() {
        return desktop.getMotherboardId();
    }


    public String getDesktopName() {
        return desktop.getName();
    }

    public String getVideoadapter() {
        return desktop.getVideoadapter();
    }


    public Integer getRam() {
        return desktop.getRam();
    }

    public String getStorage() {
        return desktop.getStorage();
    }


    public Integer getPrice() {
        return desktop.getPrice();
    }


    public String getType() {
        return desktop.getType();
    }

}
