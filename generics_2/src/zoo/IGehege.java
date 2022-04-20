package zoo;

import zoo.tiere.Tier;

public interface IGehege {
    /**
     * Nimmt ein Tier in das Gehege auf.
     *
     * @param tier Tier das aufgenommen werden soll.
     */
    void aufnehmen(Tier tier);

    /**
     * Entfernt ein Tier aus dem Gehege.
     *
     * @param tier Tier das entfernt werden soll.
     */
    void entfernen(Tier tier);

    /**
     * Gibt den Namen des Geheges zurück.
     *
     * @return Name des Geheges.
     */
    String getName();
}
