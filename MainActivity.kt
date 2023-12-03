package com.example.unitconverter

import android.graphics.drawable.Icon
import android.os.Bundle
import android.text.style.BackgroundColorSpan
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.materialIcon
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.currentCompositionLocalContext
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ModifierLocalBeyondBoundsLayout
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.unitconverter.ui.theme.UnitConverterTheme
import kotlin.math.roundToInt

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UnitConverterTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background

                ) {

                    unitConverter()
                }
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun unitConverter() {

    var inputValue by remember{ mutableStateOf("") }
    var outputVlue by remember { mutableStateOf("") }
    var inputUnit by remember { mutableStateOf("Meters") }
    var outputUnit by remember { mutableStateOf("Meters") }
    var iExpand by remember { mutableStateOf(false) }
    var oExpand by remember { mutableStateOf(false) }
    val factor = remember { mutableStateOf(1.00) }
    val ofactor = remember { mutableStateOf(1.00) }



    fun convertUnits (){
        //?: elvis operator
        val inputValueDouble = inputValue.toDoubleOrNull() ?: 0.0
        val result = (inputValueDouble * factor.value*100.0/ofactor.value).roundToInt()/100.0
        outputVlue = result.toString()
    }




    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Unit Converter", fontFamily = FontFamily.SansSerif
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(value = inputValue, onValueChange = {
            inputValue = it
            convertUnits()
        }, label = { Text(text = "Enter Value")})
        Spacer(modifier = Modifier.height(16.dp))
        Row {
            //input Box
            Box {
                //input Button
                Button(onClick = { iExpand = true }) {
                    Text(text = inputUnit)
                    Icon(
                        Icons.Default.ArrowDropDown,
                        contentDescription = "Arrow Down"
                    )
                }
                DropdownMenu(expanded = iExpand, onDismissRequest = { iExpand = false }) {
                    DropdownMenuItem(text = { Text(text = "Centimeters") }, onClick = {
                        iExpand = false
                        inputUnit = "Centimeters"
                        factor.value = 0.01
                        convertUnits()
                    })
                    DropdownMenuItem(text = { Text(text = "Meters") }, onClick = {
                        iExpand = false
                        inputUnit = "Meters"
                        factor.value = 1.00
                        convertUnits()
                    })
                    DropdownMenuItem(text = { Text(text = "Feet") }, onClick = {
                        iExpand = false
                        inputUnit = "Feet"
                        factor.value = 0.3048
                        convertUnits()
                    })
                    DropdownMenuItem(text = { Text(text = "Milimeters") }, onClick = { iExpand = false
                        inputUnit = "Milimeters"
                        factor.value = 0.001
                        convertUnits()
                    })
                }


            }
            Spacer(modifier = Modifier.width(16.dp))
            //output box
            Box {
                //output Button
                Button(onClick = { oExpand = true }) {
                    Text(text = outputUnit)
                    Icon(
                        Icons.Default.ArrowDropDown,
                        contentDescription = "Arrow Down"
                    )
                }
                DropdownMenu(expanded = oExpand, onDismissRequest = { oExpand = false }) {
                    DropdownMenuItem(text = { Text(text = "Centimeters") }, onClick = {
                        oExpand = false
                        outputUnit = "Centimeters"
                        ofactor.value = 0.01
                        convertUnits()
                    })
                    DropdownMenuItem(text = { Text(text = "Meters") }, onClick = {
                        oExpand = false
                        outputUnit = "Meters"
                        ofactor.value = 1.00
                        convertUnits()
                    })
                    DropdownMenuItem(text = { Text(text = "Feet") }, onClick = {
                        oExpand = false
                        outputUnit = "Feet"
                        ofactor.value = 0.3048
                        convertUnits()
                    })
                    DropdownMenuItem(text = { Text(text = "Milimeters") }, onClick = { oExpand = false
                        outputUnit = "Milimeters"
                        ofactor.value = 0.001
                        convertUnits()})
                }
            }

        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Result:${outputVlue}${outputUnit}",
            style = MaterialTheme.typography.headlineMedium
        )
        Spacer(modifier = Modifier.height(150.dp))
        Text(text = "Design by:Sahil", fontFamily = FontFamily.Serif)
    }
}
@Preview(showBackground = true)
@Composable
fun unitConverterPreview(){
    unitConverter()
}