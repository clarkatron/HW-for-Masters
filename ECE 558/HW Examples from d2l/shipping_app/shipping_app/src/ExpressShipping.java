/** ExpressShipping
*   cost is $10, in addition to standard shipping
*   Anderson, Franceschi
*/

public class ExpressShipping extends StandardShipping 
{   
   private final double EXPRESS_SURCHARGE = 10;
   
   @Override
   public double calculateShipping( double weight )
   {
      return EXPRESS_SURCHARGE + super.calculateShipping( weight );       
   } 
}