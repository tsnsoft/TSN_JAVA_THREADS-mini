package javaapplication1;

class SomeThing implements Runnable {

    @Override
    public void run() {
        System.out.println("Привет из побочного потока!");
    }
}

public class JavaApplication1 {

    static SomeThing sth;

    public static void main(String[] args) {
        sth = new SomeThing();
        Thread t = new Thread(sth);
        t.start();
        System.out.println("Главный поток завершён...");
    }
}
