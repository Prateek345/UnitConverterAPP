package com.example.unitconverter


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.unitconverter.ui.theme.UnitConverterTheme
import kotlin.math.roundToInt
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight


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
                   unitconverter()

                }
            }
        }
    }
}

@Composable
fun unitconverter(){
    var inputValue by remember{ mutableStateOf(" ") }
    var outputValue by remember{ mutableStateOf("") }
    var inputUnit by remember{ mutableStateOf("Meters") }
    var outputUnit by remember { mutableStateOf("Meters") }
    var iExpended by remember { mutableStateOf(false) }
    var oExpended by remember { mutableStateOf(false) }
    var conversionFactor = remember { mutableStateOf(1.00) }
    var oconversionFactor = remember { mutableStateOf(1.00) }

    val customTextStyle = TextStyle(
        fontFamily = FontFamily.Default,
        fontSize = 23.sp,
        fontWeight = FontWeight.Bold
    )



    fun convertunits(){
        var inputvaluedouble=inputValue.toDoubleOrNull()?: 0.0
        var result=(inputvaluedouble * conversionFactor.value *100.0/oconversionFactor.value).roundToInt()/100.0
        outputValue=result.toString()
    }

    Column (modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally){
        //UI items will be stacked here below each other
        Text(" ",style = customTextStyle)
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(value = inputValue, onValueChange ={ inputValue=it
                                                             convertunits() //what should happen when value in outlinedtextfield changes
        }, label = { Text("Enter Value")})
        Spacer(modifier = Modifier.height(16.dp))


        Row {
            //input box
            Box{
                //input button
                Button(onClick = {iExpended=true}) {
                    Text(text = inputUnit)
                    Icon(Icons.Default.ArrowDropDown,"Drop down")
                }
                DropdownMenu(expanded = iExpended, onDismissRequest = {iExpended=false}) {
                    DropdownMenuItem(text = { Text(text = "Centimeter")}, onClick = {
                        iExpended=false
                        inputUnit="Centimeter"
                        conversionFactor.value=0.01
                        convertunits()
                    })

                    DropdownMenuItem(text = { Text(text = "Meter")}, onClick = {
                        iExpended=false
                        inputUnit="Meter"
                        conversionFactor.value=1.00
                        convertunits()
                    })
                    DropdownMenuItem(text = { Text(text = "Inches")}, onClick = {
                        iExpended=false
                        inputUnit="Inches"
                        conversionFactor.value=0.0254
                        convertunits()
                    })
                    DropdownMenuItem(text = { Text(text = "Milimeter")}, onClick = {
                        iExpended=false
                        inputUnit="Milimeter"
                        conversionFactor.value=0.001
                        convertunits()
                    })
                }
            }

            Spacer(modifier = Modifier.width(16.dp))

            //output box
            Box{
                //output button
                Button(onClick = {oExpended=true}) {
                    Text(text = outputUnit)
                    Icon(Icons.Default.ArrowDropDown,"Drop down")
                }
                DropdownMenu(expanded = oExpended, onDismissRequest = {oExpended=false}) {
                    DropdownMenuItem(text = { Text(text = "Centimeter")}, onClick = {
                        oExpended=false
                        outputUnit="Centimeter"
                        oconversionFactor.value=0.01
                        convertunits()
                    })
                    DropdownMenuItem(text = { Text(text = "Meter")}, onClick = {
                        oExpended=false
                        outputUnit="Meter"
                        oconversionFactor.value=1.00
                        convertunits()
                    })
                    DropdownMenuItem(text = { Text(text = "Inches")}, onClick = {
                        oExpended=false
                        outputUnit="Inches"
                        oconversionFactor.value=0.0254
                        convertunits()
                    })
                    DropdownMenuItem(text = { Text(text = "Milimeter")}, onClick = {
                        oExpended=false
                        outputUnit="Milimeter"
                        oconversionFactor.value=0.001
                        convertunits()
                    })
                }
            }
            //UI items will be stacked next to each other
        }
        Spacer(modifier = Modifier.height(15.dp))
        Text("Result:$outputValue $outputUnit",
            style = MaterialTheme.typography.headlineSmall
            )
    }

}

@Preview(showBackground = true)
@Composable
fun unitconverterPreview(){
    unitconverter()
}
