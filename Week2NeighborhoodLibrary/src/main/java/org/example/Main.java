package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        //create an array of 20 books
        Book[] library = {
                new Book(1023, "9780143127741", "The Lost Realm", false, ""),
                new Book(2045, "9780062315007", "Echoes of Tomorrow", false, ""),
                new Book(3099, "9780451524935", "Whispers in the Fog", false, ""),
                new Book(4120, "9780385754729", "The Clockwork Garden", false, ""),
                new Book(5176, "9780553573404", "Shadows of Arkenfall", false, ""),
                new Book(6243, "9780345803481", "Frostbound", false, ""),
                new Book(7388, "9780307277671", "The Last Ember", false, ""),
                new Book(8421, "9780061120084", "Chronicles of the Hollow", false, ""),
                new Book(9562, "9780141439600", "Midnight’s Mirage", false, ""),
                new Book(1034, "9781451673319", "The Glass Tundra", false, ""),
                new Book(1167, "9780316769488", "Beyond the Iron Sky", false, ""),
                new Book(2299, "9780439023481", "The Library of Echoes", false, ""),
                new Book(3340, "9780743273565", "Starfall Prophecy", false, ""),
                new Book(4481, "9780316015844", "The Crimson Pact", false, ""),
                new Book(5523, "9780618260300", "Winds of Isalor", false, ""),
                new Book(6639, "9780060850524", "The Seventh Seal", false, ""),
                new Book(7752, "9780385472579", "A Song for the Void", false, ""),
                new Book(8864, "9780451526342", "The Dreamcatcher's Curse", false, ""),
                new Book(9910, "9780142424179", "The Alchemist’s Code", false, ""),
                new Book(1098, "9780547928227", "Fragments of the Moon", false, "")
        };


        Scanner scanner = new Scanner(System.in);

        //create home screen with infinite while loop
        while (true) {
            System.out.println("\nHome Screen\n");
            System.out.println(
                    "(1) - Show available books\n" +
                    "(2) - Show checked out books\n" +
                    "(3) - Exit application\n"
            );
            System.out.print("Enter your choice: ");

            int userChoice = scanner.nextInt();

            switch (userChoice) {
                case 1:
                    displayBooks(library, false);
                    userCheckOut(library, scanner);
                    break;
                case 3:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice, please select one of the above options.");
                    break;
            }
        }
    }

    public static void displayBooks(Book[] library, boolean showCheckedOut) {
        //loop through and display book information
        for (Book book : library) {
            if (book != null && showCheckedOut == book.isCheckedOut()) {
                System.out.println(book.toString());
            }
        }
    }

    public static void userCheckOut (Book[] library, Scanner scanner) {
        //option select
        System.out.println(
                "\n(C) - Check out a book\n" +
                "(X) - Exit to home screen\n"
        );
        System.out.print("Enter your choice: ");

        scanner.nextLine();
        String userChoice = scanner.nextLine().toUpperCase();

        switch (userChoice) {
            case "C":
                System.out.print("Enter your name: ");
                String userName = scanner.nextLine();
                System.out.print("Enter the title of the book you want to check out: ");
                String bookTitle = scanner.nextLine();

                //search for book in library, update properties
                for (Book book : library) {
                    if (book != null && !book.isCheckedOut() && book.getTitle().equalsIgnoreCase(bookTitle)) {
                        book.setCheckedOut(true);
                        book.setCheckedOutTo(userName);
                        System.out.println(book.getTitle() + " was successfully checked out");
                        return;
                    }
                }
                System.out.println("Book not found or already checked out...returning to home screen");

                break;
            case "X":
                break;
            default:
                System.out.println("Invalid choice, please select one of the above options.");
                break;
        }
    }
}