public abstract class Food
{
	public String foodDescription;
	public double calorieCount;
	
	public Food (String foodDescription, double calorieCount)
	{
		this.foodDescription = foodDescription;
		this.calorieCount = calorieCount;
	}
	
	public abstract double servingCalories (int numServing);
}

public class fruitFood extends Food
{
	public String season;
	
	public Fruit(String foodDescription, double calorieCount, String season)
	{
		this.season = season;
		super(foodDescription, calorieCount);
	}
	
	public String getSeason() {return this.season};
	
	public fruitFood setSeason(String season) {this.season = season;}
	
	@Override
	public double servingCalories(int numServing)
	{
		return super.calorieCount*numServing;
	}
	
	@Override
	public String toString()
	{
		return "Fruit Description: " + foodDescription + " with a total of: " + calorieCount + " calories and its growing season is: " + season;
	}
}

public class liquidFood extends Food
{
	public double viscosity;
	
	public liquidFood(String foodDescription, double calorieCount, double viscosity)
	{
		this.viscosity = viscosity;
		super(foodDescription, calorieCount);
	}
	
	public double getViscosity () {return this.viscosity;}
	
	public liquidFood setViscosity (double viscosity) {this.viscosity = viscosity;}
	
	@Override
	public double servingCalories(int numServing)
	{
		return super.calorieCount*numServing;
	}
	
	@Override
	public String toString()
	{
		return "Liquid Description: " + foodDescription + " with a total of: " + calorieCount + "calories and a viscosity of: " + viscosity;
	}
}