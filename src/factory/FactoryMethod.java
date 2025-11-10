package factory;

interface Notification {
    void send(String message);
}

class EmailNotification implements Notification {
    @Override
    public void send(String message) {
        System.out.println("Отправка email: " + message);
    }
}

class SMSNotification implements Notification {
    @Override
    public void send(String message) {
        System.out.println("Отправка SMS: " + message);
    }
}

abstract class NotificationFactory {
    public abstract Notification createNotification();
    
    public void sendNotification(String message) {
        Notification notification = createNotification();
        notification.send(message);
    }
}

class EmailNotificationFactory extends NotificationFactory {
    @Override
    public Notification createNotification() {
        return new EmailNotification();
    }
}

class SMSNotificationFactory extends NotificationFactory {
    @Override
    public Notification createNotification() {
        return new SMSNotification();
    }
}

public class FactoryMethod {
    public static void main(String[] args) {
        NotificationFactory emailFactory = new EmailNotificationFactory();
        emailFactory.sendNotification("Привет по email!");
        
        NotificationFactory smsFactory = new SMSNotificationFactory();
        smsFactory.sendNotification("Привет по SMS!");
    }
}
