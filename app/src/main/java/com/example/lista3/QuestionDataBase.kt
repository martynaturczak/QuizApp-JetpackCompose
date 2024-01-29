package com.example.lista3

data class Question(
    val question: String,
    val options: List<String>,
    val correctAnswer: String
)

object QuestionDataBase {
    val question = listOf(
        "Które miasto jest stolicą Francji?",
        "Które miasto jest stolicą Włoch?",
        "Które miasto jest stolicą Niemiec?",
        "Które miasto jest stolicą Hiszpanii?",
        "Które miasto jest stolicą Japonii?",
        "Które miasto jest stolicą Francji?",
        "Które miasto jest stolicą Włoch?",
        "Które miasto jest stolicą Niemiec?",
        "Które miasto jest stolicą Hiszpanii?",
        "Które miasto jest stolicą Japonii?"
    )

    val options = listOf(
        listOf("Paryż", "Rzym", "Berlin", "Madryt"),
        listOf("Rzym", "Mediolan", "Madryt", "Łódź"),
        listOf("Paryż", "Rzym", "Berlin", "Madryt"),
        listOf("Paryż", "Rzym", "Berlin", "Madryt"),
        listOf("Tokio", "Osaka", "Kioto", "Hiroshima"),
        listOf("Paryż", "Rzym", "Berlin", "Madryt"),
        listOf("Łódź", "Rzym", "Mediolan", "Madryt"),
        listOf("Paryż", "Rzym", "Berlin", "Madryt"),
        listOf("Paryż", "Rzym", "Berlin", "Madryt"),
        listOf("Tokio", "Osaka", "Kioto", "Hiroshima")
    )

    val correctAnswer = listOf(
        "Paryż",
        "Rzym",
        "Berlin",
        "Madryt",
        "Tokio",
        "Paryż",
        "Rzym",
        "Berlin",
        "Madryt",
        "Tokio"
    )

    fun createQuestions(): List<Question> {
        val questionTexts = question
        val answers = options
        val correctAnswers = correctAnswer

        return List(questionTexts.size) { index ->
            Question(
                question = questionTexts[index],
                options = answers[index],
                correctAnswer = correctAnswers[index]
            )
        }
    }
}
