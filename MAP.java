import java.util.ArrayList;
import java.util.Random;

public class MAP {

    private ArrayList<COORDINATE> coordinates;
    private ArrayList<ROOM> rooms;

    public MAP() {
        coordinates = new ArrayList<COORDINATE>();
        rooms = new ArrayList<ROOM>();
    }

    public void addRoom(int x, int y, int z, String name, String description) {
        int judge = 0;
        ROOM room = new ROOM(x, y, z, name, description);
        for (COORDINATE coordinate : coordinates) {
            if (coordinate.getXYZ().equals(room.getCoordinate().getXYZ())) {
                judge = 1;
            }
        }
        if (judge == 0) {
            rooms.add(room);
            coordinates.add(room.getCoordinate());
            /*
             * System.out.println(rooms); System.out.println(coordinates);
             */
        } else {
            System.out.println("same room, error");
        }

    }

    public ROOM getTheRoom(COORDINATE inputCoo) {
        for (ROOM theRoom : rooms) {
            if (theRoom.getCoordinate().getXYZ().equals(inputCoo.getXYZ())) {
                return theRoom;
            }
        }
        System.out.println(inputCoo.getXYZ() + "不存在");
        return null;
    }

    public ArrayList<COORDINATE> getAllCoordinates() {
        return coordinates;
    }

    public boolean isThereCoo(COORDINATE coordinate) {
        boolean judge = false;
        for (COORDINATE theCoordinate : coordinates) {
            if (theCoordinate.getXYZ().equals(coordinate.getXYZ())) {
                judge = true;
            }
        }
        return judge;
    }

    public boolean isThereCooXYZ(int x, int y, int z) {
        COORDINATE coordinate = new COORDINATE(x, y, z);
        boolean judge = false;
        for (COORDINATE theCoordinate : coordinates) {
            if (theCoordinate.getXYZ().equals(coordinate.getXYZ())) {
                judge = true;
            }
        }
        return judge;
    }

    public boolean isThereDirection(COORDINATE theCoordinate, String direction) {
        switch (direction) {
        case "north":
            if (isThereCooXYZ(theCoordinate.getX(), theCoordinate.getY() + 1, theCoordinate.getZ())) {
                return true;
            } else {
                return false;
            }
        case "south":
            if (isThereCooXYZ(theCoordinate.getX(), theCoordinate.getY() - 1, theCoordinate.getZ())) {
                return true;
            } else {
                return false;
            }
        case "west":
            if (isThereCooXYZ(theCoordinate.getX() - 1, theCoordinate.getY(), theCoordinate.getZ())) {
                return true;
            } else {
                return false;
            }
        case "east":
            if (isThereCooXYZ(theCoordinate.getX() + 1, theCoordinate.getY(), theCoordinate.getZ())) {
                return true;
            } else {
                return false;
            }
        case "up":
            if (isThereCooXYZ(theCoordinate.getX(), theCoordinate.getY(), theCoordinate.getZ() + 1)) {
                return true;
            } else {
                return false;
            }
        case "down":
            if (isThereCooXYZ(theCoordinate.getX(), theCoordinate.getY(), theCoordinate.getZ() - 1)) {
                return true;
            } else {
                return false;
            }
        }
        return false;

    }

    public void addItem(COORDINATE theCoordinate, String name, int attack, boolean eatable, boolean moveable,
            boolean carryable, String description, int hpplus) {
        for (int i = 0; i < rooms.size(); i++) {
            if (rooms.get(i).getCoordinate().getXYZ().equals(theCoordinate.getXYZ())) {
                rooms.get(i).addItem(name, attack, eatable, moveable, carryable, description, hpplus);
            }
        }
    }

    public void addItem(COORDINATE theCoordinate, String name, String description, int attack, int hpplus, int weight,
            boolean eatable, boolean carryable) {
        for (int i = 0; i < rooms.size(); i++) {
            if (rooms.get(i).getCoordinate().getXYZ().equals(theCoordinate.getXYZ())) {
                rooms.get(i).addItem(name, description, attack, hpplus, weight, eatable, carryable);
            }
        }
    }

    public void addMonster(COORDINATE theCoordinate, String name, String description, int hp, int attack) {
        for (int i = 0; i < rooms.size(); i++) {
            if (rooms.get(i).getCoordinate().getXYZ().equals(theCoordinate.getXYZ())) {
                rooms.get(i).addMonster(name, description, hp, attack);
            }
        }
    }

    public COORDINATE transports() {
        Random rand = new Random();
        return coordinates.get(rand.nextInt(rooms.size()));
    }
/*
    public void printRoom(){
        for (ROOM theroom:rooms){
            System.out.println(theroom.getName());
        }
    }*/
}
