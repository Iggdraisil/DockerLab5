package ua.lviv.iot.database.lab4.controller.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.lviv.iot.database.lab4.DTO.RoutersDTO;
import ua.lviv.iot.database.lab4.controller.RoutersController;
import ua.lviv.iot.database.lab4.exceptions.*;
import ua.lviv.iot.database.lab4.exceptions.ExistsWorkspaceForRoutersException;
import ua.lviv.iot.database.lab4.model.RoutersEntity;
import ua.lviv.iot.database.lab4.model.RoutersEntity;
import ua.lviv.iot.database.lab4.service.RoutersService;
import ua.lviv.iot.database.lab4.service.RoutersService;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
public class RoutersControllerImpl implements RoutersController {
    @Autowired
    RoutersService routersService;

    @Override
    @PostMapping("api/routers")
    public ResponseEntity<RoutersDTO> create(@RequestBody RoutersEntity entity) throws  NoSuchRoutersException {
        routersService.create(entity);
        var link = linkTo(methodOn(RoutersControllerImpl.class).create(entity)).withSelfRel();
        var routersDTO = new RoutersDTO(entity, link);
        return new ResponseEntity<>(routersDTO, HttpStatus.OK);

    }

    @Override
    @PutMapping(value = "api/routers/{id}")
    public ResponseEntity<RoutersDTO> update(@PathVariable String id, @RequestBody RoutersEntity router) throws  NoSuchRoutersException{
        routersService.update(router, id);
        var routerp = routersService.findById(id);
        var link = linkTo(methodOn(RoutersControllerImpl.class).update(id, router)).withSelfRel();
        var routersDTO = new RoutersDTO(routerp, link);
        return new ResponseEntity<>(routersDTO, HttpStatus.OK);
    }

    @Override
    @DeleteMapping(value = "api/routers/{id}")
    public ResponseEntity delete(@PathVariable String id) throws  NoSuchRoutersException, ExistsWorkspaceForRoutersException {
        routersService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @Override
    @GetMapping(value = "api/routers/{id}")
    public ResponseEntity<RoutersDTO> find(@PathVariable String id) throws  NoSuchRoutersException {
        var router = routersService.findById(id);
        var link = linkTo(methodOn(RoutersControllerImpl.class).find(id)).withSelfRel();

        var routersDTO = new RoutersDTO(router, link);

        return new ResponseEntity<>(routersDTO, HttpStatus.OK);
    }

    @Override
    @GetMapping(value = "api/routers")
    public ResponseEntity<List<RoutersDTO>> findAll() {
        List<RoutersEntity> routers = routersService.findAll();
        var link = linkTo(methodOn(RoutersControllerImpl.class).findAll()).withSelfRel();
        List<RoutersDTO> routerDTOs = new ArrayList<>();
        routers.forEach(routersEntity -> routerDTOs.add( new RoutersDTO(routersEntity, new Link(link.getHref() + "/" + routersEntity.getIp()).withSelfRel())));
        return new ResponseEntity<>(routerDTOs, HttpStatus.OK);
    }

}
