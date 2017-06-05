import java.lang.*;
import java.util.*;
import java.io.*;

public class PhoneDict {
	// Read the ACMIA_WORDS
	// Rerturn the list of words
	public static ArrayList<String> wordList(String fileName) {
		String line = null;
		ArrayList<String> wordList = new ArrayList<String>();
		try {
			FileReader fileReader = new FileReader(fileName);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			while((line = bufferedReader.readLine()) != null) {
				wordList.add(line);
			}   
			bufferedReader.close();         
		}
		catch(FileNotFoundException ex) {
			System.out.println(
				"Unable to open file '" + 
				fileName + "'");                
		}
		catch(IOException ex) {
			System.out.println(
				"Error reading file '" 
				+ fileName + "'");                  
		}
		return wordList;
	}

	// Modify the list of words to one to many relationship
	public static HashMap<String,List<String>> mapping(List<String> list) {
		HashMap<String,List<String>> map = new HashMap<String,List<String>>();
		// Loop throught each word in the list
		// Pop the Hashmap
		for(String word: list) {
			String tmp = new String(word.toLowerCase());
			StringBuffer sb = new StringBuffer();
			// Map each character to the phone butoon number
			for(char s: tmp.toCharArray()) {
				//System.out.println(s);
				if(s == 'a'||s == 'b'||s == 'c') sb.append("2");
				if(s == 'd'||s == 'e'||s == 'f') sb.append("3");
				if(s == 'g'||s == 'h'||s == 'i') sb.append("4");
				if(s == 'j'||s == 'k'||s == 'l') sb.append("5");
				if(s == 'm'||s == 'n'||s == 'o') sb.append("6");
				if(s == 'p'||s == 'q'||s == 'r'||s == 's') sb.append("7");
				if(s == 't'||s == 'u'||s == 'v') sb.append("8");
				if(s == 'w'||s == 'x'||s == 'y'||s == 'z') sb.append("9");
			}
			String key = sb.toString();
			if(map.containsKey(key)){
				List<String> words = new ArrayList<String>(map.get(key));
				words.add(word);
				map.put(key, words);
			} else {
			// sb does not exist
				List<String> words  = new ArrayList<String>();
				words.add(word);
				map.put(key, words);
			}
		}
		return map;
	}
	// Return a list of input
	public static String[] inputList(String input) {
		String[] _inputList = input.split("0+");
		
		return _inputList;
	}
	// Print the needed information by checking the dictionary
	public static void printInformation(String[] inputs, Map<String,List<String>> dict) {
		for(String input: inputs) {
			if(input!=""){
				if(dict.containsKey(input)){
					List<String> words = new ArrayList<String>(dict.get(input));
					if(words.size() == 1) for(String word: words) System.out.print(word+" ");
					else {
						String joined = String.join("|", words);
						System.out.print("("+joined+") ");
					}
				} else {
					int length = input.length();
					for(int i = 0; i < length; i++) {
						System.out.print("*");
					}
					System.out.print(" ");
				}
			}
		}
		// Avoid the result overlaps with the command line
		System.out.println("");
	}

	public static void main(String[] args) {
		// Prepare for the dictionary
		String fileName= System.getenv("ACMIA_WORDS"); // Get the path to ACMIA_WORDS
		List<String> list = new ArrayList<String>(wordList(fileName));
		Map<String,List<String>> dict = new HashMap<String,List<String>>(mapping(list));
		// Prepare the input sequence
		String[] inputs = inputList(args[0]);
		// Print out the needed information
		printInformation(inputs, dict);
	}
}

