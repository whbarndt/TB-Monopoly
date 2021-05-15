public class RailroadSquare extends BuyableSquare
{
    private int rent;

    RailroadSquare(int Class, String name, int price, int rent, boolean isOwned)
    {
        super(Class, name, price, isOwned);
        this.rent = rent;
    }

    public int getRent()
    {
        return rent;
    }

    public void setRent(int rent)
    {
        this.rent = rent;
    }

    //Perform's tile operation for a RailroadSquare (Buying, Rent based upon amount of owned properties)
    @Override
    void performTileOperation(Player p)
    {
        if(!getIsOwned())
        {
            System.out.println("Would " + p.getName() + " like to buy the property");
            String answer = Monopoly.scanner.next();
            if (answer.toLowerCase().equals("y") || answer.toLowerCase().equals("yes"))
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
            Player p2 = ((RailroadSquare) p.getCurrentSquare()).getOwnerOfProperty(p);
            System.out.println("This property is owned, get rekt scrub");
            int counter = 0;

            RailroadSquare rs = (RailroadSquare) Monopoly.getBoardSquare(5);
            if (rs.getIsOwned()) {
                counter++;
            }
            RailroadSquare rs2 = (RailroadSquare) Monopoly.getBoardSquare(15);
            if (rs.getIsOwned()) {
                counter++;
            }
            RailroadSquare rs3 = (RailroadSquare) Monopoly.getBoardSquare(25);
            if (rs.getIsOwned()) {
                counter++;
            }
            RailroadSquare rs4 = (RailroadSquare) Monopoly.getBoardSquare(35);
            if (rs.getIsOwned()) {
                counter++;
            }

            if (counter != 0)
            {
                int total = 25;
                for (int i = 1; i < counter; i++)
                    total += total;


                if (p.getMoney() < total) {
                    System.out.println(p.getName() + " lost all of his money to " + p2.getName());
                    p2.setMoney(p.getMoney() + p2.getMoney());
                    p.setMoney(0);
                }
                else {
                    System.out.println(p.getName() + " pays: " + total + " to " + p2.getName());
                    p.setMoney(p.getMoney() - total);
                    p2.setMoney(p2.getMoney() + total);
                    System.out.println(p.getName() + " currently has " + p.getMoney());
                    System.out.println(p2.getName() + " currently has " + p2.getMoney());
                }
            }

        }
    }

}
