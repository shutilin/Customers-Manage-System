package by.bsu.shutilin.customersms.repository;

import by.bsu.shutilin.customersms.model.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface CustomersRepository extends JpaRepository<Customer, Long> {
    @Transactional
    Page<Customer> findByCustomerTypeId(Long typeId, Pageable pageable);

    @Transactional
    @Query(
        "SELECT c FROM Customer c " +
        "WHERE UPPER(c.firstNameMetaphone) LIKE UPPER(:firstMet) AND UPPER(c.lastNameMetaphone) LIKE UPPER(:lastMet)")
    Page<Customer> findByFirstnameAndLastNameMetaphone(@Param("firstMet") String firstMet, @Param("lastMet") String lastMet, Pageable pageable);
}
