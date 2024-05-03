package com.iir4.managerscrum4iir.UserStory;

import jakarta.persistence.*;

@Entity
@Table(name = "user_stories")
public class UserStory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String description;
}
