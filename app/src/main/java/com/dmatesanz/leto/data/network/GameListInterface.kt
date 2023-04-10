package com.dmatesanz.leto.data.network

import com.dmatesanz.leto.data.model.GameListResponse
import com.dmatesanz.leto.utils.ConstantsUtil
import retrofit2.Response
import retrofit2.http.FieldMap
import retrofit2.http.GET
import retrofit2.http.Headers

interface GameListInterface {

    @Headers("Content-Type: application/json;charset=UTF-8")
    @GET(ConstantsUtil.GAMES_PATH + ConstantsUtil.RAWG_API_KEY)
    suspend fun getGames(): Response<GameListResponse>
}
