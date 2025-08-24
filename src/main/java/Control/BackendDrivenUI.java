package Control;

import entities.User;
import service.UserService;

import java.util.List;
import java.util.Scanner;

public class BackendDrivenUI {
    private UserService userService = new UserService();
    private Scanner scanner = new Scanner(System.in);
    private String s;

    BackendDrivenUI() {
    }
    public void save() {
        userService.saveUser(newUser());
        System.out.println("Данные успешно добавлены");

    }

    public void update() {
        User oldUser = findById();
        User newUser = newUser();
        oldUser.setName(newUser.getName());
        oldUser.setAge(newUser.getAge());
        oldUser.setEmail(newUser.getEmail());
        userService.updateUser(oldUser);
        System.out.println("Данные успешно обновлены");

    }

    public void delete() {
        userService.deleteUser(findById());
        System.out.println("Данные успешно удалены");

    }
    public void find() {
        System.out.println(findById().toString());

    }
    public User findById() {
        System.out.println("Укажите id");
        s = scanner.next();
        User user = userService.findUser(Integer.parseInt(s));
        return user;
    }

    public void findAll() {
        System.out.println(userService.findAllUsers().toString());
    }
    User newUser(){
        User user = new User();
        System.out.println("Введите Имя");
        s = scanner.next();
        user.setName(s);
        System.out.println("Введите Возраст");
        s = scanner.next();
        user.setAge(Integer.parseInt(s));
        System.out.println("Введите Почту");
        s = scanner.next();
        user.setEmail(s);
        return user;
    }
}
