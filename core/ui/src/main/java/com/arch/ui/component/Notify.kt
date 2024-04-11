package com.arch.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import com.arch.design_system.annotations.ThemePreviews
import com.arch.design_system.theme.AppTheme
import com.arch.design_system.theme.basePadding
import com.arch.ui.R

@Composable
fun NotifyCard(
    message: String,
    onActionClick: () -> Unit,
    modifier: Modifier = Modifier,
    actionText: String = stringResource(id = R.string.btn_continue),
) {
    Card(
        modifier = modifier
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(basePadding, Alignment.CenterVertically),
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .padding(basePadding)
        ) {
            Text(
                text = message,
                style = MaterialTheme.typography.bodyLarge,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.fillMaxWidth()
            )
            Button(
                onClick = onActionClick,
                modifier = Modifier
                    .padding(top = basePadding)
            ) {
                Text(text = actionText)
            }
        }
    }
}

@ThemePreviews
@Composable
private fun NotifyCardPreview() {
    AppTheme {
        NotifyCard(
            message = stringResource(id = R.string.message_location_permission),
            onActionClick = {}
        )
    }
}