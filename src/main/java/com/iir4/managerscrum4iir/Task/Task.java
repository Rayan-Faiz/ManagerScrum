package com.iir4.managerscrum4iir.Task;

import com.iir4.managerscrum4iir.Sprint.Sprint;
import jakarta.persistence.*;

@Entity
@Table(name = "tasks")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    @ManyToOne
    @JoinColumn(name = "sprint_id")
    private Sprint sprint;
}
