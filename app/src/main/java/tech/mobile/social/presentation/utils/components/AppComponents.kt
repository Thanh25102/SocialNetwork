package tech.mobile.social.presentation.utils.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import tech.mobile.social.R
import tech.mobile.social.presentation.utils.validateInput
import tech.mobile.social.ui.theme.BtnColor
import tech.mobile.social.ui.theme.TextColor

@Composable
fun TxtApp(value: String = "Hello world!") {
    Text(
        text = value,
        modifier = Modifier
            .fillMaxSize()
            .heightIn(min = 80.dp),
        style = TextStyle(
            fontSize = 18.sp,
            fontWeight = FontWeight.Normal,
            fontStyle = FontStyle.Normal
        ),
        textAlign = TextAlign.Center,
        color = TextColor
    )
}

@Composable
fun TxtActApp(value: String = "Click me", onClick: () -> Unit = {}) {
    Text(
        text = value,
        modifier = Modifier
            .clickable { onClick() }
            .heightIn(min = 80.dp),
        style = TextStyle(
            fontSize = 18.sp,
            fontWeight = FontWeight.ExtraBold,
            fontStyle = FontStyle.Normal
        ),
        textAlign = TextAlign.Center,
        color = TextColor
    )
}

@Composable
fun AuthTxtApp(value: String = "Đã có tài khoản?", activeValue: String = "Đăng nhập", onClick: () -> Unit = {}) {
    Row {
        Text(
            text = value,
            modifier = Modifier
                .heightIn(min = 80.dp),
            style = TextStyle(
                fontSize = 18.sp,
                fontWeight = FontWeight.Normal,
                fontStyle = FontStyle.Normal
            ),
            textAlign = TextAlign.Center,
            color = TextColor
        )
        Text(
            text = activeValue,
            modifier = Modifier
                .clickable { onClick() }
                .heightIn(min = 80.dp),
            style = TextStyle(
                fontSize = 18.sp,
                fontWeight = FontWeight.ExtraBold,
                fontStyle = FontStyle.Normal
            ),
            textAlign = TextAlign.Center,
            color = TextColor
        )
    }
}

@Composable
fun HeadingTxtApp(value: String = "Hello world!") {
    Text(
        text = value,
        modifier = Modifier
            .fillMaxSize()
            .heightIn(min = 80.dp),
        style = TextStyle(
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            fontStyle = FontStyle.Normal
        ),
        textAlign = TextAlign.Center,
        color = TextColor
    )
}

@Composable
fun InputApp(
    modifier: Modifier = Modifier,
    onValueChange: (String) -> Unit, label: String = "",
    iconModifier: Modifier = Modifier,
    imageVector: ImageVector? = Icons.Filled.Person,
    regex: String? = null,
    errMsg: String = "Dữ liệu không hợp lệ!",
) {

    var value by rememberSaveable {
        mutableStateOf("")
    }
    var error by rememberSaveable {
        mutableStateOf(false)
    }
    var errorMessage by rememberSaveable { mutableStateOf("") }

    Column {
        OutlinedTextField(
            value = value,
            onValueChange = {
                value = it
                error = !validateInput(it, regex)
                errorMessage = if (error) errMsg else ""
                onValueChange(it)
            },
            label = { Text(text = label) },
            singleLine = true,
            leadingIcon = {
                Icon(
                    imageVector = imageVector ?: Icons.Filled.Person,
                    contentDescription = label,
                    modifier = iconModifier
                )
            },
            modifier = modifier.fillMaxWidth(),
            isError = error,
            trailingIcon = {
                if (error) {
                    Icon(
                        imageVector = Icons.Filled.Error,
                        contentDescription = "Error",
                        tint = MaterialTheme.colorScheme.error
                    )
                }
            }
        )
        if (error) {
            Text(
                text = errMsg,
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.titleSmall,
                modifier = Modifier.padding(start = 16.dp)
            )
        }
    }
}

@Composable
fun DividerWithText(text: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        HorizontalDivider(
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 10.dp)
        )
        Text(
            text = text,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            style = MaterialTheme.typography.bodyMedium
        )
        HorizontalDivider(
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 10.dp)
        )
    }
}

