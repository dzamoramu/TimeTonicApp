package com.example.timetonicapp.landing

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.timetonicapp.R
import com.example.timetonicapp.data.model.AllBooks
import com.example.timetonicapp.data.model.Contact
import com.example.timetonicapp.data.model.UserAllBooks
import com.example.timetonicapp.landing.components.BookItemCard
import com.example.timetonicapp.login.components.ProgressBarLoading

@Composable
fun LandingScreen(
    viewModel: LandingViewModel,
) {
    Column(
        verticalArrangement = Arrangement.Center
    ) {
        if (viewModel.showContent.value) {
            val userBooks: UserAllBooks by viewModel.allBooks.observeAsState(
                UserAllBooks(
                    status = "",
                    allBooks = AllBooks(emptyList(), emptyList(), 0, 0, "")
                )
            )
            val (selectedContact, setSelectedContact) = remember { mutableStateOf<Contact?>(null) }

            Column(
                verticalArrangement = Arrangement.spacedBy(14.dp)
            ) {
                ContactDropDownMenu(userBooks, setSelectedContact)
                ListItems(userBooks, selectedContact)
            }
        } else {
            ProgressBarLoading(isLoading = viewModel.progressBar.value)
        }
    }
}

@Composable
fun ContactDropDownMenu(
    userBooks: UserAllBooks,
    onContactSelected: (Contact?) -> Unit,
) {
    var selectedContact by remember { mutableStateOf("") }
    var expanded by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier.padding(20.dp)
    ) {
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { expanded = true },
            value = selectedContact,
            onValueChange = { selectedContact = it },
            label = { Text(text = stringResource(id = R.string.select_option)) },
            enabled = false,
            readOnly = true,
            trailingIcon = {
                Icon(
                    imageVector = Icons.Filled.ArrowDropDown,
                    contentDescription = stringResource(id = R.string.dropdown_icon),
                    tint = Color.Black,
                    modifier = Modifier
                        .align(Alignment.End)
                        .padding(start = 4.dp)
                )
            }
        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            userBooks.allBooks.contacts.forEach { contact ->
                DropdownMenuItem(
                    text = { contact.firstName },
                    onClick = { onContactSelected(contact) }
                )
            }
        }
    }
}

@Composable
fun ListItems(
    userBooks: UserAllBooks,
    selectedContact: Contact?,
) {
    val filteredBooks = remember(userBooks, selectedContact) {
        userBooks.allBooks.books.filter { book ->
            selectedContact == null || book.members.any { it.uC == selectedContact.uC }
        }
    }

    LazyColumn {
        items(filteredBooks) { item ->
            BookItemCard(item = item)
        }
    }
}