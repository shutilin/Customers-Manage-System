package by.bsu.shutilin.customersms.repository;

import by.bsu.shutilin.customersms.model.Customers;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomersRepository extends JpaRepository<Customers, Long> {
    Page<Customers> findByCustomerTypeId(Long typeId, Pageable pageable);
}
