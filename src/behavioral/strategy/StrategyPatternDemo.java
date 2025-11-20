// Strategy interface
interface PaymentStrategy {
    void pay(int amount);
}

// Concrete Strategies
class CreditCardPayment implements PaymentStrategy {
    private String cardNumber;
    
    public CreditCardPayment(String cardNumber) {
        this.cardNumber = cardNumber;
    }
    
    @Override
    public void pay(int amount) {
        System.out.println("Paid " + amount + " using Credit Card: " + cardNumber.substring(0, 4) + "****");
    }
}

class PayPalPayment implements PaymentStrategy {
    private String email;
    
    public PayPalPayment(String email) {
        this.email = email;
    }
    
    @Override
    public void pay(int amount) {
        System.out.println("Paid " + amount + " using PayPal: " + email);
    }
}

class CryptoPayment implements PaymentStrategy {
    private String walletAddress;
    
    public CryptoPayment(String walletAddress) {
        this.walletAddress = walletAddress;
    }
    
    @Override
    public void pay(int amount) {
        System.out.println("Paid " + amount + " using Crypto: " + walletAddress.substring(0, 8) + "...");
    }
}

// Context
class ShoppingCart {
    private PaymentStrategy paymentStrategy;
    
    public void setPaymentStrategy(PaymentStrategy paymentStrategy) {
        this.paymentStrategy = paymentStrategy;
    }
    
    public void checkout(int amount) {
        if (paymentStrategy == null) {
            System.out.println("Please select payment method");
            return;
        }
        paymentStrategy.pay(amount);
    }
}

// Main class
public class StrategyPatternDemo {
    public static void main(String[] args) {
        ShoppingCart cart = new ShoppingCart();
        
        // Different payment strategies
        cart.setPaymentStrategy(new CreditCardPayment("1234567812345678"));
        cart.checkout(100);
        
        cart.setPaymentStrategy(new PayPalPayment("user@example.com"));
        cart.checkout(200);
        
        cart.setPaymentStrategy(new CryptoPayment("1A2b3C4d5E6f7G8h9I0j"));
        cart.checkout(150);
    }
}