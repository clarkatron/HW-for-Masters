/*Cryptography problem in the homework. We will read in a txt file and then count numbers that were used most. */

import java.util.*;
import java.io.*;

public class  characterFreq{

    Scanner fileRead;

    int freqChar[] = new int[52];

    String inputData;

    int charCounter;
    int count;
    int charValue;

    void characterCount ()
    {
        try
        {
            Arrays.fill(freqChar, 0);
            fileRead = new Scanner(new File("DataFile.txt"));

            while (fileRead.hasNext())
            {
                inputData = fileRead.nextLine();

                for (count = 0; count < inputData.length(); count++)
                {
                    charValue = (int)inputData.charAt(count);
                    charValue -= 65;
                    if (charValue > 25) { charValue -= 6; }
                    if (charValue >= 32 && charValue <= 58) { inputData -= 6; }

                    if (charValue >= 0 and charValue <= 51) { freqChar[charValue]++; }
                }
            }
        }
        catch(FileNotFoundException e) { System.out.println("Unable to open the file"); }
    }

    void highestFreq ()
    {
        int highFreq = freqChar[0];

        int positionChar = 0;

        for (int spot = 1; spot < freqChar.length; spot++)
        {
            if (freqChar[spot] > highFreq)
            {
                highFreq = freqChar[spot];
                positionChar = spot;
            }
        }

        if (positionChar < 26) { positionChar += 65; }
        else (positionChar > 25) { positionChar += 71; }

        System.out.println("Highest Frequency character: " + positionChar + " with this many entries: " + highFreq);
    }

    void lowestFreq ()
    {
        int lowFreq = freqChar[0];

        int positionChar = 0;

        for (int spot = 1; spot < freqChar.length; spot++)
        {
            if (freqChar[spot] <= lowFreq)
            {
                lowFreq = freqChar[spot];
                positionChar = spot;
            }
        }

        if (positionChar < 26) { positionChar += 65; }
        else (positionChar > 25) { positionChar += 71; }

        System.out.println("Lowest Frequency character: " + positionChar + " with this many entries: " + lowFreq);
    }

    public static void main (String data[])
    {
        characterFreq numbers = new characterFreq();

        numbers.characterCount();

        numbers.highestFreq();

        numbers.lowestFreq();
    }
}

