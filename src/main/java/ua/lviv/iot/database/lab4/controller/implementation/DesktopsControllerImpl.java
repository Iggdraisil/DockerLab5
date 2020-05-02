package ua.lviv.iot.database.lab4.controller.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.lviv.iot.database.lab4.DTO.DesktopsDTO;
import ua.lviv.iot.database.lab4.controller.DesktopsController;
import ua.lviv.iot.database.lab4.exceptions.ExistsWorkspaceForDesktopsException;
import ua.lviv.iot.database.lab4.exceptions.NoSuchDesktopsException;
import ua.lviv.iot.database.lab4.exceptions.NoSuchWorkspaceException;
import ua.lviv.iot.database.lab4.model.DesktopsEntity;
import ua.lviv.iot.database.lab4.service.DesktopsService;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
public class DesktopsControllerImpl implements DesktopsController {

    @Autowired
    DesktopsService desktopsService;

    @Override
    @PostMapping("api/desktops")
    public ResponseEntity<DesktopsDTO> create(@RequestBody DesktopsEntity entity) {
        entity.setId(0);
        desktopsService.create(entity);
        var link = linkTo(methodOn(DesktopsControllerImpl.class).create(entity)).withSelfRel();
        var desktops = desktopsService.findAll();
        var desktopsDTO = new DesktopsDTO(desktops.get(desktops.size() - 1), link);
        return new ResponseEntity<>(desktopsDTO, HttpStatus.OK);

    }

    @Override
    @PutMapping(value = "api/desktops/{id}")
    public ResponseEntity<DesktopsDTO> update(@PathVariable Integer id, @RequestBody DesktopsEntity desktop) throws SQLException, NoSuchDesktopsException {
       desktopsService.update(desktop, id);
       var desktopp = desktopsService.findById(id);
       var link = linkTo(methodOn(DesktopsControllerImpl.class).update(id, desktop)).withSelfRel();
       var desktopsDTO = new DesktopsDTO(desktopp, link);
       return new ResponseEntity<>(desktopsDTO, HttpStatus.OK);
    }

    @Override
    @DeleteMapping(value = "api/desktops/{id}")
    public ResponseEntity delete(@PathVariable Integer id) throws SQLException, NoSuchDesktopsException, ExistsWorkspaceForDesktopsException {
        desktopsService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @Override
    @GetMapping(value = "api/desktops/{id}")
    public ResponseEntity<DesktopsDTO> find(@PathVariable Integer id) throws SQLException, NoSuchDesktopsException {
        var desktop = desktopsService.findById(id);
        var link = linkTo(methodOn(DesktopsControllerImpl.class).find(id)).withSelfRel();

        var desktopsDTO = new DesktopsDTO(desktop, link);

        return new ResponseEntity<>(desktopsDTO, HttpStatus.OK);
    }

    @Override
    @GetMapping(value = "api/desktops")
    public ResponseEntity<List<DesktopsDTO>> findAll() throws SQLException {
        List<DesktopsEntity> desktops = desktopsService.findAll();
        var link = linkTo(methodOn(DesktopsControllerImpl.class).findAll()).withSelfRel();
        List<DesktopsDTO> desktopDTOs = new ArrayList<>();
        desktops.forEach(desktopsEntity -> desktopDTOs.add( new DesktopsDTO(desktopsEntity, new Link(link.getHref() + "/" + desktopsEntity.getId()).withSelfRel())));
        return new ResponseEntity<>(desktopDTOs, HttpStatus.OK);
    }

    @GetMapping(value = "api/desktops/workspace/{id}")
    public ResponseEntity<List<DesktopsDTO>> getDesktopsByWorkspace(@PathVariable Integer id) throws SQLException, NoSuchWorkspaceException {
        Set<DesktopsEntity> desktops = desktopsService.getDesktopsByWorkspaceId(id);
        var link = linkTo(methodOn(DesktopsControllerImpl.class).getDesktopsByWorkspace(id)).withSelfRel();

        List<DesktopsDTO> desktopDTOs = new ArrayList<>();
        desktops.forEach(desktopsEntity ->
                desktopDTOs.add( new DesktopsDTO(desktopsEntity, new Link(link.getHref() + "/" + desktopsEntity.getId()).withSelfRel())));
        return new ResponseEntity<>(desktopDTOs, HttpStatus.OK);
    }


}
