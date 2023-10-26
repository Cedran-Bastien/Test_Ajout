package org.iut.tdd;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
* La classe ListesNumeriques permet d'ajouter 2 entiers représentés en utilisant des listes de chiffres.
* 
*/
public class ListesNumeriques {

    /**
     * <p>La méthode considère 2 entiers qui sont représentés en utilisant des listes de 
     * chiffres, respectivement <code>nb1</code> et <code>nb2</code>.
     * La méthode ajoute ces 2 entiers et renvoie le résultat comme une liste de chiffres.
     * </p>
     * <p>Par exemple, si on veut ajouter les entiers 142 et 13, on doit créer une liste 
     * (nb1) avec trois éléments [1,4,2] et une liste (nb2) avec deux éléments [1,3].
     * Comme 142+13 = 155, le programme doit donc produire une liste avec trois éléments [1,5,5]
     * </p>
     * [1,4,2] + [1,3] = [1,5,5]
     * <p>
     * Chaque élément des listes nb1 et nb2 doit être compris entre 0 et 9.
     * Une exception <code>IllegalArgumentException</code> est levée si cette condition préalable n'est 
     * pas remplie.
     * </p>
     * 
     * @param nb1  liste qui contient le premier entier. Null renvoie <code>null</code>, vide signifie 0
     * @param nb2  liste qui contient le deuxième entier. Null renvoie <code>null</code>, vide signifie 0
     * @return la somme de nb1 et nb2 représentée comme une liste de chiffres
     */
    public List<Integer> ajoute(List<Integer> nb1, List<Integer> nb2) {

        if (nb1 == null || nb2 == null){
            return null;
        }

        if (nb1.isEmpty()){
            return nb2;
        }else if (nb2.isEmpty()){
            return nb1;
        }

        if (!nb1.stream().allMatch(item -> item >= 0 && item <= 9) || !nb2.stream().allMatch(item -> item >= 0 && item <= 9)){
            throw new IllegalArgumentException();
        }


        List<Integer> res = new ArrayList<>();
        int retenue = 0;
        for (int i = 1 ; i <= Math.max(nb1.size(), nb2.size()); i++){
            int n1 = nb1.size() - i >= 0 ? nb1.get( nb1.size() - i) : 0;
            int n2 = nb2.size() - i >= 0 ? nb2.get(nb2.size() - i ) : 0;
            int sommeI = n1 + n2 + retenue;

            retenue = sommeI / 10 ;

            res.add(0, sommeI%10);
        }

        if (retenue != 0 )
            res.add(0,retenue);

        return res;
    }

}
