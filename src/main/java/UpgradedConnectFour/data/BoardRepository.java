package UpgradedConnectFour.data;

import UpgradedConnectFour.model.Space;

import java.util.ArrayList;

public class BoardRepository {

    private ArrayList<ArrayList<Space>> theBoard = new ArrayList<>();

    public BoardRepository() {

        for (int i = 0; i < 7; i++) {
            ArrayList<Space> column = new ArrayList<>();
            for (int j = 0; j < 7; j++) {
                column.add(j, new Space());
            }
            theBoard.add(i, column);
        }
    }

    public void clearBoard(){

        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 7; j++) {
                getSpace(i,j).setTile(0);
            }
        }

        /*
        ArrayList<ArrayList<Space>> newBoard = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            ArrayList<Space> column = new ArrayList<>();
            for (int j = 0; j < 7; j++) {
                column.add(j, new Space());
            }
            newBoard.add(i, column);
        }
        return newBoard;

         */
    }

    public ArrayList<ArrayList<Space>> getTheBoard() {
        return theBoard;
    }

    public Space getSpace(int i, int j){//i is column, j is row
        return theBoard.get(i).get(j);
    }

    public String getABCordinates(int i, int j){
        String a = getCharForNumber(i);
        String b = getCharForNumber(j);
        return a.concat(b);
    }

    public String getCharForNumber(int i) {
        //copied from StackOverflow
        return i >= 0 && i < 27 ? String.valueOf((char)(i + 65)) : null;
    }

    public boolean isColumnPlayable(int column){
        if(getSpace(column,0).getTaken() == 0){
            return true;
        } else{
            return false;
        }
    }

    public void makeMove(int column, int player){
        int j=0;
        while(getSpace(column,j).getTaken() == 0){
            j++;
            if(j==7){
                break;
            }
        }
        System.out.println("Setting in row: "+j);
        getSpace(column,j-1).setTile(player);
    }



    public boolean checkBoard(int turn, int inRow){  //j is vertical i is horizontal
        String checking = "";
        if(turn == 1){
            for(int i=0;i<inRow;i++){
                checking = checking+"H";
            }
        }
        else{
            for(int i=0;i<inRow;i++){
                checking = checking+"C";
            }
        }


        ArrayList<String> possibilities = new ArrayList<>();
        String line = "";
        for(int i=0;i<theBoard.size();i++){ //adding in all vertical lines
            for(int j=0; j<theBoard.get(i).size();j++){
                line = line + getSpace(i,j).getTile();
            }
            possibilities.add(line);
            line = "";
        }

        line = "";
        for (int j = 0; j < theBoard.get(1).size(); j++) { //adding in all horizontal lines
            for (int i = 0; i < theBoard.size(); i++) {
                line = line + getSpace(i,j).getTile();
            }
            possibilities.add(line);
            line = "";
        }
        //toRight
        possibilities.add(step(0,2,true));
        possibilities.add(step(0,1,true));
        possibilities.add(step(0,0,true));
        possibilities.add(step(1,0,true));
        possibilities.add(step(2,0,true));
        possibilities.add(step(3,0,true));

        //toLeft
        possibilities.add(step(2,6,false));
        possibilities.add(step(1,6,false));
        possibilities.add(step(0,6,false));
        possibilities.add(step(0,5,false));
        possibilities.add(step(0,4,false));
        possibilities.add(step(0,3,false));

        for(int i=0;i<possibilities.size();i++){ ///checking for 4 in a row
            if(possibilities.get(i).contains(checking))
                return true;
        }

        return false;
    }

    public String step(int j, int i, boolean toRight){
        String line = "";
        while(j<=6 && j>=0 && i<=6 && i>=0){
            line = line+getSpace(j,i).getTile();
            if(toRight){
                j++;
                i++;
            } else{
                j++;
                i--;
            }
        }

        return line;
    }


}
