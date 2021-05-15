public class UtilitySquare extends BuyableSquare
{
    private int multiplierValue;

    UtilitySquare(int Class, String name, int price, int multiplierValue, boolean isOwned)
    {
        super(Class, name, price, isOwned);
        this.multiplierValue = multiplierValue;
    }

    public int getMultiplierValue()
    {
        return multiplierValue;
    }

    //Perform's tile operation for a UtilitySquare (Buying, Then Taking money based upon roll and multiplier)
    @Override
    void performTileOperation(Player p)
    {
        if(!getIsOwned())
        {
            System.out.println("Would " + p.getName() + " like to buy the property");
            String answer = Monopoly.scanner.next();
            if (answer.toLowerCase().equals("y")|| answer.toLowerCase().equals("yes"))
            {
                if(p.getMoney() < getPrice())
                {
                    System.out.println("Whoops can't buy this lol");
                }
                else
                {
                    p.setMoney(p.getMoney() - getPrice());
                    p.addProperty(p.getCurrentSquare());
                    setisOwned(true);
                    System.out.println(p.getName() + " currently has " + p.getMoney());
                }
            }
            else
            System.out.println(p.getName() + " decided not to buy " + p.getCurrentSquare().getName());
        }
        else
        {
            System.out.println("This property is owned, get rekt scrub");
            UtilitySquare s =  (UtilitySquare) p.getCurrentSquare();
            Player p2 = ((UtilitySquare) p.getCurrentSquare()).getOwnerOfProperty(p);
            int diceA = Monopoly.getRandomValue(6);
            int diceB = Monopoly.getRandomValue(6);
            System.out.println(p.getName() + " rolled: " + diceA + " and " + diceB);
            if(p.getMoney() < (diceA + diceB) * s.getMultiplierValue()) {
                System.out.println(p.getName() + " lost all of their money to " + p2.getName());
                p2.setMoney(p.getMoney() + p2.getMoney());
                p.setMoney(0);
                System.out.println(p.getName() + " currently has " + p.getMoney());
                System.out.println(p2.getName() + " currently has " + p2.getMoney());
            }
            else {
                System.out.println(p.getName() + " pays: " + (diceA + diceB) * s.getMultiplierValue() + " to " + p2.getName());
                p.setMoney(p.getMoney() - ((diceA + diceB) * s.getMultiplierValue()));
                p2.setMoney(p2.getMoney() + ((diceA + diceB) * s.getMultiplierValue()));
                System.out.println(p.getName() + "currently has " + p.getMoney());
                System.out.println(p2.getName() + " currently has " + p2.getMoney());
            }
        }
    }
}