@Composable
fun PasswordApp(
    modifier: Modifier = Modifier,
    onValueChange: (String) -> Unit, label: String = "Mật khẩu",
    iconModifier: Modifier = Modifier,
    regex: String? = null,
    errMsg: String = "Mật khẩu không hợp lệ!",
    repeated: Boolean = false,
    repeatedPassword: String = "",
) {

    var password by remember { mutableStateOf("") }
    var passwordVisibility by remember { mutableStateOf(false) }
    var error by rememberSaveable {
        mutableStateOf(false)
    }
    var errorMessage by rememberSaveable { mutableStateOf("") }

    OutlinedTextField(
        value = password,
        onValueChange = {
            password = it
            error = if (repeated) {
                it != repeatedPassword
            } else {
                !validateInput(it, regex)
            }
            errorMessage = if (error) errMsg else ""
            onValueChange(it)
        },
        label = { Text(text = label) },
        singleLine = true,
        visualTransformation = if (passwordVisibility) VisualTransformation.None else PasswordVisualTransformation(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        leadingIcon = {
            Icon(
                imageVector = Icons.Filled.Password,
                contentDescription = "Password",
                modifier = iconModifier
            )
        },
        trailingIcon = {
            IconButton({ passwordVisibility = !passwordVisibility }) {
                Icon(
                    imageVector = if (passwordVisibility) Icons.Filled.Visibility else Icons.Filled.VisibilityOff,
                    contentDescription = "Toggle Password Visibility"
                )
            }
        },
        isError = error,
        modifier = modifier.fillMaxWidth(),
    )
    if (error) {
        Text(
            text = errMsg,
            color = MaterialTheme.colorScheme.error,
            style = MaterialTheme.typography.titleSmall,
            modifier = Modifier.padding(start = 16.dp)
        )
    }
}

@Composable
fun EmailApp(
    modifier: Modifier = Modifier,
    onValueChange: (String) -> Unit, label: String = "Email",
    iconModifier: Modifier = Modifier,
) {

    var email by remember { mutableStateOf("") }

    OutlinedTextField(
        value = email,
        onValueChange = {
            email = it
            onValueChange(it)
        },
        label = { Text(text = label) },
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
        leadingIcon = {
            Icon(
                imageVector = Icons.Filled.Mail,
                contentDescription = "Email",
                modifier = iconModifier
            )
        },
        modifier = modifier.fillMaxWidth(),
    )
}

@Composable
fun BtnApp(
    label: String = "Click me",
    modifier: Modifier = Modifier,
    backgroundColor: Color = BtnColor,
    outline: Boolean = false,
    onClick: () -> Unit = {},
) {
    if (outline) {
        OutlinedButton(
            onClick = onClick,
            modifier = modifier.widthIn(min = 48.dp),
            shape = RoundedCornerShape(10.dp),
            border = BorderStroke(1.dp, backgroundColor)
        ) {
            Text(label)
        }
    } else {
        Button(
            onClick = onClick,
            colors = ButtonDefaults.buttonColors(backgroundColor),
            modifier = modifier.widthIn(min = 48.dp),
            shape = RoundedCornerShape(10.dp)
        ) {
            Text(label)
        }
    }
}

@Preview(name = "InputApp", showBackground = true)
@Composable
fun PreviewInputApp() {
    Column(modifier = Modifier.padding(16.dp)) {
        InputApp(onValueChange = {}, label = "Username")
        PasswordApp(onValueChange = {})
        DividerWithText("OR")
        BtnApp()
        BtnApp(outline = true)
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp), horizontalArrangement = Arrangement.SpaceEvenly
        ) {

            Image(
                painter = painterResource(id = R.drawable.google),
                contentDescription = stringResource(id = R.string.login_google)
            )
            Image(
                painter = painterResource(id = R.drawable.facebook),
                contentDescription = stringResource(id = R.string.login_facebook)
            )
        }
        Row {
//            BtnApp()
            TxtActApp("Login")
            Spacer(modifier = Modifier.width(8.dp))
            AuthTxtApp()
        }

    }
}