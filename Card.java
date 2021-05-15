public class Card
{
    private String cardname;

    Card(String s)
    {
        cardname = s;
    }

    public String getCardname()
    {
        return cardname;
    }

    //Perform's card operation. Based upon which card tile you land on it'll do that cards operation
    static void performCardOperation(Player player, String cardType)
    {
        Card[] cards = Monopoly.getChanceCards();
        int i = Monopoly.getRandomValue(5) - 1;

        if(cardType.equals("Chance"))
        {
            switch (cards[i].getCardname())
            {
                case "Take A Walk On The Boardwalk":
                    System.out.println("You got the 'Take A Walk On The Boardwalk' chance card");
                    player.setCurrentSquareIndex(39);
                    System.out.println("You landed on Boardwalk");
                    player.getCurrentSquare().performTileOperation(player);
                    break;
                case "Advance to Illinois Ave.":
                    System.out.println("You got the 'Advance to Illinois Ave.' chance card");
                    player.setCurrentSquareIndex(24);
                    System.out.println("You landed on " + player.getCurrentSquare().getName());
                    player.getCurrentSquare().performTileOperation(player);
                    break;
                case "Advance to Nearest Utility":
                    System.out.println("You got the 'Advance to Nearest Utility' chance card");
                    if (player.getCurrentSquareIndex() < 12 || player.getCurrentSquareIndex() > 28)
                        player.setCurrentSquareIndex(12);
                    else
                        player.setCurrentSquareIndex(28);
                    System.out.println("You landed on " + player.getCurrentSquare().getName());
                    player.getCurrentSquare().performTileOperation(player);
                    break;
                case "Advance to Nearest Railroad":
                    System.out.println("You got the 'Advance to Nearest Railroad' chance card");
                    if (player.getCurrentSquareIndex() < 5)
                        player.setCurrentSquareIndex(5);
                    else if (player.getCurrentSquareIndex() > 5 && player.getCurrentSquareIndex() < 15)
                        player.setCurrentSquareIndex(15);
                    else if (player.getCurrentSquareIndex() > 15 && player.getCurrentSquareIndex() < 25)
                        player.setCurrentSquareIndex(25);
                    else if (player.getCurrentSquareIndex() > 25 && player.getCurrentSquareIndex() < 35)
                        player.setCurrentSquareIndex(35);
                    else
                        player.setCurrentSquareIndex(5);
                    System.out.println("You landed on " + player.getCurrentSquare().getName());
                    player.getCurrentSquare().performTileOperation(player);
                    break;
                case "You Won The Lottery":
                    System.out.println("You got the 'You Won The Lottery' chance card");
                    player.setMoney(player.getMoney() + 1000);
                    System.out.println("You gained $1000!!!");
                    player.getCurrentSquare().performTileOperation(player);
                    break;
            }
        }
        else
        {
            switch (cards[i].getCardname())
            {
                case "Advance to Go":
                    System.out.println("You got the 'Advance to Go' community card");
                    player.setCurrentSquareIndex(0);
                    player.setMoney(player.getMoney() + 200);
                    System.out.println("You went to Go!");
                case "Doctors Fees":
                    System.out.println("You got the 'Doctors Fees' community card");
                    player.setMoney(player.getMoney() - 100);
                    System.out.println("You lost $100...");
                case "Go to Jail":
                    System.out.println("You got the 'Go to Jail' community card");
                    player.setCurrentSquareIndex(10);
                    player.setisJailed(true);
                    System.out.println("You went to jail...");
                case "School Fees":
                    System.out.println("You got the 'School Fees' community card");
                    player.setMoney(player.getMoney() - 200);
                    System.out.println("You lost $200...");
                case "PFD":
                    System.out.println("You got the 'PFD' community card");
                    player.setMoney(player.getMoney() + 350);
                    System.out.println("You gained $350!");
            }
        }
    }
}
