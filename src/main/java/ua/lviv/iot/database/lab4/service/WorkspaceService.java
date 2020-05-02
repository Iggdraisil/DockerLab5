package ua.lviv.iot.database.lab4.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.lviv.iot.database.lab4.exceptions.ExistsWorkspaceForWorkspaceException;
import ua.lviv.iot.database.lab4.exceptions.NoSuchWorkspaceException;
import ua.lviv.iot.database.lab4.model.WorkspaceEntity;
import ua.lviv.iot.database.lab4.repository.DesktopsRepository;
import ua.lviv.iot.database.lab4.repository.MonitorsRepository;
import ua.lviv.iot.database.lab4.repository.WorkspaceRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;

@Service
public class WorkspaceService {
    @Autowired
    WorkspaceRepository workspaceRepository;

    @Autowired
    DesktopsRepository desktopsRepository;
    
    @Autowired
    MonitorsRepository monitorsRepository;

    public Set<WorkspaceEntity> getWorkspaceByDesktopId(Integer desktop_id) throws NoSuchWorkspaceException {
//        Workspace workspace = workspaceRepository.findOne(workspace_id);//1.5.9
        var desktop = desktopsRepository.findById(desktop_id).get();//2.0.0.M7
        if (desktop == null) throw new NoSuchWorkspaceException();
        return desktop.getWorkspaceHasDesktopsById();
    }

    public Set<WorkspaceEntity> getWorkspaceByMonitorId(Integer monitor_id) throws NoSuchWorkspaceException {
//        Workspace workspace = workspaceRepository.findOne(workspace_id);//1.5.9
        var monitor = monitorsRepository.findById(monitor_id).get();//2.0.0.M7
        if (monitor == null) throw new NoSuchWorkspaceException();
        return monitor.getWorkspaceHasMonitorsById();
    }

    public WorkspaceEntity findById(Integer workspace_id) throws NoSuchWorkspaceException {
//        Workspace workspace = workspaceRepository.findOne(workspace_id);//1.5.9
        var workspace = workspaceRepository.findById(workspace_id).get();//2.0.0.M7
        if (workspace == null) throw new NoSuchWorkspaceException();
        return workspace;
    }

    public List<WorkspaceEntity> findAll() {
        return workspaceRepository.findAll();
    }

    @Transactional
    public void create(WorkspaceEntity workspace) {
        workspaceRepository.save(workspace);
    }

    @Transactional
    public void update(WorkspaceEntity uWorkspace, Integer workspace_id) throws NoSuchWorkspaceException {
//        Workspace workspace = workspaceRepository.findOne(workspace_id);//1.5.9
        var workspace = workspaceRepository.findById(workspace_id).get();//2.0.0.M7
        if (workspace == null) throw new NoSuchWorkspaceException();
        //update
        workspace.setIp(uWorkspace.getIp());
        workspace.setOfficeId(uWorkspace.getOfficeId());
        workspace.setWorker(uWorkspace.getWorker());
    }

    @Transactional
    public void delete(Integer workspace_id) throws NoSuchWorkspaceException, ExistsWorkspaceForWorkspaceException {
//        Workspace workspace = workspaceRepository.findOne(workspace_id);//1.5.9
        var workspace = workspaceRepository.findById(workspace_id).get();//2.0.0.M7

        if (workspace == null) throw new NoSuchWorkspaceException();
//        if (workspace.getWorkspaceHasDesktopsById().size() != 0) throw new ExistsWorkspaceForWorkspaceException();
//        if (workspace.getWorkspaceHasMonitorsById().size() != 0) throw new ExistsWorkspaceForWorkspaceException();
        workspaceRepository.delete(workspace);
    }
}
