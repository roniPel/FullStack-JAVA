import java.util.Scanner;

public class Main {
    public static void main(String[] args)
    {
        Scanner s = new Scanner(System.in);

        // Question 1 - write a function that receives an array of integers and prints it out with a space between each item.
        System.out.println("***********  Question 1 **********");
        int [] arr = new int[] {3,6,1,9,33,26,98,4,158,931};    // A sample array in order to demonstrate the 'PrintArray' function.
        System.out.println("Please find the printed array below: ");
        PrintArray(arr);
        System.out.println();

        // Question 2 - Create 2 random arrays with values between 0 and 9 and print them out using the function from Question 1
        System.out.println("***********  Question 2 **********");
        int size2 = 15, range2, min2 = 0, max2 = 9;     //Variables contain the number 2 - referring to question #2
        range2 = max2-min2+1;
        int [] arr1 = CreateRandomArray(min2, range2, size2);
        int [] arr2 = CreateRandomArray(min2, range2, size2);
        System.out.println("The first array is: ");
        PrintArray(arr1);
        System.out.println("The second array is: ");
        PrintArray(arr2);
        System.out.println();

        // Question 3 - create a variable named 'max' which will be the largest 3-digit number
        // found in arr1 and is made up of consecutive numbers in the array
        System.out.println("***********  Question 3 **********");
        int max3, dig3 = 3;
        System.out.println("When looking at the following array: ");
        PrintArray(arr1);
        max3 = FindLargest3DigitNum(arr1);
        System.out.println("The largest "+dig3+"-digit number in the array is: "+max3);
        System.out.println();

        // Question 4 - create a variable named 'min' which will be the lowest 3-digit number
        //found in arr2 and is made up of consecutive numbers in the array
        System.out.println("***********  Question 4 **********");
        int min4, dig4 = 3;
        System.out.println("When looking at the following array: ");
        PrintArray(arr2);
        min4 = FindLowest3DigitNum(arr2);
        System.out.println("The lowest "+dig4+"-digit number in the array is: "+min4);
        System.out.println();

        // Question 5 - Create an array named 'distinct', size 10, with initial values '-1'.
        // Add to the distinct array all values that are distinct from arr1 and arr2
        System.out.println("***********  Question 5 **********");
        int [] distinct = FindDistinct(arr1, arr2);
        System.out.println("The distinct values between the two arrays are: ");
        PrintArray(distinct);
        System.out.println();

        // Question 6 - Create two variables - 'opposite' and 'number' which will contain the numbers inside the 'distinct' array, without the '-1' values.
        System.out.println("***********  Question 5 **********");
        int opposite, number;
        number = ExtractNum(distinct);
        opposite = FlipNum(number);
        System.out.println("The value 'number' is: "+number);
        System.out.println("The value 'opposite' is: "+opposite);
        System.out.println();

        System.out.println("***********  THE END **********");
    }
    static int ExtractNum(int[] a)
    {
        int count = 0, num;
        for (int i = 0; i < a.length; i++)
        {
            if(a[i] != -1)
            {
                count++;
            }
        }
        num = ConvertToInt(0,a,count);
        return num;
    }
    static int FlipNum(int n)
    {
        int flipNum, i = 0;
        int[] flipArr = CreateNegativeOneArray(10);
        while(n>0)
        {
            flipArr[i] = n%10;
            n/=10;
            i++;
        }
        return ExtractNum(flipArr);
    }
    static int[] CreateNegativeOneArray(int size)
    {
        int [] negOne = new int[size];
        for (int i = 0; i<size; i++)
        {
            negOne[i] = -1;
        }
        return negOne;
    }
    static int[] FindDistinct(int[] a1, int[] a2)
    {
        int size = 10, index = 0;
        int [] distinct1 = FindUnique(a1,a2);
        int [] distinct2 = FindUnique(a2,a1);
        //int[] united = CreateNegativeOneArray(size);
        // Unite the distinct arrays
        int [] united = UniteDistinctArrays(index,distinct1, distinct2);
        return united;
    }
    static int[] UniteDistinctArrays(int index, int[] d1, int[] d2)
    {
        int[] united = CreateNegativeOneArray(d1.length);
        for (int i = 0; i < d1.length; i++)
        {
            if(d1[i]== -1)
            {
                continue;
            }
            else
            {
                united[index] = d1[i];
                index++;
            }
        }
        for (int i = 0; i < d2.length; i++)
        {
            if(d2[i]== -1)
            {
                continue;
            }
            else
            {
                united[index] = d2[i];
                index++;
            }
        }
        return united;
    }
    static int[] FindUnique(int[] a1, int[] a2)
    {
        int size = 10, index = 0;
        int [] distinct = CreateNegativeOneArray(size);
        for (int i = 0; i < a1.length; i++)
        {
            for (int j = 0; j < a2.length; j++)
            {
                if(a1[i] == a2[j])
                {
                    break;
                }
                else if (j == (a2.length-1))
                {
                    for (int k = 0; k < distinct.length; k++)
                    {
                        if(distinct[k] == a1[i])
                        {
                            break;
                        }
                        else if (k == (distinct.length - 1))
                        {
                            distinct[index] = a1[i];
                            index++;
                        }
                    }
                }
            }

        }
        return distinct;
    }
    static int FindLowest3DigitNum(int[] a)
    {
        int min=999;
        int temp = 9;
        for (int i = 0; i<a.length; i++)
        {
            int minFirstDigit = min/100;
            if(i+2 < a.length)  //Avoid 'out of bounds' exception
            {
                if (a[i] < minFirstDigit)
                {
                    min = ConvertToInt(i, a, 3);
                }
                if (a[i] == minFirstDigit)   //If value in array matches the first digit in 'min', create a 'temp' number and compare 'temp' and 'min'
                {
                    temp = ConvertToInt(i, a, 3);
                    if (temp < min) {
                        min = temp;
                    }
                }
            }
        }
        return min;
    }
    static int FindLargest3DigitNum(int[] a)
    {
        int max=0;
        int temp = 0;
        for (int i = 0; i<a.length; i++)
        {
            int maxFirstDigit = max/100;
            if(i+2 < a.length)  //Avoid 'out of bounds' exception
            {
                if (a[i] > maxFirstDigit)
                {
                    max = ConvertToInt(i, a, 3);
                }
                if (a[i] == maxFirstDigit)   //If value in array matches the first digit in 'max', create a 'temp' number and compare 'temp' and 'max'
                {
                    temp = ConvertToInt(i, a, 3);
                    if (temp > max) {
                        max = temp;
                    }
                }
            }
        }
        return max;
    }
    static int ConvertToInt(int ind,int[] a, int dig)
    {
        int num = 0;
        for(int i=ind;i<dig+ind;i++)
        {
            num = num*10+a[i];
        }
        return num;
    }
    static void PrintArray(int [] a)
    {
        System.out.print("[ ");
        for (int i = 0; i < a.length; i++)
        {
            System.out.print(a[i]+" ");
        }
        System.out.print("]");
        System.out.println();
    }
    static int [] CreateRandomArray(int min, int range, int size)
    {
        int [] a = new int[size];
        for (int i = 0; i<size; i++)
        {
            a[i] = (int)(Math.random() * range) + min;
        }
        return a;
    }
}