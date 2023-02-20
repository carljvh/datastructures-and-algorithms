import java.util.Scanner;

public class GuessTheNumber2 {
    public static void guess2nd(int lo, int hi, Scanner input) {
        String ans = "";
        int mid = (hi + lo) / 2;
        System.out.println("My guess is: " + mid);
        while (!ans.equals("correct")) {
            System.out.println("Enter higher, lower or correct: ");
            ans = input.nextLine();
            if (ans.equals("correct"))
                break;
            if (ans.equals("lower")) {
                hi = (hi + lo) / 2 - 1;
            }
            if (ans.equals("higher")) {
                lo = (hi + lo) / 2 + 1;
            }
            System.out.println("Guess: " + (hi + lo) / 2);
        }
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter interval in format \'startpoint-endpoint\': ");
        String interval = input.nextLine();
        String[] endpoints = interval.split("-");
        guess2nd(Integer.parseInt(endpoints[0]), Integer.parseInt(endpoints[1]), input);
    }
}