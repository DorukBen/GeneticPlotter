import javax.activation.MimetypesFileTypeMap;
import java.io.File;
import java.util.Objects;
import java.util.Scanner;

public class Controller {
    public static void main(String[] args) {
        shellLoop();
    }

    private static void shellLoop() {
        boolean status = true;
        while (status) {
            if (shellRun()) {
                System.out.println("Again?[Y/n]");
                Scanner scanner = new Scanner(System.in);
                String input = scanner.nextLine();
                clearShell();
                status = Objects.equals(input, "y") || Objects.equals(input, "Y");
            }
        }
    }

    private static boolean shellRun() {
        System.out.println("Please enter image path:");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        File file = new File(input);
        if(file.exists() && !file.isDirectory()) {
            String mimeType= new MimetypesFileTypeMap().getContentType(file);
            String type = mimeType.split("/")[0];
            if(type.equals("image")) {
                System.out.println("Please enter similarity threshold:");
                scanner = new Scanner(System.in);
                float threshold = scanner.nextFloat();
                GeneticPlotter geneticPlotter = new GeneticPlotter(file);
                geneticPlotter.start(threshold);
                return true;
            } else {
                System.out.println("Given file is not an image, please try again.");
                return false;
            }
        } else {
            System.out.println("File not exist, please try again.");
            return false;
        }
    }


    private static void clearShell() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
