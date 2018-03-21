package searchsort;

public class HeapSortThread extends AbstractSearchSortThread {
    public HeapSortThread(BarArray barArray, StatsThread stats, ThreadCompletedDelegate del) {
        super("HeapSortThread", barArray, stats, del);
    }

    @Override
    public int executeAlgorithm() throws InterruptedException {
        heapsort(mainArray.size());

        return STATUS_FINISHED;
    }

    public void heapsort(int count) {

        //input: an unordered array a of length count

        //(Build the heap in array a so that largest value is at the root)
        heapify(count);

        //    (The following loop maintains the invariants that a[0:end] is a heap and every element
    //    beyond end is greater than everything before it (so a[end:count] is in sorted order))
        int end = count - 1;
        while(end >0) {
            //            (a[0] is the root and largest value. The swap moves it in front of the sorted elements.)
            mainArray.swap(end,0);
            //        (the heap size is reduced by one)
            end -= 1;
            //            (the swap ruined the heap property, so restore it)
            siftDown(0,end);
        }

    }

//    (Put elements of 'a' in heap order, in-place)
    public void heapify(int count) {
//        (start is assigned the index in 'a' of the last parent node)
//        (the last element in a 0 - based array is at index count - 1;
//        find the parent of that element)
        int start = iParent(count - 1);

        while (start >= 0) {
//            (sift down the node at index 'start' to the proper place such that all nodes below
//            the start index are in heap order)
            siftDown(start, count - 1);
//            (go to the next parent node)
            start = start - 1;
//            (after sifting down the root all nodes/elements are in heap order)
        }
    }
//        (Repair the heap whose root element is at index 'start', assuming the heaps rooted at its children are valid)
        public void siftDown (int start, int end) {
        int root = start;

        while (iLeftChild(root) <= end) {
//            (While the root has at least one child){
                int child = iLeftChild(root); /*(Left child of root)*/
                int swap = root; /*(Keeps track of child to swap with)*/

                if (mainArray.get(swap).compareTo(mainArray.get(child)) < 0) {
                    swap = child;
//                            (If there is a right child and that child is greater)
                }
                if (child + 1 <= end && mainArray.get(swap).compareTo(mainArray.get(child+1))< 0){
                    swap = child + 1;
                }
                if (swap == root) {
//                        (The root holds the largest element.Since we assume the heaps rooted at the
//                                children are valid, this means that we are done.)
                    return;
                } else {
                    mainArray.swap(root,swap);
                    root = swap; /*(repeat to continue sifting down the child now)*/
                }
            }
    }

    public int iParent(int i) {
        return (int) Math.floor((i-1)/2);
    }

    public int iLeftChild(int i) {
        return 2*i +1;
    }

    public int iRightChild(int i) {
        return 2*i + 2;
    }

}
