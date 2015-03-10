package com.lagnada.xmx1024.config.search;

public class RecursiveBinarySearch {

    private static final int KEY_NOT_FOUND = -1;
    private int[] data;

    public RecursiveBinarySearch(int... data) {
        this.data = data;
    }


    public boolean search(int key) {
        return binary_search(data, key, 0, data.length - 1) != -1;
    }

    int binary_search(int A[], int key, int imin, int imax) {
        // test if array is empty
        if (imax < imin)
            // set is empty, so return value showing not found
            return KEY_NOT_FOUND;
        else {
            // calculate midpoint to cut set in half
            int imid = midpoint(imin, imax);

            // three-way comparison
            if (A[imid] > key)
                // key is in lower subset
                return binary_search(A, key, imin, imid - 1);
            else if (A[imid] < key)
                // key is in upper subset
                return binary_search(A, key, imid + 1, imax);
            else
                // key has been found
                return imid;
        }
    }

    private int midpoint(int imin, int imax) {
        int mid = (imax- imin + 1) >> 1;
        return imin + mid;
    }

    private int midpointxx(int imin, int imax) {
        int mod = (imax - imin) % 2;
        int mid = (imax - imin) / 2;

        if (mod != 0) {
            mid++;
        }
        if (mid == 0) {
            return imin;
        }
        return imin + mid;
    }
}
