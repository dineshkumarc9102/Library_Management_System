package javaapplication10;
import java.util.Scanner;
public class JavaApplication10 {
	Bo bo = new Bo();
	public static void main(String[] args) {
		JavaApplication10 ui = new JavaApplication10 ();
		ui.welcome();
	}
	public void welcome() {
		System.out.println("*********************************");
		System.out.println("******* LIBRARY MANAGEMENT SYSTEM ********");
		System.out.println("*********************************");
		loginBoundary();//Login interface
	}
	public void loginBoundary() {
		System.out.print("Username:");
		Scanner scanner = new Scanner(System.in);
		String username = scanner.nextLine();
		System.out.print("Password:");
		Scanner scanner1 = new Scanner(System.in);
		String password = scanner1.nextLine();
		int flag = bo.login(username, password);//login verification
		switch (flag) {
		case 0:
			System.out.println("Incorrect Password, Please Re-enetr Password");
			loginBoundary();
			break;
		case 1:
			System.out.println("Login successfully!");
			showMenu();
			break;
		case -1:
			System.out.println("The username you entered does not exist, please confirm and enter a valid username again.");
			loginBoundary();
			break;
		}
	}
	public void showMenu() {
		System.out.println("\n\nEnter Your Choice:");
		System.out.println("Book Details:--1");
		System.out.println("Add New Book:--2");
		System.out.println("Modify Book Details:--3");
		System.out.println("Delete Book Details:--4");
		System.out.println("Return Book:--5");
		System.out.println("Borrow Book:--6");
		System.out.println("Exit:--7");
		Scanner scanner = new Scanner(System.in);
		int a = scanner.nextInt();
		switch (a) {
		case 1:
			showBook();
			returnMethod();
			break;
		case 2:
			addBook();
			break;
		case 3:
			modBookInfo();
			break;
		case 4:
			delete();
			break;
		case 5:
			returnBook();
			break;
		case 6:
			borrowBook();
			break;
		case 7:
			welcome();
			scanner.close();
			break;
		}
	}
	public void returnMethod() {
		System.out.print("Press ENTER to return to MAIN MENU :");
		Scanner scanner = new Scanner(System.in);
		String i = scanner.nextLine();
		showMenu();
	}
	public void showBook() {
		bo.showBook();
	}
	public void addBook() {
		System.out.print("Please enter the name of the Book to be added:");
		Scanner scanner = new Scanner(System.in);
		String bookname = scanner.nextLine();
		System.out.print ( "Please enter Author name:");
		Scanner scanner1 = new Scanner(System.in);
		String author = scanner1.nextLine();
		System.out.print("Please enter the publication date of (format such as 2000-01-01):");
		Scanner scanner2 = new Scanner(System.in);
		String pubdate = scanner2.nextLine();
		System.out.print("Please enter total number of pages (pages):");
		Scanner scanner3 = new Scanner(System.in);
		String sumpagination = scanner3.nextLine();
		int flag = bo.addBook(bookname, author, pubdate, sumpagination);
		
		switch (flag) {
		case 1:
			System.out.println("New book added successfully, the library currently has " + bo.remainSpace() + " locations for storing new books.");
			returnMethod();
			break;
		case 0:
			System.out.println("Failed to add book, JavaPRoject"
                                + "already exist in this library!");
			returnMethod();
			break;
		case -1:
			System.out.println("Adding failed, the library is full, please delete some books before adding them.");
			showMenu();
			break;
		}
	}
	public void modBookInfo() {
		System.out.print("Please enter the Title of the book you want to operate:");
		Scanner scanner = new Scanner(System.in);
		String bookname = scanner.nextLine();
		int flag = bo.selectBook(bookname);
		switch (flag) {
		case 0:
			System.out.print("Related books not found, enter 0 and press Enter to enter the SubMenu, and enter 1 and press Enter to return to the Main menu.");
			int a = scanner.nextInt();
			if (a == 0) {
				modBookInfo();
			} else if (a == 1) {

				showMenu();
			}
			break;
		case -1:
			System.out.println("The Title of the book is empty, enter 0 and press Enter to enter the SubMenu, and enter 1 and press Enter to return to Main Menu.");
			int a1 = scanner.nextInt();
			if (a1 == 0) {
				modBookInfo();
			} else {
				showMenu();
			}
			break;
		case 1:
			System.out.print("Related Search Results,  \nwhere (the number "+1+"represents the title of the book, \nThe number "+2+" represents the author, \nThe number "+3+" represents the publication date, \nand the number "+4+" represents the total number of pages ,\nThe number "+5+" represents the borrower) Enter the relevant number to modify the relevant information: ");
			int a2 = scanner.nextInt();
			if (a2> 0 && a2 <6) {
				System.out.print("Please enter what you want to Modify" + sortName(a2) + ":");
				Scanner scanner2 = new Scanner(System.in);
				String value = scanner2.nextLine();
				int index = bo.selectIndex(bookname);
				bo.modinfo(a2, value, index);
				System.out.println(bookname + "Details has been modified successfully!");
				returnMethod();
			} else {
				System.out.println("The Number you entered is invalid. Please try again!");
				modBookInfo();
			}
			break;
		}
	}
	public String sortName(int flag) {
		if (flag == 1)
			return "Book Title";
		if (flag == 2)
			return "Author";
		if (flag == 3)
			return "Publication Date";
		if (flag == 4)
			return "Total Pages";
		else
			return "Borrower";
	}
	public void delete() {
		System.out.print("Please enter the Title of the book to be deleted:");
		Scanner scanner = new Scanner(System.in);
		String bookname = scanner.nextLine();
		int flag = bo.testReader(bookname);
		if (flag == 0) {
			bo.deleteBook(bookname);
			System.out.println(bookname + "Successfully removed Book Details from the library! Enter 0 to return to the Main Menu, enter 1 to Continue Deleting");
			delReturn();
		} else if (flag == 1) {
			System.out.println("This book has been lent, Please wait for it to be returned before deleting. Enter 0 to return to the Main menu, enter 1 to Continue to Delete");
			delReturn();
		} else {
			System.out.println("This book does not exist in this library,Enter 0 to return to the Main menu, enter 1 to Continue to Delete");
			delReturn();
		}
	}
	public void delReturn() {
		Scanner scanner1 = new Scanner(System.in);
		int i = scanner1.nextInt();
		if (i == 0) {
			showMenu();
		} else if (i == 1) {
			delete();
		}
	}
	public void returnBook() {
		System.out.print("Please Enter the Name of the book to be returned:");
		Scanner scanner = new Scanner(System.in);
		String bookname = scanner.nextLine();
		int flag = bo.testReader(bookname);
		if (flag == 0) {
			System.out.println(bookname + "Not yet loaned out.");
			System.out.print("Enter 0 and press Enter to return to the main menu, enter other keys to continue returning the book");
			String a = scanner.nextLine();
			if(a.equals("0")){
				showMenu();
			}else {
				returnBook();
			}
		} else if (flag == -1) {
			System.out.println(bookname + "Does not exist in this library.");
			System.out.print("Enter 0 and press Enter to return to the Main menu, Enter other keys to Continue Returning the book");
			String a = scanner.nextLine();
			if(a.equals("0")){
				showMenu();
			}else {
				returnBook();
			}
		} else if (flag == 1) {

			System.out.println(bo.getBorrowReader(bookname) + "The Book has been returned successfully!");
			bo.setBorrowReader(bookname);
			System.out.print("Enter 0 and press Enter to return to the Main menu, enter other keys to Continue Returning the book");
			String a = scanner.nextLine();
			if(a.equals("0")){
				showMenu();
			}else {
				returnBook();
			}
		}
	}
	public void borrowBook() {
		System.out.print("Please Enter the Name of the book to be borrowed:");
		Scanner scanner = new Scanner(System.in);
		String bookname = scanner.nextLine();
		int flag = bo.testReader(bookname);
		switch (flag) {
		case 0:
			System.out.println("Please enter the name of the borrower:");
			String readername = scanner.nextLine();
			bo.borrow(bookname,readername);
			System.out.println(readername+" borrowed a book named "+bookname+" from this library.");
			System.out.print("Enter 0 and press Enter to return to the main menu, and enter 1 and press Enter to continue borrowing the book:");
			int a0 = scanner.nextInt();
				if(a0==0)
					showMenu();
				if(a0==1)
					borrowBook();
			break;

		case 1:
			System.out.println("Book borrowing failed," + bookname + "Already borrowed!");
			System.out.print("Enter 0 and press Enter to return to the main menu, and enter 1 and press Enter to continue borrowing the book:");
			int a = scanner.nextInt();
			switch (a) {
			case 0:
				showMenu();
				break;
			case 1:
				borrowBook();
				break;
				}
		case -1:
			System.out.println("Book borrowing failed," + bookname + "does not exist in the library.");
			System.out.print("Enter 0 and press Enter to return to the main menu, and enter 1 and press Enter to continue borrowing the book:");
			int a1 = scanner.nextInt();
			switch (a1) {
			case 0:
				showMenu();
				break;
			case 1:
				borrowBook();
				break;
			}
		}
	}
}

    
    

