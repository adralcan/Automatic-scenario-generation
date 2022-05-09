package com.mapGenerator.springboot.solutionGenerator.models;

import java.util.ArrayList;

public class SolutionGeneratorRules {
    static Boolean leastOneKeyBlock(String keyBlock, ArrayList<String> partialSolution) {
        int cont = 0;
        for (String block : partialSolution) {
            if (block.equals(keyBlock)) cont++;
        }
        return cont > 0;
    }

    static ArrayList<String> simplifyTurns(ArrayList<String> partialSolution) {
        if (partialSolution.size() <= 3) return partialSolution;
        int cont = 0;
        boolean same = false;
        for (int i = 1; i <= partialSolution.size(); i++) {
            if (partialSolution.get(i).equals("turnLeft") || partialSolution.get(i).equals("turnRight")) {
                if (partialSolution.get(i).equals(partialSolution.get(i - 1))) {
                    if (!same) {
                        same = true;
                        cont = 2;
                    } else {
                        cont++;
                    }
                }
            }
            if (cont == 3) {
                same = false;
                cont = 0;
                partialSolution.set(i, partialSolution.get(i).equals("turnLeft") ? "turnRight" : "turnLeft");
                partialSolution.subList(i - 2, i - 1).clear();
                i -= 2;
            }
        }
        return partialSolution;
    }
}
