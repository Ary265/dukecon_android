package org.dukecon.remote.mapper


import org.dukecon.android.api.model.Speaker
import org.dukecon.data.model.SpeakerEntity
import org.dukecon.data.source.ConferenceConfiguration
import org.dukecon.domain.aspects.twitter.TwitterLinks
import javax.inject.Inject


/**
 * Map a [Speaker] to and from a [EventEntity] instance when data is moving between
 * this later and the Data layer
 */
open class SpeakerEntityMapper @Inject constructor(val conferenceConfiguration: ConferenceConfiguration,
                                                   val twitterLinkMapper: TwitterLinks) : EntityMapper<Speaker, SpeakerEntity> {

    /**
     * Map an instance of a [org.dukecon.android.api.model.Speaker] to a [SpeakerEntity] model
     */
    override fun mapFromRemote(type: Speaker): SpeakerEntity {
        return SpeakerEntity(type.id, type.name, type.company
                ?: "", twitterLinkMapper.getNormalizedTwitterUrl(type.twitter ?: ""),
                type.bio ?: "", type.website ?: "", getAvatarUrlFromId(type.photoId ?: ""))
    }


    private fun getAvatarUrlFromId(s: String): String {
        return conferenceConfiguration.speakerAvatarUrl + s
    }

}