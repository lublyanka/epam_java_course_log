package com.epam.rd.autocode.startegy.cards;

import com.epam.rd.autocode.startegy.cards.Strategies.BridgeCardDealingStrategy;
import com.epam.rd.autocode.startegy.cards.Strategies.ClassicPokerCardDealingStrategy;
import com.epam.rd.autocode.startegy.cards.Strategies.FoolCardDealingStrategy;
import com.epam.rd.autocode.startegy.cards.Strategies.TexasHoldemCardDealingStrategy;

public class CardDealingStrategies {
    public static CardDealingStrategy texasHoldemCardDealingStrategy() {
        return new TexasHoldemCardDealingStrategy();
    }

    public static CardDealingStrategy classicPokerCardDealingStrategy() {
        return new ClassicPokerCardDealingStrategy();
    }

    public static CardDealingStrategy bridgeCardDealingStrategy() {
        return new BridgeCardDealingStrategy();
    }

    public static CardDealingStrategy foolCardDealingStrategy() {
        return new FoolCardDealingStrategy();
    }
}