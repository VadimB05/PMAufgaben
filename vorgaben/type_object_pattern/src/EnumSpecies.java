public enum EnumSpecies {
    RAT("Nagetier",10,5),
    GOAT("Huftier",11,4),
    MONKEY("Affe",15,8),
    GORILLA(MONKEY,20);

    public final String variety;
    public final int xp;
    public final int magic;

    EnumSpecies(String variety, int xp, int magic){
        this.variety = variety;
        this.xp = xp;
        this.magic = magic;
    }

    EnumSpecies(EnumSpecies type, int xp){
        this.xp = xp;
        this. variety = type.variety;
        this.magic = type.magic;
    }
}
