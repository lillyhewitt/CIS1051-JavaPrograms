import java.io.*;
import java.util.Locale;
import java.util.Scanner;

public class ReadingFromFileExample {
	
	public static void main(String [] args) {
		IndexTree index = new IndexTree();
		String fileName = "pg100.txt";
		int x = 1;

		try {
			Scanner scanner = new Scanner(new File(fileName));
			while(scanner.hasNextLine()){
				String line = scanner.nextLine();
				System.out.println(line);
				String[] words = line.split("\\s+");
				for(String word : words){
					word = word.toLowerCase();
					word = word.replaceAll("\\W+", "");
					word = word.replaceAll(":", "");
					word = word.replaceAll(",", "");
					System.out.println(word);
					index.add(word, x);
				}
				x++;
			}
			// prints out the index
			//index.printIndex();
			scanner.close();
			
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}
}


