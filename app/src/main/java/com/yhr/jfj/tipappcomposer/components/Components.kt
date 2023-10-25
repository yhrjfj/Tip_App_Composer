package com.yhr.jfj.tipappcomposer.components

<<<<<<< HEAD
import androidx.compose.foundation.background
=======
>>>>>>> origin/master
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AttachMoney
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
<<<<<<< HEAD
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
=======
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
>>>>>>> origin/master
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
<<<<<<< HEAD
import androidx.compose.ui.tooling.preview.Preview
=======
>>>>>>> origin/master
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material3.OutlinedTextField as OutlinedTextField


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InputField(
    modifier: Modifier = Modifier,
    valueState: MutableState<String>,
    labelId: String,
    enabled: Boolean,
    isSingleLine: Boolean,
    keyboardType: KeyboardType = KeyboardType.Number,
    imeAction: ImeAction = ImeAction.Next,
    onAction: KeyboardActions = KeyboardActions.Default,
) {
    OutlinedTextField(
        value = valueState.value,
        onValueChange = { valueState.value = it },
        label = { Text(text = labelId) },
        leadingIcon = {
            Icon(
                imageVector = Icons.Rounded.AttachMoney,
                contentDescription = "Money Icon"
            )
        },
<<<<<<< HEAD
        modifier = modifier.padding(start = 10.dp, end = 10.dp, bottom = 10.dp).background(MaterialTheme.colorScheme.onBackground),
        singleLine = isSingleLine,
        textStyle = TextStyle(fontSize = 18.sp),
//        color = TextFieldDefaults.outlinedTextFieldColors(textColor = Color.White, placeholderColor = Color.Gray),
=======
        modifier = modifier.padding(start = 10.dp, end = 10.dp, bottom = 10.dp),
        singleLine = isSingleLine,
        textStyle = TextStyle(fontSize = 18.sp),
>>>>>>> origin/master
//        colors = MaterialTheme.colorScheme.onBackground,
        enabled = enabled,
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType, imeAction = imeAction)
    )
}
<<<<<<< HEAD

=======
>>>>>>> origin/master
