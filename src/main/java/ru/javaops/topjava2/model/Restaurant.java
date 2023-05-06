package ru.javaops.topjava2.model;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import java.util.List;

@Entity
@Table(name = "Restaurant")
@Getter
@Setter
@NoArgsConstructor
@ToString(callSuper = true)
public class Restaurant extends NamedEntity {

    @Column(name = "address", nullable = false)
    @NotBlank
    @Size(min = 5, max = 50)
    private String address;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "restaurant")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonManagedReference
    private List<Dish> menu;

}
