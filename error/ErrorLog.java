package error;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

public class ErrorLog {
	public static void flush() {
		try {
			File file = new File("log.txt");
			OutputStream os = new FileOutputStream(file);
			OutputStreamWriter osw = new OutputStreamWriter(os);
			
			osw.flush();
			
			osw.close();
			os.close();
		}
		catch (Exception ex) {
			System.err.println(ex.getMessage());
		}
	}
	
	public static void log(String msg) {
		String data = "-- " + msg;
		
		try {
			File file = new File("log.txt");
			OutputStream os = new FileOutputStream(file, true);
			OutputStreamWriter osw = new OutputStreamWriter(os);
			BufferedWriter w = new BufferedWriter(osw);
			
			w.append(data);
			w.newLine();
			
			w.close();
			osw.close();
			os.close();
		}
		catch (Exception ex) {
			System.err.println(ex.getMessage());
		}
	}
}
