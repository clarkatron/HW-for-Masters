/** FlatRateShipping
*   cost is .50/pound
*   Anderson, Franceschi
*/

public class FlatRateShipping implements ShippingCost
{
   private final double RATE_PER_POUND = .50;
   
   @Override
   public double calculateShipping( double weight )
   {
      return RATE_PER_POUND * weight; 
   } 
}