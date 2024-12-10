package fr.univavignon.pokedex.api;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class IPokemonFactoryTest {
    IPokemonFactory pokemonFactory;
    final Pokemon expectedBulbizarre = new Pokemon(0, "Bulbizarre", 126, 126, 90, 613, 64, 4000, 4, 56d);
    final Pokemon expectedAquali = new Pokemon(133, "Aquali", 186, 168, 260, 2729, 202, 5000, 4, 100d);

    @BeforeEach
    void createPokemonFactory() {
        pokemonFactory = mock(IPokemonFactory.class);
    }

    @Test
    void createPokemon() {
        when(pokemonFactory.createPokemon(0, 613, 64, 4000, 4))
            .thenReturn(expectedBulbizarre);
        when(pokemonFactory.createPokemon(133, 2729, 202, 5000, 4))
            .thenReturn(expectedAquali);

        Pokemon actualBulbizarre = pokemonFactory.createPokemon(0, 613, 64, 4000, 4);
        assertEquals(613, actualBulbizarre.getCp());
        assertEquals(64, actualBulbizarre.getHp());
        assertEquals(4000, actualBulbizarre.getDust());
        assertEquals(4, actualBulbizarre.getCandy());
        assertEquals(56d, actualBulbizarre.getIv());

        Pokemon actualAquali = pokemonFactory.createPokemon(133, 2729, 202, 5000, 4);
        assertEquals(2729, actualAquali.getCp());
        assertEquals(202, actualAquali.getHp());
        assertEquals(5000, actualAquali.getDust());
        assertEquals(4, actualAquali.getCandy());
        assertEquals(100d, actualAquali.getIv());
    }
}
