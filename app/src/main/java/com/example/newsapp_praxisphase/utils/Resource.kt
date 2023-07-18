package com.example.newsapp_praxisphase.utils
/**
 * Eine abstrakte Klasse, die als Wrapper für den Status einer Ressource fungiert.
 *
 * @param T Der Typ der Daten, die in der Ressource enthalten sind.
 * @property data Die Daten der Ressource.
 * @property message Eine optionale Nachricht, die weitere Informationen über den Status der Ressource enthält.
 */
sealed class Resource<T>(

    val data: T? = null,
    val message: String? = null
) {
    /**
     * Eine Unterklasse von Resource, die den Erfolgsstatus repräsentiert und Daten enthält.
     *
     * @param T Der Typ der Daten.
     * @property data Die Daten des Erfolgsstatus.
     */
    class Success<T>(data: T) : Resource<T>(data)

    /**
     * Eine Unterklasse von Resource, die den Fehlerstatus repräsentiert und optional Daten und eine Fehlermeldung enthält.
     *
     * @param T Der Typ der Daten.
     * @property message Die Fehlermeldung.
     * @property data Die Daten des Fehlerstatus.
     */
    class Error<T>(message: String, data: T? = null) : Resource<T>(data, message)

    /**
     * Eine Unterklasse von Resource, die den Ladevorgang repräsentiert und keine Daten enthält.
     *
     * @param T Der Typ der Daten.
     */
    class Loading<T> : Resource<T>()
}
