import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class Memorymanagment 
  {
	private int Size;
	private ArrayList<Partition> Partitions;
	
	public Simulator()
	{
		this.Size=0;
		this.Partitions=new Arraylist<>();
	}
	Public Simulator(int Size)
	{
	  this.Size=Size;
	  this.Partitions=new ArrayList<>();
	  this.Partitions.add(new Partition(0, Size, "Free"));
	}
	
	public void Run()
	{
		
	  int Choice;
	  while((Choice=getChoice())!=-1)
	  {
		  int Policy=4;
		  while(policy>3||Policy<1)
		  {
			  System.out.print("\nEnter Policy Type : ");
              Scanner scanner = new Scanner(System.in);
              Policy = scanner.nextInt(); 
		  }
		  if (!Allocate(PolicyType))
		  {
			 System.err.println("\nOcuupation Failed"); 
		  }
		  else
		  {
			  System.out.println("\nOcuupation Succeded");
		  } 
		  
	  } else if (Choice == 2)
	  {
		if (!MemoryDeallocate()) 
		{
			System.err.println("\nDeallocation Failed");
		}
		 else {
             
             System.out.println("\nDeallocation Succeded");
             
         }
         
     }
	  else if (Ch == 3)
     {   
         ShowPartitions();   
     } 
     else if (Ch == 4)
     {
         ContiguousBlocksDefragmentation();   
     } 
     else if (Ch == 5)
     {
         nonContiguousBlocksDefragmentation();   
     } 
     else 
     {
         
         System.err.println("\n Bad Input");
	 }
	}
  }



private boolean Allocate(int PolicyType){
    
    int Size;
    System.out.print("\nSize of the Partition : ");
    Scanner scanner = new Scanner(System.in);
    Size = scanner.nextInt();
    
    Partition New = new Partition(Size, "Occupied");
    
    int ind = -1;
    if (PolicyType == 1)
    {     
        ind = BestFit(New); 
    } else if (PolicyType == 2)
    {     
      ind = WorstFit(New);   
    } else if (PolicyType == 3)
    {     
        ind = FirstFit(New);  
    }
    if (ind == -1) 
    {     
        return false;       
    } else 
    {    
        Partition Chosen = this.Partitions.get(ind); 
        int start = Chosen.getStartAddress();
        New.setStartAddress(start);
          
        this.Partitions.remove(ind);
        this.Partitions.add(ind, New);
        
        int remainingSize = Chosen.getSize() - Size;
        if (remainingSize != 0)
        {
            int startFree = start + Size;
            this.Partitions.add(ind+1, new Partition(startFree, remainingSize, "Free"));
        }       
        return true;
    }
}
private int BestFit(Partition New)
{
    int ind = -1, mn = 1000000;
    for(int i=0; i<Partitions.size(); ++i)
    {
        if (Partitions.get(i).getStatus().equals("Free"))
        {
            if (Partitions.get(i).getSize() >= New.getSize() 
                 && Partitions.get(i).getSize() < mn)
            {
                mn = Partitions.get(i).getSize();
                ind = i;
            }
        }
    }
    return ind;
}

private int WorstFit(Partition New)
{
    int ind = -1, mx = 0;
    for(int i=0; i<Partitions.size(); ++i)
    {
        if (Partitions.get(i).getStatus().equals("Free"))
        {
            if (Partitions.get(i).getSize() >= New.getSize() 
                 && Partitions.get(i).getSize() > mx)
            {
                mx = Partitions.get(i).getSize();
                ind = i;
            }
        }
    }
    
    return ind;
}

private int FirstFit(Partition New)
{
    for(int i=0; i<Partitions.size(); ++i)
    {
        if (Partitions.get(i).getStatus().equals("Free"))
        {
            if (Partitions.get(i).getSize() >= New.getSize())
            {
                return i;
            }
        }
    }
    
    return -1; 
}

private boolean Deallocate()
{   
    ShowPartitions(); 
    
    int startAddress;
    System.out.print("\n Enter the Start Address: ");
    Scanner scanner = new Scanner(System.in);
    startAddress = scanner.nextInt();
 
    for(int i=0; i<Partitions.size(); ++i)
    {    
        if (Partitions.get(i).getStartAddress() == startAddress && Partitions.get(i).getStatus() != "Free")
        {     
            Partitions.get(i).setStatus("Free");
            return true;   
        }        
    }
    
    return false;    
}

private void ContiguousBlocksDefragmentation()
{
    
    for(int i=0; i<this.Partitions.size()-1; ++i)
    {     
        if  ( this.Partitions.get(i).getStatus() == "Free"
           && this.Partitions.get(i+1).getStatus() == "Free")
        {
            this.Partitions.get(i).increaseSize(this.Partitions.get(i+1).getSize());
            this.Partitions.remove(i+1);
            i--;
        }
        
    }
    
}

private void nonContiguousBlocksDefragmentation()
{  
    int AllfreeSpace = AllFreeSpaceinMemory();
    removeFreeParitions();
    int start = OcuupiedPartitions();
    
    this.Partitions.add(new Partition(start, AllfreeSpace, "Free"));
    
}

private int shiftOcuupied()
{ 
    int start = 0; 
    for(int i=0; i<this.Partitions.size(); ++i)
    {    
        if (this.Partitions.get(i).getStartAddress() != start)
        {
            this.Partitions.get(i).setStartAddress(start);
        }
        
        start = this.Partitions.get(i).getStartAddress() + this.Partitions.get(i).getSize();   
    }
    return start;
}


private void removeFree()
{    
    for(int i=0; i<this.Partitions.size(); ++i)
    {        
        if (this.Partitions.get(i).getStatus() == "Free")
        {            
            this.Partitions.remove(i);
            i--;           
        }  
    }   
}

private int AllFreeSpaceinMemory()
{   
   int sum = 0;
    
    for(int i=0; i<this.Partitions.size(); ++i)
    {      
        if (this.Partitions.get(i).getStatus() == "Free")
        {
            sum += this.Partitions.get(i).getSize();
        }       
    }  
    return sum;   
}
private void ShowPartitions()
{   
    
    for(int i=0; i<this.Partitions.size(); ++i){
        
        int start = this.Partitions.get(i).getStartAddress();
        int size = this.Partitions.get(i).getSize();
        String status = this.Partitions.get(i).getStatus();
        
        System.out.println(
             "\tFrom: " + start + ","
           + "\tTo: " + (start+size-1) + ","
           + "\tStatus: " + status
        ); 
    }
