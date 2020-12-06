package xyz.chengzi.cs102a.chinesechess.chess;

import xyz.chengzi.cs102a.chinesechess.chess.ChessColor;
import xyz.chengzi.cs102a.chinesechess.chess.ChessComponent;
import xyz.chengzi.cs102a.chinesechess.chessboard.ChessboardPoint;

import javax.swing.*;
import java.awt.*;

public class ElephantChessComponent extends ChessComponent {
    public ElephantChessComponent(ChessboardPoint chessboardPoint, Point location, ChessColor color) {
        super(chessboardPoint, location, color);
    }

    @Override
    public boolean canMoveTo(ChessComponent[][] chessboard, ChessboardPoint destination) {
        ChessboardPoint source = getChessboardPoint();
        if(this.getChessColor()==ChessColor.BLACK) {
            if (destination.getX() <= 4) {
                if(Math.abs(source.getX()-destination.getX())==2&&Math.abs(source.getY()-destination.getY())==2){
                    if(chessboard[(source.getX()+destination.getX())/2][(source.getY()+destination.getY())/2]
                            instanceof EmptySlotComponent) {
                        return true;
                    }
                }
            }
        }
        else{
            if (destination.getX() > 4) {
                if(Math.abs(source.getX()-destination.getX())==2&&Math.abs(source.getY()-destination.getY())==2){
                    if(chessboard[(source.getX()+destination.getX())/2][(source.getY()+destination.getY())/2]
                            instanceof EmptySlotComponent) {
                        return true;
                    }
                }
            }
        }
                return false;
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if(getChessColor().getColor()==Color.BLACK) {
            ImageIcon icon = new ImageIcon("src/黑象.gif");
            Image img = icon.getImage();
            g.drawImage(img, 0, 0, icon.getIconWidth() - 10,
                    icon.getIconHeight() - 10, icon.getImageObserver());
        }
        if(getChessColor().getColor()==Color.RED) {
            ImageIcon icon = new ImageIcon("src/红象.gif");
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
            g.drawString("象", 15, 25);
        }else if(g.getColor()==Color.RED){
            g.drawString("相", 15, 25);
        }
        if (isSelected()) {
            g.setColor(Color.RED);
            g.drawRect(0, 0, getWidth() - 1, getHeight() - 1);
        }

         */
    }
}
