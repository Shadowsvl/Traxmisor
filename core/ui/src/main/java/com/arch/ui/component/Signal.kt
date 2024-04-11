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
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import com.arch.design_system.annotations.ThemePreviews
import com.arch.design_system.theme.AppTheme
import com.arch.design_system.theme.basePadding
import com.arch.ui.R

@Composable
fun SosSignals(
    onVehicleMalfunctionSignal: (String) -> Unit,
    onAccidentSignal: (String) -> Unit,
    onFlatTiresSignal: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
    ) {
        val vehicleMalfunctionSignal = stringResource(id = R.string.signal_vehicle_malfunction)
        val accidentSignal = stringResource(id = R.string.signal_accident)
        val flatTiresSignal = stringResource(id = R.string.signal_flat_tires)

        Column(
            verticalArrangement = Arrangement.spacedBy(basePadding),
            modifier = Modifier
                .fillMaxWidth()
                .padding(basePadding)
        ) {
            Text(
                text = stringResource(id = R.string.message_sos_signals),
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(bottom = basePadding)
            )

            Button(
                onClick = { onVehicleMalfunctionSignal(vehicleMalfunctionSignal) },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = vehicleMalfunctionSignal,
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.SemiBold
                )
            }
            Button(
                onClick = { onAccidentSignal(accidentSignal) },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = accidentSignal,
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.SemiBold
                )
            }
            Button(
                onClick = { onFlatTiresSignal(flatTiresSignal) },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = flatTiresSignal,
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }
    }
}

@ThemePreviews
@Composable
private fun SosSignalsPreview() {
    AppTheme {
        SosSignals(
            onVehicleMalfunctionSignal = {},
            onAccidentSignal = {},
            onFlatTiresSignal = {},
            modifier = Modifier.fillMaxWidth()
        )
    }
}