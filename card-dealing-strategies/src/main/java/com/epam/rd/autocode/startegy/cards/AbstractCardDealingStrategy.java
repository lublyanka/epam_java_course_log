package com.epam.rd.autocode.startegy.cards;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public abstract class AbstractCardDealingStrategy implements CardDealingStrategy{

    protected Map<String, List<Card>> getPlayersEmptyStacks(int players) {
        Map<String, List<Card>> resultMap = new TreeMap<>();
        IntStream.range(1, players + 1)
                .forEachOrdered(playerNumber -> resultMap.put("Player " + playerNumber, new ArrayList<>()));
        return resultMap;
    }

    protected void getRemainingStack(Deck deck, Map<String, List<Card>> resultMap) {
        List<Card> restCards = deck.restCards();
        if (restCards.size() > 0)
            resultMap.put("Remaining", restCards);
    }

    protected void fillPlayersStacks(Deck deck, Map<String, List<Card>> resultMap, int cardsPerPlayer) {
        IntStream.range(0, cardsPerPlayer)
                .forEach(round -> resultMap.forEach((stackName, cardStack) -> cardStack.add(deck.dealCard())));
    }

    protected void addAdditionalStack(String name, int size, Deck deck, Map<String, List<Card>> resultMap) {
        resultMap.put(name,IntStream.range(0, size).mapToObj(x -> deck.dealCard()).collect(Collectors.toList()));
    }
}