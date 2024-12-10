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
        PokemonMetadata expectedBulbizarreMetadata = new PokemonMetadata(0, "Bulbizarre", 126, 126, 90);
        PokemonMetadata expectedAqualiMetadata = new PokemonMetadata(133, "Aquali", 186, 168, 260);

        when(pokemonMetadataProvider.getPokemonMetadata(0))
            .thenReturn(expectedBulbizarreMetadata);
        when(pokemonMetadataProvider.getPokemonMetadata(133))
            .thenReturn(expectedAqualiMetadata);

        PokemonMetadata actualBulbizarreMetadata = pokemonMetadataProvider.getPokemonMetadata(0);
        assertEquals(0, actualBulbizarreMetadata.getIndex());
        assertEquals("Bulbizarre", actualBulbizarreMetadata.getName());
        assertEquals(126, actualBulbizarreMetadata.getAttack());
        assertEquals(126, actualBulbizarreMetadata.getDefense());
        assertEquals(90, actualBulbizarreMetadata.getStamina());

        PokemonMetadata actualAqualiMetadata = pokemonMetadataProvider.getPokemonMetadata(133);
        assertEquals(133, actualAqualiMetadata.getIndex());
        assertEquals("Aquali", actualAqualiMetadata.getName());
        assertEquals(186, actualAqualiMetadata.getAttack());
        assertEquals(168, actualAqualiMetadata.getDefense());
        assertEquals(260, actualAqualiMetadata.getStamina());
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
