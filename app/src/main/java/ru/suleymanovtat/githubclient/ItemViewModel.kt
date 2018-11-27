package ru.suleymanovtat.githubclient

import ru.suleymanovtat.githubclient.model.data.Item

class ItemViewModel {

    public var name: String? = null
    public var avatar: String? = null

    public constructor(item: Item) {
        name = item.login
        avatar = item.avatar_url
    }
}