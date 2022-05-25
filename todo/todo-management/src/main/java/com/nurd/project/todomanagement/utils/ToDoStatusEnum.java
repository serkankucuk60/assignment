package com.nurd.project.todomanagement.utils;

public enum ToDoStatusEnum {

    OPEN("open"),
    DONE("done"),
    DELETED("deleted");

    private final String value;

    ToDoStatusEnum(String value) {
        this.value = value;
    }
}
