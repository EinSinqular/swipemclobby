package de.sinqular.lobbysystem.lotterie.coins;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class RandomCoins {

    public List<Integer> getList() {
        List<Integer> list = new LinkedList<Integer>();
        for (int i = 0; i < 54; i++) {
            list.add(randomCoin());
        }
        Collections.shuffle(list);
        return list;
    }
    public Integer randomCoin() {
        HashMap<Integer, Integer> randoms = new HashMap<>();

        randoms.put((int) Math.ceil(Math.random() * 100), 100);
        randoms.put((int) Math.ceil(Math.random() * 1000), 60);
        randoms.put((int) Math.ceil(Math.random() * 10000), 6);
        randoms.put((int) Math.ceil(Math.random() * 100000), 2);
        randoms.put((int) 1e6, 1);

        List<Integer> arrayList = new ArrayList<Integer>();

        for (Integer coins : randoms.keySet()) {
            for (int i = 0; i < (randoms.get(coins) * 10); i++) {
                arrayList.add(coins);
            }
        }
        Collections.shuffle(arrayList);

        int r = new Random().nextInt(arrayList.size());

        return arrayList.get(r);
    }

}
