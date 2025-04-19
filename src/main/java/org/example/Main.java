package org.example;

import org.example.userservice.dao.UserDao;
import org.example.userservice.dao.UserDaoImpl;
import org.example.userservice.entity.User;
import org.example.userservice.util.HibernateUtil;

import java.util.List;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final UserDao userDao = new UserDaoImpl();

    public static void main(String[] args) {
    boolean quit = false;

    while (!quit) {
    printMenu();
    String input = scanner.nextLine();

    switch (input) {
        case "1" -> createUser();
        case "2" -> findUser();
        case "3" -> listUsers();
        case "4" -> updateUser();
        case "5" -> deleteUser();
        case "6" -> quit = true;
        default -> System.out.println("Неверный ввод");
    }
    }
        HibernateUtil.shutdown();
        System.out.println("Выход");
    }

    private static void printMenu() {
        System.out.println("\n    Меню   ");
        System.out.println("1. Создать пользователя");
        System.out.println("2. Найти пользователя по id");
        System.out.println("3. Показать всех пользователей");
        System.out.println("4. Обновить пользователя");
        System.out.println("5. Удалить пользователя");
        System.out.println("6. Выход");
        System.out.print("Выберите действие: ");
    }

    private static void createUser() {
        System.out.print("Имя: ");
        String name = scanner.nextLine();

        System.out.print("Email: ");
        String email = scanner.nextLine();

        System.out.print("Возраст: ");
        int age = Integer.parseInt(scanner.nextLine());

        User user = new User(name, email, age);
        userDao.save(user);
        System.out.println("Пользователь создан: " + user);
    }

    private static void findUser(){
        System.out.print("ID пользователя: ");
        long id = Long.parseLong(scanner.nextLine());

        User user = userDao.findById(id);
        if (user == null) {
            System.out.println("Пользователь не найден.");
        }else System.out.println(user);
    }

    private static void listUsers() {
        List<User> users = userDao.findAll();
        if (users.isEmpty()) {
            System.out.println("Пользователи не найдены.");
        } else {
            users.forEach(System.out::println);
        }
    }

    private static void updateUser() {
        System.out.print("ID пользователя: ");
        long id = Long.parseLong(scanner.nextLine());

        User user = userDao.findById(id);
        if (user == null) {
            System.out.println("Пользователь не найден.");
            return;
        }

        System.out.print("Новое имя (текущее: " + user.getName() + "): ");
        String name = scanner.nextLine();
        if (!name.isEmpty()) user.setName(name);

        System.out.print("Новый email (текущий: " + user.getEmail() + "): ");
        String email = scanner.nextLine();
        if (!email.isEmpty()) user.setEmail(email);

        System.out.print("Новый возраст (текущий: " + user.getAge() + "): ");
        String ageStr = scanner.nextLine();
        if (!ageStr.isEmpty()) user.setAge(Integer.parseInt(ageStr));

        userDao.update(user);
        System.out.println("Пользователь обновлён.");
    }

    private static void deleteUser() {
        System.out.print("ID пользователя для удаления: ");
        long id = Long.parseLong(scanner.nextLine());
        User user = userDao.findById(id);
        if (user == null) {
            System.out.println("Пользователя не существует.");
            return;
        }
        userDao.delete(id);

        System.out.println("Пользователь удалён");
    }
}