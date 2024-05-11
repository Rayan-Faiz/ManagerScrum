package com.iir4.managerscrum4iir.Notification;

import com.iir4.managerscrum4iir.Users.Users;
import com.iir4.managerscrum4iir.Users.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.mail.MessagingException;
import java.util.List;

@Controller
@RequestMapping("/notifications")
public class NotificationController {

    @Autowired
    private EmailService emailService;

    @Autowired
    private UsersRepository usersRepository;

    private boolean autoNotificationEnabled = true;

    // Scheduled task to send notifications every day at 3:30 pm
    @Scheduled(cron = "0 30 15 * * *")
    public void sendNotifications() {
        if (!autoNotificationEnabled) {
            return;
        }
        List<Users> users = usersRepository.findAll();
        for (Users user : users) {
            try {
                emailService.sendNotificationEmail(user.getEmail(), "Reminder", "This is a reminder message.");
            } catch (MessagingException e) {
                e.printStackTrace();
            }
        }
    }

    @PostMapping("/toggleAutoNotification")
    public ResponseEntity<String> toggleAutoNotification() {
        autoNotificationEnabled = !autoNotificationEnabled;
        String message = autoNotificationEnabled ? "Auto notification enabled" : "Auto notification disabled";
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @GetMapping("/send")
    public ResponseEntity<String> sendTestNotification() {
        try {
            emailService.sendNotificationEmail("rakka5@outlook.fr", "Test Reminder", "This is a test reminder message.");
            return new ResponseEntity<>("Notification sent successfully", HttpStatus.OK);
        } catch (MessagingException e) {
            e.printStackTrace();
            return new ResponseEntity<>("Failed to send notification", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
