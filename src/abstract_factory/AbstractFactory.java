package abstract_factory;

interface Button {
    void render();
}

interface TextField {
    void display();
}

class WindowsButton implements Button {
    @Override
    public void render() {
        System.out.println("Отрисовка Windows кнопки");
    }
}

class WindowsTextField implements TextField {
    @Override
    public void display() {
        System.out.println("Отображение Windows текстового поля");
    }
}

class MacButton implements Button {
    @Override
    public void render() {
        System.out.println("Отрисовка Mac кнопки");
    }
}

class MacTextField implements TextField {
    @Override
    public void display() {
        System.out.println("Отображение Mac текстового поля");
    }
}

interface GUIFactory {
    Button createButton();
    TextField createTextField();
}

class WindowsFactory implements GUIFactory {
    @Override
    public Button createButton() {
        return new WindowsButton();
    }
    
    @Override
    public TextField createTextField() {
        return new WindowsTextField();
    }
}

class MacFactory implements GUIFactory {
    @Override
    public Button createButton() {
        return new MacButton();
    }
    
    @Override
    public TextField createTextField() {
        return new MacTextField();
    }
}

class Application {
    private Button button;
    private TextField textField;
    
    public Application(GUIFactory factory) {
        button = factory.createButton();
        textField = factory.createTextField();
    }
    
    public void renderUI() {
        button.render();
        textField.display();
    }
}

public class AbstractFactory {
    public static void main(String[] args) {
        System.out.println("=== Windows UI ===");
        Application windowsApp = new Application(new WindowsFactory());
        windowsApp.renderUI();
        
        System.out.println("\n=== Mac UI ===");
        Application macApp = new Application(new MacFactory());
        macApp.renderUI();
    }
}
