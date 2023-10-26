package org.iut.tdd;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;
import static org.assertj.core.api.Assertions.assertThat;

public class ListesNumeriquesTest {
    ListesNumeriques listesNumeriques;

    @BeforeEach
    void setEnvironment(){
        listesNumeriques = new ListesNumeriques();
    }

    @ParameterizedTest
    @MethodSource("gen")
    void testAjouteResult(List<Integer> x , List<Integer> y, List<Integer> result){
        assertThat(listesNumeriques.ajoute(x,y)).isEqualTo(result);
    }

    public static Stream<Arguments> gen(){
        return Stream.of(
                Arguments.of(Lists.newArrayList(1,4,3), Lists.newArrayList(1,3), Lists.newArrayList(1,5,6)),
                Arguments.of(Lists.newArrayList(1,4), Lists.newArrayList(1,6), Lists.newArrayList(3,0)),
                Arguments.of(Lists.newArrayList(9,9), Lists.newArrayList(1), Lists.newArrayList(1,0,0)),
                Arguments.of(Lists.newArrayList(1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1), Lists.newArrayList(1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1), Lists.newArrayList(2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2))
                );
    }

    @ParameterizedTest
    @MethodSource("genNull")
    void testAjouteNull(List<Integer> x, List<Integer> y){
        assertThat(listesNumeriques.ajoute(x,y)).isNull();
    }

    public static Stream<Arguments> genNull(){
        return Stream.of(
                Arguments.of(null, Lists.newArrayList(1,3)),
                Arguments.of(Lists.newArrayList(1,4), null)
        );
    }

    @ParameterizedTest
    @MethodSource("gen0")
    void testArg0(List<Integer> x, List<Integer> y, List<Integer> attemped){
        assertThat(listesNumeriques.ajoute(x,y)).isSameAs(attemped);
    }

    public static Stream<Arguments> gen0(){
        List<Integer> x = Lists.newArrayList(1, 3);
        return Stream.of(
                Arguments.of(Lists.newArrayList(), x, x),
                Arguments.of(x, Lists.newArrayList(), x)
        );
    }


    @ParameterizedTest
    @MethodSource("genGreater9")
    void testArg1Greater9TrownExeption(List<Integer> arg){
        assertThatThrownBy(() -> listesNumeriques.ajoute(arg, Lists.newArrayList(1,5,4))).isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @MethodSource("genGreater9")
    void testArg2Greater9TrownExeption(List<Integer> arg){
        assertThatThrownBy(() -> listesNumeriques.ajoute( Lists.newArrayList(1,5,4), arg)).isInstanceOf(IllegalArgumentException.class);
    }

    public static Stream<Arguments> genGreater9(){
        return Stream.of(
                Arguments.of(Lists.newArrayList(1,10,3) ),
                Arguments.of(Lists.newArrayList(1,5,80)),
                Arguments.of(Lists.newArrayList(54,5,4))
        );
    }

    @ParameterizedTest
    @MethodSource("genLess0")
    void testLess0TrownExeption(List<Integer> arg){
        assertThatThrownBy(() -> listesNumeriques.ajoute(arg, Lists.newArrayList(1,5,4))).isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> listesNumeriques.ajoute( Lists.newArrayList(1,5,4), arg)).isInstanceOf(IllegalArgumentException.class);
    }

    public static Stream<Arguments> genLess0(){
        return Stream.of(
                Arguments.of(Lists.newArrayList(1,-1,3) ),
                Arguments.of(Lists.newArrayList(-10,5,8))
        );
    }
}
