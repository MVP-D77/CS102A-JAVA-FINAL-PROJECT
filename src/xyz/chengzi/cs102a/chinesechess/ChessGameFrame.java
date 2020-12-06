package xyz.chengzi.cs102a.chinesechess;

import xyz.chengzi.cs102a.chinesechess.chessboard.ChessboardComponent;
import xyz.chengzi.cs102a.chinesechess.listener.ChessListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class ChessGameFrame extends JFrame {
    public ChessGameFrame() {
        PlayMusic music =new PlayMusic();
        setTitle("2019 CS102A Project Demo");
        setSize(520 , 450);
        setLocationRelativeTo(null); // Center the window.
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(null);

        JLabel b = new JLabel(new ImageIcon("src/y.jpg"));
        b.setLocation(300, 40);
        b.setSize(220, 200);
        add(b);

        JLabel a = new JLabel(new ImageIcon("src/timg.jpeg"));
        a.setLocation(0, 40);
        a.setSize(520, 350);
        add(a);

        JButton button0 = new JButton("Let's play a chess");
        button0.setLocation(150, 10);
        button0.setSize(200, 20);
        button0.setBackground(Color.RED);
        add(button0);
        button0.setVisible(true);
        button0.addActionListener((e) -> {
            remove(a);
            remove(b);
            remove(button0);
            ChessboardComponent chessboard = new ChessboardComponent(400, 400);
            add(chessboard);

            JButton button1 = new JButton("New Game");
            button1.addActionListener((e1) -> {
                chessboard.Initialize();
            });

            button1.setLocation(400,40);
            button1.setSize(120,40);
            button1.setBackground(Color.CYAN);
            add(button1);
            button1.setVisible(true);

            JButton button2 = new JButton("Load Game");
            button2.setLocation(400,80);
            button2.setSize(120,40);
            button2.setBackground(Color.CYAN);
            button2.addActionListener((e2) -> {
                try{
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.showOpenDialog(null);
                String path = fileChooser.getSelectedFile().getAbsolutePath();
                    chessboard.loadGame(path);
                }catch(NullPointerException ex) {
                    JOptionPane.showMessageDialog(null, "读取文档失败，未选择文件");
                }catch (Exception ex) {
                        ex.printStackTrace();
                    }
            });
                add(button2);
            button2.setVisible(true);

            JButton button3 = new JButton("Save Game");
            button3.setLocation(400,120);
            button3.setSize(120,40);
            button3.setBackground(Color.CYAN);
            button3.addActionListener((e1) -> {
                try {
                    JFileChooser fileChooser = new JFileChooser();
                    fileChooser.showSaveDialog(null);
                    String file = fileChooser.getSelectedFile().getAbsolutePath();
                    chessboard.save(file);
                }catch(NullPointerException ex){
                    JOptionPane.showMessageDialog(null, "保存棋盘失败，未选择保存路径");
                }
                catch (Exception ex) {
                    ex.printStackTrace();
                }
            });
                add(button3);
            button3.setVisible(true);

            JButton button4 = new JButton("Load Step");
            button4.setLocation(400,160);
            button4.setSize(120,40);
            button4.setBackground(Color.CYAN);
            add(button4);
            button4.setVisible(true);
            button4.addActionListener((e4) ->{
                try{
                    JFileChooser fileChooser = new JFileChooser();
                    fileChooser.showOpenDialog(null);
                    String path = fileChooser.getSelectedFile().getAbsolutePath();
                    chessboard.loadSteps(path);
                }catch(NullPointerException ex) {
                    JOptionPane.showMessageDialog(null, "读取棋谱失败，未选择文件");
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            });

            JButton button5 = new JButton("Undo operation");
            button5.setLocation(400,200);
            button5.setSize(120,40);
            button5.setBackground(Color.CYAN);
            button5.addActionListener((e5)->{
                chessboard.Undo();
            });
            add(button5);
            button5.setVisible(true);

            JButton button6 = new JButton("Listen Music");
            button6.setLocation(400,240);
            button6.setSize(120,40);
            button6.addActionListener((e6) ->{
                music.play();
                    });
                button6.setBackground(Color.CYAN);
            add(button6);
            button6.setVisible(true);

            JButton button7 = new JButton("Stop Music");
            button7.setLocation(400,280);
            button7.setSize(120,40);
            button7.addActionListener((e7) ->{
                music.stop();
            });
            button7.setBackground(Color.CYAN);
            add(button7);
            button7.setVisible(true);

            JButton button8 = new JButton("Give Up");
            button8.setLocation(400,320);
            button8.setSize(120,40);
            button8.addActionListener((e8) ->{
                chessboard.giveUp();
            });
            button8.setBackground(Color.CYAN);
            add(button8);
            button8.setVisible(true);
            repaint();


        });

        JLabel statusLabel = new JLabel("Chinese Chess");
        statusLabel.setLocation(0, 400);
        statusLabel.setSize(200, 10);
        add(statusLabel);

        JButton button = new JButton("...");
        button.addActionListener((e) -> JOptionPane.showMessageDialog(this, "Hello, world!"));
        button.setLocation(370, 400);
        button.setSize(20, 10);
        add(button);
        button.setVisible(true);

    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ChessGameFrame mainFrame = new ChessGameFrame();
            mainFrame.setVisible(true);
        });
    }
}