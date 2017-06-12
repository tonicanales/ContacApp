package util;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;


public class FileHelper {
	final static Charset ENCODING = StandardCharsets.UTF_8;
	
	
	/**
	 * Read the lines of a text file file, coded in UTF_8 
	 * 
	 * @param  fileLines name of text file
	 * @return the array with the lines of file, if there are problems reading the file it is null
	 */
	public static String[] readFile(String aFileName){
		String[] fileLines=null; 
		
			List<String> list;
		try {
				list = readSmallTextFile(aFileName);
				int size = list.size(); 
				fileLines = new String[size]; 
				for(int i=0; i<size; i++)
					fileLines[i] = list.get(i); 	
			
			} catch (IOException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				fileLines=null;
			}
		
		return fileLines;   
	}
	

	/**
	 * Write a String array to a text file
	 * @param aLines array width the lines that you want to write 
	 * @param aFileName name of file
	 * @return
	 */
	public static boolean writeFile(String[] aLines, String aFileName){
		boolean done = true; 
		List<String> list = new ArrayList<String>(aLines.length);
		
		for(int i=0; i < aLines.length; i++)
			list.add(aLines[i]); 
		
		try {
			writeSmallTextFile(list,aFileName);
		} catch (IOException e) {
			e.printStackTrace(); 
			done = false; 
		} 
		return done; 
	}
	
	
	

	private static List<String> readSmallTextFile(String aFileName) throws IOException {
	    Path path = Paths.get(aFileName);
	    return Files.readAllLines(path, ENCODING);
	}
		
	private static void writeSmallTextFile(List<String> aLines, String aFileName) throws IOException {
	    Path path = Paths.get(aFileName);
	    Files.write(path, aLines, ENCODING, StandardOpenOption.CREATE,
	            StandardOpenOption.TRUNCATE_EXISTING );
    }
	
	

}
