/** StandardShipping
*   cost is $2.50 for the first 5 pounds,
*      and $1 for each additonal pound
*   Anderson, Franceschi
*/

public class StandardShipping implements ShippingCost
{   
    private final double BASE_WEIGHT = 5;
    private final double BASE_CHARGE = 2.5;
    private final double EXTRA_POUND_RATE = 1.00;
   
   @Override
   public double calculateShipping( double weight )
   {
      if ( weight > BASE_WEIGHT )    
        return BASE_CHARGE 
             +  ( ( weight - BASE_WEIGHT ) * EXTRA_POUND_RATE ); 
      else 
        return BASE_CHARGE;     
   } 
}