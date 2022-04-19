import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean finished = false;
        while(true){
            if(finished){
                break;
            }
            System.out.println("Created by Truong Vu");
            System.out.println("Play in full screen to see easier");
            System.out.println("This is the order of turns: You, Computer One, Computer Two, Computer Three");
            System.out.println("Press 1 to start the four player game: ");
            boolean hasInt = scanner.hasNextInt();
            if(hasInt){
                int choice = scanner.nextInt();
                scanner.nextLine();
                switch(choice){
                    case 1:
                        ThreePlayerGame.threePlayerGame();
                        finished = true;
                        break;
                    default:
                        System.out.println("Invalid input. Please try again.");
                        break;

                }
            } else {
                scanner.nextLine();
                System.out.println("Invalid input. Please try again");
            }


        }
    }
}
