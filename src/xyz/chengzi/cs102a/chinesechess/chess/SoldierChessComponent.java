package xyz.chengzi.cs102a.chinesechess.chess;
import xyz.chengzi.cs102a.chinesechess.chess.ChessColor;
import xyz.chengzi.cs102a.chinesechess.chess.ChessComponent;
import xyz.chengzi.cs102a.chinesechess.chessboard.ChessboardPoint;

import javax.swing.*;
import java.awt.*;

public class SoldierChessComponent extends ChessComponent {
    public SoldierChessComponent(ChessboardPoint chessboardPoint, Point location, ChessColor color) {
        super(chessboardPoint, location, color);
    }

    @Override
    public boolean canMoveTo(ChessComponent[][] chessboard, ChessboardPoint destination) {
        ChessboardPoint source = getChessboardPoint();
        if (getChessColor() == ChessColor.BLACK) {
            if (source.getY() == destination.getY()) {
                if (source.getX() != destination.getX() - 1) {
                    return false;
                }
            } else if (source.getX() == destination.getX()) {
                if (source.getX() < 5) {
                    return false;
                }
                else if (Math.abs(source.getY()-destination.getY())!=1) {
                    return false;
                }
            }else{
                return false;
            }
        } else if (getChessColor() == ChessColor.RED) {
            if (source.getY() == destination.getY()) {
                if (source.getX() != destination.getX() + 1) {
                    return false;
                }
            }
            else if (source.getX() == destination.getX()) {
                if (source.getX() >= 5) {
                    return false;
                }
                else if (Math.abs(source.getY()-destination.getY())!=1) {
                    return false;
                }
            }
            else{
                return false;
            }
        }
        return true;
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if(getChessColor().getColor()==Color.BLACK) {
            ImageIcon icon = new ImageIcon("src/黑卒.gif");
            Image img = icon.getImage();
            g.drawImage(img, 0, 0, icon.getIconWidth() - 10,
                    icon.getIconHeight() - 10, icon.getImageObserver());
        }
        if(getChessColor().getColor()==Color.RED) {
            ImageIcon icon = new ImageIcon("src/红卒.gif");
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
            g.drawString("卒", 15, 25);
        }else if(g.getColor()==Color.RED){
            g.drawString("兵", 15, 25);
        }
        if (isSelected()) {
            g.setColor(Color.RED);
            g.drawRect(0, 0, getWidth() - 1, getHeight() - 1);
        }

         */
    }
}

