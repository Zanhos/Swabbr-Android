package com.laixer.swabbr.data.datasource.remote

import com.laixer.swabbr.data.datasource.VlogRemoteDataSource
import com.laixer.swabbr.datasource.model.mapToDomain
import com.laixer.swabbr.datasource.model.remote.VlogsApi
import com.laixer.swabbr.domain.model.Vlog
import io.reactivex.Single
import java.util.UUID

class VlogRemoteDataSourceImpl constructor(
    val api: VlogsApi
) : VlogRemoteDataSource {

    override fun getUserVlogs(userId: UUID): Single<List<Vlog>> = api.getUserVlogs(userId)
            .map { it.vlogs.mapToDomain() }

    override fun get(vlogId: UUID): Single<Vlog> = api.getVlog(vlogId)
        .map { it.mapToDomain() }

    override fun getRecommendedVlogs(): Single<List<Vlog>> = api.getRecommendedVlogs()
        .map { it.vlogs.mapToDomain () }
}
