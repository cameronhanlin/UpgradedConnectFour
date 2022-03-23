package UpgradedConnectFour.data;

import UpgradedConnectFour.model.Space;

import java.util.ArrayList;

public class BoardRepository {

    private ArrayList<ArrayList<Space>> theBoard = new ArrayList<>();

    public BoardRepository() {
        theBoard = clearBoard();
    }

    public ArrayList<ArrayList<Space>> clearBoard(){
        ArrayList<ArrayList<Space>> newBoard = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            ArrayList<Space> column = new ArrayList<>();
            for (int j = 0; j < 7; j++) {
                column.add(j, new Space());
            }
            newBoard.add(i, column);
        }
        return newBoard;
    }

    public Space getSpace(int i, int j){//i is column, j is row
        return theBoard.get(i).get(j);
    }




}
