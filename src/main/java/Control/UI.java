package Control;


import service.UserService;

import java.util.Scanner;

public class UI {
    Scanner scanner = new Scanner(System.in);
    UserService userService = new UserService();
    BackendDrivenUI backUI = new BackendDrivenUI();
    public void run(){
        System.out.print("Управлеие бд:" +
                "\n1-найти пользователя по id" +
                "\n2-добавить нового пользователя" +
                "\n3-удалить пользователя" +
                "\n4-обновить данные пользователя" +
                "\n5-вывести список всех пользователей" +
                "\nВведите команду " );
        String s = scanner.next();
        switch (s){
            case "1":backUI.find();run();
            case "2":backUI.save();run();
            case "3":backUI.delete();run();
            case "4":backUI.update();run();
            case "5":backUI.findAll();run();
            default:run();
        }
    }
}
