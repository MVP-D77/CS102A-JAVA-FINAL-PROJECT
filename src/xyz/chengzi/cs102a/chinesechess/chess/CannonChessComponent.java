package xyz.chengzi.cs102a.chinesechess.chess;
import xyz.chengzi.cs102a.chinesechess.chessboard.ChessboardPoint;

import javax.swing.*;
import java.awt.*;
import java.util.EmptyStackException;

public class CannonChessComponent extends ChessComponent {
    public CannonChessComponent(ChessboardPoint chessboardPoint, Point location, ChessColor color) {
        super(chessboardPoint, location, color);
    }

    @Override
    public boolean canMoveTo(ChessComponent[][] chessboard, ChessboardPoint destination) {
        ChessboardPoint source = getChessboardPoint();
        if (source.getX() == destination.getX()) {
            int row = source.getX();
            int judge=0;
            for (int col = Math.min(source.getY(), destination.getY()) + 1;
                 col < Math.max(source.getY(), destination.getY()); col++) {
                if (!(chessboard[row][col] instanceof EmptySlotComponent)) {
                    judge++;
                }
            }
            if(judge==1&&!(chessboard[destination.getX()][destination.getY()]instanceof EmptySlotComponent)){
                return true;
            }
            else if(judge==0&&chessboard[destination.getX()][destination.getY()]instanceof EmptySlotComponent){
                return true;
            }
        } else if (source.getY() == destination.getY()) {
            int col = source.getY();
            int judge = 0;
            for (int row = Math.min(source.getX(), destination.getX()) + 1;
                 row < Math.max(source.getX(), destination.getX()); row++) {
                if (!(chessboard[row][col] instanceof EmptySlotComponent)) {
                    judge++;
                }
            }
            if(judge==1&&!(chessboard[destination.getX()][destination.getY()]instanceof EmptySlotComponent)){
                return true;
            }
            else if(judge==0&&chessboard[destination.getX()][destination.getY()]instanceof EmptySlotComponent){
                return true;
            }
        }
        return false;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if(getChessColor().getColor()==Color.BLACK) {
            ImageIcon icon = new ImageIcon("src/黑炮.gif");
            Image img = icon.getImage();
            g.drawImage(img, 0, 0, icon.getIconWidth() - 10,
                    icon.getIconHeight() - 10, icon.getImageObserver());
        }
        if(getChessColor().getColor()==Color.RED) {
            ImageIcon icon = new ImageIcon("src/红炮.gif");
            Image img = icon.getImage();
            g.drawImage(img, 0, 0, icon.getIconWidth() - 10,
                    icon.getIconHeight() - 10, icon.getImageObserver());
        }
        repaint();


/*
        g.setColor(CHESS_COLOR);
        g.fillOval(0, 0, getWidth() - 1, getHeight() - 1);
        g.setColor(getChessColor().getColor());
        g.drawOval(2, 2, getWidth() - 5, getHeight() - 5);
        g.setColor(getChessColor().getColor());
        if(g.getColor()==Color.BLACK){
            g.drawString("砲", 15, 25);
        }else if(g.getColor()==Color.RED){
            g.drawString("炮", 15, 25);
        }

 */

        if (isSelected()) {
            g.setColor(Color.RED);
            g.drawRect(0, 0, getWidth() - 1, getHeight() - 1);
        }
    }
}
