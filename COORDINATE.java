public class COORDINATE{
    private int x;
    private int y;
    private int z;

    public COORDINATE(int x, int y, int z) {

        this.x = x;
        this.y = y;
        this.z = z;
    }

    public COORDINATE() {
        x = 0;
        y = 0;
        z = 0;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getZ() {
        return z;
    }

    public String getXYZ() {
        String n = " " + x + " " + y + " " + z;
        return n;
    }

    public void moveNorth() {
        y = y + 1;
    }

    public void moveSouth() {
        y = y - 1;
    }

    public void moveWest() {
        x = x - 1;
    }

    public void moveEast() {
        x = x + 1;
    }

    public void moveUp() {
        z = z + 1;
    }

    public void moveDown() {
        z = z - 1;
    }
}
