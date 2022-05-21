package com.mapGenerator.springboot.solutionGenerator.models;

import java.util.ArrayList;

public class SolutionGenerator {
    private ArrayList<ArrayList<String>> solutions; // soluciones diferentes, cada solucion es una lista de instrucciones


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
    // Llamemos a 'a' uno de los sucesos necesarios y a 'b' el otro suceso necesario
    // Se hace la combinatoria de los otros tres elementos que no sean necesarios
    // Combinatoria de posición: Primero una combinatoria simple de bcd
    // y luego con los resultados pivotar la posición con a

    // Echar un ojo a Principio del palomar

    // defaultBlocks: instrucciones por defecto en la unidad
    // tempIndex: indices de la combinacion que se esta formando
    // r: tamaño de la combinación que se va a generar
    // start & end:
    public static void candidateSolutionGenerator(ArrayList<ArrayList<String>> candidateSolutions,
                                                ArrayList<String> defaultBlocks,
                                                int tempIndex[], int index, int r, int start, int end) {
        // https://www.geeksforgeeks.org/combinations-with-repetitions/

        // Cuando el indice se convierte en el limite, es que se puede guardar la solucion
        if (index == r) {
            ArrayList<String> candidateSolution = new ArrayList<>();
            for (int i : tempIndex) {
                candidateSolution.add(defaultBlocks.get(i));
            }
            candidateSolutions.add(candidateSolution);
        }
        // Elegir todos los elementos posibles de uno en uno. Como se puede repetir no se tiene en cuenta si ha sido
        // elegido ya. Se procede a la recurrencia.
        for (int i = 0; i <= end; i++) {
            tempIndex[index] = i;
            candidateSolutionGenerator(candidateSolutions, defaultBlocks, tempIndex, index + 1, r, i, end);
        }
    }

    // Casos base
    //Combinaciones con repetición (n k):
    //
    //Si tengo menos elementos disponibles de los agrupados lo que voy a hacer es coger los elementos por orden,
    //y hacer combinaciones con ese elemento repitiendo de 1 a m veces
    //
    //1º caso crítico:
    //Una sola instrucción
    //n = 1  1 <= k < m siendo m un numero natural
    //Ejemplo de avance => A, k = 4
    //
    //2º caso normal:
    //Dos instrucciones -> n < k
    //n = 1  1 <= k < m siendo m un numero natural
    //Ejemplo de avance => A giro derecha => T, k = 4
    //AT de 4
    //TAAA ATAA AATA
    //TTTA TATT TTAT
    //AATT ATTA TTAA ATAT TATA
    //3º caso
    // n > r: de momento lo dejamos hasta que sea un problema
    public static void buildCombination(ArrayList<ArrayList<String>> candidateSolutions, ArrayList<String> defaultBlocks, int r) {
        ArrayList<String> newSetDefaultBlocks = defaultBlocks;
        for (int i = 0; i < defaultBlocks.size(); i++) {
            while (newSetDefaultBlocks.size() < r) {
                newSetDefaultBlocks.add(defaultBlocks.get(i));
            }
            int tempIndex[] = new int[r + 1];
            candidateSolutionGenerator(candidateSolutions, newSetDefaultBlocks, tempIndex, 0, r, 0, newSetDefaultBlocks.size() - 1);
            newSetDefaultBlocks = defaultBlocks;
        }
    }

    // defaultBlocks: instrucciones por defecto en la unidad
    // r: tamaño de la combinación que se va a generar
    public static void solutionGenerator(ArrayList<String> defaultBlocks, int r, ArrayList<String> additionalKeyBlocks) {
        // (?) r deberia pasar a la solucion parcial con su propio valor o se divide en partes para creaar subsoluciones

        // IMPORTANTE: que pasa si defaultBlocks.size() es mayor que r

        ArrayList<ArrayList<String>> candidateSolutions = new ArrayList<>();
        int tempIndex[] = new int[r + 1];
        if (defaultBlocks.size() < r) {
            buildCombination(candidateSolutions, defaultBlocks, r);
        } else {
            candidateSolutionGenerator(candidateSolutions, defaultBlocks, tempIndex, 0, r, 0, defaultBlocks.size() - 1);
        }
        // Ahora tengo las soluciones sin filtrar
        // Separar las que son candidatas por si mismas del resto
        // Para que sea candidata debe cumplir los requisitos
        // Reglas que se me van ocurriendo:
        // Que haya siempre un avance o un retroceder
        // Si hay tres giros seguidos, cambiarlo por el giro contrario
        // No puede haber un avanzar y un retroceder seguidos, da igual el orden
        // ^ Esta todavía no se mete en SolutionGeneratorRules porque puede haber teletransportes
        // Se puede meter como regla o se puede poner un teleport
        ArrayList<ArrayList<String>> discardedSolutions = new ArrayList<>();
        ArrayList<ArrayList<String>> solutions = new ArrayList<>();
        for (ArrayList<String> candidateSolution : candidateSolutions) {
            if(!isCandidate(candidateSolution, additionalKeyBlocks)){
                discardedSolutions.add(candidateSolution);
            }
            else {
                solutions.add(candidateSolution);
            }
        }
        // Que hago con las que no son candidatas, las deshecho o las reciclo
    }

    static boolean isCandidate(ArrayList<String> candidateSolution, ArrayList<String> additionalKeyBlocks) {
        String keyBlocks[] = {"advance", "backwards"};
        if(additionalKeyBlocks != null) {
            System.arraycopy(additionalKeyBlocks.toArray(), 0, keyBlocks, 0, additionalKeyBlocks.size());
        }
        int keBlocksReps[] = SolutionGeneratorRules.leastOneKeyBlock(keyBlocks, candidateSolution);
        for (int reps : keBlocksReps) {
            if (reps < 1) {
                return false;
            }
        }
        int prevSize = candidateSolution.size();
        candidateSolution = SolutionGeneratorRules.simplifyTurns(candidateSolution);
        if(candidateSolution.size() != prevSize) {
            return false;
        }
        if(!SolutionGeneratorRules.lastBlock(candidateSolution)) {
            return false;
        }
        return true;
    }
}
