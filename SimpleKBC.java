package project;
import java.util.Scanner;
public class KBCQuiz {
    public static final String GREEN = "\u001B[32m";
    public static final String RESET = "\u001B[0m";
    public static final String RED = "\u001B[31m";
    public static final String BLUE = "\u001B[34m";
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println(RED + "========================================" + RESET);
        System.out.println(RED + "        WELCOME TO KBC QUIZ         " + RESET);
        System.out.println(RED + "========================================" + RESET);
        System.out.print("Enter Player Name: ");
        String name = sc.nextLine();
        System.out.print("Enter Age: ");
        int age = sc.nextInt();
        System.out.println(GREEN + "\nWelcome " + name + " (" + age + " years)" + RESET);
        System.out.println("----------------------------------------");
        System.out.println(" Rules:");
        System.out.println("1. Each question has 5 options");
        System.out.println("2. Option 5 = Lifeline");
        System.out.println("3. Lifelines: 50-50 & Audience Poll (Once each)");
        System.out.println("4. Wrong answer ends the game");
        System.out.println("----------------------------------------\n");
        String[] questions = {
        	    "1. Which keyword is used to inherit a class in Java?",
        	    "2. Which planet has the largest number of moons?",
        	    "3. What will be the output of: System.out.println(5 + 2 + \"3\");",
        	    "4. Who directed the movie *Baahubali*?",
        	    "5. Which data structure works on LIFO principle?",
        	    "6. Which is the longest river in the world?",
        	    "7. What is the time complexity of Binary Search?",
        	    "8. Who won the Cricket World Cup 2011?",
        	    "9. Which HTML tag is used to create a hyperlink?",
        	    "10. What will be the output of: System.out.println(\"Java\".length());"
        };
        String[][] options = {
        	    {"1) implements", "2) inherit", "3) extends", "4) super"},
        	    {"1) Earth", "2) Mars", "3) Jupiter", "4) Saturn"},
        	    {"1) 10", "2) 73", "3) 523", "4) 32"},
        	    {"1) S. S. Rajamouli", "2) Shankar", "3) Mani Ratnam", "4) Atlee"},
        	    {"1) Queue", "2) Stack", "3) Array", "4) Tree"},
        	    {"1) Amazon", "2) Nile", "3) Yangtze", "4) Mississippi"},
        	    {"1) O(n)", "2) O(log n)", "3) O(n²)", "4) O(1)"},
        	    {"1) Australia", "2) India", "3) England", "4) Sri Lanka"},
        	    {"1) <link>", "2) <href>", "3) <a>", "4) <url>"},
        	    {"1) 3", "2) 4", "3) 5", "4) 6"}
        };
        int[] answers = {3, 4, 2, 1, 2, 2, 2, 2, 3, 2};
        int[] prize = {1000, 2000, 5000, 10000, 20000, 40000, 80000, 160000, 320000, 640000};
        boolean fiftyUsed = false;
        boolean audienceUsed = false;
        for (int i = 0; i < questions.length; i++) {
            System.out.println(BLUE + "\n----------------------------------------" + RESET);
            System.out.println( questions[i]);
            for (int j = 0; j < 4; j++) {
                System.out.println(options[i][j]);
            }
            System.out.println("5) Help (Lifeline)");
            System.out.print("Choose your option: ");
            int choice = sc.nextInt();
            if (choice == 5) {
                if (fiftyUsed && audienceUsed) {
                    System.out.println(RED + "No lifelines left!" + RESET);
                    i--;
                    continue;
                }
                System.out.println("Choose Lifeline:");
                if (!fiftyUsed) System.out.println("1) 50-50");
                if (!audienceUsed) System.out.println("2) Audience Poll");
                int life = sc.nextInt();
                if (life == 1 && !fiftyUsed) {
                    fiftyUsed = true;
                    System.out.println(BLUE + "50-50 Lifeline Activated!" + RESET);
                    boolean[] show = new boolean[4];
                    int correct = answers[i] - 1;
                    show[correct] = true;
                    int randomWrong;
                    do {
                        randomWrong = (int) (Math.random() * 4);
                    } while (randomWrong == correct);
                    show[randomWrong] = true;
                    System.out.println(BLUE + "\n" + questions[i] + RESET);
                    for (int k = 0; k < 4; k++) {
                        if (show[k]) {
                            System.out.println(options[i][k]);
                        }
                    }
                    System.out.print("Choose your option: ");
                    int newChoice = sc.nextInt();
                    if (newChoice == answers[i]) {
                        System.out.println(GREEN + "✔ Correct Answer!" + RESET);
                        System.out.println(GREEN + "🎉 Congratulations " + name +
                                ", your prize is Rs." + prize[i] + RESET);
                    } else {
                        System.out.println(RED + "✖ Wrong Answer!" + RESET);
                        System.out.println(RED + "💥 Game Over " + name + RESET);
                        System.out.println("You won Rs." + (i > 0 ? prize[i - 1] : 0));
                        return;
                    }
                    continue;
                } 
                else if (life == 2 && !audienceUsed) {
                    audienceUsed = true;
                    System.out.println(BLUE + "Audience Poll:" + RESET);
                    System.out.println(BLUE + "Option " + answers[i] + " has highest votes!" + RESET);
                    i--;
                    continue;
                } 
                else {
                    System.out.println(RED + "This lifeline already used!" + RESET);
                    i--;
                    continue;
                }
            }
            if (choice == answers[i]) {
                System.out.println(GREEN + " Correct Answer!" + RESET);
                System.out.println(GREEN + " Congratulations " + name +
                        ", your prize is Rs." + prize[i] + RESET);
            } else {
                System.out.println(RED + " Wrong Answer!" + RESET);
                System.out.println(RED + " Game Over " + name + RESET);
                System.out.println("You won Rs." + (i > 0 ? prize[i - 1] : 0));
                return;
            }
        }
        System.out.println(GREEN + "\n Amazing " + name + "! You completed all questions!" + RESET);
        System.out.println(GREEN + " Total Prize: Rs." + prize[9] + RESET);
        sc.close();
    }
}
