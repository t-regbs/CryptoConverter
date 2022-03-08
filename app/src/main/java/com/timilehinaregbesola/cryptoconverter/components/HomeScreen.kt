package com.timilehinaregbesola.cryptoconverter.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.SwapHoriz
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.timilehinaregbesola.cryptoconverter.main.MainViewModel
import com.timilehinaregbesola.cryptoconverter.ui.theme.CryptoConverterTheme
import com.timilehinaregbesola.cryptoconverter.util.cryptoList
import com.timilehinaregbesola.cryptoconverter.util.fiatList
import org.koin.androidx.compose.get

@Composable
fun HomeScreen() {
    val viewModel = get<MainViewModel>()
    val conversionState = viewModel.conversion.value
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Converter", color = Color.White) },
                backgroundColor = Color.Black
            )
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color(0xFF050506))
                .padding(horizontal = 32.dp, vertical = 24.dp)
                .verticalScroll(rememberScrollState())
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                FeaturedCoinCard(
                    modifier = Modifier
                        .width(150.dp)
                        .height(150.dp),
                    backgroundColor = Color(0xFFA5CAE3),
                    name = "Bitcoin" to "BTC",
                    percentage = 6,
                    amount = 6.0286
                )
                FeaturedCoinCard(
                    modifier = Modifier
                        .width(150.dp)
                        .height(150.dp),
                    backgroundColor = Color(0xFFCEB7F0),
                    name = "Dogecoin" to "DOGE",
                    percentage = 118,
                    amount = 16.800
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                FeaturedCoinCard(
                    modifier = Modifier
                        .width(150.dp)
                        .height(150.dp),
                    backgroundColor = Color(0xFFE4E4E5),
                    name = "Ethereum" to "ETH",
                    percentage = 18,
                    amount = 0.0086
                )
                FeaturedCoinCard(
                    modifier = Modifier
                        .width(150.dp)
                        .height(150.dp),
                    backgroundColor = Color(0xFF383F48),
                    name = "Dollars" to "USDT",
                    percentage = 78,
                    amount = 7.860,
                    textColor = Color.White
                )
            }
            Spacer(Modifier.height(64.dp))
            Column(
                Modifier.padding(horizontal = 32.dp),
                verticalArrangement = Arrangement.Center
            ) {
                val sendAmountText = rememberSaveable { mutableStateOf("0.686") }
                val currSelectedCurrency = rememberSaveable { mutableStateOf("NGN") }
                val currSelectedToken = rememberSaveable { mutableStateOf("ETH") }
                Text(
                    modifier = Modifier.padding(bottom = 8.dp),
                    text = "Send",
                    fontSize = 12.sp,
                    color = Color(0xFFA2A2A6)
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    TextField(
                        modifier = Modifier.width(150.dp),
                        value = sendAmountText.value,
                        onValueChange = { sendAmountText.value = it },
                        singleLine = true,
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Number,
                            imeAction = ImeAction.Done
                        ),
                        textStyle = TextStyle(
                            color = Color.White,
                            fontSize = 21.sp,
//                            textAlign = TextAlign.Center
                        ),
                        colors = TextFieldDefaults.textFieldColors(
                            backgroundColor = Color(0xFF050506),
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent,
                            disabledIndicatorColor = Color.Transparent
                        ),
                        shape = MaterialTheme.shapes.medium.copy(CornerSize(24.dp))
                    )
                    CurrencySelector(initialDiff = 3, isCrypto = false) {
                        currSelectedCurrency.value = fiatList[it]
                    }
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Divider(
                        modifier = Modifier.width(80.dp),
                        color = Color(0xFFA2A2A6),
                    )
                    Button(
                        modifier = Modifier
                            .padding(8.dp),
                        shape = RoundedCornerShape(16.dp),
                        colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFFE4E4E5)),
                        onClick = {
                            viewModel.convert(
                                sendAmountText.value,
                                currSelectedCurrency.value,
                                currSelectedToken.value
                            )
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Filled.SwapHoriz,
                            contentDescription = "Change icon",
                            tint = Color.Black
                        )
                    }
                    Divider(
                        modifier = Modifier.width(80.dp),
                        color = Color(0xFFA2A2A6),
                    )
                }
                Text(
                    modifier = Modifier.padding(bottom = 8.dp),
                    text = "Get",
                    fontSize = 12.sp,
                    color = Color(0xFFA2A2A6)
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        modifier = Modifier.padding(bottom = 8.dp),
                        text = conversionState.coin,
                        fontSize = 21.sp,
                        color = Color.White
                    )
                    CurrencySelector(initialDiff = 11, isCrypto = true) {
                        currSelectedToken.value = cryptoList[it]
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    CryptoConverterTheme {
        HomeScreen()
    }
}
