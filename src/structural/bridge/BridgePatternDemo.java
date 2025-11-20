// Implementation hierarchy - Rendering APIs
interface Renderer {
    void renderCircle(float radius);
    void renderSquare(float side);
    void renderTriangle(float base, float height);
}

class OpenGLRenderer implements Renderer {
    @Override
    public void renderCircle(float radius) {
        System.out.println("OpenGL: Drawing circle with radius " + radius);
    }
    
    @Override
    public void renderSquare(float side) {
        System.out.println("OpenGL: Drawing square with side " + side);
    }
    
    @Override
    public void renderTriangle(float base, float height) {
        System.out.println("OpenGL: Drawing triangle with base " + base + " and height " + height);
    }
}

class VulkanRenderer implements Renderer {
    @Override
    public void renderCircle(float radius) {
        System.out.println("Vulkan: Rendering circle (radius: " + radius + ")");
    }
    
    @Override
    public void renderSquare(float side) {
        System.out.println("Vulkan: Rendering square (side: " + side + ")");
    }
    
    @Override
    public void renderTriangle(float base, float height) {
        System.out.println("Vulkan: Rendering triangle (base: " + base + ", height: " + height + ")");
    }
}

// Abstraction hierarchy - Shapes
abstract class Shape {
    protected Renderer renderer;
    
    protected Shape(Renderer renderer) {
        this.renderer = renderer;
    }
    
    public abstract void draw();
    public abstract void resize(float factor);
}

class Circle extends Shape {
    private float radius;
    
    public Circle(Renderer renderer, float radius) {
        super(renderer);
        this.radius = radius;
    }
    
    @Override
    public void draw() {
        renderer.renderCircle(radius);
    }
    
    @Override
    public void resize(float factor) {
        radius *= factor;
        System.out.println("Circle resized to radius: " + radius);
    }
}

class Square extends Shape {
    private float side;
    
    public Square(Renderer renderer, float side) {
        super(renderer);
        this.side = side;
    }
    
    @Override
    public void draw() {
        renderer.renderSquare(side);
    }
    
    @Override
    public void resize(float factor) {
        side *= factor;
        System.out.println("Square resized to side: " + side);
    }
}

// Main class
public class BridgePatternDemo {
    public static void main(String[] args) {
        // Different combinations of shapes and renderers
        Renderer opengl = new OpenGLRenderer();
        Renderer vulkan = new VulkanRenderer();
        
        Shape circleWithOpenGL = new Circle(opengl, 5.0f);
        Shape squareWithVulkan = new Square(vulkan, 10.0f);
        Shape circleWithVulkan = new Circle(vulkan, 7.5f);
        
        circleWithOpenGL.draw();
        squareWithVulkan.draw();
        circleWithVulkan.draw();
        
        System.out.println("\nAfter resizing:");
        circleWithOpenGL.resize(1.5f);
        circleWithOpenGL.draw();
    }
}