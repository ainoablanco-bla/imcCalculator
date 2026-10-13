package org.insbaixcamp.imccalculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.insbaixcamp.imccalculator.ui.theme.BmiNormalPink
import org.insbaixcamp.imccalculator.ui.theme.BmiObesityPink
import org.insbaixcamp.imccalculator.ui.theme.BmiOverweightPink
import org.insbaixcamp.imccalculator.ui.theme.BmiUnderweightPink
import org.insbaixcamp.imccalculator.ui.theme.BorderPinkSubtle
import org.insbaixcamp.imccalculator.ui.theme.DarkBg
import org.insbaixcamp.imccalculator.ui.theme.DarkSurface
import org.insbaixcamp.imccalculator.ui.theme.DarkSurfaceVariant
import org.insbaixcamp.imccalculator.ui.theme.ImcCalculatorTheme
import org.insbaixcamp.imccalculator.ui.theme.NeonPink
import org.insbaixcamp.imccalculator.ui.theme.SoftPink
import org.insbaixcamp.imccalculator.ui.theme.TextMuted
import org.insbaixcamp.imccalculator.ui.theme.TextPrimary
import org.insbaixcamp.imccalculator.ui.theme.TextSecondary
import java.util.Locale

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ImcCalculatorTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    containerColor = DarkBg
                ) { innerPadding ->
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                    ) {
                        BMIScreen()
                    }
                }
            }
        }
    }
}

