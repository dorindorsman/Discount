package com.example.discount.welcome

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.LiveRegionMode
import androidx.compose.ui.semantics.heading
import androidx.compose.ui.semantics.liveRegion
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.discount.R
import com.example.discount.ui.theme.CnnBlueColor

@Composable
fun WelcomeView(
    viewModel: WelcomeViewModel
) {

    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
    ) {
        val (
            nameRow,
            dateTimeColumn,
            emptyLabelRow,
            button,
        ) = createRefs()

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .constrainAs(nameRow) {
                    linkTo(
                        start = parent.start,
                        end = parent.end,
                    )
                    top.linkTo(parent.top, 11.dp)
                },
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = stringResource(R.string.user_name),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.h3,
                color = CnnBlueColor,
                modifier = Modifier
                    .fillMaxWidth()
                    .semantics {
                        liveRegion = LiveRegionMode.Assertive
                        heading()
                    }
            )
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .constrainAs(dateTimeColumn) {
                    linkTo(
                        start = parent.start,
                        end = parent.end,
                    )
                    top.linkTo(nameRow.bottom, 30.dp)
                },
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(id = R.string.date_time),
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.DarkGray
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = viewModel.time,
                fontSize = 18.sp,
                color = Color.Gray
            )
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .constrainAs(emptyLabelRow) {
                    linkTo(
                        start = parent.start,
                        end = parent.end,
                    )
                    top.linkTo(dateTimeColumn.bottom, 16.dp)
                },
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(id = R.string.link),
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.DarkGray
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = viewModel.linkRepository.getLink(),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.body1,
                color = CnnBlueColor,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        viewModel.handle(WelcomeEvent.WebItemClicked)
                    }
            )
        }

        Button(
            modifier = Modifier
                .constrainAs(button) {
                    linkTo(
                        end = parent.end,
                        start = parent.start
                    )
                    bottom.linkTo(parent.bottom, 16.dp)
                },
            colors = ButtonDefaults.buttonColors(CnnBlueColor),
            onClick = {
                viewModel.handle(WelcomeEvent.ContinueClicked)
            },
        ) {
            Icon(
                painter = painterResource(id = R.drawable.arrow_forward),
                contentDescription = null,
                modifier = Modifier
                    .size(26.dp)
                    .padding(end = 6.dp)
            )
            Text(
                modifier = Modifier.padding(8.dp),
                text = stringResource(R.string.next),
                style = MaterialTheme.typography.button.copy(fontWeight = FontWeight.Medium),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }


    }


}



