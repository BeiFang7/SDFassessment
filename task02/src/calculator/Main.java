package calculator;

import java.io.Console;

public class Main {
  public static void main(String[] args) {
    
    Console cons = System.console();
    
    Boolean stop = false;
    Double last = (double) 0;
    Double number1 = (double) 0;
    Double number2 = (double) 0;
    String operation ="";

    System.out.println("Welcome.");
    
    while (!stop){
      String line = cons.readLine("> ");
      if(line.isEmpty()){
        System.out.println("You have not entered any operation.");
      } else if (line.equals("exit")){
        System.out.println("Bye bye");
        stop=true;
      } else if (line.trim().split(" ").length==3){
        line = line.trim();
        String[] lines = line.split(" ");

        operation = lines[1];


        if (lines[0].contains("$last")&&lines[2].contains("$last")){
          number1 = last;
          number2 = last;
        } else if(lines[0].contains("$last")){
          number1 = last;
          number2 = Double.parseDouble(lines[2]);
        } else if (lines[2].contains("$last")){
          number2 = last;
          number1 = Double.parseDouble(lines[0]);
        } else {
          number1 = Double.parseDouble(lines[0]);
          number2 = Double.parseDouble(lines[2]);
        }
        
        
        switch(operation){
          case "+":
            System.out.println(number1 + number2);
            last = number1 + number2;

            break;
          case "-":
            System.out.println(number1 - number2);
            last = number1 - number2;
            break;
          case "*":
            System.out.println(number1 * number2);
            last = number1 * number2;
            break;
          case "/":
            System.out.println(number1 / number2);
            last = number1 / number2;
            break;
          default:
            System.out.println("unknown operation");  
        }

        
      } else {
        System.out.println("Invalid command.");

      }//if-else loop


    }//while loop
  }//main
}//class
