package UpgradedConnectFour.model;

public class Space {
    private String tile;
    private int taken;

    public Space() {
        tile = "E";
        taken = 0;
    }

    public String getTile() {
        return tile;
    }

    public void setTile(int player) {
        taken = player;
        if (player == 1){
            tile = "H";
        } else if (player == 2){
            tile = "C";
        } else {
            tile ="E";
        }


    }

    public int getTaken() {
        return taken;
    }


}