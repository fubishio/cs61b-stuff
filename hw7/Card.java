// Represents a card from a 52-card deck.
public class Card {

    // Declare some constants. Your code may or may not find this helpful.
    public static final int SPADES = 1;
    public static final int HEARTS = 2;
    public static final int DIAMONDS = 3;
    public static final int CLUBS = 4;

    // These should be declared private, but declare them public for ease of
    // testing (name, for compatibility with the provided Junit test)
    public int suit;
    public int number;

    public Card(int suit, int number) {
        if (suit < 1 || suit > 4) {
            throw new IllegalArgumentException("Invalid card suit input");
        }
        if (number < 1 || number > 13) {
            throw new IllegalArgumentException("Invalid card number input");
        }
        this.suit = suit;
        this.number = number;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Card) {
<<<<<<< HEAD
            if (((Card) o).suit != this.suit) {
            	return false;
            }
            if (((Card) o).number != this.number) {
            	return false;
            }
            return true;
=======
            // YOUR CODE HERE 
>>>>>>> 8f6f408a5fd4236ae5ef3fa608c34848a757227e
        }
        return false;
    }

    @Override
    public int hashCode() {
<<<<<<< HEAD
        return 13 * suit + number;
=======
        // YOUR CODE HERE (this is possible in one concise line)
        return 0;
>>>>>>> 8f6f408a5fd4236ae5ef3fa608c34848a757227e
    }
}