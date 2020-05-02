package ua.lviv.iot.database.lab4.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.lviv.iot.database.lab4.exceptions.ExistsWorkspaceForDesktopsException;
import ua.lviv.iot.database.lab4.exceptions.NoSuchDesktopsException;
import ua.lviv.iot.database.lab4.exceptions.NoSuchWorkspaceException;
import ua.lviv.iot.database.lab4.model.DesktopsEntity;
import ua.lviv.iot.database.lab4.repository.DesktopsRepository;
import ua.lviv.iot.database.lab4.repository.WorkspaceRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;

@Service
public class DesktopsService {
    @Autowired
    DesktopsRepository desktopRepository;

    @Autowired
    WorkspaceRepository workspaceRepository;

    public Set<DesktopsEntity> getDesktopsByWorkspaceId(Integer workspace_id) throws NoSuchWorkspaceException {
//        Workspace workspace = workspaceRepository.findOne(workspace_id);//1.5.9
        var workspace = workspaceRepository.findById(workspace_id).get();//2.0.0.M7
        if (workspace == null) throw new NoSuchWorkspaceException();
        return workspace.getWorkspaceHasDesktopsById();
    }

    public DesktopsEntity findById(Integer desktop_id) throws NoSuchDesktopsException {
//        Desktops desktop = desktopRepository.findOne(desktop_id);//1.5.9
        var desktop = desktopRepository.findById(desktop_id).get();//2.0.0.M7
        if (desktop == null) throw new NoSuchDesktopsException();
        return desktop;
    }

    public List<DesktopsEntity> findAll() {
        return desktopRepository.findAll();
    }

    @Transactional
    public void create(DesktopsEntity desktop) {
        desktopRepository.save(desktop);
    }

    @Transactional
    public void update(DesktopsEntity uDesktops, Integer desktop_id) throws NoSuchDesktopsException {
//        Desktops desktop = desktopRepository.findOne(desktop_id);//1.5.9
        var desktop = desktopRepository.findById(desktop_id).get();//2.0.0.M7
        if (desktop == null) throw new NoSuchDesktopsException();
        //update
        desktop.setMotherboardId(uDesktops.getMotherboardId());
        desktop.setName(uDesktops.getName());
        desktop.setPrice(uDesktops.getPrice());
        desktop.setRam(uDesktops.getRam());
        desktop.setStorage(uDesktops.getStorage());
        desktop.setType(uDesktops.getType());
        desktop.setVideoadapter(uDesktops.getVideoadapter());
    }

    @Transactional
    public void delete(Integer desktop_id) throws NoSuchDesktopsException, ExistsWorkspaceForDesktopsException {
//        Desktops desktop = desktopRepository.findOne(desktop_id);//1.5.9
        var desktop = desktopRepository.findById(desktop_id).get();//2.0.0.M7

        if (desktop == null) throw new NoSuchDesktopsException();
//        if (desktop.getWorkspaceHasDesktopsById().size() != 0) throw new ExistsWorkspaceForDesktopsException();
        desktopRepository.delete(desktop);
    }
}
