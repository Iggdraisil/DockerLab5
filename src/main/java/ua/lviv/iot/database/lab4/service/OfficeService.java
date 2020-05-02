package ua.lviv.iot.database.lab4.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.lviv.iot.database.lab4.exceptions.ExistsOfficeForOfficeException;
import ua.lviv.iot.database.lab4.exceptions.NoSuchOfficeException;
import ua.lviv.iot.database.lab4.exceptions.NoSuchWorkspaceException;
import ua.lviv.iot.database.lab4.model.OfficeEntity;
import ua.lviv.iot.database.lab4.repository.OfficeRepository;
import ua.lviv.iot.database.lab4.repository.WorkersRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;
@Service
public class OfficeService {
    @Autowired
    OfficeRepository officeRepository;

    @Autowired
    WorkersRepository workersRepository;

    public Set<OfficeEntity> getOfficesByWorkersId(Integer workerId) throws NoSuchWorkspaceException {
//        Workspace workspace = workspaceRepository.findOne(workspace_id);//1.5.9
        var worker = workersRepository.findById(workerId).get();//2.0.0.M7
        if (worker == null) throw new NoSuchWorkspaceException();
        return worker.getWorkersByOfficeById();
    }

    public OfficeEntity findById(Integer office_id) throws NoSuchOfficeException {
//        Office office = officeRepository.findOne(office_id);//1.5.9
        var office = officeRepository.findById(office_id).get();//2.0.0.M7
        if (office == null) throw new NoSuchOfficeException();
        return office;
    }

    public List<OfficeEntity> findAll() {
        return officeRepository.findAll();
    }

    @Transactional
    public void create(OfficeEntity office) {
        officeRepository.save(office);
    }

    @Transactional
    public void update(OfficeEntity uOffice, Integer office_id) throws NoSuchOfficeException {
//        Office office = officeRepository.findOne(office_id);//1.5.9
        var office = officeRepository.findById(office_id).get();//2.0.0.M7
        if (office == null) throw new NoSuchOfficeException();
        //update
        office.setAddress(uOffice.getAddress());
        office.setName(uOffice.getName());
    }

    @Transactional
    public void delete(Integer office_id) throws NoSuchOfficeException, ExistsOfficeForOfficeException {
//        Office office = officeRepository.findOne(office_id);//1.5.9
        var office = officeRepository.findById(office_id).get();//2.0.0.M7

        if (office == null) throw new NoSuchOfficeException();
        if (office.getWorkersHasOfficesById().size() != 0) throw new ExistsOfficeForOfficeException();
        officeRepository.delete(office);
    }
}
