package com.dmatesanz.leto.data.model

import com.dmatesanz.leto.utils.ConstantsUtil
import com.google.gson.annotations.SerializedName

data class GameListResponse(
    @SerializedName(ConstantsUtil.COUNT_PARAM) val count: Int,
    @SerializedName(ConstantsUtil.NEXT_PARAM) val next: String?,
    @SerializedName(ConstantsUtil.PREVIOUS_PARAM) val previous: String?,
    @SerializedName(ConstantsUtil.RESULTS_PARAM) val results: List<Game>
)

data class Game(
    @SerializedName(ConstantsUtil.ID_PARAM) val id: Int,
    @SerializedName(ConstantsUtil.SLUG_PARAM) val slug: String,
    @SerializedName(ConstantsUtil.NAME_PARAM) val name: String,
    @SerializedName(ConstantsUtil.RELEASED_PARAM) val released: String,
    @SerializedName(ConstantsUtil.TBA_PARAM) val tba: String,
    @SerializedName(ConstantsUtil.BACKGROUND_IMAGE_PARAM) val backgroundImage: String,
    @SerializedName(ConstantsUtil.RATING_PARAM) val rating: Float,
    @SerializedName(ConstantsUtil.RATING_TOP_PARAM) val ratingTop: Int,
    @SerializedName(ConstantsUtil.RATINGS_PARAM) val ratings: Any,
    @SerializedName(ConstantsUtil.RATINGS_COUNT_PARAM) val ratingsCount: Int,
    @SerializedName(ConstantsUtil.REVIEWS_TEXT_COUNT_PARAM) val reviewsTextCount: String,
    @SerializedName(ConstantsUtil.ADDED_PARAM) val added: Int,
    @SerializedName(ConstantsUtil.ADDED_BY_STATUS_PARAM) val addedByStatus: Any,
    @SerializedName(ConstantsUtil.METACRITIC_PARAM) val metacritic: Int,
    @SerializedName(ConstantsUtil.PLAY_TIME_PARAM) val playTime: Int,
    @SerializedName(ConstantsUtil.SUGGESTIONS_COUNT_PARAM) val suggestionsCount: Int,
    @SerializedName(ConstantsUtil.UPDATED_PARAM) val updated: String,
    @SerializedName(ConstantsUtil.ESRB_RATING_PARAM) val esrbRating: ESRBRating,
    @SerializedName(ConstantsUtil.PLATFORMS_PARAM) val platforms: List<Platform>
)

data class ESRBRating(
    @SerializedName(ConstantsUtil.ID_PARAM) val id: Int,
    @SerializedName(ConstantsUtil.SLUG_PARAM) val slug: String,
    @SerializedName(ConstantsUtil.NAME_PARAM) val name: String
)

data class Platform(
    @SerializedName(ConstantsUtil.PLATFORM_PARAM) val platform: SimplePlatform,
    @SerializedName(ConstantsUtil.RELEASED_AT_PARAM) val releasedAt: String,
    @SerializedName(ConstantsUtil.REQUIREMENTS_PARAM) val requirements: Requirements
)

data class SimplePlatform(
    @SerializedName(ConstantsUtil.ID_PARAM) val id: Int,
    @SerializedName(ConstantsUtil.SLUG_PARAM) val slug: String,
    @SerializedName(ConstantsUtil.NAME_PARAM) val name: String,
)

data class Requirements(
    @SerializedName(ConstantsUtil.MINIMUM_PARAM) val minimum: String,
    @SerializedName(ConstantsUtil.RECOMMENDED_PARAM) val recommended: String
)
