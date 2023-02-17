package com.epam.rd.autocode.startegy.cards.Strategies;

import com.epam.rd.autocode.startegy.cards.AbstractCardDealingStrategy;
import com.epam.rd.autocode.startegy.cards.Card;
import com.epam.rd.autocode.startegy.cards.Deck;

import java.util.List;
import java.util.Map;

public class ClassicPokerCardDealingStrategy extends AbstractCardDealingStrategy {
    final int cardsPerPlayer = 5;

    @Override
    public Map<String, List<Card>> dealStacks(Deck deck, int players) {
        Map<String, List<Card>> resultMap = getPlayersEmptyStacks(players);
        fillPlayersStacks(deck, resultMap, cardsPerPlayer);
        getRemainingStack(deck, resultMap);
        return resultMap;
    }
}