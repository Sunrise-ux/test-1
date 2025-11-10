package singleton;

public class Singleton {
    private static Singleton instance;
    
    private Singleton() {
        System.out.println("Singleton создан!");
    }
    
    public static Singleton getInstance() {
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }
    
    public void showMessage() {
        System.out.println("Привет от Singleton!");
    }
}

class SingletonDemo {
    public static void main(String[] args) {
        Singleton singleton1 = Singleton.getInstance();
        singleton1.showMessage();
        
        Singleton singleton2 = Singleton.getInstance();
        System.out.println("Один и тот же объект? " + (singleton1 == singleton2));
    }
}
