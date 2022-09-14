package br.inatel.thisismeapi.entities.entitiesDTO;


import br.inatel.thisismeapi.entities.Character;

public class UserDtoOutput {

    String id;

    Character character;

    public UserDtoOutput() {
    }

    public UserDtoOutput(String id, Character character) {
        this.id = id;
        this.character = character;
    }

    public Character getCharacter() {
        return character;
    }

    public void setCharacter(Character character) {
        this.character = character;
    }





    public UserDtoOutput(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
