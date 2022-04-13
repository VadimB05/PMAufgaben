public abstract class Drive {
    private int storageSize;

    public Drive(int storageSize) {
        this.storageSize = storageSize;
    }

    public int getStorageSize() {
        return storageSize;
    }
}
