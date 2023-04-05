import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws Exception {
        Scanner exp = new Scanner(System.in);
        System.out.println("Введите выражение арабских или римских цифр:");
        String strExp = exp.nextLine();
        String strExpNoSpace = strExp.replace(" ", "");
        String resultCalc = calc(strExpNoSpace);
        System.out.println("Ответ:");
        System.out.println(resultCalc);
    }

    public static String calc(String input) throws Exception {
        boolean checkRoman;
        String[] arrOper = {"+", "-", "*", "/"};

        int operIndex = -1;
        for (int i = 0; i < arrOper.length; i++) {
            if (input.contains(arrOper[i])) {
                operIndex = i;
                break;
            }
        }
        if (operIndex == -1) {
            throw new Exception("не верное выржение");
        }
        String[] numbers = input.split("[+\\-*/]");
        if (numbers.length != 2) {
            throw new Exception("т.к. формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");
        }
        int a, b;
        int result = 0;

        if (!Roman.checkRoman((numbers[0])) && !Roman.checkRoman(numbers[1])) {
            a = Integer.parseInt(numbers[0]);
            b = Integer.parseInt(numbers[1]);
            checkRoman = false;
        } else if (Roman.checkRoman((numbers[0])) && Roman.checkRoman(numbers[1])) {
            a = Roman.converterToArab(numbers[0]);
            b = Roman.converterToArab(numbers[1]);
            checkRoman = true;
        } else {
            throw new Exception("т.к. используются одновременно разные системы счисления");
        }
        if (a <= 0 || a > 10 && b <= 0 || b > 10) {
            throw new Exception("Калькулятор принимает на вход числа от 1 до 10 включительно");
        } else {
            switch (arrOper[operIndex]) {
                case "+":
                    result = result + a + b;
                    break;
                case "-":
                    result = result + a - b;
                    break;
                case "*":
                    result = result + a * b;
                    break;
                case "/":
                    result = result + a / b;
                    break;
            }
            String result1 = String.valueOf(result);
            if (checkRoman && result <= 0) {
                throw new Exception("т.к. в римской системе нет отрицательных чисел");
            } else if (checkRoman && result > 0) {
                result1 = Roman.converterToRom(result);
            }
            return result1;
        }
    }
}

class Roman{
    static String[] romanArr = new String[]{"O", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IV", "X", "XI", "XII", "XIII", "XIV", "XV", "XVI", "XVII",
            "XVIII", "XIV", "XX", "XXI", "XXII", "XXIII", "XXIV", "XXV", "XXVI", "XXVII", "XXVIII", "XXIX", "XXX", "XXXI", "XXXII",
            "XXXIII", "XXXIV", "XXXV", "XXXVI", "XXXVII", "XXXVIII", "XXXIX", "XL", "XLI", "XLII", "XLIII", "XLIV", "XLV", "XLVI",
            "XLVII", "XLVIII", "XLIX", "L","LI","LII","LIII","LIV","LV","LVI","LVII","LVIII","LIX","LX","LXI","LXII","LXIII","LXIV","LXV","LXVI","LXVII","LXVIII","LXIX","LXX",
            "LXXI","LXXII","LXXIII","LXXIV","LXXV","LXXVI","LXXVII","LXXVIII","LXXIX","LXXX","LXXXI","LXXXII","LXXXIII","LXXXIV","LXXXV","LXXXVI","LXXXVII","LXXXVIII","LXXXIX","XC",
            "XCI","XCII","XCIII","XCIV","XCV","XCVI","XCVII","XCVIII","XCIX","C"};
    public static boolean checkRoman(String numb) {
        for (int i = 0; i < romanArr.length; i++) {
            if (numb.equals(romanArr[i])) {
                return true;
            }
        }
        return false;
    }
    public static int converterToArab(String numbRom) {
        for (int i = 0; i < romanArr.length; i++){
            if (numbRom.equals(romanArr[i])){
                return i;
            }
        }
        return -1;
    }
    public static String converterToRom( int result) {
        return romanArr[result]; }
}