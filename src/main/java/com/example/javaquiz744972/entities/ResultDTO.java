package com.example.javaquiz744972.entities;

import java.util.List;

public record ResultDTO(String quizId,
                        String name,
                        List<Result> results) {


}
