package store.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Fragrance {
    private Long id;
    private String fragranceName;
    private Brand brand;
    private List<Seller> sellers;

    public Fragrance() {
    }

    public Fragrance(String fragranceName, Brand brand) {
        this.fragranceName = fragranceName;
        this.brand = brand;
        sellers = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFragranceName() {
        return fragranceName;
    }

    public void setFragranceName(String fragranceName) {
        this.fragranceName = fragranceName;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public List<Seller> getSellers() {
        return sellers;
    }

    public void setSellers(List<Seller> sellers) {
        this.sellers = sellers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Fragrance fragrance = (Fragrance) o;
        return Objects.equals(id, fragrance.id)
                && Objects.equals(fragranceName, fragrance.fragranceName)
                && Objects.equals(brand, fragrance.brand)
                && Objects.equals(sellers, fragrance.sellers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fragranceName, brand, sellers);
    }

    @Override
    public String toString() {
        return "Fragrance{"
                + "id=" + id
                + ", fragranceName='" + fragranceName
                + '\'' + ", brand=" + brand
                + ", sellers=" + sellers
                + '}';
    }
}
