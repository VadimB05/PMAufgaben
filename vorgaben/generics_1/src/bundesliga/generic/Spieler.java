package bundesliga.generic;

public class Spieler implements ISpieler{

    private final String name;

    public Spieler(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }
}
