package fr.univavignon.pokedex.api;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class IPokemonFactoryTest {
    IPokemonFactory pokemonFactory;
    final Pokemon exampleBulbizarre = new Pokemon(0, "Bulbizarre", 126, 126, 90, 613, 64, 4000, 4, 56d);
    final Pokemon exampleAquali = new Pokemon(133, "Aquali", 186, 168, 260, 2729, 202, 5000, 4, 100d);

    @BeforeEach
    void createPokemonFactory() {
        pokemonFactory = mock(IPokemonFactory.class);
    }

    @Test
    void createPokemon() {
        when(pokemonFactory.createPokemon(0, 613, 64, 4000, 4))
            .thenReturn(exampleBulbizarre);
        when(pokemonFactory.createPokemon(133, 2729, 202, 5000, 4))
            .thenReturn(exampleAquali);

        assertEquals(exampleBulbizarre, pokemonFactory.createPokemon(0, 613, 64, 4000, 4));
        assertEquals(exampleAquali, pokemonFactory.createPokemon(133, 2729, 202, 5000, 4));
    }
}
