package cpu;

public class RuntimeDemo {


    public static void main(String[] args) {

        // print a normal message
        System.out.println("Hello world!");

        // check the number of processors available
        System.out.println(""+Runtime.getRuntime().availableProcessors());
    }
}