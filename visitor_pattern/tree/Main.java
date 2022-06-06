package tree;

import card.*;
import java.util.*;

public class Main {
    private static Random random = new Random();

    public static void main(String[] args) {

        // Create Dataset
        List<Card> cards = new ArrayList<>();
        cards.add(new Card(CardType.CAR, "Car_1", 350f, 120, 10, 8050.5f));
        cards.add(new Card(CardType.CAR, "Car_2", 1000f, 1230, 35.5f, 300050f));
        cards.add(new Card(CardType.CAR, "Car_3", 5f, 0.5f, 0f, 120f));
        cards.add(new Card(CardType.CAR, "Car_4", 450f, 500f, 7, 9000f));
        cards.add(new Card(CardType.AIRPLANE, "Airplane_1", 12341f, 15654f, 2300, 123123f));
        cards.add(new Card(CardType.AIRPLANE, "Airplane_2", 45671f, 16543f, 2100, 112389f));
        cards.add(new Card(CardType.AIRPLANE, "Airplane_3", 135f, 14568f, 1900, 67923f));
        cards.add(new Card(CardType.AIRPLANE, "Airplane_4", 12342f, 14563f, 1600, 5367f));
        cards.add(new Card(CardType.MOTORCYCLE, "Motorcycle_1", 21f, 105f, 3f, 1500f));
        cards.add(new Card(CardType.MOTORCYCLE, "Motorcycle_2", 15f, 510f, 8f, 2000f));
        cards.add(new Card(CardType.MOTORCYCLE, "Motorcycle_3", 41f, 251f, 8f, 1300f));
        cards.add(new Card(CardType.MOTORCYCLE, "Motorcycle_4", 12f, 161f, 4f, 1500.5f));
        cards.add(new Card(CardType.BOAT, "Boat_1", 115f, 115f, 14f, 11111f));
        cards.add(new Card(CardType.BOAT, "Boat_2", 112f, 16f, 12f, 15000f));
        cards.add(new Card(CardType.BOAT, "Boat_3", 113f, 156f, 11f, 1200f));
        cards.add(new Card(CardType.BOAT, "Boat_4", 131f, 185f, 18f, 11612f));
        // Shuffle dataset
        Collections.shuffle(cards);

        // Create tree
        Node root = new Node(cards.get(0));
        cards.remove(0);
        cards.forEach(c -> root.addChild(c));
        System.out.println(root.toDotGraph());

        // TODO Visitor
    }
}
