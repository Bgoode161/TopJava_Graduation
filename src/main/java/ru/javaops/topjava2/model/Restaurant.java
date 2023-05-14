package ru.javaops.topjava2.model;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import java.util.List;

@Entity
@Table(name = "Restaurant", uniqueConstraints = {@UniqueConstraint (columnNames = {"id", "name"}, name = "restaurant_unique_name")})
@Getter
@Setter
@NoArgsConstructor
public class Restaurant extends NamedEntity {

    @Column(name = "address", nullable = false)
    @NotBlank
    @Size(min = 5, max = 50)
    private String address;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "restaurant")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonManagedReference
    private List<Dish> dishes;

    public Restaurant(Integer id, String name, String address) {
        super(id, name);
        this.address = address;
    }

    public Restaurant(Integer id, String name, String address, List<Dish> dishes) {
        super(id, name);
        this.address = address;
        this.dishes = dishes;
    }

}
