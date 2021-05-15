import java.util.ArrayList;

public class Player
{
    private String name = "";
    private String playerpiece;
    private int money = 1500;
    private int currentSquare = 0;
    private ArrayList<Square> ownedproperties = new ArrayList<Square>();
    private boolean Jailed = false;

    Player(String n, String pp, int m, int cS)
    {
        name = n;
        playerpiece = pp;
        money = m;
        currentSquare = cS;
    }

    void setName(String n)
    {
        name = n;
    }

    String getName()
    {
        return name;
    }

    void setPlayerpiece(String s)
    {
        playerpiece = s;
    }

    String getPlayerpiece()
    {
        return playerpiece;
    }

    void setMoney(int m)
    {
        money = m;
    }

    int getMoney()
    {
        return money;
    }

    void setCurrentSquareIndex(int cS)
    {
        currentSquare = cS;
    }

    int getCurrentSquareIndex()
    {
        return currentSquare;
    }

    Square getCurrentSquare()
    {
        return Monopoly.getBoardSquare(currentSquare);
    }

    boolean getisJailed()
    {
        return Jailed;
    }

    void setisJailed(boolean isJailed)
    {
        Jailed = isJailed;
    }

    ArrayList<Square> getOwnedProperties()
    {
        return ownedproperties;
    }

    void addProperty(Square s)
    {
        ownedproperties.add(s);
    }
}
