
//import java.text.BreakIterator;
import java.util.ArrayList;
import java.util.Random;
//import java.lang.Thread;

public class GAME {
    private MAP theMap;
    // private ROOM currentRoom;
    // private COORDINATE currentCoordinate;
    private PLAYER player;
    private Parser parser;
    private ArrayList<ITEM> bag;
    private ArrayList<ITEM> body;
    // private Random random;
    private ArrayList<String> directionList;
    private int totalWeight;

    public GAME() {
        theMap = new MAP();
        createMap();
        player = new PLAYER(theMap);
        player.lossHP(1);
        parser = new Parser();
        bag = new ArrayList<ITEM>();
        body = new ArrayList<ITEM>();
        totalWeight = 20;
        // currentCoordinate = player.getCoordinate();
        // currentRoom = player.getRoom();
    }

    /*
     * =============================================================================
     * =============================================================================
     */

    public void gameProcess() {
        printWelcome();
        boolean finished = false;
        while (!finished) {
            Command command = parser.getCommand();
            finished = gameEngine(command);

        }

        System.out.println("Thank you for playing.  Good bye.");
    }

    private boolean gameEngine(Command command) {
        boolean judge = false;

        if (command.isUnknown()) {
            System.out.println("I don't know what you mean...");
            return false;
        }

        String commandWord = command.getCommandWord();
        // "go", "quit", "help", "pickup", "fight", "puton", "look"

        switch (commandWord) {

        case "help":
            printHelp();
            break;

        case "quit":
            judge = true;
            break;

        case "go":
            go(command);
            break;

        case "pickup":
            pickUp(command);
            break;

        case "fight":
            fight(command);
            if (player.getHP() <= 0) {
                judge = false;
                System.out.println("你输了");
            }
            break;

        case "puton":
            putOn(command);
            break;

        case "look":
            look(command);
            break;

        case "eat":
            eat(command);
            break;

        case "trans":
            trans();
            break;
        }
        if (player.getHP() <= 0) {
            judge = true;
        }
        return judge;
    }

    /*
     * =============================================================================
     * =============================================================================
     */

    private void look(Command command) {
        if (!command.hasSecondWord()) {
            System.out.println("你现在在 " + player.getRoom().getName());
            System.out.println(player.getRoom().getDesc());
            System.out.println("你的血量是 " + player.getHP());
            System.out.println("你的攻击是 " + player.getAttack());
            System.out.println("你看到了 ");
            for (ITEM theItem : player.getRoom().getAllItems()) {
                System.out.print(theItem.getName() + ", ");
            }
            if (player.getRoom().isThereMonster()) {
                System.out.println("\n" + "这里有一只" + player.getRoom().getMonster().getName() + "\n"
                        + player.getRoom().getMonster().getDesc() + "\n");
            }
            System.out.println("有以下方向");
            player.lookDirection();
            System.out.println("\n" + "身上有" + getList());
            System.out.println("包里有" + getBag());
            System.out.println("还能背" + totalWeight + "kg重量");

            // System.out.println(player.getCoordinate().getXYZ());

        } else {
            if (player.getRoom().isThereItem(command.getSecondWord())) {
                System.out.println(player.getRoom().getItem(command.getSecondWord()).getName()
                        + player.getRoom().getItem(command.getSecondWord()).getDesc());
                System.out.println("重" + player.getRoom().getItem(command.getSecondWord()).getWeight() + "kg");
            } else if (command.getSecondWord().equals(player.getRoom().getMonster().getName())) {
                System.out.println("它的攻击是" + player.getRoom().getMonster().getAttack());
                System.out.println("它的血量是" + player.getRoom().getMonster().getHP());
            } else {
                System.out.println("没有这个东西");
            }
        }

    }

    private void pickUp(Command command) {
        if (!command.hasSecondWord()) {
            System.out.println("捡起啥");
        } else {
            String itemName = command.getSecondWord();
            if (player.getRoom().isThereItem(itemName)) {
                if (totalWeight - player.getRoom().getItem(itemName).getWeight() < 0) {
                    System.out.println("过重");
                } else {
                    bag.add(player.getRoom().getItem(itemName));
                    totalWeight = totalWeight - player.getRoom().getItem(itemName).getWeight();
                    System.out.println("成功捡起了" + itemName);
                    player.getRoom().breakItem(itemName);

                }
            } else {
                System.out.println("没有找到物件");
            }
        }
    }

    private ArrayList<String> getList() {
        ArrayList<String> returnStringBody = new ArrayList<String>();
        for (ITEM theItem : body) {
            returnStringBody.add(theItem.getName());
        }
        return returnStringBody;
    }

    private ArrayList<String> getBag() {
        ArrayList<String> returnStringBag = new ArrayList<String>();
        for (ITEM theItem : bag) {
            returnStringBag.add(theItem.getName());
        }
        return returnStringBag;
    }

    private void putOn(Command command) {
        boolean judge = false;
        int i;
        for (i = 0; i < bag.size(); i++) {
            if (bag.get(i).getName().equals(command.getSecondWord()) && bag.get(i).getCarry()) {
                body.add(bag.get(i));

                judge = true;
                break;
            }
        }
        if (!judge) {
            System.out.println("包里没有这个装备");

        } else {
            System.out.println("你成功装备了" + command.getSecondWord());
            player.addAttack(bag.get(i).getAttack());
            bag.remove(i);
        }

    }

