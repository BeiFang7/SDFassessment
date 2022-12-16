package mailmerge;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
  public static void main(String[] args) {
    String csv = args[0];
    String txt = args[1];
    Map<String, List<String>> csvMap = new HashMap<>();

    //Read CSV file and store the values in Map<<String> header, List<String> data>
    try {
      Reader csvr = new FileReader(csv);
      BufferedReader csvbr = new BufferedReader(csvr);
      String csvline;
      String[] headers = {};
      String[] lines = {};

      //Read first line of CSV, split with , and put as key in the Map
      if((csvline = csvbr.readLine()) != null){
        headers = csvline.split(",");

        for (String h: headers){
          csvMap.put(h, new ArrayList<>());
        }
      }

      //Read other lines of CSV, split with , and put as list in the Map
      while ((csvline = csvbr.readLine()) != null){
        lines = csvline.split(",");
        for (Integer i = 0; i < lines.length; i++){
          csvMap.get(headers[i]).add(lines[i]);
        }
      }

      //CHECK csvMap
      //System.out.println(csvMap);

      String txtline;

      //loop over Map value list size
      for (Integer h=0; h<csvMap.get("last_name").size();h++){

        //Read txt files, need to put within loop to read the file loop number of times.
        Reader txtr = new FileReader(txt);
        BufferedReader txtbr = new BufferedReader(txtr);
        
        
        while((txtline = txtbr.readLine()) != null){
          
          //Check no of times read txt file
          //System.out.println("readtxt");

          //Check presence of << & >>
          Integer startIndex = txtline.indexOf("<<");
          Integer endIndex = txtline.indexOf(">>");
          
          //Get the variable within the << >>
          if(startIndex != -1 && endIndex != -1){
            String variable = txtline.substring(startIndex+2,endIndex);
            String variableWithDelimiters = txtline.substring(startIndex,endIndex+2);

            //Replace the variable with Map ArrayList
            if(csvMap.containsKey(variable)){
              txtline = txtline.replaceAll(variableWithDelimiters,csvMap.get(variable).get(h));
              System.out.println(txtline);
            }
          } 
        
        }System.out.println("\n");
        txtr.close();
        txtr.close();

      }
      
      csvbr.close();
      csvr.close();

    } catch (FileNotFoundException e) {
      e.printStackTrace();
      System.out.println("File Not Found");
    } catch (IOException e) {
      e.printStackTrace();
      System.out.println("IOException");
    }
    
    


  }
  
}
