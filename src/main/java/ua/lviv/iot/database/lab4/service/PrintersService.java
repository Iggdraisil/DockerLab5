package ua.lviv.iot.database.lab4.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.lviv.iot.database.lab4.exceptions.ExistsPrintersForWorkspaceException;
import ua.lviv.iot.database.lab4.exceptions.NoSuchPrintersException;
import ua.lviv.iot.database.lab4.exceptions.NoSuchWorkspaceException;
import ua.lviv.iot.database.lab4.model.PrintersEntity;
import ua.lviv.iot.database.lab4.model.PrintersEntity;
import ua.lviv.iot.database.lab4.repository.PrintersRepository;
import ua.lviv.iot.database.lab4.repository.WorkspaceRepository;

import javax.transaction.Transactional;
import java.sql.SQLException;
import java.util.List;
import java.util.Set;

@Service
public class PrintersService {
    @Autowired
    PrintersRepository printersRepository;

    @Autowired
    WorkspaceRepository workspaceRepository;

    public Set<PrintersEntity> getPrintersByWorkspacesId(Integer workspaceId) throws NoSuchWorkspaceException {
//        Workspace workspace = workspaceRepository.findOne(workspace_id);//1.5.9
        var workspace = workspaceRepository.findById(workspaceId).get();//2.0.0.M7
        if (workspace == null) throw new NoSuchWorkspaceException();
        return workspace.getPrintersById();
    }

    public PrintersEntity findById(Integer printers_id) throws NoSuchPrintersException {
//        Printers printers = printersRepository.findOne(printers_id);//1.5.9
        var printers = printersRepository.findById(printers_id).get();//2.0.0.M7
        if (printers == null) throw new NoSuchPrintersException();
        return printers;
    }

    public List<PrintersEntity> findAll() {
        return printersRepository.findAll();
    }

    @Transactional
    public void create(PrintersEntity printers) {
        printersRepository.save(printers);
    }

    @Transactional
    public void update(PrintersEntity uPrinters, Integer printers_id) throws NoSuchPrintersException {
//        Printers printers = printersRepository.findOne(printers_id);//1.5.9
        var printers = printersRepository.findById(printers_id).get();//2.0.0.M7
        if (printers == null) throw new NoSuchPrintersException();
        //update
        printers.setBrand(uPrinters.getBrand());
        printers.setModel(uPrinters.getModel());
        printers.setType(uPrinters.getType());
    }

    @Transactional
    public void delete(Integer printers_id) throws NoSuchPrintersException, ExistsPrintersForWorkspaceException {
//        Printers printers = printersRepository.findOne(printers_id);//1.5.9
        var printers = printersRepository.findById(printers_id).get();//2.0.0.M7

        if (printers == null) throw new NoSuchPrintersException();
        if (printers.getWorkspacesById().size() != 0) throw new ExistsPrintersForWorkspaceException();
        printersRepository.delete(printers);
    }
}
