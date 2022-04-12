public abstract class Computer {
    private Ram ram;
    private Drive drive;
    private Cpu cpu;

    public Computer(Ram ram, Drive drive, Cpu cpu) {
        this.ram = ram;
        this.drive = drive;
        this.cpu = cpu;
    }

    public Ram getRam() {
        return ram;
    }

    public Drive getDrive() {
        return drive;
    }

    public Cpu getCpu() {
        return cpu;
    }
}
