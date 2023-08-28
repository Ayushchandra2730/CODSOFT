// Number Game
import java.util.Random;
import java.util.Scanner;
class ngame{
    public static void main (String args[]){
         Scanner sc = new Scanner(System.in);

        //multiple rounds
         boolean cont = true;
         while(cont){

        //Genrating random number
        Random r = new Random();
        int randomNum = r.nextInt(100) + 1;

      // Taking input
      System.out.println("Total chances will be 5");
       System.out.println("Enter your guessd number: ");
       int num = sc.nextInt();
        System.out.println();

       //Checking 
       int i=1;
       int t=5;
       while(num != randomNum && t != 1  ){
        if(num > randomNum){
            System.out.println("high");
        }
        else if(num < randomNum){
            System.out.println("low");
        } 
           i++;t--;
           System.out.println("Chances left: "+t);
           System.out.println("Retry");
           System.out.println();
           num = sc.nextInt();
       }

       //Result
       if(num == randomNum){
        System.out.println("correct");
        System.out.println("Number of attempts: "+i);
        System.out.println();
    }
       else{
        System.out.println("You failed");
       }

    //Asking
       System.out.println("Play again? yes/no ");
       String Option = sc.next().toLowerCase();
       System.out.println();
       if(Option.equals("no")){
        cont = false;
       }
    }
       sc.close();
      
    }
}