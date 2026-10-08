import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class RockPaperScissorsFrame extends JFrame implements ActionListener
{
    private final JTextField playerWinsField = createCountField();
    private final JTextField computerWinsField = createCountField();
    private final JTextField tiesField = createCountField();
    private final JTextArea resultsArea = new JTextArea();
    private final ComputerPlayer computer = new ComputerPlayer();

    private int playerWins;
    private int computerWins;
    private int ties;


    public RockPaperScissorsFrame()
    {
        super("Rock Paper Scissors Game");
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter()
        {
            @Override
            public void windowClosing(java.awt.event.WindowEvent event)
            {
                confirmQuit();
            }
        });

        JPanel content = new JPanel(new BorderLayout(14, 14));
        content.setBorder(BorderFactory.createEmptyBorder(16, 16, 16, 16));
        content.add(createStatsPanel(), BorderLayout.NORTH);
        content.add(createButtonsPanel(), BorderLayout.CENTER);
        content.add(createResultsPanel(), BorderLayout.SOUTH);
        setContentPane(content);

        updateStats();
        setMinimumSize(new Dimension(680, 520));
        setSize(760, 590);
        setLocationRelativeTo(null);
    }

    private JPanel createStatsPanel()
    {
        JPanel stats = new JPanel(new GridLayout(1, 6, 8, 4));
        stats.setBorder(BorderFactory.createTitledBorder("Session Statistics"));
        stats.add(new JLabel("Player Wins", SwingConstants.RIGHT));
        stats.add(playerWinsField);
        stats.add(new JLabel("Computer Wins", SwingConstants.RIGHT));
        stats.add(computerWinsField);
        stats.add(new JLabel("Ties", SwingConstants.RIGHT));
        stats.add(tiesField);
        return stats;
    }

    private JPanel createButtonsPanel()
    {
        JPanel buttons = new JPanel(new GridLayout(1, 4, 12, 12));
        buttons.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(), "Choose your move",
                TitledBorder.CENTER, TitledBorder.TOP));

        buttons.add(createMoveButton("🪨 Rock", "R"));
        buttons.add(createMoveButton("📄 Paper", "P"));
        buttons.add(createMoveButton("✂️ Scissors", "S"));

        JButton quitButton = new JButton("Quit");
        quitButton.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 18));
        quitButton.addActionListener(event -> confirmQuit());
        buttons.add(quitButton);
        return buttons;
    }

    private JButton createMoveButton(String label, String move)
    {
        JButton button = new JButton(label);
        button.setActionCommand(move);
        button.setFont(new Font("Segoe UI Emoji", Font.BOLD, 24));
        button.setFocusPainted(false);
        button.setToolTipText(label.substring(label.indexOf(' ') + 1));
        button.addActionListener(this);
        return button;
    }

    private JPanel createResultsPanel()
    {
        JPanel results = new JPanel(new BorderLayout());
        results.setBorder(BorderFactory.createTitledBorder("Game Results"));
        resultsArea.setEditable(false);
        resultsArea.setLineWrap(true);
        resultsArea.setWrapStyleWord(true);
        resultsArea.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 14));
        results.add(new JScrollPane(resultsArea), BorderLayout.CENTER);
        results.setPreferredSize(new Dimension(100, 190));
        return results;
    }

    private static JTextField createCountField()
    {
        JTextField field = new JTextField("0", 5);
        field.setEditable(false);
        field.setHorizontalAlignment(SwingConstants.CENTER);
        return field;
    }

    @Override
    public void actionPerformed(ActionEvent event)
    {
        String playerMove = event.getActionCommand();
        String computerMove = computer.getMove(playerMove);

        int winner = RPSLogic.determineWinner(playerMove, computerMove);
        if (winner == 1)
        {
            playerWins++;
        }
        else if (winner == -1)
        {
            computerWins++;
        }
        else
        {
            ties++;
        }
        updateStats();

        String outcome = winner == 1 ? "Player wins!"
                : winner == -1 ? "Computer wins!" : "Tie!";
        resultsArea.append(RPSLogic.describeRound(playerMove, computerMove)
                + " (" + outcome + " Computer: " + computer.getStrategyName() + ")\n");
        resultsArea.setCaretPosition(resultsArea.getDocument().getLength());
    }

    private void updateStats()
    {
        playerWinsField.setText(Integer.toString(playerWins));
        computerWinsField.setText(Integer.toString(computerWins));
        tiesField.setText(Integer.toString(ties));
    }

    public void confirmQuit()
    {
        int choice = JOptionPane.showConfirmDialog(this, "Are you sure you want to quit?",
                "Quit Game", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (choice == JOptionPane.YES_OPTION)
        {
            dispose();
        }
    }

}
