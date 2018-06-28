package com.lagnada.xmx1024.representation;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.joda.time.LocalDate;

import javax.validation.constraints.Size;
import java.io.Serializable;
import java.net.URI;

@JsonTypeName("account")
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder({
        "id", "username", "email",
        "firstName", "lastName", "fullName", "birthdate", "prettyBirthdate",
        "deleted", "reference"
})
@JsonAutoDetect(
        getterVisibility = JsonAutoDetect.Visibility.NONE,
        setterVisibility = JsonAutoDetect.Visibility.NONE)
public class AccountRepresentation implements Serializable {

    private static final long serialVersionUID = -6214910323191581222L;

    @JsonProperty("reference")
    private URI reference;

    @JsonProperty("id")
    private Long id;

    @JsonProperty("username")
    @Size(min = 5, max = 15)
    private String username;

    @JsonProperty("password")
    @Size(min = 7, max = 30)
    @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
    private String password;

    @JsonProperty("firstName")
    @Size(min = 1, max = 100)
    private String firstName;

    @JsonProperty("lastName")
    @Size(min = 1, max = 100)
    private String lastName;

    @JsonProperty("email")
    @NotEmpty(message = "{invalid.email}")
    @Email
    private String email;

    @JsonProperty("deleted")
    private boolean deleted = false;

    @JsonProperty("birthdate")
    private LocalDate birthdate;

    public AccountRepresentation() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public URI getReference() {
        return reference;
    }

    public void setReference(URI reference) {
        this.reference = reference;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }

}
