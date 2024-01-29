package com.example.lista3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.graphics.*
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.*
import com.example.lista3.ui.theme.Lista3Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Lista3Theme {
                MyApp()
            }
        }
    }
}
@Composable
fun MyApp() {
    var progress by remember { mutableFloatStateOf(1.0f) }
    var currentQuestionNumber by remember { mutableIntStateOf(1) }
    var selectedOption by remember { mutableStateOf<String?>(null) }
    var score by remember { mutableIntStateOf(0) }
    val questions = QuestionDataBase.createQuestions()
    var currentQuestion by remember { mutableStateOf(questions.firstOrNull()) }
    var quizFinished by remember { mutableStateOf(false) }

    if (!quizFinished) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(vertical = 10.dp, horizontal = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            currentQuestion?.let { question ->
                Text(
                    text = "Pytanie $currentQuestionNumber/10",
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .padding(top = 26.dp)
                )
                LinearProgressIndicator(
                    progress = progress / 10,
                    color = Color.Green,
                    modifier = Modifier
                        .padding(top = 40.dp)
                )
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(140.dp)
                        .padding(top = 40.dp)
                        .background(Color.Gray),
                    shape = RectangleShape
                ) {
                    Text(
                        text = question.question,
                        fontSize = 20.sp,
                        modifier = Modifier
                            .padding(top = 20.dp)
                    )
                }
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(380.dp)
                        .padding(top = 30.dp)
                        .background(Color.White),
                    shape = RoundedCornerShape(10.dp),
                ) {
                    Column (){
                        question.options.forEach { option ->
                            Row (
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = 30.dp, start = 16.dp, end = 16.dp)
                                    .background(
                                        Color.LightGray,
                                        RoundedCornerShape(10.dp))
                            ){
                                RadioButton(
                                    selected = selectedOption == option,
                                    onClick = { selectedOption = option },
                                    modifier = Modifier.clickable { selectedOption = option }
                                )
                                Text(
                                    text = "$option",
                                    fontSize = 20.sp,
                                    fontWeight = FontWeight.Bold,
                                    modifier = Modifier
                                        .align(Alignment.CenterVertically)
                                )
                            }
                        }
                    }
                }
                Button(
                    onClick = {
                        if (selectedOption != null) {
                            if (question.correctAnswer == selectedOption)
                                score += 10
                        }
                        if (currentQuestionNumber < 10) {
                            progress += 1.0f
                            currentQuestionNumber += 1
                            selectedOption = null
                            currentQuestion = questions.getOrNull(currentQuestionNumber - 1)
                        }
                        else
                            quizFinished = true
                    },
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp)
                        .padding(top = 10.dp)
                ) {
                    Text(
                        text = "Następne",
                        fontSize = 20.sp,
                        color = Color.Black
                    )
                }
            }
        }
    }
    else {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(vertical = 16.dp, horizontal = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (score > 60) {
                Text(
                    text = "Gratulacje!",
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(top = 100.dp)
                )
            } else {
                Text(
                    text = "Koniec quizu",
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(top = 100.dp)
                )
            }
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(520.dp)
                    .padding(top = 50.dp)
                    .background(Color.White),
                shape = RoundedCornerShape(10.dp),
            ) {
                Text(
                    text = "Zdobyłeś $score pkt",
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .padding(top = 200.dp, bottom = 200.dp, start = 60.dp, end = 60.dp)
                        .wrapContentSize()
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MyAppPreview() {
    Lista3Theme {
        MyApp()
    }
}