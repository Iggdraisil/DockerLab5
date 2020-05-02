package ua.lviv.iot.database.lab4.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.lviv.iot.database.lab4.model.WorkersEntity;
@Repository
public interface WorkersRepository extends JpaRepository<WorkersEntity, Integer> {
}
