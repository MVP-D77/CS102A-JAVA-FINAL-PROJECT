package xyz.chengzi.cs102a.chinesechess.chessboard;
import java.io.File;
import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.Formatter;

import xyz.chengzi.cs102a.chinesechess.PlayMusic;
import xyz.chengzi.cs102a.chinesechess.chess.*;
import xyz.chengzi.cs102a.chinesechess.listener.ChessListener;
import xyz.chengzi.cs102a.chinesechess.listener.ChessboardChessListener;

import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ChessboardComponent extends JComponent {
    private ChessListener chessListener = new ChessboardChessListener(this);
    private ChessComponent[][] chessboard = new ChessComponent[10][9];
    private ChessColor currentColor = ChessColor.RED;
    private JLabel redPlayer = new JLabel("回合：红棋");
    private JLabel blackPlayer = new JLabel("回合：黑棋");
    private boolean judge = true;
    PlayMusic music = new PlayMusic();
    private ArrayList<ChessboardPoint> a = new ArrayList<>();
    private ArrayList<ChessboardPoint> b = new ArrayList<>();
    private ArrayList<ChessComponent> c =  new ArrayList<>();

    public boolean getJudge() {
        return judge;
    }

    public ChessboardComponent(int width, int height) {
        setLayout(null); // Use absolute layout.
        setSize(width, height);

        ChessComponent.registerListener(chessListener);

        this.Initialize();
    }

    public void Initialize() {
        for (int i = 0; i < chessboard.length; i++) {
            for (int j = 0; j < chessboard[i].length; j++) {
                putChessOnBoard(new EmptySlotComponent(new ChessboardPoint(i, j), calculatePoint(i, j)));
            }
        }
        currentColor = ChessColor.RED;
        judge = true;
        this.repaint();
        // FIXME: Initialize chessboard for testing only.
        initTestBoard(0, 0, ChessColor.BLACK);
        initTestBoard(0, 8, ChessColor.BLACK);
        initTestBoard(9, 0, ChessColor.RED);
        initTestBoard(9, 8, ChessColor.RED);
        initTestBoard1(0, 1, ChessColor.BLACK);
        initTestBoard1(0, 7, ChessColor.BLACK);
        initTestBoard1(9, 1, ChessColor.RED);
        initTestBoard1(9, 7, ChessColor.RED);
        initTestBoard2(0, 2, ChessColor.BLACK);
        initTestBoard2(0, 6, ChessColor.BLACK);
        initTestBoard2(9, 2, ChessColor.RED);
        initTestBoard2(9, 6, ChessColor.RED);
        initTestBoard3(0, 3, ChessColor.BLACK);
        initTestBoard3(0, 5, ChessColor.BLACK);
        initTestBoard3(9, 3, ChessColor.RED);
        initTestBoard3(9, 5, ChessColor.RED);
        initTestBoard4(0, 4, ChessColor.BLACK);
        initTestBoard4(9, 4, ChessColor.RED);
        initTestBoard5(2, 1, ChessColor.BLACK);
        initTestBoard5(2, 7, ChessColor.BLACK);
        initTestBoard5(7, 1, ChessColor.RED);
        initTestBoard5(7, 7, ChessColor.RED);
        initTestBoard6(3, 0, ChessColor.BLACK);
        initTestBoard6(3, 2, ChessColor.BLACK);
        initTestBoard6(3, 4, ChessColor.BLACK);
        initTestBoard6(3, 6, ChessColor.BLACK);
        initTestBoard6(3, 8, ChessColor.BLACK);
        initTestBoard6(6, 0, ChessColor.RED);
        initTestBoard6(6, 2, ChessColor.RED);
        initTestBoard6(6, 4, ChessColor.RED);
        initTestBoard6(6, 6, ChessColor.RED);
        initTestBoard6(6, 8, ChessColor.RED);
    }

    public ChessComponent[][] getChessboard() {
        return chessboard;
    }

    public ChessColor getCurrentColor() {
        return currentColor;
    }

    public void putChessOnBoard(ChessComponent chessComponent) {
        int row = chessComponent.getChessboardPoint().getX(), col = chessComponent.getChessboardPoint().getY();
        if (chessboard[row][col] != null) {
            remove(chessboard[row][col]);
        }
        add(chessboard[row][col] = chessComponent);
    }

    public void swapChessComponents(ChessComponent chess1, ChessComponent chess2) {
        ChessComponent chess= null;
        if(chess2 instanceof AdvisorChessComponent){
            chess=new AdvisorChessComponent(chess2.getChessboardPoint(),chess2.getLocation(),chess2.getChessColor());
        }else if (chess2 instanceof CannonChessComponent){
            chess=new CannonChessComponent(chess2.getChessboardPoint(),chess2.getLocation(),chess2.getChessColor());
        }else if (chess2 instanceof ChariotChessComponent){
            chess=new ChariotChessComponent(chess2.getChessboardPoint(),chess2.getLocation(),chess2.getChessColor());
        }else if (chess2 instanceof ElephantChessComponent){
            chess=new ElephantChessComponent(chess2.getChessboardPoint(),chess2.getLocation(),chess2.getChessColor());
        }else if (chess2 instanceof GeneralChessComponent){
            chess=new GeneralChessComponent(chess2.getChessboardPoint(),chess2.getLocation(),chess2.getChessColor());
        }else if (chess2 instanceof HorseChessComponent){
            chess=new HorseChessComponent(chess2.getChessboardPoint(),chess2.getLocation(),chess2.getChessColor());
        }else if (chess2 instanceof SoldierChessComponent){
            chess=new SoldierChessComponent(chess2.getChessboardPoint(),chess2.getLocation(),chess2.getChessColor());
        }else if (chess2 instanceof EmptySlotComponent){
            chess=new EmptySlotComponent(chess2.getChessboardPoint(),chess2.getLocation());
        }
        a.add(chess1.getChessboardPoint());
        b.add(chess2.getChessboardPoint());
        c.add(chess);


        if (chess2 instanceof GeneralChessComponent) {
            if (chess2.getChessColor() == ChessColor.BLACK) {
                JOptionPane.showMessageDialog(null, "Red Win");
            } else if (chess2.getChessColor() == ChessColor.RED) {
                JOptionPane.showMessageDialog(null, "Black Win");
            }
            remove(chess2);
            add(chess2 = new EmptySlotComponent(chess2.getChessboardPoint(), chess2.getLocation()));
            judge = false;
        } else {
            // Note that chess1 has higher priority, 'destroys' chess2 if exists.
            if (!(chess2 instanceof EmptySlotComponent)) {
                remove(chess2);
                add(chess2 = new EmptySlotComponent(chess2.getChessboardPoint(), chess2.getLocation()));
            }
        }
        chess1.swapLocation(chess2);
        music.eat();
        int row1 = chess1.getChessboardPoint().getX(), col1 = chess1.getChessboardPoint().getY();
        chessboard[row1][col1] = chess1;
        int row2 = chess2.getChessboardPoint().getX(), col2 = chess2.getChessboardPoint().getY();
        chessboard[row2][col2] = chess2;
    }


    public void swapColor() {
        currentColor = currentColor == ChessColor.BLACK ? ChessColor.RED : ChessColor.BLACK;
    }

    private void initTestBoard(int row, int col, ChessColor color) {
        ChessComponent chessComponent = new ChariotChessComponent(new ChessboardPoint(row, col), calculatePoint(row, col), color);
        chessComponent.setVisible(true);
        putChessOnBoard(chessComponent);
    }

    private void initTestBoard1(int row, int col, ChessColor color) {
        ChessComponent chessComponent = new HorseChessComponent(new ChessboardPoint(row, col), calculatePoint(row, col), color);
        chessComponent.setVisible(true);
        putChessOnBoard(chessComponent);
    }

    private void initTestBoard2(int row, int col, ChessColor color) {
        ChessComponent chessComponent = new ElephantChessComponent(new ChessboardPoint(row, col), calculatePoint(row, col), color);
        chessComponent.setVisible(true);
        putChessOnBoard(chessComponent);
    }

    private void initTestBoard3(int row, int col, ChessColor color) {
        ChessComponent chessComponent = new AdvisorChessComponent(new ChessboardPoint(row, col), calculatePoint(row, col), color);
        chessComponent.setVisible(true);
        putChessOnBoard(chessComponent);
    }

    private void initTestBoard4(int row, int col, ChessColor color) {
        ChessComponent chessComponent = new GeneralChessComponent(new ChessboardPoint(row, col), calculatePoint(row, col), color);
        chessComponent.setVisible(true);
        putChessOnBoard(chessComponent);
    }

    private void initTestBoard5(int row, int col, ChessColor color) {
        ChessComponent chessComponent = new CannonChessComponent(new ChessboardPoint(row, col), calculatePoint(row, col), color);
        chessComponent.setVisible(true);
        putChessOnBoard(chessComponent);
    }

    private void initTestBoard6(int row, int col, ChessColor color) {
        ChessComponent chessComponent = new SoldierChessComponent(new ChessboardPoint(row, col), calculatePoint(row, col), color);
        chessComponent.setVisible(true);
        putChessOnBoard(chessComponent);
    }

    public void giveUp(){
        if (currentColor == ChessColor.BLACK) {
            JOptionPane.showMessageDialog(null, "Red Win");
        judge=false;
        } else if (currentColor == ChessColor.RED) {
            JOptionPane.showMessageDialog(null, "Black Win");
        judge = false;
        }
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        ImageIcon icon = new ImageIcon("src/timg 1.jpeg");
        Image img = icon.getImage();
        g.drawImage(img, 0, 8, icon.getIconWidth() - 316,
                icon.getIconHeight() - 415, icon.getImageObserver());

        if (judge) {
            if (currentColor == ChessColor.RED) {
                remove(blackPlayer);
                redPlayer.setLocation(170, 175);
                redPlayer.setSize(200, 50);
                redPlayer.setForeground(Color.RED);
                add(redPlayer);
            } else if (currentColor == ChessColor.BLACK) {
                remove(redPlayer);
                blackPlayer.setLocation(170, 175);
                blackPlayer.setSize(200, 50);
                blackPlayer.setForeground(Color.BLACK);
                add(blackPlayer);
            } else {
                remove(redPlayer);
                remove(blackPlayer);
            }
        }
        /*
        ImageIcon icon=new ImageIcon("src/timg.jpeg" );
        Image img = icon.getImage();
        g.drawImage(img, 0, 8, icon.getIconWidth(), icon.getIconHeight(), icon.getImageObserver());
        ((Graphics2D) g).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        paintBoardLine(g, 0, 0, 9, 0);
        paintBoardLine(g, 0, 8, 9, 8);
        paintHalfBoard(g, 0);
        paintHalfBoard(g, 5);
        paintKingSquare(g, 1, 4);
        paintKingSquare(g, 8, 4);

         */

    }

    private void paintHalfBoard(Graphics g, int startRow) {
        for (int row = startRow; row < startRow + 5; row++) {
            paintBoardLine(g, row, 0, row, 8);
        }
        for (int col = 0; col < 9; col++) {
            paintBoardLine(g, startRow, col, startRow + 4, col);
        }
    }

    private void paintKingSquare(Graphics g, int centerRow, int centerCol) {
        paintBoardLine(g, centerRow - 1, centerCol - 1, centerRow + 1, centerCol + 1);
        paintBoardLine(g, centerRow - 1, centerCol + 1, centerRow + 1, centerCol - 1);
    }

    private void paintBoardLine(Graphics g, int rowFrom, int colFrom, int rowTo, int colTo) {
        int offsetX = ChessComponent.CHESS_SIZE.width / 2, offsetY = ChessComponent.CHESS_SIZE.height / 2;
        Point p1 = calculatePoint(rowFrom, colFrom), p2 = calculatePoint(rowTo, colTo);
        g.drawLine(p1.x + offsetX, p1.y + offsetY, p2.x + offsetX, p2.y + offsetY);
    }

    private Point calculatePoint(int row, int col) {
        return new Point(col * getWidth() / 9, row * getHeight() / 10);
    }

    public void loadSteps(String path) throws Exception {
        File it =new File(path.trim());
        String name =it.getName();
        Scanner input = new Scanner(new File(path));
        int counter = 0;
        int step = 0;
        while (true) {
            counter++;
            String a = input.nextLine();
            if (a.contains("@TOTAL_STEP=")) {
                int n = a.indexOf("=");
                step = Integer.parseInt(a.substring(n + 1));
            }
            if (a.contains("@LAST_MOVER=")) {
                int n = a.indexOf("=");
                currentColor = a.charAt(n + 1) == 'B' ? ChessColor.RED : ChessColor.BLACK;
            }
            if (a.contains("@@")) {
                break;
            }
        }
        String m = input.nextLine();
        counter++;
        currentColor = ChessColor.RED;
        int startX = 0;
        int startY = 0;
        int finalX = 0;
        int finalY = 0;
        for(int i=0;i<step;i++) {
            counter++;
            ChessColor color = currentColor == ChessColor.RED ? ChessColor.BLACK : ChessColor.RED;
            if (currentColor == ChessColor.RED) {
                startY = 9 - input.nextInt();
                startX = 10 - input.nextInt();
                finalY = 9 - input.nextInt();
                finalX = 10 - input.nextInt();
                ChessboardPoint destination = new ChessboardPoint(finalX, finalY);
                if (startX < 0 || startY < 0 || finalX < 0 || finalY < 0 || startX >= 10 || startY >= 9 || finalX >= 10 || finalY >= 9) {
                    System.out.printf("%s:%s %d\n", name, "Position Out Of Range at Line", counter);
                } else if (chessboard[startX][startY] instanceof EmptySlotComponent) {
                    System.out.printf("%s:%s %d\n", name, "Invalid From Position at Line", counter);
                } else if (chessboard[startX][startY].getChessColor() == color) {
                    System.out.printf("%s:%s %d\n", name, "Invalid From Position at Line", counter);
                } else if (chessboard[finalX][finalY].getChessColor() == currentColor) {
                    System.out.printf("%s:%s %d\n", name, "Invalid To Position at Line", counter);
                } else if (!chessboard[startX][startY].canMoveTo(this.chessboard, destination)) {
                    System.out.printf("%s:%s %d\n", name, "Invalid Move Pattern at Line", counter);
                } else {
                    ChessboardChessListener one = new ChessboardChessListener(this);
                    one.onClick(chessboard[startX][startY]);
                    one.onClick(chessboard[finalX][finalY]);
                }
            } else {
                startY = input.nextInt() - 1;
                startX = input.nextInt() - 1;
                finalY = input.nextInt() - 1;
                finalX = input.nextInt() - 1;
                ChessboardPoint destination = new ChessboardPoint(finalX, finalY);
                if (startX < 0 || startY < 0 || finalX < 0 || finalY < 0 || startX >= 10 || startY >= 9 || finalX >= 10 || finalY >= 9) {
                    System.out.printf("%s:%s %d\n", name, "Position Out Of Range at Line", counter);
                } else if (chessboard[startX][startY] instanceof EmptySlotComponent) {
                    System.out.printf("%s:%s %d\n", name, "Invalid From Position at Line", counter);
                } else if (chessboard[startX][startY].getChessColor() == color) {
                    System.out.printf("%s:%s %d\n", name, "Invalid From Position at Line", counter);
                } else if (chessboard[finalX][finalY].getChessColor() == currentColor) {
                    System.out.printf("%s:%s %d\n", name, "Invalid To Position at Line", counter);
                } else if (!chessboard[startX][startY].canMoveTo(chessboard, destination)) {
                    System.out.printf("%s:%s %d\n", name, "Invalid Move Pattern at Line", counter);
                } else {
                    ChessboardChessListener one = new ChessboardChessListener(this);
                    one.onClick(chessboard[startX][startY]);
                    one.onClick(chessboard[finalX][finalY]);
                }
            }
        }
    }
    public void save(String name) throws Exception {
        Formatter output = new Formatter(name);
        String color = currentColor == ChessColor.RED ? "BLACK" : "RED";
        output.format("%s%s\n%s\n\n", "@LAST_MOVER=", color, "@@");
        for (int i = 0; i < 5; i++) {
            String a = "";
            for (int j = 0; j < 9; j++) {
                ChessComponent information = chessboard[i][j];
                if (information.getChessColor() == ChessColor.BLACK) {
                    if (information instanceof GeneralChessComponent) {
                        a = a.concat("G");
                    } else if (information instanceof AdvisorChessComponent) {
                        a = a.concat("A");
                    } else if (information instanceof ElephantChessComponent) {
                        a = a.concat("E");
                    } else if (information instanceof HorseChessComponent) {
                        a = a.concat("H");
                    } else if (information instanceof ChariotChessComponent) {
                        a = a.concat("C");
                    } else if (information instanceof CannonChessComponent) {
                        a = a.concat("N");
                    } else if (information instanceof SoldierChessComponent) {
                        a = a.concat("S");
                    }
                } else if (information.getChessColor() == ChessColor.RED) {
                    if (information instanceof GeneralChessComponent) {
                        a = a.concat("g");
                    } else if (information instanceof AdvisorChessComponent) {
                        a = a.concat("a");
                    } else if (information instanceof ElephantChessComponent) {
                        a = a.concat("e");
                    } else if (information instanceof HorseChessComponent) {
                        a = a.concat("h");
                    } else if (information instanceof ChariotChessComponent) {
                        a = a.concat("c");
                    } else if (information instanceof CannonChessComponent) {
                        a = a.concat("n");
                    } else if (information instanceof SoldierChessComponent) {
                        a = a.concat("s");
                    }
                } else {
                    a = a.concat(".");
                }
            }
            output.format("%s\n", a);
        }
        String b = "";
        for (int i = 0; i < 9; i++) {
            b = b.concat("-");
        }
        output.format(b + "\n");
        for (int i = 5; i < 10; i++) {
            String a = "";
            for (int j = 0; j < 9; j++) {
                ChessComponent information = chessboard[i][j];
                if (information.getChessColor() == ChessColor.BLACK) {
                    if (information instanceof GeneralChessComponent) {
                        a = a.concat("G");
                    } else if (information instanceof AdvisorChessComponent) {
                        a = a.concat("A");
                    } else if (information instanceof ElephantChessComponent) {
                        a = a.concat("E");
                    } else if (information instanceof HorseChessComponent) {
                        a = a.concat("H");
                    } else if (information instanceof ChariotChessComponent) {
                        a = a.concat("C");
                    } else if (information instanceof CannonChessComponent) {
                        a = a.concat("N");
                    } else if (information instanceof SoldierChessComponent) {
                        a = a.concat("S");
                    }
                } else if (information.getChessColor() == ChessColor.RED) {
                    if (information instanceof GeneralChessComponent) {
                        a = a.concat("g");
                    } else if (information instanceof AdvisorChessComponent) {
                        a = a.concat("a");
                    } else if (information instanceof ElephantChessComponent) {
                        a = a.concat("e");
                    } else if (information instanceof HorseChessComponent) {
                        a = a.concat("h");
                    } else if (information instanceof ChariotChessComponent) {
                        a = a.concat("c");
                    } else if (information instanceof CannonChessComponent) {
                        a = a.concat("n");
                    } else if (information instanceof SoldierChessComponent) {
                        a = a.concat("s");
                    }
                } else {
                    a = a.concat(".");
                }
            }
            output.format("%s\n", a);
        }
        output.close();
    }

    public void loadGame(String path) throws Exception {
        File file =new File(path.trim());
        String name =file.getName();
        int temp = 0;
        Scanner input = new Scanner(new File(path));
        for (int i = 0; i < chessboard.length; i++) {
            for (int j = 0; j < chessboard[i].length; j++) {
                putChessOnBoard(new EmptySlotComponent(new ChessboardPoint(i, j), calculatePoint(i, j)));
            }
        }
        int counter = 0;
        while (true) {
            counter++;
            String a = input.nextLine();
            if (a.contains("@LAST_MOVER=")) {
                int n = a.indexOf("=");
                currentColor = a.charAt(n + 1) == 'B' ? ChessColor.RED : ChessColor.BLACK;
            }
            if (a.contains("@@")) {
                break;
            }
        }
        String m = input.nextLine();
        counter++;
        ArrayList<String> array = new ArrayList<>();
        int judge = 0;
        int judge1 = 0;
        String row0 ="";
        int count=1;
        while (input.hasNext()) {
            String a = input.nextLine();
            if (a.contains("-")) {
                judge = 1;
            } else if (a.length() != 9&&!a.equals("")) {
                String b =String.format("%s",count+counter);
                row0=row0.concat(" Line").concat(b);
                judge1++;
                array.add(a);
            } else if(!a.equals("")){
                array.add(a);
            }
            count++;
        }
        if (judge != 1) {
            temp=1;
            System.out.printf("%s:%s\n",name,"River Missing");
        }
        char[][] information = new char[10][9];
        if (judge1 == array.size()) {
            temp = 1;
            System.out.printf("%s:%s\n",name,"Invalid Dimension about column");
        } else if (judge1 < 10&&judge1>0) {
            temp = 1;
            System.out.printf("%s:%s%s\n",name,"Space Missing at Line ",row0.trim());
        }
        if (array.size() == 10) {
            if(temp==0) {
                for (int i = 0; i < 10; i++) {
                    char it[] = array.get(i).toCharArray();
                    for (int j = 0; j < 9; j++) {
                        information[i][j] = it[j];
                    }
                }
            }
        } else {
                temp = 1;
                System.out.printf("%s:%s\n",name,"Invalid Dimension about row");
            }
        if(temp==0) {
            int c = 0, h = 0, g = 0, a = 0, e = 0, n = 0, s = 0, C = 0, H = 0, G = 0, A = 0, E = 0, N = 0, S = 0;
            for (int i = 0; i < 10; i++) {
                for (int j = 0; j < 9; j++) {
                    ChessboardPoint x = chessboard[i][j].getChessboardPoint();
                    int row = x.getX();
                    int col = x.getY();
                    switch (information[i][j]) {
                        case '.':
                            break;
                        case 'c':
                            initTestBoard(row, col, ChessColor.RED);
                            c++;
                            break;
                        case 'h':
                            initTestBoard1(row, col, ChessColor.RED);
                            h++;
                            break;
                        case 'g':
                            initTestBoard4(row, col, ChessColor.RED);
                            g++;
                            break;
                        case 'a':
                            initTestBoard3(row, col, ChessColor.RED);
                            a++;
                            break;
                        case 'e':
                            initTestBoard2(row, col, ChessColor.RED);
                            e++;
                            break;
                        case 'n':
                            initTestBoard5(row, col, ChessColor.RED);
                            n++;
                            break;
                        case 's':
                            initTestBoard6(row, col, ChessColor.RED);
                            s++;
                            break;
                        case 'C':
                            initTestBoard(row, col, ChessColor.BLACK);
                            C++;
                            break;
                        case 'H':
                            initTestBoard1(row, col, ChessColor.BLACK);
                            H++;
                            break;
                        case 'G':
                            initTestBoard4(row, col, ChessColor.BLACK);
                            G++;
                            break;
                        case 'A':
                            initTestBoard3(row, col, ChessColor.BLACK);
                            A++;
                            break;
                        case 'E':
                            initTestBoard2(row, col, ChessColor.BLACK);
                            E++;
                            break;
                        case 'N':
                            initTestBoard5(row, col, ChessColor.BLACK);
                            N++;
                            break;
                        case 'S':
                            initTestBoard6(row, col, ChessColor.BLACK);
                            S++;
                            break;
                    }
                    chessboard[i][j].setVisible(true);
                    putChessOnBoard(chessboard[i][j]);
                }
            }
            if (c > 5) {
                temp = 1;
                System.out.printf("%s:%s\n", name, "Invalid Chess Amount:RED Chariot is more");
            }
            if (C > 5) {
                temp = 1;
                System.out.printf("%s:%s\n", name, "Invalid Chess Amount:BLACK Chariot is more");
            }
            if (h > 2) {
                temp = 1;
                System.out.printf("%s:%s\n", name, "Invalid Chess Amount:RED Horse is more");
            }
            if (H > 2) {
                temp = 1;
                System.out.printf("%s:%s\n", name, "Invalid Chess Amount:BLACK Horse is more");
            }
            if (e > 2) {
                temp = 1;
                System.out.printf("%s:%s\n", name, "Invalid Chess Amount:RED Elephant is more");
            }
            if (E > 2) {
                temp = 1;
                System.out.printf("%s:%s\n", name, "Invalid Chess Amount:BLACK Elephant is more");
            }
            if (a > 2) {
                temp = 1;
                System.out.printf("%s:%s\n", name, "Invalid Chess Amount:RED Advisor is more");
            }
            if (A > 2) {
                temp = 1;
                System.out.printf("%s:%s\n", name, "Invalid Chess Amount:BLACK Advisor is more");
            }
            if (n > 2) {
                temp = 1;
                System.out.printf("%s:%s\n", name, "Invalid Chess Amount:RED Cannon is more");
            }
            if (N > 2) {
                temp = 1;
                System.out.printf("%s:%s\n", name, "Invalid Chess Amount:BLACK Cannon is more");
            }
            if (s > 5) {
                temp = 1;
                System.out.printf("%s:%s\n", name, "Invalid Chess Amount:RED Soldier is more");
            }
            if (S > 5) {
                temp = 1;
                System.out.printf("%s:%s\n", name, "Invalid Chess Amount:BLACK Soldier is more");
            }
            if (g > 1) {
                temp = 1;
                System.out.printf("%s:%s\n", name, "Invalid Chess Amount:RED General is more");
            }
            if (g == 0) {
                temp = 1;
                System.out.printf("%s:%s\n", name, "Invalid Chess Amount:RED General is Less");
            }
            if (G > 1) {
                temp = 1;
                System.out.printf("%s:%s\n", name, "Invalid Chess Amount:BLACK General is more");
            }
            if (G == 0) {
                temp = 1;
                System.out.printf("%s:%s\n", name, "Invalid Chess Amount:BLACK General is Less");
            }
            if (temp == 0) {
                repaint();
                JOptionPane.showMessageDialog(null, "Loading completed");
                this.judge = true;
            }
        }
    }
    public void Undo() {
        if (a.size() == 0) {
            JOptionPane.showMessageDialog(null, "已经初始化棋盘了，无法进行悔棋");
        } else {
            ChessComponent chess1 = chessboard[a.get(a.size() - 1).getX()][a.get(a.size() - 1).getY()];
            ChessComponent chess2 = chessboard[b.get(b.size() - 1).getX()][b.get(b.size() - 1).getY()];
            swapChessComponents(chess2, chess1);
            putChessOnBoard(c.get(c.size() - 2));
            swapColor();
            a.remove(a.size() - 1);
            a.remove(a.size() - 1);
            b.remove(b.size() - 1);
            b.remove(b.size() - 1);
            c.remove(c.size() - 1);
            c.remove(c.size() - 1);
            repaint();
        }
    }
}
