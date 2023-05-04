package com.example.kotlinproject.domain.model

data class FavPokemon(
    var idPokemon: String,
    var email: String
)
//{
//    override fun equals(other: Any?): Boolean {
//        val fp: FavPokemon = other as FavPokemon
//        return this.idPokemon==fp.idPokemon && this.email==fp.email
//    }
//
//    override fun hashCode(): Int {
//        var result = idPokemon.hashCode()
//        result = 31 * result + email.hashCode()
//        return result
//    }
//}