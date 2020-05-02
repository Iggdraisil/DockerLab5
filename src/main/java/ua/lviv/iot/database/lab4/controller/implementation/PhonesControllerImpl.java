package ua.lviv.iot.database.lab4.controller.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.lviv.iot.database.lab4.DTO.MonitorsDTO;
import ua.lviv.iot.database.lab4.DTO.PhonesDTO;
import ua.lviv.iot.database.lab4.controller.PhonesController;
import ua.lviv.iot.database.lab4.exceptions.ExistsWorkspaceForPhonesException;
import ua.lviv.iot.database.lab4.exceptions.NoSuchPhonesException;
import ua.lviv.iot.database.lab4.exceptions.NoSuchWorkspaceException;
import ua.lviv.iot.database.lab4.model.PhonesEntity;
import ua.lviv.iot.database.lab4.model.PhonesEntity;
import ua.lviv.iot.database.lab4.service.PhonesService;
import ua.lviv.iot.database.lab4.service.PhonesService;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
public class PhonesControllerImpl implements PhonesController {
    @Autowired
    PhonesService phonesService;

    @Override
    @PostMapping("api/phones")
    public ResponseEntity<PhonesDTO> create(@RequestBody PhonesEntity entity) throws  NoSuchPhonesException {
        phonesService.create(entity);
        var link = linkTo(methodOn(PhonesControllerImpl.class).create(entity)).withSelfRel();
        var phones = phonesService.findAll();
        var phonesDTO = new PhonesDTO(phones.get(phones.size() - 1), link);
        return new ResponseEntity<>(phonesDTO, HttpStatus.OK);

    }

    @Override
    @PutMapping(value = "api/phones/{id}")
    public ResponseEntity<PhonesDTO> update(@PathVariable Integer id, @RequestBody PhonesEntity phone) throws  NoSuchPhonesException {
        phonesService.update(phone, id);
        var uPhone = phonesService.findById(id);
        var link = linkTo(methodOn(PhonesControllerImpl.class).update(id, phone)).withSelfRel();
        var phonesDTO = new PhonesDTO(uPhone, link);
        return new ResponseEntity<>(phonesDTO, HttpStatus.OK);
    }

    @Override
    @DeleteMapping(value = "api/phones/{id}")
    public ResponseEntity delete(@PathVariable Integer id) throws NoSuchPhonesException {
        phonesService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @Override
    @GetMapping(value = "api/phones/{id}")
    public ResponseEntity<PhonesDTO> find(@PathVariable Integer id) throws  NoSuchPhonesException {
        var phone = phonesService.findById(id);
        var link = linkTo(methodOn(PhonesControllerImpl.class).find(id)).withSelfRel();

        var phonesDTO = new PhonesDTO(phone, link);

        return new ResponseEntity<>(phonesDTO, HttpStatus.OK);
    }

    @Override
    @GetMapping(value = "api/phones")
    public ResponseEntity<List<PhonesDTO>> findAll() {
        List<PhonesEntity> phones = phonesService.findAll();
        var link = linkTo(methodOn(PhonesControllerImpl.class).findAll()).withSelfRel();
        List<PhonesDTO> phoneDTOs = new ArrayList<>();
        phones.forEach(phonesEntity -> phoneDTOs.add( new PhonesDTO(phonesEntity, new Link(link.getHref() + "/" + phonesEntity.getNumber()).withSelfRel())));
        return new ResponseEntity<>(phoneDTOs, HttpStatus.OK);
    }

//    @GetMapping(value = "api/phones/workspace/{id}")
//    public ResponseEntity<List<PhonesDTO>> getPhonesByWorkspace(@PathVariable Integer id) throws NoSuchWorkspaceException {
//        Set<PhonesEntity> phones = phonesService.getPhonesByWorkspaceId(id);
//        var link = linkTo(methodOn(PhonesControllerImpl.class).getPhonesByWorkspace(id)).withSelfRel();
//
//        List<PhonesDTO> phoneDTOs = new ArrayList<>();
//        phones.forEach(phonesEntity ->
//                phoneDTOs.add( new PhonesDTO(phonesEntity, new Link(link.getHref() + "/" + phonesEntity.getNumber()).withSelfRel())));
//        return new ResponseEntity<>(phoneDTOs, HttpStatus.OK);
//    }
}
