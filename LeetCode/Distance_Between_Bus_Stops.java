/* Noah Park

A bus has n stops numbered from 0 to n - 1 that form a circle. We know the distance between all pairs of neighboring stops where distance[i] is the distance between the stops number i and (i + 1) % n.

The bus goes along both directions i.e. clockwise and counterclockwise.

Return the shortest distance between the given start and destination stops.

*/

class Solution {
    public int distanceBetweenBusStops(int[] distance, int start, int destination) {
        // left going in the left direction, right going in the right direction
        int left = start, right = start, leftDistance = 0, rightDistance = 0;
        while(left != destination){ // check the distance from start to destination traversing in the left direction
            if(left == 0) left = distance.length - 1;
            else left--;
            leftDistance += distance[left];
        }
        while(right != destination){ // check the distance from start to destination traversing in the right direction
            rightDistance += distance[right];
            right++;
            if(right == distance.length) right = 0;
        }
        
        // return the minimum of the two distances
        return Math.min(leftDistance, rightDistance);
    }
}
