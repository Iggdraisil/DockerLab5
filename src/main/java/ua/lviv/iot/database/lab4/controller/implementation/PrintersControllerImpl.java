package ua.lviv.iot.database.lab4.controller.implementation;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.lviv.iot.database.lab4.DTO.PrintersDTO;
import ua.lviv.iot.database.lab4.controller.PrintersController;
import ua.lviv.iot.database.lab4.exceptions.ExistsPrintersForWorkspaceException;
import ua.lviv.iot.database.lab4.exceptions.ExistsWorkspaceForPrintersException;
import ua.lviv.iot.database.lab4.exceptions.NoSuchPrintersException;
import ua.lviv.iot.database.lab4.exceptions.NoSuchWorkspaceException;
import ua.lviv.iot.database.lab4.model.PrintersEntity;
import ua.lviv.iot.database.lab4.model.PrintersEntity;
import ua.lviv.iot.database.lab4.service.PrintersService;
import ua.lviv.iot.database.lab4.service.PrintersService;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
public class PrintersControllerImpl implements PrintersController {
    @Autowired
    PrintersService printersService;

    @Override
    @PostMapping("api/printers")
    public ResponseEntity<PrintersDTO> create(@RequestBody PrintersEntity entity) {
        entity.setId(0);
        printersService.create(entity);
        var link = linkTo(methodOn(PrintersControllerImpl.class).create(entity)).withSelfRel();
        var printers = printersService.findAll();
        var printersDTO = printers.get(printers.size() - 1);
        return new ResponseEntity<>(new PrintersDTO(printersDTO, link), HttpStatus.OK);

    }

    @Override
    @PutMapping(value = "api/printers/{id}")
    public ResponseEntity<PrintersDTO> update(@PathVariable Integer id, @RequestBody PrintersEntity printer) throws NoSuchPrintersException {
        printersService.update(printer, id);
        var printerp = printersService.findById(id);
        var link = linkTo(methodOn(PrintersControllerImpl.class).update(id, printer)).withSelfRel();
        var printersDTO = new PrintersDTO(printerp, link);
        return new ResponseEntity<>(printersDTO, HttpStatus.OK);
    }

    @Override
    @DeleteMapping(value = "api/printers/{id}")
    public ResponseEntity delete(@PathVariable Integer id) throws NoSuchPrintersException, ExistsPrintersForWorkspaceException {
        printersService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @Override
    @GetMapping(value = "api/printers/{id}")
    public ResponseEntity<PrintersDTO> find(@PathVariable Integer id) throws NoSuchPrintersException {
        var printer = printersService.findById(id);
        var link = linkTo(methodOn(PrintersControllerImpl.class).find(id)).withSelfRel();

        var printersDTO = new PrintersDTO(printer, link);

        return new ResponseEntity<>(printersDTO, HttpStatus.OK);
    }

    @Override
    @GetMapping(value = "api/printers")
    public ResponseEntity<List<PrintersDTO>> findAll() {
        List<PrintersEntity> printers = printersService.findAll();
        var link = linkTo(methodOn(PrintersControllerImpl.class).findAll()).withSelfRel();
        List<PrintersDTO> printerDTOs = new ArrayList<>();
        printers.forEach(printersEntity -> printerDTOs.add( new PrintersDTO(printersEntity, new Link(link.getHref() + "/" + printersEntity.getId()).withSelfRel())));
        return new ResponseEntity<>(printerDTOs, HttpStatus.OK);
    }

    @GetMapping(value = "api/printers/workspace/{id}")
    public ResponseEntity<List<PrintersDTO>> getPrintersByWorkspace(@PathVariable Integer id) throws NoSuchWorkspaceException {
        Set<PrintersEntity> printers = printersService.getPrintersByWorkspacesId(id);
        var link = linkTo(methodOn(PrintersControllerImpl.class).getPrintersByWorkspace(id)).withSelfRel();

        List<PrintersDTO> printerDTOs = new ArrayList<>();
        printers.forEach(printersEntity ->
                printerDTOs.add( new PrintersDTO(printersEntity, new Link(link.getHref() + "/" + printersEntity.getId()).withSelfRel())));
        return new ResponseEntity<>(printerDTOs, HttpStatus.OK);
    }
}
