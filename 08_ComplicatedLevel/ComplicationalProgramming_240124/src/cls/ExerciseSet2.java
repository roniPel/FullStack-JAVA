package cls;

public class ExerciseSet2 {
    public static void RunProgram_Ex2() {
        int size = 100;
        int min = 1, max = 35;

        int[] myNumArray = CreateArray(size, min, max);
        int randIdx = (int) (Math.random()*size);
        boolean isBalanced = isBalanced(myNumArray,randIdx);
    }

    private static boolean isBalanced(int[] myArray, int x) {
        int sumBeforeX= 0, sumAfterX = 0;
        int arrLen = myArray.length;
        for (int i = 0; i < arrLen; i++) {
            if(i <= x) {
                sumBeforeX += myArray[i];
            }
            else {
                sumAfterX += myArray[i];
            }
        }
        if(sumBeforeX == sumAfterX) {
            System.out.println("Array is balanced! :)");
            return true;
        }
        System.out.println("Array is not balanced! :(");
        return false;
    }
    public static int[] CreateArray(int size, int min, int max) {
        int [] myArray = new int[size];
        int range = max-min+1;
        for (int i = 0; i < myArray.length; i++) {
            myArray[i] = (int)(Math.random() * range) + min;
        }
        // Todo - sort myArray = myArray.sort(function(a, b) {return a - b}) ;
        return myArray;
    }
}
