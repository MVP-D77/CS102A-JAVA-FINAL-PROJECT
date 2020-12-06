package xyz.chengzi.cs102a.chinesechess.listener;

import xyz.chengzi.cs102a.chinesechess.chess.ChessComponent;
import xyz.chengzi.cs102a.chinesechess.chessboard.ChessboardComponent;

public class ChessboardChessListener extends ChessListener {
    private ChessboardComponent chessboardComponent;
    private ChessComponent first;

    public ChessboardChessListener(ChessboardComponent chessboardComponent) {
        this.chessboardComponent = chessboardComponent;
    }

    @Override
    public void onClick(ChessComponent chessComponent) {
        if (chessboardComponent.getJudge()) {
            if (first == null) {
                if (handleFirst(chessComponent)) {
                    chessComponent.setSelected(true);
                    first = chessComponent;
                    for (int i = 0; i < 10; i++) {
                        for (int j = 0; j < 9; j++) {
                            ChessComponent information = chessboardComponent.getChessboard()[i][j];
                            if (first.canMoveTo(chessboardComponent.getChessboard(), information.getChessboardPoint())) {
                                if (first.getChessColor() != information.getChessColor()) {
                                    information.setJudge(true);
                                }
                            }
                        }
                    }
                    chessboardComponent.repaint();
                }
            } else {
                if (first == chessComponent) { // Double-click to unselect.
                    chessComponent.setSelected(false);
                    for (int i = 0; i < 10; i++) {
                        for (int j = 0; j < 9; j++) {
                            chessboardComponent.getChessboard()[i][j].setJudge(false);
                        }
                    }
                    first = null;
                    chessboardComponent.repaint();
                } else {
                    if (first.getChessColor() == chessComponent.getChessColor()) {
                        first.setSelected(false);
                        chessComponent.setSelected(true);
                        for (int i = 0; i < 10; i++) {
                            for (int j = 0; j < 9; j++) {
                                chessboardComponent.getChessboard()[i][j].setJudge(false);
                            }
                        }
                        first = chessComponent;
                        for (int i = 0; i < 10; i++) {
                            for (int j = 0; j < 9; j++) {
                                ChessComponent information = chessboardComponent.getChessboard()[i][j];
                                if (first.canMoveTo(chessboardComponent.getChessboard(), information.getChessboardPoint())) {
                                    if (first.getChessColor() != information.getChessColor()) {
                                        information.setJudge(true);
                                    }
                                }
                            }
                        }
                        chessboardComponent.repaint();


                    } else if (handleSecond(chessComponent)) {
                        chessboardComponent.swapChessComponents(first, chessComponent);
                        chessboardComponent.swapColor();
                        for (int i = 0; i < 10; i++) {
                            for (int j = 0; j < 9; j++) {
                                chessboardComponent.getChessboard()[i][j].setJudge(false);
                            }
                        }
                        first.setSelected(false);
                        first = null;
                        chessboardComponent.repaint();
                    }
                }
            }
        }
    }
    private boolean handleFirst(ChessComponent chessComponent) {
        return chessComponent.getChessColor() == chessboardComponent.getCurrentColor();
    }

    private boolean handleSecond(ChessComponent chessComponent) {
        return chessComponent.getChessColor() != chessboardComponent.getCurrentColor() &&
                first.canMoveTo(chessboardComponent.getChessboard(), chessComponent.getChessboardPoint());
    }
}
