package UpgradedConnectFour.model;

public class Space {
    private String tile;
    private int taken;

    public Space() {
        tile = "_";
        taken = 0;
    }

    public String getTile() {
        return tile;
    }

    public void setTile(int play) {
        taken = play;
        if (play == 1)
            tile = "X";
        else
            tile = "O";
    }

    public int getTaken() {
        return taken;
    }


}