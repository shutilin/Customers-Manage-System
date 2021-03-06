package by.bsu.shutilin.customersms.repository;

import by.bsu.shutilin.customersms.model.CustomerTypes;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface CustomerTypesRepository extends JpaRepository<CustomerTypes, Long> {
    @Transactional
    Page<CustomerTypes> findCustomerTypesById(Long typeId, Pageable pageable);
}
