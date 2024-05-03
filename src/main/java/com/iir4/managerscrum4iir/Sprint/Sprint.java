package com.iir4.managerscrum4iir.Sprint;

import com.iir4.managerscrum4iir.Task.Task;
import jakarta.persistence.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "sprints")
public class Sprint {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Date startDate;

    @Column(nullable = false)
    private Date endDate;

    @OneToMany(mappedBy = "sprint", cascade = CascadeType.ALL)
    private Set<Task> tasks = new HashSet<>();
}
