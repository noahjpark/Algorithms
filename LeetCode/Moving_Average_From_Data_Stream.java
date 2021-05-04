/* Noah Park

Given a stream of integers and a window size, calculate the moving average of all integers in the sliding window.

Implement the MovingAverage class:

MovingAverage(int size) Initializes the object with the size of the window size.
double next(int val) Returns the moving average of the last size values of the stream.

*/

class MovingAverage {
    
    // Intuition: Apply a circular queue. Maintain the total sum of the window and override the oldest value with the new one once we have updated the sum. 
    // Time: O(1) since we never have to loop and we maintain our sum and window values.
    // Space: O(n) where n is the size of the window.
    int[] window;
    int size = 0, i = 0, n;
    double sum = 0;

    /** Initialize your data structure here. */
    public MovingAverage(int size) {
        window = new int[size];
        n = size;
    }
    
    public double next(int val) {
        int old = size < n ? 0 : window[i % n];
        window[i++ % n] = val;
        
        sum += (val - old);
        if (size < window.length) size++;
        
        return sum / size;
    }
}

/**
 * Your MovingAverage object will be instantiated and called as such:
 * MovingAverage obj = new MovingAverage(size);
 * double param_1 = obj.next(val);
 */
