public class TaxSquare extends Square
{
    private int tax;

    TaxSquare(int Class, String name, int tax)
    {
        super(Class, name);
        this.tax = tax;
    }

    //Performs the Tile Operation for the Tax Square (Taxing you to death)
    @Override
    void performTileOperation(Player p)
    {
        System.out.println("Rest in peppers, you've just landed on a tax square");
        if(p.getMoney() < tax)
        {
            p.setMoney(0);
        }
        p.setMoney(p.getMoney() - tax);
        System.out.println(p.getName() + " have lost " + tax + " dollars");
        System.out.println(p.getName() + " currently has " + p.getMoney());
    }

}