@Composable
fun BMIScreen() {
    var nom by remember { mutableStateOf("") }
    var pes by remember { mutableIntStateOf(70) }
    var alcada by remember { mutableFloatStateOf(170f) }
    var imc by remember { mutableStateOf<Double?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 22.dp, vertical = 24.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        // --- Minimal Header ---
        HeaderSection()

        // --- User Profile Card ---
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(containerColor = DarkSurface),
            border = BorderStroke(1.dp, BorderPinkSubtle)
        ) {
            Column(modifier = Modifier.padding(18.dp)) {
                Text(
                    text = "NOM DE L'USUARI",
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 1.5.sp,
                    color = SoftPink,
                    fontFamily = FontFamily.Monospace
                )
                Spacer(modifier = Modifier.height(10.dp))
                OutlinedTextField(
                    value = nom,
                    onValueChange = { nom = it },
                    placeholder = {
                        Text(
                            text = "Escriu el teu nom aquí...",
                            color = TextMuted,
                            fontSize = 14.sp
                        )
                    },
                    singleLine = true,
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedContainerColor = DarkSurfaceVariant,
                        unfocusedContainerColor = DarkSurfaceVariant,
                        focusedBorderColor = NeonPink,
                        unfocusedBorderColor = Color.Transparent,
                        focusedTextColor = TextPrimary,
                        unfocusedTextColor = TextPrimary
                    ),
                    shape = RoundedCornerShape(14.dp),
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }

        // --- Weight & Height Controls ---
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Weight Selector Card
            Card(
                modifier = Modifier.weight(1f),
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(containerColor = DarkSurface),
                border = BorderStroke(1.dp, BorderPinkSubtle)
            ) {
                Column(
                    modifier = Modifier.padding(18.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "PES",
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 1.5.sp,
                        color = TextSecondary,
                        fontFamily = FontFamily.Monospace
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Row(
                        verticalAlignment = Alignment.Bottom
                    ) {
                        Text(
                            text = "$pes",
                            fontSize = 40.sp,
                            fontWeight = FontWeight.Bold,
                            color = TextPrimary,
                            fontFamily = FontFamily.Monospace
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = "kg",
                            fontSize = 14.sp,
                            color = SoftPink,
                            modifier = Modifier.padding(bottom = 6.dp),
                            fontFamily = FontFamily.Monospace
                        )
                    }
                    Spacer(modifier = Modifier.height(12.dp))
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(12.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        IconButton(
                            onClick = { if (pes > 20) pes-- },
                            modifier = Modifier
                                .size(42.dp)
                                .clip(CircleShape)
                                .background(DarkSurfaceVariant)
                        ) {
                            Text(
                                text = "−",
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold,
                                color = SoftPink
                            )
                        }

                        IconButton(
                            onClick = { if (pes < 250) pes++ },
                            modifier = Modifier
                                .size(42.dp)
                                .clip(CircleShape)
                                .background(DarkSurfaceVariant)
                        ) {
                            Text(
                                text = "+",
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold,
                                color = SoftPink
                            )
                        }
                    }
                }
            }
        }

        // Height Selector Card
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(containerColor = DarkSurface),
            border = BorderStroke(1.dp, BorderPinkSubtle)
        ) {
            Column(
                modifier = Modifier.padding(18.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "ALÇADA",
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 1.5.sp,
                        color = TextSecondary,
                        fontFamily = FontFamily.Monospace
                    )
                    Row(verticalAlignment = Alignment.Bottom) {
                        Text(
                            text = "${alcada.toInt()}",
                            fontSize = 26.sp,
                            fontWeight = FontWeight.Bold,
                            color = TextPrimary,
                            fontFamily = FontFamily.Monospace
                        )
                        Spacer(modifier = Modifier.width(2.dp))
                        Text(
                            text = "cm",
                            fontSize = 13.sp,
                            color = SoftPink,
                            fontFamily = FontFamily.Monospace
                        )
                    }
                }

                Spacer(modifier = Modifier.height(14.dp))

                Slider(
                    value = alcada,
                    onValueChange = { alcada = it },
                    valueRange = 100f..230f,
                    colors = SliderDefaults.colors(
                        thumbColor = NeonPink,
                        activeTrackColor = NeonPink,
                        inactiveTrackColor = DarkSurfaceVariant
                    )
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text("100 cm", fontSize = 10.sp, color = TextMuted, fontFamily = FontFamily.Monospace)
                    Text("230 cm", fontSize = 10.sp, color = TextMuted, fontFamily = FontFamily.Monospace)
                }
            }
        }

        // --- Calculate Button with Gradient Pink ---
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .clip(CircleShape)
                .background(
                    brush = Brush.horizontalGradient(
                        colors = listOf(NeonPink, SoftPink)
                    )
                )
        ) {
            Button(
                onClick = {
                    val alcadaMetres = alcada / 100f
                    imc = (pes / (alcadaMetres * alcadaMetres)).toDouble()
                },
                modifier = Modifier.fillMaxSize(),
                shape = CircleShape,
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Transparent,
                    contentColor = TextPrimary
                )
            ) {
                Text(
                    text = "CALCULAR IMC",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 2.sp,
                    fontFamily = FontFamily.Monospace
                )
            }
        }

        // --- Result Dashboard ---
        imc?.let { resultImc ->
            AnimatedVisibility(
                visible = true,
                enter = fadeIn() + slideInVertically()
            ) {
                ResultCard(nom = nom, imc = resultImc)
            }
        }
    }
}

@Composable
fun HeaderSection() {
    Column {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(8.dp)
                    .clip(CircleShape)
                    .background(NeonPink)
            )
            Text(
                text = "BIOMETRICS // ONLINE",
                fontSize = 11.sp,
                fontWeight = FontWeight.Bold,
                letterSpacing = 2.sp,
                color = SoftPink,
                fontFamily = FontFamily.Monospace
            )
        }

        Spacer(modifier = Modifier.height(6.dp))

        Text(
            text = "Calculadora IMC",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            color = TextPrimary
        )

        Text(
            text = "Anàlisi corporal minimalista i estilós",
            fontSize = 13.sp,
            color = TextSecondary
        )
    }
}

