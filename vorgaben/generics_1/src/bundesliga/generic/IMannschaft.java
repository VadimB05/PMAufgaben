package bundesliga.generic;

public interface IMannschaft<ISpieler> {

    boolean aufnehmen(ISpieler iSpieler);

    boolean rauswerfen(ISpieler iSpieler);
}
