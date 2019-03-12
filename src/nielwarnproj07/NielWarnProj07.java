/*
    This program displays the running time of the selection,
    bubble, insertion, merge, and quick sorts. It also allows
    the user to select how many integers to sort in an array.
    It will ask the user if it wants to run the program again.

    Warner Nielsen
    3/4/19
    Project 07
    CS 2420
    Garth Sorenson
*/

package nielwarnproj07;
import java.util.*;

public class NielWarnProj07 {

    public static void main(String[] args) {
        int sizeOfArray; // keep track of array size
        boolean again; // boolean used for userChoice if they want to run the program again
        
        System.out.println("Welcome to the Sort Methods Timer Program");
        
        do {
            // give sizeOfArray it's size using user input found in createArraySize() method
            sizeOfArray = createArraySize();
            // create new random number assignment
            Random randomNum = new Random();

            // initialize origNumsArray with sizeOfArray variable 
            Integer[] origNumsArray = new Integer[sizeOfArray];
  
            // fill original array with random numbers that range from 0 - size of array
            for (int i = 0; i < sizeOfArray; i++) {
                origNumsArray[i] = randomNum.nextInt(sizeOfArray);
            }

            // display the first 10 nums from array before it's sorted
            System.out.print("\nUnsorted Array: ");
            displayArray(origNumsArray);
       
            // Run each sort method using the origNumbersArray
            runSelectionSort(origNumsArray);
            runBubbleSort(origNumsArray);
            runInsertionSort(origNumsArray);
            runMergeSort(origNumsArray);
            runQuickSort(origNumsArray);
            
            again = runAgain(); // get users choice and store in again variable
        } while (again);
    }
    
    public static void runSelectionSort(Integer[] originalArr) {
        // variables start and end for measuring the time it takes to run sort
        long start, end, totalTime;
        Integer[] selectionSortArr;
        
        System.out.println("\nStats for Selection Sort:");
        
        // copy original array to sort arrays
        selectionSortArr = copyArray(originalArr);
        
        // assign to start the current time in milliseconds
        start = System.currentTimeMillis();
        // run the sort algorithm using the selectionSort array and its length
        selectionSort(selectionSortArr, selectionSortArr.length);
        // assign to end the current time in milliseconds after the sort method
        end = System.currentTimeMillis();
        // calculate totalTime
        totalTime = (end-start);
        // display the amount of time it took to sort the array
        System.out.println("The Selection Sort took " + totalTime + " milliseconds to sort.");
        
        // display the first 10 nums from the array after it's sorted
        System.out.print("Sorted Array: ");
        displayArray(selectionSortArr);
    }
    
    public static void runBubbleSort(Integer[] originalArr) {
        // variables start and end for measuring the time it takes to run sort
        long start, end, totalTime;
        Integer[] bubbleSortArr;
        
        System.out.println("\nStats for Bubble Sort:");
        
        // copy original array to sort arrays
        bubbleSortArr = copyArray(originalArr);
        
        // assign to start the current time in milliseconds
        start = System.currentTimeMillis();
        // run the sort algorithm using the bubbleSort Array and its length
        bubbleSort(bubbleSortArr, bubbleSortArr.length);
        // assign to end the current time in milliseconds after the sort method
        end = System.currentTimeMillis();
        // calculate totalTime
        totalTime = (end-start);
        // display the amount of time it took to sort the array
        System.out.println("The Bubble Sort took " + totalTime + " milliseconds to sort.");
        
        // display the first 10 nums from the array after it's sorted
        System.out.print("Sorted Array: ");
        displayArray(bubbleSortArr);
    }
    
