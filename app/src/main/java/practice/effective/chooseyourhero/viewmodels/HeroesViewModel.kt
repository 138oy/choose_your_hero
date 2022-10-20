package practice.effective.chooseyourhero.viewmodels

import practice.effective.chooseyourhero.R

class HeroesViewModel {
    private var heroesList: Map<String, Int> = mapOf(
        "Apocalypse" to R.drawable.apocalypse,
        "Captain America" to R.drawable.captainamerica,
        "Deadpool" to R.drawable.deadpool,
        "Iron Man" to R.drawable.ironman,
        "Nebula" to R.drawable.nebula,
        "Peggy Carter" to R.drawable.peggycarter,
        "Scarlet Witch" to R.drawable.scarletwitch,
        "Sif" to R.drawable.sif,
        "Spiderman" to R.drawable.spiderman,
        "Storm" to R.drawable.storm,
    )

    internal fun getHeroesList(): Map<String, Int> = heroesList
}