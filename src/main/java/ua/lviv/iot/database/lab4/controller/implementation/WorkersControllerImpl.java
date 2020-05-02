package ua.lviv.iot.database.lab4.controller.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.lviv.iot.database.lab4.DTO.WorkersDTO;
import ua.lviv.iot.database.lab4.DTO.WorkersDTO;
import ua.lviv.iot.database.lab4.controller.WorkersController;
import ua.lviv.iot.database.lab4.exceptions.*;
import ua.lviv.iot.database.lab4.model.WorkersEntity;
import ua.lviv.iot.database.lab4.model.WorkersEntity;
import ua.lviv.iot.database.lab4.service.WorkersService;
import ua.lviv.iot.database.lab4.service.WorkersService;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
public class WorkersControllerImpl implements WorkersController {
    @Autowired
    WorkersService workersService;

    @Override
    @PostMapping("api/workers")
    public ResponseEntity<WorkersDTO> create(@RequestBody WorkersEntity entity) throws NoSuchWorkersException {
        entity.setId(0);
        workersService.create(entity);
        var link = linkTo(methodOn(WorkersControllerImpl.class).create(entity)).withSelfRel();
        var workers = workersService.findAll();
        var workersDTO = new WorkersDTO(workers.get(workers.size() - 1), link);
        return new ResponseEntity<>(workersDTO, HttpStatus.OK);

    }

    @Override
    @PutMapping(value = "api/workers/{id}")
    public ResponseEntity<WorkersDTO> update(@PathVariable Integer id, @RequestBody WorkersEntity worker) throws NoSuchWorkersException {
        workersService.update(worker, id);
        var workerp = workersService.findById(id);
        var link = linkTo(methodOn(WorkersControllerImpl.class).update(id, worker)).withSelfRel();
        var workersDTO = new WorkersDTO(workerp, link);
        return new ResponseEntity<>(workersDTO, HttpStatus.OK);
    }

    @Override
    @DeleteMapping(value = "api/workers/{id}")
    public ResponseEntity delete(@PathVariable Integer id) throws NoSuchWorkersException, ExistsWorkersForOfficeException {
        workersService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @Override
    @GetMapping(value = "api/workers/{id}")
    public ResponseEntity<WorkersDTO> find(@PathVariable Integer id) throws NoSuchWorkersException {
        var worker = workersService.findById(id);
        var link = linkTo(methodOn(WorkersControllerImpl.class).find(id)).withSelfRel();

        var workersDTO = new WorkersDTO(worker, link);

        return new ResponseEntity<>(workersDTO, HttpStatus.OK);
    }

    @Override
    @GetMapping(value = "api/workers")
    public ResponseEntity<List<WorkersDTO>> findAll() {
        List<WorkersEntity> workers = workersService.findAll();
        var link = linkTo(methodOn(WorkersControllerImpl.class).findAll()).withSelfRel();
        List<WorkersDTO> workerDTOs = new ArrayList<>();
        workers.forEach(workersEntity -> workerDTOs.add( new WorkersDTO(workersEntity, new Link(link.getHref() + "/" + workersEntity.getId()).withSelfRel())));
        return new ResponseEntity<>(workerDTOs, HttpStatus.OK);
    }

    @GetMapping(value = "api/workers/workspace/{id}")
    public ResponseEntity<List<WorkersDTO>> getWorkersByWorkspace(@PathVariable Integer id) throws NoSuchOfficeException {
        Set<WorkersEntity> workers = workersService.getWorkersByOfficesId(id);
        var link = linkTo(methodOn(WorkersControllerImpl.class).getWorkersByWorkspace(id)).withSelfRel();

        List<WorkersDTO> workerDTOs = new ArrayList<>();
        workers.forEach(workersEntity ->
                workerDTOs.add( new WorkersDTO(workersEntity, new Link(link.getHref() + "/" + workersEntity.getId()).withSelfRel())));
        return new ResponseEntity<>(workerDTOs, HttpStatus.OK);
    }
}
