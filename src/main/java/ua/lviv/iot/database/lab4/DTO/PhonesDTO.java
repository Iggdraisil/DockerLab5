package ua.lviv.iot.database.lab4.DTO;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.ResourceSupport;
import ua.lviv.iot.database.lab4.model.PhonesEntity;

public class PhonesDTO extends ResourceSupport {
    PhonesEntity phone;
    public PhonesDTO(PhonesEntity phone, Link selfLink) {
        this.phone=phone;
        add(selfLink);
    }
    public Integer getphoneId() {
        return phone.getNumber();
    }

    public Integer getAddress() {
        return phone.getPrice();
    }


    public Integer getphoneName() {
        return phone.getWorkspace();
    }
}
