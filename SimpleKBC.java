import java.util.Scanner;

public class SimpleKBC {
    public static void main(String[] args) {
        
        String[] questions = {
                "Which country is the largest by land area?",
                "What is the capital of France?",
                "Which organization has 193 member states?",
                "Which country is not a permanent UN Security Council member?",
                "What is the currency of Japan?",
                "Which country is the world's largest democracy?",
                "What is the capital of Canada?",
                "Which country has the world's largest population?",
                "What is the official language of Brazil?",
                "Which country is not part of the European Union?"
        };

        String[][] options = {
                { "China", "Russia", "Canada", "USA" },
                { "London", "Berlin", "Paris", "Madrid" },
                { "NATO", "UNESCO", "United Nations", "European Union" },
                { "China", "France", "Germany", "Russia" },
                { "Yen", "Won", "Yuan", "Baht" },
                { "USA", "India", "Indonesia", "Brazil" },
                { "Toronto", "Vancouver", "Ottawa", "Montreal" },
                { "India", "China", "USA", "Indonesia" },
                { "Spanish", "Portuguese", "French", "English" },
                { "Germany", "France", "Norway", "Italy" }
        };

        int[] correctAnswers = { 1, 2, 2, 2, 0, 1, 2, 1, 1, 2 };
        int[] prizes = { 1000, 2000, 3000, 5000, 10000, 20000, 40000, 80000, 160000, 320000 };

        boolean fiftyFiftyAvailable = true;
        boolean audiencePollAvailable = true;
        int totalPrize = 0;
        Scanner scanner = new Scanner(System.in);

        System.out.println("====================================");
        System.out.println("       KAUN BANEGA CROREPATI ");
        System.out.println("====================================");

        System.out.print("Enter your name: ");
        String playerName = scanner.nextLine();

        System.out.println("\nWelcome, " + playerName + "! Let's begin the game!");
        System.out.println("You have 2 lifelines: 50-50 and Audience Poll");
        System.out.println("You can use each lifeline only once during the game.\n");

        for (int i = 0; i < questions.length; i++) {
            System.out.println("\nQuestion " + (i + 1) + " for Rs." + prizes[i] + ":");
            System.out.println(questions[i]);

            for (int j = 0; j < 4; j++) {
                System.out.println((j + 1) + ". " + options[i][j]);
            }

            if ((fiftyFiftyAvailable || audiencePollAvailable) && i < questions.length - 1) {
                System.out.println("5. Use Lifeline");
            }

            System.out.print("\nEnter your choice (1-5): ");
            int choice = scanner.nextInt();

            if (choice == 5) {
                if ((fiftyFiftyAvailable || audiencePollAvailable) && i < questions.length - 1) {
                    
                    System.out.println("\nAvailable Lifelines:");
                    if (fiftyFiftyAvailable)
                        System.out.println("1. 50-50");
                    if (audiencePollAvailable)
                        System.out.println("2. Audience Poll");
                    System.out.print("Select lifeline (0 to go back): ");

                    int lifelineChoice = scanner.nextInt();

                    if (lifelineChoice == 1 && fiftyFiftyAvailable) {
                        
                        System.out.println("\n50-50 Lifeline:");
                        int correct = correctAnswers[i];
                        int wrong1, wrong2;

                        do {
                            wrong1 = (int) (Math.random() * 4);
                        } while (wrong1 == correct);

                        do {
                            wrong2 = (int) (Math.random() * 4);
                        } while (wrong2 == correct || wrong2 == wrong1);

                        for (int j = 0; j < 4; j++) {
                            if (j == wrong1 || j == wrong2) {
                                System.out.println((j + 1) + ". [HIDDEN]");
                            } else {
                                System.out.println((j + 1) + ". " + options[i][j]);
                            }
                        }

                        fiftyFiftyAvailable = false;
                        i--; 
                        continue;
                    } else if (lifelineChoice == 2 && audiencePollAvailable) {
                    	
                        System.out.println("\nAudience Poll Results:");
                        int correct = correctAnswers[i];
                        int correctPercent = 60 + (int) (Math.random() * 31); // 60-90%
                        int remaining = 100 - correctPercent;

                        int[] percents = new int[4];
                        percents[correct] = correctPercent;

                        for (int j = 0; j < 4; j++) {
                            if (j != correct) {
                                percents[j] = (int) (Math.random() * (remaining / 2));
                                remaining -= percents[j];
                            }
                        }

                        percents[0] += remaining;

                        for (int j = 0; j < 4; j++) {
                            System.out.println((j + 1) + ". " + options[i][j] + ": " + percents[j] + "%");
                        }

                        audiencePollAvailable = false;
                        i--; 
                        continue;
                    }
                } else {
                    if (i == questions.length - 1) {
                        System.out.println("\n\033[36mCannot use lifelines on the last question!\033[0m");
                    } else {
                        System.out.println("No lifelines available!");
                    }
                    i--; 
                    continue;
                }
            }

            if (choice - 1 == correctAnswers[i]) {
                totalPrize = prizes[i];
                System.out.println("\n\033[32mCorrect Answer!\033[0m");
                System.out.println("Congratulations! You've won Rs. " + totalPrize + " so far!");
            } else {
                System.out.println("\n\033[31mWrong Answer!\033[0m");
                System.out.println("Game Over! Your total prize money: Rs. " + totalPrize);
                break;
            }

            if (i == questions.length - 1) {
                System.out.println("\n\033[36mCONGRATULATIONS " + playerName.toUpperCase() + "!\033[0m");
                System.out.println("You've answered all questions correctly and won Rs. " + totalPrize + "!");
            }
        }

        scanner.close();
    }
}