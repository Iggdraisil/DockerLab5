package ua.lviv.iot.database.lab4.controller;

import org.springframework.http.ResponseEntity;
import ua.lviv.iot.database.lab4.exceptions.*;

import java.sql.SQLException;
import java.util.List;

public interface Controller<EN, ID, T> {
    ResponseEntity<T> create(EN entity) throws SQLException, NoSuchDesktopsException, NoSuchMonitorsException, NoSuchWorkspaceException;
    ResponseEntity<T> update(ID id, EN entity) throws SQLException, NoSuchDesktopsException, NoSuchMonitorsException, NoSuchWorkspaceException;
    ResponseEntity delete(ID id) throws SQLException, NoSuchDesktopsException, ExistsWorkspaceForDesktopsException, NoSuchMonitorsException, ExistsWorkspaceForMonitorsException, NoSuchWorkspaceException, ExistsWorkspaceForWorkspaceException;
    ResponseEntity<T> find(ID id) throws SQLException, NoSuchDesktopsException, NoSuchMonitorsException, NoSuchWorkspaceException;
    ResponseEntity<List<T>> findAll() throws SQLException;
}
