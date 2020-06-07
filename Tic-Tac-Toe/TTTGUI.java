// sirdaniel711
// TTTGUI.java
//
// This program represents the view of the TTT.java game (3rd attempt)
// * Need to fix bug where the cpu is supposed to pick another location if the current panel is already taken.
// * Seems to happen when "O" player is set to hard computer, and "X" player picks top left followed by bottom right

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TTTGUI extends JFrame			    // main window is a JFrame
{
    public static final int HEIGHT = 510; 		// size of 1st window
    public static final int WIDTH = 505;
    public static final int HEIGHT_2 = 660;     // 2nd window
    public static final int WIDTH_2 = 645;
    public static final int HEIGHT_3 = 810;
    public static final int WIDTH_3 = 785;
    public static final int HEIGHT_4 = 170;     // 4th window
    public static final int WIDTH_4 = 330;
    public static final int FONT_SIZE = 12;
    public static final int FONT_SIZE_2 = 20;
    public static final int DEFAULT_SIZE = 3;   // grid dimensions 
    public static final int DEFAULT_SIZE_2 = 4;
    public static final int DEFAULT_SIZE_3 = 5;
    public static final int ROW = 2;            // grid panel dimensions
    public static final int COL = 1;
    public static final int ROW_2 = 3;
    public static final String DATAPATH = "ttt_gif/";
    public static final String EXTENSION = ".gif";
    private static ImageIcon image[] = {new ImageIcon(DATAPATH + "blank" + EXTENSION), new ImageIcon(DATAPATH + "cross" + EXTENSION), 
        new ImageIcon(DATAPATH + "circle" + EXTENSION), new ImageIcon(DATAPATH + "border" + EXTENSION), new ImageIcon(DATAPATH + "border2" + EXTENSION), 
        new ImageIcon(DATAPATH + "border3" + EXTENSION), new ImageIcon(DATAPATH + "blank2" + EXTENSION), new ImageIcon(DATAPATH + "border12" + EXTENSION), 
        new ImageIcon(DATAPATH + "border22" + EXTENSION), new ImageIcon(DATAPATH + "border32" + EXTENSION), new ImageIcon(DATAPATH + "blank3" + EXTENSION), 
        new ImageIcon(DATAPATH + "border13" + EXTENSION), new ImageIcon(DATAPATH + "border23" + EXTENSION), new ImageIcon(DATAPATH + "border33" + EXTENSION)};

    private TTT game;
    private boolean cpu1;
    private boolean cpu2;
    private ImageIcon player1Icon;
    private ImageIcon player2Icon;
    
    // 1st window, 3x3 board
    private JButtonCoordinant[][] space3X3;       // 3x3 grid of buttons
    private JLabel gameLabel;
    private JLabel errLabel;
    private JMenuBar menu;
    private JMenu file;
    private JMenu edit;
    private JMenuItem newGame;
    private JMenuItem exit;
    private JMenuItem size;
    private JMenuItem aI;
    private JFrame frame1;                      // frame for a 3x3 grid
    private JFrame currentFrame;                // current frame being used
    
    // 2nd window, 4x4 board
    private JButtonCoordinant[][] space4X4;       // 4x4 grid of buttons
    private JLabel gameLabel2; 
    private JMenuBar menu2;
    private JMenu file2;
    private JMenu edit2;
    private JMenuItem newGame2;
    private JMenuItem exit2;
    private JMenuItem size2;
    private JMenuItem aI2;
    private JFrame frame2;                      // frame for a 4x4 grid    
    private JLabel errLabel2;
    
    // 3rd window, 5x5 board
    private JButtonCoordinant[][] space5X5;
    private JLabel gameLabel3;
    private JMenuBar menu3;
    private JMenu file3;
    private JMenu edit3;
    private JMenuItem newGame3;
    private JMenuItem exit3;
    private JMenuItem size3;
    private JMenuItem aI3;
    private JFrame frame3;
    private JLabel errLabel3;
    
    // 4th window, board size window
    private JFrame frame4;
    private JButton confirmButton;
    private JButton cancelButton;
    private JRadioButton small;
    private JRadioButton medium;
    private JRadioButton large;
    
    // 5th window, player window
    private JFrame frame5;
    private JButton confirmButton2;
    private JButton cancelButton2;
    private JRadioButton player1;
    private JRadioButton easy1;
    private JRadioButton med1;
    private JRadioButton hard1;
    private JRadioButton player2;
    private JRadioButton easy2;
    private JRadioButton med2;
    private JRadioButton hard2;
    
    public static void main(String[] args)
    {
        TTTGUI win = new TTTGUI();			    // sets up main window
        win.currentFrame.setVisible(true);
    }

    public TTTGUI()
    {
        super("Tic-Tac-Toe");				            // title for main window
        setSize(WIDTH, HEIGHT);				            // sets dimensions
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	// exit when window closes
        
        game = new TTT();
        player1Icon = new ImageIcon();
        player1Icon = image[1];
        player2Icon = new ImageIcon();
        player2Icon = image[2];
        
        // 1. Setting up components
       
        // 1st window
        space3X3 = new JButtonCoordinant[DEFAULT_SIZE][];
        for (int i = 0; i < DEFAULT_SIZE; i++)
        {
            space3X3[i] = new JButtonCoordinant[DEFAULT_SIZE];
            for (int j = 0; j < DEFAULT_SIZE; j++)
                space3X3[i][j] = new JButtonCoordinant(image[0], i, j);
        }
        
        frame1 = new JFrame("Tic-Tac-Toe");
        frame1.setSize(WIDTH, HEIGHT);
        frame1.setLocationRelativeTo(null);     // setting frames to center on screen
        frame1.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame1.setResizable(false);
        
        gameLabel = new JLabel("It is player " + game.getCurrentPlayer() + "'s turn!", JLabel.CENTER);	
        gameLabel.setForeground(Color.RED);
        errLabel = new JLabel("", JLabel.CENTER);
        errLabel.setForeground(Color.RED);
        JLabel borderLabel1 = new JLabel(image[3]);
        JLabel borderLabel2 = new JLabel(image[5]);
        JLabel borderLabel3 = new JLabel(image[4]);
        
        menu = new JMenuBar();
        file = new JMenu("File");
        edit = new JMenu("Edit");
        newGame = new JMenuItem("New Game");
        exit = new JMenuItem("Exit Game");
        size = new JMenuItem("Grid Size");
        aI = new JMenuItem("Players");
        
        file.setMnemonic('f');
        edit.setMnemonic('e');
        newGame.setMnemonic('n');
        newGame.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, Event.CTRL_MASK));
        exit.setMnemonic('x');
        exit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, Event.CTRL_MASK));
        size.setMnemonic('s');
        size.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, Event.CTRL_MASK));
        aI.setMnemonic('p');
        aI.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, Event.CTRL_MASK));
        
        menu.add(file);
        menu.add(edit);
        file.add(newGame);
        file.add(exit);
        edit.add(size);
        edit.add(aI);
        
        // 2nd window        
        space4X4 = new JButtonCoordinant[DEFAULT_SIZE_2][];
        for (int i = 0; i < DEFAULT_SIZE_2; i++)
        {
            space4X4[i] = new JButtonCoordinant[DEFAULT_SIZE_2];
            for (int j = 0; j < DEFAULT_SIZE_2; j++)
                space4X4[i][j] = new JButtonCoordinant(image[6], i, j);
        }
        
        frame2 = new JFrame("Tic-Tac-Toe");
        frame2.setSize(WIDTH_2, HEIGHT_2);
        frame2.setLocationRelativeTo(null);
        frame2.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame2.setResizable(false);
        JLabel borderLabel12 = new JLabel(image[7]);
        JLabel borderLabel22 = new JLabel(image[9]);
        JLabel borderLabel32 = new JLabel(image[8]);
        
        menu2 = new JMenuBar();
        file2 = new JMenu("File");
        edit2 = new JMenu("Edit");
        newGame2 = new JMenuItem("New Game");
        exit2 = new JMenuItem("Exit Game");
        size2 = new JMenuItem("Grid Size");
        aI2 = new JMenuItem("Players");
        
        gameLabel2 = new JLabel("It is player " + game.getCurrentPlayer() + "'s turn!", JLabel.CENTER);	
        gameLabel2.setForeground(Color.RED);
        errLabel2 = new JLabel("", JLabel.CENTER);
        errLabel2.setForeground(Color.RED);
        
        file2.setMnemonic('f');
        edit2.setMnemonic('e');
        newGame2.setMnemonic('n');
        newGame2.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, Event.CTRL_MASK));
        exit2.setMnemonic('x');
        exit2.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, Event.CTRL_MASK));
        size2.setMnemonic('s');
        size2.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, Event.CTRL_MASK));
        aI2.setMnemonic('p');
        aI2.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, Event.CTRL_MASK));
        
        menu2.add(file2);
        menu2.add(edit2);
        file2.add(newGame2);
        file2.add(exit2);
        edit2.add(size2);
        edit2.add(aI2);
        
        // 3rd window
        space5X5 = new JButtonCoordinant[DEFAULT_SIZE_3][];
        for (int i = 0; i < DEFAULT_SIZE_3; i++)
        {
            space5X5[i] = new JButtonCoordinant[DEFAULT_SIZE_3];
            for (int j = 0; j < DEFAULT_SIZE_3; j++)
                space5X5[i][j] = new JButtonCoordinant(image[10], i, j);
        }
        
        frame3 = new JFrame("Tic-Tac-Toe");
        frame3.setSize(WIDTH_3, HEIGHT_3);
        frame3.setLocationRelativeTo(null);
        frame3.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame3.setResizable(false);
        JLabel borderLabel13 = new JLabel(image[11]);
        JLabel borderLabel23 = new JLabel(image[13]);
        JLabel borderLabel33 = new JLabel(image[12]);
        
        menu3 = new JMenuBar();
        file3 = new JMenu("File");
        edit3 = new JMenu("Edit");
        newGame3 = new JMenuItem("New Game");
        exit3 = new JMenuItem("Exit Game");
        size3 = new JMenuItem("Grid Size");
        aI3 = new JMenuItem("Players");
        
        gameLabel3 = new JLabel("It is player " + game.getCurrentPlayer() + "'s turn!", JLabel.CENTER);	
        gameLabel3.setForeground(Color.RED);
        errLabel3 = new JLabel("", JLabel.CENTER);
        errLabel3.setForeground(Color.RED);
        
        file3.setMnemonic('f');
        edit3.setMnemonic('e');
        newGame3.setMnemonic('n');
        newGame3.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, Event.CTRL_MASK));
        exit3.setMnemonic('x');
        exit3.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, Event.CTRL_MASK));
        size3.setMnemonic('s');
        size3.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, Event.CTRL_MASK));
        aI3.setMnemonic('p');
        aI3.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, Event.CTRL_MASK));
        
        menu3.add(file3);
        menu3.add(edit3);
        file3.add(newGame3);
        file3.add(exit3);
        edit3.add(size3);
        edit3.add(aI3);
        
        
        // 4th window
        frame4 = new JFrame("Settings: Board Size");
        frame4.setSize(WIDTH_4, HEIGHT_4);
        frame4.setLocationRelativeTo(null);
        frame4.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame4.setResizable(false);
        
        JLabel sizeLabel = new JLabel("Select a board size:", JLabel.CENTER);
        sizeLabel.setFont(new Font("Sanserif", Font.BOLD, FONT_SIZE_2));
        small = new JRadioButton("3x3 (Small)");
        small.setToolTipText("Standard game of Tic-Tac-Toe");
        small.setSelected(true);
        medium = new JRadioButton("4x4 (Medium)");
        medium.setToolTipText("Great for a longer game");
        large = new JRadioButton("5x5 (Large)");
        large.setToolTipText("Great for a really long game");
        confirmButton = new JButton("Ok");
        cancelButton = new JButton("Cancel");
        
        ButtonGroup sizeGroup = new ButtonGroup();
        sizeGroup.add(small);
        sizeGroup.add(medium);
        sizeGroup.add(large);
        
        // 5th window
        frame5 = new JFrame("Settings: Players");
        frame5.setSize(WIDTH_4, HEIGHT_4);
        frame5.setLocationRelativeTo(null);
        frame5.setDefaultCloseOperation(EXIT_ON_CLOSE);
        // frame5.setResizable(false);
        
        JLabel playerLabel = new JLabel("Choose player options:", JLabel.CENTER);
        playerLabel.setFont(new Font("Sanserif", Font.BOLD, FONT_SIZE_2));
        JLabel player1Label = new JLabel("PLAYER 1");
        player1Label.setFont(new Font("Sanserif", Font.BOLD, FONT_SIZE_2));
        JLabel player2Label = new JLabel("PLAYER 2");
        player2Label.setFont(new Font("Sanserif", Font.BOLD, FONT_SIZE_2));
        player1 = new JRadioButton("Player");
        player1.setToolTipText("Standard player");
        player1.setSelected(true);
        easy1 = new JRadioButton("Computer (Easy)");
        easy1.setToolTipText("Good for beginners");
        med1 = new JRadioButton("Computer (Medium)");
        med1.setToolTipText("Puts up a fight");
        hard1 = new JRadioButton("computer (Hard)");
        hard1.setToolTipText("Nearly unbeatable");
        player2 = new JRadioButton("Player");
        player2.setToolTipText("Standard player");
        player2.setSelected(true);
        easy2 = new JRadioButton("Computer (Easy)");
        easy2.setToolTipText("Good for beginners");
        med2 = new JRadioButton("Computer (Medium)");
        med2.setToolTipText("Puts up a fight");
        hard2 = new JRadioButton("computer (Hard)");
        hard2.setToolTipText("Nearly unbeatable");
        confirmButton2 = new JButton("Ok");
        cancelButton2 = new JButton("Cancel");
    
        ButtonGroup player1Group = new ButtonGroup();
        player1Group.add(player1);
        player1Group.add(easy1);
        player1Group.add(med1);
        player1Group.add(hard1);
        ButtonGroup player2Group = new ButtonGroup();
        player2Group.add(player2);
        player2Group.add(easy2);
        player2Group.add(med2);
        player2Group.add(hard2);
        
        // other
        currentFrame = frame1;
        cpu1 = false;
        cpu2 = false;
        
        // 2. creating listeners & assigning
        
        // declaration
        SpaceListener listener1 = new SpaceListener();
        SpaceListener2 listener2 = new SpaceListener2();
        SpaceListener3 listener3 = new SpaceListener3();
        NewGameListener listener4 = new NewGameListener();
        ExitListener listener5 = new ExitListener();
        DimensionListener listener6 = new DimensionListener();
        ButtonListener listener7 = new ButtonListener();
        ButtonListener2 listener8 = new ButtonListener2();
        PlayerListener listener9 = new PlayerListener();

        // 1st window
        for (int i = 0; i < DEFAULT_SIZE; i++)
        {
            for (int j = 0; j < DEFAULT_SIZE; j++)
                space3X3[i][j].addActionListener(listener1);
        }
        
        newGame.addActionListener(listener4);
        exit.addActionListener(listener5);
        size.addActionListener(listener6);
        aI.addActionListener(listener9);
        
        // 2nd window
        for (int i = 0; i < DEFAULT_SIZE_2; i++)
        {
            for (int j = 0; j < DEFAULT_SIZE_2; j++)
                space4X4[i][j].addActionListener(listener2);
        }

        newGame2.addActionListener(listener4);
        exit2.addActionListener(listener5);
        size2.addActionListener(listener6);
        aI2.addActionListener(listener9);
        
        // 3rd window
        for (int i = 0; i < DEFAULT_SIZE_3; i++)
        {
            for (int j = 0; j < DEFAULT_SIZE_3; j++)
                space5X5[i][j].addActionListener(listener3);
        }
        
        newGame3.addActionListener(listener4);
        exit3.addActionListener(listener5);
        size3.addActionListener(listener6);
        aI3.addActionListener(listener9);
        
        // 4th window
        confirmButton.addActionListener(listener7);
        cancelButton.addActionListener(listener7);
        
        // 5th window
        confirmButton2.addActionListener(listener8);
        cancelButton2.addActionListener(listener8);
        
        // 3. setting up layouts
        
        // 1st window
        JPanel buttonPanel1 = new JPanel(new GridLayout(DEFAULT_SIZE, DEFAULT_SIZE));    // grid layout for spaces
        for (int i = 0; i < DEFAULT_SIZE; i++)
        {
            for (int j = 0; j < DEFAULT_SIZE; j++)
                buttonPanel1.add(space3X3[i][j]);
        }
        
        JPanel border1 = new JPanel();
        JPanel border2 = new JPanel();
        JPanel border3 = new JPanel(); 
        border1.setBackground(Color.BLACK);
        border2.setBackground(Color.BLACK);
        border3.setBackground(Color.BLACK);
        border1.add(borderLabel1);
        border2.add(borderLabel2);
        border3.add(borderLabel3);
        
        JPanel labelPanel1 = new JPanel(new GridLayout(ROW, COL));
        labelPanel1.add(gameLabel);
        labelPanel1.add(errLabel);
        labelPanel1.setBackground(Color.BLACK);
        
        frame1.setLayout(new BorderLayout());
        frame1.setJMenuBar(menu);
        frame1.add(buttonPanel1, BorderLayout.CENTER);
        frame1.add(border3, BorderLayout.NORTH);
        frame1.add(labelPanel1, BorderLayout.SOUTH);
        frame1.add(border1, BorderLayout.WEST);
        frame1.add(border2, BorderLayout.EAST);
        
        // 2nd window        
        JPanel buttonPanel2 = new JPanel(new GridLayout(DEFAULT_SIZE_2, DEFAULT_SIZE_2));
        for (int i = 0; i < DEFAULT_SIZE_2; i++)
        {
            for (int j = 0; j < DEFAULT_SIZE_2; j++)
                buttonPanel2.add(space4X4[i][j]);
        }

        JPanel border12 = new JPanel();
        JPanel border22 = new JPanel();
        JPanel border32 = new JPanel(); 
        border12.setBackground(Color.BLACK);
        border22.setBackground(Color.BLACK);
        border32.setBackground(Color.BLACK);
        border12.add(borderLabel12);
        border22.add(borderLabel22);
        border32.add(borderLabel32);
        
        JPanel labelPanel2 = new JPanel(new GridLayout(ROW, COL));
        labelPanel2.add(gameLabel2);
        labelPanel2.add(errLabel2);
        labelPanel2.setBackground(Color.BLACK);        
        
        frame2.setLayout(new BorderLayout());
        frame2.setJMenuBar(menu2);
        frame2.add(buttonPanel2, BorderLayout.CENTER);
        frame2.add(border32, BorderLayout.NORTH);
        frame2.add(labelPanel2, BorderLayout.SOUTH);
        frame2.add(border12, BorderLayout.WEST);
        frame2.add(border22, BorderLayout.EAST);
        
        // 3rd window
        JPanel buttonPanel3 = new JPanel(new GridLayout(DEFAULT_SIZE_3, DEFAULT_SIZE_3));
        for (int i = 0; i < DEFAULT_SIZE_3; i++)
        {
            for (int j = 0; j < DEFAULT_SIZE_3; j++)
                buttonPanel3.add(space5X5[i][j]);
        }
        
        JPanel border13 = new JPanel();
        JPanel border23 = new JPanel();
        JPanel border33 = new JPanel(); 
        border13.setBackground(Color.BLACK);
        border23.setBackground(Color.BLACK);
        border33.setBackground(Color.BLACK);
        border13.add(borderLabel13);
        border23.add(borderLabel23);
        border33.add(borderLabel33);
        
        JPanel labelPanel3 = new JPanel(new GridLayout(ROW, COL));
        labelPanel3.add(gameLabel3);
        labelPanel3.add(errLabel3);
        labelPanel3.setBackground(Color.BLACK);        
        
        frame3.setLayout(new BorderLayout());
        frame3.setJMenuBar(menu3);
        frame3.add(buttonPanel3, BorderLayout.CENTER);
        frame3.add(border33, BorderLayout.NORTH);
        frame3.add(labelPanel3, BorderLayout.SOUTH);
        frame3.add(border13, BorderLayout.WEST);
        frame3.add(border23, BorderLayout.EAST);
        
        // 4th window
        JPanel sizePanel = new JPanel(new GridLayout(ROW_2, COL));
        sizePanel.add(small);
        sizePanel.add(medium);
        sizePanel.add(large);
        JPanel eastPanel = new JPanel();
        eastPanel.add(sizePanel, BorderLayout.EAST);                            // formatting layout...
        JPanel buttonPanel4 = new JPanel(new GridLayout(1, 2));
        buttonPanel4.add(confirmButton);
        buttonPanel4.add(cancelButton);
        frame4.setLayout(new BorderLayout());
        frame4.add(sizeLabel, BorderLayout.NORTH);
        frame4.add(eastPanel, BorderLayout.CENTER);
        frame4.add(buttonPanel4, BorderLayout.SOUTH);
        
        // 5th window
        JPanel playerPanel = new JPanel(new GridLayout(5, 2));
        playerPanel.add(player1Label);
        playerPanel.add(player2Label);
        playerPanel.add(player1);
        playerPanel.add(player2);
        playerPanel.add(easy1);
        playerPanel.add(easy2);
        playerPanel.add(med1);
        playerPanel.add(med2);
        playerPanel.add(hard1);
        playerPanel.add(hard2);
        // JPanel eastPanel2 = new JPanel();
        // eastPanel2.add(playerPanel, BorderLayout.EAST);                            // formatting layout...
        JPanel buttonPanel5 = new JPanel(new GridLayout(1, 2));
        buttonPanel5.add(confirmButton2);
        buttonPanel5.add(cancelButton2);
        frame5.setLayout(new BorderLayout());
        frame5.add(playerLabel, BorderLayout.NORTH);
        frame5.add(playerPanel, BorderLayout.CENTER);
        frame5.add(buttonPanel5, BorderLayout.SOUTH);
    }			

    private class SpaceListener implements ActionListener       // listener for 3x3 board
    {
        public void actionPerformed(ActionEvent event)
        {
            int rowSelected;
            int colSelected;
            JButtonCoordinant obj;						// get coordinates of selected button

            obj = (JButtonCoordinant) event.getSource();
            rowSelected = obj.getXCoordinant();
            colSelected = obj.getYCoordinant(); 
    								                    // make move
            if (game.isOver())
                errLabel.setText("Game is over, select new game to start over");
            else if (!game.isOpen(rowSelected, colSelected))
                errLabel.setText("That space is already occupied, select another");
            else
            {
                errLabel.setText("");
                if (game.isXPlayerTurn())
                    space3X3[rowSelected][colSelected].setIcon(player1Icon);
                else
                    space3X3[rowSelected][colSelected].setIcon(player2Icon);

                game.makeMove(rowSelected, colSelected);
                                                        // check if game over, otherwise next turn
                if (game.isOver())
                {
                    if (game.isCat())
                        gameLabel.setText("Game Over! This game is a CAT!"); 
                    else
                        gameLabel.setText("Game Over! Player " + game.getCurrentWinner() + " wins!");
                    gameLabel.setForeground(Color.GREEN);
                }

                else
                {
                    gameLabel.setText("It is player " + game.getCurrentPlayer() + "'s turn!");
                    if (game.isXPlayerTurn())
                        gameLabel.setForeground(Color.RED);
                    else
                        gameLabel.setForeground(Color.BLUE);
                }
                
                if ((cpu1) || (cpu2))
                {
                    takeTurn();
                    // System.out.println("cpu is taking its turn...");
                }
            }
        }
        
        public void takeTurn()      // for cpu
        {
            int rowSelected;
            int colSelected;
            
            // System.out.println("cpu is making it's move...");
            
            game.aIMove();
            rowSelected = game.getAIRow();
            colSelected = game.getAICol();
            
            if (game.isOver())
                errLabel.setText("Game is over, select new game to start over");
            else if (!game.isOpen(rowSelected, colSelected))
                errLabel.setText("That space is already occupied, select another");
            else
            {
                errLabel.setText("");
                if (game.isXPlayerTurn())
                    space3X3[rowSelected][colSelected].setIcon(player1Icon);
                else
                    space3X3[rowSelected][colSelected].setIcon(player2Icon);
                
                game.makeMove(rowSelected, colSelected);
                // check if game over, otherwise next turn
                if (game.isOver())
                {
                    if (game.isCat())
                        gameLabel.setText("Game Over! This game is a CAT!"); 
                    else
                        gameLabel.setText("Game Over! Player " + game.getCurrentWinner() + " wins!");
                    gameLabel.setForeground(Color.GREEN);
                }
                
                else
                {
                    gameLabel.setText("It is player " + game.getCurrentPlayer() + "'s turn!");
                    if (game.isXPlayerTurn())
                        gameLabel.setForeground(Color.RED);
                    else
                        gameLabel.setForeground(Color.BLUE);
                }
            }
        }
    }
    
    private class SpaceListener2 implements ActionListener      // listener for 4x4 board
    {
        public void actionPerformed(ActionEvent event)
        {
            int rowSelected;
            int colSelected;
            JButtonCoordinant obj;						// get coordinates of selected button
            
            obj = (JButtonCoordinant) event.getSource();
            rowSelected = obj.getXCoordinant();
            colSelected = obj.getYCoordinant();
            // make move
            if (game.isOver())
                errLabel2.setText("Game is over, select new game to start over");
            else if (!game.isOpen(rowSelected, colSelected))
                errLabel2.setText("That space is already occupied, select another");
            else
            {
                errLabel2.setText("");
                if (game.isXPlayerTurn())
                    space4X4[rowSelected][colSelected].setIcon(player1Icon);
                else
                    space4X4[rowSelected][colSelected].setIcon(player2Icon);
                
                game.makeMove(rowSelected, colSelected);
                // check if game over, otherwise next turn
                if (game.isOver())
                {
                    if (game.isCat())
                        gameLabel2.setText("Game Over! This game is a CAT!"); 
                    else
                        gameLabel2.setText("Game Over! Player " + game.getCurrentWinner() + " wins!");
                    gameLabel2.setForeground(Color.GREEN);
                }
                
                else
                {
                    gameLabel2.setText("It is player " + game.getCurrentPlayer() + "'s turn!");
                    if (game.isXPlayerTurn())
                        gameLabel2.setForeground(Color.RED);
                    else
                        gameLabel2.setForeground(Color.BLUE);
                }
                
                if ((cpu1) || (cpu2))
                    takeTurn();
            }
        }
        
        public void takeTurn()      // for cpu
        {
            int rowSelected;
            int colSelected;
            
            game.aIMove();
            rowSelected = game.getAIRow();
            colSelected = game.getAICol();
            
            if (game.isOver())
                errLabel.setText("Game is over, select new game to start over");
            else if (!game.isOpen(rowSelected, colSelected))
                errLabel.setText("That space is already occupied, select another");
            else
            {
                errLabel.setText("");
                if (game.isXPlayerTurn())
                    space4X4[rowSelected][colSelected].setIcon(player1Icon);
                else
                    space4X4[rowSelected][colSelected].setIcon(player2Icon);
                
                game.makeMove(rowSelected, colSelected);
                // check if game over, otherwise next turn
                if (game.isOver())
                {
                    if (game.isCat())
                        gameLabel2.setText("Game Over! This game is a CAT!"); 
                    else
                        gameLabel2.setText("Game Over! Player " + game.getCurrentWinner() + " wins!");
                    gameLabel2.setForeground(Color.GREEN);
                }
                
                else
                {
                    gameLabel2.setText("It is player " + game.getCurrentPlayer() + "'s turn!");
                    if (game.isXPlayerTurn())
                        gameLabel2.setForeground(Color.RED);
                    else
                        gameLabel2.setForeground(Color.BLUE);
                }
            }
        }
    }
    
    private class SpaceListener3 implements ActionListener      // listener for 5x5 board
    {
        public void actionPerformed(ActionEvent event)
        {
            int rowSelected;
            int colSelected;
            JButtonCoordinant obj;						// get coordinates of selected button
            
            obj = (JButtonCoordinant) event.getSource();
            rowSelected = obj.getXCoordinant();
            colSelected = obj.getYCoordinant();
            // make move
            if (game.isOver())
                errLabel3.setText("Game is over, select new game to start over");
            else if (!game.isOpen(rowSelected, colSelected))
                errLabel3.setText("That space is already occupied, select another");
            else
            {
                errLabel3.setText("");
                if (game.isXPlayerTurn())
                    space5X5[rowSelected][colSelected].setIcon(player1Icon);
                else
                    space5X5[rowSelected][colSelected].setIcon(player2Icon);
                
                game.makeMove(rowSelected, colSelected);
                // check if game over, otherwise next turn
                if (game.isOver())
                {
                    if (game.isCat())
                        gameLabel3.setText("Game Over! This game is a CAT!"); 
                    else
                        gameLabel3.setText("Game Over! Player " + game.getCurrentWinner() + " wins!");
                    gameLabel3.setForeground(Color.GREEN);
                }
                
                else
                {
                    gameLabel3.setText("It is player " + game.getCurrentPlayer() + "'s turn!");
                    if (game.isXPlayerTurn())
                        gameLabel3.setForeground(Color.RED);
                    else
                        gameLabel3.setForeground(Color.BLUE);
                }
                
                if ((cpu1) && (cpu2))
                    takeTurn();
            }
        }
        
        public void takeTurn()      // for cpu
        {
            int rowSelected;
            int colSelected;
            
            game.aIMove();
            rowSelected = game.getAIRow();
            colSelected = game.getAICol();
            
            if (game.isOver())
                errLabel.setText("Game is over, select new game to start over");
            else if (!game.isOpen(rowSelected, colSelected))
                errLabel.setText("That space is already occupied, select another");
            else
            {
                errLabel.setText("");
                if (game.isXPlayerTurn())
                    space5X5[rowSelected][colSelected].setIcon(player1Icon);
                else
                    space5X5[rowSelected][colSelected].setIcon(player2Icon);
                
                game.makeMove(rowSelected, colSelected);
                // check if game over, otherwise next turn
                if (game.isOver())
                {
                    if (game.isCat())
                        gameLabel3.setText("Game Over! This game is a CAT!"); 
                    else
                        gameLabel3.setText("Game Over! Player " + game.getCurrentWinner() + " wins!");
                    gameLabel3.setForeground(Color.GREEN);
                }
                
                else
                {
                    gameLabel3.setText("It is player " + game.getCurrentPlayer() + "'s turn!");
                    if (game.isXPlayerTurn())
                        gameLabel3.setForeground(Color.RED);
                    else
                        gameLabel3.setForeground(Color.BLUE);
                }
            }
        }
    }
    
    private class NewGameListener implements ActionListener     // listener for the new game option
    {
        public void actionPerformed(ActionEvent event)
        {
            game.newGame();
            gameLabel.setText("It is player " + game.getCurrentPlayer() + "'s turn!");
            gameLabel.setForeground(Color.RED);  
            errLabel.setText("");  
            player1Icon = image[1];
            player2Icon = image[2];
            
            for (int i = 0; i < DEFAULT_SIZE; i++)
            {
                for (int j = 0; j < DEFAULT_SIZE; j++)
                {
                    space3X3[i][j].setIcon(image[0]);
                    space3X3[i][j].setEnabled(true);
                }
            }
            
            gameLabel2.setText("It is player " + game.getCurrentPlayer() + "'s turn!");
            gameLabel2.setForeground(Color.RED);  
            errLabel2.setText("");  
            for (int i = 0; i < DEFAULT_SIZE_2; i++)
            {
                for (int j = 0; j < DEFAULT_SIZE_2; j++)
                {
                    space4X4[i][j].setIcon(image[6]);
                    space4X4[i][j].setEnabled(true);
                }
            }
            
            gameLabel3.setText("It is player " + game.getCurrentPlayer() + "'s turn!");
            gameLabel3.setForeground(Color.RED);  
            errLabel3.setText("");  
            for (int i = 0; i < DEFAULT_SIZE_3; i++)
            {
                for (int j = 0; j < DEFAULT_SIZE_3; j++)
                {
                    space5X5[i][j].setIcon(image[10]);
                    space5X5[i][j].setEnabled(true);
                }
            }
            
            // for cpu
            if (cpu1)
            {
                int rowSelected;
                int colSelected;
                
                // System.out.println("cpu 1 is making its move...");
                
                game.aIMove();
                rowSelected = game.getAIRow();
                colSelected = game.getAICol();
                
                if (game.isOver())
                    errLabel.setText("Game is over, select new game to start over");
                else if (!game.isOpen(rowSelected, colSelected))
                    errLabel.setText("That space is already occupied, select another");
                else
                {
                    errLabel.setText("");
                    if (game.isXPlayerTurn())
                        space3X3[rowSelected][colSelected].setIcon(player1Icon);
                    else
                        space3X3[rowSelected][colSelected].setIcon(player2Icon);
                    
                    game.makeMove(rowSelected, colSelected);
                    // check if game over, otherwise next turn
                    if (game.isOver())
                    {
                        if (game.isCat())
                            gameLabel.setText("Game Over! This game is a CAT!"); 
                        else
                            gameLabel.setText("Game Over! Player " + game.getCurrentWinner() + " wins!");
                        gameLabel.setForeground(Color.GREEN);
                    }
                    
                    else
                    {
                        gameLabel.setText("It is player " + game.getCurrentPlayer() + "'s turn!");
                        if (game.isXPlayerTurn())
                            gameLabel.setForeground(Color.RED);
                        else
                            gameLabel.setForeground(Color.BLUE);
                    }
                }
            }
        }
    }

    private class ExitListener implements ActionListener        // listener for closing the game
    {
        public void actionPerformed(ActionEvent event)
        {
            System.exit(0);
        }
    }
    
    private class DimensionListener implements ActionListener       // opens board size window
    {
        public void actionPerformed(ActionEvent event)
        {
            frame4.setVisible(true);
        }
    }
    
    private class PlayerListener implements ActionListener          // opens player window
    {
        public void actionPerformed(ActionEvent event)
        {
            frame5.setVisible(true);
        }
    }
    
    private class ButtonListener implements ActionListener          // listener for board size window
    {
        public void actionPerformed(ActionEvent event)
        {
            String choice = event.getActionCommand();
                        
            if (choice.equals("Ok"))
            {
                currentFrame.setVisible(false);
                frame4.setVisible(false);
                if (small.isSelected())
                {
                    currentFrame = frame1;
                    game.setDimension(DEFAULT_SIZE);
                }
                else if (medium.isSelected())
                {
                    currentFrame = frame2;
                    game.setDimension(DEFAULT_SIZE_2);
                }
                else
                {
                    currentFrame = frame3;
                    game.setDimension(DEFAULT_SIZE_3);
                }
                
                // reset game
                game.newGame();
                gameLabel.setText("It is player " + game.getCurrentPlayer() + "'s turn!");
                gameLabel.setForeground(Color.RED);  
                errLabel.setText("");  
                player1Icon = image[1];
                player2Icon = image[2];
                
                for (int i = 0; i < DEFAULT_SIZE; i++)
                {
                    for (int j = 0; j < DEFAULT_SIZE; j++)
                    {
                        space3X3[i][j].setIcon(image[0]);
                        space3X3[i][j].setEnabled(true);
                    }
                }
                
                gameLabel2.setText("It is player " + game.getCurrentPlayer() + "'s turn!");
                gameLabel2.setForeground(Color.RED);  
                errLabel2.setText("");  
                for (int i = 0; i < DEFAULT_SIZE_2; i++)
                {
                    for (int j = 0; j < DEFAULT_SIZE_2; j++)
                    {
                        space4X4[i][j].setIcon(image[6]);
                        space4X4[i][j].setEnabled(true);
                    }
                }
                
                gameLabel3.setText("It is player " + game.getCurrentPlayer() + "'s turn!");
                gameLabel3.setForeground(Color.RED);  
                errLabel3.setText("");  
                for (int i = 0; i < DEFAULT_SIZE_3; i++)
                {
                    for (int j = 0; j < DEFAULT_SIZE_3; j++)
                    {
                        space5X5[i][j].setIcon(image[10]);
                        space5X5[i][j].setEnabled(true);
                    }
                }
                currentFrame.setVisible(true);
                
                // for cpu
                if (cpu1)
                {
                    int rowSelected;
                    int colSelected;
                    
                    game.aIMove();
                    rowSelected = game.getAIRow();
                    colSelected = game.getAICol();
                    
                    if (game.isOver())
                        errLabel.setText("Game is over, select new game to start over");
                    else if (!game.isOpen(rowSelected, colSelected))
                        errLabel.setText("That space is already occupied, select another");
                    else
                    {
                        errLabel.setText("");
                        if (game.isXPlayerTurn())
                            space3X3[rowSelected][colSelected].setIcon(player1Icon);
                        else
                            space3X3[rowSelected][colSelected].setIcon(player2Icon);
                        
                        game.makeMove(rowSelected, colSelected);
                        // check if game over, otherwise next turn
                        if (game.isOver())
                        {
                            if (game.isCat())
                                gameLabel.setText("Game Over! This game is a CAT!"); 
                            else
                                gameLabel.setText("Game Over! Player " + game.getCurrentWinner() + " wins!");
                            gameLabel.setForeground(Color.GREEN);
                        }
                        
                        else
                        {
                            gameLabel.setText("It is player " + game.getCurrentPlayer() + "'s turn!");
                            if (game.isXPlayerTurn())
                                gameLabel.setForeground(Color.RED);
                            else
                                gameLabel.setForeground(Color.BLUE);
                        }
                    }
                }
            }
            
            else
            {
                frame4.setVisible(false);
            }
        }
    }
    
    private class ButtonListener2 implements ActionListener         // listener for player window
    {
        public void actionPerformed(ActionEvent event)
        {
            String choice = event.getActionCommand();
            
            if (choice.equals("Ok"))
            {
                int diff1 = 0;
                int diff2 = 0;
                
                if (!player1.isSelected() && !player2.isSelected())     // note: does not support both players being computer yet...
                {
                    player1.setSelected(true);
                }
                
                if (med1.isSelected())
                    diff1 = 1;
                else if (hard1.isSelected())
                    diff1 = 2;
                if (med2.isSelected())
                    diff2 = 1;
                else if (hard2.isSelected())
                    diff2 = 2;
                
                currentFrame.setVisible(false);
                frame5.setVisible(false);
                game.setPlayer(!player1.isSelected(), !player2.isSelected(), diff1, diff2);
                cpu1 = !player1.isSelected();
                cpu2 = !player2.isSelected();
                
                
                // if (player1.isSelected() && player2.isSelected())
                // {
                //     game.setPlayer(false, false, 0, 0);
                //     cpu1 = false;
                //     cpu2 = false;
                //     System.out.println("cpu set to false");
                // }
                // else if (player1.isSelected() && easy2.isSelected())
                // {
                //     game.setPlayer(false, true, 0, 0);
                //     cpu1 = false;
                //     cpu2 = true;
                //     System.out.println("cpu2 set to easy true");
                // }
                // else if (player1.isSelected() && med2.isSelected())
                // {
                //     game.setPlayer(false, true, 0, 1);
                //     cpu = true;
                //     System.out.println("cpu set to med true");
                // }
                // else
                // {
                //     game.setPlayer(false, true, 0, 2);
                //     cpu = true;
                //     System.out.println("cpu set to hard true");
                // }
                
                // reset game
                game.newGame();
                gameLabel.setText("It is player " + game.getCurrentPlayer() + "'s turn!");
                gameLabel.setForeground(Color.RED);  
                errLabel.setText("");  
                player1Icon = image[1];
                player2Icon = image[2];
                
                for (int i = 0; i < DEFAULT_SIZE; i++)
                {
                    for (int j = 0; j < DEFAULT_SIZE; j++)
                    {
                        space3X3[i][j].setIcon(image[0]);
                        space3X3[i][j].setEnabled(true);
                    }
                }
                
                gameLabel2.setText("It is player " + game.getCurrentPlayer() + "'s turn!");
                gameLabel2.setForeground(Color.RED);  
                errLabel2.setText("");  
                for (int i = 0; i < DEFAULT_SIZE_2; i++)
                {
                    for (int j = 0; j < DEFAULT_SIZE_2; j++)
                    {
                        space4X4[i][j].setIcon(image[6]);
                        space4X4[i][j].setEnabled(true);
                    }
                }
                
                gameLabel3.setText("It is player " + game.getCurrentPlayer() + "'s turn!");
                gameLabel3.setForeground(Color.RED);  
                errLabel3.setText("");  
                for (int i = 0; i < DEFAULT_SIZE_3; i++)
                {
                    for (int j = 0; j < DEFAULT_SIZE_3; j++)
                    {
                        space5X5[i][j].setIcon(image[10]);
                        space5X5[i][j].setEnabled(true);
                    }
                }
                currentFrame.setVisible(true);
                
                // for cpu
                if (cpu1)
                {
                    System.out.println("cpu1 taking his turn...");
                    int rowSelected;
                    int colSelected;
                    
                    game.aIMove();
                    rowSelected = game.getAIRow();
                    colSelected = game.getAICol();
                    
                    if (game.isOver())
                        errLabel.setText("Game is over, select new game to start over");
                    else if (!game.isOpen(rowSelected, colSelected))
                        errLabel.setText("That space is already occupied, select another");
                    else
                    {
                        errLabel.setText("");
                        if (game.isXPlayerTurn())
                            space3X3[rowSelected][colSelected].setIcon(player1Icon);
                        else
                            space3X3[rowSelected][colSelected].setIcon(player2Icon);
                        
                        game.makeMove(rowSelected, colSelected);
                        // check if game over, otherwise next turn
                        if (game.isOver())
                        {
                            if (game.isCat())
                                gameLabel.setText("Game Over! This game is a CAT!"); 
                            else
                                gameLabel.setText("Game Over! Player " + game.getCurrentWinner() + " wins!");
                            gameLabel.setForeground(Color.GREEN);
                        }
                        
                        else
                        {
                            gameLabel.setText("It is player " + game.getCurrentPlayer() + "'s turn!");
                            if (game.isXPlayerTurn())
                                gameLabel.setForeground(Color.RED);
                            else
                                gameLabel.setForeground(Color.BLUE);
                        }
                    }
                }
            }
            
            else
            {
                frame5.setVisible(false);
            }
        }
    }
}

           
            
        
   
        
    