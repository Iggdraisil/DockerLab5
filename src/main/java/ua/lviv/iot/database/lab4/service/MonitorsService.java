package ua.lviv.iot.database.lab4.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.lviv.iot.database.lab4.exceptions.ExistsWorkspaceForMonitorsException;
import ua.lviv.iot.database.lab4.exceptions.NoSuchMonitorsException;
import ua.lviv.iot.database.lab4.exceptions.NoSuchWorkspaceException;
import ua.lviv.iot.database.lab4.model.MonitorsEntity;
import ua.lviv.iot.database.lab4.repository.MonitorsRepository;
import ua.lviv.iot.database.lab4.repository.WorkspaceRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;

@Service
public class MonitorsService {
    @Autowired
    MonitorsRepository monitorRepository;

    @Autowired
    WorkspaceRepository workspaceRepository;

    public Set<MonitorsEntity> getMonitorsByWorkspaceId(Integer workspace_id) throws NoSuchWorkspaceException {
//        Workspace workspace = workspaceRepository.findOne(workspace_id);//1.5.9
        var workspace = workspaceRepository.findById(workspace_id).get();//2.0.0.M7
        if (workspace == null) throw new NoSuchWorkspaceException();
        return workspace.getWorkspaceHasMonitorsById();
    }

    public MonitorsEntity findById(Integer monitor_id) throws NoSuchMonitorsException {
//        Monitors monitor = monitorRepository.findOne(monitor_id);//1.5.9
        var monitor = monitorRepository.findById(monitor_id).get();//2.0.0.M7
        if (monitor == null) throw new NoSuchMonitorsException();
        return monitor;
    }

    public List<MonitorsEntity> findAll() {
        return monitorRepository.findAll();
    }

    @Transactional
    public void create(MonitorsEntity monitor) {
        monitorRepository.save(monitor);
    }

    @Transactional
    public void update(MonitorsEntity uMonitors, Integer monitor_id) throws NoSuchMonitorsException {
//        Monitors monitor = monitorRepository.findOne(monitor_id);//1.5.9
        var monitor = monitorRepository.findById(monitor_id).get();//2.0.0.M7
        if (monitor == null) throw new NoSuchMonitorsException();
        //update
        monitor.setMatrixType(uMonitors.getMatrixType());
        monitor.setResolution(uMonitors.getResolution());
        monitor.setPrice(uMonitors.getPrice());
        monitor.setSrgbCoverage(uMonitors.getSrgbCoverage());
    }

    @Transactional
    public void delete(Integer monitor_id) throws NoSuchMonitorsException, ExistsWorkspaceForMonitorsException {
//        Monitors monitor = monitorRepository.findOne(monitor_id);//1.5.9
        var monitor = monitorRepository.findById(monitor_id).get();//2.0.0.M7

        if (monitor == null) throw new NoSuchMonitorsException();
//        if (monitor.getWorkspaceHasMonitorsById().size() != 0) throw new ExistsWorkspaceForMonitorsException();
        monitorRepository.delete(monitor);
    }
}
