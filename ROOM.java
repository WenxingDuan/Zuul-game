import java.util.ArrayList;

public class ROOM {
    private COORDINATE location;
    private String description;
    private String name;
    private ArrayList<ITEM> things;
    private MONSTER monster;

    public ROOM(int x, int y, int z, String name, String description) {
        location = new COORDINATE(x, y, z);
        this.description = description;
        this.name = name;
        things = new ArrayList<ITEM>();

    }

    public void addItem(String name, int attack, boolean eatable, boolean moveable, boolean carryable,
            String description, int hpplus) {
        ITEM theThing = new ITEM(name, attack, eatable, moveable, carryable, description, hpplus);
        things.add(theThing);
    }

    public void addItem(String name, String description, int attack, int hpplus, int weight, boolean eatable,
            boolean carryable) {
        ITEM theThing = new ITEM(name, description, attack, hpplus, weight, eatable, carryable);
        things.add(theThing);
    }

    public COORDINATE getCoordinate() {
        return location;
    }

    public void addMonster(String name, String description, int hp, int attack) {
        monster = new MONSTER(name, description, hp, attack);
    }

    public String getName() {
        return name;
    }

    public String getDesc() {
        return description;
    }

    public ArrayList<ITEM> getThings() {
        return things;
    }

    public MONSTER getMonster() {
        return monster;
    }

    public void delMonster() {
        monster = null;
    }

    public void breakItem(String name) {
        for (int i = 0; i < things.size(); i++) {
            if (things.get(i).getName().equals(name)) {
                things.remove(i);
                break;
            }
        }
    }

    public ITEM getItem(String name) {
        for (int i = 0; i < things.size(); i++) {
            if (things.get(i).getName().equals(name)) {
                return things.get(i);
            }
        }
        return null;
    }

    public boolean isThereMonster() {
        if (monster != null) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isThereItem(String name) {
        boolean judge = false;
        for (ITEM theItem : things) {
            if (theItem.getName().equals(name)) {
                judge = true;
            }
        }
        return judge;
    }

    public ArrayList<ITEM> getAllItems() {
        return things;
    }
}
