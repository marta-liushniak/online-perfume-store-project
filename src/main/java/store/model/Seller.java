package store.model;

import java.util.Objects;

public class Seller {
    private Long id;
    private String name;
    private String lastName;
    private String login;
    private String password;

    public Seller() {
    }

    public Seller(String name, String lastName) {
        this.name = name;
        this.lastName = lastName;
    }

    public Seller(String name, String lastName, String login, String password) {
        this(name, lastName);
        this.login = login;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Seller seller = (Seller) o;
        return Objects.equals(id, seller.id)
                && Objects.equals(name, seller.name)
                && Objects.equals(lastName, seller.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, lastName);
    }
}
