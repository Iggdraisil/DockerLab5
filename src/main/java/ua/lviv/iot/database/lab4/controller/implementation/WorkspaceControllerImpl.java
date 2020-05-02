package ua.lviv.iot.database.lab4.controller.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.lviv.iot.database.lab4.DTO.WorkspaceDTO;
import ua.lviv.iot.database.lab4.DTO.WorkspaceDTO;
import ua.lviv.iot.database.lab4.controller.WorkspaceController;
import ua.lviv.iot.database.lab4.exceptions.ExistsWorkspaceForWorkspaceException;
import ua.lviv.iot.database.lab4.exceptions.NoSuchWorkspaceException;
import ua.lviv.iot.database.lab4.exceptions.NoSuchWorkspaceException;
import ua.lviv.iot.database.lab4.model.WorkspaceEntity;
import ua.lviv.iot.database.lab4.model.WorkspaceEntity;
import ua.lviv.iot.database.lab4.service.WorkspaceService;
import ua.lviv.iot.database.lab4.service.WorkspaceService;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
public class WorkspaceControllerImpl implements WorkspaceController {
    @Autowired
    WorkspaceService workspaceService;

    @Override
    @PostMapping("api/workspace")
    public ResponseEntity<WorkspaceDTO> create(@RequestBody WorkspaceEntity entity) {
        entity.setId(0);
        workspaceService.create(entity);
        var link = linkTo(methodOn(WorkspaceControllerImpl.class).create(entity)).withSelfRel();
        var workspace = workspaceService.findAll();
        var workspaceDTO = new WorkspaceDTO(workspace.get(workspace.size() - 1), link);
        return new ResponseEntity<>(workspaceDTO, HttpStatus.OK);

    }

    @Override
    @PutMapping(value = "api/workspace/{id}")
    public ResponseEntity<WorkspaceDTO> update(@PathVariable Integer id, @RequestBody WorkspaceEntity workspace) throws NoSuchWorkspaceException {
        workspaceService.update(workspace, id);
        var workspacep = workspaceService.findById(id);
        var link = linkTo(methodOn(WorkspaceControllerImpl.class).update(id, workspace)).withSelfRel();
        var workspaceDTO = new WorkspaceDTO(workspacep, link);
        return new ResponseEntity<>(workspaceDTO, HttpStatus.OK);
    }

    @Override
    @DeleteMapping(value = "api/workspace/{id}")
    public ResponseEntity delete(@PathVariable Integer id) throws NoSuchWorkspaceException, ExistsWorkspaceForWorkspaceException {
        workspaceService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @Override
    @GetMapping(value = "api/workspace/{id}")
    public ResponseEntity<WorkspaceDTO> find(@PathVariable Integer id) throws SQLException, NoSuchWorkspaceException {
        var workspace = workspaceService.findById(id);
        var link = linkTo(methodOn(WorkspaceControllerImpl.class).find(id)).withSelfRel();

        var workspaceDTO = new WorkspaceDTO(workspace, link);

        return new ResponseEntity<>(workspaceDTO, HttpStatus.OK);
    }

    @Override
    @GetMapping(value = "api/workspace")
    public ResponseEntity<List<WorkspaceDTO>> findAll() throws SQLException {
        List<WorkspaceEntity> workspace = workspaceService.findAll();
        var link = linkTo(methodOn(WorkspaceControllerImpl.class).findAll()).withSelfRel();
        List<WorkspaceDTO> workspaceDTOs = new ArrayList<>();
        workspace.forEach(workspaceEntity -> workspaceDTOs.add( new WorkspaceDTO(workspaceEntity, new Link(link.getHref() + "/" + workspaceEntity.getId()).withSelfRel())));
        return new ResponseEntity<>(workspaceDTOs, HttpStatus.OK);
    }

    @GetMapping(value = "api/workspace/desktops/{id}")
    public ResponseEntity<List<WorkspaceDTO>> getWorkspaceByDesktop(@PathVariable Integer id) throws SQLException, NoSuchWorkspaceException {
        Set<WorkspaceEntity> workspace = workspaceService.getWorkspaceByDesktopId(id);
        var link = linkTo(methodOn(WorkspaceControllerImpl.class).getWorkspaceByDesktop(id)).withSelfRel();

        List<WorkspaceDTO> workspaceDTOs = new ArrayList<>();
        workspace.forEach(workspaceEntity ->
                workspaceDTOs.add( new WorkspaceDTO(workspaceEntity, new Link(link.getHref() + "/" + workspaceEntity.getId()).withSelfRel())));
        return new ResponseEntity<>(workspaceDTOs, HttpStatus.OK);
    }
    
    @GetMapping(value = "api/workspace/workspace/{id}")
    public ResponseEntity<List<WorkspaceDTO>> getWorkspaceByMonitor(@PathVariable Integer id) throws SQLException, NoSuchWorkspaceException {
        Set<WorkspaceEntity> workspace = workspaceService.getWorkspaceByMonitorId(id);
        var link = linkTo(methodOn(WorkspaceControllerImpl.class).getWorkspaceByMonitor(id)).withSelfRel();

        List<WorkspaceDTO> workspaceDTOs = new ArrayList<>();
        workspace.forEach(workspaceEntity ->
                workspaceDTOs.add( new WorkspaceDTO(workspaceEntity, new Link(link.getHref() + "/" + workspaceEntity.getId()).withSelfRel())));
        return new ResponseEntity<>(workspaceDTOs, HttpStatus.OK);
    }
}
