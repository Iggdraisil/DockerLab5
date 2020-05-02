package ua.lviv.iot.database.lab4.DTO;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.ResourceSupport;
import ua.lviv.iot.database.lab4.model.PrintersEntity;

public class PrintersDTO extends ResourceSupport {
    PrintersEntity printer;
    public PrintersDTO(PrintersEntity printer, Link selfLink) {
        this.printer=printer;
        add(selfLink);
    }
    public Integer getprinterId() {
        return printer.getId();
    }

    public String getBrand() {
        return printer.getBrand();
    }


    public String getPrinterModel() {
        return printer.getModel();
    }

    public String getType() {
        return printer.getType();
    }
}
