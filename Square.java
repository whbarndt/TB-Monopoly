public class Square
{
    protected int Class;
    protected String name;

    Square(int Class, String name)
    {
        this.Class = Class;
        this.name = name;
    }

    public String getName()
    {
        return name;
    }

    int getSquareClass()
    {
        return Class;
    }

    //Perform Tile operations on the outcast square
    void performTileOperation(Player p)
    {
        Square sq = p.getCurrentSquare();
        switch(sq.getName()) {
            case "GO":
                p.setMoney(p.getMoney() + 200);
                break;
            case "Free Parking":
                p.setMoney(p.getMoney() + 20);
                break;
            case "Go to Jail":
                p.setCurrentSquareIndex(10);
                p.setisJailed(true);
                break;
            case "Chance":
                Card.performCardOperation(p, "Chance");
                break;
            case "Community":
                Card.performCardOperation(p, "Community");
                break;

        }
    }
}
