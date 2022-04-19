public class Main {
    public static void main(String[] args) {
        Ringbuffer buffer = new Ringbuffer(5);
        try {
            buffer.remove();
        } catch (IllegalStateException stateException) {
            System.err.println(stateException.getMessage());
        }
        buffer.add("First");
        System.out.println("First element added succesfully");
        buffer.add("Second");
        System.out.println("Second element added Succesfully");
        try {
            buffer.add("Third");
            System.out.println("Third element added Succesfully");
        } catch (IllegalStateException stateException) {
            System.err.println(stateException.getMessage());
        }
        String first = buffer.remove();
        System.out.printf("The removed element is: %s", first);
    }
}
