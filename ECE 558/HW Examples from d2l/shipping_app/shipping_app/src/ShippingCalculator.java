/** ShippingCalculator
*   Anderson, Franceschi
*/
import java.util.Scanner;
import java.text.NumberFormat;

public class ShippingCalculator
{
   public static void main( String [] args )
   {
     Scanner scan = new Scanner( System.in );
     NumberFormat money = NumberFormat.getCurrencyInstance( );
     
     ShippingCost shippingCost = null;
     
     System.out.print( "What is the weight of the item? " );
     double weight = scan.nextDouble( );
     
     System.out.println( "Our shipping options are: "
                        + "\n\t1 Express shipping: 1-2 business days"
                        + "\n\t2 Standard shipping: 3-5 business days"
                        + "\n\t3 Flat Rate: 6-8 business days" );
     System.out.print( "Select your shipping option > " );
     int option = scan.nextInt( );
     
     double cost = 0.0;
     switch ( option )
     {
        case 1:
           shippingCost = new ExpressShipping( );
           break;
        case 2: 
           shippingCost = new StandardShipping( );
           break;
        case 3: 
           shippingCost = new FlatRateShipping( );
           break;
        default:
           System.out.println( "Invalid selection." );
     } 
     
     if ( shippingCost != null )
        System.out.println( "Your shipping cost is " 
            + money.format( shippingCost.calculateShipping( weight ) ) );
     else 
        System.out.println( "No valid shipping option selected." );         
   }
} 