public class Main {
    public static void main(String[] args) {
        final Species RAT = new Species("Nagetier",10,5);
        final Species GOAT = new Species("Huftier",11,4);
        final Species MONKEY = new Species("Affe",15,8);
        final Species GORILLA = new Species(MONKEY,20);

        Monster harald = RAT.newMonster();
        Monster magnus = GOAT.newMonster();
        Monster jochen = MONKEY.newMonster();
        Monster albert = GORILLA.newMonster();


//        Monster harald = new Monster(EnumSpecies.RAT);
//        Monster magnus = new Monster(EnumSpecies.GOAT);
//        Monster jochen = new Monster(EnumSpecies.MONKEY);
//        Monster albert = new Monster(EnumSpecies.GORILLA);

        harald.makeNoise();
        magnus.makeNoise();
        jochen.makeNoise();
        albert.makeNoise();
    }
}
