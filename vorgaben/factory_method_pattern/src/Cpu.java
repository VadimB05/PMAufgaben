public class Cpu {
    private int cores;
    private float frequency;

    public Cpu(int cores, float frequency) {
        this.cores = cores;
        this.frequency = frequency;
    }

    public int getCores() {
        return cores;
    }

    public float getFrequency() {
        return frequency;
    }
}
