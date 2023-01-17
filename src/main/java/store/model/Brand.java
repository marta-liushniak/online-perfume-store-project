package store.model;

import java.util.Objects;

public class Brand {
    private Long id;
    private String brandName;
    private String country;

    public Brand() {
    }

    public Brand(String brandName, String country) {
        this.brandName = brandName;
        this.country = country;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Brand brand = (Brand) o;
        return Objects.equals(id, brand.id)
                && Objects.equals(brandName, brand.brandName)
                && Objects.equals(country, brand.country);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, brandName, country);
    }
}
