package cheetsheets.dk.api;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.Set;

public class User {

    @JsonProperty("id")
    private Long id;

    @NotEmpty
    @Min(5)
    @Max(100)
    @Email
    @JsonProperty("email")
    private String email;

    @NotEmpty
    @Min(10)
    @Max(256)
    private String password;

    @NotEmpty
    @Max(50)
    private String name;

    @Max(50)
    private String lastName;

    private Set<String> roles;

    public Long getId() {
        return id;
    }

    public User setId(Long id) {
        this.id = id;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public User setEmail(String email) {
        this.email = email;
        return this;
    }

    @JsonIgnore
    public String getPassword() {
        return password;
    }

    @JsonProperty("password")
    public User setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getName() {
        return name;
    }

    public User setName(String name) {
        this.name = name;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public User setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public Set<String> getRoles() {
        return roles;
    }

    public User setRoles(Set<String> roles) {
        this.roles = roles;
        return this;
    }
}
