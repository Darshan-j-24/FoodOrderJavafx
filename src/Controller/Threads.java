package Controller;

import Model.Food;

import java.util.List;



public class Threads extends Thread{
    private final List<Food> list1;
    private final List<Food> list2;
    private final String toSearch;
    private  int result;

    public void LinearSearch()
    {
        int size1 = this.list1.size();
        int size2 = this.list2.size();
        for(int i=0 ; i<size1 ; i++)
        {
            if(this.list1.get(i).getName().equalsIgnoreCase(toSearch))
            {
                System.out.println(list1.get(i).getName() + "Index :" + i);
                this.result = i;
                return;
            }

        }
        for(int i=0 ; i<size2 ; i++)
        {
            if(this.list2.get(i).getName().equalsIgnoreCase(toSearch))
            {
                System.out.println(list2.get(i).getName() + "Index :" + i);
                this.result = size1 + i;
                return;
            }

        }

    }

    public int getResult()
    {
        return this.result;
    }


    Threads(List<Food> List1,List<Food> List2, String toSearch)
    {
        this.list1 = List1;
        this.list2 = List2;
        this.toSearch = toSearch;
        this.result = -1;
    }

   public void run()
   {
       this.LinearSearch();
   }


}