@Composable
fun ResultCard(nom: String, imc: Double) {
    val (categoryText, categoryColor, progressValue) = getBmiCategoryInfo(imc)

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(containerColor = DarkSurface),
        border = BorderStroke(1.dp, categoryColor.copy(alpha = 0.5f))
    ) {
        Column(
            modifier = Modifier.padding(22.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (nom.isNotBlank()) {
                Text(
                    text = "INFORME DE ${nom.uppercase(Locale.getDefault())}",
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 1.5.sp,
                    color = SoftPink,
                    fontFamily = FontFamily.Monospace
                )
                Spacer(modifier = Modifier.height(10.dp))
            }

            Text(
                text = "ÍNDEX DE MASSA CORPORAL",
                fontSize = 10.sp,
                letterSpacing = 2.sp,
                color = TextMuted,
                fontFamily = FontFamily.Monospace
            )

            Spacer(modifier = Modifier.height(6.dp))

            // Main BMI Score Display
            Text(
                text = String.format(Locale.US, "%.1f", imc),
                fontSize = 54.sp,
                fontWeight = FontWeight.Bold,
                color = categoryColor,
                fontFamily = FontFamily.Monospace
            )

            // Category Badge
            Surface(
                shape = CircleShape,
                color = categoryColor.copy(alpha = 0.15f),
                border = BorderStroke(1.dp, categoryColor.copy(alpha = 0.4f))
            ) {
                Text(
                    text = categoryText,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    color = categoryColor,
                    letterSpacing = 1.5.sp,
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 6.dp),
                    fontFamily = FontFamily.Monospace
                )
            }

            Spacer(modifier = Modifier.height(18.dp))

            // Progress Spectrum Meter
            val animatedProgress by animateFloatAsState(
                targetValue = progressValue,
                label = "BmiProgressPink"
            )

            Column(modifier = Modifier.fillMaxWidth()) {
                LinearProgressIndicator(
                    progress = { animatedProgress },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(8.dp)
                        .clip(CircleShape),
                    color = categoryColor,
                    trackColor = DarkSurfaceVariant
                )

                Spacer(modifier = Modifier.height(8.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text("15.0", fontSize = 10.sp, color = TextMuted, fontFamily = FontFamily.Monospace)
                    Text("18.5", fontSize = 10.sp, color = TextMuted, fontFamily = FontFamily.Monospace)
                    Text("25.0", fontSize = 10.sp, color = TextMuted, fontFamily = FontFamily.Monospace)
                    Text("30.0", fontSize = 10.sp, color = TextMuted, fontFamily = FontFamily.Monospace)
                    Text("40.0", fontSize = 10.sp, color = TextMuted, fontFamily = FontFamily.Monospace)
                }
            }
        }
    }
}

private data class BmiCategoryInfo(
    val category: String,
    val color: Color,
    val progress: Float
)

private fun getBmiCategoryInfo(imc: Double): BmiCategoryInfo {
    return when {
        imc < 18.5 -> BmiCategoryInfo(
            category = "SOTAPES",
            color = BmiUnderweightPink,
            progress = ((imc - 15.0) / 25.0).coerceIn(0.05, 0.25).toFloat()
        )
        imc < 25.0 -> BmiCategoryInfo(
            category = "PES NORMAL",
            color = BmiNormalPink,
            progress = (0.25 + (imc - 18.5) / 6.5 * 0.25).coerceIn(0.25, 0.50).toFloat()
        )
        imc < 30.0 -> BmiCategoryInfo(
            category = "SOBREPES",
            color = BmiOverweightPink,
            progress = (0.50 + (imc - 25.0) / 5.0 * 0.25).coerceIn(0.50, 0.75).toFloat()
        )
        else -> BmiCategoryInfo(
            category = "OBESITAT",
            color = BmiObesityPink,
            progress = (0.75 + (imc - 30.0) / 10.0 * 0.25).coerceIn(0.75, 1.0).toFloat()
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ImcCalculatorTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = DarkBg
        ) {
            BMIScreen()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ResultPreview() {
    ImcCalculatorTheme {
        Surface(
            modifier = Modifier.fillMaxWidth(),
            color = DarkBg
        ) {
            Box(modifier = Modifier.padding(16.dp)) {
                ResultCard(nom = "Ainoa", imc = 22.4)
            }
        }
    }
}
