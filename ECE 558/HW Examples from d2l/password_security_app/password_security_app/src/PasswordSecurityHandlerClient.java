/*  PasswordSecurityHandlerClient class
 *  Anderson, Franceschi
*/

import java.util.Scanner;

public class PasswordSecurityHandlerClient
{
  public static void main( String [] args )
  {
    Scanner scan = new Scanner( System.in );
    System.out.println( "A strong password has at least 8 \n"
          +  "characters and contains at least one digit \n"
          +  "and one special character." );
    System.out.print( "Enter a password > " );
    String password = scan.next( );
  
    PasswordSecurityHandler psh = new PasswordSecurityHandler( );
    psh.parse( password );
    
    System.out.println( password + "'s security is " 
               + psh.securityLevel( ) );
  }
}