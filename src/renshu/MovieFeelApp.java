package renshu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class MovieFeelApp {

	public static void main(String[] args) {
		ArrayList<Movie> movies = new ArrayList<Movie>();
		
		System.out.println("動画の感想");
		
		while(true) {
			System.out.print("1.記録 2.表示 3.内容変更 4.削除 5.終了 >>");
			int select = new Scanner(System.in).nextInt();
			
			switch(select) {
			case 1: //感想の記録
				Movie movie = record();
				movies.add(movie);
				break;
			case 2: //感想のリストを表示
				display(movies);
				break;
			case 3: //感想の内容変更
				alteration(movies);
				break;
			case 4: //感想の削除
				delete(movies);
				break;
			case 5: //アプリ終了
				tally(movies);
				System.out.println("アプリケーションを終了します");
				return;
			default :
				System.out.println("操作は1～5を選んでください");
				break;
			}
		}
		
	}
	
	//内容を記録する
	public static Movie record() {
		System.out.println("\n→記録を開始します\n");
		//タイトル入力
		String title = inputTitle();

		//時間入力
		String time = inputTime();
		
		//感想入力
		int select = feeling();
		System.out.println("");
		
		//インスタンス生成
		Movie movie = new Movie(title,time,select);
		return movie;
	}
	
	//入力事項
	public static String inputTitle() {
		//タイトル入力
		System.out.print("動画のタイトルを入力してください >>");
		String title = new Scanner(System.in).next();
		
		return title;
	}
	public static String inputTime() {
		//時間入力
		System.out.print("記録する時間を入力してください >>");
		String time = new Scanner(System.in).next();
		
		return time;
	}
	//感想入力
	public static int feeling() {
		int select;
		do {
			System.out.print("感想を選択してください >>");
			System.out.print("1.かわいい 2.かっこいい 3.おもしろい 4.びっくり 5.感動 >>");
			select = new Scanner(System.in).nextInt();
			if(0 > select || select > Movie.FEELINGS.length + 1) {
				System.out.println("数字は1～5を選んでください");
			}
		}while(0 > select || select > Movie.FEELINGS.length + 1);
		return --select;
	}
	
	//内容を表示する
	public static void display(ArrayList<Movie> movies) {
		//感想が記録されていない場合
		if(movies.isEmpty()) {
			System.out.println("\n感想が記録されていません");
		}
		
		//記録された感想のリストを表示
		System.out.println("\n+++感想リスト+++");
		for(int i = 1;i - 1 < movies.size();i++) {
			System.out.print(i + ":");
			movies.get(i - 1).display();
		}
		System.out.println("");
	}
	
	//感想の選択
	public static int select(ArrayList<Movie> movies) {
		int select;
		do {
			select = new Scanner(System.in).nextInt();
			if(0 > select || select > movies.size() + 1) {
				System.out.println("不正な入力です");
			}
		}while(0 > select || select > movies.size() + 1);
		return --select;
	}
	
	
	//内容を変更する
	public static void alteration(ArrayList<Movie> movies) {
		//変更する感想の表示
		display(movies);
		
		//タイトルの変更
		System.out.print("どの感想を変更しますか >>");
		int select = select(movies);
		System.out.println();
		//変更する感想
		Movie m = movies.get(select);
		//変更する項目を選択
		altHeading(m);
		
		System.out.println("\n変更を終了します");
		System.out.println("");	
	}
	
	//内容変更の選択肢
	public static void altHeading(Movie m) {
		System.out.print("タイトルを変更しますか 1.はい 2.いいえ>>");
		int select = new Scanner(System.in).nextInt();
		if(select == 1) {
			String title = inputTitle();
			m.setTitle(title);
			System.out.println("タイトルを変更しました");
		}
		//時間の変更
		System.out.print("\n時間を変更しますか 1.はい 2.いいえ>>");
		select = new Scanner(System.in).nextInt();
		if(select == 1) {
			String time = inputTime();
			m.setTime(time);
			System.out.println("時間を変更しました");
		}
		//感想の変更
		System.out.print("\n感想を変更しますか 1.はい 2.いいえ>>");
		select = new Scanner(System.in).nextInt();
		if(select == 1) {
			int feeling = feeling();
			m.setReport(feeling);
			System.out.println("感想を変更しました");
		}
	}
	
	
	
	//内容を削除する
	public static void delete(ArrayList<Movie> movies) {
		//感想リストの表示
		display(movies);
		//削除する感想の選択
		System.out.print("どの感想を削除しますか >>");
		int delete = select(movies);
		//削除した感想を取り出す
		Movie m = movies.remove(delete);
		//削除した感想を表示
		System.out.print("\n感想 ");
		m.display();
		System.out.println("を削除しました");
		System.out.println("");
	}
	
	//感想ごとに集計する
	public static void tally(ArrayList<Movie> movies) {
		int[] count = new int[Movie.FEELINGS.length];
		for(int i = 0;i < Movie.FEELINGS.length;i++) {
			count[i] = 0;
		}
		for(Movie movie : movies) {
			int index = Arrays.asList(Movie.FEELINGS).indexOf(movie.getReport());
			count[index]++;
		}
		System.out.println("\n***感想の集計結果***");
		for(int i = 0;i < Movie.FEELINGS.length;i++) {
			System.out.println(Movie.FEELINGS[i] + ":" + count[i]);
		}
	}
	
}
