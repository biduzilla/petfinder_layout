package com.ricky.petfinderlayout.utils

import com.ricky.petfinderlayout.data.network.models.Address
import com.ricky.petfinderlayout.data.network.models.Contact
import com.ricky.petfinderlayout.data.network.models.Photo
import com.ricky.petfinderlayout.domain.model.PetOwnerContacts
import com.ricky.petfinderlayout.domain.model.PetPhoto
import com.ricky.petfinderlayout.utils.Constants.EMPTY_DATA

object Utils {
    fun formatContacts(contact: Contact?): PetOwnerContacts {
        return PetOwnerContacts(
            address = formatData(formatAddress(contact?.address)),
            email = formatData(contact?.email),
            phone = formatData(contact?.phone)
        )
    }

    private fun formatAddress(address: Address?): String {
        val dot = "-"
        if (address != null) {
            return "${address.city}$dot${address.country}"
        }

        return ""
    }

    fun formatData(data: String?): String {
        return data ?: EMPTY_DATA
    }

    fun formatPhotos(photoList: List<Photo>?): List<PetPhoto> {
        return photoList?.map { photo ->
            PetPhoto(
                full = formatData(photo.full),
                large = formatData(photo.large),
                medium = formatData(photo.medium),
                small = formatData(photo.small)
            )
        } ?: listOf()
    }
}