package br.inatel.thisismeapi.entities.entitiesDTO;


public class UserDtoOutput {

    String id;

    public UserDtoOutput() {
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
