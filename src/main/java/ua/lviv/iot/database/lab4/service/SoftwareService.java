package ua.lviv.iot.database.lab4.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.lviv.iot.database.lab4.exceptions.ExistsSoftwareForDesktopsException;
import ua.lviv.iot.database.lab4.exceptions.NoSuchSoftwareException;
import ua.lviv.iot.database.lab4.exceptions.NoSuchDesktopsException;
import ua.lviv.iot.database.lab4.model.SoftwareEntity;
import ua.lviv.iot.database.lab4.repository.SoftwareRepository;
import ua.lviv.iot.database.lab4.repository.DesktopsRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;

@Service
public class SoftwareService {
    @Autowired
    SoftwareRepository softwareRepository;

    @Autowired
    DesktopsRepository desktopsRepository;

    public Set<SoftwareEntity> getSoftwareByDesktopssId(Integer desktopsId) throws NoSuchDesktopsException {
//        Desktops desktops = desktopsRepository.findOne(desktops_id);//1.5.9
        var desktops = desktopsRepository.findById(desktopsId).get();//2.0.0.M7
        if (desktops == null) throw new NoSuchDesktopsException();
        return desktops.getDesktopsHasSoftwareById();
    }

    public SoftwareEntity findById(Integer software_id) throws NoSuchSoftwareException {
//        Software software = softwareRepository.findOne(software_id);//1.5.9
        var software = softwareRepository.findById(software_id).get();//2.0.0.M7
        if (software == null) throw new NoSuchSoftwareException();
        return software;
    }

    public List<SoftwareEntity> findAll() {
        return softwareRepository.findAll();
    }

    @Transactional
    public void create(SoftwareEntity software) {
        softwareRepository.save(software);
    }

    @Transactional
    public void update(SoftwareEntity uSoftware, Integer software_id) throws NoSuchSoftwareException {
//        Software software = softwareRepository.findOne(software_id);//1.5.9
        var software = softwareRepository.findById(software_id).get();//2.0.0.M7
        if (software == null) throw new NoSuchSoftwareException();
        //update
        software.setName(uSoftware.getName());
        software.setPrice(uSoftware.getPrice());
    }

    @Transactional
    public void delete(Integer software_id) throws NoSuchSoftwareException, ExistsSoftwareForDesktopsException {
//        Software software = softwareRepository.findOne(software_id);//1.5.9
        var software = softwareRepository.findById(software_id).get();//2.0.0.M7

        if (software == null) throw new NoSuchSoftwareException();
        if (software.getDesktopsById().size() != 0) throw new ExistsSoftwareForDesktopsException();
        softwareRepository.delete(software);
    }
}
