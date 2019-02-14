package ru.scrumtrek.uiserver.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
public class Line implements Serializable {
    @Getter private int lineId;
    @Getter private String lineChars;
}
