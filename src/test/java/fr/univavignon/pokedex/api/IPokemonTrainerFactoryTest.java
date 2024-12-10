package fr.univavignon.pokedex.api;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class IPokemonTrainerFactoryTest {
    IPokemonTrainerFactory pokemonTrainerFactory;
    final IPokedexFactory pokedexFactory = new PokedexFactory();

    @BeforeEach
    void createPokemonTrainerFactory() {
        pokemonTrainerFactory = new PokemonTrainerFactory();
    }

    @Test
    void createTrainer() {
        PokemonTrainer actualTrainer = pokemonTrainerFactory.createTrainer("José", Team.INSTINCT, pokedexFactory);
        assertEquals("José", actualTrainer.getName());
        assertEquals(Team.INSTINCT, actualTrainer.getTeam());
        assertNotNull(actualTrainer.getPokedex());
    }
}
