import java.util.Scanner;

public class RPSLogic
{
    public static int determineWinner(String playerMove, String computerMove)
    {
        if (playerMove.equalsIgnoreCase(computerMove))
        {
            return 0;
        }
        if ((playerMove.equalsIgnoreCase("r") && computerMove.equalsIgnoreCase("s"))
                || (playerMove.equalsIgnoreCase("p") && computerMove.equalsIgnoreCase("r"))
                || (playerMove.equalsIgnoreCase("s") && computerMove.equalsIgnoreCase("p")))
        {
            return 1;
        }
        return -1;
    }

    public static String describeRound(String playerMove, String computerMove)
    {
        if (playerMove.equalsIgnoreCase(computerMove))
        {
            return moveName(playerMove) + " vs. " + moveName(computerMove) + ".";
        }

        String winningMove = determineWinner(playerMove, computerMove) == 1
                ? playerMove : computerMove;
        String losingMove = winningMove.equalsIgnoreCase(playerMove) ? computerMove : playerMove;
        String rule = winningMove.equalsIgnoreCase("r") ? " breaks "
                : winningMove.equalsIgnoreCase("p") ? " covers " : " cuts ";
        return moveName(winningMove) + rule + moveName(losingMove).toLowerCase() + ".";
    }

    private static String moveName(String move)
    {
        if (move.equalsIgnoreCase("r"))
        {
            return "Rock";
        }
        if (move.equalsIgnoreCase("p"))
        {
            return "Paper";
        }
        return "Scissors";
    }

    public static void main(String[] args)
    {
        String playerMove = "";
        String computerMove = "";
        String playAgain = "";
        int done = 0;
        Scanner input = new Scanner(System.in);

        do
        {
            done = 0;
            playerMove = "";
            computerMove = "";

            do
            {
                if (done == 0)
                {
                    System.out.println("Player 1 enter your move");
                    playerMove = input.nextLine();
                    if (playerMove.equalsIgnoreCase("r")
                            || playerMove.equalsIgnoreCase("p")
                            || playerMove.equalsIgnoreCase("s"))
                    {
                        done++;
                    }
                    else
                    {
                        System.out.println("Improper value entered please try again with R,P,S");
                    }
                }
                else if (done == 1)
                {
                    System.out.println("Player 2 enter your move");
                    computerMove = input.nextLine();
                    if (computerMove.equalsIgnoreCase("r")
                            || computerMove.equalsIgnoreCase("p")
                            || computerMove.equalsIgnoreCase("s"))
                    {
                        done++;
                    }
                    else
                    {
                        System.out.println("Improper value entered please try again with R,P,S");
                    }
                }
            }
            while (done < 2);

            if (playerMove.equalsIgnoreCase(computerMove) && playerMove.equalsIgnoreCase("r"))
            {
                System.out.println("Rock vs Rock it's a Tie!");
            }
            else if (playerMove.equalsIgnoreCase(computerMove) && playerMove.equalsIgnoreCase("p"))
            {
                System.out.println("Paper vs Paper it's a Tie!");
            }
            else if (playerMove.equalsIgnoreCase(computerMove) && playerMove.equalsIgnoreCase("s"))
            {
                System.out.println("Scissors vs Scissors it's a Tie!");
            }
            else if (playerMove.equalsIgnoreCase("r") && computerMove.equalsIgnoreCase("s"))
            {
                System.out.println("Rock breaks Scissors");
                System.out.println("Player 1 wins!");
            }
            else if (playerMove.equalsIgnoreCase("s") && computerMove.equalsIgnoreCase("p"))
            {
                System.out.println("Scissors cuts Paper");
                System.out.println("Player 1 wins!");
            }
            else if (playerMove.equalsIgnoreCase("p") && computerMove.equalsIgnoreCase("r"))
            {
                System.out.println("Paper covers Rock");
                System.out.println("Player 1 wins!");
            }
            else if (playerMove.equalsIgnoreCase("s") && computerMove.equalsIgnoreCase("r"))
            {
                System.out.println("Rock breaks Scissors");
                System.out.println("Player 2 wins!");
            }
            else if (playerMove.equalsIgnoreCase("p") && computerMove.equalsIgnoreCase("s"))
            {
                System.out.println("Scissors cuts Paper");
                System.out.println("Player 2 wins!");
            }
            else if (playerMove.equalsIgnoreCase("r") && computerMove.equalsIgnoreCase("p"))
            {
                System.out.println("Paper covers Rock");
                System.out.println("Player 2 wins!");
            }

            System.out.println("Would you like to play again? [Y/N]");
            playAgain = input.nextLine();
        }
        while (playAgain.equalsIgnoreCase("y"));

        System.out.println("Thanks for playing!");
    }
}
