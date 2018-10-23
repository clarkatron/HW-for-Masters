/*Question 1:
a: 
b: 
imageButton.setOnClickListener(new View.OnClickListener(){
	public void onClick(View v) {
		result = (TextView) findViewById(R.id.editText);
	
		randoWord = WordList.getRandomWord();
		if (!randoWord) {result.setText("Error no words available");}
		else { result.setText(randoWord);}
	}
};


*/
/*Question 2:
Part 1:
Created: This is where the app starts. It has focus and is visible in the sense that it is controlling flow of data. It will pull any saved state if it needs to 
(it is not the first time the app has been run). After it is finished executing it will immediatly move to the started activity but may not necessarily 
be destroyed as it is a part of memory and it is up to the system whether it needs to free that memory up. But it can be destroyed. 

Resumed: The resumed state is what happens after the event comes back from the paused state or stopped state. It will have the focus and will be visible for the 
time that is it available such that it will be working with data. This state can also be destroyed but it is unlikely unless the system needs the memory. 

Paused: The paused state is what happens when the system does something else away from the app. It will not have focus or be visible. It will be destroyed upon
going from this state to the resumed state as that memory will be needed again and if the activity needs to be paused again there will be a different bundle 
for the status of the app at separate times.

Stopped: This is where the system is no longer using the activity. The system will free up any memory not needed by the activity. It will also stop any processes
within the activity to save resources. It will not be visible nor have focus. This state can be destroyed to free up resources.

Part 2:
The back stack is a list of activities that users interact with during a certain job. For instance: if a user is sending a taking a picture and wants to back out of 
the selection window the activity before that would be saved to the back stack so that the user can easily access it quickly. 
*/

/*
Question 3
1) a: 
public boolean equals( Object o )
  {
	 if ( ! ( o instanceof Q ) )
	   return false;
	 else
	 {
	   Q s = (Q) o;
      return ( name.equalsIgnoreCase( s.MiddleName ) );
    }
  }
  
 b: 
 public class K extends Q implements R
 {
	 public K(){
	 //constructs variables and methods within K}
 }

2) a:
public double returnBudget(double expenses)
{
	curbudget = getBudget();
	curexpenses = expenses;
	return (curbudget - curexpenses);
}

b:
public class VacationBrands extends Vacation {
	String brand;
	int rating;
	double price;
	
	public VacationBrands( String newbrand, int newRating, double newPrice)
	{
		this.brand = newBrand;
		this.rating = newRating;
		this.price = newPrice;
	}
}

Question 4
a:
mCalcResult.setText(result);
Intent data = new Intent();
data.putExtra(EXTRA_RESULT, result);
setResult(RESULT_OK, data);

	

	
	 