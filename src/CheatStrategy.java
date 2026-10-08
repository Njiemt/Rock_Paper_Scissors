public class CheatStrategy implements Strategy
{
    @Override
    public String getMove(String playerMove)
    {
        switch (playerMove)
        {
            case "R":
                return "P";
            case "P":
                return "S";
            case "S":
                return "R";
            default:
                throw new IllegalArgumentException("Move must be R, P, or S.");
        }
    }
}
