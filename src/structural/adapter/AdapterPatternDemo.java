// Old legacy system (Adaptee)
class LegacyNotificationSystem {
    public void sendSMS(String phoneNumber, String text) {
        System.out.println("[Legacy SMS] To: " + phoneNumber + " Message: " + text);
    }
    
    public void sendPagerMessage(String pagerId, String message) {
        System.out.println("[Legacy Pager] To: " + pagerId + " Message: " + message);
    }
}

// New target interface
interface ModernNotificationService {
    void send(String recipient, String message, String channel);
}

// Adapter
class LegacyNotificationAdapter implements ModernNotificationService {
    private LegacyNotificationSystem legacySystem;
    
    public LegacyNotificationAdapter(LegacyNotificationSystem legacySystem) {
        this.legacySystem = legacySystem;
    }
    
    @Override
    public void send(String recipient, String message, String channel) {
        switch (channel.toLowerCase()) {
            case "sms":
                legacySystem.sendSMS(recipient, message);
                break;
            case "pager":
                legacySystem.sendPagerMessage(recipient, message);
                break;
            default:
                throw new IllegalArgumentException("Unsupported channel: " + channel);
        }
    }
}

// New modern service (for comparison)
class EmailNotificationService implements ModernNotificationService {
    @Override
    public void send(String recipient, String message, String channel) {
        System.out.println("[Email] To: " + recipient + " Subject: " + message);
    }
}

// Main class
public class AdapterPatternDemo {
    public static void main(String[] args) {
        // Using modern service directly
        ModernNotificationService emailService = new EmailNotificationService();
        emailService.send("user@example.com", "Welcome!", "email");
        
        // Using legacy system through adapter
        LegacyNotificationSystem legacySystem = new LegacyNotificationSystem();
        ModernNotificationService adaptedService = new LegacyNotificationAdapter(legacySystem);
        
        adaptedService.send("+1234567890", "Your code: 1234", "sms");
        adaptedService.send("pager123", "Urgent: System update", "pager");
    }
}