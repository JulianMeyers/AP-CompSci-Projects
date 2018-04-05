package searchsort;

public class RadixSortThread extends AbstractSearchSortThread{


    public RadixSortThread(BarArray barArray, StatsThread stats, ThreadCompletedDelegate del) {
        super("Radix Sort", barArray, stats, del);
    }

    @Override
    public int executeAlgorithm() throws InterruptedException {
        //radixsort(mainArray,mainArray.size());
        sort(mainArray,10);
        return STATUS_SORTED;
    }

    // A function to do counting search of arr[] according to
    // the digit represented by exp.
    void countSort(BarArray arr, int n, int exp) throws InterruptedException {
        BarArray output = new BarArray(n); // output array
        int i;
        int count[] = new int[10];

        // Store count of occurrences in count[]
        for (i = 0; i < n; i++)
            count[(int) ((arr.get(i).getValue()*10/exp)%10)]++;

        // Change count[i] so that count[i] now contains
        // actual position of this digit in output[]
        for (i = 1; i < 10; i++)
            count[i] += count[i - 1];

        // Build the output array
        for (i = n - 1; i >= 0; i--)
        {
            output.set(count[(int) (arr.get(i).getValue()*10/exp%10)] - 1, arr.get(i));
            count[(int) ((arr.get(i).getValue()*10/exp)%10)]--;
            checkIn();
        }

        // Copy the output array to arr[], so that arr[] now
        // contains sorted numbers according to curent digit
        for (i = 0; i < n; i++) {
            arr.set(i, output.get(i));
            checkIn();
        }
    }

    // The main function to that sorts arr[] of size n using
    // Radix Sort
    void radixsort(BarArray arr, int n) throws InterruptedException {
        // Find the maximum number to know number of digits
//        SortableBar m = getMax(arr, n);

        // Do counting search for every digit. Note that instead
        // of passing digit number, exp is passed. exp is 10^i
        // where i is current digit number
        for (int exp = 1; exp < 10000; exp *= 10)//I already know max val, so I set max exponent
            countSort(arr, n, exp);
    }

    public static void sort(BarArray array, int radix) {
        if (array.size() == 0) {
            return;
        }

        // Determine minimum and maximum values
        double minValue = array.get(0).getValue()*10;
        double maxValue = array.get(0).getValue()*10;
        for (int i = 1; i < array.size(); i++) {
            if (array.get(i).getValue()*10 < minValue) {
                minValue = array.get(i).getValue()*10;
            } else if (array.get(i).getValue()*10 > maxValue) {
                maxValue = array.get(i).getValue()*10;
            }
        }

        // Perform counting search on each exponent/digit, starting at the least
        // significant digit
        int exponent = 1;
        while ((maxValue - minValue) / exponent >= 1) {
            countingSortByDigit(array, radix, exponent, minValue);
            exponent *= radix;
        }
    }

    private static void countingSortByDigit(
            BarArray array, int radix, int exponent, double minValue) {
        int bucketIndex;
        int[] buckets = new int[radix];
        BarArray output = new BarArray(array.size());

        // Initialize bucket
        for (int i = 0; i < radix; i++) {
            buckets[i] = 0;
        }

        // Count frequencies
        for (int i = 0; i < array.size(); i++) {
            bucketIndex = (int)(((array.get(i).getValue()*10 - minValue) / exponent) % radix);
            buckets[bucketIndex]++;
        }

        // Compute cumulates
        for (int i = 1; i < radix; i++) {
            buckets[i] += buckets[i - 1];
        }

        // Move records
        for (int i = array.size() - 1; i >= 0; i--) {
            bucketIndex = (int)(((array.get(i).getValue()*10 - minValue) / exponent) % radix);
            output.set(--buckets[bucketIndex],array.get(i));
        }

        // Copy back
        for (int i = 0; i < array.size(); i++) {
            array.set(i,output.get(i));
        }
    }
}
