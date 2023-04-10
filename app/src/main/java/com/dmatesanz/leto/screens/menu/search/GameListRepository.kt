package com.dmatesanz.leto.screens.menu.search

import com.dmatesanz.leto.data.model.GameListResponse
import com.dmatesanz.leto.data.network.GameListInterface
import com.dmatesanz.leto.utils.RetrofitUtil
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class GameListRepository {

    suspend fun getGames(): Response<GameListResponse> {
        return withContext(Dispatchers.IO) {
            RetrofitUtil.instance.create(GameListInterface::class.java).getGames()
        }
    }
}
