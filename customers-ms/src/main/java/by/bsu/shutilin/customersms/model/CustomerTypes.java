package by.bsu.shutilin.customersms.model;

import org.hibernate.validator.constraints.UniqueElements;

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

    @Column(unique = true)
    @NotBlank
    private String caption;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }
}
