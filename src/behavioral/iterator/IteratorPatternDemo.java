// Iterator interface
interface Iterator<T> {
    boolean hasNext();
    T next();
    void reset();
}

// Aggregate interface
interface Aggregate<T> {
    Iterator<T> createIterator();
}

// Book class
class Book {
    private String title;
    private String author;
    
    public Book(String title, String author) {
        this.title = title;
        this.author = author;
    }
    
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    
    @Override
    public String toString() {
        return "\"" + title + "\" by " + author;
    }
}

// Concrete Aggregate
class BookCollection implements Aggregate<Book> {
    private Book[] books;
    private int count;
    
    public BookCollection(int capacity) {
        books = new Book[capacity];
        count = 0;
    }
    
    public void addBook(Book book) {
        if (count < books.length) {
            books[count++] = book;
        }
    }
    
    @Override
    public Iterator<Book> createIterator() {
        return new BookIterator();
    }
    
    // Concrete Iterator
    private class BookIterator implements Iterator<Book> {
        private int position;
        
        public BookIterator() {
            position = 0;
        }
        
        @Override
        public boolean hasNext() {
            return position < count;
        }
        
        @Override
        public Book next() {
            if (hasNext()) {
                return books[position++];
            }
            throw new IndexOutOfBoundsException("No more books");
        }
        
        @Override
        public void reset() {
            position = 0;
        }
    }
}

// Main class
public class IteratorPatternDemo {
    public static void main(String[] args) {
        BookCollection library = new BookCollection(5);
        library.addBook(new Book("The Great Gatsby", "F. Scott Fitzgerald"));
        library.addBook(new Book("1984", "George Orwell"));
        library.addBook(new Book("To Kill a Mockingbird", "Harper Lee"));
        library.addBook(new Book("The Catcher in the Rye", "J.D. Salinger"));
        
        Iterator<Book> iterator = library.createIterator();
        
        System.out.println("Books in collection:");
        while (iterator.hasNext()) {
            System.out.println(" - " + iterator.next());
        }
        
        // Reset and iterate again
        iterator.reset();
        System.out.println("\nFirst book: " + iterator.next());
    }
}