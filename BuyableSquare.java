import java.sql.SQLOutput;

public class BuyableSquare extends Square
{
    private boolean isOwned = false;
    private int price;

    BuyableSquare(int Class, String name, int price, boolean isOwned)
    {
        super(Class, name);
        this.price = price;
        this.isOwned = isOwned;
    }

    //Get's price
    int getPrice()
    {
        return price;
    }

    //Returns the isOwned variable. used for if the property is owned or not
    boolean getIsOwned()
    {
        return isOwned;
    }

    //Sets the isOwned variable
    void setisOwned(boolean isOwned)
    {
        this.isOwned = isOwned;
    }

    //Get's the Owner of the property by grabbing the name of the tile and matching it with the name of a tile in the other players ownedproperties array
    Player getOwnerOfProperty(Player currentplayer)
    {
        Player p = null;
        for(int i = 0; i < Monopoly.getPlayers().size(); i++)
        {
            for(int j = 0; j < Monopoly.getPlayers().get(i).getOwnedProperties().size(); j++)
            {
                if(currentplayer.getCurrentSquare().getName().equals(Monopoly.getPlayers().get(i).getOwnedProperties().get(j).getName()))
                {
                    p = Monopoly.getPlayers().get(i);
                }
            }
        }
        return p;
    }

}
