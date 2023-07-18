package com.example.newsapp_praxisphase.utils

/**
 * Eine Hilfsklasse, die Konstanten für die News-App definiert.
 */
class Constants {

    /**
     * Ein Companion-Objekt, das die konstanten Werte enthält.
     */
    companion object {
        /**
         * Die API-Schlüsselkonstante für den Zugriff auf die News-API.
         */
        const val API_KEY = "32b73bd11b3143a59eaba34b8d37b1ee"

        /**
         * Die Basis-URL für die News-API.
         */
        const val BASE_URL = "https://newsapi.org/"

        /**
         * Die Verzögerungszeit für die Suche in Millisekunden.
         */
        const val SEARCH_TIME_DELAY = 500L
    }
}
