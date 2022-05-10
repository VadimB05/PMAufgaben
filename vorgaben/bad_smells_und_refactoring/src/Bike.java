
public abstract class Bike {
    protected String productName;
    protected double price;
    protected int maxSpeed;
    protected int rearGearsCount;
    protected int frontGearsCount;

    public int getGearsCount() {
        return rearGearsCount * frontGearsCount;
    }

    public int getMaxSpeed() {
        return maxSpeed;
    }
}
