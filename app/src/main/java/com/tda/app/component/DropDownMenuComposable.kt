package com.tda.app.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.tda.app.model.DivisionGroup
import com.tda.app.model.response.AddressResponse
import com.tda.app.ui.theme.colorPrimary
import com.tda.app.ui.theme.gray

@Composable
fun DropDownMenuAddress(
    list: List<AddressResponse>,
    content: String,
    onItemSelected: (Long) -> Unit,
    onUpdate: (Long) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    var selectedItem by remember { mutableStateOf("") }

    Column(modifier = Modifier.fillMaxWidth()) {
        Box(modifier = Modifier.fillMaxWidth()) {
            OutlinedTextField(
                value = selectedItem,
                onValueChange = {
                    selectedItem = it
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable(onClick = { expanded = true }),
                label = {
                    Text(text = content)
                },
                readOnly = true,
                trailingIcon = {
                    Icon(
                        if (expanded) Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown,
                        contentDescription = null,
                        modifier = Modifier.clickable(onClick = { expanded = !expanded })
                    )
                },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = colorPrimary,
                    focusedLabelColor = gray
                )
            )
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                modifier = Modifier.fillMaxWidth()
            ) {
                list.forEach { t ->
                    DropdownMenuItem(onClick = {
                        selectedItem = t.addressDetail
                        expanded = false
                        onItemSelected(t.addressId)
                        onUpdate(t.addressId)
                    }) {
                        Text(text = t.addressDetail)
                    }
                }
            }
        }
    }
}