package com.highpeak.problem.pkg1;

import java.io.*;
import java.util.*;
//goodies and its prices...
class Item {
  String name;
  int price;

  public Item(String name, int price) {
    this.name = name;
    this.price = price;
  }

  public String toString() { 
      return this.name + ": " + this.price;
  }
}

public class Main {
  public static void main(String[] args) throws Exception {
	  
	  File f=new File("C:\\Users\\omsai\\Desktop\\HighPeak\\input22222.txt");
	  try {
		  //creating a file named f....
		  f.createNewFile();
	  }
	  catch(Exception e) {
		  e.printStackTrace();
	  }
	  
    FileInputStream fis=new FileInputStream(f);
    // reading input from the created file.... 
    Scanner sc=new Scanner(fis);
    // numbers of employees are reading from input file....
    int number_of_employees = Integer.parseInt(sc.nextLine().split(": ")[1]);
    sc.nextLine(); sc.nextLine(); sc.nextLine();
    // storing all the goodies in ArrayList....
    ArrayList<Item> goodies_items = new ArrayList<Item>();

    while(sc.hasNextLine())  
    {
      String current[] = sc.nextLine().split(": ");
      goodies_items.add(new Item(current[0], Integer.parseInt(current[1])));
    }
    sc.close();

    // sorting goodies according to there price in acceding order....
     Collections.sort(goodies_items, new Comparator<Item>(){
      public int compare(Item a, Item b) { 
        return a.price - b.price; 
      } 
    });

     // calculating the minimum difference of the goodies.....
    int min_diff = goodies_items.get(goodies_items.size()-1).price;
    int min_index = 0;
    for(int i=0;i<goodies_items.size()-number_of_employees+1;i++) {
      int diff = goodies_items.get(number_of_employees+i-1).price-goodies_items.get(i).price;

      if(diff<=min_diff) {
        min_diff = diff;
        min_index = i;
      }
    }
    // creating output file.....
    
     
    FileWriter fw = new FileWriter("C:\\Users\\omsai\\Desktop\\HighPeak\\output22222.txt");
    fw.write("Number of the employees: " + number_of_employees+"\n\n");
    //writing on output file......
    fw.write("Here the goodies that are selected for distribution are:\n\n");
    for(int i=min_index;i<min_index + number_of_employees; i++) {
      fw.write(goodies_items.get(i).toString() + "\n");
    }

    fw.write("\nAnd the difference between the chosen goodie with highest price and the lowest price is " + min_diff);
	  fw.close();
  }
}