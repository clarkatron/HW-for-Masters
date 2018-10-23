/*  PasswordSecurityHandler class
 *  Implements methods of the StringHandler interface
 *  to parse a String containing a password.
 *  
 *  Anderson, Franceschi
*/

public class PasswordSecurityHandler 
                    implements StringHandler
{
  private int length;               
  private boolean digit;            
  private boolean otherCharacter; 
  
  /** default constructor
  *  sets length to 0, digit and otherCharacter flags to false
  */
  public PasswordSecurityHandler( )
  {
    length = 0;
    digit = false;
    otherCharacter = false;
  }

  /** processLetter method
  * @param c  character to process
  * adds 1 to length
  */
  @Override
  public void processLetter( char c )
  { 
    length++;
  }

  /** processDigit method
  * @param c  character to process
  * adds 1 to length, sets digit flag to true
  */
  @Override
  public void processDigit( char c )
  { 
    length++;
    digit = true; 
  }
  
  /** processOther method
  * @param c  character to process
  * adds 1 to length,
  * sets otherCharacter flag to true
  */
  @Override
  public void processOther( char c )
  {
    length++;
    otherCharacter = true; 
  }

  /** securityLevel method 
  * @return  "weak" if password contains fewer than 6 characters
  *          "strong" if password has at least 8 characters, at least
  *                  one digit, and at least one other character
  *                 that is neither a letter nor a digit
  *          "medium" otherwise
  */
  public String securityLevel( )
  {
    if ( length < 6 )
      return "weak";
    else if ( length >= 8 && digit && otherCharacter )
      return "strong";
    else
      return "medium";
  }
}