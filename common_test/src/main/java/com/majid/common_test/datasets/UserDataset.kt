package com.majid.common_test.datasets

import com.majid.model.User
import java.util.*

object UserDataset {

    val DATE_REFRESH: Date = GregorianCalendar(2018, 5, 12).time
    val FAKE_USERS = listOf(
        User(
            id = 1,
            login = "Login_1",
            avatarUrl = "AvatarUrl_1",
            blog = "Blog1",
            company = "Company1",
            lastRefreshed = DATE_REFRESH,
            name = "Name1"
        ),
        User(
            id = 2,
            login = "Login_2",
            avatarUrl = "AvatarUrl_2",
            blog = "Blog2",
            company = "Company2",
            lastRefreshed = DATE_REFRESH,
            name = "Name2"
        ),
        User(
            id = 3,
            login = "Login_3",
            avatarUrl = "AvatarUrl_3",
            blog = "Blog3",
            company = "Company3",
            lastRefreshed = DATE_REFRESH,
            name = "Name3"
        )
    )
}