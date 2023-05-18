import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.shape.ZeroCornerSize
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Divider
import androidx.compose.material.LocalTextStyle
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.tda.app.navigation.Screen
import com.tda.app.ui.theme.colorPrimary
import com.tda.app.ui.theme.dark_gray
import com.tda.app.ui.theme.ghost_white
import com.tda.app.ui.theme.white
import com.tda.app.view.MessageDialog
import com.tda.app.view.ProgressIndicator
import com.tda.app.viewmodel.VerifyViewModel

@Composable
fun VerificationScreen(
    nav: NavController,
    verifyViewModel: VerifyViewModel = hiltViewModel()
) {
    val verify by verifyViewModel.state.collectAsState()
    var code by remember { mutableStateOf("") }

    var isOpened by remember {
        mutableStateOf(false)
    }
    var displayed by remember {
        mutableStateOf(false)
    }
    if (isOpened) {
        MessageDialog(
            title = "Xác thực thất bại",
            msg = "Mã xác thực không chính xác hoặc hết hạn",
            onChange = {
                isOpened = false
                verifyViewModel.continueVerify()
            }
        )
    }
    if (displayed) {
        ProgressIndicator()
    }
    when (verify) {
        0 -> {}
        1 -> {
            displayed = false
            LaunchedEffect(key1 = "") {
                nav.navigate(Screen.LoginScreen.route)
            }
        }

        2 -> {
            displayed = false
            isOpened = true
        }
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        ConstraintLayout {
            val (cartitemsbgref, checkoutref) = createRefs()
            Box(modifier = Modifier
                .height(100.dp)
                .constrainAs(cartitemsbgref) {
                    top.linkTo(cartitemsbgref.top)
                    bottom.linkTo(cartitemsbgref.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }) {
                Image(
                    painter = ColorPainter(colorPrimary),
                    contentDescription = "TDA bg",
                    contentScale = ContentScale.FillWidth
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                }
            }
            Surface(color = ghost_white,
                shape = RoundedCornerShape(40.dp).copy(
                    bottomStart = ZeroCornerSize,
                    bottomEnd = ZeroCornerSize
                ), modifier = Modifier
                    .padding(top = 70.dp)
                    .constrainAs(checkoutref) {
                        bottom.linkTo(parent.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center

                ) {
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = "Xác minh tài khoản của bạn",
                        fontWeight = FontWeight.Bold,
                        fontSize = 24.sp,
                        color = colorPrimary,
                    )
                    Divider(
                        color = Color.Gray,
                        thickness = 1.dp,
                        modifier = Modifier.padding(vertical = 16.dp)
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = "Vui lòng kiểm tra email",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Medium,
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = "Mã xác thực",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF6A6955),
                    )
                    OutlinedTextField(
                        value = code,
                        textStyle = LocalTextStyle.current.copy(
                            textAlign = TextAlign.Center,
                            fontSize = 22.sp
                        ),
                        onValueChange = {
                            code = it
                        },
                        placeholder = {
                            Text(
                                text = "X-X-X-X-X-X",
                                textAlign = TextAlign.Center,
                                modifier = Modifier.fillMaxWidth(),
                                fontSize = 18.sp
                            )
                        },
                        modifier = Modifier.padding(20.dp)

                    )

                    Button(
                        onClick = {
                            verifyViewModel.verifyAccount(token = code)
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 16.dp)
                            .height(50.dp),
                        shape = RoundedCornerShape(16.dp),
                        colors = ButtonDefaults.buttonColors(backgroundColor = colorPrimary),
                        border = BorderStroke(1.dp, dark_gray)
                    ) {
                        Text(
                            text = "Xác thực",
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp,
                            color = white,
                        )
                    }
                    Divider(
                        color = Color.Gray,
                        thickness = 1.dp,
                        modifier = Modifier.padding(vertical = 16.dp)
                    )
                    Text(
                        text = buildAnnotatedString {
                            withStyle(
                                style = SpanStyle(
                                    fontStyle = FontStyle.Italic
                                )
                            ) {
                                append("TDA Co.\n")
                            }
                            append("No. 1 Vo Van Ngan Street, Linh Chieu Ward, Thu Duc City, Ho Chi Minh City,\n")
                            append("Vietnam.")
                        },
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )

                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DefaultPreview() {
    val nav = rememberNavController()
    VerificationScreen(nav)
}