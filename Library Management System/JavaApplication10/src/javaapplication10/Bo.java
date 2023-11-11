package javaapplication10;
import java.util.Scanner;
public class Bo {
	public static BookData book = new BookData();
	UserData user = new UserData();
	public int login(String username, String password) {
		if (username.equals(user.getUser())) {
			if (password.equals(user.getPassword())) {
				return 1;
			} else {
				return 0;
			}
		} else {
			return -1;
		}
	}
	public void showBook() {
		System.out.println("This library has totally " + (book.booknames.length-remainSpace()) + " book");
		int a = bookinfo();
		if (a == 0) {
			showAll();
		} else if (a> 0 && a <= book.booknames.length) {
			showPart(a);
		} else {
			System.out.println("The Number entered is Invalid!");
			showBook();
		}
	}
	public void showAll() {
		for (int i = 0; i <book.booknames.length; i++) {
			if (book.booknames[i] != null) {
				System.out.println("---------------------------------------Number" + ( i + 1)
						+ "------------------------------------------------ --------------");
				System.out.println("The Book number is" + (i + 1));
                                
                                System.out.println("The detailed information is:");
				System.out.print("Book name:" + book.booknames[i] + "");
				System.out.print ("Author:" + book.authors [i] + "");
				System.out.print("Publication Date:" + book.pubdates[i] + "");
				System.out.print("Total pages:" + book.sumpaginations[i] + "");
				System.out.println("Borrower:" + book.borrowreaders[i]);
			}
		}
		System.out.println("------------------------------------------------- -------------------------------------------------- ------");
	}
	public void showPart(int a) {
		int i = a;
		System.out.println("---------------Boundary Line-------------------------------- -------------------------------------------------- -----------------");
		System.out.println("Book Number" + (i));
                System.out.println("The detailed information is:");
		System.out.println("Book name:" + book.booknames[i-1] + "");
		System.out.println ("Author Name:" + book.authors [i - 1] + "");
		System.out.println("Publication Date:" + book.pubdates[i-1] + "");
		System.out.println("Total pages:" + book.sumpaginations[i-1] + "");
		System.out.println("Borrower:" + book.borrowreaders[i-1]);
		System.out.println("------------------------------------------------- --------------Boundary Line ---------------------------------- -----------------");
	}
	public int bookinfo() {
		for (int i = 0; i <book.booknames.length; i++) {
			if (book.booknames[i] != null) {
				System.out.println("Book name number" + (i + 1) + ":" + book.booknames[i] + "");
			}
		}
		System.out.print("Enter the corresponding number to view the detailed information of the corresponding book (input 0 to view all information):");
		Scanner scanner = new Scanner(System.in);
		int i = scanner.nextInt();
		return i;
	}
	public int addBook(String bookname, String author, String pubdate, String sumpagination) {
		int flag = 0;
		if (book.booknames[book.booknames.length-1] != null) {
			flag = -1;
		}
		if (book.booknames[book.booknames.length-1] == null) {
			for (int i = 0; i <book.booknames.length; i++) {
				if (bookname.equals(book.booknames[i])) {
					flag = 0;
					break;
				}
				if (!(bookname.equals(book.booknames[i]))) {
					add(bookname, author, pubdate, sumpagination);
					flag = 1;
					break;
				}
			}
		}
		return flag;
	}
	public void add(String bookname, String author, String pubdate, String sumpagination) {
		for (int i = 0; i <book.booknames.length; i++) {
			if (book.booknames[i] == null) {
				book.booknames[i] = bookname;
				book.authors[i] = author;
				book.pubdates[i] = pubdate;
				book.sumpaginations[i] = sumpagination;
				break;
			}
		}
	}
	public int remainSpace() {
		int count = 0;
		try {
			for (int i = 0; i <book.booknames.length; i++) {
				if (book.booknames[i] == null) {
					++count;
				}
			}
		} catch (Exception e) {
		}
		return count;
	}
	public int selectBook(String bookname) {
		int flag = 0;
		for (int i = 0; i <book.booknames.length; i++) {
			if (bookname.equals("")) {
				flag = -1;
				break;
			}
			if (bookname.equals(book.booknames[i])) {
				flag = 1;
				showPart(i + 1);
				break;
			}
			if (!bookname.equals(book.booknames[i])) {
				if (i == book.booknames.length-1) {
					flag = 0;
				}
			}
		}
		return flag;
	}
	public int selectIndex(String bookname) {
		int flag = -1;
		for (int i = 0; i <book.booknames.length; i++) {
			if (bookname.equals(book.booknames[i])) {
				flag = i;
			}
		}
		return flag;
	}
	public void modinfo(int a, String value, int index) {

		switch (a) {
		case 1:
			book.booknames[index] = value;
			break;

		case 2:
			book.authors[index] = value;
			break;
		case 3:
			book.pubdates[index] = value;
			break;
		case 4:
			book.sumpaginations[index] = value;
			break;
		case 5:
			book.borrowreaders[index] = value;
			break;
		}
	}
	public void deleteBook(String bookname) {
		int a = selectIndex(bookname);
		book.booknames[a] = null;
		book.authors[a] = null;
		book.pubdates[a] = null;
		book.sumpaginations[a] = null;
		book.booknames[a] = null;
	}
	public int testReader(String bookname) {
		int a = selectIndex(bookname);
		if (a >= 0) {
			if ((book.borrowreaders[a].equals("无") || book.borrowreaders[a].equals("")))
				return 0;
			else
				return 1;
		} else {
			return -1;
		}
	}
	public void setBorrowReader(String bookname){
		int a = selectIndex(bookname);
		book.borrowreaders[a]="None";
	}
	public String getBorrowReader(String bookname){
		int a = selectIndex(bookname);
		return book.borrowreaders[a];
	}
	public void borrow(String bookname,String readername){
		int a = selectBook(bookname);
		book.borrowreaders[a]=readername;
	}
}

