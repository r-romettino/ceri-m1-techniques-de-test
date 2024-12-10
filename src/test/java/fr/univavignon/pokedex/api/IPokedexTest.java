package fr.univavignon.pokedex.api;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class IPokedexTest {
    IPokedex pokedex;
    PokemonMetadataProvider pokemonMetadataProvider = new PokemonMetadataProvider();
    PokemonFactory pokemonFactory = new PokemonFactory();
    final Pokemon exampleBulbizarre = new Pokemon(0, "Bulbizarre", 126, 126, 90, 613, 64, 4000, 4, 56d);
    final Pokemon exampleAquali = new Pokemon(133, "Aquali", 186, 168, 260, 2729, 202, 5000, 4, 100d);

    @BeforeEach
    void createPokedex() {
        pokedex = new Pokedex(pokemonMetadataProvider, pokemonFactory);
    }

    @Test
    void testAddPokemon() {
        assertEquals(Arrays.asList(), pokedex.getPokemons());

        pokedex.addPokemon(exampleBulbizarre);
        assertEquals(Arrays.asList(exampleBulbizarre), pokedex.getPokemons());

        pokedex.addPokemon(exampleAquali);
        assertEquals(Arrays.asList(exampleBulbizarre, exampleAquali), pokedex.getPokemons());
    }

    @Test
    void testAddPokemonReturnValue() {
        assertEquals(0, pokedex.addPokemon(exampleBulbizarre));
        assertEquals(1, pokedex.addPokemon(exampleAquali));
    }

    @Test
    void testGetNumberOfPokemon() {
        assertEquals( 0, pokedex.size());

        pokedex.addPokemon(exampleBulbizarre);

        assertEquals(1, pokedex.size());

        pokedex.addPokemon(exampleAquali);

        assertEquals(2, pokedex.size());
    }

    @Test
    void testGetNotExistingPokemonThrowsError() {
        assertThrows(
            PokedexException.class,
            () -> pokedex.getPokemon(0),
            "Pokemon not found"
        );
    }

    @Test
    void testGetPokemon() throws PokedexException {
        pokedex.addPokemon(exampleBulbizarre);
        pokedex.addPokemon(exampleAquali);

        assertEquals(exampleBulbizarre, pokedex.getPokemon(0));
        assertEquals(exampleAquali, pokedex.getPokemon(1));
    }

    @Test
    void testGetSortedPokemon() {
        pokedex.addPokemon(exampleBulbizarre);
        pokedex.addPokemon(exampleAquali);

        assertEquals(
                Arrays.asList(exampleAquali, exampleBulbizarre),
                pokedex.getPokemons(PokemonComparators.NAME)
        );
        assertEquals(
                Arrays.asList(exampleBulbizarre, exampleAquali),
                pokedex.getPokemons(PokemonComparators.INDEX)
        );
    }

    @Test
    void getOOBPokemon() {
        pokedex.addPokemon(exampleBulbizarre);
        pokedex.addPokemon(exampleAquali);

        assertThrows(
                PokedexException.class,
                () -> pokedex.getPokemon(-1),
                "Pokemon not found"
        );

        assertThrows(
                PokedexException.class,
                () -> pokedex.getPokemon(2),
                "Pokemon not found"
        );
    }

    @Test
    void createPokemon() {
        Pokemon actualPokemon = pokedex.createPokemon(0, 11, 12, 13, 14);
        assertEquals(0, actualPokemon.getIndex());
        assertEquals(11, actualPokemon.getCp());
        assertEquals(12, actualPokemon.getHp());
        assertEquals(13, actualPokemon.getDust());
        assertEquals(14, actualPokemon.getCandy());
    }

    @Test
    void getPokemonMetadata() throws PokedexException {
        PokemonMetadata pokemonMetadata = pokedex.getPokemonMetadata(0);
    }

    @Test
    void getUnknownPokemonMetadataThrows() {
        assertThrows(
                PokedexException.class,
                () -> pokedex.getPokemonMetadata(39),
                "Pokemon not found"
        );
    }
}
