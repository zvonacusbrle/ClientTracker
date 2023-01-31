package android.tvz.hr.clienttracker.clients_list.data.repository.mapper

import android.tvz.hr.clienttracker.data.domain.model.Client
import android.tvz.hr.clienttracker.data.local.entities.ClientEntity
import android.tvz.hr.clienttracker.data.remote.model.ClientResponse

fun ClientResponse.toClient(): Client {
    return Client(
        id = id,
        name = name,
        age = age,
        picture = picture,
        aboutUser = aboutUser
    )
}