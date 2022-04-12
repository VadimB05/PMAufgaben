public class Utility {
    private int intResult = 0;
    private Evil evilClass;

    public Utility(Evil evilClass) {
        this.evilClass = evilClass;
    }

    public void evilMethod() {
        throw new RuntimeException("bam!");
    }

    public int nonEvilAdd(int a, int b) {
        return a + b;
    }

    public int evilAdd(int a, int b) {
        evilClass.evilMethod();
        return a + b;
    }

    public void veryEvilAdd(int a, int b) {
        evilMethod();
        evilClass.evilMethod();
        intResult = a + b;
    }

    public int getIntResult() {
        return intResult;
    }
}
