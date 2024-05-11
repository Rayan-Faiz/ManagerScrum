package com.iir4.managerscrum4iir.Notification;

import jakarta.mail.MessagingException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class NotificationControllerTest {

    @Mock
    private EmailService emailServiceMock;

    @InjectMocks
    private NotificationController controller;

    @Test
    public void testSendTestNotification() throws MessagingException {
        // Define the behavior of the mock EmailService for void methods
        doNothing().when(emailServiceMock).sendNotificationEmail(anyString(), anyString(), anyString());

        // Call the method under test
        ResponseEntity<String> response = controller.sendTestNotification();

        // Verify that the method under test behaves as expected
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Notification sent successfully", response.getBody());

        // Optionally, verify interactions with the mock EmailService
        verify(emailServiceMock, times(1)).sendNotificationEmail(anyString(), anyString(), anyString());
    }
}
