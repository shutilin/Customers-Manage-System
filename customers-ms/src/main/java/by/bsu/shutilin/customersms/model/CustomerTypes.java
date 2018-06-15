package by.bsu.shutilin.customersms.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name="CUSTOMER_TYPES")
public class CustomerTypes {

    @Id
    @GeneratedValue(generator = "type_id_gen")
    @SequenceGenerator(
            name = "type_id_gen",
            sequenceName = "type_id_seq",
            initialValue = 1000
    )
    private Long id;

    @NotBlank
    private String title;

   /* @OneToMany(mappedBy = "customerType", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Customers> customers;

    public Set<Customers> getCustomers() {
        return customers;
    }

    public void setCustomers(Set<Customers> customers) {
        this.customers = customers;
    }*/

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
