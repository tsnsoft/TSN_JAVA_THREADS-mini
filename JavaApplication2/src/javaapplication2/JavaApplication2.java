package javaapplication2;

class HelloThread extends Thread {
    @Override
    public void run() {
        System.out.println("Hello from a thread!");
    }

}

public class JavaApplication2 {
    public static void main(String[] args) {
        (new HelloThread()).start();
    }
}
