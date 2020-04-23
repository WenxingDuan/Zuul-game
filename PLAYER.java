import java.util.ArrayList;
//import java.util.Random;

public class PLAYER extends CHARACTER {
    // private String name;
    // private String description;
    // private int hp;
    // private int attack;
    private ROOM currentRoom;
    private COORDINATE currentCoordinate;
    private MAP theMap;

    public PLAYER(MAP map) {
        super("勇士", "这个世界的勇士", 100, 10);
        // this.name = "勇士";
        // this.attack = 10;
        // this.hp = 100;
        // this.description = "这个世界的勇士";
        currentCoordinate = new COORDINATE(0, 0, 0);
        theMap = map;
        currentRoom = theMap.getTheRoom(currentCoordinate);
    }

    public PLAYER(MAP map, COORDINATE theCoordinate, int hp, int attack) {
        super("勇士", "这个世界的勇士", hp, attack);
        // this.name = "勇士";
        // this.attack = 10;
        // this.hp = 100;
        // this.description = "这个世界的勇士";
        currentCoordinate = theCoordinate;
        theMap = map;
        currentRoom = theMap.getTheRoom(currentCoordinate);
    }

    public PLAYER() {
        super("勇士", "这个世界的勇士", 100, 10);
        currentCoordinate = new COORDINATE(0, 0, 0);
    }

   
    public void addHP(int n) {
        hp = hp + n;
    }

    public void addAttack(int n) {
        attack = attack + n;
    }

    public COORDINATE getCoordinate() {
        return currentCoordinate;
    }

    public ROOM getRoom() {
        return currentRoom;
    }

    public void move(String direction) {
        // if (isRightDirection(direction) == true) {

        switch (direction) {

        case "north":
            currentCoordinate.moveNorth();
            currentRoom = theMap.getTheRoom(currentCoordinate);
            break;

        case "south":
            currentCoordinate.moveSouth();
            currentRoom = theMap.getTheRoom(currentCoordinate);
            break;

        case "east":
            currentCoordinate.moveEast();
            currentRoom = theMap.getTheRoom(currentCoordinate);
            break;

        case "west":
            currentCoordinate.moveWest();
            currentRoom = theMap.getTheRoom(currentCoordinate);
            break;

        case "up":
            currentCoordinate.moveUp();
            currentRoom = theMap.getTheRoom(currentCoordinate);
            break;

        case "down":
            currentCoordinate.moveDown();
            currentRoom = theMap.getTheRoom(currentCoordinate);
            break;

        }
        //currentCoordinate = currentRoom.getCoordinate();
        // System.out.println("success");
        // } else {
        // System.out.println("unsuccess");
        // }
        // theMap.getTheRoom(currentCoordinate);

    }

    public boolean isRightDirection(String direction) {
        if (direction != "north" && direction != "south" && direction != "west" && direction != "east"
                && direction != "up" && direction != "down") {
            return false;

        } else {
            return true;
        }
    }

    public void lookDirection() {

        if (theMap.isThereDirection(currentCoordinate, "north")) {
            System.out.print("north, ");
        }
        if (theMap.isThereDirection(currentCoordinate, "south")) {
            System.out.print("south, ");
        }
        if (theMap.isThereDirection(currentCoordinate, "east")) {
            System.out.print("east, ");
        }
        if (theMap.isThereDirection(currentCoordinate, "west")) {
            System.out.print("west, ");
        }
        if (theMap.isThereDirection(currentCoordinate, "up")) {
            System.out.print("up, ");
        }
        if (theMap.isThereDirection(currentCoordinate, "down")) {
            System.out.print("down, ");
        }
    }

    public ArrayList<String> getDirection() {
        ArrayList<String> directionList = new ArrayList<String>();
        if (theMap.isThereDirection(currentCoordinate, "north")) {
            directionList.add("north");
        }
        if (theMap.isThereDirection(currentCoordinate, "south")) {
            directionList.add("south");
        }
        if (theMap.isThereDirection(currentCoordinate, "east")) {
            directionList.add("east");
        }
        if (theMap.isThereDirection(currentCoordinate, "west")) {
            directionList.add("west");
        }
        if (theMap.isThereDirection(currentCoordinate, "up")) {
            directionList.add("up");
        }
        if (theMap.isThereDirection(currentCoordinate, "down")) {
            directionList.add("down");
        }
        return directionList;
    }

    public void transport(){
        
        currentCoordinate = theMap.transports();
        currentRoom =theMap.getTheRoom(currentCoordinate) ;
        
        //theMap.printRoom();
    }

    /*
     * COORDINATE newCoordinate = new COORDINATE(); newCoordinate =
     * currentCoordinate; newCoordinate.moveNorth(); if
     * (theMap.isThereCoo(newCoordinate)) { System.out.print("north, "); }
     * newCoordinate = currentCoordinate; newCoordinate.moveSouth(); if
     * (theMap.isThereCoo(newCoordinate)) { System.out.print("south, "); }
     * newCoordinate = currentCoordinate; newCoordinate.moveEast(); if
     * (theMap.isThereCoo(newCoordinate)) { System.out.print("east, "); }
     * newCoordinate = currentCoordinate; newCoordinate.moveWest(); if
     * (theMap.isThereCoo(newCoordinate)) { System.out.print("west, "); }
     * newCoordinate = currentCoordinate; newCoordinate.moveUp(); if
     * (theMap.isThereCoo(newCoordinate)) { System.out.print("up, "); }
     * newCoordinate = currentCoordinate; newCoordinate.moveDown(); if
     * (theMap.isThereCoo(newCoordinate)) { System.out.print("down, "); }
     * 
     * System.out.println("");
     */

}
