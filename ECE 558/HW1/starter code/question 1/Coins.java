/* Coins Class
   Original code written by Anderson, Franceschi
   Modified for ECE 558 by Roy Kravitz (2018)
*/

import java.text.DecimalFormat;

public class Coins
{
  public final DecimalFormat MONEY = new DecimalFormat( "$#,##0.00" );
  public final double QUARTER_VALUE = .25;
  public final double DIME_VALUE = .1;
  public final double NICKEL_VALUE = .05;
  public final double PENNY_VALUE = .01;
 
  // TODO: Define your instance variables here.  Make them private int
  

  /**
  * Constructor:<BR>
  * Allows client to set beginning values for quarters,
  *   dimes, nickels, and pennies
  * This constructor takes four parameters<BR>
  * Calls mutator methods to validate new values
  * @param newQuarters the number of quarters
  * @param newDimes the number of dimes
  * @param newNickels the number of nickels
  * @param newPennies the number of pennies
  */
  public Coins( int newQuarters, int newDimes, int newNickels, int newPennies )
  {
    // TODO:  Complete the code.  Use the setters to set initial values
  }

  /** getQuarters method
  * @return the number of quarters
  */
  public int getQuarters( )
  {
    return quarters;
  }

  /**
  * Mutator method:<BR>
  * Allows client to set value of quarters
  * Does not change value if the new value is less than 0<BR>
  * @param quarters the new number of quarters
  * @return a reference to this object
  */
  public Coins setQuarters( int quarters )
  {


  }

  /** getDimes method
  * @return number of dimes
  */
  public int getDimes( )
  {
    return dimes;
  }

  /**
  * Mutator method:<BR>
  * Allows client to set value of dimes
  * Does not change value if new value is less than 0<BR>
  * @param newdimes the new number of dimes
  * @return a reference to this object
  */
  public Coins setDimes( int dimes )
  {
    // TODO: Complete the code
  }

  /** getNickels method
  * @return the number of nickels
  */
  public int getNickels( )
  {
    return nickels;
  }

  /**
  * Mutator method:<BR>
  * Allows client to set value of nickels
  * Does not change value if new value is less than 0<BR>
  * @param nickels the new number of nickels
  * @return a reference to this object
  */
  public Coins setNickels( int nickels )
  {
   // TODO: Complete the code
  }

  /** getPennies method
  * @return the number of pennies
  */
  public int getPennies( )
  {
    return pennies;
  }

  /**
  * Mutator method:<BR>
  * Allows client to set value of pennies
  * Does not change value if new value is less than 0<BR>
  * @param pennies the new number of pennies
  * @return a reference to this object
  */
  public Coins setPennies( int pennies )
  {
    // TODO: Complete the code
  }

/**
  * @return the number of quarters, dimes, nickels, and pennies for the coins
  */
  public String toString( )
  {
    // TODO: Complete the code
  }
  }

  /**
  * equals method
  * Compares two Coins objects for the same field values
  * @param o another Coins object
  * @return a boolean, true if this object
  * has the same field values as the parameter c
  */
  public boolean equals( Object o )
  {
	// TODO: Complete the code
  }

  /**
  * outputTotalAmount method
  * Outputs the total amount in $ notation
  */
  public void outputTotalAmount( )
  {
    // TODO: Complete the code.  Make use of the java.text.DecimalFormat class library that was imported
  }

  /**
  * moneyFromQuarters method
  * Computes the dollar amount from quarters
  * @return a double, the dollar money amount from quarters
  *
  * NOTE:  Need to do the same for dimes, nickels, and pennies
  */
  public double moneyFromQuarters( )
  {

    this.quarters = getQuarters();
    double result = QUARTER_VALUE*this.quarters;
    return result;
  }

  /**
  * moneyFromDimes method
  * Computes the dollar amount from dimes
  * @return a double, the dollar money amount from dimes
  */
  public double moneyFromDimes( )
  {
    // TODO: Complete the code
  }

  /**
  * moneyFromNickels method
  * Computes the dollar amount from nickels
  * @return a double, the dollar money amount from nickels
  */
  public double moneyFromNickels( )
  {
    // TODO: Complete the code
  }

  /**
  * moneyFromPennies method
  * Computes the dollar amount from pennies
  * @return a double, the dollar money amount from pennies
  */
  public double moneyFromPennies( )
  {
    // TODO: Complete the code
  }
}