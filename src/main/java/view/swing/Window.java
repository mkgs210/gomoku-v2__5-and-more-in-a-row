package view.swing;

import model.Game;
import model.Move;
import model.UserException;
import view.GameView;
import view.swing.SwingView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class Window extends JFrame {
    JLabel titleLable = new JLabel("Ещё раз?");
    JLabel length_Lable = new JLabel("Размер поля");
    JLabel layers_Lable = new JLabel("Сколько в ряд?");
    JTextField length = new JTextField();
    JTextField layers = new JTextField();
    JButton start = new JButton("Запуск");
    JButton exit = new JButton("Выход");

    public Window() {
        super("");
        this.setBounds(400, 280, 560, 340);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setVisible(true);

        Container mainPanel = this.getContentPane();
        mainPanel.setLayout(new GridLayout(3, 2, 2, 2));

        mainPanel.add(titleLable);
        mainPanel.add(length_Lable);
        mainPanel.add(layers_Lable);
        mainPanel.add(length);
        mainPanel.add(layers);

        ButtonGroup b_group = new ButtonGroup();
        b_group.add(start);
        b_group.add(exit);
        mainPanel.add(start);
        mainPanel.add(exit);
        mainPanel.setLayout(null);
        start.addActionListener(new ButtonStart());
        exit.addActionListener(e -> {
            System.exit(1);
        });

        titleLable.setBounds(120, 40, 340, 35);
        titleLable.setFont(new Font("Полужирный", Font.BOLD, 30));
        length_Lable.setBounds(94, 124, 90, 30);
        layers_Lable.setBounds(94, 174, 90, 30);
        length.setBounds(204, 124, 260, 30);
        layers.setBounds(204, 174, 260, 30);
        start.setBounds(157, 232, 100, 30);
        exit.setBounds(304, 232, 100, 30);
        mainPanel.setBackground(Color.WHITE);
    }

    class ButtonStart implements ActionListener {

        public void dots(Integer size, Integer line) throws IOException {
            Game game = new Game(size, line);

            GameView gameView = new SwingView(game);
            while (!game.isOver()) {
                try {
                    Move move = gameView.inputMove();
                    try {
                        game.move(move);
                    } catch (NullPointerException ignored) {
                    }
                } catch (UserException e) {
                    gameView.reportError(e);
                }
            }
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                    int size  = Integer.parseInt(length.getText().replace(" ", ""));
                    int line = Integer.parseInt(layers.getText().replace(" ", ""));
                    if (size > 0 & line > 0) {
                        setVisible(false);
                        dots(size, line);
                    } else {
                        System.out.println("Размеры должны быть > 0");
                    }
            } catch (IOException ex) {
                System.out.println("Что-то пошло не так");
                setVisible(true);
                JOptionPane.showMessageDialog(null, "Что-то пошло не так", "Error", JOptionPane.PLAIN_MESSAGE);
            }
        }
    }
}