class Auftrag {
    private WarenTyp warenTyp;
    private int anzahl;

    public Auftrag(WarenTyp warenTyp, int anzahl) {
        this.warenTyp = warenTyp;
        this.anzahl = anzahl;
    }

    public WarenTyp getWarenTyp() {
        return warenTyp;
    }

    public int getAnzahl() {
        return anzahl;
    }
}
