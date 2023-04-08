package android.tvz.hr.clienttracker.client_details.data.mapper

import android.content.ContentValues.TAG
import android.tvz.hr.clienttracker.data.domain.model.ClientDetails
import android.tvz.hr.clienttracker.data.local.entities.ClientEntity
import android.tvz.hr.clienttracker.data.remote.model.ClientResponse
import android.util.Log

fun ClientEntity.toClientDetails() : ClientDetails{
    return ClientDetails(
        id = id,
        name = name,
        age = age,
        picture = picture,
        aboutUser = aboutUser,
        weight = toListOfDoubles(weight = weight) ?: emptyList()
    )
}

fun toListOfDoubles(weight: String?): List<Double>? {
    return weight?.split(",\\s*".toRegex())?.map { it.toDoubleOrNull() }?.filterNotNull() ?: emptyList()
}


