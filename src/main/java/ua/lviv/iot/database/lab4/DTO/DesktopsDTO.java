package ua.lviv.iot.database.lab4.DTO;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.ResourceSupport;
import ua.lviv.iot.database.lab4.model.DesktopsEntity;


import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

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


    public String getName() {
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


    public Integer getDesktopPrice() {
        return desktop.getPrice();
    }


    public String getType() {
        return desktop.getType();
    }
}
