import java.util.Random;

public class ComputerPlayer
{
    private final int[] moveCounts = new int[3];
    private final Random random = new Random();
    private final Strategy cheatStrategy = new CheatStrategy();
    private final Strategy randomStrategy = new RandomStrategy();
    private final Strategy[] strategies = {
            cheatStrategy,
            new LeastUsedStrategy(), new LeastUsedStrategy(),
            new MostUsedStrategy(), new MostUsedStrategy(),
            new LastUsedStrategy(), new LastUsedStrategy(),
            randomStrategy, randomStrategy, randomStrategy
    };
    private final String[] strategyNames = {
            "Cheat",
            "Least Used", "Least Used",
            "Most Used", "Most Used",
            "Last Used", "Last Used",
            "Random", "Random", "Random"
    };

    private String lastPlayerMove;
    private String lastStrategyName;

    public String getMove(String playerMove)
    {
        int strategyIndex = random.nextInt(strategies.length);

        String computerMove = strategies[strategyIndex].getMove(playerMove);
        lastStrategyName = strategyNames[strategyIndex];
        moveCounts["RPS".indexOf(playerMove)]++;
        lastPlayerMove = playerMove;
        return computerMove;
    }

    public String getStrategyName()
    {
        return lastStrategyName;
    }

    private String leastOrMostUsedMove(boolean least)
    {
        int targetCount = moveCounts[0];
        for (int count : moveCounts)
        {
            targetCount = least ? Math.min(targetCount, count) : Math.max(targetCount, count);
        }

        String candidates = "";
        for (int index = 0; index < moveCounts.length; index++)
        {
            if (moveCounts[index] == targetCount)
            {
                candidates += "RPS".charAt(index);
            }
        }
        return String.valueOf(candidates.charAt(random.nextInt(candidates.length())));
    }

    private final class LeastUsedStrategy implements Strategy
    {
        @Override
        public String getMove(String playerMove)
        {
            return cheatStrategy.getMove(leastOrMostUsedMove(true));
        }
    }

    private final class MostUsedStrategy implements Strategy
    {
        @Override
        public String getMove(String playerMove)
        {
            return cheatStrategy.getMove(leastOrMostUsedMove(false));
        }
    }

    private final class LastUsedStrategy implements Strategy
    {
        @Override
        public String getMove(String playerMove)
        {
            return lastPlayerMove == null
                    ? randomStrategy.getMove(playerMove)
                    : cheatStrategy.getMove(lastPlayerMove);
        }
    }
}
