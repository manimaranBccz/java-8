@FunctionalInterface
public interface WelcomePerson {
    void greatUserByName(String name);
    default void sayHello(){
        System.out.println("Hello");
    }
    static void sayHelloWorld(){
        System.out.println("Hello world");
    }
}
