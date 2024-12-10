package fr.univavignon.pokedex.api;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class IPokedexFactoryTest {
    IPokedexFactory pokedexFactory;
    final IPokemonMetadataProvider pokemonMetadataProvider = new PokemonMetadataProvider();
    final IPokemonFactory pokemonFactory = new PokemonFactory();

    @BeforeEach
    void createPokedexFactory() {
        pokedexFactory = new PokedexFactory();
    }

    @Test
    void createPokedex() {
        IPokedex pokedex = pokedexFactory.createPokedex(pokemonMetadataProvider, pokemonFactory);

        assertNotNull(pokedex);
    }
}
