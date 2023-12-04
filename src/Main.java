import java.util.Scanner;

class Message {
    private String content;

    public Message(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }
}

class CustomQueue {
    private Message[] array;
    private int front;
    private int rear;

    public CustomQueue(int capacity) {
        array = new Message[capacity];
        front = rear = -1;
    }

    public void enqueue(Message message) {
        if (rear == array.length - 1) {
            System.out.println("Queue is full. Cannot enqueue.");
            return;
        }

        if (isEmpty()) {
            front++;
        }

        array[++rear] = message;
    }

    public Message dequeue() {
        if (isEmpty()) {
            System.out.println("Queue is empty. Cannot dequeue.");
            return null;
        }

        Message dequeuedMessage = array[front];

        if (front == rear) {
            front = rear = -1; // Reset when the last element is dequeued
        } else {
            front++;
        }

        return dequeuedMessage;
    }

    public boolean isEmpty() {
        return front == -1;
    }

    public void displayQueue() {
        if (isEmpty()) {
            System.out.println("Queue is empty.");
            return;
        }

        System.out.println("Queue:");
        for (int i = front; i <= rear; i++) {
            System.out.println(array[i].getContent());
        }
    }
}

class CustomStack {
    private Message[] array;
    private int top;

    public CustomStack(int capacity) {
        array = new Message[capacity];
        top = -1;
    }

    public void push(Message message) {
        if (top == array.length - 1) {
            System.out.println("Stack is full. Cannot push.");
            return;
        }

        array[++top] = message;
    }

    public Message pop() {
        if (isEmpty()) {
            System.out.println("Stack is empty. Cannot pop.");
            return null;
        }

        return array[top--];
    }

    public boolean isEmpty() {
        return top == -1;
    }

    public void displayStack() {
        if (isEmpty()) {
            System.out.println("Stack is empty.");
            return;
        }

        System.out.println("Stack:");
        for (int i = top; i >= 0; i--) {
            System.out.println(array[i].getContent());
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the capacity for both stack and queue: ");
        int capacity = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        CustomQueue messageQueue = new CustomQueue(capacity);
        CustomStack messageStack = new CustomStack(capacity);

        int choice;
        do {
            System.out.println("=== MENU SYSTEM ===");
            System.out.println("1. Input message");
            System.out.println("2. Push to stack");
            System.out.println("3. Show queue");
            System.out.println("4. Show stack");
            System.out.println("5. Pop from stack");
            System.out.println("6. Exit");
            System.out.print("Enter your selection: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    // Input message: Input and enqueue into queue
                    System.out.println("Enter the message to input:");
                    String receivedMessage = scanner.nextLine();
                    Message receivedMsg = new Message(receivedMessage);
                    messageQueue.enqueue(receivedMsg);
                    break;

                case 2:
                    // Output message:
                    // Dequeue from queue and push into stack
                    Message msgQueue = messageQueue.dequeue();
                    messageStack.push(msgQueue);
                    break;

                case 3:
                    // Show queue
                    messageQueue.displayQueue();
                    break;

                case 4:
                    // Show stack
                    messageStack.displayStack();
                    break;
                case 5:
                    // Pop and display messages from stack
                    Message msgStack = messageStack.pop();
                    System.out.println("Output message: " + msgStack.getContent());
                    break;
                case 6:
                    // Exit
                    System.out.println("Exiting program.");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }

        } while (choice != 6);
    }
}
