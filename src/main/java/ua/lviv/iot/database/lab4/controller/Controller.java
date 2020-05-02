package ua.lviv.iot.database.lab4.controller;

import org.springframework.http.ResponseEntity;
import ua.lviv.iot.database.lab4.DTO.DesktopsDTO;
import ua.lviv.iot.database.lab4.exceptions.*;

import java.sql.SQLException;
import java.util.List;

public interface Controller<EN, ID, T> {
    ResponseEntity<T> create(EN entity) throws SQLException, NoSuchDesktopsException, NoSuchMonitorsException, NoSuchWorkspaceException, NoSuchOfficeException, NoSuchPhonesException, NoSuchPrintersException, NoSuchRoutersException, NoSuchSoftwareException, NoSuchWorkersException;
    ResponseEntity<T> update(ID id, EN entity) throws SQLException, NoSuchDesktopsException, NoSuchMonitorsException, NoSuchWorkspaceException, NoSuchOfficeException, NoSuchPhonesException, NoSuchPrintersException, NoSuchRoutersException, NoSuchSoftwareException, NoSuchWorkersException;
    ResponseEntity delete(ID id) throws SQLException, NoSuchDesktopsException, ExistsWorkspaceForDesktopsException, NoSuchMonitorsException, ExistsWorkspaceForMonitorsException, NoSuchWorkspaceException, ExistsWorkspaceForWorkspaceException, NoSuchOfficeException, NoSuchPhonesException, NoSuchPrintersException, NoSuchSoftwareException, NoSuchWorkersException, ExistsOfficeForOfficeException, ExistsWorkspaceForPhonesException, ExistsWorkspaceForPrintersException, ExistsPrintersForWorkspaceException, NoSuchRoutersException, ExistsWorkspaceForRoutersException, ExistsSoftwareForDesktopsException, ExistsWorkspaceForWorkersException, ExistsWorkersForOfficeException;
    ResponseEntity<T> find(ID id) throws SQLException, NoSuchDesktopsException, NoSuchMonitorsException, NoSuchWorkspaceException, NoSuchOfficeException, NoSuchPhonesException, NoSuchPrintersException, NoSuchRoutersException, NoSuchSoftwareException, NoSuchWorkersException;
    ResponseEntity<List<T>> findAll() throws SQLException;
}
