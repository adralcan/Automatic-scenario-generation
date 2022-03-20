package com.mapGenerator.springboot.solutionGenerator.models;

import java.util.List;

public class SolutionGenerator {
    private List<String> defaultBlocks; // Instrucciones disponibles para generar la solucion
    private List<List<String>> solutions; // soluciones diferentes, cada solucion es una lista de instrucciones

    public List<String> getDefaultBlocks() {
        return defaultBlocks;
    }

    public SolutionGenerator(List<String> defaultBlocks) {
        // Las instrucciones disponibles estaran definidos por la unidad a la que pertenezcan.
        // El peor caso es aquella unidad que tengan todas las instrucciones del plan de estudios disponibles

        // (?) Combinaciones con repeticion - n sobre k, siendo n defaultBlocks.length y 1 <= k
        // Siempre, casi siempre va a haber menos bloques disponibles de los que vamos a utilizar
        // Entonces, a lo mejor podemos hacer set de combinaciones
        // Ejemplo de wikipedia: https://es.wikipedia.org/wiki/Combinaciones_con_repetición
        // X={a, b, c, d}
        // aaa	aab	aac	aad	abb	abc	abd	acc	acd	add
        // bbb	bbc	bbd	bcc	bcd	bdd	ccc	ccd	cdd	ddd
        // Esas son las combinaciones disponibles, entonces ahora hago otra vez combinaciones pero de los resultados


    }
}
