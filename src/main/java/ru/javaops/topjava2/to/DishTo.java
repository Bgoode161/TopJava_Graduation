package ru.javaops.topjava2.to;

public class DishTo extends NamedTo {

    private long price;

    public DishTo(Integer id, String name, Long price) {
        super(id, name);
        this.price = price;

    }

    public long getPrice() {
        return price;
    }
}
