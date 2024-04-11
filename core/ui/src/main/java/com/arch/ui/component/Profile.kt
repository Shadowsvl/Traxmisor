package com.arch.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.arch.design_system.annotations.ThemePreviews
import com.arch.design_system.component.AppAsyncImage
import com.arch.design_system.theme.AppTheme
import com.arch.design_system.theme.basePadding
import com.arch.ui.R
import com.arch.ui.fakeUser
import com.traxion.model.data.User

@Composable
fun Profile(
    user: User,
    modifier: Modifier = Modifier
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
    ) {
        Column(
            modifier = Modifier.weight(0.7f, fill = true)
        ) {
            Text(
                text = user.fullName,
                style = MaterialTheme.typography.headlineMedium,
                color = MaterialTheme.colorScheme.onBackground
            )
            Text(
                text = "${user.vehicle} - ${user.plates}",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onBackground
            )
        }
        AppAsyncImage(
            imageUrl = user.profileImage,
            contentScale = ContentScale.Crop,
            placeholder = painterResource(R.drawable.ic_user),
            error = painterResource(R.drawable.ic_user),
            contentDescription = "Poster",
            modifier = Modifier
                .requiredSize(65.dp)
                .clip(CircleShape)
        )
    }
}

@ThemePreviews
@Composable
private fun ProfilePreview() {
    AppTheme {
        Profile(
            user = fakeUser,
            modifier = Modifier
                .fillMaxWidth()
                .padding(basePadding)
        )
    }
}