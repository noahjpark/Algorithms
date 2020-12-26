/* Noah Park

In a deck of cards, every card has a unique integer.  You can order the deck in any order you want.

Initially, all the cards start face down (unrevealed) in one deck.

Now, you do the following steps repeatedly, until all cards are revealed:

Take the top card of the deck, reveal it, and take it out of the deck.
If there are still cards in the deck, put the next top card of the deck at the bottom of the deck.
If there are still unrevealed cards, go back to step 1.  Otherwise, stop.
Return an ordering of the deck that would reveal the cards in increasing order.

The first entry in the answer is considered to be the top of the deck.

*/

class Solution {
    public int[] deckRevealedIncreasing(int[] deck) {
        if (deck == null || deck.length == 0) return deck; // edge cases
        
        Arrays.sort(deck); // sort deck so we can do the algorithm in reverse order
        
        LinkedList<Integer> q = new LinkedList<>(); // use a linked list easily add to the front and end or remove from the front and end
        
        // iterate through the deck backwards (largest to smallest) and do the algorithm backwards:
        // first move the last element to the front, then add the next largest element from the deck
        for (int i = deck.length - 1; i >= 0; i--) {
            if (i == deck.length - 1) q.addFirst(deck[i]); // if the list is empty we need to put the first value in
            else {
                q.addFirst(q.removeLast());
                q.addFirst(deck[i]);
            }
        }
        
        int i = 0;
        while (!q.isEmpty()) deck[i++] = q.removeFirst(); // the list will be in the proper order, we just need to put it into the deck to return the proper list
        
        return deck;
    }
}
