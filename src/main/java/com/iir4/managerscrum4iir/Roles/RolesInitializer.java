package com.iir4.managerscrum4iir.Roles;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class RolesInitializer implements CommandLineRunner {

    @Autowired
    private RolesRepository rolesRepository;

    @Override
    public void run(String... args) throws Exception {
        // Check if "Member" role exists
        if (!rolesRepository.existsByName("Member")) {
            Roles memberRole = new Roles();
            memberRole.setName("Member");
            rolesRepository.save(memberRole);
        }

        // Check if "Master" role exists
        if (!rolesRepository.existsByName("Master")) {
            Roles masterRole = new Roles();
            masterRole.setName("Master");
            rolesRepository.save(masterRole);
        }
    }
}

