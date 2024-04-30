package com.own_world.jetpackdiceapp

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.w3c.dom.Text
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Greeting()
        }
    }
}


@Composable
fun Greeting(modifier: Modifier = Modifier) {
    var result1 by remember {
        mutableStateOf(1)
    }

    var result2 by remember {
        mutableStateOf(2)
    }
    var dicesum by remember {
        mutableStateOf(0)
    }
    var target by remember {
        mutableStateOf(0)
    }
    var status by remember {
        mutableStateOf("")
    }
    var btnText by remember {
        mutableStateOf("Roll")
    }
    var isGameOver by remember {
        mutableStateOf(false)
    }

    fun reset() {
        result1 = 1
        result2 = 2
        dicesum = 0
        target = 0
        status = ""
        btnText = "Roll"
        isGameOver = false
    }

    fun rollTheDice(){
        result1 = (1..6).random()
        result2 = (1..6).random()
        dicesum = result1 + result2

        if (target > 0) {
            if (dicesum == target) {
                status = "You win"
                isGameOver = true
            } else {
                status = "You lose"
                isGameOver = true
            }
        } else if (dicesum == 7 || dicesum == 11) {
            status = "You win"
            isGameOver = true
        } else if (dicesum == 2 || dicesum == 3 || dicesum == 12) {
            status = "You lose"
            isGameOver = true
        } else {
            target = dicesum
        }
        if (isGameOver== true){
            btnText = "Reset"
        }
    }


    val imageResourse1 = ImagerResourse(result1)
    val imageResourse2 = ImagerResourse(result2)

    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Row {
            Image(
                painter = painterResource(id = imageResourse1), contentDescription = null,
                modifier = Modifier.size(100.dp)
            )
            Image(
                painter = painterResource(id = imageResourse2), contentDescription = null,
                modifier = Modifier.size(100.dp)
            )
        }
        Text(text = "Sum of dices is: $dicesum")
        Spacer(modifier = Modifier.height(20.dp))

        Text(text = status, fontSize = 25.sp)

        if (target>0) {
            Text(text ="Target: $target", fontSize = 25.sp)
        }



        Button(onClick = {
            if (isGameOver){
                reset()
            }
            else{
                rollTheDice()
            }

        }) {
            Text(text = btnText)
        }
    }
}


fun ImagerResourse(resut: Int): Int {
    return when (resut) {
        1 -> R.drawable.dice_1
        2 -> R.drawable.dice_2
        3 -> R.drawable.dice_3
        4 -> R.drawable.dice_4
        5 -> R.drawable.dice_5
        else -> R.drawable.dice_6


    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Greeting()
}






