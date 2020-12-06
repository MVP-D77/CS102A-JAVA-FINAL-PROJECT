package xyz.chengzi.cs102a.chinesechess.chess;
import java.awt.*;

public enum ChessName {
    G("将",Color.BLACK),
    g("帥",Color.RED),
    A("士",Color.BLACK),
    a("仕",Color.RED),
    E("象",Color.BLACK),
    e("相",Color.RED),
    H("馬",Color.BLACK),
    h("傌",Color.RED),
    C("車",Color.BLACK),
    c("俥",Color.RED),
    N("砲",Color.BLACK),
    n("炮",Color.RED),
    S("卒",Color.BLACK),
    s("兵",Color.RED);

    private  String name;
    private  Color color;
    private ChessName(String x,Color y){
        this.name = x;
        this.color = y;
    }

    public Color getColor() {
        return color;
    }

    public String getName() {
        return name;
    }
}
