package com.design;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


/**
 * Class Refrigerator
 */
 
 public class Refrigerator{
     // Refrigerator can have shelves, which is an list
     //and shelves are of different sizes
     //each shelves can hold food of different sizes
     List<Shelves> shelves = new ArrayList<Shelves>();
     Refrigerator(List<Shelves> shelves){
         this.shelves = shelves;
     }
     public void put(FoodInFridge food, Shelves shelf) throws Exception{
         Size foodSize = food.getFoodSize();
         Size shelfSize = shelf.getShelfSize();
         Integer shelfNumber = shelf.getShelfNumber();
         HashMap<Integer,List<FoodInFridge>> foodSpace = shelf.getFoodSpaceInShelf();
         if(foodSize==shelfSize){
        	 List<FoodInFridge> foodList = new ArrayList<FoodInFridge>();
        	 if(foodSpace.get(shelfNumber)!=null){
        		foodList = foodSpace.get(shelfNumber);
        	 }
        	 foodList.add(food);
         	 foodSpace.put(shelfNumber,foodList);	 
        	 System.out.println("Putting food in fridge shelf -"+shelfNumber+" - "+food.getFoodType());
         }else{
             throw new Exception("Cannot fit in shelf "+ shelfNumber+" "+food.getFoodType() + " in size "+ shelf.getShelfSize());
         }
         
         shelf.setFoodSpaceInShelf(foodSpace);
     }
     
      public void get(FoodInFridge food, Shelves shelf){
    	  HashMap<Integer,List<FoodInFridge>> foodSpace = shelf.getFoodSpaceInShelf();
    	  List<FoodInFridge> foodFound = foodSpace.get(shelf.getShelfNumber());
    	  if(foodFound!=null){
    		  System.out.print("Getting food from fridge shelf "+shelf.getShelfNumber()+"-");
    		  foodFound.forEach((k) ->{
    			  System.out.print(k.getFoodType() +",");
    		  });
    		  System.out.println("");
    	  }else{
    		  System.out.println("No food in the shelf");
    	  }
     }
      
      public static void main(String[] args) throws Exception{
    	     Size size = null;
    	     //Create some shelves
    	     Shelves shelf1 = new Shelves(0, size.SMALL, new HashMap<Integer,List<FoodInFridge>>());
    	     Shelves shelf2 = new Shelves(1, size.MEDIUM, new HashMap<Integer,List<FoodInFridge>>());
    	     Shelves shelf3 = new Shelves(2, size.MEDIUM, new HashMap<Integer,List<FoodInFridge>>());
    	     Shelves shelf4 = new Shelves(3, size.LARGE, new HashMap<Integer,List<FoodInFridge>>());
    	     List<Shelves> shelves = new ArrayList<Shelves>();
    	     shelves.add(shelf1);
    	     shelves.add(shelf2);
    	     shelves.add(shelf3);
    	     shelves.add(shelf4);
    	     //Add shelves to the fridge
    	     Refrigerator fridge = new Refrigerator(shelves);
    	     //Get some food
    	     Beverage bev = new Beverage();
    	     fridge.put(bev,shelf1);
    	     fridge.get(bev,shelf1);
    	      bev = new Beverage();
    	     fridge.put(bev,shelf1);
    	     fridge.get(bev,shelf1);
    	     CookedFood cooked = new CookedFood();
    	     fridge.put(cooked,shelf4);
    	     fridge.get(cooked,shelf4);
    	     Fruits fruit = new Fruits();
    	     fridge.put(fruit,shelf3);
    	     fridge.get(fruit,shelf3);
    	 }
 }
 //Interface for Food that goes in or out of fridge
 interface FoodInFridge{
    //Size foodSize=Size;
    String getFoodType();
    Size getFoodSize();
}

 class Beverage implements FoodInFridge{
    
    //Different type of food has different sizes
    public String getFoodType(){
        return "Beverage";
    }
    
     public Size getFoodSize(){
        return Size.SMALL;
    }
}

 class Fruits implements FoodInFridge{
    //Different type of food has different types and sizes
    public String getFoodType(){
        return "Fruits";
    }
    
     public Size getFoodSize(){
        return Size.MEDIUM;
    }
}

 class CookedFood implements FoodInFridge{
    //Different type of food has different sizes
    public String getFoodType(){
        return "CookedFood";
    }
    
     public Size getFoodSize(){
        return Size.LARGE;
    }
}

 //Creating the shelves object
  class Shelves {
     Integer shelfNumber;
     Size shelfsize;
     HashMap<Integer,List<FoodInFridge>> foodSpaceInShelf = new  HashMap<Integer,List<FoodInFridge>>();
     
     public Shelves(Integer shelfNumber, Size shelfsize, HashMap<Integer,List<FoodInFridge>> foodSpaceInShelf){
         this.shelfsize = shelfsize;
         this.shelfNumber = shelfNumber;
         this.foodSpaceInShelf = foodSpaceInShelf;
     }
     
     public void setShelfSize(Size size){
        this.shelfsize= size;
    }
     public Size getShelfSize(){
        return shelfsize;
    }
    
    public void setShelfNumber( Integer shelfNumber){
    	 this.shelfNumber = shelfNumber;
    }
     public Integer getShelfNumber(){
        return shelfNumber;
    }

	public HashMap<Integer,List<FoodInFridge>>  getFoodSpaceInShelf() {
		return this.foodSpaceInShelf;
	}

	public void setFoodSpaceInShelf(HashMap<Integer,List<FoodInFridge>>   foodSpace) {
		this.foodSpaceInShelf = foodSpaceInShelf;
	}
     
 }
 
  enum Size {SMALL, MEDIUM, LARGE};
 
