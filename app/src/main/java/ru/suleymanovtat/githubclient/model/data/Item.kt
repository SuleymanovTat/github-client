package ru.suleymanovtat.githubclient.model.data

import android.os.Parcel
import android.os.Parcelable

class Item() : Parcelable {


    var login: String? = null

    var id: Int? = null

    var nodeId: String? = null

    var avatar_url: String? = null

    var gravatarId: String? = null

    var url: String? = null

    var html_url: String? = null

    var followersUrl: String? = null

    var followingUrl: String? = null

    var gistsUrl: String? = null

    var starredUrl: String? = null

    var subscriptionsUrl: String? = null

    var organizationsUrl: String? = null

    var reposUrl: String? = null

    var eventsUrl: String? = null

    var receivedEventsUrl: String? = null

    var type: String? = null

    var siteAdmin: Boolean? = null

    var score: Int? = null

    constructor(parcel: Parcel) : this() {
        login = parcel.readString()
        id = parcel.readValue(Int::class.java.classLoader) as? Int
        nodeId = parcel.readString()
        avatar_url = parcel.readString()
        gravatarId = parcel.readString()
        url = parcel.readString()
        html_url = parcel.readString()
        followersUrl = parcel.readString()
        followingUrl = parcel.readString()
        gistsUrl = parcel.readString()
        starredUrl = parcel.readString()
        subscriptionsUrl = parcel.readString()
        organizationsUrl = parcel.readString()
        reposUrl = parcel.readString()
        eventsUrl = parcel.readString()
        receivedEventsUrl = parcel.readString()
        type = parcel.readString()
        siteAdmin = parcel.readValue(Boolean::class.java.classLoader) as? Boolean
        score = parcel.readValue(Int::class.java.classLoader) as? Int
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(login)
        parcel.writeValue(id)
        parcel.writeString(nodeId)
        parcel.writeString(avatar_url)
        parcel.writeString(gravatarId)
        parcel.writeString(url)
        parcel.writeString(html_url)
        parcel.writeString(followersUrl)
        parcel.writeString(followingUrl)
        parcel.writeString(gistsUrl)
        parcel.writeString(starredUrl)
        parcel.writeString(subscriptionsUrl)
        parcel.writeString(organizationsUrl)
        parcel.writeString(reposUrl)
        parcel.writeString(eventsUrl)
        parcel.writeString(receivedEventsUrl)
        parcel.writeString(type)
        parcel.writeValue(siteAdmin)
        parcel.writeValue(score)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Item> {
        override fun createFromParcel(parcel: Parcel): Item {
            return Item(parcel)
        }

        override fun newArray(size: Int): Array<Item?> {
            return arrayOfNulls(size)
        }
    }


}