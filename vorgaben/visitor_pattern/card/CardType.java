package card;

/** The types of vehicles that can be seen on the cards. */
public enum CardType {
    CAR("IM A CAR *BRUMM BRUMM BRUMM BRUMM*"),
    AIRPLANE("IM A AIRPLANE *Airplane sound*"),
    MOTORCYCLE("IM A Motorcycle *BRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRUM*"),
    BOAT("IM A BOAT *Boat sound*");

    private final String sound;

    /**
     * @param sound The Sound that the vehicle on the card would make.
     */
    CardType(final String sound) {
        this.sound = sound;
    }

    /**
     * @return The Sound that the vehicle on the card would make.
     */
    public final String getSound() {
        return sound;
    }
}
