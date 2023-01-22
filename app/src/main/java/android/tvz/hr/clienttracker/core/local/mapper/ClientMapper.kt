package android.tvz.hr.clienttracker.core.local.mapper

import android.tvz.hr.clienttracker.core.domain.model.Client
import android.tvz.hr.clienttracker.core.local.entities.ClientEntity

fun ClientEntity.toClient(): Client {
    return Client(
        id = id,
        name = name,
        age = age,
        picture = picture,
        aboutUser = aboutUser
    )
}