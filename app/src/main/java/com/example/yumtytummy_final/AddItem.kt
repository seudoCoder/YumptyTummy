package com.example.yumtytummy_final


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.yumtytummy_final.ui.theme.YumtyTummy_finalTheme

 data class Food(
    val name: String,
    val canteen: String,
    val price: Double,
    val type: FoodType,
    val cuisine: String
) : (Food) -> Unit {
     override fun invoke(p1: Food) {
         TODO("Not yet implemented")
     }
 }

enum class FoodType {
    BEVERAGE,
    DESSERT,
    MAIN_COURSE
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddFoodScreen(
    onSave: (Food) -> Unit // Callback to handle saving the food item
) {
    var name by remember { mutableStateOf("") }
    var canteen by remember { mutableStateOf("") }
    var price by remember { mutableStateOf("") }
    var type by remember { mutableStateOf(FoodType.MAIN_COURSE) }
    var cuisine by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Add Food Item") }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(16.dp)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                label = { Text("Name") }
            )
            OutlinedTextField(
                value = canteen,
                onValueChange = { canteen = it },
                label = { Text("Canteen") }
            )
            OutlinedTextField(
                value = price,
                onValueChange = { price = it },
                label = { Text("Price") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )
            // Dropdown for FoodType
            ExposedDropdownMenuBox(
                expanded = false,
                onExpandedChange = { }
            ) {
                OutlinedTextField(
                    value = type.name,
                    onValueChange = {},
                    readOnly = true,
                    label = { Text("Type") },
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = false) }
                )
                ExposedDropdownMenu(
                    expanded = false,
                    onDismissRequest = { }
                ) {
                    FoodType.values().forEach { selectedType ->
                        DropdownMenuItem(
                            text = { Text(selectedType.name) },
                            onClick = {
                                type = selectedType
                            }
                        )
                    }
                }
            }
            OutlinedTextField(
                value = cuisine,
                onValueChange = { cuisine = it },
                label = { Text("Cuisine") }
            )
            Button(
                onClick = {
                    // Basic validation (you might want to add more robust checks)
                    if (name.isNotBlank() && canteen.isNotBlank() && price.isNotBlank() && cuisine.isNotBlank()) {
                        val foodItem = Food(
                            name = name,
                            canteen = canteen,
                            price = price.toDoubleOrNull() ?: 0.0,
                            type = type,
                            cuisine = cuisine
                        )
                        onSave(foodItem) // Call the save callback
                    }
                }
            ) {
                Text("Save")
            }
        }
    }
}
@Preview(showBackground = true)
@Composable
fun AddFoodScreenPreview() {
    val pizza = Food("Pizza", "Main Canteen", 12.99, FoodType.MAIN_COURSE, "Italian")
    AddFoodScreen(pizza)


}