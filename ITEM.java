public class ITEM {
    private String name;
    private String description;
    private int attack;
    private int hpplus;
    private boolean eatable;
    private boolean moveable;
    private boolean carryable;
    private int weight;

    public ITEM(String name, int attack, boolean eatable, boolean moveable, boolean carryable, String description,
            int hpplus) {
        this.attack = attack;
        this.name = name;
        this.eatable = eatable;
        this.moveable = moveable;
        this.carryable = carryable;
        this.description = description;
        this.hpplus = hpplus;

    }

    public ITEM(String name, String description, int attack, int hpplus, int weight,boolean eatable,boolean carryable) {
        this.attack = attack;
        this.name = name;
        this.description = description;
        this.hpplus = hpplus;
        this.weight = weight;
        this.eatable = eatable;
        this.carryable = carryable;

    }

    public String getName() {
        return name;
    }

    public String getDesc() {
        return description;
    }

    public int getAttack() {
        return attack;
    }

    public int getHpplus() {
        return hpplus;
    }

    public boolean getEat() {
        return eatable;
    }

    public boolean getMove() {
        return moveable;
    }

    public boolean getCarry() {
        return carryable;
    }
    public int getWeight(){
        return weight;
    }
}
