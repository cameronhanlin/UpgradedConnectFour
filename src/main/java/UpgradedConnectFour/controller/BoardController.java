package UpgradedConnectFour.controller;

import UpgradedConnectFour.data.BoardRepository;
import UpgradedConnectFour.model.Space;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.jws.WebParam;
import java.util.ArrayList;
import java.util.Random;

@Controller
public class BoardController {

    private Random rand = new Random();
    private BoardRepository boardRepository = new BoardRepository();
    private int turns;

    @RequestMapping("/")
    public String displayHome(ModelMap modelMap) {
        boardRepository.clearBoard();
        modelMap.put("hidden", true);
        turns = 1;
        return "home";
    }

    @RequestMapping("/playerturn")
    public String playerTurn(ModelMap modelMap){


        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 7; j++) {
                modelMap.put(boardRepository.getABCordinates(i,j), boardRepository.getSpace(i,j).getTile());
            }
        }
        System.out.println("back to playerturn");


        if(turns>49){
            modelMap.put("hidden", false);
        } else {
            modelMap.put("hidden", true);
        }
        modelMap.put("isWinner", boardRepository.checkBoard(2,4));



        return "playerturn";
    }


    @RequestMapping("/computerturn/{playerMove}")
    public String computerTurn(@PathVariable int playerMove, ModelMap modelMap){


        System.out.println("In computerturn, the playerMove is "+playerMove);
        System.out.println(!boardRepository.isColumnPlayable(playerMove));

        if(!boardRepository.isColumnPlayable(playerMove)){
            //TODO something to let player know they cant go into that column, maybe a separate page?
            System.out.println("The human is trying to play in a place it cant.");

            for (int i = 0; i < 7; i++) {
                for (int j = 0; j < 7; j++) {
                    modelMap.put(boardRepository.getABCordinates(i, j), boardRepository.getSpace(i, j).getTile());
                }
            }

            modelMap.put("computermove", "Not making a move because you tried to overfill a column");
        }else {
            System.out.println("Human move column: "+(playerMove+1));
            boardRepository.makeMove(playerMove, 1);
            turns++;
            modelMap.put("isWinner", boardRepository.checkBoard(1,4));

            for (int i = 0; i < 7; i++) {
                for (int j = 0; j < 7; j++) {
                    modelMap.put(boardRepository.getABCordinates(i, j), boardRepository.getSpace(i, j).getTile());
                }
            }


            int computerMove;
            do {
                computerMove = rand.nextInt(7);
            } while (!boardRepository.isColumnPlayable(computerMove) && turns<=49);




            if(turns<=49){

                boardRepository.makeMove(computerMove, 2);
                turns++;

                System.out.println("The Computer Move: " + (computerMove + 1));
                modelMap.put("computermove", computerMove + 1);
            }


        }

        if(turns>49){
            modelMap.put("hidden", false);
        } else {
            modelMap.put("hidden", true);
        }




        return "computerturn";

    }

}
