import java.util.Random;
import java.util.Scanner;
import java.io.*;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args)
    {
        Monopoly game = new Monopoly();
        for(int i = 0; i < (Monopoly.getNumOfPlayers()*10); i++)
        {
            Player p = game.getPlayers().get(game.getCurrentPlayer());
            if(p.getisJailed())
            {
                System.out.println(p.getName() + " is Jailed");
                game.setCurrentPlayer(game.getCurrentPlayer() + 1);
                p.setisJailed(false);
            }
            else {
                game.MovePlayer();
                game.DoTileOperation();
                game.buyHouses();
                game.setCurrentPlayer(game.getCurrentPlayer() + 1);
            }
        }
    }
}