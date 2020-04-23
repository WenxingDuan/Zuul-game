import java.util.Random;

public class CHARACTER
{
    protected String name;
    protected String description;
    protected int hp;
    protected int attack;

    public CHARACTER(String name, String description, int hp, int attack) {
        this.name = name;
        this.attack = attack;
        this.hp = hp;
        this.description = description;
    }

    public void lossHP(int n) {
        hp = hp - n;
    }

    public int attackHP() {
        Random rand = new Random();
        int lossHp = rand.nextInt(5) + attack;
        return lossHp;
    }

    public String getName() {
        return name;
    }

    public String getDesc() {
        return description;
    }

    public int getHP() {
        return hp;
    }

    public int getAttack() {
        return attack;
    }
}
