public class PropertySquare extends BuyableSquare
{
    private String color;
    private int rent;
    private int houseprices[];
    private int housecount = 0;

    PropertySquare(int Class, String name, String color, int price, int rent, int houseprices[], boolean isOwned)
    {
        super(Class, name, price, isOwned);
        this.color = color;
        this.rent = rent;
        this.houseprices = houseprices;
        isOwned = false;
    }

    void setRent(int r) {
        rent = r;
    }

    int getRent() {
        return rent;
    }

    int[] getHouseprices() {
        return houseprices;
    }

    int getHousePriceFromArray(int x)
    {
        return houseprices[x];
    }

    int getHousecount()
    {
        return housecount;
    }

    void setHousecount(int hc)
    {
        housecount = hc;
    }

    //Perform's the tile operation for a Property Square (Buying, Rent)
    @Override
    void performTileOperation(Player p)
    {

        if (!getIsOwned())
        {
            System.out.println("Would " + p.getName() + " like to buy the property");
            String answer = Monopoly.scanner.next();
            if (answer.toLowerCase().equals("y") || answer.toLowerCase().equals("yes"))
            {
                if (p.getMoney() < getPrice())
                {
                    System.out.println("Whoops can't buy this lol");
                    System.out.println(p.getName() + " currently has " + p.getMoney());
                }
                else {
                    p.setMoney(p.getMoney() - getPrice());
                    p.addProperty(p.getCurrentSquare());
                    setisOwned(true);
                    System.out.println(p.getName() + " purchased this property");
                    System.out.println(p.getName() + " currently has " + p.getMoney());
                }
            }
            else {
                System.out.println(p.getName() + " decided not to buy " + p.getCurrentSquare().getName());
            }
        }
        else {
            System.out.println("This property is owned, get rekt scrub");
            PropertySquare ps = (PropertySquare) p.getCurrentSquare();
            Player p2 = ((PropertySquare) p.getCurrentSquare()).getOwnerOfProperty(p);

            if(p.getMoney() < ps.getRent())
            {
                p2.setMoney(p.getMoney() + p2.getMoney());
                p.setMoney(0);

                System.out.println("You've lost all your money to " + p2.getName());
                System.out.println(p.getName() + " currently has " + p.getMoney());
                System.out.println(p2.getName() + " currently has " + p2.getMoney());
            }
            else {
                p2.setMoney(p2.getMoney() + ps.getRent());
                p.setMoney(p.getMoney() - ps.getRent());
                System.out.println(p.getName() + " lost " + ps.getRent() + " dollars to " + p2.getName());
                System.out.println(p.getName() + " currently has " + p.getMoney());
                System.out.println(p2.getName() + " currently has " + p2.getMoney());
            }
        }
    }
}
