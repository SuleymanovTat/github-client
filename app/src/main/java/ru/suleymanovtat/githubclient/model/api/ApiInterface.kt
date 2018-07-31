package ru.suleymanovtat.githubclient.model.api

import io.reactivex.Observable
import retrofit2.http.GET
import ru.suleymanovtat.githubclient.model.data.ItemResponse

interface ApiInterface {

    @GET("search/users?q=language:java+location:russia")
    fun getUsers(): Observable<ItemResponse>

}