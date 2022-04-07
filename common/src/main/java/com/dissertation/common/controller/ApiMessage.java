package com.dissertation.common.controller;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
@SuperBuilder
public class ApiMessage {
    String field;
    String title;
}