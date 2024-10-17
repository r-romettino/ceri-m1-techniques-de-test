package fr.univavignon.pokedex.api;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class IPokemonMetadataProviderTest {
    IPokemonMetadataProvider pokemonMetadataProvider;

    @BeforeEach
    void createPokemonMetadataProvider() {
        pokemonMetadataProvider = mock(IPokemonMetadataProvider.class);
    }

    @Test
    void getMetadata() throws PokedexException {
        PokemonMetadata bulbizarreMetadata = new PokemonMetadata(0, "Bulbizarre", 126, 126, 90);
        PokemonMetadata aqualiMetadata = new PokemonMetadata(133, "Aquali", 186, 168, 260);

        when(pokemonMetadataProvider.getPokemonMetadata(0))
            .thenReturn(bulbizarreMetadata);
        when(pokemonMetadataProvider.getPokemonMetadata(133))
            .thenReturn(aqualiMetadata);

        assertEquals(bulbizarreMetadata, pokemonMetadataProvider.getPokemonMetadata(0));
        assertEquals(aqualiMetadata, pokemonMetadataProvider.getPokemonMetadata(133));
    }

    @Test
    void getNotExistingPokemonMetadataThrowsException() throws PokedexException {
        when(pokemonMetadataProvider.getPokemonMetadata(255))
            .thenThrow(new PokedexException("No pokemon exists with index 255"));

        assertThrows(
            PokedexException.class,
            () -> pokemonMetadataProvider.getPokemonMetadata(255),
            "No pokemon exists with index 255"
        );
    }
}