    public static void runInsertionSort(Integer[] originalArr) {
        // variables start and end for measuring the time it takes to run sort
        long start, end, totalTime;
        Integer[] insertionSortArr;
        
        System.out.println("\nStats for Insertion Sort:");
        
        // copy original array to sort arrays
        insertionSortArr = copyArray(originalArr);
        
        // assign to start the current time in milliseconds
        start = System.currentTimeMillis();
        // run the sort algorithm using the insertionArray and its length
        insertionSort(insertionSortArr, insertionSortArr.length);
        // assign to end the current time in milliseconds after the sort method
        end = System.currentTimeMillis();
        // calculate totalTime
        totalTime = (end-start);
        // display the amount of time it took to sort the array
        System.out.println("The Insertion Sort took " + totalTime + " milliseconds to sort.");
        
        // display the first 10 nums from the array after it's sorted
        System.out.print("Sorted Array: ");
        displayArray(insertionSortArr);
    }
    
    public static void runMergeSort(Integer[] originalArr) {
        // variables start and end for measuring the time it takes to run sort
        long start, end, totalTime;
        Integer[] mergeSortArr;
        
        System.out.println("\nStats for Merge Sort:");
        
        // copy original array to sort arrays
        mergeSortArr = copyArray(originalArr);
        
        // assign to start the current time in milliseconds
        start = System.currentTimeMillis();
        // run the sort algorithm using the mergeSort Array
        mergesort(mergeSortArr);
        // assign to end the current time in milliseconds after the sort method
        end = System.currentTimeMillis();
        // calculate totalTime
        totalTime = (end-start);
        // display the amount of time it took to sort the array
        System.out.println("The Merge Sort took " + totalTime + " milliseconds to sort.");
        
        // display the first 10 nums from the array after it's sorted
        System.out.print("Sorted Array: ");
        displayArray(mergeSortArr);
    }
    
    public static void runQuickSort(Integer[] originalArr) {
        // variables start and end for measuring the time it takes to run sort
        long start, end, totalTime;
        Integer[] quickSortArr;
        
        System.out.println("\nStats for Quick Sort:");
        
        // copy original array to sort arrays
        quickSortArr = copyArray(originalArr);
        
        // assign to start the current time in milliseconds
        start = System.currentTimeMillis();
        // run the sort algorithm using the array and a starting and ending value
        quickSort(quickSortArr, 0, quickSortArr.length-1);
        // assign to end the current time in milliseconds after the sort method
        end = System.currentTimeMillis();
        // calculate totalTime
        totalTime = (end-start);
        // display the amount of time it took to sort the array
        System.out.println("The Quick Sort took " + totalTime + " milliseconds to sort.");
        
        // display the first 10 nums from the array after it's sorted
        System.out.print("Sorted Array: ");
        displayArray(quickSortArr);
    }
    
    /*
        Method to create the size for the original array. All other
        arrays are based off this size when the original array is
        used in copyArray(). It validates for non integer user input,
        and it also limits the user to select an integer value of 10
        or more. It takes no parameters and it returns an integer
        value to be stored in size.
    */
    public static int createArraySize() {
        int arraySize;
        // get user input for how many numbers to store in the array
        Scanner userInput = new Scanner(System.in);
        
        do {
            System.out.print("Please enter how many numbers you would like to sort (10 or more): ");
            while (!userInput.hasNextInt()) {
                System.out.println("Sorry, that's not an integer...");
                System.out.print("Please enter how many numbers you would like to sort (10 or more): ");
                userInput.next();
            }
            arraySize = userInput.nextInt();
        } while (arraySize < 10);
        return arraySize;
    }
    
    /*
        Method to copy one array to another. It takes in the array
        to copy. It creates a new array and returns the new array
        with the copied values from the original array.
    */
    public static Integer[] copyArray(Integer[] arrayToCopy) {
        Integer[] newArray = new Integer[arrayToCopy.length];
        for (int i = 0; i < arrayToCopy.length; i++) {
            newArray[i] = arrayToCopy[i];
        }
        return newArray;
    }
    
