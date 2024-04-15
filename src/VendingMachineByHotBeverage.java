import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class VendingMachineByHotBeverage implements VendingMachine {

    private static List<HotBeverage> beverages;

    public VendingMachineByHotBeverage() {
        beverages = new ArrayList<>();
    }

    @Override
    public void addBeverage(List<Product> productLst) {

    }

    public void addBeverage(HotBeverage beverage) {
        beverages.add(beverage);
    }

    @Override
    public Product getProduct(String name) {
        return getProduct(name, 100); // По умолчанию, объем 100 мл
    }

    public Product getProduct(String name, int volume) {
        for (HotBeverage beverage : beverages) {
            if (beverage.getName().equals(name) && beverage.getVolume() == volume) {
                return beverage;
            }
        }
        return null; // Или выбросить исключение, если продукт не найден
    }

    public Set<Object> extractFieldValues(String fieldName, String productName) {
        Set<Object> fieldValues = new TreeSet<>();
        for (HotBeverage beverage : beverages) {
            if (productName.equals(beverage.getName())) {
                switch (fieldName) {
                    case "volume":
                        fieldValues.add(beverage.getVolume());
                        break;
                    case "price":
                        fieldValues.add(beverage.getPrice());
                        break;
                    case "temperature":
                        fieldValues.add(beverage.getTemperature());
                        break;
                    default:
                        // Неизвестное поле
                        // System.out.println("Неизвестное поле: " + fieldName);
                        break;
                }
            }
        }
        return fieldValues;
    }

    public static void main(String[] args) {
        VendingMachineByHotBeverage machine = new VendingMachineByHotBeverage();

        // Наполнение ассортимента нашего автомата
        Tea greenTea = new Tea("Green Tea", 55.0, 400, 90.0, "Maofen");
        Coffe espresso = new Coffe("Espresso", 70.0, 100, 85.5, "Dark Roast");
        Coffe americano = new Coffe("Americano", 110.0, 150, 75.0, "Medium Roast");
        Coffe americanoDouble = new Coffe("Americano", 170.0, 300, 75.0, "Medium Roast");
        Tea blackTea = new Tea("Black Tea", 60.0, 400, 90.0, "Orange Pecoe");
        Tea jasmineTea = new Tea("Jasmine Green Tea", 75.0, 400, 90.0, "Molly Tcha Van");
        Coffe cappucino = new Coffe("Cappucino", 150.0, 300, 72.0, "Dark Roast");
        Coffe cappucinoXL = new Coffe("Cappucino", 190.0, 400, 72.0, "Medium Roast");
        Coffe cappucinoXXL = new Coffe("Cappucino", 230.0, 500, 72.0, "Light Roast");
        Coffe latte = new Coffe("Latte", 170.0, 300, 72.0, "Dark Roast");
        Coffe latteXl = new Coffe("Latte", 210.0, 400, 72.0, "Medium Roast");
        Coffe latteXXL = new Coffe("Latte", 250.0, 500, 72.0, "Light Roast");

        machine.addBeverage(greenTea);
        machine.addBeverage(espresso);
        machine.addBeverage(americano);
        machine.addBeverage(americanoDouble);
        machine.addBeverage(blackTea);
        machine.addBeverage(jasmineTea);
        machine.addBeverage(cappucino);
        machine.addBeverage(cappucinoXL);
        machine.addBeverage(cappucinoXXL);
        machine.addBeverage(latte);
        machine.addBeverage(latteXl);
        machine.addBeverage(latteXXL);

        // Реализация логики автомата
        HashSet<HotBeverage> beverageSet = new HashSet<>(beverages);
        Scanner scanner = new Scanner(System.in);
        boolean continueShopping = true;
        while (continueShopping) {
            System.out.println("Выберите напиток из списка:");
            for (HotBeverage beverage : beverageSet) {
                System.out.println(beverage.getName());
            }
            System.out.print("Введите название напитка: ");
            String productName = scanner.nextLine();

            Set<Object> volumeValues = machine.extractFieldValues("volume", productName);
            System.out.print("Теперь выберите объём вашего напитка из " + volumeValues + " мл.: ");
            try {
                int requiredVolume = scanner.nextInt();
                scanner.nextLine(); // Прочитать оставшийся символ новой строки после nextInt()
                Product product = machine.getProduct(productName, requiredVolume);
                if (product != null) {
                    if (product instanceof HotBeverage) {
                        System.out.println("Вы выбрали: " + product);
                    }
                } else {
                    System.out.println("Такого напитка в автомате сейчас нет...");

                }
            } catch (NumberFormatException e) {
                System.err.println("Ошибка: введенное значение не является целым числом.");
            }

            System.out.print("Продолжить покупки? (y/n): ");
            String choice = scanner.nextLine();
            continueShopping = choice.equalsIgnoreCase("y");
        }

        scanner.close();
    }

}
