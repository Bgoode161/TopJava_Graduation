package ru.javaops.topjava2.to;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Value;
import org.checkerframework.checker.units.qual.N;
import ru.javaops.topjava2.HasId;

@Value
public class RestaurantTo extends NamedTo {

    @NotBlank
    @Size(min = 5, max = 50)
    String address;

    public RestaurantTo(Integer id, String name, String address) {
        super(id,name);
        this.address = address;
    }

    @Override
    public String toString() {
        return "RestaurantTo{" +
                "address='" + address + '\'' +
                ", name='" + name + '\'' +
                ", id=" + id +
                '}';
    }
}