    /*
        Method to print out the values of an array
        in order. It takes in an array and doesn't
        return any value.
    */
    public static <T extends Comparable<? super T>>
         void displayArray(T[] array) {
        for (int i = 0; i < 10; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println("");
    }
    
    /*
        Method to ask the user if they want to run the entire program again.
        It will get the userChoice and validate if it's anything other than
        y or n. If y it will run the program again. If n it will end the program.
        It takes no parameters and returns a boolean value.
    */
    public static boolean runAgain() {
        System.out.print("\nWould you like to run this again with a different number of items?(y/n) ");
        Scanner input = new Scanner(System.in);
        String userChoice = input.next();
        while (!userChoice.equalsIgnoreCase("y") && !userChoice.equalsIgnoreCase("n")) {
            System.out.print("Invalid response. Try again(y/n): ");
            userChoice = input.next();
            System.out.println("");
        }
        if (userChoice.equalsIgnoreCase("n")) {
            System.out.println("Come back again.");
            return false;
        } else {
            return userChoice.equalsIgnoreCase("y");
        }
    }

    public static <T extends Comparable<? super T>>
           void selectionSort(T[] theArray, int n) {
    // ---------------------------------------------------
    // Sorts the items in an array into ascending order.
    // Precondition: theArray is an array of n items.
    // Postcondition: theArray is sorted into
    // ascending order.
    // Calls: indexOfLargest.
    // ---------------------------------------------------
      // last = index of the last item in the subarray of
      //        items yet to be sorted
      // largest = index of the largest item found

      for (int last = n-1; last >= 1; last--) {
        // Invariant: theArray[last+1..n-1] is sorted
        // and > theArray[0..last]

        // select largest item in theArray[0..last]
        int largest = indexOfLargest(theArray, last+1);

        // swap largest item theArray[largest] with
        // theArray[last]
        T temp = theArray[largest];
        theArray[largest] = theArray[last];
        theArray[last] = temp;
      }  // end for
    }  // end selectionSort

    private static <T extends Comparable<? super T>>
            int indexOfLargest(T[] theArray, int size) {
    // ---------------------------------------------------
    // Finds the largest item in an array.
    // Precondition: theArray is an array of size items;
    // size >= 1.
    // Postcondition: Returns the index of the largest
    // item in the array.
    // ---------------------------------------------------
      int indexSoFar = 0; // index of largest item found so far

      // Invariant: theArray[indexSoFar]>=theArray[0..currIndex-1]
      for (int currIndex = 1; currIndex < size; ++currIndex) {
        if (theArray[currIndex].compareTo(theArray[indexSoFar])>0) {
          indexSoFar = currIndex;
        }  // end if
      } // end for

      return indexSoFar;  // index of largest item
    }  // end indexOfLargest

    public static <T extends Comparable<? super T>>
           void bubbleSort(T[] theArray, int n) {
    // ---------------------------------------------------
    // Sorts the items in an array into ascending order.
    // Precondition: theArray is an array of n items.
    // Postcondition: theArray is sorted into ascending
    // order.
    // ---------------------------------------------------
      boolean sorted = false;  // false when swaps occur

      for (int pass = 1; (pass < n) && !sorted; ++pass) {
        // Invariant: theArray[n+1-pass..n-1] is sorted
        //            and > theArray[0..n-pass]
        sorted = true;  // assume sorted
        for (int index = 0; index < n-pass; ++index) {
          // Invariant: theArray[0..index-1] <= theArray[index]
          int nextIndex = index + 1;
          if (theArray[index].compareTo(theArray[nextIndex]) > 0) {
            // exchange items
            T temp = theArray[index];
            theArray[index] = theArray[nextIndex];
            theArray[nextIndex] = temp;
            sorted = false;  // signal exchange
          }  // end if
        }  // end for

        // Assertion: theArray[0..n-pass-1] < theArray[n-pass]
      }  // end for
    }  // end bubbleSort

    public static <T extends Comparable<? super T>>
           void insertionSort(T[] theArray, int n) {
    // ---------------------------------------------------
    // Sorts the items in an array into ascending order.
    // Precondition: theArray is an array of n items.
    // Postcondition: theArray is sorted into ascending
    // order.
    // ---------------------------------------------------
      // unsorted = first index of the unsorted region,
      // loc = index of insertion in the sorted region,
      // nextItem = next item in the unsorted region

      // initially, sorted region is theArray[0],
      //          unsorted region is theArray[1..n-1];
      // in general, sorted region is theArray[0..unsorted-1],
      //          unsorted region is theArray[unsorted..n-1]

      for (int unsorted = 1; unsorted < n; ++unsorted) {
        // Invariant: theArray[0..unsorted-1] is sorted

        // find the right position (loc) in
        // theArray[0..unsorted] for theArray[unsorted],
        // which is the first item in the unsorted
        // region; shift, if necessary, to make room
        T nextItem = theArray[unsorted];
        int loc = unsorted;

        while ((loc > 0) &&
               (theArray[loc-1].compareTo(nextItem) > 0)) {
          // shift theArray[loc-1] to the right
          theArray[loc] = theArray[loc-1];
          loc--;
        }  // end while
        // Assertion: theArray[loc] is where nextItem belongs
        // insert nextItem into sorted region
        theArray[loc] = nextItem;
      }  // end for
    }  // end insertionSort

    public static<T extends Comparable<? super T>>
           void mergesort(T[ ] theArray) {
    // Declare temporary array used for merge, must do
    // unchecked cast from Comparable<?>[] to T[]

      T[] tempArray = (T[])new Comparable<?>[theArray.length];
      mergesort(theArray, tempArray, 0, theArray.length - 1 );
    } // end mergesort

    private static<T extends Comparable<? super T>>
            void merge(T[] theArray, T[] tempArray,
                       int first, int mid, int last) {

    // ---------------------------------------------------------
    // Merges two sorted array segments theArray[first..mid] and
    // theArray[mid+1..last] into one sorted array.
    // Precondition: first <= mid <= last. The subarrays
    // theArray[first..mid] and theArray[mid+1..last] are
    // each sorted in increasing order.
    // Postcondition: theArray[first..last] is sorted.
    // Implementation note: This method merges the two
    // subarrays into a temporary array and copies the result
    // into the original array theArray.
    // ---------------------------------------------------------

      // initialize the local indexes to indicate the subarrays
      int first1 = first;    // beginning of first subarray
      int last1  = mid;      // end of first subarray
      int first2 = mid + 1;  // beginning of second subarray
      int last2  = last;     // end of second subarray
      // while both subarrays are not empty, copy the
      // smaller item into the temporary array
      int index = first1;    // next available location in
                             // tempArray

      while ((first1 <= last1) && (first2 <= last2)) {
        // Invariant: tempArray[first1..index-1] is in order
        if (theArray[first1].compareTo(theArray[first2])<0) {
          tempArray[index] = theArray[first1];
          first1++;
        }
        else {
          tempArray[index] = theArray[first2];
          first2++;
        }  // end if
        index++;
      }  // end while

      // finish off the nonempty subarray

      // finish off the first subarray, if necessary
      while (first1 <= last1) {
        // Invariant: tempArray[first1..index-1] is in order
        tempArray[index] = theArray[first1];
        first1++;
        index++;
      }  // end while
      // finish off the second subarray, if necessary
      while (first2 <= last2) {
        // Invariant: tempArray[first1..index-1] is in order
        tempArray[index] = theArray[first2];
        first2++;
        index++;
      }  // end while

      // copy the result back into the original array
      for (index = first; index <= last; ++index) {
        theArray[index] = tempArray[index];
      }  // end for
    }  // end merge

    public static <T extends Comparable<? super T>>
           void mergesort(T[] theArray, T[] tempArray,
                          int first, int last) {
    // ---------------------------------------------------------
    // Sorts the items in an array into ascending order.
    // Precondition: theArray[first..last] is an array.
    // Postcondition: theArray[first..last] is sorted in
    // ascending order.
    // Calls: merge.
    // ---------------------------------------------------------
      if (first < last) {
        // sort each half
        int mid = (first + last)/2;   // index of midpoint
        // sort left half theArray[first..mid]
        mergesort(theArray, tempArray, first, mid);
        // sort right half theArray[mid+1..last]
        mergesort(theArray, tempArray, mid+1, last);

        // merge the two halves
        merge(theArray, tempArray, first, mid, last);
      }  // end if
    }  // end mergesort

    private static <T extends Comparable<? super T>>
             void choosePivot(T[] theArray, int first, int last) {
    // ---------------------------------------------------------
    // Chooses a pivot for quicksort's partition algorithm and
    // swaps it with the first item in an array.
    // Precondition: theArray[first..last] where first <= last.
    // Postcondition: theArray[first] is the pivot.
    // ---------------------------------------------------------
        T indexFirst, indexMid, indexLast;
        
        // mid used to find the mdidle value of the array by taking the array length and dividing by 2
        int mid = theArray.length/2;
        
        // these are temp indexes used for the swapping in the array
        indexFirst = theArray[first];
        indexMid = theArray[mid];
        indexLast = theArray[last];
        
        // checks if mid < first && if mid < last, if so it swaps mid and first
        if ((indexMid.compareTo(indexFirst) < 0 && indexMid.compareTo(indexLast) > 0) || 
                (indexMid.compareTo(indexFirst) > 0 && indexMid.compareTo(indexLast) < 0)) {
            theArray[first] = indexMid;
            theArray[mid] = indexFirst;
        // else if last < first && last < mid, swap first and last
        } else if ((indexLast.compareTo(indexFirst) < 0 && indexLast.compareTo(indexMid) > 0) || 
                (indexLast.compareTo(indexFirst) > 0 && indexLast.compareTo(indexMid) < 0)) {
            theArray[first] = indexLast;
            theArray[last] = indexFirst;
        // otherwise keep first where it is
        } else {
            theArray[first] = indexFirst;
        }
    }  // end choosePivot

    private static <T extends Comparable<? super T>>
            int partition(T[] theArray, int first, int last) {
    // ---------------------------------------------------------
    // Partitions an array for quicksort.
    // Precondition: theArray[first..last] where first <= last.
    // Postcondition: Returns the index of the pivot element of
    // theArray[first..last]. Upon completion of the method,
    // this will be the index value lastS1 such that
    //    S1 = theArray[first..lastS1-1] <  pivot
    //         theArray[lastS1]          == pivot
    //    S2 = theArray[lastS1+1..last]  >= pivot
    // Calls: choosePivot.
    // ---------------------------------------------------------
      // tempItem is used to swap elements in the array
      T tempItem;
      // place pivot in theArray[first]
      choosePivot(theArray, first, last);
      T pivot = theArray[first];   // reference pivot

      // initially, everything but pivot is in unknown
      int lastS1 = first;          // index of last item in S1

      // move one item at a time until unknown region is empty
      // firstUnknown is the index of first item in unknown region

      for (int firstUnknown = first + 1; firstUnknown <= last;
                                        ++firstUnknown) {
        // Invariant: theArray[first+1..lastS1] < pivot
        //            theArray[lastS1+1..firstUnknown-1] >= pivot
        // move item from unknown to proper region
        if (theArray[firstUnknown].compareTo(pivot) < 0) {
          // item from unknown belongs in S1
          ++lastS1;
          tempItem = theArray[firstUnknown];
          theArray[firstUnknown] = theArray[lastS1];
          theArray[lastS1] = tempItem;
        }  // end if
      // else item from unknown belongs in S2
      }  // end for

      // place pivot in proper position and mark its location
      tempItem = theArray[first];
      theArray[first] = theArray[lastS1];
      theArray[lastS1] = tempItem;
      return lastS1;
    }  // end partition

    public static <T extends Comparable<? super T>>
           void quickSort(T[] theArray, int first, int last) {

    // ---------------------------------------------------------
    // Sorts the items in an array into ascending order.
    // Precondition: theArray[first..last] is an array.
    // Postcondition: theArray[first..last] is sorted.
    // Calls: partition.
    // ---------------------------------------------------------

      int pivotIndex;

      if (first < last) {
        // create the partition: S1, Pivot, S2
        pivotIndex = partition(theArray, first, last);

        // sort regions S1 and S2
        quickSort(theArray, first, pivotIndex-1);
        quickSort(theArray, pivotIndex+1, last);
      }  // end if
    }  // end quickSort

}