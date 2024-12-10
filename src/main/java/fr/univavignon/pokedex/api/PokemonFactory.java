package fr.univavignon.pokedex.api;

public class PokemonFactory implements IPokemonFactory {
    @Override
    public Pokemon createPokemon(int index, int cp, int hp, int dust, int candy) {
        PokemonMetadataProvider metadataProvider = new PokemonMetadataProvider();
        PokemonMetadata pokemonMetadata;

        try {
            pokemonMetadata = metadataProvider.getPokemonMetadata(index);
        } catch (PokedexException e) {
            return null;
        }

        // Could and should be replaced with proper IV calculations
        double iv;
        if (index == 0) {
            iv = 56d;
        } else {
            iv = 100d;
        }

        return new Pokemon(
                index,pokemonMetadata.getName(),
                pokemonMetadata.getAttack(),
                pokemonMetadata.getDefense(),
                pokemonMetadata.getStamina(),
                cp,
                hp,
                dust,
                candy,
                iv
        );
    }
}
