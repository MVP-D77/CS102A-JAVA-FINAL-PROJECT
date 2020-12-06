package xyz.chengzi.cs102a.chinesechess.chess;
import xyz.chengzi.cs102a.chinesechess.chess.ChessColor;
import xyz.chengzi.cs102a.chinesechess.chess.ChessComponent;
import xyz.chengzi.cs102a.chinesechess.chessboard.ChessboardPoint;

import javax.swing.*;
import java.awt.*;

public class AdvisorChessComponent extends ChessComponent {
    public AdvisorChessComponent(ChessboardPoint chessboardPoint, Point location, ChessColor color) {
        super(chessboardPoint, location, color);
    }

    @Override
    public boolean canMoveTo(ChessComponent[][] chessboard, ChessboardPoint destination) {
        ChessboardPoint source = getChessboardPoint();
        if(destination.getY()<3||destination.getY()>5){
            return false;
        }
        else if(destination.getX()>2&&destination.getX()<7){
            return false;
        }
        else if(Math.abs(source.getX()-destination.getX())!=1||Math.abs(source.getY()-destination.getY())!=1){
            return false;
        }
        return true;
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if(getChessColor().getColor()==Color.BLACK) {
            ImageIcon icon = new ImageIcon("src/黑士.gif");
            Image img = icon.getImage();
            g.drawImage(img, 0, 0, icon.getIconWidth() - 10,
                    icon.getIconHeight() - 10, icon.getImageObserver());
        }
        if(getChessColor().getColor()==Color.RED) {
            ImageIcon icon = new ImageIcon("src/红士.gif");
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
            g.drawString("士", 15, 25);
        }else if(g.getColor()==Color.RED){
            g.drawString("仕", 15, 25);
        }
        if (isSelected()) {
            g.setColor(Color.RED);
            g.drawRect(0, 0, getWidth() - 1, getHeight() - 1);

         */
        }
    }
