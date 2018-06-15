package by.bsu.shutilin.customersms.repository;

import by.bsu.shutilin.customersms.model.CustomerTypes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerTypesRepository extends JpaRepository<CustomerTypes, Long> {
}
