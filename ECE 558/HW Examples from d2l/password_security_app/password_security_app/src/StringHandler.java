/*  StringHandler interface
*   Anderson, Franceschi
*/

public interface StringHandler
{
  // abstract methods to implement
  void processLetter( char c );
  void processDigit( char c );
  void processOther( char c );
  
  /** default parse method
  * @param s the String to parse
  */
  default void parse( String s )
  {
    for ( int i = 0; i < s.length( ); i++ )
    {
      char c = s.charAt( i );
      if ( Character.isDigit( c ) )
        processDigit( c );
      else if ( Character.isLetter( c ) )
        processLetter( c );
      else
        processOther( c );
    }
  }
}