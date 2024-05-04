package com.iir4.managerscrum4iir.Roles;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.iir4.managerscrum4iir.Users.Users;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "roles")
@EntityListeners(AuditingEntityListener.class)
public class Roles {

    @Id
    @GeneratedValue
    private Integer id;
    @Column(unique = true)
    private String name;
    @Column(name="users")
    @ManyToMany(mappedBy = "roles")
    @JsonIgnore
    private List<Users> users;

    @Override
    public String toString() {
        return name;
    }
}