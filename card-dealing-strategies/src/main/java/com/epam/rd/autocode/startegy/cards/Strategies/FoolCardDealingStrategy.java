package com.epam.rd.autocode.startegy.cards.Strategies;

import com.epam.rd.autocode.startegy.cards.AbstractCardDealingStrategy;
import com.epam.rd.autocode.startegy.cards.Card;
import com.epam.rd.autocode.startegy.cards.Deck;

import java.util.List;
import java.util.Map;

public class FoolCardDealingStrategy extends AbstractCardDealingStrategy {
    final int cardsPerPlayer = 6;

    @Override
    public Map<String, List<Card>> dealStacks(Deck deck, int players) {
        Map<String, List<Card>> resultMap = getPlayersEmptyStacks(players);
        fillPlayersStacks(deck, resultMap, cardsPerPlayer);
        addAdditionalStack("Trump card", 1, deck, resultMap);
        getRemainingStack(deck, resultMap);
        return resultMap;
    }
}