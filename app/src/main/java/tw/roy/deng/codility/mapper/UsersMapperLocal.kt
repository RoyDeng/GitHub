package tw.roy.deng.codility.mapper

import tw.roy.deng.codility.data.model.User
import tw.roy.deng.codility.data.source.local.entity.DBUser

class UsersMapperLocal : BaseMapper<List<DBUser>, List<User>> {
    override fun transformToDomain(type: List<DBUser>): List<User> {
        return type.map { dbUser ->
            User(
                dbUser.login,
                dbUser.id,
                dbUser.nodeId,
                dbUser.avatarUrl,
                dbUser.gravatarId,
                dbUser.url,
                dbUser.htmlUrl,
                dbUser.followersUrl,
                dbUser.followingUrl,
                dbUser.gistsUrl,
                dbUser.starredUrl,
                dbUser.subscriptionsUrl,
                dbUser.organizationsUrl,
                dbUser.reposUrl,
                dbUser.eventsUrl,
                dbUser.receivedEventsUrl,
                dbUser.type,
                dbUser.siteAdmin,
                dbUser.score
            )
        }
    }

    override fun transformToDto(type: List<User>): List<DBUser> {
        return type.map { user ->
            DBUser(
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
    }

}