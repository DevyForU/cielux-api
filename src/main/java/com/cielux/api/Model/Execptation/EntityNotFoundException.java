package com.cielux.api.Model.Execptation;

public class EntityNotFoundException extends RuntimeException {
    public EntityNotFoundException(String entityName) {
        super(entityName + " not found");
    }
}
