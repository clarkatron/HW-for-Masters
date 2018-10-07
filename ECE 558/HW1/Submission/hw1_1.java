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
  private int Quarter_count = 0;
  private int Dime_count = 0;
  private int Nickel_count = 0;
  private int Penny_count = 0;
  


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
    this.quarters = quarters;

    this.dimes = dimes;

    this.nickels = nickels;

    this.pennies = pennies;
  }

  /** getQuarters method
  * @return the number of quarters
  */
  public double getQuarters( )
  {
    return quarters*QUARTER_VALUE;
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
    this.quarters = quarters;
  }

  /** getDimes method
  * @return number of dimes
  */
  public double getDimes( )
  {
    return dimes*DIME_VALUE;
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
    this.dimes = dimes;
  }

  /** getNickels method
  * @return the number of nickels
  */
  public double getNickels( )
  {
    return nickels*NICKEL_VALUE;
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
   this.nickels = nickels;
  }

  /** getPennies method
  * @return the number of pennies
  */
  public double getPennies( )
  {
    return pennies*PENNY_VALUE;
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
    this.pennies = pennies;
  }

/**
  * @return the number of quarters, dimes, nickels, and pennies for the coins
  */
  public String toString( )
  {
    return quarters + " quarters, " + dimes " dimes, " + nickels " nickels, and " + pennies " pennies.";
  }


  /**
  * equals method
  * Compares two Coins objects for the same field values
  * @param o another Coins object
  * @return a boolean, true if this object
  * has the same field values as the parameter c
  */
  @Override
  public boolean equals( Object o )
  {
    if ( !(o instanceof Coins))
      return false;
    else {
      Coins objCoin = (Coins) o;

      if ((this.quarters == objCoin.quarters)
              && (this.dimes == objCoin.dimes)
              && (this.nickels == objCoin.nickels)
              && (this.pennies == objCoin.pennies))
        return true;
      else
        return false;
    }
  }
  /**
  * outputTotalAmount method
  * Outputs the total amount in $ notation
  */
  public void outputTotalAmount( )
  {
    float totalMoney = 0;
    total += moneyFromPennies();
    total += moneyFromNickels();
    total += moneyFromDimes();
    total += moneyFromQuarters();

    return MONEY.format(total);
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
    double result = QUARTER_VALUE * this.quarters;
    return result;
  }
  /**
  * moneyFromDimes method
  * Computes the dollar amount from dimes
  * @return a double, the dollar money amount from dimes
  */
  public double moneyFromDimes( )
  {
    double result = DIME_VALUE * this.dimes;
    return result;
  }
  /**
  * moneyFromNickels method
  * Computes the dollar amount from nickels
  * @return a double, the dollar money amount from nickels
  */
  public double moneyFromNickels( )
  {
    double result = NICKEL_VALUE * this.nickels;
    return result;
  }
  /**
  * moneyFromPennies method
  * Computes the dollar amount from pennies
  * @return a double, the dollar money amount from pennies
  */
  public double moneyFromPennies( )
  {
    double result = PENNY_VALUE * this.pennies;
    return result;
  }
}

public class testCoins {
  public static void main(String[] args) {
    Coins coins = new Coins(2, 3, 8, 7);

    System.out.println(coins.toString());

    System.out.print("Total: ");
    System.out.println(coins.outputTotalAmount());

    System.out.println("Quarters: " + coins.getQuarters());

    System.out.println("Dimes: " + coins.getDimes());

    System.out.println("Nickels: " + coins.getNickels());

    System.out.println("Pennies: " + coins.getPennies());
    }
  }




  }
}