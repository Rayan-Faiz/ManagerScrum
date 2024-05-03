package com.iir4.managerscrum4iir.Dashboard;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/dashboard")
public class DashboardController {

    @GetMapping("/member-dashboard")
    public String memberDashboard() {

        return "member-dashboard";
    }

    @GetMapping("/scrum-master-dashboard")
    public String scrumMasterDashboard() {
        return "scrum-master-dashboard";
    }

}

