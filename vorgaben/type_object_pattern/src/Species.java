public final class Species {
    public final String variety;
    public final int xp;
    public final int magic;

    public Species(String variety, int xp, int magic){
        this.xp = xp;
        this.variety = variety;
        this.magic = magic;
    }

    public Species(Species parent, int xp){
        this.xp = xp;
        this.variety = parent.variety;
        this.magic = parent.magic;
    }

    public Monster newMonster(){
        return new Monster(this);
    }

}
