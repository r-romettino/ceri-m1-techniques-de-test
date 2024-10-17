package fr.univavignon.pokedex.api;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class IPokedexTest {
    IPokedex pokedex;
    final Pokemon exampleBulbizarre = new Pokemon(0, "Bulbizarre", 126, 126, 90, 613, 64, 4000, 4, 56d);
    final Pokemon exampleAquali = new Pokemon(133, "Aquali", 186, 168, 260, 2729, 202, 5000, 4, 100d);

    @BeforeEach
    void createPokedex() {
        pokedex = mock(IPokedex.class);
    }

    @Test
    void testAddPokemon() {
        when(pokedex.getPokemons())
            .thenReturn(Arrays.asList())
            .thenReturn(Arrays.asList(exampleBulbizarre))
            .thenReturn(Arrays.asList(exampleBulbizarre, exampleAquali));

        assertEquals(Arrays.asList(), pokedex.getPokemons());

        pokedex.addPokemon(exampleBulbizarre);
        assertEquals(Arrays.asList(exampleBulbizarre), pokedex.getPokemons());

        pokedex.addPokemon(exampleAquali);
        assertEquals(Arrays.asList(exampleBulbizarre, exampleAquali), pokedex.getPokemons());
    }

    @Test
    void testAddPokemonReturnValue() {
        when(pokedex.addPokemon(exampleBulbizarre)).thenReturn(0);
        when(pokedex.addPokemon(exampleAquali)).thenReturn(1);

        assertEquals(0, pokedex.addPokemon(exampleBulbizarre));
        assertEquals(1, pokedex.addPokemon(exampleAquali));
    }

    @Test
    void testGetNumberOfPokemon() {
        when(pokedex.size())
            .thenReturn(0)
            .thenReturn(1)
            .thenReturn(2);

        assertEquals( 0, pokedex.size());

        pokedex.addPokemon(exampleBulbizarre);

        assertEquals(1, pokedex.size());

        pokedex.addPokemon(exampleAquali);

        assertEquals(2, pokedex.size());
    }

    @Test
    void testGetNotExistingPokemonThrowsError() throws PokedexException {
        when(pokedex.getPokemon(0)).thenThrow(new PokedexException("Pokemon not found"));

        assertThrows(
            PokedexException.class,
            () -> pokedex.getPokemon(0),
            "Pokemon not found"
        );
    }

    @Test
    void testGetPokemon() throws PokedexException {
        when(pokedex.getPokemon(0)).thenReturn(exampleBulbizarre);
        when(pokedex.getPokemon(1)).thenReturn(exampleAquali);

        assertEquals(exampleBulbizarre, pokedex.getPokemon(0));
        assertEquals(exampleAquali, pokedex.getPokemon(1));
    }

    @Test
    void testGetSortedPokemon() {
        when(pokedex.getPokemons(PokemonComparators.NAME)).thenReturn(
                Arrays.asList(exampleAquali, exampleBulbizarre)
        );
        when(pokedex.getPokemons(PokemonComparators.INDEX)).thenReturn(
                Arrays.asList(exampleBulbizarre, exampleAquali)
        );

        assertEquals(
                Arrays.asList(exampleAquali, exampleBulbizarre),
                pokedex.getPokemons(PokemonComparators.NAME)
        );
        assertEquals(
                Arrays.asList(exampleBulbizarre, exampleAquali),
                pokedex.getPokemons(PokemonComparators.INDEX)
        );
    }
}
