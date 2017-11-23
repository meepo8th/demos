package practice;

import java.util.Observable;
import java.util.Observer;

class Game extends Observable {
    private String name;
    private String state;

    public Game(String name, String state) {
        this.name = name;
        this.state = state;
    }

    public String getName() {
        return name;
    }

    public String getState() {
        this.notifyObservers(state);
        System.out.println(getName() + state);
        return state;
    }

    public void setState(String state) {
        this.state = state;
        this.setChanged();
        this.notifyObservers(state);
    }
}

//No.1
//开始写代码，在此构造Guard类
class Guard implements Observer {
    String game;

    public Guard(String game) {
        this.game = game;
    }

    @Override
    public void update(Observable o, Object arg) {
        System.out.println(this.game + arg);
    }
}
//end_code

public class ObserverTest {
    public static void main(String[] args) {
        Game game = new Game("游戏", "开始");
        Guard guard = new Guard("游戏");
        game.addObserver(guard);
        game.getState();
        game.setState("进行中");
        game.setState("结束");
    }
}