package zoo;

public interface IZoo {
    /**
     * Fügt ein Gehege dem Zoo hinzu.
     *
     * @param gehege Gehege das hinzugefügt werden soll.
     */
    void errichten(IGehege gehege);

    /**
     * Entfernt ein Gehege aus dem Zoo.
     *
     * @param gehege Gehege das entfernt werden soll.
     */
    void abreissen(IGehege gehege);
}
