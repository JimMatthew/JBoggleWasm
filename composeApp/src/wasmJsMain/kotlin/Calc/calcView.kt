package Calc

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import calcController


@Composable
fun calcDisplay(calc : calcController) {
    var ans by remember { mutableStateOf("0") }
    var run by remember { mutableStateOf("") }
    val materialBlue700= Color(0xFF1976D2)
    val radioOptions = listOf("dec","hex","bin")
    var selectedOption by remember { mutableStateOf(radioOptions[0]) }
    val checkedState = remember { mutableStateOf(false) }
    val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Open))
    Column(horizontalAlignment = Alignment.CenterHorizontally) {


        Row{
            Column {

                Row {
                    Text(
                        text = "Answer ",
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                        modifier = Modifier.padding(15.dp)
                    )
                    TextField(
                        value = ans,
                        onValueChange = { },
                        label = { Text("") },
                        maxLines = 2,
                        textStyle = TextStyle(color = Color.Blue, fontWeight = FontWeight.Bold),
                        modifier = Modifier.padding(20.dp),
                        readOnly = true
                    )

                }

                Row {
                    Text(
                        text = "Calculation ",
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                        modifier = Modifier.padding(15.dp)
                    )
                    TextField(
                        value = run,
                        onValueChange = { },
                        label = { Text("") },
                        maxLines = 1,
                        textStyle = TextStyle(color = Color.Blue, fontWeight = FontWeight.Bold),
                        modifier = Modifier.padding(20.dp),
                        readOnly = true
                    )


                }
            }
            Column {
                Row {
                    Column(
                        modifier = Modifier.padding(1.dp),
                        verticalArrangement = Arrangement.spacedBy(1.dp)
                    ) {
                        radioOptions.forEach { fruitName ->
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                RadioButton(
                                    selected = (fruitName == selectedOption),
                                    onClick = {
                                        selectedOption = fruitName
                                        changeBase(selectedOption, calc)
                                        ans = calc.getFormattedDisplay
                                    }
                                )
                                Text(
                                    text = fruitName,
                                    modifier = Modifier.padding(start = 1.dp)
                                )
                            }
                        }
                    }
                }
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Checkbox(
                        checked = checkedState.value,
                        onCheckedChange = {
                            checkedState.value = it
                            calc.useScientificNotation(checkedState.value)
                            ans = calc.getFormattedDisplay
                        }
                    )
                    Text(
                        text = "SN",
                        modifier = Modifier.padding(start = 1.dp)
                    )
                }
            }


        }
        Column(horizontalAlignment = Alignment.CenterHorizontally) {


            Row(
                verticalAlignment = Alignment.Bottom,
                modifier = Modifier
                    .padding(top = 40.dp)
            ){}

            if (selectedOption == "hex") {
                Row(){
                    Button(
                        colors = ButtonDefaults.buttonColors(Color(0xFF3287d3)),
                        modifier = Modifier.padding(1.dp),
                        onClick = {
                            calc.numberPressed("A")
                            run = calc.getRunningCalculation()
                            ans = calc.getFormattedDisplay
                        }) {
                        Text("A")
                    }
                    Button(
                        colors = ButtonDefaults.buttonColors(Color(0xFF3287d3)),
                        modifier = Modifier.padding(1.dp),
                        onClick = {
                            calc.numberPressed("B")
                            run = calc.getRunningCalculation()
                            ans = calc.getFormattedDisplay
                        }) {
                        Text("B")
                    }
                    Button(
                        colors = ButtonDefaults.buttonColors(Color(0xFF3287d3)),
                        modifier = Modifier.padding(1.dp),
                        onClick = {
                            calc.numberPressed("C")
                            run = calc.getRunningCalculation()
                            ans = calc.getFormattedDisplay
                        }) {
                        Text("C")
                    }
                    Button(
                        colors = ButtonDefaults.buttonColors(Color(0xFF3287d3)),
                        modifier = Modifier.padding(1.dp),
                        onClick = {
                        }) {
                        Text("")
                    }
                    Button(
                        colors = ButtonDefaults.buttonColors(Color(0xFF3287d3)),
                        modifier = Modifier.padding(1.dp),
                        onClick = {
                        }) {
                        Text("")
                    }
                }
                Row{
                    Button(
                        colors = ButtonDefaults.buttonColors(Color(0xFF3287d3)),
                        modifier = Modifier.padding(1.dp),
                        onClick = {
                            calc.numberPressed("D")
                            run = calc.getRunningCalculation()
                            ans = calc.getFormattedDisplay
                        }) {
                        Text("D")
                    }
                    Button(
                        colors = ButtonDefaults.buttonColors(Color(0xFF3287d3)),
                        modifier = Modifier.padding(1.dp),
                        onClick = {
                            calc.numberPressed("E")
                            run = calc.getRunningCalculation()
                            ans = calc.getFormattedDisplay
                        }) {
                        Text("E")
                    }
                    Button(
                        colors = ButtonDefaults.buttonColors(Color(0xFF3287d3)),
                        modifier = Modifier.padding(1.dp),
                        onClick = {
                            calc.numberPressed("F")
                            run = calc.getRunningCalculation()
                            ans = calc.getFormattedDisplay
                        }) {
                        Text("F")
                    }
                    Button(
                        colors = ButtonDefaults.buttonColors(Color(0xFF3287d3)),
                        modifier = Modifier.padding(1.dp),
                        onClick = {
                        }) {
                        Text("")
                    }
                    Button(
                        colors = ButtonDefaults.buttonColors(Color(0xFF3287d3)),
                        modifier = Modifier.padding(1.dp),
                        onClick = {
                        }) {
                        Text("")
                    }
                }
            }
            Row{
                Button(
                    colors = ButtonDefaults.buttonColors(Color(0xFF3287d3)),
                    modifier = Modifier.padding(1.dp),
                    onClick = {
                        calc.numberPressed("7")
                        run = calc.getRunningCalculation()
                        ans = calc.getFormattedDisplay
                    }) {
                    Text("7")
                }
                Button(
                    colors = ButtonDefaults.buttonColors(Color(0xFF3287d3)),
                    modifier = Modifier.padding(1.dp),
                    onClick = {
                        calc.numberPressed("8")
                        run = calc.getRunningCalculation()
                        ans = calc.getFormattedDisplay
                    }) {
                    Text("8")
                }
                Button(
                    colors = ButtonDefaults.buttonColors(Color(0xFF3287d3)),
                    modifier = Modifier.padding(1.dp),
                    onClick = {
                        calc.numberPressed("9")
                        run = calc.getRunningCalculation()
                        ans = calc.getFormattedDisplay
                    }) {
                    Text("9")
                }
                Button(
                    colors = ButtonDefaults.buttonColors(Color(0xFF3287d3)),
                    modifier = Modifier.padding(1.dp),
                    onClick = {
                        calc.operationInitiated(calcController.Operation.MULTIPLY)
                        run = calc.getRunningCalculation()
                        ans = calc.getFormattedDisplay
                    }) {
                    Text("X")
                }

                Button(
                    colors = ButtonDefaults.buttonColors(Color(0xFF3287d3)),
                    modifier = Modifier.padding(1.dp),
                    onClick = {
                        calc.operationInitiated(calcController.Operation.COSINE)
                        run = calc.getRunningCalculation()
                        ans = calc.getFormattedDisplay
                    }) {
                    Text("cos")
                }

            }
            Row {
                Button(
                    colors = ButtonDefaults.buttonColors(Color(0xFF3287d3)),
                    modifier = Modifier.padding(1.dp),
                    onClick = {
                        calc.numberPressed("4")
                        run = calc.getRunningCalculation()
                        ans = calc.getFormattedDisplay
                    }) {
                    Text("4")
                }
                Button(
                    colors = ButtonDefaults.buttonColors(Color(0xFF3287d3)),
                    modifier = Modifier.padding(1.dp),
                    onClick = {
                        calc.numberPressed("5")
                        run = calc.getRunningCalculation()
                        ans = calc.getFormattedDisplay
                    }) {
                    Text("5")
                }
                Button(
                    colors = ButtonDefaults.buttonColors(Color(0xFF3287d3)),
                    modifier = Modifier.padding(1.dp),
                    onClick = {
                        calc.numberPressed("6")
                        run = calc.getRunningCalculation()
                        ans = calc.getFormattedDisplay
                    }) {
                    Text("6")
                }
                Button(
                    colors = ButtonDefaults.buttonColors(Color(0xFF3287d3)),
                    modifier = Modifier.padding(1.dp),
                    onClick = {
                        calc.operationInitiated(calcController.Operation.SUBTRACT)
                        run = calc.getRunningCalculation()
                        ans = calc.getFormattedDisplay
                    }) {
                    Text("-")
                }


                Button(
                    colors = ButtonDefaults.buttonColors(Color(0xFF3287d3)),
                    modifier = Modifier.padding(1.dp),
                    onClick = {
                        calc.operationInitiated(calcController.Operation.SINE)
                        run = calc.getRunningCalculation()
                        ans = calc.getFormattedDisplay
                    }) {
                    Text("sin")
                }
            }

            Row {
                Button(
                    colors = ButtonDefaults.buttonColors(Color(0xFF3287d3)),
                    modifier = Modifier.padding(1.dp),
                    onClick = {
                        calc.numberPressed("1")
                        run = calc.getRunningCalculation()
                        ans = calc.getFormattedDisplay
                    }) {
                    Text("1")
                }
                Button(
                    colors = ButtonDefaults.buttonColors(Color(0xFF3287d3)),
                    modifier = Modifier.padding(1.dp),
                    onClick = {
                        calc.numberPressed("2")
                        run = calc.getRunningCalculation()
                        ans = calc.getFormattedDisplay
                    }) {
                    Text("2")
                }
                Button(
                    colors = ButtonDefaults.buttonColors(Color(0xFF3287d3)),
                    modifier = Modifier.padding(1.dp),
                    onClick = {
                        calc.numberPressed("3")
                        run = calc.getRunningCalculation()
                        ans = calc.getFormattedDisplay
                    }) {
                    Text("3")
                }
                Button(
                    colors = ButtonDefaults.buttonColors(Color(0xFF3287d3)),
                    modifier = Modifier.padding(1.dp),
                    onClick = {
                        calc.operationInitiated(calcController.Operation.ADD)
                        run = calc.getRunningCalculation()
                        ans = calc.getFormattedDisplay
                    }) {
                    Text("+")
                }
                Button(
                    colors = ButtonDefaults.buttonColors(Color(0xFF3287d3)),
                    modifier = Modifier.padding(1.dp),
                    onClick = {
                        calc.operationInitiated(calcController.Operation.SQUARE)
                        run = calc.getRunningCalculation()
                        ans = calc.getFormattedDisplay
                    }) {
                    Text("²")
                }

            }

            Row {
                Button(
                    colors = ButtonDefaults.buttonColors(Color(0xFF3287d3)),
                    modifier = Modifier.padding(1.dp),
                    onClick = {
                        calc.negateInputPressed()
                        run = calc.getRunningCalculation()
                        ans = calc.getFormattedDisplay
                    }) {
                    Text("-")
                }
                Button(
                    colors = ButtonDefaults.buttonColors(Color(0xFF3287d3)),
                    modifier = Modifier.padding(1.dp),
                    onClick = {
                        calc.numberPressed("0")
                        run = calc.getRunningCalculation()
                        ans = calc.getFormattedDisplay
                    }) {
                    Text("0")
                }
                Button(
                    colors = ButtonDefaults.buttonColors(Color(0xFF3287d3)),
                    modifier = Modifier.padding(1.dp),
                    onClick = {
                        calc.numberPressed(".")
                        run = calc.getRunningCalculation()
                        ans = calc.getFormattedDisplay
                    }) {
                    Text(".")
                }
                Button(
                    colors = ButtonDefaults.buttonColors(Color(0xFF3287d3)),
                    modifier = Modifier.padding(1.dp),
                    onClick = {
                        calc.equalPressed()
                        run = calc.getRunningCalculation()
                        ans = calc.getFormattedDisplay
                    }) {
                    Text("=")
                }
                Button(
                    colors = ButtonDefaults.buttonColors(Color(0xFF3287d3)),
                    modifier = Modifier.padding(1.dp),
                    onClick = {
                        calc.clearCalculatorPressed()
                        run = calc.getRunningCalculation()
                        ans = calc.getFormattedDisplay
                    }) {
                    Text("c")
                }

            }
            Row {
                Button(
                    colors = ButtonDefaults.buttonColors(Color(0xFF3287d3)),
                    modifier = Modifier.padding(1.dp),
                    onClick = {
                        calc.operationInitiated(calcController.Operation.FACTORIAL)
                        run = calc.getRunningCalculation()
                        ans = calc.getFormattedDisplay
                    }) {
                    Text("!")
                }
                Button(
                    colors = ButtonDefaults.buttonColors(Color(0xFF3287d3)),
                    modifier = Modifier.padding(1.dp),
                    onClick = {
                        calc.operationInitiated(calcController.Operation.DIVIDE)
                        run = calc.getRunningCalculation()
                        ans = calc.getFormattedDisplay
                    }) {
                    Text("/")
                }
                Button(
                    colors = ButtonDefaults.buttonColors(Color(0xFF3287d3)),
                    modifier = Modifier.padding(1.dp),
                    onClick = {
                        calc.operationInitiated(calcController.Operation.SQUAREROOT)
                        run = calc.getRunningCalculation()
                        ans = calc.getFormattedDisplay
                    }) {
                    Text("√")
                }
                Button(
                    colors = ButtonDefaults.buttonColors(Color(0xFF3287d3)),
                    modifier = Modifier.padding(1.dp),
                    onClick = {
                        calc.operationInitiated(calcController.Operation.POWER)
                        run = calc.getRunningCalculation()
                        ans = calc.getFormattedDisplay
                    }) {
                    Text("^")
                }
                Button(
                    colors = ButtonDefaults.buttonColors(Color(0xFF3287d3)),
                    modifier = Modifier.padding(1.dp),
                    onClick = {
                        calc.operationInitiated(calcController.Operation.POWER)
                        run = calc.getRunningCalculation()
                        ans = calc.getFormattedDisplay
                    }) {
                    Text("^")
                }
            }
        }
}}

fun changeBase(base: String, calc: calcController) {
    if (base == "bin") {
        calc.changeNumberBase(calcController.NumberBase.BINARY)
    } else if (base == "hex") {
        calc.changeNumberBase(calcController.NumberBase.HEXIDECIMAL)
    } else if (base == "dec") {
        calc.changeNumberBase(calcController.NumberBase.DECIMAL)
    }
}


