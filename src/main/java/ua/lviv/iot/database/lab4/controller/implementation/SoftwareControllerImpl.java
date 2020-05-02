package ua.lviv.iot.database.lab4.controller.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.lviv.iot.database.lab4.DTO.SoftwareDTO;
import ua.lviv.iot.database.lab4.DTO.SoftwareDTO;
import ua.lviv.iot.database.lab4.controller.SoftwareController;
import ua.lviv.iot.database.lab4.exceptions.ExistsSoftwareForDesktopsException;
import ua.lviv.iot.database.lab4.exceptions.NoSuchDesktopsException;
import ua.lviv.iot.database.lab4.exceptions.NoSuchSoftwareException;
import ua.lviv.iot.database.lab4.exceptions.NoSuchWorkspaceException;
import ua.lviv.iot.database.lab4.model.SoftwareEntity;
import ua.lviv.iot.database.lab4.model.SoftwareEntity;
import ua.lviv.iot.database.lab4.service.SoftwareService;
import ua.lviv.iot.database.lab4.service.SoftwareService;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
public class SoftwareControllerImpl implements SoftwareController {
    @Autowired
    SoftwareService softwareService;

    @Override
    @PostMapping("api/software")
    public ResponseEntity<SoftwareDTO> create(@RequestBody SoftwareEntity entity) throws SQLException, NoSuchSoftwareException {
        entity.setId(0);
        softwareService.create(entity);
        var link = linkTo(methodOn(SoftwareControllerImpl.class).create(entity)).withSelfRel();
        var software = softwareService.findAll();
        var softwareDTO = new SoftwareDTO(software.get(software.size() - 1), link);
        return new ResponseEntity<>(softwareDTO, HttpStatus.OK);

    }

    @Override
    @PutMapping(value = "api/software/{id}")
    public ResponseEntity<SoftwareDTO> update(@PathVariable Integer id, @RequestBody SoftwareEntity software) throws SQLException, NoSuchSoftwareException {
        softwareService.update(software, id);
        var softwarep = softwareService.findById(id);
        var link = linkTo(methodOn(SoftwareControllerImpl.class).update(id, software)).withSelfRel();
        var softwareDTO = new SoftwareDTO(softwarep, link);
        return new ResponseEntity<>(softwareDTO, HttpStatus.OK);
    }

    @Override
    @DeleteMapping(value = "api/software/{id}")
    public ResponseEntity delete(@PathVariable Integer id) throws SQLException, NoSuchSoftwareException, ExistsSoftwareForDesktopsException {
        softwareService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @Override
    @GetMapping(value = "api/software/{id}")
    public ResponseEntity<SoftwareDTO> find(@PathVariable Integer id) throws SQLException, NoSuchSoftwareException {
        var software = softwareService.findById(id);
        var link = linkTo(methodOn(SoftwareControllerImpl.class).find(id)).withSelfRel();

        var softwareDTO = new SoftwareDTO(software, link);

        return new ResponseEntity<>(softwareDTO, HttpStatus.OK);
    }

    @Override
    @GetMapping(value = "api/software")
    public ResponseEntity<List<SoftwareDTO>> findAll() {
        List<SoftwareEntity> software = softwareService.findAll();
        var link = linkTo(methodOn(SoftwareControllerImpl.class).findAll()).withSelfRel();
        List<SoftwareDTO> softwareDTOs = new ArrayList<>();
        software.forEach(softwareEntity -> softwareDTOs.add( new SoftwareDTO(softwareEntity, new Link(link.getHref() + "/" + softwareEntity.getId()).withSelfRel())));
        return new ResponseEntity<>(softwareDTOs, HttpStatus.OK);
    }

    @GetMapping(value = "api/software/workspace/{id}")
    public ResponseEntity<List<SoftwareDTO>> getSoftwareByWorkspace(@PathVariable Integer id) throws SQLException, NoSuchWorkspaceException, NoSuchDesktopsException {
        Set<SoftwareEntity> software = softwareService.getSoftwareByDesktopssId(id);
        var link = linkTo(methodOn(SoftwareControllerImpl.class).getSoftwareByWorkspace(id)).withSelfRel();

        List<SoftwareDTO> softwareDTOs = new ArrayList<>();
        software.forEach(softwareEntity ->
                softwareDTOs.add( new SoftwareDTO(softwareEntity, new Link(link.getHref() + "/" + softwareEntity.getId()).withSelfRel())));
        return new ResponseEntity<>(softwareDTOs, HttpStatus.OK);
    }
}
