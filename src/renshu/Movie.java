package renshu;

public class Movie {
	final static String[] FEELINGS = {"かわいい","かっこいい","おもしろい","びっくり","感動"};
	String title;
	String time;
	String report;
	
	public Movie(String title,String time,int report) {
		this.title = title;
		this.time = time;
		this.report = FEELINGS[report];
	}
	
	public void display() {
		System.out.printf("%s [%S] *** %s \n",this.title,this.time,this.report);
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public void setTime(String time) {
		this.time = time;
	}
	
	public void setReport(int report) {
		this.report = FEELINGS[report];
	}
	
	public String getReport() {
		return this.report;
	}
}
