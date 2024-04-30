import java.util.Scanner;

/**
 * Producerconsumer
 */
public class Producerconsumer {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Buffer buffer = new Buffer();

        int n = -1;
        do {
            System.out.println("\n1.Producer\n2.Consumer\n3.Exit");
            System.out.println("Enter yout choice: ");
            n = sc.nextInt();

            switch (n) {
                case 1:
                    buffer.producer();
                    break;
                case 2:
                    buffer.consumer();
                    break;
                case -1:
                    break;
                default:
                    System.out.println("Invalid choice");
                    break;
            }

        } while (n != -1);
        sc.close();
    }
}

/**
 * Buffer
 */
class Buffer {

    boolean mutex_ready = true;
    int full = 0;
    int empty = 5;
    int value = 0;

    void producer() {
        if (full == 5) {
            System.out.println("Buffer is full");
            System.out.println("mutex state: " + (mutex_ready == false ? "Not Ready" : "Ready"));

        } else {
            mutex_ready = false;
            System.out.println("mutex state: " + (mutex_ready == false ? "Producing" : "Ready"));
            full++;
            empty--;
            value++;
            mutex_ready = true;
            System.out.println("Producer produces, items in buffer = " + (value));
            System.out.println("mutex state: " + (mutex_ready == false ? "Not Ready" : "Ready"));

        }
    }

    void consumer() {
        if (empty == 5) {
            System.out.println("Buffer is Empty");
            System.out.println("mutex state: " + (mutex_ready == false ? "Not Ready" : "Ready"));
        } else {
            mutex_ready = false;
            System.out.println("mutex state: " + (mutex_ready == false ? "Consuming" : "Ready"));

            full--;
            empty++;
            value--;
            mutex_ready = true;
            System.out.println("Consumer consumed item items in buffer = " + (value));
            System.out.println("mutex state: " + (mutex_ready == false ? "Not Ready" : "Ready"));

        }
    }

}