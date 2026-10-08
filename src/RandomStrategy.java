import java.util.concurrent.ThreadLocalRandom;

public class RandomStrategy implements Strategy
{
    private static final String[] MOVES = {"R", "P", "S"};

    @Override
    public String getMove(String playerMove)
    {
        return MOVES[ThreadLocalRandom.current().nextInt(MOVES.length)];
    }
}
