package builder;

class Computer {
    private String CPU;
    private String RAM;
    private String storage;
    
    public void setCPU(String CPU) {
        this.CPU = CPU;
    }
    
    public void setRAM(String RAM) {
        this.RAM = RAM;
    }
    
    public void setStorage(String storage) {
        this.storage = storage;
    }
    
    @Override
    public String toString() {
        return "Computer{CPU='" + CPU + "', RAM='" + RAM + "', storage='" + storage + "'}";
    }
}

interface ComputerBuilder {
    void buildCPU();
    void buildRAM();
    void buildStorage();
    Computer getResult();
}

class GamingComputerBuilder implements ComputerBuilder {
    private Computer computer;
    
    public GamingComputerBuilder() {
        this.computer = new Computer();
    }
    
    @Override
    public void buildCPU() {
        computer.setCPU("Intel i9");
    }
    
    @Override
    public void buildRAM() {
        computer.setRAM("32GB DDR5");
    }
    
    @Override
    public void buildStorage() {
        computer.setStorage("2TB SSD");
    }
    
    @Override
    public Computer getResult() {
        return computer;
    }
}

class OfficeComputerBuilder implements ComputerBuilder {
    private Computer computer;
    
    public OfficeComputerBuilder() {
        this.computer = new Computer();
    }
    
    @Override
    public void buildCPU() {
        computer.setCPU("Intel i5");
    }
    
    @Override
    public void buildRAM() {
        computer.setRAM("16GB DDR4");
    }
    
    @Override
    public void buildStorage() {
        computer.setStorage("512GB SSD");
    }
    
    @Override
    public Computer getResult() {
        return computer;
    }
}

class ComputerDirector {
    private ComputerBuilder builder;
    
    public ComputerDirector(ComputerBuilder builder) {
        this.builder = builder;
    }
    
    public void constructComputer() {
        builder.buildCPU();
        builder.buildRAM();
        builder.buildStorage();
    }
}

public class Builder {
    public static void main(String[] args) {
        System.out.println("=== Игровой компьютер ===");
        ComputerBuilder gamingBuilder = new GamingComputerBuilder();
        ComputerDirector gamingDirector = new ComputerDirector(gamingBuilder);
        gamingDirector.constructComputer();
        Computer gamingComputer = gamingBuilder.getResult();
        System.out.println(gamingComputer);
        
        System.out.println("\n=== Офисный компьютер ===");
        ComputerBuilder officeBuilder = new OfficeComputerBuilder();
        ComputerDirector officeDirector = new ComputerDirector(officeBuilder);
        officeDirector.constructComputer();
        Computer officeComputer = officeBuilder.getResult();
        System.out.println(officeComputer);
    }
}
