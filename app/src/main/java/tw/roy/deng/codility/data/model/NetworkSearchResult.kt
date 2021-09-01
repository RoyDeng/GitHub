package tw.roy.deng.codility.data.model

data class NetworkSearchResult(
    val total_count: Int,
    val incomplete_results: Boolean,
    val items: List<NetworkUser>
)
