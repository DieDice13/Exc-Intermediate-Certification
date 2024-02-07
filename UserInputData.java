import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

/**
 * UserInputData
 */
public class UserInputData {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(
                "Введите данные (Фамилию, Имя, Отчество, Дату рождения(01.01.0001), Номер телефона и пол(f или m, где f(женский) а m(мужской)) через пробел):");
        String input = scanner.nextLine();
        String[] data = input.split(" ");

        if (data.length != 6) {
            System.out.println("Вы ввели неверное количество данных. Требуется 6, но получено " + data.length);
            return;
        }

        String surname = data[0];
        String name = data[1];
        String patronymic = data[2];
        String birthDate = data[3];
        String phoneNumber = data[4];
        String gender = data[5];

        SimpleDateFormat format = new SimpleDateFormat("dd.mm.yyyy");
        try {
            format.parse(birthDate);
        } catch (ParseException e) {
            System.out.println("Неверный формат даты рождения. Требуется dd.mm.yyyy");
            return;
        }

        try {
            Long.parseLong(phoneNumber);
        } catch (NumberFormatException e) {
            System.out.println("Неверный формат номера телефона. Требуется целое число");
            return;
        }

        if (!gender.equals("f") && !gender.equals("m")) {
            System.out.println("Неверный формат пола. Требуется 'f' или 'm'");
            return;
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(surname + ".txt", true))) {
            writer.write(surname + " " + name + " " + patronymic + " " + birthDate + " " + phoneNumber + " " + gender);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}