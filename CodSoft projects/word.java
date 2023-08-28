import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
public class word {
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        //Asking user to provide a text or a file
        System.out.println("you want to count words of text or a file");
        String ch = sc.nextLine();
        String inputText = "";
        //if text
        if(ch.equalsIgnoreCase("text")){
            System.out.println("Enter your text: ");
            inputText = sc.nextLine();
        }
        //if file
        else if(ch.equalsIgnoreCase("file")){
            System.out.println("Enter the file path: ");
            String fp = sc.nextLine();
            try{
                inputText = readFile(fp);
            }catch(IOException e){
                System.out.println("Error reading the file: " + e.getMessage());
            
            }
        }
        else{
            System.out.println("Invalid Choice");
        
        }
        int counter = cwords(inputText);
        System.out.println("Word count: " + counter);

        sc.close();
    }
    //Counting words of text
    public static int cwords(String Text){
        String[] words = Text.trim().split("[\\s\\p{Punct}]+");
        return words.length;
    }
    //for file
    public static String readFile(String fp) throws IOException{
        StringBuilder content = new StringBuilder();
        BufferedReader reader = new BufferedReader(new FileReader(fp));
        String line;
        while((line = reader.readLine()) != null){
            content.append(line).append("\n");
        }
        reader.close();
        return content.toString();
    }
}
