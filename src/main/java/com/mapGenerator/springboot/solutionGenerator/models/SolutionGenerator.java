package com.mapGenerator.springboot.solutionGenerator.models;

import java.util.List;

public class SolutionGenerator {
    private List<String> defaultBlocks; // Instrucciones disponibles para generar la solucion
    private List<List<String>> solutions; // soluciones diferentes, cada solucion es una lista de instrucciones

    public List<String> getDefaultBlocks() {
        return defaultBlocks;
    }

    public SolutionGenerator(List<String> defaultBlocks) {
        // Las instrucciones disponibles estaran definidas por la unidad a la que pertenezcan.
        // El peor caso es aquella unidad que tengan todas las instrucciones del plan de estudios disponibles

        // Tiene que cumplir las reglas de validación

        // (?) Combinaciones con repeticion - n sobre k,
        // siendo n defaultBlocks.length y 1(ese uno es avanzar/retrodecer) <= k
        // Siempre, casi siempre va a haber menos bloques disponibles de los que vamos a utilizar
        // Entonces, a lo mejor podemos hacer set de combinaciones
        // Ejemplo de wikipedia: https://es.wikipedia.org/wiki/Combinaciones_con_repetición
        // X={a, b, c, d}
        // aaa	aab	aac	aad	abb	abc	abd	acc	acd	add
        // bbb	bbc	bbd	bcc	bcd	bdd	ccc	ccd	cdd	ddd
        // Esas son las combinaciones disponibles, entonces ahora hago otra vez combinaciones pero de los resultados

        // Árbol con dos tipos de probabilidades a calcular:
        // LLamemos a 'a' uno de los sucesos necesarios y a 'b' el otro suceso necesario
        // Se hace la combinatoria de los otros tres elementos que no sean necesarios
        // Combinatoria de posición: Primero una combinatoria simple de bcd
        // y luego con los resultados pivotar la posición con a

        // Echar un ojo a Principio del palomar

        // Casos base
        // Qué pasa cuando solo hay un tipo de elemento (?)
    }
}
