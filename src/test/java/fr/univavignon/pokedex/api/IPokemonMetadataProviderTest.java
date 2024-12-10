package fr.univavignon.pokedex.api;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class IPokemonMetadataProviderTest {
    IPokemonMetadataProvider pokemonMetadataProvider;

    @BeforeEach
    void createPokemonMetadataProvider() {
        pokemonMetadataProvider = new PokemonMetadataProvider();
    }

    @Test
    void getMetadata() throws PokedexException {
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
        assertThrows(
            PokedexException.class,
            () -> pokemonMetadataProvider.getPokemonMetadata(255),
            "No pokemon exists with index 255"
        );
    }
}
