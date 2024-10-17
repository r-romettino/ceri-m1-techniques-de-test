package fr.univavignon.pokedex.api;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class IPokedexFactoryTest {
    IPokedexFactory pokedexFactory;
    final IPokemonMetadataProvider pokemonMetadataProvider = mock(IPokemonMetadataProvider.class);
    final IPokemonFactory pokemonFactory = mock(IPokemonFactory.class);
    final IPokedex pokedex = mock(IPokedex.class);

    @BeforeEach
    void createPokedexFactory() {
        pokedexFactory = mock(IPokedexFactory.class);
    }

    @Test
    void createPokedex() {
        when(pokedexFactory.createPokedex(pokemonMetadataProvider, pokemonFactory))
            .thenReturn(pokedex);

        assertEquals(pokedex, pokedexFactory.createPokedex(pokemonMetadataProvider, pokemonFactory));
    }
}
