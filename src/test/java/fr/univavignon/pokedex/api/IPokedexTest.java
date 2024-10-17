package fr.univavignon.pokedex.api;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class IPokedexTest {
    IPokedex pokedex;
    final Pokemon examplePokemon1 = new Pokemon(0, "Bulbizarre", 126, 126, 90, 613, 64, 4000, 4, 56d);
    final Pokemon examplePokemon2 = new Pokemon(133, "Aquali", 186, 168, 260, 2729, 202, 5000, 4, 100d);

    @BeforeEach
    void createPokedex() {
        pokedex = mock(IPokedex.class);
    }

    @Test
    void testAddPokemon() {
        when(pokedex.getPokemons())
            .thenReturn(Arrays.asList())
            .thenReturn(Arrays.asList(examplePokemon1))
            .thenReturn(Arrays.asList(examplePokemon1, examplePokemon2));

        assertEquals(Arrays.asList(), pokedex.getPokemons());

        pokedex.addPokemon(examplePokemon1);
        assertEquals(Arrays.asList(examplePokemon1), pokedex.getPokemons());

        pokedex.addPokemon(examplePokemon2);
        assertEquals(Arrays.asList(examplePokemon1, examplePokemon2), pokedex.getPokemons());
    }

    @Test
    void testAddPokemonReturnValue() {
        when(pokedex.addPokemon(examplePokemon1)).thenReturn(0);
        when(pokedex.addPokemon(examplePokemon2)).thenReturn(1);

        assertEquals(0, pokedex.addPokemon(examplePokemon1));
        assertEquals(1, pokedex.addPokemon(examplePokemon2));
    }

    @Test
    void testGetNumberOfPokemon() {
        when(pokedex.size())
            .thenReturn(0)
            .thenReturn(1)
            .thenReturn(2);

        assertEquals( 0, pokedex.size());

        pokedex.addPokemon(examplePokemon1);

        assertEquals(1, pokedex.size());

        pokedex.addPokemon(examplePokemon2);

        assertEquals(2, pokedex.size());
    }

    @Test
    void testGetNotExistingPokemonThrowsError() throws PokedexException {
        when(pokedex.getPokemon(0)).thenThrow(new PokedexException("Pokemon not found"));

        assertThrows(
            PokedexException.class,
            () -> {
                pokedex.getPokemon(0);
            },
            "Pokemon not found"
        );
    }

    @Test
    void testGetPokemon() throws PokedexException {
        when(pokedex.getPokemon(0)).thenReturn(examplePokemon1);
        when(pokedex.getPokemon(1)).thenReturn(examplePokemon2);

        assertEquals(examplePokemon1, pokedex.getPokemon(0));
        assertEquals(examplePokemon2, pokedex.getPokemon(1));
    }

    @Test
    void testGetSortedPokemon() throws PokedexException {
        when(pokedex.getPokemons(PokemonComparators.NAME)).thenReturn(
                Arrays.asList(examplePokemon2, examplePokemon1)
        );
        when(pokedex.getPokemons(PokemonComparators.INDEX)).thenReturn(
                Arrays.asList(examplePokemon1, examplePokemon2)
        );

        assertEquals(
                Arrays.asList(examplePokemon2, examplePokemon1),
                pokedex.getPokemons(PokemonComparators.NAME)
        );
        assertEquals(
                Arrays.asList(examplePokemon1, examplePokemon2),
                pokedex.getPokemons(PokemonComparators.INDEX)
        );
    }
}
