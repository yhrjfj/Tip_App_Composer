package com.yhr.jfj.tipappcomposer

import android.hardware.lights.Light
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
//import androidx.compose.foundation.layout.BoxScopeInstance.align
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Minimize
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.yhr.jfj.tipappcomposer.components.InputField
import com.yhr.jfj.tipappcomposer.ui.theme.TipAppComposerTheme
import com.yhr.jfj.tipappcomposer.widgets.RoundIconsButton
import kotlin.math.nextUp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApp {
                Column {
                    MainContent()
                }
            }
        }
    }
}

// Main Function
@Composable
fun MyApp(content: @Composable () -> Unit) {
    TipAppComposerTheme {
        // A surface container using the 'background' color from the theme
        Surface(
            color = MaterialTheme.colorScheme.background
        ) {
            content()
        }
    }
}

// TopHeader Functiion where user can see how much money the need to pay
//@Preview
@Composable
fun TopHeader(totalPerPerson: Double = 134.0) {
    Surface(
        modifier = Modifier
            .padding(all = 16.dp)
            .fillMaxWidth()
            .height(200.dp)
            .clip(shape = CircleShape.copy(all = CornerSize(12.dp))),
//            .clip(shape = RoundedCornerShape(corner = CornerSize(12.dp))),
        color = Color(0xFFE9D7F7)
    ) {
        Column(
            modifier = Modifier.padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            val total = "%.2f".format(totalPerPerson)
            Text(
                text = "Total per person",
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.SemiBold
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = "$$total",
                fontWeight = FontWeight.ExtraBold,
                fontSize = 40.sp
            )
        }
    }
}

// MainContent function where user can enter amount and add person for tpis
@OptIn(ExperimentalComposeUiApi::class)
@Preview(showBackground = true)
@Composable
fun MainContent() {
    BillForm() { billAmt ->
        Log.d("Amount", "$billAmt")
    }
}

// BillForm
@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun BillForm(
    modifier: Modifier = Modifier,
    onValChange: (String) -> Unit = {},
) {
    // Amount which need to pay
    val totalBillState = remember {
        mutableStateOf("")
    }
    val validState = remember(totalBillState.value) {
        totalBillState.value.trim().isNotEmpty()
    }

    // How many person should pay
    var splitByState by remember {
        mutableStateOf(2)
    }

    // Give range so that user can not access more then that
    val range = IntRange(start = 1, endInclusive = 100)

    // Slider for entering tips percentage
    var slidePositionState by remember {
        mutableStateOf(0f)
    }

    // Show percentage in Int
    val tipPercentage = (slidePositionState * 100).nextUp().toInt()

    // Keyboard controller
    val keybordController = LocalSoftwareKeyboardController.current

    //Content
    TopHeader()
    Surface(
        modifier = Modifier
            .padding(start = 16.dp, top = 0.dp, end = 16.dp, bottom = 16.dp)
            .fillMaxWidth(),
        shape = CircleShape.copy(all = CornerSize(12.dp)),
        border = BorderStroke(width = 1.dp, color = Color.LightGray)
    ) {
        Column(
            modifier = Modifier.padding(5.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start
        ) {
            InputField(
                valueState = totalBillState,
                labelId = "Enter Bill",
                enabled = true,
                isSingleLine = true,
                onAction = KeyboardActions {
                    if (!validState) return@KeyboardActions
                    onValChange(totalBillState.value.trim())
                    keybordController?.hide()
                }
            )
//            if (validState) {
            Row(
                modifier = Modifier.padding(5.dp),
                horizontalArrangement = Arrangement.Start,
            ) {
                Text(
                    text = "Split",
                    modifier = Modifier
                        .align(alignment = Alignment.CenterVertically)
                        .padding(start = 16.dp),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold
                )
                Spacer(modifier = Modifier.width(120.dp))
                Row(
                    modifier = Modifier.padding(horizontal = 5.dp),
                    horizontalArrangement = Arrangement.End
                ) {
                    // Minus button which minimize person
                    RoundIconsButton(
                        imageVector = Icons.Default.Remove,
                        contentDescription = "Minimize",
                        onClick = {
                            if (splitByState <= 1) {
                                splitByState = 1
                            } else {
                                splitByState -= 1
                            }
                        })

                    // Number which show how many person will split the bill
                    Text(
                        text = "${splitByState}",
                        modifier = Modifier
                            .align(Alignment.CenterVertically)
                            .padding(start = 9.dp, end = 9.dp)
                    )

                    // Add button which increase person
                    RoundIconsButton(
                        imageVector = Icons.Default.Add,
                        contentDescription = "Minimize",
                        onClick = {
                            if (splitByState < range.last){
                                splitByState += 1
                            }
                        })
                }
            }

            // Tip row where user can see how mouch money should he/she need to pay
            Row(
                modifier = Modifier.padding(5.dp),
                horizontalArrangement = Arrangement.Start,
            ) {
                Text(
                    text = "Tip",
                    modifier = Modifier
                        .align(alignment = Alignment.CenterVertically)
                        .padding(start = 16.dp),
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 18.sp
                )
                Spacer(modifier = Modifier.width(200.dp))
                Text(
                    text = "$33.00",
                    modifier = Modifier.align(alignment = Alignment.CenterVertically)
                )
            }

            // Column where user can slide and select his/her tip percentage
            Column(
                modifier = Modifier
                    .padding(5.dp)
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(
                    text = "$tipPercentage%",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold
                )

                Spacer(modifier = Modifier.height(16.dp))

                Slider(
                    value = slidePositionState, onValueChange = { newVal ->
                        slidePositionState = newVal
                    },
                    modifier = Modifier.padding(start = 16.dp, end = 16.dp),
                    steps = 99
                )
            }


//            } else {
//                // When user does not give any input
//                Box {
//
//                }
//            }
        }
    }
}

//@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TipAppComposerTheme {
        MyApp {
            Text(text = "YHRJFJ")
        }
    }
}