
import java.util.*;
import java.io.*;

public class Csv {
	private BufferedReader input;
	private String line;
	private ArrayList<String> field = null;
	private String fieldSep;

	public Csv(String file, String sep){
			fieldSep = sep;
			FileReader inFile = new FileReader(file);
			input = new BufferedReader(inFile);
	}
	public String getLine() {
		try{
			line = input.readLine();
			if(line == null){
				return line;
			}
			field = split(line, fieldSep);
			return line;
		}
		catch(Exception ex){
			System.out.println(ex.getMessage());
		}
		return "";
	}	
	
	public String getField(int n) {
		if (n < 0 ||( n >= getNField()))
			return "";
		else
			return (String) field.get(n);

	}
	public int getNField(){
		return field.size();
	}

	private static int advQuoted(String s, int i, String sep, StringBuffer
			field){
		int j;
		for(j = i; j<s.length(); j++) {
			if (s.charAt(j) == '"' && s.charAt(++j)!='"') {
				int k = s. indexOf(sep, j);
				if(k == -1)
					k=s.length();
				field.append((s.substring(j,k)));
				j=k;
				break;
			}
			field.append(s.charAt(j));
		}
		return j;
	}
	private static ArrayList split(String line, String sep) {
		ArrayList list = new ArrayList();
		int i, j;

		if(line.length() == 0)
			return list;
		
		i = 0 ;
		try{
			do {
				if(i<line.length() && line.charAt(i) == '"') {
					StringBuffer field = new StringBuffer();
					j=advQuoted(line, ++i, sep, field);
					list.add(field.toString());

				}
				else {
					j = line.indexOf(sep, i);
					if(j == -1)
						j = line.length();
					list.add(line.substring(i,j));

				}
				i = j + sep.length();
			}while(j<line.length());
			return list;
		}catch(Exception ex){
			System.out.println(ex.getMessage());
		}
		return list;
	}
	public static void main(String arg[])	{
		String line;
		Csv csvFile = new Csv("test.csv", ",");
		while ((line = csvFile.getLine()) != null) {
			System.out.println("line = `" + line + "'");
				for(int i = 0; i<csvFile.getNField(); i++)
					System.out.println("field[" +i + "] =`" +csvFile.getField(i));
		}
	}

}
