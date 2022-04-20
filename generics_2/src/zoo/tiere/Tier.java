package zoo.tiere;

public abstract class Tier {
    private final String name;

    /**
     * Setzt den Namen des Tieres.
     *
     * @param name der Name des Tieres.
     */
    protected Tier(String name) {
        this.name = name;
    }

    /**
     * Gibt den Namen des Tieres zurück.
     *
     * @return Name des Tieres.
     */
    public String getName() {
        return name;
    }
}
