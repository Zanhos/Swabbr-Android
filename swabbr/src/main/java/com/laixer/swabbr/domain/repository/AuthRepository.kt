package com.laixer.swabbr.domain.repository

import com.laixer.swabbr.domain.model.AuthUser
import com.laixer.swabbr.domain.model.Login
import com.laixer.swabbr.domain.model.Registration
import com.laixer.swabbr.domain.model.Settings
import com.laixer.swabbr.domain.model.User
import io.reactivex.Completable
import io.reactivex.Single

interface AuthRepository {

    fun login(login: Login): Single<AuthUser>

    fun register(registration: Registration): Single<AuthUser>

//    fun logout(): Completable

    fun getToken(): String
}
