import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * ATM类
 * 可以登陆、取款、存款、退出
 * Created by meepoth on 2017/12/20.
 */
public class ATM {

    public static final Map<String, ATMMenu> MENU_MAP = new HashMap<>();//菜单映射

    //初始化菜单映射
    static {
        MENU_MAP.put("1", new LoginMenu());
        MENU_MAP.put("2", new BalanceMenu());
        MENU_MAP.put("3", new MoneyInMenu());
        MENU_MAP.put("4", new MoneyOutMenu());
        MENU_MAP.put("5", new LoginOutMenu());
    }

    Account nowAccount; //当前登陆用户
    Stack<ATMMenu> menuStack = new Stack<>();//菜单栈 用于返回
    boolean quit = false;

    /**
     * 运行ATM
     *
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        ATM atm = new ATM();
        atm.showMenu();
        while (!atm.quit) {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String input = br.readLine();
            atm.processInput(input);
        }
    }

    /**
     * 展示菜单
     */
    public void showMenu() {
        if (menuStack.isEmpty()) {
            menuStack.push(new MainMenu());
        }
        ATMMenu menu = menuStack.pop();
        menu.showInfo(this);
        menuStack.push(menu);
    }

    /**
     * 处理输入
     *
     * @param input
     */
    public void processInput(String input) {
        if (menuStack.isEmpty()) {
            menuStack.push(new MainMenu());
        }
        ATMMenu menu = menuStack.pop();
        menu.processInput(this, input);
        menuStack.push(menu);
    }
}

/**
 * 菜单接口
 */
abstract class ATMMenu {
    String menuInfo = "";

    /**
     * 展示信息
     */
    void showInfo(ATM atm) {
        System.out.println(menuInfo);
    }


    /**
     * 处理输入
     *
     * @param atm
     */
    void processInput(ATM atm, String input) {
        atm.showMenu();
    }
}

/**
 * 主菜单
 */
class MainMenu extends ATMMenu {


    public MainMenu() {
        menuInfo = "1.输入账号密码\n" +
                "2.查看账户余额\n" +
                "3.存款\n" +
                "4.取款\n" +
                "5.退出";
    }


    @Override
    public void processInput(ATM atm, String input) {
        if (null == atm.nowAccount) {

        }
        atm.menuStack.push(atm.MENU_MAP.get(input));
        super.processInput(atm, input);
    }
}

/**
 * 登陆菜单
 */
class LoginMenu extends ATMMenu {
    public LoginMenu() {
        menuInfo = "1.输入账号密码\n" +
                "2.退出";
    }

    @Override
    public void processInput(ATM atm, String input) {

    }
}

/**
 * 查询余额菜单
 */
class BalanceMenu extends ATMMenu {

    @Override
    public void processInput(ATM atm, String input) {

    }
}

/**
 * 存款菜单
 */
class MoneyInMenu extends ATMMenu {

    @Override
    public void processInput(ATM atm, String input) {

    }
}

/**
 * 取款菜单
 */
class MoneyOutMenu extends ATMMenu {


    @Override
    public void processInput(ATM atm, String input) {

    }
}

/**
 * 登出菜单
 */
class LoginOutMenu extends ATMMenu {

    @Override
    public void processInput(ATM atm, String input) {

    }
}

/**
 * 账户类
 */
class Account {
    String cardId; //卡号
    long balance; //余额（分为单位，不用符点数防止精度丢失）
    String password; //密码

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public long getBalance() {
        return balance;
    }

    public void setBalance(long balance) {
        this.balance = balance;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Account{" +
                "cardId='" + cardId + '\'' +
                ", balance=" + balance +
                '}';
    }
}
