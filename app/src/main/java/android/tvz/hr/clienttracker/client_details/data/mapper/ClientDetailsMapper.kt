package android.tvz.hr.clienttracker.client_details.data.mapper

import android.tvz.hr.clienttracker.data.domain.model.ClientDetails
import android.tvz.hr.clienttracker.data.local.entities.ClientEntity
import android.tvz.hr.clienttracker.data.remote.model.ClientResponse

fun ClientResponse.toClientDetails() : ClientDetails{
    return ClientDetails(
        id = id,
        name = name,
        age = age,
        picture = picture,
        aboutUser = aboutUser,
        weight = toListOfDoubles(weight)
    )
}

fun toListOfDoubles(weight: String?): List<Double>? {
    return weight?.split(", ")?.map { it.toDouble() }
}


