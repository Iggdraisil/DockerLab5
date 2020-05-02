package ua.lviv.iot.database.lab4.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.lviv.iot.database.lab4.exceptions.NoSuchRoutersException;
import ua.lviv.iot.database.lab4.exceptions.NoSuchOfficeException;
import ua.lviv.iot.database.lab4.model.RoutersEntity;
import ua.lviv.iot.database.lab4.model.RoutersEntity;
import ua.lviv.iot.database.lab4.repository.RoutersRepository;
import ua.lviv.iot.database.lab4.repository.OfficeRepository;

import javax.transaction.Transactional;
import java.sql.SQLException;
import java.util.List;
import java.util.Set;

@Service
public class RoutersService {
    @Autowired
    RoutersRepository routersRepository;

    @Autowired
    OfficeRepository officeRepository;

    public RoutersEntity findById(String routers_id) throws NoSuchRoutersException {
//        Routers routers = routersRepository.findOne(routers_id);//1.5.9
        var routers = routersRepository.findById(routers_id).get();//2.0.0.M7
        if (routers == null) throw new NoSuchRoutersException();
        return routers;
    }

    public List<RoutersEntity> findAll() {
        return routersRepository.findAll();
    }

    @Transactional
    public void create(RoutersEntity routers) {
        routersRepository.save(routers);
    }

    @Transactional
    public void update(RoutersEntity uRouters, String routers_id) throws NoSuchRoutersException {
//        Routers routers = routersRepository.findOne(routers_id);//1.5.9
        var routers = routersRepository.findById(routers_id).get();//2.0.0.M7
        if (routers == null) throw new NoSuchRoutersException();
        //update
        routers.setOfficeId(uRouters.getOfficeId());
    }

    @Transactional
    public void delete(String routers_id) throws NoSuchRoutersException {
//        Routers routers = routersRepository.findOne(routers_id);//1.5.9
        var routers = routersRepository.findById(routers_id).get();//2.0.0.M7

        if (routers == null) throw new NoSuchRoutersException();
        routersRepository.delete(routers);
    }
}
