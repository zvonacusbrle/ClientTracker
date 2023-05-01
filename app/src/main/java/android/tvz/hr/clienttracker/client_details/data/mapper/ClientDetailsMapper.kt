package android.tvz.hr.clienttracker.client_details.data.mapper

import android.tvz.hr.clienttracker.data.domain.model.ClientDetails
import android.tvz.hr.clienttracker.data.local.entities.ClientEntity

fun ClientEntity.toClientDetails() : ClientDetails{
    return ClientDetails(
        id = id,
        name = name,
        age = age,
        picture = picture,
        aboutUser = aboutUser,
        weight = toListOfDoubles(weight = weight) ?: emptyList(),
        height = height
    )
}

fun toListOfDoubles(weight: String?): List<Double>? {
    return weight?.split(",\\s*".toRegex())?.map { it.toDoubleOrNull() }?.filterNotNull() ?: emptyList()
}


