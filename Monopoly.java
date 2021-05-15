import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Monopoly {

    public static Scanner scanner = new Scanner(System.in);

    private static ArrayList<Player> players = new ArrayList<Player>();
    private String pieces[] = new String[5];

    private static Square board[] = new Square[40];
    private static Card communityCards[] = new Card[5];
    private static Card chanceCards[] = new Card[5];

    private int currentPlayer = 0;
    private static int numOfPlayers;

    public Monopoly()
    {
        LoadBoard();
        LoadCommunityCards();
        LoadChanceCards();
        LoadPieces();

        AssignPlayers();
    }

    static int getNumOfPlayers()
    {
        return numOfPlayers;
    }

    void setCurrentPlayer(int i)
    {
        if(currentPlayer == players.size() - 1)
            currentPlayer = 0;
        else
            currentPlayer = i;
    }

    static ArrayList<Player> getPlayers()
    {
        return players;
    }

    //Get's current player
    public int getCurrentPlayer()
    {
        return currentPlayer;
    }

    //Get's board
    public static Square getBoardSquare(int i)
    {
        return board[i];
    }

    //Get's Community Cards
    public static Card[] getCommunityCards()
    {
        return communityCards;
    }

    //Get's Chance Cards
    public static Card[] getChanceCards()
    {
        return chanceCards;
    }

    //Buy's houses
    void buyHouses()
    {
        Player p = players.get(currentPlayer);

        if(!p.getOwnedProperties().isEmpty())
        {
            String answer = "n";

            while (answer.toLowerCase().equals("n") || answer.toLowerCase().equals("no")) {
                System.out.println("These are the properties " + p.getName() +" owns");
                for (int i = 0; i < p.getOwnedProperties().size(); i++) {
                    System.out.println(i + ". " + p.getOwnedProperties().get(i).getName());
                }
                System.out.println("Would " + p.getName() + " like to purchase a house? (y or n)");
                String answer2 = scanner.next();
                if (!answer2.toLowerCase().equals("y") || answer2.toLowerCase().equals("yes"))
                    System.out.println("Ok, " + p.getName() + " are not purchasing a house at this point");
                else {
                    System.out.println("Which property would " + p.getName() + " like to put a house on (Enter the number next to it)");
                    int property = scanner.nextInt();
                    if (p.getOwnedProperties().get(property) instanceof RailroadSquare || p.getOwnedProperties().get(property) instanceof UtilitySquare)
                        System.out.println("Sorry " + p.getName() + "cannot put a house on this property");
                    else {
                        PropertySquare ps = (PropertySquare) p.getOwnedProperties().get(property);
                        if (ps.getHousecount() < 4) {
                            if (!(p.getMoney() < 50)) {
                                p.setMoney(p.getMoney() - 50);
                                ps.setHousecount(ps.getHousecount() + 1);
                                ps.setRent(ps.getHousePriceFromArray(ps.getHousecount() - 1));
                                System.out.println(p.getName() + " put one house on " + ps.getName() + " with " + ps.getHousecount() + " in total");
                                System.out.println(p.getName() + " currently have " + p.getMoney());
                            } else {
                                System.out.println(p.getName() + " do no have enough money to buy a house for this property.");
                                System.out.println(p.getName() + "currently have " + p.getMoney());
                            }
                        } else
                            System.out.println(p.getName() + " cannot buy anymore houses for this property.");

                    }
                }
                System.out.println("Is " + p.getName() + " done buying houses? -> (y or n)");
                answer = scanner.next();
            }
        } else
            System.out.println(p.getName() + " do not have any properties to buy houses for.");
    }

    //Based up on the tile landed on, perform the operation of that tile type
    void DoTileOperation()
    {
        Square s = board[players.get(currentPlayer).getCurrentSquareIndex()];
        s.performTileOperation(players.get(currentPlayer));
    }

    //Reads the file and potentially catches an exception
    BufferedReader getFile(String filename)
    {
        BufferedReader br = null;
        try
        {
           br = new BufferedReader(new FileReader(filename));
        } catch (Exception e)
        {
            System.out.println("ERROR " + e.getMessage() + " in getFile()");
            System.exit(1);
        }

        return br;
    }

    // Reads the line from the buffered reader and potentially catches an exception
    String getLine(BufferedReader br)
    {
        String temp = "";
        try
        {
            temp = br.readLine();
        } catch (Exception e)
        {
            System.out.println("ERROR " + e.getMessage() + " in getLine()");
            System.exit(1);
        }

        return temp;
    }

    // Returns the parseInt function so I don't have to type so much in one line lol
    int intoInt(String s)
    {
        return (int) Double.parseDouble(s);
    }

    int[] partArrintoAnother(String[] arr)
    {
        int[] temp = new int[4];

        for(int i = 5; i < arr.length; i++)
        {
            temp[i - 5] = intoInt(arr[i]);
        }

        return temp;
    }

    // Loads the Board squares into the board array
    void LoadBoard()
    {
        try
        {
            Scanner scnr = new Scanner(new FileInputStream("src\\squares.txt"));
            //String temp = "";
            //String[] temp2 = new String[8];

            for(int i = 0; i < board.length; i++)
            {
                String temp = scnr.nextLine();
                String[] temp2 = temp.split(",");

                switch (temp2[0])
                {
                    case "1":
                        board[i] = new Square(1,temp2[1]);
                        break;
                    case "2":
                        board[i] = new PropertySquare(2, temp2[1], temp2[2], (int) intoInt(temp2[3]), intoInt(temp2[4]), partArrintoAnother(temp2), false);
                        break;
                    case "3":
                        board[i] = new TaxSquare(3, temp2[1], intoInt(temp2[2]));
                        break;
                    case "4":
                        board[i] = new RailroadSquare(4, temp2[1], intoInt(temp2[2]), intoInt(temp2[3]), false);
                        break;
                    case "5":
                        board[i] = new UtilitySquare(5, temp2[1], intoInt(temp2[2]), intoInt(temp2[3]), false);
                        break;
                }
            }
        }
        catch (Exception e)
        {
            System.out.println("ERROR " + e.getMessage() + " in LooadBoard()");
            System.exit(1);
        }
    }

    // Loads the Community cards from the text file into an Array
    void LoadCommunityCards()
    {
        BufferedReader br = getFile("src\\community.txt");

        for(int i = 0; i < communityCards.length; i++)
            communityCards[i] = new Card(getLine(br));
    }

    // Loads the Chance cards from the text file into an Array
    void LoadChanceCards()
    {
        BufferedReader br = getFile("src\\chance.txt");

        for(int i = 0; i < chanceCards.length; i++)
            chanceCards[i] = new Card(getLine(br));
    }

    // Loads the Monopoly pieces into an array
    void LoadPieces()
    {
        BufferedReader br = getFile("src\\pieces.txt");

        for(int i = 0; i < pieces.length; i++)
        {
            int randomPosition = 0;
            int counter = 0;

            while(counter < 5)
            {
                randomPosition = getRandomValue(5) - 1;

                if(pieces[randomPosition] == null)
                {
                    pieces[randomPosition] = getLine(br);
                    break;
                }

                if(++counter == 100)
                    System.out.println("ERROR: Piece assignment timed out on index " + i);
            }
        }
    }

    // Initialize players
    void AssignPlayers()
    {
        // Gets the number of players
        do
        {
            System.out.println("How many players are playing? -> (Please enter a number) ");
            numOfPlayers = scanner.nextInt();

            if(numOfPlayers > 5 || numOfPlayers < 0)
                System.out.println("Invalid input. Enter (1 - 5).");
        }
        while(numOfPlayers > 5 || numOfPlayers < 0);

        // Assigns the appropriate data to each player
        for(int i = 0; i < numOfPlayers; i++)
        {
            // Give the player a name
            System.out.print("Enter player " + (i + 1) + "'s name -> ");
            String name = scanner.next();

            // Give the player a random piece
            String playerpiece = pieces[i];

            players.add(i, new Player(name, playerpiece, 1500, 0));
        }
    }

    // Move the player based upon a random value (dice roll)
    void MovePlayer()
    {
        System.out.println("Rolling:");
        int roll_dieX = getRandomValue(6);
        int roll_dieY = getRandomValue(6);
        System.out.println(players.get(currentPlayer).getName() + " rolled " + roll_dieX + " and " + roll_dieY);

        Player p = players.get(currentPlayer);
        int endTile = p.getCurrentSquareIndex() + roll_dieX + roll_dieY;

        // 39 is the actual length (0 - 39) of the board
        if(endTile > 39)
        {
            p.setCurrentSquareIndex((endTile - 39) - 1);
            p.setMoney(p.getMoney() + 200);
        }
        else
            p.setCurrentSquareIndex(endTile);

        System.out.println(p.getName() + " has landed on: " + p.getCurrentSquare().getName());
    }

    // Returns a random value from 1 to (range) value
    static int getRandomValue(int range)
    {
        Random r = new Random();
        return r.nextInt(range) + 1;
    }
}
