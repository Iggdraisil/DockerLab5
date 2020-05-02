package ua.lviv.iot.database.lab4.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.lviv.iot.database.lab4.exceptions.NoSuchPhonesException;
import ua.lviv.iot.database.lab4.exceptions.NoSuchWorkspaceException;
import ua.lviv.iot.database.lab4.model.OfficeEntity;
import ua.lviv.iot.database.lab4.model.PhonesEntity;
import ua.lviv.iot.database.lab4.model.PhonesEntity;
import ua.lviv.iot.database.lab4.repository.PhonesRepository;
import ua.lviv.iot.database.lab4.repository.WorkspaceRepository;

import javax.transaction.Transactional;
import java.sql.SQLException;
import java.util.List;
import java.util.Set;

@Service
public class PhonesService {
    @Autowired
    PhonesRepository phonesRepository;

    public PhonesEntity findById(Integer phones_id) throws NoSuchPhonesException {
//        Phones phones = phonesRepository.findOne(phones_id);//1.5.9
        var phones = phonesRepository.findById(phones_id).get();//2.0.0.M7
        if (phones == null) throw new NoSuchPhonesException();
        return phones;
    }

    public List<PhonesEntity> findAll() {
        return phonesRepository.findAll();
    }

    @Transactional
    public void create(PhonesEntity phones) {
        phonesRepository.save(phones);
    }

    @Transactional
    public void update(PhonesEntity uPhones, Integer phones_id) throws NoSuchPhonesException {
//        Phones phones = phonesRepository.findOne(phones_id);//1.5.9
        var phones = phonesRepository.findById(phones_id).get();//2.0.0.M7
        if (phones == null) throw new NoSuchPhonesException();
        //update
        phones.setPrice(uPhones.getPrice());
        phones.setWorkspace(uPhones.getWorkspace());
    }

    @Transactional
    public void delete(Integer phones_id) throws NoSuchPhonesException {
//        Phones phones = phonesRepository.findOne(phones_id);//1.5.9
        var phones = phonesRepository.findById(phones_id).get();//2.0.0.M7

        if (phones == null) throw new NoSuchPhonesException();
        phonesRepository.delete(phones);
    }
}
