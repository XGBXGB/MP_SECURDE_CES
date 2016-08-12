package model;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class LogWriter {
	public void write(String line) {
		try {

			File file = new File("C:\\Users\\Jake\\Desktop\\Projects\\Sample\\logsheet.txt");
			System.out.println("PASOK DITO SA WRITE!!!!!");
			// if file doesnt exists, then create it
			if (!file.exists()) {
				file.createNewFile();
			}
			System.out.println("PASOK DITO SA WRITE!!!!!2");

			FileWriter fw = new FileWriter(file.getAbsoluteFile(), true);
			BufferedWriter bw = new BufferedWriter(fw);
			bw.append(line + System.getProperty("line.separator"));
			bw.close();
			System.out.println("PASOK DITO SA WRITE!!!!!21231312");

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