    private void eat(Command command) {
        boolean judge = false;
        int i;
        for (i = 0; i < bag.size(); i++) {
            if (bag.get(i).getName().equals(command.getSecondWord()) && bag.get(i).getEat()) {
                // body.add(bag.get(i));

                judge = true;

                break;
            }
        }
        if (!judge) {
            System.out.println("包里没有这个食物");

        } else {
            System.out.println("你成功吃了" + command.getSecondWord());
            totalWeight = totalWeight + bag.get(i).getWeight();
            if (player.getHP() + bag.get(i).getHpplus() > 100) {
                player.addHP(100 - player.getHP());
                bag.remove(i);
            } else {
                player.addHP(bag.get(i).getHpplus());
                bag.remove(i);
            }
        }

    }

    private void go(Command command) {
        if (!command.hasSecondWord()) {
            System.out.println("去哪");
            return;
        } else {
            String direction = command.getSecondWord();
            System.out.println("你往" + direction + "走去了");
            directionList = player.getDirection();
            if (isInTheList(direction)) {
                player.move(direction);
            } else {
                System.out.println("没有方向");
            }
        }
    }

    private void fight(Command command) {
        // boolean judge = true;
        if (player.getRoom().isThereMonster()) {
            Random rand = new Random();

            int playerAttack = player.getAttack() + rand.nextInt(10);
            if (player.getRoom().getMonster().getHP() - playerAttack < 0) {
                player.getRoom().getMonster().lossHP(player.getRoom().getMonster().getHP());
                if (playerAttack - player.getAttack() > 5) {
                    System.out.println("你暴击！！ " + playerAttack + "伤害");
                } else {
                    System.out.println("你造成了 " + playerAttack + "伤害");
                }
                System.out.println("怪物去世");
                player.getRoom().delMonster();
            } else {
                player.getRoom().getMonster().lossHP(playerAttack);
                if (playerAttack - player.getAttack() > 5) {
                    System.out.println("你暴击！！ " + playerAttack + "伤害");
                } else {
                    System.out.println("你造成了 " + playerAttack + "伤害");
                }
                System.out.println("怪物HP" + player.getRoom().getMonster().getHP());
                int monsterAttack = player.getRoom().getMonster().getAttack() + rand.nextInt(5);

                if (player.getHP() - monsterAttack <= 0) {
                    player.lossHP(player.getHP());
                    if (monsterAttack - player.getRoom().getMonster().getAttack() > 3) {
                        System.out.println("怪物暴击！！ " + monsterAttack + "伤害");
                    } else {
                        System.out.println("怪物造成了 " + monsterAttack + "伤害");
                    }
                    System.out.println("你去世");
                } else {
                    player.lossHP(monsterAttack);
                    if (monsterAttack - player.getRoom().getMonster().getAttack() > 3) {
                        System.out.println("怪物暴击！！ " + monsterAttack + "伤害");
                    } else {
                        System.out.println("怪物造成了 " + monsterAttack + "伤害");
                    }
                    System.out.println("你现在HP" + player.getHP());
                }
            }
        } else {
            System.out.println("没有怪物");
        }

    }

    /*
     * =============================================================================
     * =============================================================================
     */
    private void printWelcome() {
        System.out.println();
        System.out.println("Welcome to the World of Zuul!");
        System.out.println("World of Zuul is a new, incredibly boring adventure game.");
        System.out.println("Type 'help' if you need help.");
        System.out.println();
        System.out.println(player.getRoom().getDesc());
    }

    private void printHelp() {
        System.out.println("You are lost. You are alone. You wander");
        System.out.println("around at the university.");
        System.out.println();
        System.out.println("Your command words are:");
        parser.showCommands();
    }

    private void createMap() {
        // String name, String description, int attack, int hpplus, int weight
        theMap.addRoom(0, 0, 0, "草丛0, 0, 0", "你醒了过来，看到的是一片虚无");
        theMap.addRoom(1, 0, 0, "另一片草丛1, 0, 0", "这里和你的出生点毫无区别");
        theMap.addRoom(2, 0, 0, "还是一片草丛2, 0, 0", "你开始怀疑自己的眼睛，究竟是一模一样，还是你一直在一个房间打转");
        theMap.addRoom(3, 0, 0, "3, 0, 0", "the description");
        theMap.addRoom(4, 0, 0, "4, 0, 0", "the description");
        theMap.addRoom(3, 1, 0, "3, 1, 0", "the description");
        theMap.addRoom(3, 2, 0, "3, 2, 0", "the description");
        theMap.addRoom(1, 1, 0, "1, 1, 0", "the description");
        theMap.addRoom(1, 1, 1, "1, 1, 1", "the description");
        theMap.addItem(new COORDINATE(0, 0, 0), "rock", "一块平平无奇的石头，砸在人身上能造成巨大伤害", 10, 0, 5, false, true);
        theMap.addItem(new COORDINATE(0, 0, 0), "pie", "派，回血", 0, 10, 1, true, false);
        theMap.addMonster(new COORDINATE(0, 0, 0), "Jerry", "杰瑞看起来很凶恶", 30, 5);

        // ==================================================未完成==================================================
        // ==================================================未完成==================================================
    }

    private void trans() {
        if (player.getCoordinate().getXYZ().equals(new COORDINATE(2, 0, 0).getXYZ())) {
            player.transport();
        } else {
            System.out.println("can't trans");
        }

        //PLAYER newPlayer = new PLAYER(theMap, player.getCoordinate(), player.getHP(),player.getAttack());
        //player = newPlayer;
        
    }

    private boolean isInTheList(String direction) {
        for (String theDirection : directionList) {
            if (theDirection.equals(direction)) {
                return true;
            }
        }
        return false;

    }

}
