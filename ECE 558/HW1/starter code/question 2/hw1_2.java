/*1: The following code sequence is intended to print Hello four times; however, it
only prints Hello once. Where is the problem in this code sequence? */
for (int i = 0; i <= 3; i++);
	System.out.println("Hello");
	
//The issue with this is the semi-colon on the for line. It should read out:
for (int i = 0; i <= 3; i++)
	System.out.println("Hello");

/*2: The following snippet of code was supposed to output a total of 60 but it is
only showing 13. What is the problem and how do you fix it? */
int a = 47;
a =+ 13;
System.out.println("The value of a is " + a);

/*The fix to this is in how you are incrementing a. You need to use the pre-increment
instead of the post-increment which only sets the variable not update it. */
int a = 47;
a += 13;
System.out.println("The value of a is " + a);

/*3: The following code has an error in the output which is an arrayoutofindex error. 
What is going on and how do you fix it? */
int[][] a = {{3,2,1,0},{5,10,15,20}};
for (int j = 0, j <= a[1].length; j++) {
	if (a[1][j] == 20) {
		System.out.println("Found 20 at column index " + j + " of second row");
	}
}

/* The reason for this is that you are using the <= in the four loop so you are trying to access 
more elements than are there. There are two separate ways that you could use to fix this. One 
is "for (int j = 0; j <= a[1].length-1; j++)" The other way is: */
int[][] a = {{3,2,1,0},{5,10,15,20}};
for (int j = 0; j < a[1].length; j++) {
	if (a[1][j] == 20) {
		System.out.println("Found 20 at column index " + j + " of second row");
	}
}

/*4: Write a method that takes an integer input from the user, then prompts for additional
integers and keeps track of the sum of all of the integers. When the user enters a negative 
number the program should print out the sum of all of the integers. Do not include the negative
number in the sum. */

import java.util.*;
import java.io.*;

public static void main( String [] args)
{
	Scanner scan = new Scanner(System.in);
    int result = 0;
	
    System.out.println("Enter an integer to add to the total: ");
	int temp = scan.nextInt();
    
	while (temp >= 0) 
	{
		if (temp >= 0) 
		{
			result += temp;
		}
		System.out.println("Enter an integer to add to the total: ");
		temp = scan.nextInt();
	}
	System.out.println("Total is: " + result);
}
