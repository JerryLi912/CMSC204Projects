import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
/**
 * This class converts morseCode to English.
 * @author jerry
 *
 */
public class MorseCodeConverter {
		
	public MorseCodeConverter() {}

	/**
	 * returns a string with all the data in the tree in LNR order with an space in between them. 
	 * Uses the toArrayList method in MorseCodeTree. 
	 * It should return the data in this order:
     * "h s v i f u e l r a p w j  b d x n c k y t z g q m o"
     * Note the extra space between j and b - that is because there is an empty string that is the root, and in the LNR traversal, 
     * the root would come between the right most child of the left tree (j) and the left most child of the right tree (b). 
     * This is used for testing purposes to make sure the MorseCodeTree has been built properly
	 *
	 * @return the data in the tree in LNR order separated by a space.
	 */
	public static String printTree() {
		MorseCodeTree<String> s = new MorseCodeTree<>();
		ArrayList<String> list = new ArrayList<>();
		String tree_information = " ";
		list = s.toArrayList();
		String tree[] = new String[list.size()];
		for(int i = 0; i < list.size(); i++) {
			tree[i] = list.get(i);
			tree_information += tree[i] + " ";
		}
		
		return tree_information;
	}
	
	
	/**
	 * Converts Morse code into English. 
	 * Each letter is delimited by a space (‘ ‘). 
	 * Each word is delimited by a ‘/’.
	 * Example:
     * code = ".... . .-.. .-.. --- / .-- --- .-. .-.. -.. "
     * string returned = "Hello World"
	 * @param code code -the MorseCode
	 * @return the English translation
	 */
	public static String convertToEnglish(String code) {
		MorseCodeTree<String> s = new MorseCodeTree<>();
		String letter = "";
		int temp = 0;
		int count = 0;
		for(int i = 0; i < code.length(); i++) {
			if(code.charAt(i) == ' ') {
				letter += s.fetch(code.substring(temp, i));
				temp = i + 1;	
			}
			if(code.charAt(i) == '/') {
				temp = i + 2;
				letter += " ";
				i += 1;
			}
		}
		return letter;
	}
	
	
	
	/**
	 * Converts a file of Morse code into English Each letter is delimited by a space (‘ ‘). 
	 * Each word is delimited by a ‘/’.
     * Example:
     * a file that contains ".... . .-.. .-.. --- / .-- --- .-. .-.. -.. "
     * string returned = "Hello World"
	 * @param codeFile codeFile -name of the File that contains MorseCode  
	 * @return the English translation of the file
	 * @throws FileNotFoundException
	 */
	@SuppressWarnings("resource")
	public static String convertToEnglish(File codeFile)throws FileNotFoundException{
		Scanner input = new Scanner(codeFile);
		String convertToEnglish = "";
		String result = "";
		if(!codeFile.exists()) {
			throw new FileNotFoundException();
		}
		while (input.hasNext()) {
			convertToEnglish = input.next() + " ";
			result += convertToEnglish(convertToEnglish);
		}
		input.close();
		System.out.println(result);
		return result;
	}
	
	public static void main(String[] args) {
		File input = new File("DaisyDaisy.txt");
		MorseCodeConverter.printTree();
		System.out.println( "\n\nMorse Code Convert to English:\n"+ MorseCodeConverter.convertToEnglish(".-.. --- ...- . / .-.. --- --- -.- ... / -. --- - / "
																										+ ".-- .. - .... / - .... . / . -.-- . ... / -... ..- - / "
																										+ ".-- .. - .... / - .... . / -- .. -. -.. \n"));
		System.out.println(MorseCodeConverter.convertToEnglish(".... --- .-- / -.. --- / .. / .-.. --- ...- . / - .... . . / .-.. . - / -- . / -.-. --- ..- -. - / - .... . / .-- .- -.-- ... "));
		System.out.println("\nInput file test DaisyDaisy.txt:");
		try {
			 MorseCodeConverter.convertToEnglish(input);
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		}
	
	}
}
