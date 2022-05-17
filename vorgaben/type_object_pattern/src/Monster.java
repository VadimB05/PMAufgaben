public final class Monster {
    private final Species type;
    private final String variety;
    private int xp;
    private final int magic;
    public Monster(Species type){
        this.type = type;
        this.xp = type.xp;
        this.variety = type.variety;
        this.magic = type.magic;
    }

    public void makeNoise(){
        System.out.println("Ich bin ein " + variety + " und bringe euch " + xp + " Erfahrungspunkte.");
    }
}
