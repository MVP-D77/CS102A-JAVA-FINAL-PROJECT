package xyz.chengzi.cs102a.chinesechess.chess;

import xyz.chengzi.cs102a.chinesechess.chessboard.ChessboardPoint;

import javax.swing.*;
import java.awt.*;

public class HorseChessComponent extends ChessComponent {
    public HorseChessComponent(ChessboardPoint chessboardPoint, Point location, ChessColor color) {
        super(chessboardPoint, location, color);
    }

    @Override
    public boolean canMoveTo(ChessComponent[][] chessboard, ChessboardPoint destination) {
        ChessboardPoint source = getChessboardPoint();
        if(Math.abs(source.getX()-destination.getX())==1&&Math.abs(source.getY()-destination.getY())==2){
            if(chessboard[source.getX()][Math.min(source.getY(),destination.getY())+1]instanceof EmptySlotComponent){
                return true;
            }
        }
        else if(Math.abs(source.getY()-destination.getY())==1&&Math.abs(source.getX()-destination.getX())==2) {
            if (chessboard[Math.min(source.getX(), destination.getX())+1][source.getY()]instanceof EmptySlotComponent) {
                return true;
            }
        }
        return false;
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if(getChessColor().getColor()==Color.BLACK) {
            ImageIcon icon = new ImageIcon("src/黑马.gif");
            Image img = icon.getImage();
            g.drawImage(img, 0, 0, icon.getIconWidth() - 10,
                    icon.getIconHeight() - 10, icon.getImageObserver());
        }
        if(getChessColor().getColor()==Color.RED) {
            ImageIcon icon = new ImageIcon("src/红马.gif");
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
            g.drawString("馬", 15, 25);
        }else if(g.getColor()==Color.RED){
            g.drawString("傌", 15, 25);
        }
        if (isSelected()) {
            g.setColor(Color.RED);
            g.drawRect(0, 0, getWidth() - 1, getHeight() - 1);
        }

         */
    }
}
