/* Store Class
   Original code written by Anderson, Franceschi
*/

public class Store
{
  public final double SALES_TAX_RATE = 0.06;
  private String name;

  /**
  * Constructor:<BR>
  * Allows client to set beginning value for name
  * This constructor takes one parameter<BR>
  * Calls mutator method
  * @param name the name of the store
  */
  public Store( String name )
  {
    setName( name );
  }

  /** getName method
  * @return a String, the name of the store
  */
  public String getName( )
  {
    return name;
  }

  /**
  * Mutator method:<BR>
  * Allows client to set value of name
  * @param name the new name for the store
  */
  public void setName( String name )
  {
    this.name = name;
  }

  /**
  * @return a string representation of the store
  */
  public String toString( )
  {
    return( "name: " + name );
  }

  /**
  * equals method
  * Compares two Store objects for the same field value
  * @param o another Store object
  * @return a boolean, true if this object
  * has the same field value as the parameter s
  */
  public boolean equals( Object o )
  {
	 if ( ! ( o instanceof Store ) )
	   return false;
	 else
	 {
	   Store s = (Store) o;
      return ( name.equalsIgnoreCase( s.name ) );
    }
  }
}

//Music Store being represented here:
public class musicStore extends Store
{
  int numTitles;
  String storeAddress;

  //Constructor
  public musicStore (int numTitles, String storeAddress, String newStore)
  {
    super(newStore);
    this.numTitles = numTitles;
    this.storeAddress = storeAddress;
  }

  //toString method that we will override for our own purposes.
  public String toString ()
  {
    return "Name of Store " + newStore +" with " + numTitles + " titles at " + storeAddress ".";
  }

  //equals method
  public boolean equals ( Object o )
  {
    if (!(o instanceof musicStore))
      return false;
    else
    {
      musicStore objMusic = (musicStore) o;

      if ((this.numTitles == objMusic.numTitles)
              && (this.storeAddress == objMusic.storeAddress))
        return true;
      else
        return false;
    }
  }
}