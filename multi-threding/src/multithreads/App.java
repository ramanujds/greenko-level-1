package multithreads;


public class App {

    static void download() {
        System.out.println("Downloading...");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }


    static void main() {
        Thread t1 = new Thread(()->download());
        Thread t2 = new Thread(()->download());
        Thread t3 = new Thread(()->download());
        Thread t4 = new Thread(()->download());

        t1.start();
        t2.start();
        t3.start();
        t4.start();
    }

}
