package tw.roy.deng.codility.mapper

import tw.roy.deng.codility.data.model.NetworkSearchResult
import tw.roy.deng.codility.data.model.NetworkUser
import tw.roy.deng.codility.data.model.SearchResult
import tw.roy.deng.codility.data.model.User

class SearchResultMapperRemote : BaseMapper<NetworkSearchResult, SearchResult> {
    override fun transformToDomain(type: NetworkSearchResult): SearchResult {
        return SearchResult(
            type.total_count,
            type.incomplete_results,
            type.items.map { networkUser ->
                User(
                    networkUser.login,
                    networkUser.id,
                    networkUser.node_id,
                    networkUser.avatar_url,
                    networkUser.gravatar_id,
                    networkUser.url,
                    networkUser.html_url,
                    networkUser.followers_url,
                    networkUser.following_url,
                    networkUser.gists_url,
                    networkUser.starred_url,
                    networkUser.subscriptions_url,
                    networkUser.organizations_url,
                    networkUser.repos_url,
                    networkUser.events_url,
                    networkUser.received_events_url,
                    networkUser.type,
                    networkUser.site_admin,
                    networkUser.score
                )
            }
        )
    }

    override fun transformToDto(type: SearchResult): NetworkSearchResult {
        return NetworkSearchResult(
            type.totalCount,
            type.incompleteResults,
            type.items.map { user ->
                NetworkUser(
                    user.login,
                    user.id,
                    user.nodeId,
                    user.avatarUrl,
                    user.gravatarId,
                    user.url,
                    user.htmlUrl,
                    user.followersUrl,
                    user.followingUrl,
                    user.gistsUrl,
                    user.starredUrl,
                    user.subscriptionsUrl,
                    user.organizationsUrl,
                    user.reposUrl,
                    user.eventsUrl,
                    user.receivedEventsUrl,
                    user.type,
                    user.siteAdmin,
                    user.score
                )
            }
        )
    }
}