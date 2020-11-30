package javaapplication3;

class Q {

    int n; // номер ресурса
    boolean valueSet = false; // готовность ресурса

    synchronized int get() { // получить ресурс
        while (!valueSet) {
            try {
                wait(); // ждать
            } catch (InterruptedException e) {
                System.out.println("InterruptedException caught");
            }
        }
        System.out.println("Получено: " + n);
        valueSet = false;
        notify(); // информировать приостановленный поток
        return n;
    }

    synchronized void put(int n) { // добавление ресурса
        while (valueSet) { // пока очередь не пуста, ждем
            try {
                wait();
            } catch (InterruptedException e) {
                System.out.println("InterruptedException caught");
            }
        }

        this.n = n;
        valueSet = true;
        System.out.println("Добавлено: " + n);
        notify();
    }
}

class Producer implements Runnable {

    Q q;

    Producer(Q q) {
        this.q = q;
        new Thread(this, "Producer").start();
    }

    @Override
    public void run() {
        int i = 0;
        try {
            while (true) {
                Thread.sleep(1000);
                q.put(i++); // добавляем ресурс в очередь
            }
        } catch (InterruptedException ex) {
            System.out.println("Прервано");
        }
    }
}

class Consumer implements Runnable {

    Q q;

    Consumer(Q q) {
        this.q = q;
        new Thread(this, "Consumer").start();
    }

    public void run() {
        try {
            while (true) {
                q.get();
                Thread.sleep(100);
            }
        } catch (InterruptedException ex) {
            System.out.println("Прервано");
        }
    }

}

public class JavaApplication3 {

    public static void main(String[] args) {
        Q q = new Q();
        new Producer(q);
        new Consumer(q);
        System.out.println("Press Control-C to stop.");
    }
}
