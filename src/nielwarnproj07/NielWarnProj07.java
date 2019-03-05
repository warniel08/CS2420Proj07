/*
    This program displays the running time of the selection,
    bubble, insertion, merge, and quick sorts.
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
        // variables start and end for measuring the time it takes to run sort
        long start, end;
        int sizeOfArray;
        boolean again;
        Scanner userChoice = new Scanner(System.in);
        
        do {    
            sizeOfArray = createArraySize();
            // create new random number assignment
            Random randomNum = new Random();

            // initialize arrays with Size OF Array
            Integer[] origNumsArray = new Integer[sizeOfArray];
            Integer[] selectionSortArr;
            Integer[] bubbleSortArr;
            Integer[] insertionSortArr;
            Integer[] mergeSortArr;
            Integer[] quickSortArr;

            // fill original array with random numbers that range from 0 - Size of array
            for (int i = 0; i < sizeOfArray; i++) {
                origNumsArray[i] = randomNum.nextInt(sizeOfArray);
            }

            // display the first 10 nums from array before it's sorted
            displayArray(origNumsArray);

            // copy original array to sort array
            selectionSortArr = copyArray(origNumsArray);
            // assign to start the current time in milliseconds
            start = System.currentTimeMillis();
            // run the sort algorithm
            selectionSort(selectionSortArr, sizeOfArray);
            // assign to end the current time in milliseconds after the sort method
            end = System.currentTimeMillis();
            // display the amount of time it took to sort the array by subtracting start from end
            System.out.println("\nThis sort took: " + (end-start) + " milliseconds.\n");

            // display the first 10 nums from the array after it's sorted
            displayArray(selectionSortArr);
            
            System.out.print("Would you like to run this again with a different number of items?(y/n) ");
            
            again = runAgain(userChoice.next());
        } while (again);
    }
    
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
    
    public static Integer[] copyArray(Integer[] arrayToCopy) {
        Integer[] newArray = new Integer[arrayToCopy.length];
        for (int i = 0; i < arrayToCopy.length; i++) {
            newArray[i] = arrayToCopy[i];
        }
        return newArray;
    }
    
    public static void displayArray(Integer[] array) {
        for (int i = 0; i < 10; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println("");
    }
    
    public static boolean runAgain(String choice) {
            return choice.toLowerCase().equals("y");
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
    // Implementation left as an exercise.
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