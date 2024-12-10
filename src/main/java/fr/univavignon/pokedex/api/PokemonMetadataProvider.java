package fr.univavignon.pokedex.api;

import java.util.HashMap;

public class PokemonMetadataProvider implements IPokemonMetadataProvider {
    private HashMap<Integer, PokemonMetadata> pokemonMetadatas = new HashMap<>();

    PokemonMetadataProvider() {
        pokemonMetadatas.put(0, new PokemonMetadata(0, "Bulbizarre", 126, 126, 90));
        pokemonMetadatas.put(133, new PokemonMetadata(133, "Aquali", 186, 168, 260));
    }

    @Override
    public PokemonMetadata getPokemonMetadata(int index) throws PokedexException {
        if (!pokemonMetadatas.containsKey(index))
            throw new PokedexException("No pokemon exists with index " + index);

        return pokemonMetadatas.get(index);
    }
}
