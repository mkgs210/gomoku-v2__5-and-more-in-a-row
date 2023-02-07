package controller;

import model.Game;
import model.Move;
import model.UserException;
import view.GameView;
import view.swing.SwingView;

/**
 * Контроллер
 */
public class Main {
    public static void main(String[] args) throws NullPointerException{

        Game game = new Game(20, 5);

        GameView gameView = new SwingView(game);
        while (!game.isOver()) {
            try {
                Move move = gameView.inputMove();
                try{
                    game.move(move);
                }catch (NullPointerException ignored){}
            } catch (UserException e) {
                gameView.reportError(e);
            }
        }
    }
}
