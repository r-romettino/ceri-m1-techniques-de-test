package fr.univavignon.pokedex.api;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class IPokemonTrainerFactoryTest {
    IPokemonTrainerFactory pokemonTrainerFactory;
    final IPokedexFactory pokedexFactory = mock(IPokedexFactory.class);
    final IPokedex pokedex = mock(IPokedex.class);
    final PokemonTrainer expectedPokemonTrainer = new PokemonTrainer("José", Team.INSTINCT, pokedex);

    @BeforeEach
    void createPokemonTrainerFactory() {
        pokemonTrainerFactory = mock(IPokemonTrainerFactory.class);
    }

    @Test
    void createTrainer() {
        when(pokemonTrainerFactory.createTrainer("José", Team.INSTINCT, pokedexFactory))
            .thenReturn(expectedPokemonTrainer);

        PokemonTrainer actualTrainer = pokemonTrainerFactory.createTrainer("José", Team.INSTINCT, pokedexFactory);
        assertEquals("José", actualTrainer.getName());
        assertEquals(Team.INSTINCT, actualTrainer.getTeam());
        assertEquals(pokedex, actualTrainer.getPokedex());
    }
}
