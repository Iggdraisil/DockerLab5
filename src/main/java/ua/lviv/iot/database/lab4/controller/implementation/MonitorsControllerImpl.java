package ua.lviv.iot.database.lab4.controller.implementation;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.lviv.iot.database.lab4.DTO.MonitorsDTO;
import ua.lviv.iot.database.lab4.controller.MonitorsController;
import ua.lviv.iot.database.lab4.exceptions.ExistsWorkspaceForMonitorsException;
import ua.lviv.iot.database.lab4.exceptions.NoSuchMonitorsException;
import ua.lviv.iot.database.lab4.exceptions.NoSuchWorkspaceException;
import ua.lviv.iot.database.lab4.model.MonitorsEntity;
import ua.lviv.iot.database.lab4.service.MonitorsService;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
public class MonitorsControllerImpl implements MonitorsController {

    @Autowired
    MonitorsService monitorsService;

    @Override
    @PostMapping(value = "api/monitors")
    public ResponseEntity<MonitorsDTO> create(@RequestBody MonitorsEntity entity) throws SQLException, NoSuchMonitorsException {
        monitorsService.create(entity);
        var link = linkTo(methodOn(MonitorsControllerImpl.class).find(entity.getId())).withSelfRel();
        var monitorsDTO = new MonitorsDTO(entity, link);
        return new ResponseEntity<>(monitorsDTO, HttpStatus.OK);

    }

    @Override
    @PutMapping(value = "api/monitors/{id}")
    public ResponseEntity<MonitorsDTO> update(@PathVariable Integer id, @RequestBody MonitorsEntity monitor) throws SQLException, NoSuchMonitorsException {
        monitorsService.update(monitor, id);
        var monitorp = monitorsService.findById(id);
        var link = linkTo(methodOn(MonitorsControllerImpl.class).find(id)).withSelfRel();
        var monitorsDTO = new MonitorsDTO(monitorp, link);
        return new ResponseEntity<>(monitorsDTO, HttpStatus.OK);
    }

    @Override
    @DeleteMapping(value = "api/monitors/{id}")
    public ResponseEntity delete(@PathVariable Integer id) throws SQLException, NoSuchMonitorsException, ExistsWorkspaceForMonitorsException {
        monitorsService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @Override
    @GetMapping(value = "api/monitors/{id}")
    public ResponseEntity<MonitorsDTO> find(@PathVariable Integer id) throws SQLException, NoSuchMonitorsException {
        var monitor = monitorsService.findById(id);
        var link = linkTo(methodOn(MonitorsControllerImpl.class).find(id)).withSelfRel();

        var monitorsDTO = new MonitorsDTO(monitor, link);

        return new ResponseEntity<>(monitorsDTO, HttpStatus.OK);
    }

    @Override
    @GetMapping(value = "api/monitors")
    public ResponseEntity<List<MonitorsDTO>> findAll() throws SQLException {
        List<MonitorsEntity> monitors = monitorsService.findAll();
        var link = linkTo(methodOn(MonitorsControllerImpl.class).findAll()).withSelfRel();
        List<MonitorsDTO> monitorDTOs = new ArrayList<>();
        monitors.forEach(monitorsEntity -> monitorDTOs.add( new MonitorsDTO(monitorsEntity, new Link(link.getHref() + "/" + monitorsEntity.getId()).withSelfRel())));
        return new ResponseEntity<>(monitorDTOs, HttpStatus.OK);
    }

    @GetMapping(value = "api/monitors/workspace/{id}")
    public ResponseEntity<List<MonitorsDTO>> getMonitorsByWorkspace(@PathVariable Integer id) throws SQLException, NoSuchWorkspaceException {
        var monitors = monitorsService.getMonitorsByWorkspaceId(id);
        var link = linkTo(methodOn(MonitorsControllerImpl.class).getMonitorsByWorkspace(id)).withSelfRel();

        List<MonitorsDTO> monitorDTOs = new ArrayList<>();
        monitors.forEach(monitorsEntity -> monitorDTOs.add( new MonitorsDTO(monitorsEntity, new Link(link.getHref() + "/" + monitorsEntity.getId()).withSelfRel())));
        return new ResponseEntity<>(monitorDTOs, HttpStatus.OK);
    }
}
