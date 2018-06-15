package by.bsu.shutilin.customersms.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import static by.bsu.shutilin.customersms.util.MetaphoneGenerator.getMetaphone;


@Entity
@Table(name="CUSTOMERS")
public class Customers extends AuditModel {

    @Id
    @GeneratedValue(generator = "cust_id_gen")
    @SequenceGenerator(
            name = "cust_id_gen",
            sequenceName = "cust_id_seq",
            initialValue = 1000
    )
    private Long id;

    @NotBlank
    @Size(min = 3, max = 3)
    private String title;

    @NotBlank
    @Size(min = 1, max = 50)
    private String firstName;

    @Column(name="first_name_metaphone")
    private String firstNameMetaphone;

    @NotBlank
    @Size(min = 1, max = 50)
    private String lastName;

    @Column(name="last_name_metaphone")
    private String lastNameMetaphone;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_type_id", nullable = false)
    @JsonIgnore
    private CustomerTypes customerType;

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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getFirstNameMetaphone() {
        return firstNameMetaphone;
    }

    public void setFirstNameMetaphone(String firstNameMetaphone) {
        this.firstNameMetaphone = firstNameMetaphone;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLastNameMetaphone() {
        return lastNameMetaphone;
    }

    public void setLastNameMetaphone(String lastNameMetaphone) {
        this.lastNameMetaphone = lastNameMetaphone;
    }

    public CustomerTypes getCustomerType() {
        return customerType;
    }

    public void setCustomerType(CustomerTypes customerType) {
        this.customerType = customerType;
    }

    @PrePersist
    public void createMet() {
        this.firstNameMetaphone=getMetaphone(this.firstName);
        this.lastNameMetaphone = getMetaphone(this.lastName);
    }

}
