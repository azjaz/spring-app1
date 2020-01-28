package entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Objects;

public class Spitter {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private final Long id;
    private final String username;
    private final String password;
    private final String firstName;
    private final String lastName;

    public Spitter(String username, String password, String firstName, String lastName) {
        this.id = null;
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Spitter spitter = (Spitter) o;
        return Objects.equals(id, spitter.id) &&
                Objects.equals(username, spitter.username) &&
                Objects.equals(password, spitter.password) &&
                Objects.equals(firstName, spitter.firstName) &&
                Objects.equals(lastName, spitter.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, password, firstName, lastName);
    }
}
