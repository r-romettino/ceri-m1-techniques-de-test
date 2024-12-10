package fr.univavignon.pokedex.api;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Pokedex implements IPokedex {
    private ArrayList<Pokemon> pokemonList = new ArrayList<>();
    private PokemonMetadataProvider pokemonMetadataProvider;
    private PokemonFactory pokemonFactory;

    public Pokedex(PokemonMetadataProvider pokemonMetadataProvider, PokemonFactory pokemonFactory) {
        this.pokemonMetadataProvider = pokemonMetadataProvider;
        this.pokemonFactory = pokemonFactory;
    }

    @Override
    public int size() {
        return pokemonList.size();
    }

    @Override
    public int addPokemon(Pokemon pokemon) {
        int index = pokemonList.size();

        pokemonList.add(pokemon);

        return index;
    }

    @Override
    public Pokemon getPokemon(int id) throws PokedexException {
        if (id < 0 || id >= pokemonList.size())
            throw new PokedexException("Pokemon not found");

        return pokemonList.get(id);
    }

    @Override
    public List<Pokemon> getPokemons() {
        return pokemonList;
    }

    @Override
    public List<Pokemon> getPokemons(Comparator<Pokemon> order) {
        ArrayList<Pokemon> sortedPokemonList = new ArrayList<>(pokemonList);
        sortedPokemonList.sort(order);

        return sortedPokemonList;
    }

    @Override
    public Pokemon createPokemon(int index, int cp, int hp, int dust, int candy) {
        return pokemonFactory.createPokemon(index, cp, hp, dust, candy);
    }

    @Override
    public PokemonMetadata getPokemonMetadata(int index) throws PokedexException {
        return pokemonMetadataProvider.getPokemonMetadata(index);
    }

    public PokemonFactory getPokemonFactory() {
        return pokemonFactory;
    }

    public PokemonMetadataProvider getPokemonMetadataProvider() {
        return pokemonMetadataProvider;
    }
}
