package org.example;

import java.util.Scanner;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        //create an array of 20 books
        Book[] library = {
                new Book(100, "9780143127741", "The Lost Realm", false, ""),
                new Book(101, "9780062315007", "Echoes of Tomorrow", false, ""),
                new Book(102, "9780451524935", "Whispers in the Fog", false, ""),
                new Book(103, "9780385754729", "The Clockwork Garden", false, ""),
                new Book(104, "9780553573404", "Shadows of Arkenfall", false, ""),
                new Book(105, "9780345803481", "Frostbound", false, ""),
                new Book(106, "9780307277671", "The Last Ember", false, ""),
                new Book(107, "9780061120084", "Chronicles of the Hollow", false, ""),
                new Book(108, "9780141439600", "Midnight’s Mirage", false, ""),
                new Book(109, "9781451673319", "The Glass Tundra", false, ""),
                new Book(200, "9780316769488", "Beyond the Iron Sky", false, ""),
                new Book(201, "9780439023481", "The Library of Echoes", false, ""),
                new Book(202, "9780743273565", "Starfall Prophecy", false, ""),
                new Book(203, "9780316015844", "The Crimson Pact", false, ""),
                new Book(204, "9780618260300", "Winds of Isalor", false, ""),
                new Book(205, "9780060850524", "The Seventh Seal", false, ""),
                new Book(206, "9780385472579", "A Song for the Void", false, ""),
                new Book(207, "9780451526342", "The Dreamcatcher's Curse", false, ""),
                new Book(208, "9780142424179", "The Alchemist’s Code", false, ""),
                new Book(209, "9780547928227", "Fragments of the Moon", false, "")
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
                case 2:
                    displayBooks(library, true);
                    userCheckIn(library, scanner);
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
                if (showCheckedOut) {
                    System.out.println("Checked out by: " + book.getCheckedOutTo());
                }
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

        //STRETCH: create today's date as a date object
        LocalDate todayDate = LocalDate.now();

        switch (userChoice) {
            case "C":
                System.out.print("Enter your name: ");
                String userName = scanner.nextLine();
                System.out.print("Enter the ID of the book you want to check out: ");
                int bookId = scanner.nextInt();

                //search for book in library, update properties
                for (Book book : library) {
                    if (book != null && !book.isCheckedOut() && book.getId() == bookId) {
                        book.setCheckedOut(true);
                        book.setCheckedOutTo(userName);
                        //STRETCH: set today's date + two weeks for the due date
                        book.setDueDate(todayDate.plusDays(14));
                        System.out.println(book.getTitle() + " was successfully checked out.");
                        return;
                    }
                    else if (book!= null && book.isCheckedOut() && book.getId() == bookId) {
                        System.out.println("Book is already checked out.");
                        return;
                    }
                }
                System.out.println("Book not found.");

                break;
            case "X":
                break;
            default:
                System.out.println("Invalid choice, please select one of the above options.");
                break;
        }
    }

    public static void userCheckIn (Book[] library, Scanner scanner) {
        //option select
        System.out.println(
                "\n(C) - Check in a book\n" +
                "(X) - Exit to home screen\n"
        );
        System.out.print("Enter your choice: ");

        scanner.nextLine();
        String userChoice = scanner.nextLine().toUpperCase();
        LocalDate todayDate = LocalDate.now();

        switch (userChoice) {
            case "C":
                System.out.print("Enter your name: ");
                String userName = scanner.nextLine();
                System.out.print("Enter the ID of the book you want to check in: ");
                int bookId = scanner.nextInt();

                //search for book in library, update properties
                for (Book book : library) {
                    if (book != null && book.isCheckedOut() &&
                            book.getId() == bookId &&
                            book.getCheckedOutTo().equalsIgnoreCase(userName)) {
                        book.setCheckedOut(false);
                        book.setCheckedOutTo("");
                        System.out.println(book.getTitle() + " was successfully checked in.");

                        //STRETCH: display a message if book was returned on time or not
                        if (book.getDueDate() != null) {
                            if (todayDate.isAfter(book.getDueDate())) {
                                System.out.println("Book is late, pay $5 fine.");
                            } else {
                                System.out.println("Book was returned on time.");
                            }
                        }
                        else {
                            System.out.println("Due date does not exist.");
                        }

                        return;
                    }
                    else if (book != null && !book.isCheckedOut() &&
                            book.getId() == bookId) {
                        System.out.println("Book is already checked in.");
                        return;
                    }
                    else if (book != null && book.isCheckedOut() &&
                            book.getId() == bookId &&
                            !book.getCheckedOutTo().equalsIgnoreCase(userName)) {
                        System.out.println("Book is checked out by another user.");
                        return;
                    }
                }
                System.out.println("Book not found.");

                break;
            case "X":
                break;
            default:
                System.out.println("Invalid choice, please select one of the above options.");
                break;
        }
    }
}