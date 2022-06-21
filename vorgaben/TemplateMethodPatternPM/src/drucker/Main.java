package drucker;

public class Main {
    public static void main(String args[]) {
        Tintendrucker tintendrucker = new Tintendrucker();
        Laserdrucker laserdrucker = new Laserdrucker();

        tintendrucker.kopieren();
        laserdrucker.kopieren();
    }
}
