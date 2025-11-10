import singleton.Singleton;
import factory.NotificationFactory;
import factory.EmailNotificationFactory;
import factory.SMSNotificationFactory;
import abstract_factory.Application;
import abstract_factory.WindowsFactory;
import abstract_factory.MacFactory;
import builder.ComputerBuilder;
import builder.GamingComputerBuilder;
import builder.OfficeComputerBuilder;
import builder.ComputerDirector;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== ДЕМОНСТРАЦИЯ ПОРОЖДАЮЩИХ ПАТТЕРНОВ ===\n");
        
        // 1. Singleton
        System.out.println("1. SINGLETON:");
        Singleton singleton = Singleton.getInstance();
        singleton.showMessage();
        
        // 2. Factory Method
        System.out.println("\n2. FACTORY METHOD:");
        NotificationFactory emailFactory = new EmailNotificationFactory();
        emailFactory.sendNotification("Тестовое сообщение");
        
        // 3. Abstract Factory
        System.out.println("\n3. ABSTRACT FACTORY:");
        Application windowsApp = new Application(new WindowsFactory());
        windowsApp.renderUI();
        
        // 4. Builder
        System.out.println("\n4. BUILDER:");
        ComputerBuilder builder = new GamingComputerBuilder();
        ComputerDirector director = new ComputerDirector(builder);
        director.constructComputer();
        System.out.println("Собран компьютер: " + builder.getResult());
        
        System.out.println("\n=== ВСЕ ПАТТЕРНЫ ПРОДЕМОНСТРИРОВАНЫ ===");
    }
}
