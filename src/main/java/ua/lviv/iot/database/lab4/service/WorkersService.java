package ua.lviv.iot.database.lab4.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.lviv.iot.database.lab4.exceptions.ExistsWorkersForOfficeException;
import ua.lviv.iot.database.lab4.exceptions.NoSuchOfficeException;
import ua.lviv.iot.database.lab4.exceptions.NoSuchWorkersException;
import ua.lviv.iot.database.lab4.model.WorkersEntity;
import ua.lviv.iot.database.lab4.repository.OfficeRepository;
import ua.lviv.iot.database.lab4.repository.WorkersRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;

@Service
public class WorkersService {
    @Autowired
    WorkersRepository workersRepository;

    @Autowired
    OfficeRepository officeRepository;

    public Set<WorkersEntity> getWorkersByOfficesId(Integer officeId) throws NoSuchOfficeException {
//        Office office = officeRepository.findOne(office_id);//1.5.9
        var office = officeRepository.findById(officeId).get();//2.0.0.M7
        if (office == null) throw new NoSuchOfficeException();
        return office.getWorkersHasOfficesById();
    }

    public WorkersEntity findById(Integer workers_id) throws NoSuchWorkersException {
//        Workers workers = workersRepository.findOne(workers_id);//1.5.9
        var workers = workersRepository.findById(workers_id).get();//2.0.0.M7
        if (workers == null) throw new NoSuchWorkersException();
        return workers;
    }

    public List<WorkersEntity> findAll() {
        return workersRepository.findAll();
    }

    @Transactional
    public void create(WorkersEntity workers) {
        workersRepository.save(workers);
    }

    @Transactional
    public void update(WorkersEntity uWorkers, Integer workers_id) throws NoSuchWorkersException {
//        Workers workers = workersRepository.findOne(workers_id);//1.5.9
        var workers = workersRepository.findById(workers_id).get();//2.0.0.M7
        if (workers == null) throw new NoSuchWorkersException();
        //update
        workers.setName(uWorkers.getName());
        workers.setSurname(uWorkers.getSurname());
    }

    @Transactional
    public void delete(Integer workers_id) throws NoSuchWorkersException, ExistsWorkersForOfficeException {
//        Workers workers = workersRepository.findOne(workers_id);//1.5.9
        var workers = workersRepository.findById(workers_id).get();//2.0.0.M7

        if (workers == null) throw new NoSuchWorkersException();
        if (workers.getWorkersByOfficeById().size() != 0) throw new ExistsWorkersForOfficeException();
        workersRepository.delete(workers);
    }
}
