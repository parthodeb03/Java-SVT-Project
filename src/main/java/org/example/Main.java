package org.example;

import java.util.Scanner;

public class Main {

    static Scanner sc = new Scanner(System.in);
    static StudentAccount account = null;
    static boolean accountExists = false;

    public static void main(String[] args) {

        System.out.println("╔══════════════════════════════════════╗");
        System.out.println("║     Student Account & Payment System ║");
        System.out.println("╚══════════════════════════════════════╝");

        boolean running = true;

        while (running) {
            System.out.println("\n========== MAIN MENU ==========");
            System.out.println("1. Create Account");
            System.out.println("2. Login");
            System.out.println("3. Make Payment");
            System.out.println("4. View Account Info");
            System.out.println("5. Logout");
            System.out.println("6. Exit");
            System.out.println("================================");
            System.out.print("Choose an option: ");

            int choice = readInt();

            switch (choice) {
                case 1:
                    createAccount();
                    break;
                case 2:
                    login();
                    break;
                case 3:
                    makePayment();
                    break;
                case 4:
                    viewAccountInfo();
                    break;
                case 5:
                    logout();
                    break;
                case 6:
                    System.out.println("\nGoodbye! Exiting system...");
                    running = false;
                    break;
                default:
                    System.out.println("[ERROR] Invalid option! Please choose 1-6.");
            }
        }

        sc.close();
    }

    static void createAccount() {

        if (accountExists) {
            System.out.println("[WARNING] An account already exists. Please logout first.");
            return;
        }

        System.out.println("\n--- Create New Account ---");

        System.out.print("Enter Full Name     : ");
        String name = sc.nextLine().trim();

        System.out.print("Enter Username      : ");
        String username = sc.nextLine().trim();

        System.out.print("Enter Student ID    : ");
        String id = sc.nextLine().trim();

        System.out.print("Enter Department    : ");
        String dept = sc.nextLine().trim();

        System.out.print("Enter Batch         : ");
        int batch = readInt();

        System.out.print("Enter Section       : ");
        String section = sc.nextLine().trim();

        System.out.print("Enter Password      : ");
        String password = sc.nextLine().trim();

        System.out.print("Confirm Password    : ");
        String confirmPass = sc.nextLine().trim();

        try {
            account = new StudentAccount();
            account.accountCreation(name, username, id, dept, batch, section, password, confirmPass);
            accountExists = true;
            System.out.println("\n[SUCCESS] Account created successfully for: " + name);
        } catch (IllegalAccessException e) {
            System.out.println("[ERROR] " + e.getMessage());
            account = null;
        } catch (Exception e) {
            System.out.println("[ERROR] Unexpected error: " + e.getMessage());
            account = null;
        }
    }


    static void login() {

        if (!accountExists) {
            System.out.println("[WARNING] No account found. Please create an account first.");
            return;
        }

        if (account.isLoggedIn()) {
            System.out.println("[INFO] You are already logged in as: " + account.getName());
            return;
        }

        System.out.println("\n--- Login ---");

        System.out.print("Enter Username : ");
        String username = sc.nextLine().trim();

        System.out.print("Enter Password : ");
        String password = sc.nextLine().trim();

        boolean result = account.logIn(username, password);

        if (result) {
            System.out.println("[SUCCESS] Welcome, " + account.getName() + "!");
        } else {
            System.out.println("[ERROR] Invalid username or password. Please try again.");
        }
    }


    static void makePayment() {

        if (!accountExists) {
            System.out.println("[WARNING] No account found. Please create an account first.");
            return;
        }

        if (!account.isLoggedIn()) {
            System.out.println("[WARNING] Please login first to make a payment.");
            return;
        }

        System.out.println("\n--- Make Payment ---");
        System.out.println("Student ID : " + account.getId());
        System.out.println("\nPayment Types:");
        System.out.println("  1. Tuition Fee");
        System.out.println("  2. Library Fee");
        System.out.println("  3. Lab Fee");
        System.out.println("  4. Other");
        System.out.print("Choose payment type (1-4): ");

        int typeChoice = readInt();
        String paymentType;

        switch (typeChoice) {
            case 1: paymentType = "Tuition Fee";  break;
            case 2: paymentType = "Library Fee";  break;
            case 3: paymentType = "Lab Fee";       break;
            case 4:
                System.out.print("Enter custom payment type: ");
                paymentType = sc.nextLine().trim();
                break;
            default:
                System.out.println("[ERROR] Invalid payment type selected.");
                return;
        }

        System.out.print("Enter Amount (BDT): ");
        double amount = readDouble();

        try {
            Payment payment = new Payment();
            payment.makePayment(account.getId(), paymentType, amount);

            System.out.println("\n╔══════════════════════════════╗");
            System.out.println("║       PAYMENT RECEIPT        ║");
            System.out.println("╠══════════════════════════════╣");
            System.out.printf("║ Student ID   : %-14s║%n", payment.getStudentId());
            System.out.printf("║ Student Name : %-14s║%n", account.getName());
            System.out.printf("║ Payment Type : %-14s║%n", payment.getPaymentType());
            System.out.printf("║ Amount       : BDT %-10.2f║%n", payment.getAmount());
            System.out.printf("║ Status       : %-14s║%n", payment.isPaid() ? "PAID ✓" : "UNPAID");
            System.out.println("╚══════════════════════════════╝");

        } catch (IllegalArgumentException e) {
            System.out.println("[ERROR] " + e.getMessage());
        } catch (Exception e) {
            System.out.println("[ERROR] Unexpected error: " + e.getMessage());
        }
    }


    static void viewAccountInfo() {

        if (!accountExists) {
            System.out.println("[WARNING] No account found. Please create an account first.");
            return;
        }

        if (!account.isLoggedIn()) {
            System.out.println("[WARNING] Please login first to view account info.");
            return;
        }

        System.out.println("\n╔══════════════════════════════╗");
        System.out.println("║       ACCOUNT INFORMATION    ║");
        System.out.println("╠══════════════════════════════╣");
        System.out.printf("║ Name         : %-14s║%n", account.getName());
        System.out.printf("║ Student ID   : %-14s║%n", account.getId());
        System.out.printf("║ Department   : %-14s║%n", account.getDept());
        System.out.printf("║ Batch        : %-14s║%n", account.getBatch());
        System.out.printf("║ Section      : %-14s║%n", account.getSection());
        System.out.printf("║ Status       : %-14s║%n", account.isLoggedIn() ? "Logged In" : "Logged Out");
        System.out.println("╚══════════════════════════════╝");
    }

    // ─────────────────────────────────────────
    //  5. LOGOUT
    // ─────────────────────────────────────────
    static void logout() {

        if (!accountExists) {
            System.out.println("[WARNING] No account found.");
            return;
        }

        if (!account.isLoggedIn()) {
            System.out.println("[INFO] You are not currently logged in.");
            return;
        }

        account.logout();
        System.out.println("[SUCCESS] You have been logged out successfully.");
    }

    static int readInt() {
        while (true) {
            try {
                int val = Integer.parseInt(sc.nextLine().trim());
                return val;
            } catch (NumberFormatException e) {
                System.out.print("[ERROR] Please enter a valid number: ");
            }
        }
    }


    static double readDouble() {
        while (true) {
            try {
                double val = Double.parseDouble(sc.nextLine().trim());
                return val;
            } catch (NumberFormatException e) {
                System.out.print("[ERROR] Please enter a valid amount: ");
            }
        }
    }
}