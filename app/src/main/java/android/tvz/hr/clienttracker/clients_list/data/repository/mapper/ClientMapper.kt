package android.tvz.hr.clienttracker.clients_list.data.repository.mapper

import android.tvz.hr.clienttracker.data.domain.model.Client
import android.tvz.hr.clienttracker.data.local.entities.ClientEntity
import android.tvz.hr.clienttracker.data.remote.model.ClientResponse

fun ClientEntity.toClient(): Client {
    return Client(
        id = id,
        name = name,
        picture = picture,
        )
}

fun ClientResponse.toClientEntity(): ClientEntity {
    return ClientEntity(
        id = id,
        name = name,
        age = age,
        picture = picture,
        aboutUser = aboutUser,
        weight = weight,
        height = height
    )
}