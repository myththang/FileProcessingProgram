/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */

/**
 *
 * @author myth
 */
/**
 * @param args the command line arguments
 */
import java.io.*;
import java.util.*;

public class FileProcessingProgram {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int option = 0;
        while (option != 6) {
            System.out.println("1. Check if path exists and if it is a file or directory");
            System.out.println("2. List all .java files in a directory");
            System.out.println("3. List all files with size larger than input size");
            System.out.println("4. Add content to a file");
            System.out.println("5. Count the number of words in a file");
            System.out.println("6. Exit program");
            System.out.println("Please select an option:");
            option = input.nextInt();
            input.nextLine(); // consume newline character
            switch (option) {
                case 1:
                    checkPath();
                    break;
                case 2:
                    listJavaFiles();
                    break;
                case 3:
                    listLargeFiles();
                    break;
                case 4:
                    addContentToFile();
                    break;
                case 5:
                    countWordsInTxtFile();
                    break;
                case 6:
                    System.out.println("Exiting program.");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    public static void checkPath() {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter a path:");
        String path = input.nextLine();
        File file = new File(path);
        if (!file.exists()) {
            System.out.println("Path doesn't exist.");
        } else {
            if (file.isFile()) {
                System.out.println("This is a file.");
            } else if (file.isDirectory()) {
                System.out.println("This is a directory.");
            }
        }
    }

    public static void listJavaFiles() {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter a directory path:");
        String path = input.nextLine();
        File folder = new File(path);
        if (!folder.exists()) {
            System.out.println("Path doesn't exist.");
        } else {
            int count = 0;
            File[] files = folder.listFiles();
            for (File file : files) {
                if (file.isFile() && file.getName().endsWith(".java")) {
                    System.out.println(file.getName());
                    count++;
                }
            }
            System.out.println(count + " .java files found.");
        }
    }

    public static void listLargeFiles() {
        Scanner input = new Scanner(System.in);
        int size = 0;
        File folder ;
        while (true) {
            System.out.println("Enter file size in KB:");
            try {
                size = Integer.parseInt(input.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Value of size is not numeric. Please try again.");

            }
        }
        while (true) {
            System.out.println("Enter a directory path:");
            String path = input.nextLine();
            folder = new File(path);
            if (!folder.exists()) {
                System.out.println("Path doesn't exist. Please try again.");

            } else {
                break;
            }
        }
        int count = 0;
        File[] files = folder.listFiles();
        for (File file : files) {
            if (file.isFile() && file.length() > size * 1024) {
                System.out.println(file.getName() + " (" + file.length() / 1024 + "KB)");
                count++;
            }
        }
        System.out.println(count + " files found.");
    }

    public static void addContentToFile() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the path of the file to add content to: ");
        String filePath = scanner.nextLine();
        File file = new File(filePath);
        if (!file.exists()) {
            System.out.println("File doesn't exist. Please try again.");
            return;
        }
        try {
            FileWriter writer = new FileWriter(file, true);
            System.out.print("Enter the content to add: ");
            String content = scanner.nextLine();
            writer.write(content);
            writer.close();
            System.out.println("Successfully added content to file.");
        } catch (IOException e) {
            System.out.println("An error occurred while adding content to file.");
            
        }
    }

    public static void countWordsInTxtFile() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the path of the .txt file: ");
        String filePath = scanner.nextLine();
        File file = new File(filePath);
        if (!file.exists()) {
            System.out.println("File doesn't exist. Please try again.");
            return;
        }
        try {
            FileReader reader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(reader);
            String line;
            int wordCount = 0;
            while ((line = bufferedReader.readLine()) != null) {
                String[] words = line.split("\\W+");
                wordCount += words.length;
            }
            bufferedReader.close();
            System.out.println("The file has " + wordCount + " words.");
        } catch (IOException e) {
            System.out.println("An error occurred while counting words in file.");
            e.printStackTrace();
        }
    }

}
