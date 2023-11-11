package javaapplication10;
public class BookData {
	public BookData() {
		{
			String noreaders= "No";
			for (int i = 0; i <borrowreaders.length; i++) {
				borrowreaders[i]=noreaders;
			}
			for (int i = 0; i <5; i++) {
				switch (i) {
				case 0:
					booknames[i]="Programming In Java";
					authors[i]="James Gosling";
					pubdates[i]="1995-05-31";
					sumpaginations[i]="1320";
					break;
				case 1:
					booknames[i]="Operating System";
					authors[i]="Khurana Rohit";
					pubdates[i]="1997-07-25";
					sumpaginations[i]="656";
					break;
				case 2:
					booknames[i]="Web Developement";
					authors[i]="John Duckett";
					pubdates[i]="2000-01-31";
					sumpaginations[i]="1120";
					break;
				case 3:
					booknames[i]="C Language";
					authors[i]="Bennis Ritchie";
					pubdates[i]="1977-04-04";
					sumpaginations[i]="1310";
					break;
				case 4:
					booknames[i]="Programming In C++ ";
					authors[i]="Bennis Ritchie";
					pubdates[i]="19872-08-20";
					sumpaginations[i]="954";
					break;
				}
			}										
		}
	}
	public String[] booknames = new String[10];
	public String[] authors = new String[10];
	public String[] pubdates = new String[10];
	public String[] sumpaginations = new String[10];
	public String[] borrowreaders = new String[10];
}
