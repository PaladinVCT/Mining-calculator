package by.lebedev.domain.entities

import com.google.gson.annotations.SerializedName


class Pools : ArrayList<PoolsItem>()

data class PoolsItem(
    @SerializedName("AffiliateURL")
    val affiliateURL: String,
    @SerializedName("AverageFee")
    val averageFee: String,
    @SerializedName("Coins")
    val coins: List<String>,
    @SerializedName("FeeExpanded")
    val feeExpanded: String,
    @SerializedName("Id")
    val id: String,
    @SerializedName("LogoUrl")
    val logoUrl: String,
    @SerializedName("MergedMining")
    val mergedMining: Boolean,
    @SerializedName("MergedMiningCoins")
    val mergedMiningCoins: List<Any>,
    @SerializedName("MinimumPayout")
    val minimumPayout: String,
    @SerializedName("Name")
    val name: String,
    @SerializedName("PaymentType")
    val paymentType: List<String>,
    @SerializedName("PoolFeatures")
    val poolFeatures: List<String>,
    @SerializedName("Rating")
    val rating: Rating,
    @SerializedName("Recommended")
    val recommended: Boolean,
    @SerializedName("ServerLocations")
    val serverLocations: List<String>,
    @SerializedName("SortOrder")
    val sortOrder: String,
    @SerializedName("Sponsored")
    val sponsored: Boolean,
    @SerializedName("Twitter")
    val twitter: String,
    @SerializedName("TxFeeSharedWithMiner")
    val txFeeSharedWithMiner: Boolean,
    @SerializedName("Url")
    val url: String
)

data class Rating(
    @SerializedName("Avg")
    val avg: Double,
    @SerializedName("Five")
    val five: Double,
    @SerializedName("Four")
    val four: Double,
    @SerializedName("One")
    val one: Double,
    @SerializedName("Three")
    val three: Double,
    @SerializedName("TotalUsers")
    val totalUsers: Double,
    @SerializedName("Two")
    val two: Double
)