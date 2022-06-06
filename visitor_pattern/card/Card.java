package card;

public class Card implements Comparable<Card> {
    private CardType cardType;
    private String name;
    private float weight;
    private float maxDistance;
    private float fuelConsumption;
    private float price;

    /**
     * @param cardType Type of the card
     * @param name Name of the vehicle on the card (do not use whitespaces)
     * @param weight Weight of the vehicle on the card
     * @param maxDistance Maximal travel distance of the vehicle on the card
     * @param fuelConsumption Fuel consumption (100km) of the vehicle on the card
     * @param price Price of the vehicle on the card
     */
    public Card(
            CardType cardType,
            String name,
            float weight,
            float maxDistance,
            float fuelConsumption,
            float price) {
        this.cardType = cardType;
        this.name = name;
        this.weight = weight;
        this.maxDistance = maxDistance;
        this.fuelConsumption = fuelConsumption;
        this.price = price;
    }

    /**
     * Compare to cards in follwing order * 1. price 2. maxDistance 3. fuelConsumption 4. weight 5.
     * cardType
     *
     * @param other the object to be compared.
     * @return 0 if this instance is equal to the other instance less than 0 if this instance is
     *     less than the other instance more than 0 if this instance is greater than the other
     *     instance
     */
    @Override
    public int compareTo(Card other) {
        int compareValue = Float.compare(price, other.price);
        if (compareValue != 0) {
            return compareValue;
        }
        compareValue = Float.compare(maxDistance, other.maxDistance);
        if (compareValue != 0) {
            return compareValue;
        }
        compareValue = Float.compare(fuelConsumption, other.fuelConsumption);
        if (compareValue != 0) {
            return compareValue;
        }
        compareValue = Float.compare(weight, other.weight);
        if (compareValue != 0) {
            return compareValue;
        }
        return cardType.compareTo(other.cardType);
    }

    /**
     * @return The Sound that the vehicle on the card would make.
     */
    public final String getSound() {
        return cardType.getSound();
    }

    /**
     * @return The type of the card.
     */
    public final CardType getCardType() {
        return cardType;
    }

    /**
     * @return The name of the vehicle on the card.
     */
    public final String getName() {
        return name;
    }

    /**
     * @return The weight of the vehicle on the card.
     */
    public final float getWeight() {
        return weight;
    }

    /**
     * @return The maximal travel distance of the vehicle on the card.
     */
    public final float getMaxDistance() {
        return maxDistance;
    }

    /**
     * @return The fuel consumption on 100km of the vehicle on the card.
     */
    public final float getFuelConsumption() {
        return fuelConsumption;
    }

    /**
     * @return The price of the vehicle on the card.
     */
    public final float getPrice() {
        return price;
    }
}
