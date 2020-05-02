package ua.lviv.iot.database.lab4.controller.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.lviv.iot.database.lab4.DTO.DesktopsDTO;
import ua.lviv.iot.database.lab4.DTO.OfficeDTO;
import ua.lviv.iot.database.lab4.DTO.OfficeDTO;
import ua.lviv.iot.database.lab4.controller.OfficeController;
import ua.lviv.iot.database.lab4.exceptions.ExistsOfficeForOfficeException;
import ua.lviv.iot.database.lab4.exceptions.NoSuchOfficeException;
import ua.lviv.iot.database.lab4.exceptions.NoSuchWorkspaceException;
import ua.lviv.iot.database.lab4.model.OfficeEntity;
import ua.lviv.iot.database.lab4.model.OfficeEntity;
import ua.lviv.iot.database.lab4.service.OfficeService;
import ua.lviv.iot.database.lab4.service.OfficeService;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
public class OfficeControllerImpl implements OfficeController {
    @Autowired
    OfficeService officeService;

    @Override
    @PostMapping("api/office")
    public ResponseEntity<OfficeDTO> create(@RequestBody OfficeEntity entity) throws NoSuchOfficeException {
        entity.setId(0);
        officeService.create(entity);
        var link = linkTo(methodOn(OfficeControllerImpl.class).create(entity)).withSelfRel();
        var office = officeService.findAll();
        var officeDTO = new OfficeDTO(office.get(office.size() - 1), link);
        return new ResponseEntity<>(officeDTO, HttpStatus.OK);

    }

    @Override
    @PutMapping(value = "api/office/{id}")
    public ResponseEntity<OfficeDTO> update(@PathVariable Integer id, @RequestBody OfficeEntity office) throws NoSuchOfficeException {
        officeService.update(office, id);
        var officep = officeService.findById(id);
        var link = linkTo(methodOn(OfficeControllerImpl.class).update(id, office)).withSelfRel();
        var officeDTO = new OfficeDTO(officep, link);
        return new ResponseEntity<>(officeDTO, HttpStatus.OK);
    }

    @Override
    @DeleteMapping(value = "api/office/{id}")
    public ResponseEntity delete(@PathVariable Integer id) throws NoSuchOfficeException, ExistsOfficeForOfficeException {
        officeService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @Override
    @GetMapping(value = "api/office/{id}")
    public ResponseEntity<OfficeDTO> find(@PathVariable Integer id) throws NoSuchOfficeException {
        var office = officeService.findById(id);
        var link = linkTo(methodOn(OfficeControllerImpl.class).find(id)).withSelfRel();

        var officeDTO = new OfficeDTO(office, link);

        return new ResponseEntity<>(officeDTO, HttpStatus.OK);
    }

    @Override
    @GetMapping(value = "api/office")
    public ResponseEntity<List<OfficeDTO>> findAll() {
        List<OfficeEntity> office = officeService.findAll();
        var link = linkTo(methodOn(OfficeControllerImpl.class).findAll()).withSelfRel();
        List<OfficeDTO> officeDTOs = new ArrayList<>();
        office.forEach(officeEntity -> officeDTOs.add( new OfficeDTO(officeEntity, new Link(link.getHref() + "/" + officeEntity.getId()).withSelfRel())));
        return new ResponseEntity<>(officeDTOs, HttpStatus.OK);
    }

    @GetMapping(value = "api/office/workspace/{id}")
    public ResponseEntity<List<OfficeDTO>> getOfficeByWorker(@PathVariable Integer id) throws NoSuchWorkspaceException {
        Set<OfficeEntity> office = officeService.getOfficesByWorkersId(id);
        var link = linkTo(methodOn(OfficeControllerImpl.class).getOfficeByWorker(id)).withSelfRel();

        List<OfficeDTO> officeDTOs = new ArrayList<>();
        office.forEach(officeEntity -> officeDTOs.add( new OfficeDTO(officeEntity, new Link(link.getHref() + "/" + officeEntity.getId()).withSelfRel())));
        return new ResponseEntity<>(officeDTOs, HttpStatus.OK);
    }
}
