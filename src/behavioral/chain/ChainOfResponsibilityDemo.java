// Handler
abstract class Approver {
    protected Approver nextApprover;
    
    public void setNextApprover(Approver nextApprover) {
        this.nextApprover = nextApprover;
    }
    
    public abstract void processRequest(PurchaseRequest request);
}

// Concrete Handlers
class Manager extends Approver {
    @Override
    public void processRequest(PurchaseRequest request) {
        if (request.getAmount() <= 1000) {
            System.out.println("Manager approved purchase #" + request.getId() + " for $" + request.getAmount());
        } else if (nextApprover != null) {
            nextApprover.processRequest(request);
        }
    }
}

class Director extends Approver {
    @Override
    public void processRequest(PurchaseRequest request) {
        if (request.getAmount() <= 5000) {
            System.out.println("Director approved purchase #" + request.getId() + " for $" + request.getAmount());
        } else if (nextApprover != null) {
            nextApprover.processRequest(request);
        }
    }
}

class CEO extends Approver {
    @Override
    public void processRequest(PurchaseRequest request) {
        if (request.getAmount() <= 10000) {
            System.out.println("CEO approved purchase #" + request.getId() + " for $" + request.getAmount());
        } else {
            System.out.println("Purchase #" + request.getId() + " for $" + request.getAmount() + " requires board approval!");
        }
    }
}

// Request class
class PurchaseRequest {
    private static int nextId = 1;
    private int id;
    private double amount;
    private String purpose;
    
    public PurchaseRequest(double amount, String purpose) {
        this.id = nextId++;
        this.amount = amount;
        this.purpose = purpose;
    }
    
    // Getters
    public int getId() { return id; }
    public double getAmount() { return amount; }
    public String getPurpose() { return purpose; }
}

// Main class
public class ChainOfResponsibilityDemo {
    public static void main(String[] args) {
        // Create chain
        Approver manager = new Manager();
        Approver director = new Director();
        Approver ceo = new CEO();
        
        manager.setNextApprover(director);
        director.setNextApprover(ceo);
        
        // Process requests
        PurchaseRequest[] requests = {
            new PurchaseRequest(500, "Office supplies"),
            new PurchaseRequest(2500, "Conference tickets"),
            new PurchaseRequest(8000, "New servers"),
            new PurchaseRequest(15000, "Company car")
        };
        
        for (PurchaseRequest request : requests) {
            manager.processRequest(request);
        }
    }
}